package net.hs.easyj.saas.model;

/**
 * 页面位置模型
 *
 * @author Gavin Hu
 * @create 2015/8/27
 */
public class PagePosition extends Id {

    private Long tenantId;

    private Long pageId;

    private String name;

    private Page page;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

}