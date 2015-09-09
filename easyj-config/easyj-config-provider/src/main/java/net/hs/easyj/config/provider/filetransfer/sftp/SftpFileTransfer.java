package net.hs.easyj.config.provider.filetransfer.sftp;

import net.hs.easyj.config.provider.filetransfer.AbstractFileTransfer;

/**
 * Sftp 文件传输
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
public class SftpFileTransfer extends AbstractFileTransfer {

    public SftpFileTransfer() {
        super.setSchema("sftp");
    }
}
