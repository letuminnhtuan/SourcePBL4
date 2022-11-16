package SendtoAllClient;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server extends Thread {

	public ServerSocket serverSocket;
	public List<ClientHandler> clients;

	public Server(int port) {
		try {
			this.serverSocket = new ServerSocket(port);
			System.out.println("New server initialized!");
			clients = Collections.synchronizedList(new ArrayList<ClientHandler>());
			this.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				Socket client = serverSocket.accept();
				ClientHandler newClient = new ClientHandler(client);
				clients.add(newClient);
				File f = new File("E:\\Assignment1\\html");
				ObjectInfor data = new ObjectInfor(f, "author", "date", "note");
				new SendObject(clients, data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Server(9999);
	}
}
