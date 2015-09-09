package net.hs.easyj.config.provider.filetransfer;

import net.hs.easyj.config.spi.ObjectConfig;
import net.hs.easyj.config.spi.annotation.ConfigField;

/**
 * 文件传输配置
 *
 * @author gavin
 * @create 15/8/13
 */
public class FileTransferConfig extends ObjectConfig {

    @ConfigField(description = "主机", required = true)
    private String host;

    @ConfigField(description = "端口", required = true)
    private String port;

    @ConfigField(description = "用户", required = true)
    private String username;

    @ConfigField(description = "密码", required = true)
    private String password;

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
}
