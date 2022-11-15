import java.io.File;
import java.io.IOException;

public class XuLyFile {
	public File file;

	public XuLyFile(String path) {
		file = new File(path);
		if (!this.file.exists()) {
			this.file.mkdir();
			System.out.println("Successful!");
		} else {
			System.out.println("Fail");
		}
	}

	public void createFolderInPath(String nameFolder) {
		try {
			String path = this.file.getPath();
			System.out.println(path);
			File create = new File(path + "\\" + nameFolder);
			create.mkdir();
		} catch (Exception e) {

		}
	}

	public void createFileInPath(String nameFile) {
		try {
			String path = this.file.getPath();
			File create = new File(path + "\\" + nameFile);
			if (create.createNewFile()) {
				System.out.println("Successful!");
			} else {
				System.out.println("Fail");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	public void showFileChild() {
		for(File i : this.file.listFiles()) {
			System.out.println(i.getAbsolutePath());
		}
	}

	public void showDetailofFile() {
		// Recursive
		
	}

//	public static void main(String[] args) {
//		XuLyFile xl = new XuLyFile("E:\\Assignment1\\html");
//		xl.showFileChild();
//	}
}
