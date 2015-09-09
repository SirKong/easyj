package net.hs.easyj.config.provider.filetransfer.sftp;

import net.hs.easyj.config.provider.filetransfer.FileTransferConfig;
import net.hs.easyj.config.spi.annotation.ConfigCategory;

/**
 * Sftp 文件传输配置
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
@ConfigCategory(value = "/filetransfer/sftp", label = "文件传输配置(sftp)")
public class SftpFileTransferConfig extends FileTransferConfig {

}
