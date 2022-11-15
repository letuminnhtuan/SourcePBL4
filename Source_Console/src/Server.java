import java.io.File;
import java.net.*;
import java.util.*;

public class Server extends Thread {
	private ServerSocket server;
	protected List<ServerThread> clients;
	public ObjectInfor obj;

	public Server(int port) {
		try {
			this.server = new ServerSocket(port);
			System.out.println("New server initialized!");
			clients = Collections.synchronizedList(new ArrayList<ServerThread>());
			File f = new File("E:\\\\Assignment1\\\\html");
			this.obj = new ObjectInfor(f, "admin", new Date().toString(), "none");
			this.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				Socket client = server.accept();
				ServerThread newClient = new ServerThread(client);
				clients.add(newClient);
				new SendMessage(clients, obj);
			} catch (Exception e) {
				System.out.println("Server: Error!");
			}
		}
	}

	public static void main(String[] args) {
		new Server(9999);
	}
}
