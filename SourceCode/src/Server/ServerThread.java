package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import ClassObj.ObjFile;
import ClassObj.ObjInfor;

public class ServerThread extends Thread {
	public Socket socket;
	public ObjectInputStream dataInput;
	public ObjectOutputStream dataOutput;
	Vector<ServerThread> clients = new Vector<ServerThread>();

	public ServerThread(Socket sk, Vector<ServerThread> cls) throws Exception {
		socket = sk;
		clients = cls;
		dataInput = new ObjectInputStream(socket.getInputStream());
		dataOutput = new ObjectOutputStream(socket.getOutputStream());
	}

	public void SendAll(ArrayList<Object> list) {
		for (ServerThread c : clients) {
//			System.out.println(obj.getClass());
			c.SendMess(list);
		}
	}

	public void SendMess(ArrayList<Object> list) {
		try {
			this.dataOutput.writeObject(list);
//			this.dataOutput.writeObject(objFile);
		} catch (IOException e) {

		}
	}

	public void run() {
		try {
			while (true) {
//				ObjInfor objInfor = (ObjInfor) dataInput.readObject();
//				ObjFile objFile = (ObjFile) dataInput.readObject();
				ArrayList<Object> list = (ArrayList<Object>) dataInput.readObject();
				SendAll(list);
			}
		} catch (Exception e) {

		}
	}
}
