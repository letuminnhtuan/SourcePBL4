package ClassObj;

import java.io.File;

public class ObjTree {
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
