package Client;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JTextArea;

import ClassObj.ObjInfor;

public class ClientThread extends Thread{
	public ObjectInputStream dataInput;
	public JTextArea text;
	
	public ClientThread(ObjectInputStream dataInput, JTextArea text) {
		super();
		this.dataInput = dataInput;
		this.text = text;
	}
	public void run() {
		try {
			while(true) {
				ObjInfor obj = (ObjInfor) dataInput.readObject();
				//System.out.println(dataInput.readObject().getClass());
				text.append(obj.file.getName() + "\n");
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
