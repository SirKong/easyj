package net.hs.easyj.config.provider.datasource.dbcp;

import net.hs.easyj.config.provider.datasource.DataSourceDelegate;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Commons DBCP数据源对象委托
 *
 * @author Gavin Hu
 * @create 2015/8/12
 */
public class DbcpDataSourceDelegate extends DataSourceDelegate {

    @Override
    protected void close(DataSource oldTarget) {
        try {
            ((BasicDataSource)oldTarget).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
