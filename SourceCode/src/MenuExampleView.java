import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

public class MenuExampleView extends JFrame{
	private JLabel jLabel;
	public JPopupMenu jPopupMenu;

	public MenuExampleView() {
		this.setTitle("Menu Example");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		// Tạo controller
		MenuExampleController menuExampleController = new MenuExampleController(this);
		
		// Tạo thanh menu
		JMenuBar jMenuBar = new JMenuBar();
		
		// Tạo 1 menu
		JMenu jMenu_file = new JMenu("File");
		jMenu_file.setMnemonic(KeyEvent.VK_F);
		jMenu_file.setDisplayedMnemonicIndex(0);
		
		// Tạo các menu con
		JMenuItem jMenuItem_new = new JMenuItem("New", KeyEvent.VK_N);
		jMenuItem_new.addActionListener(menuExampleController);
		jMenuItem_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
	
		JMenuItem jMenuItem_open = new JMenuItem("Open", KeyEvent.VK_O);
		jMenuItem_open.addActionListener(menuExampleController);
		jMenuItem_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		
		JMenuItem jMenuItem_exit = new JMenuItem("Exit", KeyEvent.VK_X);
		jMenuItem_exit.addActionListener(menuExampleController);
		jMenuItem_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_DOWN_MASK));
		
		jMenu_file.add(jMenuItem_new);
		jMenu_file.add(jMenuItem_open);
		jMenu_file.addSeparator();
		jMenu_file.add(jMenuItem_exit);
		
		
		JMenu jMenu_help = new JMenu("Help");
		jMenu_help.setMnemonic(KeyEvent.VK_L);
		jMenu_help.setDisplayedMnemonicIndex(0);
		
		JMenuItem jMenuItem_welcome = new JMenuItem("Welcome");
		jMenuItem_welcome.addActionListener(menuExampleController);
		jMenu_help.add(jMenuItem_welcome);
		
		jMenuBar.add(jMenu_file);
		jMenuBar.add(jMenu_help);
		
		// Thêm thanh thanh menu vào chương trình
		this.setJMenuBar(jMenuBar);
		
		
		// Tạo ToolBar (Thanh công cụ)
		JToolBar jToolBar = new JToolBar();
		JButton jButton_Undo = new JButton("Undo");
		jButton_Undo.setToolTipText("Nhấn vào đây để quay lại thao tác vừa rồi!");
		jButton_Undo.addActionListener(menuExampleController);
		JButton jButton_Redo = new JButton("Redo");
		jButton_Redo.addActionListener(menuExampleController);
		JButton jButton_Copy = new JButton("Copy");
		jButton_Copy.addActionListener(menuExampleController);
		JButton jButton_Cut = new JButton("Cut");
		jButton_Cut.addActionListener(menuExampleController);
		JButton jButton_Paste = new JButton("Paste");
		jButton_Paste.addActionListener(menuExampleController);
		jToolBar.add(jButton_Undo);
		jToolBar.add(jButton_Redo);
		jToolBar.add(jButton_Copy);
		jToolBar.add(jButton_Cut);
		jToolBar.add(jButton_Paste);
		
		this.add(jToolBar, BorderLayout.NORTH);
		
		// Menu chuột phải JPopupMenu
		jPopupMenu = new JPopupMenu();
		
		JMenu jMenuPoupFont = new JMenu("Font");
		JMenuItem jMenuItemType = new JMenuItem("Type");
		jMenuItemType.addActionListener(menuExampleController);
		JMenuItem jMenuItemSize = new JMenuItem("Size");
		jMenuItemSize.addActionListener(menuExampleController);
		jMenuPoupFont.add(jMenuItemType);
		jMenuPoupFont.add(jMenuItemSize);
		
		JMenuItem jMenuItemCut = new JMenuItem("Cut");
		jMenuItemCut.addActionListener(menuExampleController);
		JMenuItem jMenuItemCopy = new JMenuItem("Copy");
		jMenuItemCopy.addActionListener(menuExampleController);
		JMenuItem jMenuItemPaste = new JMenuItem("Paste");
		jMenuItemPaste.addActionListener(menuExampleController);
		
		jPopupMenu.add(jMenuPoupFont);
		jPopupMenu.addSeparator();
		jPopupMenu.add(jMenuItemCut);
		jPopupMenu.add(jMenuItemCopy);
		jPopupMenu.add(jMenuItemPaste);
		
		this.add(jPopupMenu);
		
		// Thêm sự kiện phải chuột vào JLabel
		MenuExampleMouseListener menuExampleMouseListener = new MenuExampleMouseListener(this);
		this.addMouseListener(menuExampleMouseListener);
		
		// Tạo label hiển thị
		Font font = new Font("Arial", Font.BOLD, 50);
		jLabel = new JLabel();
		
		
		this.add(jLabel, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public void setTextJLabel(String s) {
		this.jLabel.setText(s);
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new MenuExampleView();
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
}