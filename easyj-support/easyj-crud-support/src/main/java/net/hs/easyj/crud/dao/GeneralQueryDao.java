package net.hs.easyj.crud.dao;


import net.hs.easyj.crud.model.QueryDefinition;
import net.hs.easyj.model.Page;

import java.util.Map;

/**
 * 通用查询 Dao
 *
 * @author Gavin Hu
 * @create 2015/4/20
 */
public interface GeneralQueryDao {

    Page<Map> findPage(Page page, Map params, QueryDefinition definition);

}
