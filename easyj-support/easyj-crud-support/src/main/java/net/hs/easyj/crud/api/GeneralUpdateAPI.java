package net.hs.easyj.crud.api;

import net.hs.easyj.crud.model.UpdateDefinition;

import java.util.Map;

/**
 * 通用更新 API
 *
 * @author Gavin Hu
 * @create 2015/4/27
 */
public interface GeneralUpdateAPI {

    /**
     * 根据通用更新名称加载通用更新定义
     * @param name
     * @return
     */
    UpdateDefinition load(String name);

    /**
     * 根据更新定义进行更新
     * @param params
     * @param definition
     */
    void update(Map params, UpdateDefinition definition);
    
    /**
     * 根据更新定义进行增加
     * @param params
     * @param definition
     */
    void insert(Map params, UpdateDefinition definition);
    
    /**
     * 根据更新定义进行删除
     * @param params
     * @param definition
     */
    void delete(Map params, UpdateDefinition definition);


}
