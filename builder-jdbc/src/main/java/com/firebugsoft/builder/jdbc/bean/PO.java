package com.firebugsoft.builder.jdbc.bean;

import java.util.LinkedList;
import java.util.List;

public class PO {
    private String packages;
    private String name;
    private List<Field> fields;

    public PO() {}

    public PO(String packages, String name, List<Field> fields) {
        this.packages = packages;
        this.name = name;
        this.fields = fields;
    }

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

    public List<Field> getFields() {
        return this.fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<String> getImports() {
        List<String> imports = new LinkedList<String>();
        for (Field field : fields) {
            String type = field.getLongtype();
            if (type.startsWith("java.lang")) {
                continue;
            }
            if (imports.contains(type)) {
                continue;
            }
            imports.add(type);
        }
        return imports;
    }
}
