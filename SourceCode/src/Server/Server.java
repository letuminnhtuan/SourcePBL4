package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server{
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

	public static void main(String[] args) throws IOException {
		try {
			new Server(9090);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
//		ServerSocket sk = new ServerSocket(9988);
//		Socket ske = sk.accept();
//		System.out.println(ske);
	}
}
