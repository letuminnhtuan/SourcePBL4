package XuLi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

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
			if (checkCreate()) {
				String name = JOptionPane.showInputDialog(null, "Enter Name");
				if ((name.contains(" ") || name.contains(""))) {
					File theDir = new File(this.f.val + "\\" + name);
					ObjInfor obj = new ObjInfor(new File(this.f.val), this.f.user, "now",
							"createFol," + theDir.getAbsolutePath());
					System.out.println("--" + theDir.getAbsolutePath());
					ArrayList<Object> list = new ArrayList<>();
					list.add(obj);
					this.f.dataOutput.writeObject(list);
				}
			} else {
				JOptionPane.showMessageDialog(f, "Cannot create folder this file !!!");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public boolean checkCreate() {
		for (String str : this.f.val.split("\\\\")) {
			if (str.equals(this.f.user.name)) {
				return true;
			}
		}
		return false;
	}

}
