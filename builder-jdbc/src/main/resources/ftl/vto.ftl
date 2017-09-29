package ${packages}.vto;

import java.io.Serializable;
<#list imports as import>
import ${import};
</#list>

public class ${name}VTO implements Serializable {
<#list fields as field>
    private ${field.shorttype} ${field.name}; //${field.remarks}
</#list>

<#list fields as field>
    public ${field.shorttype} ${field.get} () {
        return this.${field.name};
    }
    public void ${field.set} (${field.shorttype} ${field.name}) {
        this.${field.name} = ${field.name};
    }
</#list>
}
