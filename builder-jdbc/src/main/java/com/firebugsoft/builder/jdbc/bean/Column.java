package com.firebugsoft.builder.jdbc.bean;

/** 数据库表字段 */
public class Column {
    private String name;    //字段名称
    private Integer type;   //字段类型
    private String remarks; //字段注释
    private boolean isNullable; //可否为空
    private boolean isAutoIncrement; //是否自增

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setIsNullable(boolean isNullable) {
        this.isNullable = isNullable;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public void setIsAutoIncrement(boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }
}
