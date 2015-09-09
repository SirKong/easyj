package net.hs.easyj.config.provider.filesystem;

import net.hs.easyj.config.spi.ObjectDelegate;

import java.io.File;
import java.io.IOException;

/**
 * 文件系统委托
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
public class FileSystemDelegate implements ObjectDelegate<FileSystem>, FileSystem {

    private FileSystem target;

    public FileSystem getTarget() {
        return target;
    }

    @Override
    public void setTarget(FileSystem target) {
        this.target = target;
    }

    @Override
    public File getFile(String path) throws IOException {
        return getTarget().getFile(path);
    }

}
