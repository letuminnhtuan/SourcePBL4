package Database;

import java.sql.*;
import java.util.ArrayList;

import ClassObj.Agent;

public class DBHelper {
	public ArrayList<Agent> GetAllAgent() {
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
	public static void main(String[] args) {
		DBHelper db = new DBHelper();
		for(Agent i : db.GetAllAgent()) {
			System.out.println(i.name);
		}
	}
	
}
