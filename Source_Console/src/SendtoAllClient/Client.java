package SendtoAllClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public Socket client;
    public ObjectInputStream objInput;

    public Client(String hostName, int ip) {
        try {
            this.client = new Socket(hostName, ip);
            this.objInput = new ObjectInputStream(this.client.getInputStream());
            ObjectInfor data = (ObjectInfor)this.objInput.readObject();
            System.out.println(data.author);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void main(String[] args) {
		new Client("localhost", 9999);
	}
}
