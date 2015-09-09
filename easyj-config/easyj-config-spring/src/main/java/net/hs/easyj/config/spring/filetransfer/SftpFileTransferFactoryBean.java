package net.hs.easyj.config.spring.filetransfer;

import net.hs.easyj.config.provider.filetransfer.FileTransfer;
import net.hs.easyj.config.provider.filetransfer.FileTransferDelegate;
import net.hs.easyj.config.provider.filetransfer.sftp.SftpFileTransferConfig;
import net.hs.easyj.config.provider.filetransfer.sftp.SftpFileTransferCreator;
import net.hs.easyj.config.spring.ObjectConfigFactoryBean;

/**
 * Sftp 文件传输工厂类
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
public class SftpFileTransferFactoryBean extends ObjectConfigFactoryBean<FileTransfer> {

    public SftpFileTransferFactoryBean() {
        super.setCategory("filetransfer/sftp");
        //
        super.setObjectType(FileTransfer.class);
        super.setObjectCreator(new SftpFileTransferCreator());
        super.setObjectDelegate(new FileTransferDelegate());
        super.setObjectConfigClass(SftpFileTransferConfig.class);
    }
}
