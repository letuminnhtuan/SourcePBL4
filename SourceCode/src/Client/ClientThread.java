package Client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JTextArea;

import ClassObj.Agent;
import ClassObj.ObjInfor;
import GUI.fMain;
import XuLi.XuLiTacVu;

public class ClientThread extends Thread{
	public ObjectInputStream dataInput;
	public Agent user;
	public fMain fmain;
	
	public ClientThread(ObjectInputStream dataInput, Agent user, fMain f) {
		super();
		this.dataInput = dataInput;
		this.user = new Agent(user);
		this.fmain = f;
	}
	public void run() {
		try {
			while(true) {
				// Client nhận được file 
				ObjInfor obj = (ObjInfor) dataInput.readObject();
				
				// Tạo file với đường dẫn tương ứng
				XuLiTacVu xl = new XuLiTacVu();
				xl.createFileInPath(this.user.path, obj.file.getName());
				// Ghi file
				File f = new File(this.user.path + "\\" + obj.file.getName());
				xl.readFile(f, obj.file);
				this.fmain.tree.setModel(this.fmain.DisplayTree_());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("1");
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}