import java.util.*;

public class SendMessage extends Thread {
	public List<ServerThread> clients;
	public ObjectInfor objInfor;

	public SendMessage(List<ServerThread> clients, ObjectInfor obj) {
		this.clients = clients;
		this.objInfor = obj;
		this.start();
	}

	public void run() {
		if (clients.size() == 1) {
			System.out.println("Enter message:");
		}
		try {
			if (clients.size() > 0) {
				for (ServerThread client : clients) {
					client.objOutput.writeObject(objInfor);
					client.objOutput.flush();
					Thread.currentThread();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
