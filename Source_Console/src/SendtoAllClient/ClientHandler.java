package SendtoAllClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler {
	public Socket client;
	public ObjectOutputStream objOutput;

    public ClientHandler(Socket client) {
        this.client = client;
        try {
            this.objOutput = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
