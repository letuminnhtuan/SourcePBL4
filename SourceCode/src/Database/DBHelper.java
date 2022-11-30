package Database;

import java.sql.*;
import java.util.ArrayList;

import ClassObj.Agent;

public class DBHelper {
	public ArrayList<Agent> getAllAgent() {
		ArrayList<Agent> list = new ArrayList<Agent>();
		Connection cnn = null;
		try {
			cnn = JDBC.getConnection();
			Statement stm = cnn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM `client`");
			while (rs.next()) {
				Agent temp = new Agent(rs.getString("name"), rs.getString("username"), rs.getString("password"),
						rs.getString("host"), rs.getInt("port"), rs.getString("path"), rs.getString("role"));
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Agent getAgentByUsername(String username) {
		Agent a = null;
		for (Agent i : getAllAgent()) {
			if (i.username.equals(username)) {
				a = new Agent(i);
				break;
			}
		}
		return a;
	}

	public boolean checkLogin(String username, String password) {
		if (getAgentByUsername(username) == null) {
			return false;
		} else if (getAgentByUsername(username).password.equals(password)) {
			return true;
		}
		return false;
	}

	public void AddUser(Agent user) {
		try {
			String query = "INSERT INTO `client` (`name`, `username`, `password`, `host`, `port`, `path`, `role`) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			Connection cnn = JDBC.getConnection();
			PreparedStatement stm = cnn.prepareStatement(query);
			stm.setString(1, user.name);
			stm.setString(2, user.username);
			stm.setString(3, user.password);
			stm.setString(4, user.host);
			stm.setString(5, user.port + "");
			stm.setString(6, user.path);
			stm.setString(7, user.role);
			stm.execute();
			JDBC.closeConnection(cnn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void UpdateUser(Agent user) {
		try {
			String query = "UPDATE `client` SET `name` = ?, `password` = ?, "
					+ "`host` = ?, `port` = ?, `path` = ?, `role` = ? WHERE `client`.`username` = ?;";
			Connection cnn = JDBC.getConnection();
			PreparedStatement stm = cnn.prepareStatement(query);
			stm.setString(1, user.name);
			stm.setString(2, user.password);
			stm.setString(3, user.host);
			stm.setString(4, user.port + "");
			stm.setString(5, user.path);
			stm.setString(6, user.role);
			stm.setString(7, user.username);
			stm.execute();
			JDBC.closeConnection(cnn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void DeleteUser(String username) {
		try {
			String query = "DELETE FROM client WHERE `client`.`username` = ?";
			Connection cnn = JDBC.getConnection();
			PreparedStatement stm = cnn.prepareStatement(query);
			stm.setString(1, username);
			stm.execute();
			JDBC.closeConnection(cnn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
