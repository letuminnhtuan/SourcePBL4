package ClassObj;

import java.io.File;
import java.io.Serializable;

public class ObjInfor implements Serializable{
	public File file;
	public Agent author;
	public String date;
	public String note;
	public ObjInfor(File file, Agent author, String date, String note) {
		this.file = file;
		this.author = new Agent(author);
		this.date = date;
		this.note = note;
	}
	@Override
	public String toString() {
		return this.file.getName();
	}
}