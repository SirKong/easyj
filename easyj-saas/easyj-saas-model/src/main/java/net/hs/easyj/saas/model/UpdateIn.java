package net.hs.easyj.saas.model;

/**
 * 更新输入模型
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
public class UpdateIn extends Id {

    private Long tenantId;

    private Long updateId;

    private String name;

    private String label;

    private String type;

    private Integer order;

    private String enabled;

    private String format;

    private String defaultValue;

    private String valueProvider;

    public UpdateIn() {
    }

    public UpdateIn(Long tenantId, Long updateId) {
        this.tenantId = tenantId;
        this.updateId = updateId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValueProvider() {
        return valueProvider;
    }

    public void setValueProvider(String valueProvider) {
        this.valueProvider = valueProvider;
    }

    public static UpdateIn exampleOf(Long tenantId, Long updateId) {
        return new UpdateIn(tenantId, updateId);
    }
}
