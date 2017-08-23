package com.firebugsoft.builder.jdbc.bean;

import com.firebugsoft.builder.jdbc.utils.StringUtils;


/** 数据库表字段 */
public class Column {
    private String name; // 字段名称
    private Integer type; // 字段类型
    private String remarks; // 字段注释
    
    public Column(String name, Integer type, String remarks) {
        this.name = name;
        this.type = type;
        this.remarks = remarks;
    }

    public String getName() {
        return this.name;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getGetMethod() {
        String fieldName = StringUtils.toUpperCamelCase(name);
        return StringUtils.toGetMethod(fieldName);
    }
}
