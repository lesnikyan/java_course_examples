
package project.dao;

import entities.User;

/**
 *
 * @author Less
 */
public interface UserDAO {
	public User getById(int id);
	public String getNameById(int id);
	public int checkByLogin(String login, String password);
	public int add(User user);
	public String getNameOfFirstUser();
}
