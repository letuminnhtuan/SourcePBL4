package XuLi;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ClassObj.ObjInfor;
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
				ObjInfor obj = (ObjInfor) this.f.tree.getSelectionPath().getLastPathComponent();
//				System.out.println(obj.file.getAbsolutePath());
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
