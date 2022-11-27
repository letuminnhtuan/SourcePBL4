package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

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

	public void SendAll(ObjInfor obj) {
		for (ServerThread c : clients) {
//			System.out.println(obj.getClass());
			c.SendMess(obj);
		}
	}

	public void SendMess(ObjInfor obj) {
		try {
			this.dataOutput.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			while (true) {
				ObjInfor obj = (ObjInfor) dataInput.readObject();
				SendAll(obj);
			}
		} catch (Exception e) {
			
		}
	}
}
