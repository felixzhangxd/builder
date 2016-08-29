package ${packageName}.dao;

import com.firebugsoft.common.jdbc.dao.AbstractDao;
import ${packageName}.po.${className}Po;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
@Repository
public class ${className}Dao extends AbstractDao<${className}Po>{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private BeanPropertyRowMapper mapper = BeanPropertyRowMapper.newInstance(${className}Po.class);
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Override
    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }
}