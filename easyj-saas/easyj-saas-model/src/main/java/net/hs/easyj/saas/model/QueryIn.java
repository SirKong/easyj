package net.hs.easyj.saas.model;

/**
 * 查询输入模型
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
public class QueryIn extends Id {

    private Long tenantId;

    private Long queryId;

    private String name;

    private String label;

    private String type;

    private Integer order;

    private String format;

    private String defaultValue;

    private String valueProvider;

    public QueryIn() {
    }

    public QueryIn(Long tenantId, Long queryId) {
        this.tenantId = tenantId;
        this.queryId = queryId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getQueryId() {
        return queryId;
    }

    public void setQueryId(Long queryId) {
        this.queryId = queryId;
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

    public static QueryIn exampleOf(Long tenantId, Long queryId) {
        return new QueryIn(tenantId, queryId);
    }

}
