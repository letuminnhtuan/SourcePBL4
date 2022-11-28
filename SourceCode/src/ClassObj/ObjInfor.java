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
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Agent getAuthor() {
		return author;
	}

	public void setAuthor(Agent author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return this.file.getName();
	}
}