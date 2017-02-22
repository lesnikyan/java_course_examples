/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.impl.UserDAOImpl;

/**
 *
 * @author Less
 */
public class DAOFactory {

	private static UserDAO userDAO = null;
	private static DAOFactory instance = null;

	public static synchronized DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}

	public UserDAO getUserDAO() {
		if (userDAO == null) {
			userDAO = new UserDAOImpl();
		}
		return userDAO;
	}
}
