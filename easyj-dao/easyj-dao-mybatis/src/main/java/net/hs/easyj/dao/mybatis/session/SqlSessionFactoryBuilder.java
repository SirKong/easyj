package net.hs.easyj.dao.mybatis.session;

import net.hs.easyj.dao.mybatis.builder.PageableStatementDynamicBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 重写 SqlSessionFactory 创建者，支持动态 MappedStatement 创建
 *
 * @author Gavin Hu
 * @create 2015/3/4
 */
public class SqlSessionFactoryBuilder extends org.apache.ibatis.session.SqlSessionFactoryBuilder {

    @Override
    public SqlSessionFactory build(Configuration configuration) {
        //
        List<PageableStatementDynamicBuilder> countPageableStatementDynamicBuilders = new ArrayList<PageableStatementDynamicBuilder>();
        List<PageableStatementDynamicBuilder> pagePageableStatementDynamicBuilders = new ArrayList<PageableStatementDynamicBuilder>();
        for(String statementName : configuration.getMappedStatementNames()) {
            if(!statementName.contains(".") || !statementName.contains("selectPage")) {
                continue;
            }
            String countStatementName = statementName + "Count";
            int indexOfLastDot = statementName.lastIndexOf(".");
            String namespace = statementName.substring(0, indexOfLastDot);
            MappedStatement mappedStatement = configuration.getMappedStatement(statementName);
            // count
            if(!configuration.hasStatement(countStatementName)) {
                PageableStatementDynamicBuilder pageableStatementDynamicBuilder = new PageableStatementDynamicBuilder(countStatementName, mappedStatement);
                pageableStatementDynamicBuilder.setCurrentNamespace(namespace);
                countPageableStatementDynamicBuilders.add(pageableStatementDynamicBuilder);
            }
            // page
            PageableStatementDynamicBuilder pageableStatementDynamicBuilder = new PageableStatementDynamicBuilder(statementName, mappedStatement);
            pageableStatementDynamicBuilder.setCurrentNamespace(namespace);
            pagePageableStatementDynamicBuilders.add(pageableStatementDynamicBuilder);
        }
        //
        for(PageableStatementDynamicBuilder countPageableStatementDynamicBuilder : countPageableStatementDynamicBuilders) {
            countPageableStatementDynamicBuilder.build(PageableStatementDynamicBuilder.Mode.COUNT);
        }
        for(PageableStatementDynamicBuilder pageableStatementDynamicBuilder : pagePageableStatementDynamicBuilders) {
            pageableStatementDynamicBuilder.build(PageableStatementDynamicBuilder.Mode.PAGE);
        }
        //
        return super.build(configuration);
    }
}
