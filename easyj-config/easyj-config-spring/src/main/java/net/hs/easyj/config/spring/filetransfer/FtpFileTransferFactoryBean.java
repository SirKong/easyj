package net.hs.easyj.config.spring.filetransfer;

import net.hs.easyj.config.provider.filetransfer.FileTransfer;
import net.hs.easyj.config.provider.filetransfer.FileTransferDelegate;
import net.hs.easyj.config.provider.filetransfer.ftp.FtpFileTransferConfig;
import net.hs.easyj.config.provider.filetransfer.ftp.FtpFileTransferCreator;
import net.hs.easyj.config.spring.ObjectConfigFactoryBean;

/**
 * Ftp 文件传输工厂类
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
public class FtpFileTransferFactoryBean extends ObjectConfigFactoryBean<FileTransfer> {

    public FtpFileTransferFactoryBean() {
        super.setCategory("filetransfer/ftp");
        //
        super.setObjectType(FileTransfer.class);
        super.setObjectCreator(new FtpFileTransferCreator());
        super.setObjectDelegate(new FileTransferDelegate());
        super.setObjectConfigClass(FtpFileTransferConfig.class);
    }

}
