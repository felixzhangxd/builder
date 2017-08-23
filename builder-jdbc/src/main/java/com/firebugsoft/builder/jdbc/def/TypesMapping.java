package com.firebugsoft.builder.jdbc.def;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库字段类型关系映射
 */
public class TypesMapping {
    private static TypesMapping instance = new TypesMapping();
    
    public static TypesMapping getInstance() {
        return instance;
    }
    private Map<Integer, String> longTypes = new HashMap<>();
    private Map<Integer, String> shortTypes = new HashMap<>();
    private TypesMapping() {
        longTypes.put(-7, "java.lang.Boolean");
        longTypes.put(-6, "java.lang.Integer");
        longTypes.put(-5, "java.lang.Long");
        longTypes.put(-4, "java.lang.byte[]");
        longTypes.put(-2, "java.lang.String");
        longTypes.put(-1, "java.lang.String");
        longTypes.put(1, "java.lang.String");
        longTypes.put(3, "java.math.BigDecimal");
        longTypes.put(4, "java.lang.Integer");
        longTypes.put(5, "java.lang.Integer");
        longTypes.put(6, "java.lang.Float");
        longTypes.put(7, "java.lang.Float");
        longTypes.put(8, "java.lang.Double");
        longTypes.put(12, "java.lang.String");
        longTypes.put(16, "java.lang.Boolean");
        longTypes.put(91, "java.sql.Date");
        longTypes.put(92, "java.sql.Time");
        longTypes.put(93, "java.sql.Timestamp");
        //
        shortTypes.put(-7, "Boolean");
        shortTypes.put(-6, "Integer");
        shortTypes.put(-5, "Long");
        shortTypes.put(-4, "byte[]");
        shortTypes.put(-2, "String");
        shortTypes.put(-1, "String");
        shortTypes.put(1, "String");
        shortTypes.put(3, "BigDecimal");
        shortTypes.put(4, "Integer");
        shortTypes.put(5, "Integer");
        shortTypes.put(6, "Float");
        shortTypes.put(7, "Float");
        shortTypes.put(8, "Double");
        shortTypes.put(12, "String");
        shortTypes.put(16, "Boolean");
        shortTypes.put(91, "Date");
        shortTypes.put(92, "Time");
        shortTypes.put(93, "Timestamp");
    }

    public static String getLongtype(Integer type) {
        return instance.longTypes.get(type);
    }
    public static String getShorttype(Integer type) {
        return instance.shortTypes.get(type);
    }
}
