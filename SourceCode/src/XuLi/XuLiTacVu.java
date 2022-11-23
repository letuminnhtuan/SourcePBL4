package XuLi;

import java.util.ArrayList;

import ClassObj.Agent;
import Database.DBHelper;

public class XuLiTacVu {
	public XuLiTacVu() {
	}

	public void CreateFile(String fileName) {
		DBHelper db = new DBHelper();
		XuLiFile xl = new XuLiFile();
		ArrayList<Agent> list = db.GetAllAgent();
		for (Agent i : list) {
			xl.createFileInPath(i.path, fileName);
		}
	}
	public static void main(String[] args) {
		XuLiTacVu xl = new  XuLiTacVu();
		xl.CreateFile("test.txt");
	}
}
