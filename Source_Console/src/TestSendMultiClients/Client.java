package TestSendMultiClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public Socket client;
    public BufferedReader in;

    public Client(String hostName, int ip) {
        try {
            this.client = new Socket(hostName, ip);
            this.in = new BufferedReader(new InputStreamReader(
                    this.client.getInputStream()));
            String buffer = null;
            while ((buffer = in.readLine()) != null) {
                System.out.println(buffer);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
		new Client("localhost", 9999);
	}
}
