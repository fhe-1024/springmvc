package springmvc.web.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import springmvc.web.dao.AppointmentBookDao;

/**
 * jdbc template 注解方式 换为hibernate后报错
 * 
 * @author fhe
 *
 */

@Repository
public class AppointmentBookDaoImpl implements AppointmentBookDao {

	private Log log = LogFactory.getLog(getClass());

	private JdbcTemplate jdbctemplate;

	@Autowired
	public void init(DataSource dataSource) {
		jdbctemplate = new JdbcTemplate(dataSource);

	}

	public List<Map<String, String>> get() throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> actors = this.jdbctemplate.query("select username, password from user",
				new RowMapper<Map<String, String>>() {
					public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("name", rs.getString("username"));
						map.put("password", rs.getString("password"));
						return map;
					}
				});
		return actors;
	}

	public void save() throws Exception {
		// TODO Auto-generated method stub
		int test = this.jdbctemplate.update("insert into user (id,username,password) values (?,?,?)", "4", "fhe",
				"helloworld");
		log.info(test);
	}

}
