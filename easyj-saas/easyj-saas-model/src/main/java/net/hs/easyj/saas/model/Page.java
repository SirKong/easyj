package net.hs.easyj.saas.model;

/**
 * 页面模型
 *
 * @author Gavin Hu
 * @create 2015/8/27
 */
public class Page extends Id {

    private Long tenantId;

    private Long parentId;

    private String title;

    private String path;

    private Integer type; // 模版

    private String layout;

    private Page parent;

    public Page() {
    }

    public Page(Long tenantId, Long parentId, String title, String path, Integer type) {
        this.tenantId = tenantId;
        this.parentId = parentId;
        this.title = title;
        this.path = path;
        this.type = type;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Page getParent() {
        return parent;
    }

    public void setParent(Page parent) {
        this.parent = parent;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public static Page exampleOf(Long tenantId, String path) {
        return new Page(tenantId, null, null, path, null);
    }
}
