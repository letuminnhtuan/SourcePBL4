package XuLi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import ClassObj.ObjInfor;
import GUI.fMain;

public class Delete implements ActionListener {
	public fMain f;

	public Delete(fMain f) {
		this.f = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			DefaultMutableTreeNode select = this.f.selectedNode;
			if(this.f.val != null) {
				if(checkDelete()) {
					int result = JOptionPane.showConfirmDialog(null, "Sure? You want to delete?", "Delete",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // 0 = yes , 1= no
					if (result == 0) {
						ObjInfor obj = new ObjInfor(new File(this.f.val), this.f.user, "now", "delete," + this.f.val);
						ArrayList<Object> list = new ArrayList<>();
						list.add(obj);
						this.f.dataOutput.writeObject(list);
					}
				}
				else {
					JOptionPane.showMessageDialog(f, "Cannot delete this file !!!");
				}
			}
			else {
				JOptionPane.showMessageDialog(f, "Select file to delete!!!");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public boolean checkDelete() {
		for(String str : this.f.val.split("\\\\")) {
			if(str.equals(this.f.user.name)) {
				return true;
			}
		}
		return false;
	}
}