package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ClassObj.Agent;
import XuLi.XuLiServer;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class fCRUDNV extends JFrame implements ActionListener {

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
	public JComboBox cbbRole;
	public String username;
	public String type;
	private JPasswordField txtPassword;
	private JButton btnShow;

	public fCRUDNV(String username, String type) {
		this.username = username;
		this.type = type;
		setVisible(true);
		setTitle("User");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
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
		txtName.setBounds(159, 52, 278, 38);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsername.setColumns(10);
		txtUsername.setBounds(159, 100, 278, 38);
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
		txtHost.setBounds(159, 194, 278, 38);
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
		txtPort.setBounds(159, 242, 278, 38);
		contentPane.add(txtPort);

		lblPath = new JLabel("Path");
		lblPath.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPath.setBounds(43, 290, 106, 38);
		contentPane.add(lblPath);

		txtPath = new JTextField();
		txtPath.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPath.setColumns(10);
		txtPath.setBounds(159, 290, 278, 38);
		contentPane.add(txtPath);

		lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRole.setBounds(43, 338, 106, 38);
		contentPane.add(lblRole);

		cbbRole = new JComboBox();
		cbbRole.setModel(new DefaultComboBoxModel(new String[] { "admin", "server" }));
		cbbRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbbRole.setBounds(159, 338, 278, 38);
		contentPane.add(cbbRole);

		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOK.setBounds(300, 390, 137, 38);
		btnOK.addActionListener(this);
		contentPane.add(btnOK);

		JButton btnCancle = new JButton("Cancle");
		btnCancle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancle.setBounds(159, 390, 131, 38);
		btnCancle.addActionListener(this);
		contentPane.add(btnCancle);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(159, 149, 230, 38);
		contentPane.add(txtPassword);
		
		btnShow = new JButton("S");
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnShow.setBounds(399, 149, 38, 38);
		btnShow.addActionListener(this);
		contentPane.add(btnShow);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		if (button.equals("OK")) {
			System.out.println("asd");
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
			} else if (this.type.equals("edit")) {
				xl.UpdateUser(user);
			}
		} else if (button.equals("Cancle")) {
			this.dispose();
		} else if (button.equals("S")) {
			if (this.txtPassword.getEchoChar() == '*') {
				this.txtPassword.setEchoChar((char) 0);
			} else {
				this.txtPassword.setEchoChar('*');
			}
		}
	}

}
