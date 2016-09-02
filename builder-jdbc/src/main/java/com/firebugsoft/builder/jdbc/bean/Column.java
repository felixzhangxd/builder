package com.firebugsoft.builder.jdbc.bean;

import com.firebugsoft.builder.jdbc.TypesMapping;
import com.hxzxg.common.bean.utils.CharacterUtils;

/** 数据库表字段 */
public class Column {
    private String columnName;    //字段名称
    private Integer columnType;   //字段类型
    private String remarks;       //字段注释

    public boolean isId() {
        return "id".equals(columnName);
    }
    public String getFieldName() {
        return CharacterUtils.toCamelLowerCase(this.columnName);
    }
    public String getFieldShortType() {
        return TypesMapping.getShortType(this.columnType);
    }
    public String getFieldLongType() {
        return TypesMapping.getLongType(this.columnType);
    }
    public String getFieldGetMethod() {
        return CharacterUtils.toGetMethod(this.getFieldName());
    }
    public String getFieldSetMethod() {
        return CharacterUtils.toSetMethod(this.getFieldName());
    }
    public String getFindMethod() {
        return "findBy" + CharacterUtils.toCamelUpperCase(this.columnName);
    }
    //
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getColumnType() {
        return columnType;
    }

    public void setColumnType(Integer columnType) {
        this.columnType = columnType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
