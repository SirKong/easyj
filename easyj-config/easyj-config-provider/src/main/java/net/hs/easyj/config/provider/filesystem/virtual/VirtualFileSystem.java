package net.hs.easyj.config.provider.filesystem.virtual;

import net.hs.easyj.config.provider.filesystem.FileSystem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.provider.sftp.SftpFileObject;

import java.io.File;
import java.io.IOException;

/**
 * 虚拟文件系统
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
public class VirtualFileSystem implements FileSystem {

    private String schema;

    private String host;

    private String port;

    private String username;

    private String password;

    private String path;

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String buildUri(String relativePath) {
        if(path!=null && path.trim().length()>0) {
            relativePath = path + relativePath;
        }
        return String.format("%s://%s:%s@%s:%s%s", schema, username, password, host, port, relativePath);
    }

    @Override
    public File getFile(String path) throws IOException {
        File file = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".tmp");
        //
        SftpFileObject sftpFileObject = (SftpFileObject) VFS.getManager().resolveFile(buildUri(path));
        FileContent sftpFileContent = sftpFileObject.getContent();
        //
        file.setLastModified(sftpFileContent.getLastModifiedTime());
        FileUtils.copyInputStreamToFile(sftpFileContent.getInputStream(), file);
        //
        return file;
    }

}
