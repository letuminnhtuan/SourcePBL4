package ClassObj;

import java.io.File;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ObjTree implements Serializable {
	public File f;

	public ObjTree(File f) {
		this.f = f;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.f.getName();
	}

}
