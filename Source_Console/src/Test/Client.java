package Test;

import java.io.ObjectInputStream;
import java.net.Socket;

public class Client {

	public Client() throws Exception {
		Socket clientSocket = new Socket("127.0.0.1", Server.PORT);
		
		ObjectInputStream objInput = new ObjectInputStream(clientSocket.getInputStream());
		ObjectInfor data = (ObjectInfor) objInput.readObject();
		System.out.println(data.file.getPath());
		clientSocket.close();
	}
	public static void main(String[] args) throws Exception {
		new Client();
	}
}
