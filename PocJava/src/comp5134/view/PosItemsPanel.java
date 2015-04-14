package comp5134.view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JButton;

import comp5134.controller.PosController;

public class PosItemsPanel extends JPanel {

	int btnCount = 1;
	
	public PosItemsPanel(){
		
		setLayout( new GridLayout( 0, 1 ) );
	}
		
	public void addButton( String ip_desc, String ip_cost, String ip_btnId, String ip_btnClientDescId, String ip_btnClientCostId, PosController ip_controller ){
		
		JButton btn = new JButton( "[" + ip_desc + ", $" + ip_cost + "]" );
		
		setBtnAttribute( btn, ip_desc, ip_cost, ip_btnId, ip_btnClientDescId, ip_btnClientCostId );
			
		addBtnListener( btn, ip_controller );
		
		add( btn );		
	}
	
	protected void setBtnAttribute( JButton ip_btn, String ip_desc, String ip_cost, String ip_btnId, String ip_btnClientDescId, String ip_btnClientCostId ){
		
		ip_btn.setActionCommand( ip_btnId+btnCount );
		
		ip_btn.putClientProperty( ip_btnClientDescId, ip_desc );
		ip_btn.putClientProperty( ip_btnClientCostId, ip_cost );
		
		btnCount++;		
	}
	
	protected void addBtnListener( JButton ip_btn, PosController ip_controller ){
		
		ip_btn.addActionListener( ip_controller );		
	}
}
