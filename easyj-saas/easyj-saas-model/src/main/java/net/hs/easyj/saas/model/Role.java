package net.hs.easyj.saas.model;

/**
 * 角色模型
 *
 * @author Gavin Hu
 * @create 2015/8/26
 */
public class Role extends Id {

    private Long tenantId;

    private String name;

    private String code;

    private String description;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}