package net.hs.easyj.config.provider.filetransfer.sftp;

import net.hs.easyj.config.provider.filetransfer.FileTransfer;
import net.hs.easyj.config.provider.filetransfer.FileTransferConfig;
import net.hs.easyj.config.provider.filetransfer.ftp.FtpFileTransfer;
import net.hs.easyj.config.spi.ObjectCreator;

/**
 * Sftp 文件传输对象创建者
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
public class SftpFileTransferCreator implements ObjectCreator<FileTransferConfig, FileTransfer> {
    @Override
    public FileTransfer create(FileTransferConfig config) {
        SftpFileTransfer fileTransfer = new SftpFileTransfer();
        fileTransfer.setHost(config.getHost());
        fileTransfer.setPort(config.getPort());
        fileTransfer.setUsername(config.getUsername());
        fileTransfer.setPassword(config.getPassword());
        //
        return fileTransfer;
    }
}
