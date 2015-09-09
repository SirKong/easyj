package net.hs.easyj.config.provider.filetransfer.ftp;

import net.hs.easyj.config.provider.filetransfer.AbstractFileTransfer;

/**
 * Ftp 文件传输
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
public class FtpFileTransfer extends AbstractFileTransfer {

    public FtpFileTransfer() {
        super.setSchema("sftp");
    }

}
