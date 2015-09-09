package net.hs.easyj.saas.service.impl;

import net.hs.easyj.saas.dao.UpdateDao;
import net.hs.easyj.saas.dao.UpdateInDao;
import net.hs.easyj.saas.dao.UpdateOutDao;
import net.hs.easyj.saas.model.Query;
import net.hs.easyj.saas.model.Update;
import net.hs.easyj.saas.model.UpdateIn;
import net.hs.easyj.saas.model.UpdateOut;
import net.hs.easyj.saas.service.QueryService;
import net.hs.easyj.saas.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询服务实现
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private UpdateDao updateDao;
    @Autowired
    private UpdateInDao updateInDao;
    @Autowired
    private UpdateOutDao updateOutDao;

    @Override
    public Update loadUpdate(Long tenantId, String code) {
        //
        Update update = updateDao.findOne(Update.exampleOf(tenantId, code));
        if(update!=null) {
            List<UpdateIn> updateIns = updateInDao.findByExample(UpdateIn.exampleOf(tenantId, update.getId()));
            update.setUpdateIns(updateIns);
            //
            List<UpdateOut> updateOuts = updateOutDao.findByExample(UpdateOut.exampleOf(tenantId, update.getId()));
            update.setUpdateOuts(updateOuts);
        }
        //
        return update;
    }

    //=======================================================================

    public void setUpdateDao(UpdateDao updateDao) {
        this.updateDao = updateDao;
    }

    public void setUpdateInDao(UpdateInDao updateInDao) {
        this.updateInDao = updateInDao;
    }

    public void setUpdateOutDao(UpdateOutDao updateOutDao) {
        this.updateOutDao = updateOutDao;
    }

}
