package net.hs.easyj.dao.mybatis.sharding;


import net.hs.easyj.dao.mybatis.sharding.model.FragmentTable;
import net.hs.easyj.dao.mybatis.sharding.model.ShardGroup;
import net.hs.easyj.dao.mybatis.sharding.model.Shard;
import org.apache.ibatis.jdbc.SqlRunner;
import org.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gavin
 * @create 15/6/17
 */
public class DefaultShardingManager implements ShardingManager, InitializingBean {

    private DataSource dataSource;

    private Map<String, List<Shard>> tableShardMap = new HashMap<String, List<Shard>>() {
        @Override
        public List<Shard> get(Object key) {
            List<Shard> shards = super.get(key);
            if(shards==null) {
                shards = new ArrayList<>();
                super.put((String) key, shards);
            }
            return shards;
        }
    };

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //
        try(Connection connection = dataSource.getConnection()) {
            SqlRunner sqlRunner = new SqlRunner(connection);
            //
            ShardGroup shardGroup = loadWritableShardGroup(sqlRunner);
            //
            List<Shard> shards = loadShards(sqlRunner, shardGroup);
            for (Shard shard : shards) {
                List<FragmentTable> fragmentTables = loadFragmentTablesByShard(sqlRunner, shard);
                for (FragmentTable fragmentTable : fragmentTables) {
                    String table = fragmentTable.getName().substring(0, fragmentTable.getName().lastIndexOf("_"));
                    List<Shard> shardList = tableShardMap.get(table);
                    if(!shardList.contains(shard)) {
                        shardList.add(shard);
                    }
                }
                shard.setFragmentTables(fragmentTables);
            }
            shardGroup.setShards(shards);
        }
    }

    private ShardGroup loadWritableShardGroup(SqlRunner sqlRunner) throws SQLException {
        //
        Map<String, Object> result = sqlRunner.selectOne("select * from tb_shard_group where writable > 0");
        ShardGroup shardGroup = new ShardGroup();
        shardGroup.setId(Long.valueOf(result.get("ID").toString()));
        shardGroup.setName(result.get("NAME").toString());
        shardGroup.setBeginId(Long.valueOf(result.get("BEGIN_ID").toString()));
        shardGroup.setEndId(Long.valueOf(result.get("END_ID").toString()));
        shardGroup.setWritable(Boolean.valueOf(result.get("WRITABLE").toString()));
        return shardGroup;
    }

    private List<Shard> loadShards(SqlRunner sqlRunner, ShardGroup shardGroup) throws SQLException {
        //
        List<Shard> shards = new ArrayList<>();
        List<Map<String, Object>> results = sqlRunner.selectAll("select * from tb_shard where shard_group_id = ?", shardGroup.getId());
        for(Map<String, Object> result : results) {
            //
            Shard shard = new Shard();
            shard.setId(Long.valueOf(result.get("ID").toString()));
            shard.setName(result.get("NAME").toString());
            shard.setHashValue(result.get("HASH_VALUE").toString());
            //
            shards.add(shard);
        }
        //
        return shards;
    }

    private List<FragmentTable> loadFragmentTablesByShard(SqlRunner sqlRunner, Shard shard) throws SQLException {
        //
        List<FragmentTable> fragmentTables = new ArrayList<>();
        List<Map<String, Object>> results = sqlRunner.selectAll("select * from tb_fragment_table where shard_id = ?", shard.getId());
        //
        for(Map<String, Object> result : results) {
            FragmentTable fragmentTable = new FragmentTable();
            fragmentTable.setId(Long.valueOf(result.get("ID").toString()));
            fragmentTable.setName(result.get("NAME").toString());
            fragmentTable.setBeginId(Long.valueOf(result.get("BEGIN_ID").toString()));
            fragmentTable.setEndId(Long.valueOf(result.get("END_ID").toString()));
            fragmentTable.setShardId(Long.valueOf(result.get("SHARD_ID").toString()));
            fragmentTable.setShard(shard);
            //
            fragmentTables.add(fragmentTable);
        }
        //
        return fragmentTables;
    }

    @Override
    public FragmentTable lookup(String table, Long id) {
        //
        List<Shard> shards = tableShardMap.get(table);
        for(Shard shard : shards) {
            //
            int size = shard.getFragmentTables(table).size();
            if(size==0) {
                continue;
            }
            if(shard.getHashValue().contains(String.valueOf(id % size))) {
                //
                for(FragmentTable fragmentTable : shard.getFragmentTables(table)) {
                    //
                    if(id>=fragmentTable.getBeginId() && id<=fragmentTable.getEndId()) {
                        return fragmentTable;
                    }
                }
            }
        }
        //
        return null;
    }

}
