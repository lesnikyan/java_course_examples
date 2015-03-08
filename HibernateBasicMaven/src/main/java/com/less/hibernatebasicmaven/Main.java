/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.less.hibernatebasicmaven;

import dao.DAOFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import structure.User;
import util.HibernateUtil;

/**
 * 
 * docs: 
 * http://javaxblog.ru/article/java-hibernate-1/
 * http://samsonych.com/lib/hibernate/index.html
 * http://www.javaportal.ru/java/articles/hibernate_annotations.html
 * 
 * Java. Методы программирования.
 * 
 * Аннотации:
 * http://www.techferry.com/articles/hibernate-jpa-annotations.html
 * 
 *
 * @author Less
 */
public class Main {
	
	public static void main(String[] args){
		//testSQL();
		testDAO();
	}
	
	private static void testSQL(){
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();

			// Simple SQL example
			p("\nSimple SQL query");

			Query query1 = session.createSQLQuery("SELECT * FROM users WHERE id between 10 AND 15; ")
					.addEntity(User.class);
			List<User> users1 = query1.list();
			p("Simple select:");
			printUsers(new ArrayList<User>(users1));

			// Prepared SQL Example
			p("\nPrepared by name example");
			
			Query query2 = session.createSQLQuery("SELECT * FROM users WHERE name IN (:name1, :name2) AND gender = :gen LIMIT 0, 3;")
					.addEntity(User.class);
			query2.setString("name1", "Vasya Pupkin");
			query2.setString("name2", "Tony Stark");
			query2.setString("gen", User.Gender.MALE);
			List<User> users2 = query2.list();
			p("Select with named params:");
			printUsers(new ArrayList<User>(users2));

			// Prepared by index SQL example
			p("\nPrepared by index example");
			
			Query query3 = session.createSQLQuery("SELECT * FROM users WHERE rating > ? LIMIT 0, 4 ;")
					.addEntity(User.class);
			query3.setDouble(0, 2.5);
			List<User> users3 = query3.list();
			p("Select by indexed params:");
			printUsers(new ArrayList<User>(users3));

		}catch(HibernateException ex){
			System.err.println(ex);
		} finally {
			if(session != null)
				session.close();
			HibernateUtil.getSessionFactory().close();
		}
		p("end of SQL");
	}

	private static void testDAO(){
		try {
			System.out.println("Start...");
			
			// create users
			User user1 = new User("Vasya Pupkin", "male", 25);
			User user2 = new User();
			
			user2.setName("Petryk P'yatochkin");
			user2.setAge(20);
			user2.setGender(User.Gender.MALE);
			// save users
			DAOFactory.getInstance().getUserDAO().addUser(user1);
			DAOFactory.getInstance().getUserDAO().addUser(user2);
			
			// get all users
			List<User> users = DAOFactory.getInstance().getUserDAO().getAllUsers();
			
			int from = users.size() > 10 ? users.size() - 10 : 0;
			List<User> last = users.subList(from, users.size() - 1);
			printUsers(new ArrayList<User>(last));
			
			System.out.println("listByLikeNameAndMinRating");
			users = DAOFactory.getInstance().getUserDAO().listByLikeNameAndMinRating("Vasya%", 2.0);
			printUsers(new ArrayList<User>(users));
			
			HibernateUtil.getSessionFactory().close();
			
		} catch (SQLException ex) {
			System.out.println("Main.main exception:");
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			System.err.println(ex);
		}
		System.out.println("end of dao");
	}
	
	/**
	 * print list of users
	 * @param users 
	 */
	private static void printUsers(ArrayList<User> users){
		System.out.println("======== Users ========");
		for(User user: users) { 
				System.out.println(
						"Name : " + user.getName() 
						+ ", Age : " + user.getAge() 
						+ ",  ID : " + user.getId());
				System.out.println("=============================");
			}       
	}
	
	/**
	 * print object
	 */
	public static void p(Object x){
		System.out.println(x);
	}
	
}

/*
POJO - Plain Ordinary Java Object
JPA - Java Persistence API


*/