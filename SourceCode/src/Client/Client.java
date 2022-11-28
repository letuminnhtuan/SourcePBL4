package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ClassObj.Agent;
import ClassObj.ObjInfor;
import Database.DBHelper;
import XuLi.XuLiTacVu;

@SuppressWarnings("serial")
public class Client extends JFrame implements ActionListener {
	public JPanel contentPane;
	public JTextArea text;
	public JTextField txtMess;
	public JButton btnSend;
	public Socket socket;
	public ObjectInputStream dataInput;
	public ObjectOutputStream dataOutput;
	public Agent user;

	public Client(String host, int port, Agent user) throws Exception {
		this.user = new Agent(user);
		socket = new Socket(host, port);
		dataOutput = new ObjectOutputStream(socket.getOutputStream());
		dataInput = new ObjectInputStream(socket.getInputStream());
		showFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
//		new ClientThread(dataInput, this.user).start();
	}

	public void showFrame() {
		setResizable(false);
		setBounds(100, 100, 450, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtMess = new JTextField();
		txtMess.setBounds(10, 294, 315, 33);
		contentPane.add(txtMess);
		txtMess.setColumns(10);

		btnSend = new JButton("Send");
		btnSend.setBounds(335, 294, 89, 33);
		btnSend.addActionListener(this);
		contentPane.add(btnSend);

		text = new JTextArea();
		text.setBounds(10, 11, 414, 277);
		contentPane.add(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			JFileChooser fileChoose = new JFileChooser();
			fileChoose.showDialog(this, "Open");
			File f = fileChoose.getSelectedFile();
			ObjInfor obj = new ObjInfor(f, this.user, "now", "none");
			//System.out.println(obj);
			dataOutput.writeObject(obj);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		DBHelper db = new DBHelper();
		Agent a = db.getAgentByUsername("minhtuan");
		Agent b = db.getAgentByUsername("ngochieu");
		Agent c = db.getAgentByUsername("quanghuy");
		System.out.println(a.path);
		System.out.println(b.path);
		System.out.println(c.path);
		Client cl = new Client("127.0.0.1", 9090, a);
		Client cl1 = new Client("127.0.0.1", 9090, b);
		Client cl2 = new Client("127.0.0.1", 9090, c);
	}
}
