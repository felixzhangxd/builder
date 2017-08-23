package com.firebugsoft.builder.jdbc.bean;

import java.util.List;

/** 数据库表 */
public class Table {
    private String catalog;
    private String name;
    private List<Column> columns;

    public Table(String catalog, String name, List<Column> columns) {
        this.catalog = catalog;
        this.name = name;
        this.columns = columns;
    }

    public String getCatalog() {
        return this.catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return this.columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
