package net.hs.easyj.config.spring.datasource;

import net.hs.easyj.config.provider.datasource.dbcp.DbcpDataSourceConfig;
import net.hs.easyj.config.provider.datasource.dbcp.DbcpDataSourceCreator;
import net.hs.easyj.config.provider.datasource.dbcp.DbcpDataSourceDelegate;
import net.hs.easyj.config.spring.ObjectConfigFactoryBean;

import javax.sql.DataSource;

/**
 * Commons DBCP 数据源工厂类
 *
 * @author Gavin Hu
 * @create 2015/8/12
 */
public class DbcpDataSourceFactoryBean extends ObjectConfigFactoryBean {

    public DbcpDataSourceFactoryBean() {
        super.setCategory("datasource/dbcp");
        //
        super.setObjectType(DataSource.class);
        super.setObjectCreator(new DbcpDataSourceCreator());
        super.setObjectDelegate(new DbcpDataSourceDelegate());
        super.setObjectConfigClass(DbcpDataSourceConfig.class);
    }

}
