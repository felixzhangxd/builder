package ${packages}.dao;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ${packages}.po.${name}PO;

@Repository
public class ${name}DAO {
    private Logger logger = LoggerFactory.getLogger("sql");
    private BeanPropertyRowMapper<${name}PO> mapper = BeanPropertyRowMapper.newInstance(${name}PO.class);
    @Resource
    private JdbcTemplate masterJdbcTemplate;
    @Resource
    private JdbcTemplate slaveJdbcTemplate;
    
    public int save(${name}PO po) {
        String sql = "insert into ${table} (${column}) values(${commas})";
        Object[] args = { ${args} };
        logger.info("{}; {}", sql, args);
        return masterJdbcTemplate.update(sql, args);
    }
    
    public int deleteById(Long id) {
        String sql = "update ${table} set deleted=true where id=?";
        Object[] args = { id };
        logger.info("{}; {}", sql, args);
        return masterJdbcTemplate.update(sql, args);
    }
    
    public int modifyById(${name}PO po) {
        StringBuilder sql = new StringBuilder();
        List<Object> args = new LinkedList<Object>();
        sql.append("update ${table} set ");
    <#list columns as column>
	<#if column.name != "id" && column.name != "deleted" && column.name != "create_at" && column.name != "update_at">
    	if(po.${column.getMethod}() != null) {
	    	sql.append("${column.name}=?,");
	    	args.add(po.${column.getMethod}());
    	}
	</#if>
    </#list>
    	sql.deleteCharAt(sql.length() - 1);
    	sql.append(" where id=?");
    	args.add(po.getId());
        logger.info("{}; {}", sql, args);
        return masterJdbcTemplate.update(sql.toString(), args.toArray());
    }
    
    public ${name}PO queryById(Long id) {
        String sql = "select ${column} from ${table} where id=?";
        Object[] args = { id };
        logger.info("{}; {}", sql, args);
        return slaveJdbcTemplate.queryForObject(sql, args, mapper);
    }
}