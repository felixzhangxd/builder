package com.firebugsoft.builder.jdbc.service;

import com.firebugsoft.builder.jdbc.bean.Column;
import com.firebugsoft.builder.jdbc.bean.Index;
import com.firebugsoft.builder.jdbc.bean.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class SchemaService {
    @Value(value="${package.name}")
    private String packgeName;
    @Resource
    private DataSource dataSource;
    /** 单库处理 */
    public List<Table> tables() throws SQLException {
        Connection connection = dataSource.getConnection();
        DatabaseMetaData meta =  connection.getMetaData();
        List<Table> tables  = new LinkedList<>();
        ResultSet rs = meta.getTables(null, null, null, new String[]{"TABLE"});
        while(rs.next()) {
            String type = rs.getString("TABLE_TYPE");
            if("TABLE".equals(type)) {
                tables.add(this.table(meta, rs.getString("TABLE_NAME")));
            }
        }
        rs.close();
        return tables;
    }

    /** 单表处理 */
    public Table table(String tableName) throws SQLException {
        Connection connection = dataSource.getConnection();
        DatabaseMetaData meta =  connection.getMetaData();
        return this.table(meta, tableName);
    }

    private Table table(DatabaseMetaData meta, String tableName) throws SQLException {
        Table table = new Table();
        table.setPackageName(packgeName);
        table.setCatalog(meta.getConnection().getCatalog());
        table.setTableName(tableName);
        table.setColumns(this.columns(meta, tableName));
        table.setIndexes(this.indexes(meta, tableName));
        table.indexRelateColumn();
        return table;
    }

    private List<Column> columns(DatabaseMetaData meta, String tableName) throws SQLException {
        List<Column> columns = new LinkedList<>();
        ResultSet rs = meta.getColumns(null,"%",tableName,"%");
        while(rs.next()) {
            Column column = new Column();
            column.setColumnName(rs.getString("COLUMN_NAME"));
            column.setColumnType(rs.getInt("DATA_TYPE"));
            column.setRemarks(rs.getString("REMARKS"));
            columns.add(column);
        }
        return columns;
    }
    private List<Index> indexes(DatabaseMetaData meta, String tableName) throws SQLException {
        List<Index> indexes = new LinkedList<>();
        ResultSet rs = meta.getIndexInfo(null, null, tableName, false, true);
        while (rs.next()){
            Index index = new Index();
            index.setName(rs.getString("INDEX_NAME"));
            index.setNonUnique(rs.getBoolean("NON_UNIQUE"));
            index.setColumnName(rs.getString("COLUMN_NAME"));
            indexes.add(index);
        }
        rs.close();
        return indexes;
    }

    private static void print(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        List<String> columns = new LinkedList<>();
        for(int i=1;i<meta.getColumnCount();i++) {
            columns.add(meta.getColumnName(i));
        }
        while(rs.next()) {
            for(String column : columns) {
                System.out.println(column + ": " + rs.getString(column));
            }
            System.out.println();
        }
    }
    private static void print(ResultSetMetaData meta) throws SQLException {
        for(int i=1; i <= meta.getColumnCount(); i++) {
            System.out.print(meta.getColumnName(i) + ",");
            System.out.print(meta.getColumnLabel(i) + ",");
            System.out.print(meta.getColumnClassName(i) + ",");
            System.out.print(meta.getCatalogName(i) + ",");
            System.out.print(meta.getColumnDisplaySize(i)+",");
            System.out.print(meta.getColumnType(i) + ",");
            System.out.print(meta.getColumnTypeName(i) + ",");
            System.out.print(meta.getScale(i) + ",");
            System.out.print(meta.getPrecision(i));
            System.out.println();
        }
    }

}
