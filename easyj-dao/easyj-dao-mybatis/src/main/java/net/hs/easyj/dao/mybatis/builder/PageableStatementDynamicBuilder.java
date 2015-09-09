package net.hs.easyj.dao.mybatis.builder;

import net.hs.easyj.dao.mybatis.sqlsource.SqlSourceBuilder;
import net.hs.easyj.dao.mybatis.sqlsource.MySQLSqlSourceBuilder;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态分页 MappedStatement 创建者
 * 当前实现仅为支持数据库分页，且仅支持 MySQL
 *
 * @author Gavin Hu
 * @create 2015/3/5
 */
public class PageableStatementDynamicBuilder {

    public enum Mode {
        COUNT,
        PAGE
    }

    private String statementId;

    private MappedStatement originalStatement;

    private MapperBuilderAssistant mapperBuilderAssistant;

    private static Map<String, SqlSourceBuilder> sqlSourceBuilderMap = new HashMap<String, SqlSourceBuilder>();

    private static Field sqlSourceInMappedStatement = ReflectionUtils.findField(MappedStatement.class, "sqlSource");

    static {
        sqlSourceBuilderMap.put("mysql", new MySQLSqlSourceBuilder());
        //
        ReflectionUtils.makeAccessible(sqlSourceInMappedStatement);
    }

    public PageableStatementDynamicBuilder(String statementId, MappedStatement originalStatement) {
        this.statementId = statementId;
        this.originalStatement = originalStatement;
        this.mapperBuilderAssistant = new MapperBuilderAssistant(originalStatement.getConfiguration(), originalStatement.getResource());
    }

    public void setCurrentNamespace(String namespace) {
        this.mapperBuilderAssistant.setCurrentNamespace(namespace);
    }

    public void build(Mode mode) {
        //
        SqlSource sqlSource = originalStatement.getSqlSource();
        String databaseId = originalStatement.getDatabaseId()==null ?  "mysql" : originalStatement.getDatabaseId();
        SqlSourceBuilder sqlSourceBuilder = sqlSourceBuilderMap.get(databaseId);
        if(mode== Mode.COUNT) {
            sqlSource = sqlSourceBuilder.buildCount(sqlSource);
            addMappedStatement(sqlSource, Long.class);
        }
        if(mode== Mode.PAGE) {
            sqlSource = sqlSourceBuilder.buildPage(sqlSource);
            updateMappedStatement(sqlSource);
        }
    }

    private void addMappedStatement(SqlSource sqlSource, Class<?> resultType) {
        mapperBuilderAssistant.addMappedStatement(
                statementId,
                sqlSource,
                originalStatement.getStatementType(),
                originalStatement.getSqlCommandType(),
                originalStatement.getFetchSize(),
                originalStatement.getTimeout(),
                null,
                null,
                null,
                resultType,
                originalStatement.getResultSetType(),
                originalStatement.isFlushCacheRequired(),
                originalStatement.isUseCache(),
                originalStatement.isResultOrdered(),
                null,
                null,
                null,
                originalStatement.getDatabaseId(),
                originalStatement.getLang(),
                null);
    }

    private void updateMappedStatement(SqlSource sqlSource) {
        ReflectionUtils.setField(sqlSourceInMappedStatement, originalStatement, sqlSource);
    }

}
