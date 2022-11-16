package SendtoAllClient;
import java.io.File;
import java.io.Serializable;

public class ObjectInfor implements Serializable{
	public File file;
	public String author;
	public String date;
	public String note;
	public ObjectInfor(File file, String author, String date, String note) {
		this.file = file;
		this.author = author;
		this.date = date;
		this.note = note;
	}
	@Override
	public String toString() {
		return this.author;
	}
}
