package net.hs.easyj.config.provider.filetransfer;

import net.hs.easyj.config.provider.filetransfer.FileTransfer;
import net.hs.easyj.config.spi.ObjectDelegate;

import java.io.File;

/**
 * Sftp 文件传输委托
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
public class FileTransferDelegate implements ObjectDelegate<FileTransfer>, FileTransfer {

    private FileTransfer target;

    public FileTransfer getTarget() {
        return target;
    }

    @Override
    public void setTarget(FileTransfer target) {
        this.target = target;
    }

    @Override
    public void send(File file, String path) {
        getTarget().send(file, path);
    }

    @Override
    public void receive(String path, File file) {
        getTarget().receive(path, file);
    }
}
