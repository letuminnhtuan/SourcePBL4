package XuLi;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import ClassObj.ObjInfor;
import GUI.fMain;

public class TreeSelectionNode implements TreeSelectionListener{
	public fMain f;
	public TreeSelectionNode(fMain f){
		this.f = f;
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
//		this.f.selectedNode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
		DefaultMutableTreeNode t = (DefaultMutableTreeNode) e.getPath().getLastPathComponent(); 
//		String str = e.getPath().getLastPathComponent().toString();
		ObjInfor o = (ObjInfor) t.getUserObject();
		this.f.val = o.file.getAbsolutePath();
//		str = str.substring(1, str.length() - 1);
//		this.f.result = str.split(", ");
	}

}
