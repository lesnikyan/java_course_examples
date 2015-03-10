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
import structure.Adress;
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
		testSQL();
		testDAO();
		testHQL();
		
		HibernateUtil.getSessionFactory().close();
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
		}
		p("end of SQL");
	}
	
	private static void testHQL(){
			/*
		http://www.techferry.com/articles/hibernate-jpa-annotations.html
		http://internetka.in.ua/hibernate-one-to-one/
		*/
		
		p("testRelations and HQL:");
		
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			List<User> users;
			// SQL
//			Query userQ = session.createSQLQuery("SELECT users.id, users.adress_id, users.name, users.age, users.gender, users.rating, "
//					+ "adress.street, adress.apartment, adress.building \n" +
//				"FROM users\n" +
//				"JOIN adress ON adress.id = users.adress_id\n" +
//				"WHERE adress_id IS NOT NULL \n" +
//				"LIMIT 0 , 1")
//					.addEntity(User.class);
//			users = userQ.list();
			
			// Criteria
//			users = session.createCriteria(User.class)
//					.add(Restrictions.isNotNull("adress"))
//					.setMaxResults(20)
//					.list();
			
			// HQL
			Query userQ = session.createQuery("select new User(u.name, u.gender, u.age, u.adress) "
					+ "from User as u join u.adress where u.adress is not null ");
			users = (List<User>) userQ.list();
			

			
			p("users count = " + users.size());
			if(users.size() > 0){
				User user = (User) users.get(0);
				Adress addr = user.getAdress();
				p("-- User Info");
				p(String.format("User: %s [%d], Address: %s, %d.", 
						user.getName(), user.getId(), 
						addr.getStreet(), addr.getBuilding()));
			}

		}catch(HibernateException ex){
			System.err.println(ex);
		} finally {
			if(session != null)
				session.close();
		}
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
			
			int from = users.size() > 10 ? users.size() - 11 : 0;
			int to = users.size() > 0 ? users.size() -1 : 0;
			p(String.format("testDAO.sublist keys: %d : %d", from, to));
			List<User> last = users.subList(from, to);
			printUsers(new ArrayList<User>(last));
			
			System.out.println("listByLikeNameAndMinRating");
			users = DAOFactory.getInstance().getUserDAO().listByLikeNameAndMinRating("Vasya%", 2.0);
			printUsers(new ArrayList<User>(users));
			
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