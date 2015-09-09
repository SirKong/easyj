package net.hs.easyj.dao.mybatis.sqlsource;

import org.apache.ibatis.mapping.SqlSource;

/**
 * SqlSource 创建者
 *
 * @author Gavin Hu
 * @create 2015/3/4
 */
public interface SqlSourceBuilder {

    SqlSource buildCount(SqlSource original);

    SqlSource buildPage(SqlSource original);

}
