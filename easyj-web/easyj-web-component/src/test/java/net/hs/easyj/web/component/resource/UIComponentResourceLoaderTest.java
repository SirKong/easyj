package net.hs.easyj.web.component.resource;

import org.junit.Assert;
import org.junit.Test;

/**
 * UI 组件资源加载器单元测试
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
public class UIComponentResourceLoaderTest {

    @Test
    public void testLoad() {
        //
        UIComponentResourceLoader resourceLoader = new ClassLoaderUIComponentResourceLoader();

        UIComponentResource resource = resourceLoader.load("ProductSelect");
        Assert.assertNotNull(resource.getComponentXDoc());
        Assert.assertNotNull(resource.getComponentUI());
        Assert.assertNotNull(resource.getComponentShowUI());
        Assert.assertNotNull(resource.getComponentScript());
        Assert.assertNotNull(resource.getComponentAdapterScript());
    }

}
