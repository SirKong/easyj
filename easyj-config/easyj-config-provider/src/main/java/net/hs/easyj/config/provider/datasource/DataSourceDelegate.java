package net.hs.easyj.config.provider.datasource;

import net.hs.easyj.config.spi.ObjectDelegate;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * 抽象的数据源
 *
 * @author Gavin Hu
 * @create 2015/8/12
 */
public abstract class DataSourceDelegate implements ObjectDelegate<DataSource>, DataSource {

    private DataSource target;

    @Override
    public void setTarget(DataSource target) {
        //
        if (target != null) {
            synchronized (target) {
                //
                DataSource oldTarget = this.target;
                this.target = target;
                //
                if (oldTarget != null) {
                    close(oldTarget);
                }
            }
        }
        //
        this.target = target;
    }

    /**
     * 关闭老数据源
     *
     * @param oldTarget
     */
    protected abstract void close(DataSource oldTarget);

    public DataSource getTarget() {
        return target;
    }

    public Connection getConnection() throws SQLException {
        return getTarget().getConnection();
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return getTarget().getConnection(username, password);
    }

    public PrintWriter getLogWriter() throws SQLException {
        return getTarget().getLogWriter();
    }

    public void setLogWriter(PrintWriter out) throws SQLException {
        getTarget().setLogWriter(out);
    }

    public int getLoginTimeout() throws SQLException {
        return getTarget().getLoginTimeout();
    }

    public void setLoginTimeout(int seconds) throws SQLException {
        getTarget().setLoginTimeout(seconds);
    }


    //---------------------------------------------------------------------
    // Implementation of JDBC 4.0's Wrapper interface
    //---------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return (T) this;
        }
        return getTarget().unwrap(iface);
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return (iface.isInstance(this) || getTarget().isWrapperFor(iface));
    }


    //---------------------------------------------------------------------
    // Implementation of JDBC 4.1's getParentLogger method
    //---------------------------------------------------------------------

    public Logger getParentLogger() {
        return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

}
