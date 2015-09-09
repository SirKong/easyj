package net.hs.easyj.web.widget.handler.event;

import java.util.HashMap;
import java.util.Map;

public class Event {

    private String name;

    private Map<String, Object> data = new HashMap<String, Object>();

    public Event(String name) {
        this.name = name;
    }

    public Event(String name, Map<String, Object> data) {
        this.name = name;
        this.data.putAll(data);
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getData() {
        return data;
    }

}
