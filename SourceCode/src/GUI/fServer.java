package GUI;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClassObj.Agent;
import XuLi.XuLiServer;

public class fServer extends JFrame {

	public JPanel contentPane;
	public JTable table;
	public JTextField txtSearch;
	public JButton btnAdd;
	public JButton btnEdit;
	public JButton btnDelete;
	public JButton btnSearch;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fServer frame = new fServer();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public fServer() {
		setVisible(true);
		setTitle("Server");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1117, 588);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new CompoundBorder());
		scrollPane.setBounds(10, 53, 1081, 485);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Username", "Password", "Name", "Role", "Path", "Host", "Port" }));
		table.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table);

		btnAdd = new JButton("ADD USER");
		btnAdd.setBounds(282, 11, 126, 31);
		contentPane.add(btnAdd);

		btnEdit = new JButton("EDIT USER");
		btnEdit.setBounds(146, 11, 126, 31);
		contentPane.add(btnEdit);

		btnDelete = new JButton("DELETE USER");
		btnDelete.setBounds(10, 11, 126, 31);
		contentPane.add(btnDelete);

		txtSearch = new JTextField();
		txtSearch.setBounds(753, 11, 202, 31);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);

		btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(965, 11, 126, 31);
		contentPane.add(btnSearch);
		LoadTable();
	}

	public void LoadTable() {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		XuLiServer xl = new XuLiServer();
		for (Agent i : xl.getAllAgent()) {
			String[] data = { i.username, i.password, i.name, i.role, i.path, i.host, i.port + "" };
			model.addRow(data);
		}
	}
}