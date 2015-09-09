package net.hs.easyj.config.provider.filetransfer;

import java.io.File;

/**
 * 文件传输
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
public interface FileTransfer {

    /**
     * 上传文件
     * @param file
     * @param path
     */
    void send(File file, String path);

    /**
     * 下载文件
     * @param path
     * @param file
     */
    void receive(String path, File file);

}
