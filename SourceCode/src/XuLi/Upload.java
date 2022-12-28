package XuLi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ClassObj.ObjFile;
import ClassObj.ObjInfor;
import GUI.fMain;

public class Upload implements ActionListener {
	public fMain f;

	public Upload(fMain f) {
		this.f = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			if(this.f.val != null) {
				if(!this.f.val.split("\\\\")[3].equals(this.f.user.path.split("\\\\")[6])){
					JOptionPane.showMessageDialog(f, "Cannot upload file in this folder !!!");
				}
				else {
					JFileChooser fileChoose = new JFileChooser(new File("C:"));
					fileChoose.showDialog(this.f, "Open");
					File f = fileChoose.getSelectedFile();
					if(f != null) {
						ObjInfor objInfor = new ObjInfor(f, this.f.user, "now", "upload," + this.f.val);
						// Read File
						FileInputStream fis = new FileInputStream(f);
						byte[] buffer = new byte[(int) f.length()];
						fis.read(buffer);
						ObjFile objFile = new ObjFile(buffer);
						fis.close();
						// Send Object
						ArrayList<Object> l = new ArrayList<>();
						l.add(objInfor);
						l.add(objFile);
						this.f.dataOutput.writeObject(l);
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	

}
