package net.hs.easyj.dao.mybatis.plugin;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitorAdapter;
import com.alibaba.druid.sql.dialect.oracle.parser.OracleStatementParser;
import com.alibaba.druid.sql.parser.SQLParser;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import net.hs.easyj.dao.mybatis.sharding.model.FragmentTable;
import net.hs.easyj.dao.mybatis.sharding.ShardingManager;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * 分库插件
 *
 * @author gavin
 * @create 15/6/21
 */
@Intercepts({
        @Signature(type= StatementHandler.class, method = "prepare", args = {Connection.class})
})
public class ShardingInterceptor implements Interceptor {

    public static ThreadLocal<Boolean> CURRENT_THREAD_SKIP = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return Boolean.FALSE;
        }
    };

    private ShardingManager shardingManager;

    private ObjectFactory objectFactory = new DefaultObjectFactory();

    private ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

    private ReflectorFactory reflectorFactory = new DefaultReflectorFactory();

    public void setShardingManager(ShardingManager shardingManager) {
        this.shardingManager = shardingManager;
    }

    public Object intercept(Invocation invocation) throws Throwable {
        //
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //
        if(!CURRENT_THREAD_SKIP.get()) {
            processStatementHandler(statementHandler);
        }
        //
        return invocation.proceed();
    }

    protected void processStatementHandler(StatementHandler statementHandler) {
        MetaObject statementHandlerMetaObject = MetaObject.forObject(statementHandler, objectFactory, objectWrapperFactory, reflectorFactory);
        //
        Configuration configuration = (Configuration) statementHandlerMetaObject.getValue("delegate.configuration");
        BoundSql boundSql = statementHandler.getBoundSql();
        //
        Long id = null;
        for(ParameterMapping parameterMapping : boundSql.getParameterMappings()) {
            //
            String property = parameterMapping.getProperty();
            if(property.contains("id")) {
                //
                Object parameterObject = boundSql.getParameterObject();
                if(ClassUtils.isAssignable(Long.class, parameterMapping.getJavaType())) {
                    id = (Long) parameterObject;
                } else {
                    MetaObject parameterMetaObject = MetaObject.forObject(parameterObject, objectFactory, objectWrapperFactory, reflectorFactory);
                    id = (Long) parameterMetaObject.getValue(property);
                }
            }
        }
        // sharding
        if(id!=null) {
            SQLParser sqlParser = SQLParserUtils.createSQLStatementParser(boundSql.getSql(), configuration.getDatabaseId());
            if(sqlParser instanceof MySqlStatementParser) {
                processMySQLSharding(boundSql, id, (MySqlStatementParser) sqlParser);
            } else if(sqlParser instanceof OracleStatementParser) {
                throw new UnsupportedOperationException();
            }
        }
    }

    protected void processMySQLSharding(final BoundSql boundSql, final Long id, final MySqlStatementParser mySqlStatementParser) {
        MetaObject boundSqlMetaObject = MetaObject.forObject(boundSql, objectFactory, objectWrapperFactory, reflectorFactory);
        //
        List<SQLStatement> sqlStatements = mySqlStatementParser.parseStatementList();
        Assert.isTrue(sqlStatements.size() == 1);
        //
        SQLStatement sqlStatement = sqlStatements.get(0);
        //
        sqlStatement.accept(new MySqlASTVisitorAdapter(){
            @Override
            public boolean visit(SQLExprTableSource x) {
                //
                String table = x.getExpr().toString();
                //
                FragmentTable fragmentTable = shardingManager.lookup(table, id);
                if(fragmentTable!=null) {
                    x.setExpr(new SQLIdentifierExpr(fragmentTable.getName()));
                }
                //
                return true;
            }
        });
        //
        boundSqlMetaObject.setValue("sql", sqlStatement.toString());
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {

    }

}
