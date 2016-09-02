package com.firebugsoft.builder.jdbc.bean;

import com.hxzxg.common.bean.utils.CharacterUtils;

import java.util.LinkedList;
import java.util.List;

/** 数据库表 */
public class Table {
    private String packageName;
    private String catalog;
    private String tableName;
    private List<Column> columns;
    private List<Index> indexes;
    //
    public List<String> getImports() {
        List<String> imports = new LinkedList<>();
        for(Column column : this.columns) {
            String type = column.getFieldLongType();
            if(!type.startsWith("java.lang")) {
                imports.add(type);
            }
        }
        return imports;
    }
    public String getClassName() {
        return CharacterUtils.toCamelUpperCase(tableName);
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void indexRelateColumn() {
        for(Index index : indexes) {
            String columnName = index.getColumnName();
            for(Column column : columns) {
                if(columnName.equals(column.getColumnName())) {
                    index.setColumn(column);
                }
            }
        }
    }
}
