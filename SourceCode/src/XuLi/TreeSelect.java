package XuLi;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.fMain;

public class TreeSelect implements MouseListener {
	public fMain f;

	public TreeSelect(fMain f) {
		this.f = f;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			if (this.f.tree.getSelectionPath() != null) {
				this.f.jtreeVal = this.f.tree.getSelectionPath().toString().replaceAll("[\\[\\]]", "").replace(", ",
						"\\");
				// sửa lại path nha
				String path = "E:\\TestPBL4\\User\\";
				String[] words = this.f.jtreeVal.split("\\\\");
				if (words.length >= 2) {
					this.f.jtreeVal = path + words[words.length - 2] + "\\" + words[words.length - 1];
					System.out.println(this.f.jtreeVal);
				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
