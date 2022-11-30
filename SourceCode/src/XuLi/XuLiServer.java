package XuLi;

import java.util.ArrayList;

import ClassObj.Agent;
import Database.DBHelper;

public class XuLiServer {
	public ArrayList<Agent> getAllAgent() {
		DBHelper db = new DBHelper();
		return db.getAllAgent();
	}
}
