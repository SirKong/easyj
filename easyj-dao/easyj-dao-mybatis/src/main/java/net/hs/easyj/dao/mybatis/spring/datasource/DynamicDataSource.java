package net.hs.easyj.dao.mybatis.spring.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源支持多数据源切换
 *
 * @author Gavin Hu
 * @create 2015/6/5
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();

    public static void setDataSourceKey(String dataSourceKey) {
        dataSourceHolder.set(dataSourceKey);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceHolder.get();
    }

}
