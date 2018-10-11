
package classes.dao;

import classes.orm.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Less
 */
public class UserHbDao implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory val){
		sessionFactory = val;
	}
	
	Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void add(User user){
		Session session = getSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(user);
		transaction.commit();
	//	session.close();
	}

}
