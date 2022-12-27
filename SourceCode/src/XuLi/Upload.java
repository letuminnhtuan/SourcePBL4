package XuLi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

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
			JFileChooser fileChoose = new JFileChooser(new File("C:"));
			fileChoose.showDialog(this.f, "Open");
			File f = fileChoose.getSelectedFile();
			ObjInfor objInfor = new ObjInfor(f, this.f.user, "now", "upload," + this.f.val);
			this.f.dataOutput.writeObject(objInfor);
			
			// Read File
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[(int) f.length()];
			fis.read(buffer);
			ObjFile objFile = new ObjFile(buffer);
			this.f.dataOutput.writeObject(objFile);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
