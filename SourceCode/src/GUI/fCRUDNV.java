package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ClassObj.Agent;
import XuLi.XuLiServer;

public class fCRUDNV extends JFrame implements ActionListener {
	public interface Load {
		void LoadTable();
	}

	public JPanel contentPane;
	public JTextField txtName;
	public JTextField txtUsername;
	public JLabel lblUsername;
	public JLabel lblPassword;
	public JTextField txtHost;
	public JLabel lblHost;
	public JLabel lblPort;
	public JTextField txtPort;
	public JLabel lblPath;
	public JTextField txtPath;
	public JLabel lblRole;
	public JComboBox<String> cbbRole;
	public String username;
	public String type;
	public JPasswordField txtPassword;
	public JButton btnShow;
	public Load load;

	public fCRUDNV(String username, String type) {
		this.username = username;
		this.type = type;
		setVisible(true);
		setTitle("User");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(43, 52, 106, 38);
		contentPane.add(lblNewLabel);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtName.setBounds(159, 52, 387, 38);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsername.setColumns(10);
		txtUsername.setBounds(159, 100, 387, 38);
		contentPane.add(txtUsername);

		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(43, 100, 106, 38);
		contentPane.add(lblUsername);

		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(43, 148, 106, 38);
		contentPane.add(lblPassword);

		txtHost = new JTextField();
		txtHost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHost.setColumns(10);
		txtHost.setBounds(159, 194, 387, 38);
		contentPane.add(txtHost);

		lblHost = new JLabel("Host");
		lblHost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHost.setBounds(43, 194, 106, 38);
		contentPane.add(lblHost);

		lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPort.setBounds(43, 242, 106, 38);
		contentPane.add(lblPort);

		txtPort = new JTextField();
		txtPort.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPort.setColumns(10);
		txtPort.setBounds(159, 242, 387, 38);
		contentPane.add(txtPort);

		lblPath = new JLabel("Path");
		lblPath.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPath.setBounds(43, 290, 106, 38);
		contentPane.add(lblPath);

		txtPath = new JTextField();
		txtPath.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPath.setColumns(10);
		txtPath.setBounds(159, 290, 303, 38);
		txtPath.setEditable(false);
		contentPane.add(txtPath);

		lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRole.setBounds(43, 338, 106, 38);
		contentPane.add(lblRole);

		cbbRole = new JComboBox<String>();
		cbbRole.setModel(new DefaultComboBoxModel<String>(new String[] { "admin", "server" }));
		cbbRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbbRole.setBounds(159, 338, 387, 38);
		cbbRole.setSelectedIndex(-1);
		contentPane.add(cbbRole);

		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOK.setBounds(409, 387, 137, 38);
		btnOK.addActionListener(this);
		contentPane.add(btnOK);

		JButton btnCancle = new JButton("Cancle");
		btnCancle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancle.setBounds(268, 387, 131, 38);
		btnCancle.addActionListener(this);
		contentPane.add(btnCancle);

		txtPassword = new JPasswordField();
		txtPassword.setEchoChar('*');
		txtPassword.setBounds(159, 149, 303, 38);
		contentPane.add(txtPassword);

		btnShow = new JButton("Show");
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShow.setBounds(472, 148, 74, 38);
		btnShow.addActionListener(this);
		contentPane.add(btnShow);

		JButton btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSelect.setBounds(472, 290, 74, 38);
		btnSelect.addActionListener(this);
		contentPane.add(btnSelect);
		SetGUI();
	}

	public void SetGUI() {
		XuLiServer xl = new XuLiServer();
		if (xl.getAgentByUsername(this.username) != null) {
			Agent user = new Agent(xl.getAgentByUsername(this.username));
			this.txtName.setText(user.name);
			this.txtUsername.setText(user.username);
			this.txtUsername.setEditable(false);
			this.txtPassword.setText(user.password);
			this.txtHost.setText(user.host);
			this.txtPath.setText(user.path);
			this.txtPort.setText(user.port + "");
			int index = (user.role.equals("admin")) ? 0 : 1;
			this.cbbRole.setSelectedIndex(index);
		}
	}

	public void ReloadGUI() {
		this.txtName.setText("");
		this.txtUsername.setText("");
		this.txtPassword.setText("");
		this.txtHost.setText("");
		this.txtPath.setText("");
		this.txtPort.setText("");
		this.cbbRole.setSelectedIndex(-1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		if (button.equals("OK")) {
			XuLiServer xl = new XuLiServer();
			String name = this.txtName.getText();
			String username = this.txtUsername.getText();
			String password = String.valueOf(this.txtPassword.getPassword());
			String host = this.txtHost.getText();
			String path = this.txtPath.getText();
			String port = this.txtPort.getText();
			String roler = this.cbbRole.getSelectedItem().toString();
			Agent user = new Agent(name, username, password, host, Integer.parseInt(port), path, roler);
			if (this.type.equals("add")) {
				xl.AddUser(user);
				ReloadGUI();
			} else if (this.type.equals("edit")) {
				xl.UpdateUser(user);
			}
			this.load.LoadTable();
		} else if (button.equals("Cancle")) {
			this.dispose();
		} else if (button.equals("Show")) {
			if (this.txtPassword.getEchoChar() == '*') {
				this.txtPassword.setEchoChar((char) 0);
			} else {
				this.txtPassword.setEchoChar('*');
			}
		} else if (button.equals("Select")) {
			JFileChooser j = new JFileChooser(new File("E:\\TestPBL4"));
			j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(j.showDialog(this, "Select") == JFileChooser.OPEN_DIALOG) {
				File dir = j.getSelectedFile();
				this.txtPath.setText(dir.getAbsolutePath());
			}
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new fCRUDNV(null, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
