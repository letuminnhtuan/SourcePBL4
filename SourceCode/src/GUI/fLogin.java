package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ClassObj.Agent;
import Database.DBHelper;

public class fLogin extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField txtUsername;
	public JPasswordField txtPassword;
	public JButton btnLogin;
	public Agent user;

	/**
	 * Launch the application.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String username = this.txtUsername.getText();
			String password = String.valueOf(this.txtPassword.getPassword());
			if (username.length() == 0 || password.length() == 0) {
				this.txtUsername.setText("");
				this.txtPassword.setText("");
				JOptionPane.showMessageDialog(this, "Fill full data");
			} else {
				DBHelper db = new DBHelper();
				if (db.checkLogin(username, password)) {
					this.txtUsername.setText("");
					this.txtPassword.setText("");
					if (db.getAgentByUsername(username).role.equals("user")) {
						new fMain(username);
					} else if (db.getAgentByUsername(username).role.equals("admin")) {
						new fServer(username);
					}
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public fLogin() {
		setResizable(false);
		setTitle("Log in");
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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new fLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
