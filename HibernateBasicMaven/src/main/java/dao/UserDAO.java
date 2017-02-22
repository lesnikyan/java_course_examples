/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.criterion.Criterion;
import structure.User;

public interface UserDAO {
	// simple CRUD
    public void addUser(User student) throws SQLException;   //добавить студента
    public void updateUser(User student) throws SQLException;//обновить студента
    public User getUserById(Long id) throws SQLException;    //получить стедента по id
    public List getAllUsers() throws SQLException;              //получить всех студентов
    public void deleteUser(User student) throws SQLException;//удалить студента
	
	// criteria expressions
	public List<User> listByBetweenAge(Integer min, Integer max);
	public List<User> listByLikeName(String nameTpl);
	public List<User> listByMinRating(Double rating);
	public List<User> listByLikeNameAndMinRating(String nameTpl, Double rating);
	public List<User> listByExpression(Criterion expr);
	public List<User> listByExpressionSet(Criterion expr);
	
	
}