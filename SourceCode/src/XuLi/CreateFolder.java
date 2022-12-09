package XuLi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import ClassObj.ObjInfor;
import GUI.fMain;

public class CreateFolder implements ActionListener {
	public fMain f;

	public CreateFolder(fMain f) {
		this.f = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String name = JOptionPane.showInputDialog(null, "Enter Name");
			if ((name.contains(" ") || name.contains(""))) {
				File theDir = new File(this.f.user.getPath() + "\\" + name);
//				System.out.println(theDir.getAbsolutePath());
				if (!theDir.exists()) {
					theDir.mkdirs();
//					new CheckFileEdit(theDir.getAbsolutePath(), this.f).start();
					ObjInfor obj = new ObjInfor(new File(this.f.user.getPath()), this.f.user, "now",
							"createFol," + name);
					this.f.dataOutput.writeObject(obj);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
