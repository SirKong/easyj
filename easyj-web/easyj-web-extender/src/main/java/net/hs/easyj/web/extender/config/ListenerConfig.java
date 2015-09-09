package net.hs.easyj.web.extender.config;

/**
 * Created by Gavin Hu on 2015/2/7.
 */
public class ListenerConfig {

    private String listenerClass;

    public ListenerConfig(String listenerClass) {
        this.listenerClass = listenerClass;
    }

    public String getListenerClass() {
        return listenerClass;
    }

    public void setListenerClass(String listenerClass) {
        this.listenerClass = listenerClass;
    }
}
