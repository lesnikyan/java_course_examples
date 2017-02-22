
package jdbclesson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdbclesson.JdbcLesson.p;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Less
 */
public class SimpleCRUD {
	
	ResultSet lastResult = null;
	String lastSql = "";
	
	String driverName = "com.mysql.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://localhost:3306/test_jdbc";
	String connectionLogin = "javadev";
	String connectionPassword = "1";
	
	Connection connection = null;
	PreparedStatement stat = null;
	boolean updateRequest = false;
	
	String table = "";
	
	ArrayList<String> fieldSet;
	
	{
		fieldSet = new ArrayList<>(Arrays.asList(new String[]{
			"id", "name", "gender", "age", "rating"
		}));
	}

	public SimpleCRUD(String table) {
		this.table = table;
	}
	
	public boolean loadDriver(){
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException ex) {
			p("loadDriver: ClassNotFoundException");
			return false;
		}
		return true;
	}
	
	public boolean connect(){
		if(!loadDriver()){
			return false;
		}
		try {
			connection = DriverManager.getConnection(connectionUrl, connectionLogin, connectionPassword);
		} catch (SQLException ex) {
			Logger.getLogger(SimpleCRUD.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		return true;
	}
	
	public void disconnect() throws SQLException{
		if(stat != null)
			stat.close();
		if(connection != null)
			connection.close();
	}
	
	public PreparedStatement getStatement(){
		return stat;
	}
	
	public ResultSet getResult(){
		return lastResult;
	}
	
	public int create(Map<String, Object> data) throws SQLException{
		// prepare data
		updateRequest = true;
		ArrayList<String> fields = new ArrayList<>();
		ArrayList<String> values = new ArrayList<>();
		for(String field: data.keySet()){
			if(! fieldSet.contains(field)){
				continue;
			}
			fields.add("`"+field+"`");
			values.add(data.get(field).toString());
		}
		
		String SQLfields = StringUtils.join(fields, ",");
		String SQLvaluesMask = StringUtils.repeat("?,", fields.size() - 1) + "?";
		lastSql = String.format("INSERT INTO `%s`(%s) VALUES (%s);", table, SQLfields ,SQLvaluesMask);
		//p("Do insert!");
		query(lastSql, values);
		ResultSet statres = stat.getGeneratedKeys();
		if(statres.next()){
			return statres.getInt(1);
		}
		return 0;
	}
	
	public ResultSet read(int id){
		updateRequest = false;
		lastSql = String.format("SELECT * FROM `%s` WHERE `id` = ?;", table);
		query(lastSql, new String[]{Integer.toString(id)});
		return lastResult;
	}
	
	public int update(int id, Map<String, Object> data) throws SQLException{
		if(data.isEmpty())
			return 0;
		// prepare data
		updateRequest = true;
		ArrayList<String> fields = new ArrayList<>();
		ArrayList<String> values = new ArrayList<>();
		for(String field: data.keySet()){
			if(! fieldSet.contains(field)){
				continue;
			}
			fields.add("`"+field+"` = ?");
			values.add(data.get(field).toString());
		}
		values.add(Integer.toString(id));
		
		String SQLfields = StringUtils.join(fields, ",");
		lastSql = String.format("UPDATE `%s` SET %s WHERE `id` = ? ;", table, SQLfields, id);
		query(lastSql, values);
		return stat.getUpdateCount();
	}
	
	public int delete(int id) throws SQLException  {
		updateRequest = true;
		lastSql = String.format("DELETE FROM `%s` WHERE `id` = ?;", table);
		query(lastSql, new ArrayList<String>(Arrays.asList(Integer.toString(id))));
		return stat.getUpdateCount();
	}
	
	public SimpleCRUD query(String sql){
		String [] v = null;
		return query(sql, v);
	}
	
	public SimpleCRUD query(String sql, ArrayList<String> values){
		String[] val = {};
		if(values != null && values.size() > 0){
			val = values.toArray(new String[values.size()]);
		}
		return query(sql, val);
	}
	
	public SimpleCRUD query(String sql, String[] values){
		String debLen = (values == null) ? "null" : Integer.toString(values.length);
		//p(Arrays.toString(values) + " len: " + debLen);
		try {
			if(stat != null){
				stat.close();
			}
			if(updateRequest){
				stat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			} else {
				stat = connection.prepareStatement(sql);
			}
			if(values != null){
				int paramLen = values.length;
				for(int i=0; i < paramLen; ++i){
		//			p("----" + String.format("stat.setString(%s)", values[i]));
					stat.setString(i+1, values[i]);
				}
			}
			if(updateRequest){
				stat.executeUpdate();
			} else {
				lastResult = stat.executeQuery();
			}
		} catch (SQLException ex) {
			p(ex);
		}
		updateRequest = false;
		return this;
	}
	
}
