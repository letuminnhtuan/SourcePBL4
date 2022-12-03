package Client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import ClassObj.Agent;
import ClassObj.ObjInfor;
import GUI.fMain;
import XuLi.XuLiTacVu;

public class ClientThread extends Thread {
	public ObjectInputStream dataInput;
	public Agent user;
	public fMain fmain;

	public ClientThread(ObjectInputStream dataInput, Agent user, fMain f) {
		super();
		this.dataInput = dataInput;
		this.user = new Agent(user);
		this.fmain = f;
	}

	public void run() {
		try {
			while (true) {
				// Client nhận được file
				ObjInfor obj = (ObjInfor) dataInput.readObject();
				// Tạo file với đường dẫn tương ứng
				XuLiTacVu xl = new XuLiTacVu();
				xl.createFileInPath(obj.author.path, obj.file.getName());
				// Ghi file
				File file = new File(obj.author.path + "\\" + obj.file.getName());
				xl.readFile(file, obj.file);
				System.out.println(obj.note);
				DefaultTreeModel model = (DefaultTreeModel) this.fmain.tree.getModel();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
				DefaultMutableTreeNode temp = findNode(root, obj.note);
				temp.add(new DefaultMutableTreeNode(new ObjInfor(file, user, "", "")));
				model.reload();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("1");
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DefaultMutableTreeNode findNode(DefaultMutableTreeNode root, String search) {
		Enumeration<TreeNode> nodeEnumeration = root.breadthFirstEnumeration();
		while (nodeEnumeration.hasMoreElements()) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodeEnumeration.nextElement();
			ObjInfor found = (ObjInfor) node.getUserObject();
//			System.out.println(found.file.getAbsolutePath());
			if (search.equals(found.file.getAbsolutePath())) {
				return node;
			}
		}
		return null;
	}
}