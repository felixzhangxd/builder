package com.firebugsoft.builder.jdbc.bean;

/**
 * 数据库表字段
 */
public class Field {
    private boolean isId=false; //是否id
    private String columnName; //数据库表字段名
    private String className; //数据库字段：java类型
    private String name;  //数据库字段：属性名
    private boolean isNullable=true; //数据库字段：可否为空
    private boolean isUnique=false; //数据库字段：是否为唯一索引
    private String remarks;  //数据库字段：属性说明
    private String get;   //数据库字段：get方法
    private String set;   //数据库字段：set方法

    public boolean isId() {
        return isId;
    }

    public void setIsId(boolean isId) {
        this.isId = isId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setIsNullable(boolean isNullable) {
        this.isNullable = isNullable;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public void setIsUnique(boolean isUnique) {
        this.isUnique = isUnique;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }
}
