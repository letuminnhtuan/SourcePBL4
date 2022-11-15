import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	protected Socket client;
	protected ObjectInputStream objInput;

	public Client(String hostName, int ip) {
		try {
			this.client = new Socket(hostName, ip);
			this.objInput = new ObjectInputStream(this.client.getInputStream());
			ObjectInfor objBuffer = null;
			while ((objBuffer = (ObjectInfor) this.objInput.readObject()) != null) {
				System.out.println(objBuffer.toString());
			}
		} catch (UnknownHostException e) {
			
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			
		}
	}
	public static void main(String[] args) {
		new Client("localhost", 9999);
	}
}
