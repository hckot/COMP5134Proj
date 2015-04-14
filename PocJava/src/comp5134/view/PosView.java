package comp5134.view;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import comp5134.business.PosConstant;
import comp5134.controller.PosController;
import comp5134.model.PosModel;

public class PosView extends JFrame{

	//MVC Structure
	private PosModel model;
	private PosController controller;

	private JLabel total = new JLabel( "Total: 0" );
	
	private JButton orderIceCream = new JButton( "[New Ice-cream]" );
	private JButton admin = new JButton( "[System Administrator" );

	//Flavor and Decorator item panel that pending for adding button in admin
	private PosItemsPanel flavorPanel = new PosItemsPanel();
	private PosItemsPanel decoratorPanel = new PosItemsPanel();

	//Alert message and system administrative dialog message box
	private PosAlertDialogView alertDialog = new PosAlertDialogView( this );
	private PosAdminDialogView adminDialog = new PosAdminDialogView ( this );
	
	
	public PosView( PosModel ip_model ){
		
		model = ip_model;
		
		attachController( makeController() );
		buildUI();
		
		//JFrame properties
		setTitle( "ICE-CREAM Point-of-Sale System" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setResizable( false );
				
		//Display the window.
		setLocationRelativeTo(null);
		setVisible( true );	    
	    pack();
	}
	
	/**
	 * add component in actionListener and will be triggered actionPerformed in PosController 
	 * @param ip_controller
	 */
	private void attachController( PosController ip_controller ){
		
		controller = ip_controller;
				
		orderIceCream.addActionListener( controller );				
		admin.addActionListener( controller );		
		adminDialog.addConfirmListener( controller );
	}
	
	private PosController makeController(){
				
		return new PosController( model, this );
	}
	
	/**
	 * building user interface with layout design
	 */
	private void buildUI(){
		
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints cons = new GridBagConstraints();
	    
	    setLayout(gridbag);
	 	
	    //new component
		JLabel flavorLbl = new JLabel( "ICE-CREAM Flavor" );
		JLabel decoratorLbl = new JLabel( "Decorator" );
						
		flavorPanel.addButton( "Chocolate", "20", PosConstant.CMD_FLAVOR, PosConstant.CMD_FLAVOR_DESC, PosConstant.CMD_FLAVOR_COST, controller );
		flavorPanel.addButton( "Vanilla", "20", PosConstant.CMD_FLAVOR, PosConstant.CMD_FLAVOR_DESC, PosConstant.CMD_FLAVOR_COST, controller );
		
		decoratorPanel.addButton( "M&M", "5", PosConstant.CMD_DECORATOR, PosConstant.CMD_DECORATOR_DESC, PosConstant.CMD_DECORATOR_COST, controller );
		decoratorPanel.addButton( "Strawberry", "4", PosConstant.CMD_DECORATOR, PosConstant.CMD_DECORATOR_DESC, PosConstant.CMD_DECORATOR_COST, controller );
		
		orderIceCream.setActionCommand( PosConstant.CMD_NEW_ICECREAM );
		admin.setActionCommand( PosConstant.CMD_ADMIN );

		//add component and allocate layout location
		setGridBagLayoutLocate( cons, 1, 1 );
		add( flavorLbl, cons );
		
		setGridBagLayoutLocate( cons, 1, 2 );
		add( decoratorLbl, cons );
		
	    setGridBagLayoutLocate( cons, 1, 3 );
		add( orderIceCream, cons  );
		
		setGridBagLayoutLocate( cons, 2, 1 );
	    cons.fill = GridBagConstraints.BOTH; 
		add( flavorPanel, cons  );
		
		setGridBagLayoutLocate( cons, 2, 2 );
	    cons.fill = GridBagConstraints.BOTH;
		add( decoratorPanel, cons  );
		
		setGridBagLayoutLocate( cons, 2, 3 );
		add( new JLabel(""), cons );
		
		setGridBagLayoutLocate( cons, 3, 1 );		
		add( new JLabel(""), cons );
		
		setGridBagLayoutLocate( cons, 3, 2 );		
		add( new JLabel(""), cons );
		
		setGridBagLayoutLocate( cons, 3, 3 );
		add( total, cons );
		
		setGridBagLayoutLocate( cons, 4, 1 );		
		add( admin, cons );		
		
	}
	
	private void setGridBagLayoutLocate( GridBagConstraints ip_cons, int ip_column, int ip_row ){
	
		ip_cons.gridy = ip_column;
		ip_cons.gridx = ip_row;
	}
	
	public PosItemsPanel getDecoratorPanel(){		
		return decoratorPanel;
	}
	
	public PosItemsPanel getFlavorPanel(){		
		return flavorPanel;
	}
	
	public JLabel getTotalLbl(){		
		return total;
	}
	
	public PosAlertDialogView getAlertDialog(){
		return alertDialog;
	}
	
	public PosAdminDialogView getAdminDialog(){
		return adminDialog;
	}
}
