
package project.dao;

import entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;



/**
 *
 * @author Less
 */
public class UserDaoSupport extends JdbcDaoSupport implements UserDAO {

	@Override
	public User getById(int id) {
		User user;
		String SELECT_BY_ID_ROW = "select * from users where id = ? LIMIT 0,1 ;";
		user = getJdbcTemplate().queryForObject(SELECT_BY_ID_ROW, (ParameterizedRowMapper<User>) (ResultSet rs, int i) -> {
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
		String SELECT_BY_ID_ROW = "select `name` from users where id = ? LIMIT 0,1 ;";
		return getJdbcTemplate().queryForObject(SELECT_BY_ID_ROW, new Object[]{id}, String.class);
	}

	@Override
	public int checkByLogin(String login, String password) {
		Map<String, Object> params = new HashMap<>();
		return getJdbcTemplate().queryForObject("select count(id) from users where login = ? AND password = ?;",
				new Object[]{login, password}, Integer.class);
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
	
}
