import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuExampleController implements ActionListener{
	private MenuExampleView menuExampleView;
	
	
	
	public MenuExampleController(MenuExampleView menuExampleView) {
		this.menuExampleView = menuExampleView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		
		if(button.equals("Exit")) {
			System.exit(0);
		}else{
			this.menuExampleView.setTextJLabel("Bạn đã click: "+button);
		}
	}

}