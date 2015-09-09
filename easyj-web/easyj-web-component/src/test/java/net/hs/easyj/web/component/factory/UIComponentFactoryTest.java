package net.hs.easyj.web.component.factory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gavin on 15/5/15.
 */
public class UIComponentFactoryTest {

    @Test
    public void testGet() {
        //
        UIComponentFactory componentFactory = new SpringUIComponentFactory();
        //
        Assert.assertNotNull(componentFactory.get("productSelect"));
    }

}
