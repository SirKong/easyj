package net.hs.easyj.dao.mybatis.sharding.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gavin Hu
 * @create 2015/6/17
 */
public class Shard {

    private Long id;

    private String name;

    private String hashValue;

    private Long shardGroupId;

    private ShardGroup shardGroup;

    private List<FragmentTable> fragmentTables = new ArrayList<>();

    private Map<String, List<FragmentTable>> fragmentTablesMap = new HashMap<String, List<FragmentTable>>(){
        @Override
        public List<FragmentTable> get(Object key) {
            List<FragmentTable> fragmentTables = super.get(key);
            if(fragmentTables==null) {
                fragmentTables = new ArrayList<>();
                super.put(key.toString(), fragmentTables);
            }
            //
            return fragmentTables;
        }
    };

    public Shard() {
    }

    public Shard(String name) {
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

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public List<FragmentTable> getFragmentTables() {
        return fragmentTables;
    }

    public void setFragmentTables(List<FragmentTable> fragmentTables) {
        this.fragmentTables = fragmentTables;
    }

    public List<FragmentTable> getFragmentTables(String table) {
        List<FragmentTable> _fragmentTables = fragmentTablesMap.get(table);
        if(_fragmentTables.isEmpty()) {
            for(FragmentTable fragmentTable : this.fragmentTables) {
                if(fragmentTable.getName().startsWith(table + "_")) {
                    _fragmentTables.add(fragmentTable);
                }
            }
        }
        //
        return _fragmentTables;
    }

    public ShardGroup getShardGroup() {
        return shardGroup;
    }

    public void setShardGroup(ShardGroup shardGroup) {
        this.shardGroup = shardGroup;
    }

    public Long getShardGroupId() {
        return shardGroupId;
    }

    public void setShardGroupId(Long shardGroupId) {
        this.shardGroupId = shardGroupId;
    }
}
