import java.io.*;
import java.net.Socket;

public class ServerThread {
	protected Socket client;
    protected ObjectOutputStream objOutput;

    public ServerThread(Socket client) {
        this.client = client;
        try {
            this.objOutput = new ObjectOutputStream(this.client.getOutputStream());
        } catch (IOException e) {
            System.out.println("ServerThread: Error");
        }
    }
}
