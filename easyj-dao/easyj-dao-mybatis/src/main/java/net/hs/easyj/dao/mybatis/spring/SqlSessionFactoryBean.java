package net.hs.easyj.dao.mybatis.spring;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.Properties;

/**
 * @author gavin
 * @create 15/6/21
 */
public class SqlSessionFactoryBean extends org.mybatis.spring.SqlSessionFactoryBean {

    private SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new net.hs.easyj.dao.mybatis.session.SqlSessionFactoryBuilder();

    @Override
    public void setSqlSessionFactoryBuilder(SqlSessionFactoryBuilder sqlSessionFactoryBuilder) {
        this.sqlSessionFactoryBuilder = sqlSessionFactoryBuilder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //
        DatabaseIdProvider databaseIdProvider = getDatabaseIdProvider();
        if(databaseIdProvider==null) {
            //
            Properties properties = new Properties();
            properties.setProperty("MySQL", "mysql");
            properties.setProperty("Oracle", "oracle");
            properties.setProperty("SQL Server", "sqlserver");
            //
            databaseIdProvider = new VendorDatabaseIdProvider();
            databaseIdProvider.setProperties(properties);
            //
            super.setDatabaseIdProvider(databaseIdProvider);
        }
        //
        super.setSqlSessionFactoryBuilder(sqlSessionFactoryBuilder);
        //
        super.afterPropertiesSet();
    }

}
