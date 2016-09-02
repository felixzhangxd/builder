package ${packageName}.po;

import java.io.Serializable;
import javax.persistence.*;
<#list imports as import>
import ${import};
</#list>

@Table(catalog = "${catalog}", name = "${tableName}")
public class ${className}Po implements Serializable {<#list columns as column>
    <#if column.id>@Id </#if>@Column(name = "${column.columnName}")
    private ${column.fieldShortType} ${column.fieldName}; //${column.remarks}</#list>

<#list columns as column>
    public ${column.fieldShortType} ${column.fieldGetMethod} () {
        return this.${column.fieldName};
    }
    public void ${column.fieldSetMethod} (${column.fieldShortType} ${column.fieldName}) {
        this.${column.fieldName} = ${column.fieldName};
    }
</#list>
}
