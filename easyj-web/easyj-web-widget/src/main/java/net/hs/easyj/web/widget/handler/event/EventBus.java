package net.hs.easyj.web.widget.handler.event;

import net.hs.easyj.web.widget.entry.config.WidgetConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Event Bus 事件总线
 *
 * @author <a href="mailto:huhz@hundsun.com">Gavin Hu</a>
 * @since 1.0.0
 */
public final class EventBus {

    private WidgetConfig widgetConfig;

    private Stack<Event> eventStack = new Stack<Event>();
    //
    private Map<String, Object> data = new HashMap<String, Object>();

    public EventBus(WidgetConfig widgetConfig) {
        this.widgetConfig = widgetConfig;
    }

    public WidgetConfig getWidgetConfig() {
        return widgetConfig;
    }

    public void trigger(String name) {
        Event event = new Event(name, data);
        this.eventStack.push(event);
    }

    public EventBus withData(String name, Object value) {
        data.put(name, value);
        return this;
    }

    public boolean hasEvents() {
        return !this.eventStack.isEmpty();
    }

    public Event receive() {
        return this.eventStack.pop();
    }

}
