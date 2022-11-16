package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public Client() throws Exception {
		Socket clientSocket = new Socket("127.0.0.1", Server.PORT);
		// Read source file
		ObjectInputStream objInput = new ObjectInputStream(clientSocket.getInputStream());
		ObjectInfor data = (ObjectInfor) objInput.readObject();
		// desFile: temp
		// srcFile: data.file[]
		if(data.file.isDirectory()){
			for (File i : data.file.listFiles()) {
				if(i.isFile()) {
					File temp = new File("E:\\TestPBL4\\Client\\Tuan\\" + i.getName());
					if(!temp.exists()) {
						temp.createNewFile();
					}
					readFile(temp, i);
				}
			}
		}
		clientSocket.close();
	}

	public void readFile(File desFile, File srcFile) {
		try {
			Scanner sc = new Scanner(srcFile);
			FileWriter fw = new FileWriter(desFile);
			while (sc.hasNextLine()) {
				fw.write(sc.nextLine());
			}
			sc.close();
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		new Client();
	}
}
