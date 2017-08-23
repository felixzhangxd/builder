package com.firebugsoft.builder.jdbc;

import com.firebugsoft.builder.jdbc.bean.Table;
import com.firebugsoft.builder.jdbc.service.SchemaService;
import com.firebugsoft.builder.jdbc.service.TemplateService;
import freemarker.template.TemplateException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Client {
    public static void main(String[] args) throws IOException, TemplateException, SQLException {
        System.setProperty("profile", "advertisement");
//        System.setProperty("profile", "housekeeper");
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext-*.xml");
        List<Table> tables = ctx.getBean(SchemaService.class).tables();
        //
        for(Table table : tables) {
            ctx.getBean(TemplateService.class).outputPO(table);
            ctx.getBean(TemplateService.class).outputDAO(table);
        }
        ctx.close();
    }
}
