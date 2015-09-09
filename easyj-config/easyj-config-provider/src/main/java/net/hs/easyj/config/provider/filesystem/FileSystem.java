package net.hs.easyj.config.provider.filesystem;

import java.io.File;
import java.io.IOException;

/**
 * 文件系统
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
public interface FileSystem {

    /**
     * 获取制定路径文件
     * @param path
     * @return
     */
    File getFile(String path) throws IOException;

}
