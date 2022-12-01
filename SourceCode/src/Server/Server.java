package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server extends Thread{
	public ServerSocket serverSocket;
	Vector<ServerThread> clients = new Vector<ServerThread>();

	public Server(int port) throws Exception {
		serverSocket = new ServerSocket(port);
		System.out.println("Server is running........");
		while (true) {
			Socket socket = serverSocket.accept();
			ServerThread thr = new ServerThread(socket, clients);
			clients.add(thr);
			thr.start();
		}
	}

	
}
