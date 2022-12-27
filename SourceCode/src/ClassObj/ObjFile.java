package ClassObj;

import java.io.Serializable;
			
@SuppressWarnings("serial")
public class ObjFile implements Serializable{
	  public byte[] data;
	  public ObjFile(byte[] bytes) {
		  data = new byte[bytes.length];
		  for(int i = 0; i < bytes.length; i++) {
			  data[i] = bytes[i];
		  }
	  }
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
}
