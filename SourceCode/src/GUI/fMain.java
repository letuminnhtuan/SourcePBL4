package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import ClassObj.Agent;
import ClassObj.ObjInfor;
import Client.ClientThread;
import Database.DBHelper;
import XuLi.CreateFolder;
import XuLi.Delete;
import XuLi.OpenFile;
import XuLi.PrevFile;
import XuLi.TreeSelect;
import XuLi.TreeSelectionNode;
import XuLi.Upload;

@SuppressWarnings("serial")
public class fMain extends JFrame {
	public DefaultMutableTreeNode root = null;
	public JTree tree;
//	public JPanel pnlRender;
	public JButton btnUpload;
	public JButton btnDelete;
	public JButton btnCreFol;
	public JButton btnOpen;
	public JButton btnPrev;
	public Container con;
	public JPanel pnTree;
	public Socket socket;
	public ObjectInputStream dataInput;
	public ObjectOutputStream dataOutput;
	public Agent user;
	public DefaultMutableTreeNode selectedNode = null;
	public String Folder_File;
	public String[] result;
	public String jtreeVal;
	public String val;
	public JEditorPane jep;
	public fMain(String username) throws Exception {
		DBHelper db = new DBHelper();
		this.user = new Agent(db.getAgentByUsername(username));
		socket = new Socket(user.host, user.port);
		dataOutput = new ObjectOutputStream(socket.getOutputStream());
		dataInput = new ObjectInputStream(socket.getInputStream());
		new ClientThread(dataInput, this.user, this).start();
		SetGUI();
		this.setSize(new Dimension(1000, 700));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		jep = new JEditorPane();
		jep.setPreferredSize(new Dimension(600, 600));
		JScrollPane scrollPane = new JScrollPane(jep);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlRight.add(scrollPane, BorderLayout.CENTER);
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(Color.WHITE);
		pnlMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 5));
		btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new Upload(this));
		btnDelete = new JButton("Delete");
		btnPrev = new JButton("Preview");
		btnPrev.addActionListener(new PrevFile(this));
		btnDelete.addActionListener(new Delete(this));

		btnOpen = new JButton("Open");

		btnOpen.addActionListener(new OpenFile(this));

		btnCreFol = new JButton("Create Folder");
		btnCreFol.addActionListener(new CreateFolder(this));
		pnlMenu.add(btnUpload);
		pnlMenu.add(btnDelete);
		pnlMenu.add(btnPrev);
		pnlMenu.add(btnOpen);
		pnlMenu.add(btnCreFol);
		pnlRight.add(pnlMenu, BorderLayout.NORTH);

		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnTree, pnlRight);
		sp.setOneTouchExpandable(true);

		con.add(sp, BorderLayout.CENTER);
	}

	public void DisplayTree() {
		pnTree = new JPanel();
		pnTree.setLayout(new BorderLayout());
		root = new DefaultMutableTreeNode(new ObjInfor(new File("E:\\TestPBL4\\"), user, "ab", "c"));
		tree = new JTree(root);
		tree.addTreeSelectionListener(new TreeSelectionNode(this));
		LoadTree(root, "E:\\TestPBL4\\User\\");
		JScrollPane sc = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnTree.add(sc, BorderLayout.CENTER);

		pnTree.setPreferredSize(new Dimension(300, 0));

		tree.addMouseListener(new TreeSelect(this));

	}

	public void LoadTree(DefaultMutableTreeNode root, String path) {
		File f = new File(path);
		DefaultMutableTreeNode temp = new DefaultMutableTreeNode(new ObjInfor(f, user, "ab", "c"));
		if (f.isDirectory()) {
//			new CheckFileEdit(path, this).start();
			root.add(temp);
			File[] fs = f.listFiles();
			for (File i : fs) {
				LoadTree(temp, i.getAbsolutePath());
			}
		} else {
			root.add(temp);
		}
	}

	public static void main(String[] args) throws Exception {
//		new fMain("minhtuan");
//		new fMain("quanghuy");
		new fMain("ngochieu");
	}
}
