package net.hs.easyj.crud.dao.impl;

import net.hs.easyj.crud.dao.GeneralUpdateDao;
import net.hs.easyj.crud.model.UpdateDefinition;
import net.hs.easyj.dao.mybatis.builder.XmlStatementDynamicBuilder;
import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 通用更新 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/4/27
 */
@Repository
public class GeneralUpdateDaoImpl extends MyBatisDaoSupport<Map> implements GeneralUpdateDao {

    private Map<String, String[]> cachedUpdatePartNames = new HashMap<>();

    @Override
    public int insert(Map params, UpdateDefinition definition) {
        //
        String updateId = generateId("insert", definition);
        String[] updateNames = loadMappedStatementFromUpdateDefinition(updateId, definition.getUpdate1());
        //
        int update = 0;
        for(String updateName : updateNames) {
            update+= super.insert(updateName, params);
        }
        return update;
    }

    @Override
    public int update(Map params, UpdateDefinition definition) {
        //
        String updateId = generateId("update", definition);
        String[] updateNames = loadMappedStatementFromUpdateDefinition(updateId, definition.getUpdate2());
        //
        int update = 0;
        for(String updateName : updateNames) {
            update+= super.update(updateName, params);
        }
        return update;
    }

    @Override
    public int delete(Map params, UpdateDefinition definition) {
        String updateId = generateId("delete", definition);
        String[] updateNames = loadMappedStatementFromUpdateDefinition(updateId, definition.getUpdate3());
        //
        int update = 0;
        for(String updateName : updateNames) {
            update+= super.delete(updateName, params);
        }
        return update;
    }

    private String[] loadMappedStatementFromUpdateDefinition(String updateId, String updateStr) {
        String[] updatePartNames = cachedUpdatePartNames.get(updateId);
        if(updatePartNames!=null) {
            return updatePartNames;
        }
        //
        int count = 0;
        List<String> updatePartNameList = new ArrayList<>();
        //
        StringTokenizer tokenizer = new StringTokenizer(updateStr, ";");
        while(tokenizer.hasMoreTokens()) {
            String sqlScript = tokenizer.nextToken();
            //
            String updatePartName = updateId + "_part"+count;
            //
            String resource = "Dynamic Update Resource [" + updatePartName + "]";
            Configuration configuration = getSqlSession().getConfiguration();
            if(configuration.hasStatement(getIdPrefix().concat(updatePartName))) {
                updatePartNameList.add(updatePartName);
                continue;
            }
            //
            XmlStatementDynamicBuilder xmlStatementDynamicBuilder = new XmlStatementDynamicBuilder(configuration, resource)
                    .id(updatePartName)
                    .namespace(GeneralUpdateDao.class.getName())
                    .parameterType(Map.class)
                    .resultType(Map.class)
                    .sqlScript(sqlScript);
            //
            MappedStatement mappedStatement = xmlStatementDynamicBuilder.build();
            //
            count++;
            updatePartNameList.add(updatePartName);
        }
        //
        updatePartNames = updatePartNameList.toArray(new String[updatePartNameList.size()]);
        this.cachedUpdatePartNames.put(updateId, updatePartNames);
        //
        return updatePartNames;
    }

    private String generateId(String type, UpdateDefinition definition) {
        StringBuffer sb = new StringBuffer(definition.getName());
        sb.append("_").append(type);
        if(definition.getVersion()!=null) {
            sb.append("_").append(definition.getVersion().replaceAll("\\.", "_"));
        }
        return sb.toString();
    }

}
