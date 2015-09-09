package net.hs.easyj.saas.model;

/**
 * 查询输出模型
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
public class QueryOut extends Id {

    private Long tenantId;

    private Long queryId;

    private String name;

    private String label;

    private String type;

    private Integer order;

    private String format;

    private String mode;

    public QueryOut() {
    }

    public QueryOut(Long tenantId, Long queryId) {
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public static QueryOut exampleOf(Long tenantId, Long queryId) {
        return new QueryOut(tenantId, queryId);
    }
}
