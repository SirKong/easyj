package net.hs.easyj.dao.mybatis.spring.datasource;

import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author gavin
 * @create 15/6/21
 */
public class ShardingDataSource extends LazyConnectionDataSourceProxy {

    private DynamicDataSource dynamicDataSource;

    private DataSource defaultTargetDataSource;

    private Map<Object, Object> targetDataSources;

    public void setDefaultTargetDataSource(DataSource defaultTargetDataSource) {
        this.defaultTargetDataSource = defaultTargetDataSource;
    }

    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        this.targetDataSources = targetDataSources;
    }

    @Override
    public void afterPropertiesSet() {
        this.dynamicDataSource = new DynamicDataSource();
        //
        this.dynamicDataSource.setDefaultTargetDataSource(defaultTargetDataSource);
        this.dynamicDataSource.setTargetDataSources(targetDataSources);
        this.dynamicDataSource.afterPropertiesSet();
        //
        super.setTargetDataSource(dynamicDataSource);
        super.afterPropertiesSet();
    }

}
