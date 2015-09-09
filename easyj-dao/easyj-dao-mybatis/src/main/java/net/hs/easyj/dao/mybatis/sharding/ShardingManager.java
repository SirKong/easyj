package net.hs.easyj.dao.mybatis.sharding;

import net.hs.easyj.dao.mybatis.sharding.model.FragmentTable;

/**
 * 分片管理
 *
 * @author gavin
 * @create 15/6/17
 */
public interface ShardingManager {

    FragmentTable lookup(String table, Long id);

}
