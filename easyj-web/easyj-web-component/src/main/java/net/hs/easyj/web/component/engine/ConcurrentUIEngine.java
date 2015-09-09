package net.hs.easyj.web.component.engine;

import net.hs.easyj.web.component.UIComponent;
import net.hs.easyj.web.component.factory.UIComponentFactory;
import net.hs.easyj.web.component.scanner.UIComponentScanner;
import net.hs.easyj.web.component.support.aware.HttpServletRequestAware;
import net.hs.easyj.web.component.support.aware.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 并发 UI 组件引擎
 *
 * @author Gavin Hu
 * @create 2015/5/6
 */
public class ConcurrentUIEngine implements UIEngine {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private UIComponentScanner uiComponentScanner;

    private UIComponentFactory uiComponentFactory;

    public void setConcurrent(int concurrent) {
        this.executorService = Executors.newFixedThreadPool(concurrent);
    }

    public void setUiComponentScanner(UIComponentScanner uiComponentScanner) {
        this.uiComponentScanner = uiComponentScanner;
    }

    public void setUiComponentFactory(UIComponentFactory uiComponentFactory) {
        this.uiComponentFactory = uiComponentFactory;
    }

    @Override
    public void run(UIContext uiContext) {
        //
        Map<String, Future<UIComponent>> futureMap = new HashMap<>();
        //
        Set<String> componentNames = uiComponentScanner.scan(uiContext.getViewName());
        for(String componentName : componentNames) {
            UIComponent uiComponent = uiComponentFactory.get(componentName);

            Future<UIComponent> future = executorService.submit(new UIComponentProcessor(uiComponent, uiContext));
            //
            futureMap.put(componentName, future);
        }
        //
        for(String componentName : componentNames) {
            try {
                Future<UIComponent> future = futureMap.get(componentName);
                UIComponent component = future.get();
                uiContext.setObject(component.getMetaKey(), component.getMeta());
                uiContext.setObject(component.getDataKey(), component.getData());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        //
        uiContext.setObject(UIContext.UI_COMPONENT_NAMES, componentNames);
    }

    private class UIComponentProcessor implements Callable<UIComponent> {

        private UIComponent uiComponent;

        private UIContext uiContext;

        public UIComponentProcessor(UIComponent uiComponent, UIContext uiContext) {
            this.uiComponent = uiComponent;
            this.uiContext = uiContext;
        }

        @Override
        public UIComponent call() throws Exception {
            // inject through aware interfaces
            if(uiComponent instanceof HttpServletRequestAware) {
                HttpServletRequest request = uiContext.getObject(HttpServletRequest.class.getName());
                ((HttpServletRequestAware) uiComponent).setHttpServletRequest(request);
            }
            if(uiComponent instanceof ServletContextAware) {
                ServletContext context = uiContext.getObject(ServletContext.class.getName());
                ((ServletContextAware) uiComponent).setServletContext(context);
            }
            // prepare meta and data
            uiComponent.getMeta();
            if(!uiComponent.isAsync()) {
                uiComponent.getData();
            }
            //
            return uiComponent;
        }
    }

}
