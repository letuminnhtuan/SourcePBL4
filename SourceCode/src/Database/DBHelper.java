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
			while(rs.next()) {
				Agent temp = new Agent(rs.getString("name"), rs.getString("username"), 
						rs.getString("password"), rs.getString("host"), rs.getInt("port"), rs.getString("path"), rs.getString("role"));
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
		for(Agent i : getAllAgent()) {
			if(i.username.equals(username)) {
				a = new Agent(i);
				break;
			}
		}
		return a;
	}
	public static void main(String[] args) {
		DBHelper db = new DBHelper();
		for(Agent i : db.getAllAgent()) {
			System.out.println(i.name);
		}
	}
	
}
