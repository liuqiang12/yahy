package cn.hd.common.util;

/**
 * 代码生成器需要的实体类
 */
public class CodeModel {
    private String packageName;//包路径名称
    private String className;//类名称
    private String classInstName;//类实例名称
    private String entityPackage;//实体类的包路径
    private String tableComment;//表的注释
    private String daoPackage;//dao层的包路径
    private String controllerPackage;//控制层的包路径




    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassInstName() {
        return classInstName;
    }

    public void setClassInstName(String classInstName) {
        this.classInstName = classInstName;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }
}
