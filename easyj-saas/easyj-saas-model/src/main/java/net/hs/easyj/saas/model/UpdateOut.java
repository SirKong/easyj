package net.hs.easyj.saas.model;

/**
 * 更新输出模型
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
public class UpdateOut extends Id {

    private Long tenantId;

    private Long updateId;

    private String name;

    private String label;

    private String type;

    private Integer order;

    private String format;

    public UpdateOut() {
    }

    public UpdateOut(Long tenantId, Long updateId) {
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public static UpdateOut exampleOf(Long tenantId, Long updateId) {
        return new UpdateOut(tenantId, updateId);
    }

}
