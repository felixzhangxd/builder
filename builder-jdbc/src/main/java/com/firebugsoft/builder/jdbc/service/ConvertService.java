package com.firebugsoft.builder.jdbc.service;

import com.firebugsoft.builder.jdbc.bean.*;
import com.hxzxg.common.bean.utils.CharacterUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ConvertService {
    @Resource
    private Map<String, String> types;
    @Value(value = "${package.name}")
    private String packageName;

    public List<Po> toPos(Schema schema) {
        List<Po> pos = new LinkedList<>();
        for(Table table : schema.getTables()) {
            Po po = new Po();
            po.setPackageName(packageName);
            po.setImports(toPoImports(table));
            po.setCatalog(schema.getName());
            po.setTableName(table.getName());
            po.setClassName(CharacterUtils.toCamelUpperCase(table.getName()));
            po.setFields(toPoFields(table));
            pos.add(po);
        }
        return pos;
    }
    private List<String> toPoImports(Table table) {
        Set<String> imports = new HashSet<>();
        for(Column column : table.getColumns()) {
            String type = types.get(column.getType()+"");
            if(!type.startsWith("java.lang")) {
                imports.add(type);
            }
        }
        return new LinkedList<>(imports);
    }
    private List<Field> toPoFields(Table table) {
        List<Field> fields = new LinkedList<>();
        for(Column column : table.getColumns()) {
            Field field = new Field();
            for(Pk pk : table.getPks()) {
                if(pk.getColumnName().equals(column.getName())) {
                    field.setIsId(true);
                    break;
                }
            }
            field.setColumnName(column.getName());
            String type = types.get(column.getType()+"");
            field.setClassName(type.substring(type.lastIndexOf(".")+1));
            field.setName(CharacterUtils.toCamelLowerCase(column.getName()));
            field.setIsNullable(column.isNullable());
            for(Index index : table.getIndexes()) {
                if(index.getColumnNames().size() == 1 && index.getColumnNames().get(0).equals(column.getName())) {
                    field.setIsUnique(true);
                    break;
                }
            }
            field.setRemarks(column.getRemarks());
            field.setGet(CharacterUtils.toGetMethod(field.getName()));
            field.setSet(CharacterUtils.toSetMethod(field.getName()));
            fields.add(field);
        }
        return fields;
    }

    public List<Dao> toDaos(Schema schema) {
        List<Dao> daos = new LinkedList<>();
        for(Table table : schema.getTables()) {
            Dao dao = new Dao();
            dao.setPackageName(packageName);
            dao.setClassName(CharacterUtils.toCamelUpperCase(table.getName()));
            daos.add(dao);
        }
        return daos;

    }
}
