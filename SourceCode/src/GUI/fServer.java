package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
import Client.ClientThread;
import Database.DBHelper;
import GUI.fCRUDNV.Load;
import XuLi.XuLiServer;

public class fServer extends JFrame implements ActionListener, Load {
	public JPanel contentPane;
	public JTable table;
	public JTextField txtSearch;
	public JButton btnAdd;
	public JButton btnEdit;
	public JButton btnDelete;
	public JButton btnSearch;
	public Agent user;

	public fServer(String username) throws IOException {
		DBHelper db = new DBHelper();
		this.user = new Agent(db.getAgentByUsername(username));
		setVisible(true);
		setTitle(this.user.name);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		btnAdd.setBounds(10, 11, 126, 31);
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);

		btnEdit = new JButton("EDIT USER");
		btnEdit.setBounds(146, 11, 126, 31);
		btnEdit.addActionListener(this);
		contentPane.add(btnEdit);

		btnDelete = new JButton("DELETE USER");
		btnDelete.setBounds(282, 12, 126, 31);
		btnDelete.addActionListener(this);
		contentPane.add(btnDelete);

		txtSearch = new JTextField();
		txtSearch.setBounds(753, 11, 202, 31);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);

		btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(965, 11, 126, 31);
		btnSearch.addActionListener(this);
		contentPane.add(btnSearch);
		LoadTable();
	}

	public void LoadTable() {
		DeleteAllRows();
		String txt = this.txtSearch.getText();
		Load(txt);
	}

	public void Load(String txtSearch) {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		XuLiServer xl = new XuLiServer();
		if (txtSearch.equals("")) {
			for (Agent i : xl.getAllAgent()) {
				String[] data = { i.username, i.password, i.name, i.role, i.path, i.host, i.port + "" };
				model.addRow(data);
			}
		} else {
			for (Agent i : xl.getAllAgent()) {
				if (i.name.contains(txtSearch) || i.username.equals(txtSearch) || i.role.equals(txtSearch)) {
					String[] data = { i.username, i.password, i.name, i.role, i.path, i.host, i.port + "" };
					model.addRow(data);
				}
			}
		}
	}

	public void DeleteAllRows() {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		if (button.equals("ADD USER")) {
			fCRUDNV f = new fCRUDNV("", "add");
			f.setLocationRelativeTo(this);
			f.load = this;
		} else if (button.equals("EDIT USER")) {
			int index;
			if ((index = this.table.getSelectedRow()) != -1) {
				DefaultTableModel model = (DefaultTableModel) this.table.getModel();
				String username = model.getValueAt(index, 0).toString();
				fCRUDNV f = new fCRUDNV(username, "edit");
				f.setLocationRelativeTo(this);
				f.load = this;
			}
		} else if (button.equals("DELETE USER")) {
			int index;
			if ((index = this.table.getSelectedRow()) != -1) {
				DefaultTableModel model = (DefaultTableModel) this.table.getModel();
				XuLiServer xl = new XuLiServer();
				xl.DeleteUser(model.getValueAt(index, 0).toString());
			}
			LoadTable();
		} else if (button.equals("SEARCH")) {
			LoadTable();
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new fServer("server");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
