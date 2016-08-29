package com.firebugsoft.builder.jdbc.service;

import com.firebugsoft.builder.jdbc.bean.Dao;
import com.firebugsoft.builder.jdbc.bean.Po;
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

    public void output(Po po) throws IOException, TemplateException {
        File dir = getOutputDir(po.getPackageName(), "po");
        File file = new File(dir, po.getClassName() + "Po.java");
        Writer out = new FileWriter(file);
        configuration.getTemplate("po.ftl").process(po, out);
    }
    public void output(Dao dao) throws IOException, TemplateException {
        File dir = getOutputDir(dao.getPackageName(), "dao");
        File file = new File(dir, dao.getClassName() + "Dao.java");
        Writer out = new FileWriter(file);
        configuration.getTemplate("dao.ftl").process(dao, out);
    }
    private File getOutputDir(String prefixPackageName, String suffixPackageName) {
        String packageName = prefixPackageName + "." + suffixPackageName;
        File dir = new File(outputBaseDir, packageName.replaceAll("^\\.$", "/"));
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

}
