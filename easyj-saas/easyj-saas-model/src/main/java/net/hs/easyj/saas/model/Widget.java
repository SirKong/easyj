package net.hs.easyj.saas.model;

/**
 * 组件模型
 *
 * @author Gavin Hu
 * @create 2015/8/27
 */
public class Widget extends Id {

    private Long tenantId;

    private Long positionId;

    private String path;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
