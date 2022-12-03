package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	public static Connection getConnection() {
		Connection cnn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mySQL://localhost:3306/pbl4";;
			String username = "root";
			String password = "";
			cnn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnn;
	}
	public static void closeConnection(Connection cnn) {
		try {
			if(cnn != null) cnn.close();
		} catch (SQLException e) {
		}
	}
}
