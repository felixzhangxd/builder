package com.firebugsoft.builder.jdbc.service;

import com.firebugsoft.builder.jdbc.bean.Table;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@Service
public class TemplateService {
    @Value(value="${output.base.dir}")
    private String outputBaseDir;
    @Resource
    private Configuration configuration;

    public void outputPo(Table table) throws IOException, TemplateException {
        File dir = getOutputDir(table.getPackageName(), "po");
        File file = new File(dir, table.getClassName() + "Po.java");
        Writer out = new FileWriter(file);
        configuration.getTemplate("po.ftl").process(table, out);
    }
    public void outputDao(Table table) throws IOException, TemplateException {
        File dir = getOutputDir(table.getPackageName(), "dao");
        File file = new File(dir, table.getClassName() + "Dao.java");
        Writer out = new FileWriter(file);
        configuration.getTemplate("dao.ftl").process(table, out);
    }
    private File getOutputDir(String prefixPackageName, String suffixPackageName) {
        String packageName = prefixPackageName + "." + suffixPackageName;
        File dir = new File(outputBaseDir + "/" + packageName.replaceAll("\\.", "/"));
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }
}
