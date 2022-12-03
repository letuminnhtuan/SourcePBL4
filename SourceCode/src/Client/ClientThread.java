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
				////////////////////////
				String[] listStr = obj.note.split(",");
				if (listStr[0].equals("upload")) {
					// Tạo file với đường dẫn tương ứng
					XuLiTacVu xl = new XuLiTacVu();
					xl.createFileInPath(obj.author.path, obj.file.getName());
					// Ghi file
					File file = new File(obj.author.path + "\\" + obj.file.getName());
					xl.readFile(file, obj.file);
					DefaultTreeModel model = (DefaultTreeModel) this.fmain.tree.getModel();
					DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
					DefaultMutableTreeNode temp = findNode(root, listStr[1]);
					temp.add(new DefaultMutableTreeNode(new ObjInfor(file, user, "", "")));
					model.reload();
				} else if (listStr[0].equals("delete")) {
					// Tạo file với đường dẫn tương ứng
					XuLiTacVu xl = new XuLiTacVu();
					xl.createFileInPath(obj.author.path, obj.file.getName());
					// Ghi file
					File file = new File(obj.author.path + "\\" + obj.file.getName());
					xl.readFile(file, obj.file);
					DefaultTreeModel model = (DefaultTreeModel) this.fmain.tree.getModel();
					DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
					DefaultMutableTreeNode temp = findNode(root, listStr[1]);
					model.removeNodeFromParent(temp);
					model.reload();
				} else if (listStr[0].equals("createFol")) {
					DefaultTreeModel model = (DefaultTreeModel) this.fmain.tree.getModel();
					DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
					DefaultMutableTreeNode temp = findNode(root, obj.file.getAbsolutePath());
					temp.add(new DefaultMutableTreeNode(
							new ObjInfor(new File(obj.file.getAbsoluteFile() + "\\" + listStr[1]), user, "", "")));
					model.reload();
				}
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