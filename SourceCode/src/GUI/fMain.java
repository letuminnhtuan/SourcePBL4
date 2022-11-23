package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class fMain extends JFrame {
	DefaultMutableTreeNode root = null;
	JTree tree;

	public fMain() {
		SetGUI();
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void SetGUI() {
		Container con = getContentPane();
		JPanel pnTree = new JPanel();
		pnTree.setLayout(new BorderLayout());
		root = new DefaultMutableTreeNode("Home");
		tree = new JTree(root);
		// Display list file in folder sync
		LoadTree(root, "E:\\TestPBL4\\User\\");
		// end
		JScrollPane sc = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnTree.add(sc, BorderLayout.CENTER);

		con.setLayout(new BorderLayout());
		pnTree.setPreferredSize(new Dimension(300, 0));

		JPanel pnRight = new JPanel();
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnTree, pnRight);
		sp.setOneTouchExpandable(true);

		con.add(sp, BorderLayout.CENTER);
	}

	public void LoadTree(DefaultMutableTreeNode root, String path) {
		File f = new File(path);
		DefaultMutableTreeNode temp = new DefaultMutableTreeNode(f.getName());
		if(f.isDirectory()) {
			root.add(temp);
			File[] fs = f.listFiles();
			for(File i : fs) {
				LoadTree(temp, i.getAbsolutePath());
			}
		}
		else {
			root.add(temp);
		}
	}

	public static void main(String[] args) {
		new fMain();
	}
}
