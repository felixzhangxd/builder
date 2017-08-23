package com.firebugsoft.builder.jdbc.bean;

import com.firebugsoft.builder.jdbc.def.TypesMapping;
import com.firebugsoft.builder.jdbc.utils.StringUtils;

public class Field {
    private String name;
    private String remarks;
    private String shorttype;
    private String longtype;

    public Field(Column column) {
        this.name = StringUtils.toLowerCamelCase(column.getName());
        this.remarks = column.getRemarks();
        this.shorttype = TypesMapping.getShorttype(column.getType());
        this.longtype = TypesMapping.getLongtype(column.getType());
    }

    public String getName() {
        return this.name;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public String getShorttype() {
        return this.shorttype;
    }

    public String getLongtype() {
        return this.longtype;
    }

    public String getGet() {
        return StringUtils.toGetMethod(this.name);
    }

    public String getSet() {
        return StringUtils.toSetMethod(this.name);
    }
}
