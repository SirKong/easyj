package net.hs.easyj.dao.api;


import net.hs.easyj.model.Page;

/**
 * 基本的分页操作
 * 所有数据库 DAO 接口如果需要支持分页基本的分页操作需要继承该接口
 *
 * @autor Gavin Hu
 * @create 2015/3/5
 */
public interface PageableDao<M> extends BaseDao<M> {

    /**
     * 分页查找
     * @param page
     * @return
     */
    Page<M> findPage(Page<M> page);

    /**
     * 分页查找根据 example 过滤
     * @param page
     * @param example
     * @return
     */
    Page<M> findPageByExample(Page<M> page, M example);

}
