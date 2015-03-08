/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

/**
 *
 * @author Less
 */
import dao.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import structure.User;
import util.HibernateUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public void addUser(User user) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil
					.getSessionFactory()
					.openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			expr(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public void updateUser(User user) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			expr(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public User getUserById(Long id) throws SQLException {
		Session session = null;
		User user = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			user = (User) session.load(User.class, id);
		} catch (HibernateException e) {
			expr(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		Session session = null;
		List<User> users = new ArrayList<User>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			users = session.createCriteria(User.class)
					.setMaxResults(20)
					.list();
		} catch (HibernateException e) {
			expr(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return users;
	}

	@Override
	public void deleteUser(User user) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			expr(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	
	/*
	Restrictionc API:
	https://docs.jboss.org/hibernate/orm/3.2/api/org/hibernate/criterion/Restrictions.html
	http://javaxblog.ru/article/java-hibernate-2/ (Expression - deprecated)
	*/
	@Override
	public List<User> listByBetweenAge(Integer min, Integer max) {
		ArrayList<User> users = new ArrayList<User>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			users = (ArrayList<User>) session.createCriteria(User.class)
					.add(Restrictions.between("age", min, max)).list();
		} catch (HibernateException e) {
			expr(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return users;
	}

	@Override
	public List<User> listByLikeName(String nameTpl) {
		ArrayList<User> users = new ArrayList<User>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			users = (ArrayList<User>) session.createCriteria(User.class)
					.add(Restrictions.like("name", nameTpl)).list();
		} catch (HibernateException e) {
			expr(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return users;
	}

	@Override
	public List<User> listByMinRating(Double rating) {
		ArrayList<User> users = new ArrayList<User>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = session.createCriteria(User.class)
					.add(Restrictions.gt("rating", rating)) // gt - greater then
					.add(Restrictions.or(Restrictions.eq("rating", rating)));
			users = (ArrayList<User>) crit.list();
		} catch (HibernateException e) {
			expr(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return users;
	}

	@Override
	public List<User> listByLikeNameAndMinRating(String nameTpl, Double rating) {
		ArrayList<User> users = new ArrayList<User>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = session.createCriteria(User.class)
					.add(Restrictions.gt("rating", rating)) // gt - greater then
					.add(Restrictions.and(Restrictions.like("name", nameTpl)));
			users = (ArrayList<User>) crit.list();
		} catch (HibernateException e) {
			expr(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return users;
	}

	@Override
	public List<User> listByExpression(Criterion expr) {
		ArrayList<User> users = new ArrayList<User>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			users = (ArrayList<User>) session.createCriteria(User.class).add(expr).list();
		} catch (HibernateException e) {
			expr(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return users;
	}

	@Override
	public List<User> listByExpressionSet(Criterion expr) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	private static void expr(Exception e) {
		System.out.println(e.getMessage());
		System.err.println(Arrays.toString(e.getStackTrace()));
	}
}
