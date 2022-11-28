package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClassObj.Agent;
import Database.DBHelper;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;

public class fLogin extends JFrame implements ActionListener {

	public JPanel contentPane;
	public JTextField txtUsername;
	public JPasswordField txtPassword;
	public JButton btnLogin;
	public Socket socket;
	public ObjectInputStream dataInput;
	public ObjectOutputStream dataOutput;
	public Agent user;
	/**
	 * Launch the application.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// check Login
		String username = this.txtUsername.getText();
		String password = String.valueOf(this.txtPassword.getPassword());
		if (username.length() == 0) {

		} else if (password.length() == 0) {

		} else {
			DBHelper db = new DBHelper();
			if(db.checkLogin(username, password)){
				this.txtUsername.setText("");
				this.txtPassword.setText("");
				try {
					new fMain("localhost", 9090, username);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public fLogin() {
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsername.setBounds(120, 64, 244, 26);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setBounds(120, 102, 244, 26);
		contentPane.add(txtPassword);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 63, 78, 26);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(10, 101, 78, 26);
		contentPane.add(lblPassword);

		btnLogin = new JButton("Log in");
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 16));
		btnLogin.setBounds(255, 144, 109, 33);
		btnLogin.addActionListener(this);
		contentPane.add(btnLogin);
	}
	public static void main(String[] args) {
	//	fLogin f1 = new fLogin();
//		fLogin f2 = new fLogin();
		fLogin f3 = new fLogin();
	}
}
