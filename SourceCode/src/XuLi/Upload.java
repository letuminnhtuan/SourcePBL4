package XuLi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

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
			JFileChooser fileChoose = new JFileChooser();
			fileChoose.showDialog(this.f, "Open");
			File f = fileChoose.getSelectedFile();
			ObjInfor obj = new ObjInfor(f, this.f.user, "now", "upload");
			this.f.dataOutput.writeObject(obj);
//			this.f.tree.setModel(this.f.DisplayTree_());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}