package Test;

import java.io.*;
import java.net.*;

public class Server {
	public static final int PORT = 9999;
	public Server() throws Exception {
		ServerSocket serverSocket = new ServerSocket(PORT);
		Socket socket = serverSocket.accept();
		
		ObjectOutputStream objOutput = new ObjectOutputStream(socket.getOutputStream());

		File f = new File("E:\\Assignment1\\html");
		ObjectInfor data = new ObjectInfor(f, "author", "date", "note");
		objOutput.writeObject(data);
		
		objOutput.close();
		serverSocket.close();
	}
	public static void main(String[] args) throws Exception {
		new Server();
	}
}
