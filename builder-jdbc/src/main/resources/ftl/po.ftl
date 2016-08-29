package ${packageName}.po;

import java.io.Serializable;
import javax.persistence.*;
<#list imports as import>
import ${import};
</#list>

@Entity
@Table(catalog = "${catalog}", name = "${tableName}")
public class ${className}Po implements Serializable {<#list fields as field>
    <#if field.id>@Id </#if>@Column(name = "${field.columnName}", unique = ${field.unique?string('true','false')}, nullable = ${field.nullable?string('true','false')})
    private ${field.className} ${field.name}; //${field.remarks}</#list>

<#list fields as field>
    public ${field.className} ${field.get} () {
        return this.${field.name};
    }
    public void ${field.set} (${field.className} ${field.name}) {
        this.${field.name} = ${field.name};
    }
</#list>
}
