
package project.dao;

import entities.User;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author Less
 */
public class NamedUserDao extends NamedParameterJdbcDaoSupport implements UserDAO {
	
	@Override
	public User getById(int id) {
		User user;
		user = getJdbcTemplate().queryForObject(
			"select * from users where id = ? LIMIT 0,1 ;", 
			(ParameterizedRowMapper<User>) (ResultSet rs, int i) -> {
				User user1 = new User();
				user1.setId(rs.getInt(1));
				user1.setName(rs.getString(2));
				user1.setLogin(rs.getString(3));
				user1.setPassword(rs.getString(4));
				user1.setEmail(rs.getString("email"));
				return user1;
			}, 
		id);
		return user;
	}

	@Override
	public String getNameById(int id) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		return getNamedParameterJdbcTemplate()
				.queryForObject("select `name` from users where id = :id LIMIT 0,1 ;", data, String.class);
	}

	@Override
	public int add(User user) {
		return getJdbcTemplate().update(
			"insert into `users` (name, login, password, email) values (?, ?, ?, ?);", 
			user.getName(),
			user.getLogin(),
			user.getPassword(),
			user.getEmail()
		);
	}

	@Override
	public String getNameOfFirstUser() {
		return getJdbcTemplate().queryForObject("select name from users LIMIT 0,1", String.class);
	}
	
	@Override
	public int checkByLogin(String login, String password){
		Map<String, Object> params = new HashMap<>();
		params.put("login", login);
		params.put("pass", password);
		return getNamedParameterJdbcTemplate().queryForObject("select count(id) from users where login = :login AND password = :pass",
				params, Integer.class);
	}
}
