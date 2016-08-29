package com.firebugsoft.builder.jdbc.service;

import com.firebugsoft.builder.jdbc.bean.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SchemaService {
    @Resource
    private DataSource dataSource;
    /** 单库处理 */
    public Schema schema() throws SQLException {
        Connection connection = dataSource.getConnection();
        String catalog = connection.getCatalog();
        DatabaseMetaData meta =  connection.getMetaData();
        List<Table> tables  = new LinkedList<>();
        ResultSet rs = meta.getTables(null, null, null, new String[]{"TABLE"});
        while(rs.next()) {
            String type = rs.getString("TABLE_TYPE");
            if("TABLE".equals(type)) {
                tables.add(table(meta, rs.getString("TABLE_NAME")));
            }
        }
        rs.close();
        return new Schema(catalog, tables);
    }
    /** 单表处理 */
    public Schema schema(String tableName) throws SQLException {
        Connection connection = dataSource.getConnection();
        String catalog = connection.getCatalog();
        DatabaseMetaData meta =  connection.getMetaData();
        List<Table> tables  = new LinkedList<>();
        tables.add(table(meta, tableName));
        return new Schema(catalog, tables);
    }
    public Table table(String tableName) throws SQLException {
        return this.table(dataSource.getConnection().getMetaData(), tableName);
    }

    private Table table(DatabaseMetaData meta, String tableName) throws SQLException {
        Table table = new Table();
        table.setName(tableName);
        table.setPks(this.pks(meta, tableName));
        table.setIndexes(this.indexes(meta, tableName));
        table.setColumns(this.columns(meta, tableName));
        return table;
    }
    private List<Pk> pks(DatabaseMetaData meta, String tableName) throws SQLException {
        List<Pk> pks = new LinkedList<Pk>();
        ResultSet rs = meta.getPrimaryKeys(null,null, tableName);
        while(rs.next()) {
            Pk pk = new Pk();
            pk.setColumnName(rs.getString("COLUMN_NAME"));
            pks.add(pk);
        }
        rs.close();
        return pks;
    }
    private List<Index> indexes(DatabaseMetaData meta, String tableName) throws SQLException {
        Map<String, Index> indexes = new LinkedHashMap<>();
        ResultSet rs = meta.getIndexInfo(null, null, tableName, false, true);
        while (rs.next()){
            String indexName = rs.getString("INDEX_NAME");
            Index index = indexes.containsKey(indexName) ? indexes.get(indexName) : new Index();
            index.setName(indexName);
            index.setIsUnique(!rs.getBoolean("NON_UNIQUE"));
            index.addColumnName(rs.getInt("ORDINAL_POSITION") - 1, rs.getString("COLUMN_NAME"));
            indexes.put(indexName, index);
        }
        rs.close();
        return new LinkedList<>(indexes.values());
    }
    private List<Column> columns(DatabaseMetaData meta, String tableName) throws SQLException {
        List<Column> columns = new LinkedList<>();
        ResultSet rs = meta.getColumns(null,"%",tableName,"%");
        while(rs.next()) {
            Column column = new Column();
            column.setName(rs.getString("COLUMN_NAME"));
            column.setType(rs.getInt("DATA_TYPE"));
            column.setIsNullable(rs.getBoolean("IS_NULLABLE"));
            column.setIsAutoIncrement(rs.getBoolean("IS_AUTOINCREMENT"));
            column.setRemarks(rs.getString("REMARKS"));
            columns.add(column);
        }
        return columns;
    }

    private static void print(String rs) throws SQLException {
        System.out.println(rs);
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
