package net.hs.easyj.crud.dao;

import net.hs.easyj.crud.model.UpdateDefinition;

import java.util.Map;

/**
 * 通用更新 Dao
 *
 * @author Gavin Hu
 * @create 2015/4/27
 */
public interface GeneralUpdateDao {

    /**
     * 根据更新定义进行插入
     * @param params
     * @param definition
     * @return
     */
    int insert(Map params, UpdateDefinition definition);

    /**
     * 根据更新定义进行更新
     * @param params
     * @param definition
     * @return
     */
    int update(Map params, UpdateDefinition definition);

    /**
     * 根据更新定义进行删除
     * @param params
     * @param definition
     * @return
     */
    int delete(Map params, UpdateDefinition definition);

}
