package ${packageName}.dao;

import com.firebugsoft.common.jdbc.dao.AbstractDao;
import ${packageName}.po.${className}Po;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.sql.*;
import javax.annotation.Resource;
@Repository
public class ${className}Dao extends AbstractDao<${className}Po>{
    private BeanPropertyRowMapper mapper = BeanPropertyRowMapper.newInstance(${className}Po.class);
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Override
    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

<#list indexes as index>
<#if index.nonUnique>
    public List<${className}Po> ${index.column.findMethod}(${index.column.fieldShortType} ${index.column.fieldName}){
        String sql = "SELECT * FROM ${catalog}.${tableName} WHERE ${index.column.columnName}=?";
        Object[] values = {${index.column.fieldName}};
        logger.info("{}\r\n{}", sql, values);
        return this.getJdbcTemplate().query(sql, values, mapper);
    }
<#else>
    public ${className}Po ${index.column.findMethod}(${index.column.fieldShortType} ${index.column.fieldName}){
        String sql = "SELECT * FROM ${catalog}.${tableName} WHERE ${index.column.columnName}=?";
        Object[] values = {${index.column.fieldName}};
        logger.info("{}\r\n{}", sql, values);
        List<${className}Po> pos = this.getJdbcTemplate().query(sql, values, mapper);
        return pos.isEmpty() ? null : pos.get(0);
    }
</#if>
</#list>
}