/*

Links:

ru:
http://www.javaportal.ru/java/tutorial/tutorialJDBC/index.html
http://javaxblog.ru/article/java-jdbc-1/

en:
http://tutorials.jenkov.com/jdbc/index.html


*/

package jdbclesson;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 */
public class JdbcLesson {
	
	SimpleCRUD model;
	int testCount = 0;

	public JdbcLesson() {
		model = new SimpleCRUD("users");
	}
	
	

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		pureJDBCExample();

		p("SimpleCRUD:");
		JdbcLesson testDB = new JdbcLesson();
		testDB.test();
		
		// типы 
		int type = java.sql.Types.DECIMAL;
		
	}
	
	/*
	
--
-- Структура таблицы `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `gender` enum('male','female') NOT NULL,
  `age` int(11) NOT NULL,
  `rating` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
	
	*/
	
	private static void pureJDBCExample(){
		String driverName = "com.mysql.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:3306/test_jdbc";
		String connectionLogin = "javadev";
		String connectionPassword = "1";

		Connection connection = null;
		
		try {
			// объявляем класс драйвера
			Class.forName(driverName);
		} catch (ClassNotFoundException ex) {
			p("Driver loading error!");
			p(ex);
			return;
		}
		
		try {
			// создаем соединение
			connection = DriverManager.getConnection(connectionUrl, connectionLogin, connectionPassword);
		} catch (SQLException ex) {
			p("Connection error!");
			p(ex);
			return;
		}
		
		try {
			// INSERTION EXAMPLE
			p("Insertion...");
			
			String sql = "INSERT INTO users (age, gender, name, rating) VALUES ( ?, ?, ?, ? );";
			
			// создаем prepared statement - параметризованный запрос, 
			// который будет возвращать (сохранять в statement) новые ключи
			PreparedStatement pstat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		//	pstat = connection.prepareStatement(sql); // без сохранения
			
			// Set values
			pstat.setInt(1, 20);
			pstat.setString(2, "male");
			pstat.setString(3, "Vasya Pupkin time-" + (new Date()).getTime());
			pstat.setDouble(4, 5.1);
			
			// выполнение запроса
			int rowCount = pstat.executeUpdate();//		executeUpdate(Statement.RETURN_GENERATED_KEYS);
			p("inserted rows: " + rowCount);
			
			// get rows count from Statement
			ResultSet keyRes = pstat.getGeneratedKeys();
			if(keyRes.next()){
				p("inserted id: " + keyRes.getInt(1));
			}
			pstat.close();
			
			// SELECTION EXAMPLE
			p("\nSelection...");
			Statement stat = connection.createStatement();
			ResultSet users = stat.executeQuery("SELECT * FROM users WHERE id > 1 LIMIT 0,3 ;");
			if(!users.isBeforeFirst()){
				p("No any result of selection!");
			}
			// get values from row
			while(users.next()){
				String info = ""
						+ "\n ID: " + users.getInt("id")
						+ "\n Name: " + users.getString("name")
						+ "\n Gender: " + new String(users.getBytes("gender"))
						+ "\n Rating: " + users.getDouble("rating")
						+ "\n";
				p(info);
			}
			stat.close();
			
			// BATCH UPDATE - пакетный запрос
			Statement batchStat = connection.createStatement();
			batchStat.addBatch("UPDATE `users` SET `name` = 'User-7' WHERE id = 7 ;");
			batchStat.addBatch("UPDATE `users` SET `name` = 'User-12_14' WHERE id = '12' OR id = 14 ;");
			int[] rowsAffected = batchStat.executeBatch(); // changed rows by each query
			System.out.println(String.format("Changed by each query: \n %s" , Arrays.toString(rowsAffected)));
			batchStat.close();
			
			// PREPARED BATCH UPDATE
			PreparedStatement prepBatchStat = connection.prepareStatement("UPDATE `users` SET `name` = ? WHERE id = ? ;");
			
			prepBatchStat.setString(1, "User-15");
			prepBatchStat.setInt(2, 15);
			prepBatchStat.addBatch();
		
			prepBatchStat.setString(1, "User-15");
			prepBatchStat.setInt(2, 15);
			prepBatchStat.addBatch();
			
			int[] rowsAffectedPrep = prepBatchStat.executeBatch();
			System.out.println(String.format("Changed by each query: \n %s" , Arrays.toString(rowsAffectedPrep)));
			// GETTING METADATA
			p("DatabaseMetaData:");
			DatabaseMetaData meta = connection.getMetaData();
			p("product: " + meta.getDatabaseProductName());
			p("prod.v.: " + meta.getDatabaseProductVersion());

			
			
		} catch (SQLException ex) {
			p("Executing Request error!");
			p(ex);
			ex.printStackTrace();
		}finally{
			try {
				if(connection != null){
					connection.close();
				}
			} catch (SQLException ex) {
				p("ну очень невезучий connection...");
			}
		}	
					
			
		// TRANSACTION
		Connection tconnection = null;	
		try{
			tconnection = DriverManager.getConnection(connectionUrl, connectionLogin, connectionPassword);

			try {
				tconnection.setAutoCommit(false); // Start transaction

			// doing something... like move money: User1 -> User2, update statistics, update user's rating

				tconnection.commit(); // commit transaction
			} catch (Exception exc){
				tconnection.rollback(); // rollback transaction
				p("...0 bad transaction");
			}
		}catch (SQLException ex){
			p("...1 bad connection in transaction");
		} finally {
			try {
				if(tconnection != null){
					tconnection.close();
				}
			} catch (SQLException ex) {
				p("...2 bad connection");
			}
		}
		
	}
	
	
	private void test(){
		try {
			if(! model.connect()){
				p("Can't create DB connection in crud model.");
				return;
			}
			
			testCreate();
			testRead(); // First read
			testUpdate();
			testDelete();
			testRead(); // Second read
			testQuerySelection();
		} catch (SQLException ex) {
			Logger.getLogger(JdbcLesson.class.getName()).log(Level.SEVERE, "{test in main}", ex);
		} finally {
			try {
				model.disconnect(); // close connection immediately
			} catch (SQLException ex) {
				p("Some terrible incident with Mysql gatekeeper happened! :)");
			}
		}
	}
	
	private void testCreate() throws SQLException{
		ResultSet idRes = model.query("SELECT id from users ORDER BY id DESC LIMIT 0,1 ;").getResult();
		int fromId = 0;
		if(idRes.next()){
			int lastId = idRes.getInt(1);
			p(lastId);
			fromId = lastId;
		}
		idRes.close();
		Random rand = new Random();
		TreeMap<String, Object> data = new TreeMap();
		for(int i=0; i < 5; ++i){
			p("\n*****************");
			String name = "User" + (fromId + i);
			String gender = rand.nextInt(10) > 5 ? "male" : "female";
			Integer age = 20 + rand.nextInt(20);
			Double rating = (double) 1 + rand.nextDouble() * 9;
			
			data.put("name", name);
			data.put("age", age);
			data.put("gender", gender);
			data.put("rating", rating);
			
			int insertedId = model.create(data);
			p("inserted id = " + insertedId);
		}
	}
	
	private void testRead() throws SQLException{
		ResultSet idRes = model.query("SELECT id from users ORDER BY id DESC LIMIT 0,1 ;").getResult();
		int fromId = 0;
		if(idRes.next()){
			int lastId = idRes.getInt(1);
			p("testRead: lastId: " + lastId);
			fromId = lastId;
		}
		ResultSet res = model.read(fromId);
		if(res.next()){
			p("id:" + res.getString(2));
			p("gender:" + res.getString("gender"));
			p("age:" + res.getString("age"));
			p("rating:" + res.getDouble(5));
		} else {
			p("testRead: no result");
		}
	}
	
	private void testUpdate() throws SQLException{
		ResultSet idRes = model.query("SELECT id from users ORDER BY id DESC LIMIT 0,1 ;").getResult();
		if(idRes.next()){
			int lastId = idRes.getInt(1);
			TreeMap<String, Object> data = new TreeMap();
			data.put("name", "Semen Obnovka " + lastId);
			data.put("age", 77);
			int updated = model.update(lastId, data);
			p("testUpdate: updated: " + updated);
		}
	}
	
	private void testDelete() throws SQLException{
		ResultSet idRes = model.query("SELECT id from users ORDER BY id DESC LIMIT 1,1 ;").getResult();
		if(idRes.next()){
			int id = idRes.getInt(1);
			int changed = model.delete(id);
			p("testDelete: deleted: " + changed);
		}
	}
	
	private void testQuerySelection() throws SQLException{
		p("\nselect multi row:\n");
		ResultSet res = model.query("SELECT * from `users` ORDER BY `id` DESC LIMIT 0,5 ;").getResult();
		StringBuilder info = new StringBuilder();
		while(res.next()){
			info.append(String.format("Id: %5d\n", res.getInt("id")))
				.append(String.format("Name: %s\n", res.getString("name")))
				.append(String.format("Gender: %s\n", res.getString("gender")))
				.append(String.format("Age: %d\n", res.getInt("age")))
				.append(String.format("Rating: %f\n", res.getDouble(5)))
				.append("------------\n\n");
		}
		p(info);
	}
	

	
	public static void p(Object x){
		System.out.println(x);
	}
}


/*

1. Load Driver: Class.forName()

2. Create connection

3. create statement (for string query)
	or create prepared statement (for query with parameters)
	or create callable statement (for stored procedures)

4. call execute() for complicated request 
	or query() for simplest request
	or executeQuery() for select
	or executeUpdate() for change db table

5. get (inserted id)or(changed row count) from statement.ResultSet (INSERT, UPDATE, DELETE)
	or get data from ResultSet

6. check next row: boolean result.next()

7. get values from row result.getInt() / .getString() / .getDouble() / etc...

8.	result.close()
	statement.close()
	connection.close() //  finally { connection.close() }
	
*/