package comp5134.view;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import comp5134.business.PosConstant;
import comp5134.controller.PosController;

public class PosAdminDialogView extends JDialog {

	private JFrame frame;
	
	private JRadioButton flavorRadioBtn = new JRadioButton( "Flavor" );
	private JRadioButton decoratorRadioBtn = new JRadioButton( "Decorator" );

	private JLabel descLbl = new JLabel( "Description" );
	private JLabel costLbl = new JLabel( "Cost" );
		
	private JTextField descTxt = new JTextField();	
	private JTextField costTxt = new JTextField();
		
	private JButton confirmBtn = new JButton( "Confirm" );
		
	public PosAdminDialogView( JFrame ip_jframe ){
				
		super( ip_jframe, "System Administrator", true );
		
		frame = ip_jframe;
		
		buildUI();
		
		setSize( 300, 150 );
	}	
	
	private void buildUI(){
		
		JPanel adminDialogPanel = new JPanel();
		adminDialogPanel.setLayout( new BorderLayout() );
		
		JPanel inputPanel = new JPanel();		
		inputPanel.setLayout( new GridLayout(0,2) );	
		
		confirmBtn.setActionCommand( PosConstant.CMD_ADMIN_CONFIRM );
				
		ButtonGroup group = new ButtonGroup();
		group.add( flavorRadioBtn );
		group.add( decoratorRadioBtn );
		
		inputPanel.add( flavorRadioBtn );
		inputPanel.add( decoratorRadioBtn );
		inputPanel.add( descLbl );
		inputPanel.add( descTxt );
		inputPanel.add( costLbl );
		inputPanel.add( costTxt );
		
		adminDialogPanel.add( inputPanel, BorderLayout.CENTER );
		adminDialogPanel.add( confirmBtn, BorderLayout.SOUTH );
		
		add( adminDialogPanel );
		
		setDefaultLookAndFeelDecorated(true);		
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

		pack();
		setLocationRelativeTo( frame );		
	}
	
	
	public void addConfirmListener( PosController ip_controller ){		
		
		confirmBtn.addActionListener( ip_controller );		
	}
	

	public JRadioButton getFlavorRadioBtn() {		
		return flavorRadioBtn;
	}
	
	public JRadioButton getDecoratorRadioBtn() {		
		return decoratorRadioBtn;
	}

	public JTextField getDescTxt() {		
		return descTxt;
	}

	public JTextField getCostTxt() {		
		return costTxt;
	}
	
	
}

