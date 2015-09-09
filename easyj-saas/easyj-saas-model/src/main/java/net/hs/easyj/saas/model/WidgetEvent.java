package net.hs.easyj.saas.model;

/**
 * 事件模型
 *
 * @author gavin
 * @create 15/8/27
 */
public class WidgetEvent extends Id {

    private Long tenantId;

    private Long widgetId;

    private String name;

    private String expression;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(Long widgetId) {
        this.widgetId = widgetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

}
