package com.firebugsoft.builder.jdbc.bean;

import java.util.List;

/** 数据库表 */
public class Table {
    private String name;
    private List<Pk> pks;
    private List<Index> indexes;
    private List<Column> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pk> getPks() {
        return pks;
    }

    public void setPks(List<Pk> pks) {
        this.pks = pks;
    }

    public List<Index> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Index> indexes) {
        this.indexes = indexes;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
