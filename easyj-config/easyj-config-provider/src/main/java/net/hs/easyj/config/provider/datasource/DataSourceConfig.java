package net.hs.easyj.config.provider.datasource;

import net.hs.easyj.config.spi.ObjectConfig;
import net.hs.easyj.config.spi.annotation.ConfigField;

/**
 * 数据源配置
 *
 * @author Gavin Hu
 * @create 2015/8/10
 */
public class DataSourceConfig extends ObjectConfig {

    @ConfigField(description = "jdbc url")
    private String url;

    @ConfigField(description = "jdbc driver")
    private String driver;

    @ConfigField(description = "jdbc username")
    private String username;

    @ConfigField(description = "jdbc password")
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
