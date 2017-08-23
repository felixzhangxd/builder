package com.firebugsoft.builder.jdbc.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.firebugsoft.builder.jdbc.bean.Column;
import com.firebugsoft.builder.jdbc.bean.DAO;
import com.firebugsoft.builder.jdbc.bean.Field;
import com.firebugsoft.builder.jdbc.bean.PO;
import com.firebugsoft.builder.jdbc.bean.Table;
import com.firebugsoft.builder.jdbc.utils.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Service
public class TemplateService {
    @Value("${output.directory}")
    private String outputDirectory;
    @Value(value = "${output.package}")
    private String outputPackge;
    @Resource
    private Configuration configuration;

    public void outputPO(Table table) throws IOException, TemplateException {
        String packages = outputPackge;
        String name = StringUtils.toUpperCamelCase(table.getName());
        List<Field> fields = new ArrayList<Field>(table.getColumns().size());
        for(Column column : table.getColumns()) {
            fields.add(new Field(column));
        }
        PO po = new PO(packages, name, fields);
        //
        File dir = getOutputDir(outputPackge, "po");
        File file = new File(dir, po.getName() + "PO.java");
        Writer out = new FileWriter(file);
        configuration.getTemplate("po.ftl").process(po, out);
    }

    public void outputDAO(Table table) throws IOException, TemplateException {
        DAO dao = new DAO();
        dao.setPackages(outputPackge);
        dao.setName(StringUtils.toUpperCamelCase(table.getName()));
        dao.setTable(table.getName());
        dao.setColumns(table.getColumns());
        //
        File dir = getOutputDir(outputPackge, "dao");
        File file = new File(dir, dao.getName() + "DAO.java");
        Writer out = new FileWriter(file);
        configuration.getTemplate("dao.ftl").process(dao, out);
    }

    private File getOutputDir(String prefixPackageName, String suffixPackageName) {
        String packageName = prefixPackageName + "." + suffixPackageName;
        File dir = new File(outputDirectory + "/" + packageName.replaceAll("\\.", "/"));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }
}
