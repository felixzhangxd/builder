package com.firebugsoft.builder.jdbc;

import com.firebugsoft.builder.jdbc.bean.Dao;
import com.firebugsoft.builder.jdbc.bean.Po;
import com.firebugsoft.builder.jdbc.bean.Schema;
import com.firebugsoft.builder.jdbc.service.ConvertService;
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
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext-*.xml");
        //
        Schema schema = ctx.getBean(SchemaService.class).schema();
        List<Po> pos = ctx.getBean(ConvertService.class).toPos(schema);
        for(Po po : pos) {
            ctx.getBean(TemplateService.class).output(po);
        }
        List<Dao> daos = ctx.getBean(ConvertService.class).toDaos(schema);
        for(Dao dao : daos) {
            ctx.getBean(TemplateService.class).output(dao);
        }
        //
        ctx.close();
    }
}
