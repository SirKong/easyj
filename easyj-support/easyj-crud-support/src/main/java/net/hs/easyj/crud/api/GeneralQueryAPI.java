package net.hs.easyj.crud.api;

import net.hs.easyj.crud.model.QueryDefinition;
import net.hs.easyj.model.Page;

import java.util.Map;

/**
 * 通用查询 API
 *
 * @author Gavin Hu
 * @create 2015/4/21
 */
public interface GeneralQueryAPI {

    /**
     * 根据通用查询名称加载通用查询定义
     * @param name 查询定义名称
     * @return
     */
    QueryDefinition load(String name);

    /**
     * 根据通用查询名称进行查询
     *
     * @param page 分页对象
     * @param params 查询参数
     * @param definition 查询定义
     * @return
     */
    Page<Map> findPage(Page page, Map params, QueryDefinition definition);

}
