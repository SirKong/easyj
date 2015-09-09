package net.hs.easyj.config.provider.filesystem.virtual;

import net.hs.easyj.config.spi.ObjectConfig;
import net.hs.easyj.config.spi.annotation.ConfigCategory;
import net.hs.easyj.config.spi.annotation.ConfigField;

/**
 * @author gavin
 * @create 15/8/13
 */
@ConfigCategory(value = "filesystem/virtual", label = "文件系统配置(virtual)")
public class VirtualFileSystemConfig extends ObjectConfig {

    @ConfigField(description = "协议", required = true)
    private String schema;

    @ConfigField(description = "主机", required = true)
    private String host;

    @ConfigField(description = "端口", required = true)
    private String port;

    @ConfigField(description = "用户", required = true)
    private String username;

    @ConfigField(description = "密码", required = true)
    private String password;

    @ConfigField(description = "路径")
    private String path;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
