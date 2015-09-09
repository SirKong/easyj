package net.hs.easyj.crud.dao.impl;

import net.hs.easyj.crud.dao.GeneralQueryDao;
import net.hs.easyj.dao.mybatis.builder.PageableStatementDynamicBuilder;
import net.hs.easyj.dao.mybatis.builder.XmlStatementDynamicBuilder;
import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.crud.model.QueryDefinition;
import net.hs.easyj.model.Page;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 通用查询 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/4/20
 */
@Repository
public class GeneralQueryDaoImpl extends MyBatisDaoSupport<Map> implements GeneralQueryDao {

    @Override
    public Page<Map> findPage(Page page, Map params, QueryDefinition definition) {
        //
        String selectId = loadMappedStatementFromQueryDefinition(definition);
        //
        return selectPage(selectId, page, params);
    }

    private String loadMappedStatementFromQueryDefinition(QueryDefinition definition) {
        String selectId = generateSelectId(definition);
        String resource = "Dynamic Query Resource [" + selectId + "]";
        Configuration configuration = getSqlSession().getConfiguration();
        if(configuration.hasStatement(getIdPrefix().concat(selectId))) {
            return selectId;
        }
        //
        XmlStatementDynamicBuilder xmlStatementDynamicBuilder = new XmlStatementDynamicBuilder(configuration, resource)
                .id(selectId)
                .namespace(GeneralQueryDao.class.getName())
                .parameterType(Map.class)
                .resultType(Map.class)
                .sqlScript(definition.getQuery());
        //
        MappedStatement mappedStatement = xmlStatementDynamicBuilder.build();
        //
        PageableStatementDynamicBuilder countStatementDynamicBuilder = new PageableStatementDynamicBuilder(mappedStatement.getId() + "Count", mappedStatement);
        countStatementDynamicBuilder.setCurrentNamespace(GeneralQueryDao.class.getName());
        PageableStatementDynamicBuilder pageStatementDynamicBuilder = new PageableStatementDynamicBuilder(mappedStatement.getId(), mappedStatement);
        pageStatementDynamicBuilder.setCurrentNamespace(GeneralQueryDao.class.getName());
        countStatementDynamicBuilder.build(PageableStatementDynamicBuilder.Mode.COUNT);
        pageStatementDynamicBuilder.build(PageableStatementDynamicBuilder.Mode.PAGE);
        //
        return selectId;
    }

    private String generateSelectId(QueryDefinition definition) {
        StringBuffer sb = new StringBuffer(definition.getName());
        if(definition.getVersion()!=null) {
            sb.append("_").append(definition.getVersion().replaceAll("\\.", "_"));
        }
        return sb.toString();
    }

}
