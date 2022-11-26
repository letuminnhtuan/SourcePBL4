package XuLi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import GUI.fMain;

public class Upload implements ActionListener{
	public fMain f;
	public Upload(fMain f) {
		this.f = f;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		File file = new File("E:\\TestPBL4\\User\\Tuan\\111222.txt");
		try {
			file.createNewFile();
//			DefaultTreeModel model = (DefaultTreeModel)this.f.tree.getModel();
//			DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
//			model.reload(root);
			this.f.tree.setModel(this.f.DisplayTree_());
			//this.f.SetGUI();
			//this.f.DisplayTree();
			
//			DefaultTreeModel model=(DefaultTreeModel)this.f.tree.getModel();
//			model.reload(file);
			
//			TreePath[] root = this.f.tree.getSelectionPaths();
//			for(TreePath i : root) {
//				DefaultMutableTreeNode tt = (DefaultMutableTreeNode) i;
//				DefaultMutableTreeNode temp = new DefaultMutableTreeNode(file.getName());
//				
//			}
//			DefaultTreeModel model = (DefaultTreeModel) this.f.tree.getModel();
//			model.reload();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
