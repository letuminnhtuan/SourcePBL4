package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ClassObj.ObjInfor;

public class testServer extends Thread {
	public static final int PORT = 3332;
	public static final int BUFFER_SIZE = 100;

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);

			while (true) {
				Socket s = serverSocket.accept();
				saveFile(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveFile(Socket socket) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		FileOutputStream fos = null;
		byte[] buffer = new byte[BUFFER_SIZE];

		// 1. Read file name.
		Object o = ois.readObject();
		ObjInfor obj = (ObjInfor) o;
		File f = new File("D:\\" + obj.file.getName());
		System.out.println("asd");
		fos = new FileOutputStream(f);
		Integer bytesRead = 0;
		do {
			o = ois.readObject();

			bytesRead = (Integer) o;
			o = ois.readObject();
			buffer = (byte[]) o;
			fos.write(buffer, 0, bytesRead);

		} while (bytesRead == BUFFER_SIZE);

		System.out.println("File transfer success");

		fos.close();
	}

	public static void throwException(String message) throws Exception {
		throw new Exception(message);
	}

	public static void main(String[] args) {
		new testServer().start();
	}
}