package com.firebugsoft.builder.jdbc.bean;

import java.util.ArrayList;
import java.util.List;

/** 数据库表索引 */
public class Index {
    private String name;
    private boolean isUnique;
    private List<String> columnNames;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(boolean isUnique) {
        this.isUnique = isUnique;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public void addColumnName(int ordinalPosition, String columnName) {
        if(this.columnNames == null) {
            this.columnNames = new ArrayList<>();
        }
        if(!this.columnNames.contains(columnName)) {
            this.columnNames.add(ordinalPosition, columnName);
        }
    }
}
