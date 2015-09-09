package net.hs.easyj.config.bean;

/**
 * @author gavin
 * @create 15/8/11
 */
public class ConfigBean {

    private String system;

    private String profile;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPath() {
        return String.format("/%s/%s", system, profile);
    }

}
