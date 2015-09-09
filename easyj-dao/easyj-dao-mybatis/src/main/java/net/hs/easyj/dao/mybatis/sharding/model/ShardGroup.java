package net.hs.easyj.dao.mybatis.sharding.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gavin
 * @create 15/6/23
 */
public class ShardGroup {

    private Long id;

    private String name;

    private Boolean writable;

    private Long beginId;

    private Long endId;

    private List<Shard> shards = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getWritable() {
        return writable;
    }

    public void setWritable(Boolean writable) {
        this.writable = writable;
    }

    public Long getBeginId() {
        return beginId;
    }

    public void setBeginId(Long beginId) {
        this.beginId = beginId;
    }

    public Long getEndId() {
        return endId;
    }

    public void setEndId(Long endId) {
        this.endId = endId;
    }

    public List<Shard> getShards() {
        return shards;
    }

    public void setShards(List<Shard> shards) {
        this.shards = shards;
    }
}
