package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import ClassObj.Agent;
import ClassObj.ObjInfor;
import Client.ClientThread;
import Database.DBHelper;
import XuLi.Upload;

@SuppressWarnings("serial")
public class fMain extends JFrame {
	public DefaultMutableTreeNode root = null;
	public JTree tree;
	public JPanel pnlRender;
	public JButton btnUpload;
	public JButton btnDelete;
	public JButton btnCreFol;
	public Container con;
	public JPanel pnTree;
	public Socket socket;
	public ObjectInputStream dataInput;
	public ObjectOutputStream dataOutput;
	public Agent user;
	public DefaultMutableTreeNode	selectedNode= null;
	public String Folder_File;
	public String[] result ;
	public fMain(String host, int port, String username) throws Exception {
		DBHelper db = new DBHelper();
		this.user = db.getAgentByUsername(username);
		this.user = new Agent(user);
		socket = new Socket(host, port);
		dataOutput = new ObjectOutputStream(socket.getOutputStream());
		dataInput = new ObjectInputStream(socket.getInputStream());
		new ClientThread(dataInput, this.user, this).start();
		SetGUI();
		this.setSize(new Dimension(1000, 700));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void SetGUI() {
		this.setTitle(this.user.name);
		con = getContentPane();
		con.setLayout(new BorderLayout());
		DisplayTree();
		JPanel pnlRight = new JPanel();
		pnlRight.setLayout(new BorderLayout(2, 2));
		this.pnlRender = new JPanel();
		pnlRight.add(this.pnlRender, BorderLayout.CENTER);
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(Color.WHITE);
		pnlMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 5));
		btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new Upload(this));
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				DefaultMutableTreeNode select = selectedNode;
				int result = JOptionPane.showConfirmDialog(null,"Sure? You want to exit?", "Swing Tester",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);	// 0 = yes ,  1= no
				if(result ==0) {
					ObjInfor o = (ObjInfor) select.getUserObject();
					o.getFile().delete();
					model.removeNodeFromParent(select);
					model.reload();
				}
			}
		});
		btnCreFol = new JButton("Create Folder");
		btnCreFol.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name=JOptionPane.showInputDialog(null,"Enter Name");      
				if((name.contains(" ") || name.contains(""))) {
					System.out.print(name);
					File theDir = new File(user.getPath()+"\\"+name);
					if (!theDir.exists()){
					    theDir.mkdirs();
					}
				}
			}
		});
		pnlMenu.add(btnUpload);
		pnlMenu.add(btnDelete);
		pnlMenu.add(btnCreFol);
		pnlRight.add(pnlMenu, BorderLayout.NORTH);

		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnTree, pnlRight);
		sp.setOneTouchExpandable(true);

		con.add(sp, BorderLayout.CENTER);
	}

	public void DisplayTree() {
		pnTree = new JPanel();
		pnTree.setLayout(new BorderLayout());
		root = new DefaultMutableTreeNode("Home");
		tree = new JTree(root);
//<<<<<<< HEAD
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				selectedNode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				String str = e.getPath().toString();
				str = str.substring(1, str.length()-1);
				result = str.split(", ");
//				if (selectedNode.isLeaf()) {
//					ObjInfor o = (ObjInfor) selectedNode.getUserObject();
//					System.out.println(o.getDate());
//					System.out.println(o.getFile());
//					System.out.println(o);
//				}
			}

		});
		;

		// Display list file in folder sync
		LoadTree(root, "E:\\TestPBL4\\User\\");

//=======
//		// Display list file in folder sync
//		LoadTree(root, "E:\\TestPBL4\\User\\");
//>>>>>>> 4bda683273464d7e1f3c2be8c7a55dcb877814a1
		// end
		JScrollPane sc = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnTree.add(sc, BorderLayout.CENTER);

		pnTree.setPreferredSize(new Dimension(300, 0));
	}

	public TreeModel DisplayTree_() {
		pnTree = new JPanel();
		pnTree.setLayout(new BorderLayout());
		root = new DefaultMutableTreeNode("Home");
		tree = new JTree(root);
		// Display list file in folder sync
		LoadTree(root, "E:\\TestPBL4\\User\\");

		// end
		JScrollPane sc = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnTree.add(sc, BorderLayout.CENTER);

		pnTree.setPreferredSize(new Dimension(300, 0));

		return tree.getModel();
	}

	public void LoadTree(DefaultMutableTreeNode root, String path) {
		File f = new File(path);
		DefaultMutableTreeNode temp = new DefaultMutableTreeNode(new ObjInfor(f,user,"ab","c"));
		if (f.isDirectory()) {
			root.add(temp);
			File[] fs = f.listFiles();
			for (File i : fs) {
//				System.out.println(i.getAbsolutePath());
				LoadTree(temp, i.getAbsolutePath());
			}
		} else {
			root.add(temp);
		}
	}

	public static void main(String[] args) throws Exception {
	//	new fMain("localhost", 9090, "minhtuan");
	//	new fMain("localhost", 9090, "quanghuy");
		new fMain("localhost", 9090, "ngochieu");

	}
}