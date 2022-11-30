package XuLi;

import java.util.ArrayList;

import ClassObj.Agent;
import Database.DBHelper;

public class XuLiServer {
	public ArrayList<Agent> getAllAgent() {
		DBHelper db = new DBHelper();
		return db.getAllAgent();
	}
	public void DeleteUser(String username) {
		DBHelper db = new DBHelper();
		db.DeleteUser(username);
	}
}
