package XuLi;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.text.html.HTMLEditorKit;

import GUI.fMain;

public class PrevFile implements ActionListener {
	public fMain f;

	public PrevFile(fMain f) {
		this.f = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
//			File file = new File(this.f.jtreeVal);
//			File file = new File(this.f.val);
//			if (file.exists()) {
//				if (Desktop.isDesktopSupported()) {
//					Desktop.getDesktop().open(file);
//				} else {
//					JOptionPane.showMessageDialog(this.f, "no support");
//				}
//			} else {
//				JOptionPane.showMessageDialog(this.f, "file does not exis or is a directory");
//			}
//			JEditorPane jep = new JEditorPane();
//		       jep.setEditable(false);   
//
//		       try {
//		         jep.setPage("file:///E:/TestPBL4/User/Tuan/17-18-19.html");
//		       }catch (IOException e1) {
//		         jep.setContentType("text/html");
//		         jep.setText("<html>Could not load</html>");
//		       } 
//		       JScrollPane scrollPane = new JScrollPane(jep);
//		       this.f.pnlRender.add(scrollPane);
//		       this.f.linkOpen = "file:///E:/TestPBL4/User/Tuan/17-18-19.html";
			this.f.jep.setEditable(false);
	        HTMLEditorKit editorKit = new HTMLEditorKit();
			try {
				this.f.jep.setPage("file:///" + this.f.val);
			} catch (IOException e1) {
				this.f.jep.setContentType("image/jpg");
				this.f.jep.setText("<html>Could not load</html>");
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}

}
