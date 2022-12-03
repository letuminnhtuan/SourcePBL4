package XuLi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
//			DefaultTreeModel model = (DefaultTreeModel) this.f.tree.getModel();
			DefaultMutableTreeNode select = this.f.selectedNode;
			int result = JOptionPane.showConfirmDialog(null, "Sure? You want to exit?", "Swing Tester",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // 0 = yes , 1= no
			if (result == 0) {
				ObjInfor o = (ObjInfor) select.getUserObject();
//				o.getFile().delete();
//				model.removeNodeFromParent(select);
//				model.reload();
				ObjInfor obj = new ObjInfor(o.getFile(), this.f.user, "now", "delete," + 
						((ObjInfor) this.f.selectedNode.getUserObject()).file.getAbsolutePath());
				this.f.dataOutput.writeObject(obj);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}