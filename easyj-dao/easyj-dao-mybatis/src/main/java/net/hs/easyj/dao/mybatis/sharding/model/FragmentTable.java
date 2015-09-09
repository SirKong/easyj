package net.hs.easyj.dao.mybatis.sharding.model;

/**
 * 分段表
 *
 * @author Gavin Hu
 * @create 2015/6/17
 */
public class FragmentTable {

    private Long id;

    private String name;

    private Long beginId;

    private Long endId;

    private Long shardId;

    private Shard shard;

    public FragmentTable() {
    }

    public FragmentTable(String name) {
        this.name = name;
    }

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

    public Long getShardId() {
        return shardId;
    }

    public void setShardId(Long shardId) {
        this.shardId = shardId;
    }

    public Shard getShard() {
        return shard;
    }

    public void setShard(Shard shard) {
        this.shard = shard;
    }

}
