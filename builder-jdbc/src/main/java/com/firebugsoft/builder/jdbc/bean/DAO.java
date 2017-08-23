package com.firebugsoft.builder.jdbc.bean;

import java.util.List;

import com.firebugsoft.builder.jdbc.utils.StringUtils;

public class DAO {
    private String packages;
    private String name;
    private String table;
    private List<Column> columns;
    private List<Field> fields;

    public String getPackages() {
        return this.packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTable() {
        return this.table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<Column> getColumns() {
        return this.columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Field> getFields() {
        return this.fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getColumn() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.columns.size(); i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(this.columns.get(i).getName());
        }
        return builder.toString();
    }

    public String getCommas() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.columns.size(); i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append("?");
        }
        return builder.toString();
    }

    public String getArgs() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.columns.size(); i++) {
            if (i != 0) {
                builder.append(", ");
            }
            String columnName = this.columns.get(i).getName();
            String fieldName = StringUtils.toUpperCamelCase(columnName);
            builder.append("po.get" + fieldName + "()");
        }
        return builder.toString();
    }

    public boolean getLogicalDelete() {
        for (Column column : columns) {
            if ("deleted".equals(column.getName())) {
                return true;
            }
        }
        return false;
    }
}
