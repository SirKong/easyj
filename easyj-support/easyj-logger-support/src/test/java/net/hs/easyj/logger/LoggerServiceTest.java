package net.hs.easyj.logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author gavin
 * @create 15/9/4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class LoggerServiceTest {

    @Autowired
    private LoggerService loggerService;

    @Test
    public void testInfo() throws Exception {
        loggerService.info("hello world!");
    }
}
