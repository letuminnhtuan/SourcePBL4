package SendtoAllClient;

import java.io.ObjectInputStream;
import java.util.List;

public class SendObject extends Thread {
	public List<ClientHandler> clients;
	public ObjectInfor objReceive;
	public ObjectInputStream objInput;

	public SendObject(List<ClientHandler> clients, ObjectInfor objReceive) {
		this.clients = clients;
		this.objReceive = objReceive;
		this.start();
	}

	public void run() {
		System.out.println("New Communication Thread Started");
		if (clients.size() == 1) {
			System.out.println("Enter message:");
		}
		try {
			if (clients.size() > 0) {
				if (objReceive != null) {
					for (ClientHandler client : clients) {
						client.objOutput.writeObject(objReceive);
						client.objOutput.flush();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
