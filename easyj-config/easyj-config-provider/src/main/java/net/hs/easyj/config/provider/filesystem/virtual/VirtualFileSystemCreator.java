package net.hs.easyj.config.provider.filesystem.virtual;

import net.hs.easyj.config.provider.filesystem.FileSystem;
import net.hs.easyj.config.spi.ObjectCreator;

/**
 * @author gavin
 * @create 15/8/13
 */
public class VirtualFileSystemCreator implements ObjectCreator<VirtualFileSystemConfig, FileSystem> {

    @Override
    public FileSystem create(VirtualFileSystemConfig config) {
        //
        VirtualFileSystem fileSystem = new VirtualFileSystem();
        fileSystem.setSchema(config.getSchema());
        fileSystem.setHost(config.getHost());
        fileSystem.setPort(config.getPort());
        fileSystem.setUsername(config.getUsername());
        fileSystem.setPassword(config.getPassword());
        fileSystem.setPath(config.getPath());
        //
        return fileSystem;
    }

}
