
package springdao;

import entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import project.dao.UserDAO;

/**
 * 
 * examples: http://www.mkyong.com/spring/spring-jdbctemplate-jdbcdaosupport-examples/
 *
 * @author Less
 */
public class SpringDAO {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		testTpl();
		testDao();
		testNamedDao();
	}
	
	private static void testTpl(){
		testBean("userDataTpl");
	}
	
	private static void testDao(){
		testBean("userDataDao");
	}
	
	private static void testNamedDao(){
		testBean("namedUserDao");
	}

	public static void testBean(String beanId){
		// users: id, name, login, password, email
		ApplicationContext context = new FileSystemXmlApplicationContext("data-src.xml");
		UserDAO udata = context.getBean(beanId, UserDAO.class);

		p("Use bean id: " + beanId);
		// get name of first user
		String fname = udata.getNameOfFirstUser();
		p(String.format("Name of first: %s", fname));

		// get name by id
		p(udata.getNameById(1));

		// check User by Login and Pass
		int checkedCount = udata.checkByLogin("megavaz", "1");
		p(String.format("Found [%d] users by login and password", checkedCount));

		// add User
		if(false){
			User nuser = new User("Olya", "hit-girl", "qwerty", "petrenko@m.com");
			int addedCount = udata.add(nuser);
						p("added " + addedCount + " users");
		}

		// get User by id
		User user = udata.getById(1);
		p("User by Id: "+user.getName() + " " + user.getEmail());

	}

	public static void jdbcDaoSupport(){
		// http://www.studytrails.com/frameworks/spring/spring-jdbc-dao-support.jsp
	}

	public static void p(Object x){
		System.out.println(x);
	}

}


/*


--
-- База данных: `test_spring_jdbc`
--

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `login` varchar(64) NOT NULL,
  `password` char(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `name`, `login`, `password`, `email`) VALUES
(1, 'Vasya Puplin', 'megavaz', '1', 'mega@mail.ru');

*/