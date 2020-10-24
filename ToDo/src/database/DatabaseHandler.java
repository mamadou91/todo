package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modell.Task;
import modell.User;

/***
 * Establish the connection with the Database
 * @author ndiaye
 *
 */
public class DatabaseHandler extends Configs {

	Connection con;

	public Connection getDbConnection() throws ClassNotFoundException, SQLException {

		String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName
				+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";

		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, dbUser, dbPass);

		return con;

	}

	/***
	 * Sign up the user
	 * consist of the SQL query
	 * 
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param loc  for location
	 * @param gender
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void signUpUser(User user) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO " + Const.USERS_TABLE + "(" + Const.USERS_FIRSTNAME + "," + Const.USERS_LASTNAME + ","
				+ Const.USERS_USERNAME + "," + Const.USERS_PASSWORD + "," + Const.USERS_LOCATION + ","
				+ Const.USERS_GENDER + ")" + "VALUES(?,?,?,?,?,?)";

		PreparedStatement statement = getDbConnection().prepareStatement(sql);

		statement.setString(1, user.getFirstname());
		statement.setString(2, user.getLastname());
		statement.setString(3, user.getUsername());
		statement.setString(4, user.getPassword());
		statement.setString(5, user.getLocation());
		statement.setString(6, user.getGender());
		statement.executeUpdate();
	}

	/***
	 * Login the User
	 * 
	 * @param username
	 * @param password
	 * @return Return a resultSet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ResultSet loginUser(String username, String password) throws ClassNotFoundException, SQLException {

		ResultSet result;
		String sql = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_USERNAME + "=?" + " AND "
				+ Const.USERS_PASSWORD + "=?";

		PreparedStatement st = getDbConnection().prepareStatement(sql);
		st.setString(1, username);
		st.setString(2, password);
		result = st.executeQuery();
		return result;
	}

	/***
	 * Save the task in the Database
	 * @param task
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void saveTask(Task task) throws ClassNotFoundException, SQLException {
		
		String query = "INSERT INTO "+Const.TASK_TABLE+"("+Const.TASK_USER_ID+","+
		Const.TASK_DATECREATED+","+Const.TASK_DESCRIPTION+","+Const.TASK+
		") VALUES(?,?,?,? )";
		
		PreparedStatement st = getDbConnection().prepareStatement(query);
		st.setInt(1, task.getUserid());
		st.setDate(2, task.getDateCreated());
		st.setString(3, task.getDescription());
		st.setString(4, task.getTask());
		
		st.executeUpdate();
	}
	
	
	/***
	 * verify if the user has tasks
	 * @param userid
	 * @return return a ResultSet of the query
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ResultSet getUsersTask(int userid) throws ClassNotFoundException, SQLException {
		
		String query = "SELECT * FROM " + Const.TASK_TABLE
				+ "  WHERE "+Const.TASK_USER_ID+ " = ?";

		PreparedStatement st = getDbConnection().prepareStatement(query);
		st.setInt(1, userid);
		ResultSet rset = st.executeQuery();
		
		return rset;

	}
	
	
	
	/***
	 * Delete a user task
	 * @param idtask
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void deteteUserTask(int idtask) throws ClassNotFoundException, SQLException {
		
		String query = "DELETE FROM "+Const.TASK_TABLE +" WHERE "+
		Const.TASK_ID+ " = ?";
		
		PreparedStatement st = getDbConnection().prepareStatement(query);
		st.setInt(1, idtask);
		st.executeUpdate();
		
		
		
	}

}
