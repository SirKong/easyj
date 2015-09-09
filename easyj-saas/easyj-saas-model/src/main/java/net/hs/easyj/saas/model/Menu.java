package net.hs.easyj.saas.model;

/**
 * 菜单模型
 *
 * @author Gavin Hu
 * @create 15/8/26
 */
public class Menu extends Id {

    private Long tenantId;

    private Long parentId;

    private String name;

    private String code;

    private String url;

    private String order; // 排序字段

    public Menu() {
    }

    public Menu(Long tenantId, Long parentId, String code) {
        this.tenantId = tenantId;
        this.parentId = parentId;
        this.code = code;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public static Menu exampleOf(Long tenantId, String code) {
        return new Menu(tenantId, null, code);
    }

}
