package net.hs.easyj.crud.api.impl;


import net.hs.easyj.crud.api.GeneralQueryAPI;
import net.hs.easyj.crud.dao.GeneralQueryDao;
import net.hs.easyj.crud.dao.QueryInDefinitionDao;
import net.hs.easyj.crud.dao.QueryOutDefinitionDao;
import net.hs.easyj.crud.model.QueryInDefinition;
import net.hs.easyj.crud.dao.QueryDefinitionDao;
import net.hs.easyj.crud.model.QueryDefinition;
import net.hs.easyj.crud.model.QueryOutDefinition;
import net.hs.easyj.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 通用查询 API 实现
 *
 * @author Gavin Hu
 * @create 2015/4/21
 */
@Service
public class GeneralQueryAPIImpl implements GeneralQueryAPI {

    @Autowired
    private QueryDefinitionDao queryDefinitionDao;

    @Autowired
    private QueryInDefinitionDao queryInDefinitionDao;

    @Autowired
    private QueryOutDefinitionDao queryOutDefinitionDao;

    @Autowired
    private GeneralQueryDao generalQueryDao;

    public GeneralQueryAPIImpl() {

    }

    @Override
    public QueryDefinition load(String name) {
        //
        QueryDefinition example = new QueryDefinition();
        example.setName(name);
        example.setEnabled(Boolean.TRUE);
        //
        List<QueryDefinition> queryDefinitions = queryDefinitionDao.findByExample(example);
        QueryDefinition queryDefinition = queryDefinitions.get(0);
        //
        QueryInDefinition inExample = new QueryInDefinition();
        inExample.setQueryId(queryDefinition.getId());
        List<QueryInDefinition> queryInDefinitions = queryInDefinitionDao.findByExample(inExample);
        //
        QueryOutDefinition outExample = new QueryOutDefinition();
        outExample.setQueryId(queryDefinition.getId());
        List<QueryOutDefinition> queryOutDefinitions = queryOutDefinitionDao.findByExample(outExample);
        //
        queryDefinition.setInDefinitions(queryInDefinitions);
        queryDefinition.setOutDefinitions(queryOutDefinitions);
        //
        return queryDefinition;
    }

    @Override
    public Page<Map> findPage(Page page, Map params, QueryDefinition definition) {
        //
        return generalQueryDao.findPage(page, params, definition);
    }

}
