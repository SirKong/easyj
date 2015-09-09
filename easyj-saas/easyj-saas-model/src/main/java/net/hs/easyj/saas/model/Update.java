package net.hs.easyj.saas.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 更新模型
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
public class Update extends Id {

    private Long tenantId;

    private String code;

    private String title;

    private String description;

    private String version;

    private String update1;

    private String update2;

    private String update3;

    private Boolean enabled = Boolean.FALSE;

    private String viewTemplate;

    private List<UpdateIn> updateIns = new ArrayList<>();

    private List<UpdateOut> updateOuts = new ArrayList<>();

    public Update() {
    }

    public Update(Long tenantId, String code,String version, Boolean enabled) {
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUpdate1() {
        return update1;
    }

    public void setUpdate1(String update1) {
        this.update1 = update1;
    }

    public String getUpdate2() {
        return update2;
    }

    public void setUpdate2(String update2) {
        this.update2 = update2;
    }

    public String getUpdate3() {
        return update3;
    }

    public void setUpdate3(String update3) {
        this.update3 = update3;
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

    public List<UpdateIn> getUpdateIns() {
        return updateIns;
    }

    public void setUpdateIns(List<UpdateIn> updateIns) {
        this.updateIns = updateIns;
    }

    public List<UpdateOut> getUpdateOuts() {
        return updateOuts;
    }

    public void setUpdateOuts(List<UpdateOut> updateOuts) {
        this.updateOuts = updateOuts;
    }

    public static Update exampleOf(Long tenantId, String code) {
        return new Update(tenantId, code, null, Boolean.TRUE);
    }
}
