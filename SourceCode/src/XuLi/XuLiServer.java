package XuLi;

import java.util.ArrayList;

import ClassObj.Agent;
import Database.DBHelper;

public class XuLiServer {
	public ArrayList<Agent> getAllAgent() {
		DBHelper db = new DBHelper();
		return db.getAllAgent();
	}
	public void AddUser(Agent user) {
		DBHelper db = new DBHelper();
		db.AddUser(user);
	}
	public void UpdateUser(Agent user) {
		DBHelper db = new DBHelper();
		db.UpdateUser(user);
	}
	public void DeleteUser(String username) {
		DBHelper db = new DBHelper();
		db.DeleteUser(username);
	}
	public Agent getAgentByUsername(String username) {
		DBHelper db = new DBHelper();
		return db.getAgentByUsername(username);
	}
}
