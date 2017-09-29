package ${packages}.vto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
<#list imports as import>
import ${import};
</#list>
@ApiModel(value = "${name}")
public class ${name}VTO implements Serializable {
<#list fields as field>
	@ApiModelProperty(value = "${field.remarks}")
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
