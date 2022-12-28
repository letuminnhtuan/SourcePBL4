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
		DefaultMutableTreeNode t = (DefaultMutableTreeNode) e.getPath().getLastPathComponent(); 
		ObjInfor o = (ObjInfor) t.getUserObject();
		this.f.val = o.file.getAbsolutePath();
		System.out.println(this.f.val);
	}

}
