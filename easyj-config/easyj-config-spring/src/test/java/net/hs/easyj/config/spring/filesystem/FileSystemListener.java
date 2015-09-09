package net.hs.easyj.config.spring.filesystem;

import net.hs.easyj.config.provider.filesystem.FileSystem;
import net.hs.easyj.config.spi.ObjectListener;

/**
 * @author gavin
 * @create 15/8/13
 */
public class FileSystemListener implements ObjectListener<FileSystem> {
    @Override
    public void change(FileSystem fileSystem) {
        //
        System.out.println(fileSystem);
    }
}
