package net.hs.easyj.saas.service;

import net.hs.easyj.saas.dao.UpdateDao;
import net.hs.easyj.saas.dao.UpdateInDao;
import net.hs.easyj.saas.dao.UpdateOutDao;
import net.hs.easyj.saas.model.Update;
import net.hs.easyj.saas.model.UpdateIn;
import net.hs.easyj.saas.model.UpdateOut;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通用更新服务单元测试
 *
 * @author Gavin Hu
 * @create 2015/9/6
 */
public class UpdateServiceTest extends ServiceTestSupport {

    @Autowired
    private UpdateService updateService;
    @Autowired
    private UpdateDao updateDao;
    @Autowired
    private UpdateInDao updateInDao;
    @Autowired
    private UpdateOutDao updateOutDao;

    @Before
    public void setUp() throws Exception {
        //
        Update update = new Update();
        update.setTenantId(tenant.getId());
        update.setCode("edit-user");
        update.setTitle("编辑用户");
        update.setUpdate1("insert into `user`(username, password) values(?, ?)");
        update.setEnabled(true);
        update.setVersion("1.0.0");
        update.setViewTemplate("/view/edit-user.html");
        updateDao.create(update);
        //
        UpdateIn updateIn = new UpdateIn();
        updateIn.setTenantId(tenant.getId());
        updateIn.setUpdateId(update.getId());
        updateIn.setName("username");
        updateIn.setLabel("账号");
        updateInDao.create(updateIn);
        //
        UpdateOut updateOut = new UpdateOut();
        updateOut.setTenantId(tenant.getId());
        updateOut.setUpdateId(update.getId());
        updateOut.setName("password");
        updateOut.setLabel("密码");
        updateOutDao.create(updateOut);
    }

    @Test
    public void testLoadUpdate() throws Exception {
        Update update = updateService.loadUpdate(tenant.getId(), "edit-user");
        System.out.println(update.getTitle());
    }
}
