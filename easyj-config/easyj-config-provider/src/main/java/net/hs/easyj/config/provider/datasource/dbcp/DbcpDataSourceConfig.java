package net.hs.easyj.config.provider.datasource.dbcp;

import net.hs.easyj.config.provider.datasource.DataSourceConfig;
import net.hs.easyj.config.spi.annotation.ConfigCategory;
import net.hs.easyj.config.spi.annotation.ConfigField;

/**
 * @author Gavin Hu
 * @create 2015/8/10
 */
@ConfigCategory(value="/datasource/dbcp", label = "数据源配置 (DBCP)")
public class DbcpDataSourceConfig extends DataSourceConfig {

    @ConfigField(description = "初始连接数")
    private int initialSize = 1;

    @ConfigField(description = "最大连接数")
    private int maxActive = 5;

    @ConfigField(description = "最大空闲连接数")
    private int maxIdle = 5;

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }
}
