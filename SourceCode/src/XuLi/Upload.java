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
			ObjInfor obj = new ObjInfor(f, this.f.user, "now", "none");
			this.f.dataOutput.writeObject(obj);
//			this.f.tree.setModel(this.f.DisplayTree_());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		try {
////			DefaultTreeModel model = (DefaultTreeModel)this.f.tree.getModel();
////			DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
////			model.reload(root);
//			
//			this.f.tree.setModel(this.f.DisplayTree_());
//			//this.f.SetGUI();
//			//this.f.DisplayTree();
//			
////			DefaultTreeModel model=(DefaultTreeModel)this.f.tree.getModel();
////			model.reload(file);
//			
////			TreePath[] root = this.f.tree.getSelectionPaths();
////			for(TreePath i : root) {
////				DefaultMutableTreeNode tt = (DefaultMutableTreeNode) i;
////				DefaultMutableTreeNode temp = new DefaultMutableTreeNode(file.getName());
////				
////			}
////			DefaultTreeModel model = (DefaultTreeModel) this.f.tree.getModel();
////			model.reload();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//	}

}
