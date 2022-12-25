package Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import ClassObj.Agent;
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
				// Client nhận được file
				Object o = dataInput.readObject();
				ObjInfor obj = (ObjInfor) o;
				String[] listStr = obj.note.split(",");
				System.out.println(obj.getFile().length());
				File file = new File("D:\\" + obj.file.getName());
				file.createNewFile();
				FileInputStream in = new FileInputStream(obj.getFile());
		        FileOutputStream out = new FileOutputStream(file);
		  
		        try {	
		            int n;
		            while ((n = in.read()) != -1) {
		                out.write(n);
		            }
		        }
		        finally {
		            if (in != null) {
		                in.close();
		            }
		            if (out != null) {
		                out.close();
		            }
		        }
		        System.out.println("File Copied");
		        
				if (listStr[0].equals("upload")) {
					// Tạo file với đường dẫn tương ứng
//					File file = new File(listStr[1] + "\\" + obj.file.getName());
//					File file = new File("D:\\" + obj.file.getName());
//					file.createNewFile();
//					-----------------------------------------------------------------------
//					-----------------------------------------------------------------------
//					-----------------------------------------------------------------------
//					System.out.println("aa");
//					System.out.println(o);
//					o = dataInput.readObject();
//					System.out.println(o);
//					System.out.println("cc");
//					System.out.println((Integer) o);
//					Integer bytesRead = (Integer) o;
//					System.out.println(bytesRead);
//					try {
//						o = dataInput.readObject();
//						System.out.println(o);
//						System.out.println("cc");
//						System.out.println((Integer) o);
//						Integer bytesRead = (Integer) o;
//						System.out.println(bytesRead);
//					} catch(Exception e) {
//						e.printStackTrace();
//					}
//					byte[] buffer = new byte[100];
//					FileOutputStream fileOutput = new FileOutputStream(file);
//					Integer bytesRead = 0;
//					do {
//						o = dataInput.readObject();
//						bytesRead = (Integer) o;
//						o = dataInput.readObject();
//						buffer = (byte[]) o;
//						fileOutput.write(buffer, 0, bytesRead);
//					} while (bytesRead == 100);
//					-----------------------------------------------------------------------
//					-----------------------------------------------------------------------
//					-----------------------------------------------------------------------
					DefaultTreeModel model = (DefaultTreeModel) this.fmain.tree.getModel();
					DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
					DefaultMutableTreeNode temp = findNode(root, listStr[1]);
					temp.add(new DefaultMutableTreeNode(new ObjInfor(file, user, "", "")));
					model.reload();
					System.out.println("ok");
				} else if (listStr[0].equals("delete")) {
//					File file = new File(listStr[1]);
//					file.delete();
					DefaultTreeModel model = (DefaultTreeModel) this.fmain.tree.getModel();
					DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
					DefaultMutableTreeNode temp = findNode(root, listStr[1]);
					model.removeNodeFromParent(temp);
					model.reload();
				} else if (listStr[0].equals("createFol")) {
					DefaultTreeModel model = (DefaultTreeModel) this.fmain.tree.getModel();
					DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
//					DefaultMutableTreeNode temp = findNode(root, obj.file.getAbsolutePath());
					DefaultMutableTreeNode temp = findNode(root, this.fmain.val);
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(
							new ObjInfor(new File(this.fmain.val + "\\" + listStr[1]), user, "", ""));
					temp.add(node);
					model.reload();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			if (search.equals(found.file.getAbsolutePath())) {
				return node;
			}
		}
		return null;
	}
}
