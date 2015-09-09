package net.hs.easyj.config.provider.filetransfer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.provider.sftp.SftpFileObject;

import java.io.File;

/**
 * 抽象的文件传输
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
public class AbstractFileTransfer implements FileTransfer {

    private String schema;

    private String host;

    private String port;

    private String username;

    private String password;

    protected void setSchema(String schema) {
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

    @Override
    public void send(File file, String path) {
        try {
            org.apache.commons.vfs2.FileObject srcFileObject = VFS.getManager().toFileObject(file);
            org.apache.commons.vfs2.FileObject destFileObject = VFS.getManager().resolveFile(buildUri(path));
            //
            destFileObject.createFile();
            destFileObject.copyFrom(srcFileObject, Selectors.SELECT_SELF);
            destFileObject.getContent().setLastModifiedTime(file.lastModified());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receive(String path, File file) {
        try {
            SftpFileObject sftpFileObject = (SftpFileObject) VFS.getManager().resolveFile(buildUri(path));
            FileContent sftpFileContent = sftpFileObject.getContent();
            //
            file.setLastModified(sftpFileContent.getLastModifiedTime());
            FileUtils.copyInputStreamToFile(sftpFileContent.getInputStream(), file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buildUri(String relativePath) {
        return String.format("%s://%s:%s@%s:%s%s", schema, username, password, host, port, relativePath);
    }

}
