package net.hs.easyj.config.provider.datasource.dbcp;

import net.hs.easyj.config.spi.ObjectCreator;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

/**
 * Commons DBCP 数据源对象创建者
 *
 * @author Gavin Hu
 * @create 2015/8/10
 */
public class DbcpDataSourceCreator implements ObjectCreator<DbcpDataSourceConfig, DataSource> {

    @Override
    public DataSource create(DbcpDataSourceConfig config) {
        //
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(config.getUrl());
        dataSource.setDriverClassName(config.getDriver());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        dataSource.setInitialSize(config.getInitialSize());
        dataSource.setMaxActive(config.getMaxActive());
        dataSource.setMaxIdle(config.getMaxIdle());
        //
        return dataSource;
    }

}
