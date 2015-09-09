package net.hs.easyj.saas.model;

/**
 * 站点树模型
 *
 * @author Gavin Hu
 * @create 2015/8/27
 */
public class SiteTree extends Id {

    private Long tenantId;

    private Long parentId;

    private String pageTitle;

    private String pagePath;

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

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }
}
