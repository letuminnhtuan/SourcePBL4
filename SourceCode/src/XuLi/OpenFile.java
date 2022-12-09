package XuLi;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import GUI.fMain;

public class OpenFile implements ActionListener{
	public fMain f;
	public OpenFile(fMain f){
		this.f = f;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			File file = new File(this.f.jtreeVal);
			if (file.exists()) {
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(file);
				} else {
					JOptionPane.showMessageDialog(this.f, "no support");
				}
			} else {
				JOptionPane.showMessageDialog(this.f, "file does not exis or is a directory");
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}

}
