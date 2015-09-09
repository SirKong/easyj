package net.hs.easyj.config.spring.filesystem;

import net.hs.easyj.config.provider.filesystem.FileSystem;
import net.hs.easyj.config.provider.filesystem.FileSystemDelegate;
import net.hs.easyj.config.provider.filesystem.virtual.VirtualFileSystemConfig;
import net.hs.easyj.config.provider.filesystem.virtual.VirtualFileSystemCreator;
import net.hs.easyj.config.spring.ObjectConfigFactoryBean;

/**
 * 虚拟文件系统工厂类
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
public class VirtualFileSystemFactoryBean extends ObjectConfigFactoryBean<FileSystem> {

    public VirtualFileSystemFactoryBean() {
        super.setCategory("filesystem/virtual");
        //
        super.setObjectType(FileSystem.class);
        super.setObjectCreator(new VirtualFileSystemCreator());
        super.setObjectDelegate(new FileSystemDelegate());
        super.setObjectConfigClass(VirtualFileSystemConfig.class);
    }

}
