package XuLi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


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
			FileInputStream fi = new FileInputStream(srcFile);
			FileOutputStream fo = new FileOutputStream(desFile);
			int byteRead = -1;
            while ((byteRead = fi.read()) != -1) {
            	fo.write(byteRead);
            }
			fi.close();
			fo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
