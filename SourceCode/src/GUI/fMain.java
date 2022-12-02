package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import ClassObj.Agent;
import Client.ClientThread;
import Database.DBHelper;
import XuLi.Upload;
import XuLi.XuLiTacVu;

@SuppressWarnings("serial")
public class fMain extends JFrame {
	public DefaultMutableTreeNode root = null;
	public JTree tree;
	public JPanel pnlRender;
	public JButton btnUpload;
	public JButton btnDelete;
	public JButton btnOpen;
	public Container con;
	public JPanel pnTree;
	public Socket socket;
	public ObjectInputStream dataInput;
	public ObjectOutputStream dataOutput;
	public Agent user;
	String jtreeVal;

	public fMain(String host, int port, String username) throws Exception {
		DBHelper db = new DBHelper();
		this.user = db.getAgentByUsername(username);
		this.user = new Agent(user);
		socket = new Socket(host, port);
		dataOutput = new ObjectOutputStream(socket.getOutputStream());
		dataInput = new ObjectInputStream(socket.getInputStream());
		new ClientThread(dataInput, this.user).start();
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
		pnlMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 5));
		btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new Upload(this));
		btnDelete = new JButton("Delete");
		btnOpen = new JButton("Open");
		
		btnOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					File file = new File(jtreeVal);
					if (file.exists()) {
						if (Desktop.isDesktopSupported()) {
							Desktop.getDesktop().open(file);
						} else {
							JOptionPane.showMessageDialog(pnlRight, "no support");
						}
					} else {
						JOptionPane.showMessageDialog(pnlRight, "file does not exis or is a directory");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		pnlMenu.add(btnUpload);
		pnlMenu.add(btnDelete);
		pnlMenu.add(btnOpen);
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
		// Display list file in folder sync
		LoadTree(root, "D:\\TestPBL4\\User\\");

		// end
		JScrollPane sc = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnTree.add(sc, BorderLayout.CENTER);

		pnTree.setPreferredSize(new Dimension(300, 0));
		
		tree.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				jtreeVal = tree.getSelectionPath().toString().replaceAll("[\\[\\]]", "").replace(", ", "\\");
				String path = "D:\\TestPBL4\\User\\";
				String[] words = jtreeVal.split("\\\\");
				if (words.length >= 2) {
					jtreeVal = path + words[words.length - 2] + "\\" + words[words.length - 1];
					
				}
			}
		});
	}

	public TreeModel DisplayTree_() {
		pnTree = new JPanel();
		pnTree.setLayout(new BorderLayout());
		root = new DefaultMutableTreeNode("Home");
		tree = new JTree(root);
		// Display list file in folder sync
		LoadTree(root, "D:\\TestPBL4\\User\\");

		// end
		JScrollPane sc = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnTree.add(sc, BorderLayout.CENTER);

		pnTree.setPreferredSize(new Dimension(300, 0));

		return tree.getModel();
	}

	public void LoadTree(DefaultMutableTreeNode root, String path) {
		File f = new File(path);
		DefaultMutableTreeNode temp = new DefaultMutableTreeNode(f.getName());
		if (f.isDirectory()) {
			root.add(temp);
			File[] fs = f.listFiles();
			for (File i : fs) {
				System.out.println(i.getAbsolutePath());
				LoadTree(temp, i.getAbsolutePath());
			}
		} else {
			root.add(temp);
		}
	}

//	public static void main(String[] args) {
//		new fMain();
//	}
}
