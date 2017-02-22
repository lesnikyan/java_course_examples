
package project.dao;

import entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author Less
 */
public class UserJdbcTemplate implements UserDAO {

	private DataSource dataSource;
	private JdbcTemplate dbTpl;
	private NamedParameterJdbcTemplate nameDbTpl;

	public UserJdbcTemplate(DataSource ds){
		dataSource = ds;
		dbTpl = new JdbcTemplate(dataSource);
		nameDbTpl = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public User getById(int id){
		User user;
		String SELECT_BY_ID_ROW = "select * from users where id = ? LIMIT 0,1 ;";
		user = dbTpl.queryForObject(
			SELECT_BY_ID_ROW,
			new ParameterizedRowMapper<User>(){
				@Override
				public User mapRow(ResultSet rs, int i) throws SQLException {
					User user = new User();
					user.setId(rs.getInt(1));
					user.setName(rs.getString(2));
					user.setLogin(rs.getString(3));
					user.setPassword(rs.getString(4));
					user.setEmail(rs.getString("email"));
					return user;
				}
			}, 
			id);
		return user;
	}

	@Override
	public String getNameById(int id){
		String SELECT_BY_ID_ROW = "select `name` from users where id = ? LIMIT 0,1 ;";
		return dbTpl.queryForObject(SELECT_BY_ID_ROW, new Object[]{id}, String.class);
	}

	@Override
	public int checkByLogin(String login, String password){
		Map<String, Object> params = new HashMap<>();
		params.put("login", login);
		params.put("pass", password);
		return nameDbTpl.queryForObject("select count(id) from users where login = :login AND password = :pass",
				params, Integer.class);
	}

	@Override
	public int add(User user){
		String sql = "insert into `users` (name, login, password, email) values (?, ?, ?, ?)";

		return dbTpl.update(
			sql, 
			user.getName(),
			user.getLogin(),
			user.getPassword(),
			user.getEmail()
		);
	}

		@Override
		public String getNameOfFirstUser(){
			return dbTpl.queryForObject("select name from users LIMIT 0,1", String.class);
		}
}
