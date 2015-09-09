package net.hs.easyj.saas.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询模型
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
public class Query extends Id {

    private Long tenantId;

    private String code;

    private String title;

    private String description;

    private String query;

    private String version;

    private Boolean enabled = Boolean.FALSE;

    private Integer pageSize = 0;

    private String viewTemplate;

    private List<QueryIn> queryIns = new ArrayList<>();

    private List<QueryOut> queryOuts = new ArrayList<>();

    public Query() {
    }

    public Query(Long tenantId, String code, String version, Boolean enabled) {
        this.tenantId = tenantId;
        this.code = code;
        this.version = version;
        this.enabled = enabled;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getViewTemplate() {
        return viewTemplate;
    }

    public void setViewTemplate(String viewTemplate) {
        this.viewTemplate = viewTemplate;
    }

    public List<QueryIn> getQueryIns() {
        return queryIns;
    }

    public void setQueryIns(List<QueryIn> queryIns) {
        this.queryIns = queryIns;
    }

    public List<QueryOut> getQueryOuts() {
        return queryOuts;
    }

    public void setQueryOuts(List<QueryOut> queryOuts) {
        this.queryOuts = queryOuts;
    }

    public static Query exampleOf(Long tenantId, String code) {
        return new Query(tenantId, code, null, Boolean.TRUE);
    }

}
