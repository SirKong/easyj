package net.hs.easyj.config.definition;

/**
 * @author gavin
 * @create 15/8/11
 */
public class ObjectConfigDefinition {

    private String className;

    private String category;

    private String subCategory1;

    private String subCategory2;

    private String subCategory3;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory1() {
        return subCategory1;
    }

    public void setSubCategory1(String subCategory1) {
        this.subCategory1 = subCategory1;
    }

    public String getSubCategory2() {
        return subCategory2;
    }

    public void setSubCategory2(String subCategory2) {
        this.subCategory2 = subCategory2;
    }

    public String getSubCategory3() {
        return subCategory3;
    }

    public void setSubCategory3(String subCategory3) {
        this.subCategory3 = subCategory3;
    }

    public String getCategoryPath() {
        StringBuffer pathBuffer = new StringBuffer("/"+category);
        if(subCategory1!=null) {
            pathBuffer.append("/").append(subCategory1);
            if(subCategory2!=null) {
                pathBuffer.append("/").append(subCategory2);
                if(subCategory3!=null) {
                    pathBuffer.append("/").append(subCategory3);
                }
            }
        }
        return pathBuffer.toString();
    }

}
