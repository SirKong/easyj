package net.hs.easyj.config.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author gavin
 * @create 15/8/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class ObjectConfigFactoryBeanTest {

    @Autowired
    @Qualifier("baseProperties")
    private Properties baseProperties;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testProperties() throws InterruptedException {
        while(true) {
            TimeUnit.SECONDS.sleep(1);
            //
            System.out.println(baseProperties.size());
        }
    }

    @Test
    public void testDataSource() throws InterruptedException {
        while(true) {
            TimeUnit.SECONDS.sleep(1);
            //
            System.out.println(dataSource);
        }
    }

}
