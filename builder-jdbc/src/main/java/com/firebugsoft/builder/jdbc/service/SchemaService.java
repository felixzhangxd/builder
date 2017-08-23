package com.firebugsoft.builder.jdbc.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.firebugsoft.builder.jdbc.bean.Column;
import com.firebugsoft.builder.jdbc.bean.Table;

@Service
public class SchemaService {
    @Resource
    private DataSource dataSource;

    /** 单库处理 */
    public List<Table> tables() throws SQLException {
        Connection connection = dataSource.getConnection();
        String catalog = connection.getCatalog();
        DatabaseMetaData meta = connection.getMetaData();
        List<Table> tables = new LinkedList<>();
        ResultSet rs = meta.getTables(null, null, null, new String[] { "TABLE" });
        while (rs.next()) {
            String type = rs.getString("TABLE_TYPE");
            if ("TABLE".equals(type)) {
                tables.add(this.table(meta, catalog, rs.getString("TABLE_NAME")));
            }
        }
        rs.close();
        return tables;
    }

    /** 单表处理 */
    public Table table(String tableName) throws SQLException {
        Connection connection = dataSource.getConnection();
        DatabaseMetaData meta = connection.getMetaData();
        String catalog = connection.getCatalog();
        return this.table(meta, catalog, tableName);
    }

    private Table table(DatabaseMetaData meta, String catalog, String tableName) throws SQLException {
        List<Column> columns = this.columns(meta, tableName);
        return new Table(catalog, tableName, columns);
    }

    private List<Column> columns(DatabaseMetaData meta, String tableName) throws SQLException {
        List<Column> columns = new LinkedList<Column>();
        ResultSet rs = meta.getColumns(null, "%", tableName, "%");
        while (rs.next()) {
            String name = rs.getString("COLUMN_NAME");
            int type = rs.getInt("DATA_TYPE");
            String remarks = rs.getString("REMARKS");
            columns.add(new Column(name, type, remarks));
        }
        return columns;
    }

    // private static void print(ResultSet rs) throws SQLException {
    // ResultSetMetaData meta = rs.getMetaData();
    // List<String> columns = new LinkedList<>();
    // for (int i = 1; i < meta.getColumnCount(); i++) {
    // columns.add(meta.getColumnName(i));
    // }
    // while (rs.next()) {
    // for (String column : columns) {
    // System.out.println(column + ": " + rs.getString(column));
    // }
    // System.out.println();
    // }
    // }
    // private static void print(ResultSetMetaData meta) throws SQLException {
    // for (int i = 1; i <= meta.getColumnCount(); i++) {
    // System.out.print(meta.getColumnName(i) + ",");
    // System.out.print(meta.getColumnLabel(i) + ",");
    // System.out.print(meta.getColumnClassName(i) + ",");
    // System.out.print(meta.getCatalogName(i) + ",");
    // System.out.print(meta.getColumnDisplaySize(i) + ",");
    // System.out.print(meta.getColumnType(i) + ",");
    // System.out.print(meta.getColumnTypeName(i) + ",");
    // System.out.print(meta.getScale(i) + ",");
    // System.out.print(meta.getPrecision(i));
    // System.out.println();
    // }
    // }
}
