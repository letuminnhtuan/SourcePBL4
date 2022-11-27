package XuLi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


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
		try (
		        InputStream inputStream = new BufferedInputStream(new FileInputStream(srcFile));
		        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(desFile));
		) {
		    byte[] buffer = new byte[8192];
		    int bytesRead = -1;
		 
		    while ((bytesRead = inputStream.read(buffer)) != -1) {
		        outputStream.write(buffer, 0, bytesRead);
		    }
		} catch (IOException ex) {
		        ex.printStackTrace();
		}
	}
}
