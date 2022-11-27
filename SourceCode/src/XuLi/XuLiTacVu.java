package XuLi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class XuLiTacVu {
	public XuLiTacVu() {
	}

	public void createFolderInPath(String path, String nameFolder) {
		File f = new File(path + "\\" + nameFolder);
		f.delete();
		f.mkdir();
	}

	public void deleteFile(String path, String nameFile) {
		File f = new File(path + "\\" + nameFile);
		f.delete();
	}

	public void createFileInPath(String path, String nameFile) {
		try {
			File f = new File(path + "\\" + nameFile);
			f.delete();
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
}
