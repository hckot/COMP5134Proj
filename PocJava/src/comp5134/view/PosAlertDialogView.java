package comp5134.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PosAlertDialogView extends JOptionPane {

	JFrame frame;
	
	public PosAlertDialogView( JFrame ip_jframe ){
		
		frame = ip_jframe;
	}	
	
	public void popUp( String ip_message ){ 
		
		showMessageDialog(
				frame,
				ip_message,
				"POS warning",
				JOptionPane.WARNING_MESSAGE
		);
	}
	
}
