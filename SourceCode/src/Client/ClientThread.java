package Client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import ClassObj.Agent;
import ClassObj.ObjFile;
import ClassObj.ObjInfor;
import GUI.fMain;

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
//				ObjInfor objInfor = (ObjInfor) dataInput.readObject();
				ArrayList<Object> list = (ArrayList<Object>) dataInput.readObject();
				ObjInfor objInfor = (ObjInfor) list.get(0);
				String[] listStr = objInfor.note.split(",");
				if (listStr[0].equals("upload")) {
					ObjFile objFile = (ObjFile) list.get(1);
					File file = new File(listStr[1] + "\\" + objInfor.file.getName());
					DefaultTreeModel model = (DefaultTreeModel) this.fmain.tree.getModel();
					DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
					DefaultMutableTreeNode temp = findNode(root, listStr[1]);
					temp.add(new DefaultMutableTreeNode(new ObjInfor(file, user, "", "")));
					try (FileOutputStream fos = new FileOutputStream(file)) {
						fos.write(objFile.data);
					}
					model.reload();
				} else if (listStr[0].equals("delete")) {
					System.out.println("del");
					File file = new File(listStr[1]);
					file.delete();
					DefaultTreeModel model = (DefaultTreeModel) this.fmain.tree.getModel();
					DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
					DefaultMutableTreeNode temp = findNode(root, listStr[1]);
					model.removeNodeFromParent(temp);
					model.reload();
				} else if (listStr[0].equals("createFol")) {
					File file = new File(listStr[1]);
					file.mkdir();
					DefaultTreeModel model = (DefaultTreeModel) this.fmain.tree.getModel();
					DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
					DefaultMutableTreeNode temp = findNode(root, file.getParent());
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(
							new ObjInfor(new File(this.fmain.val + "\\" + listStr[1]), user, "", ""));
					temp.add(node);
					model.reload();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DefaultMutableTreeNode findNode(DefaultMutableTreeNode root, String search) {
		Enumeration<TreeNode> nodeEnumeration = root.breadthFirstEnumeration();
		while (nodeEnumeration.hasMoreElements()) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodeEnumeration.nextElement();
			ObjInfor found = (ObjInfor) node.getUserObject();
			if (search.equals(found.file.getAbsolutePath())) {
				return node;
			}
		}
		return null;
	}
}
