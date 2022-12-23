package XuLi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.swing.JFileChooser;

import ClassObj.ObjInfor;
import GUI.fMain;
import test.testServer;

public class Upload implements ActionListener {
	public fMain f;

	public Upload(fMain f) {
		this.f = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			JFileChooser fileChoose = new JFileChooser(new File("C:"));
			fileChoose.showDialog(this.f, "Open");
			File f = fileChoose.getSelectedFile();
			ObjInfor obj = new ObjInfor(f, this.f.user, "now", "upload," + this.f.val);
			this.f.dataOutput.writeObject(obj);
			// Test -----------------------------------------------------
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[100];
			Integer bytesRead = 0;
			while ((bytesRead = fis.read(buffer)) > 0) {
				this.f.dataOutput.writeObject(bytesRead);
//				this.f.dataOutput.writeObject(Arrays.copyOf(buffer, buffer.length));
			}
			// Test -----------------------------------------------------
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}