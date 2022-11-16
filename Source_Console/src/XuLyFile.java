import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class XuLyFile {

	public XuLyFile() {
	}

	public void createFolderInPath(String nameFolder) {
		
	}

	public void createFileInPath(String nameFile) {
		
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
