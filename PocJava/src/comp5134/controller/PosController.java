package comp5134.controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import comp5134.business.PosConstant;
import comp5134.model.PosModel;
import comp5134.view.PosAdminDialogView;
import comp5134.view.PosView;

public class PosController implements ActionListener {

	private PosModel model;
	private PosView view;
		
	String totaldesc = "Total: ";
	
	public PosController( PosModel ip_model, PosView ip_view ){
		
		model = ip_model;
		view = ip_view;
	}
	

	/**
	 * 	event trigger
	 */
	public void actionPerformed( ActionEvent ip_event ){
		
		String command = ip_event.getActionCommand();

		if( command.equals( PosConstant.CMD_NEW_ICECREAM ) ){
			
			actNewIceCreamBtn();
			
		}else if( command.startsWith( PosConstant.CMD_FLAVOR ) ){

			actFlavorBtn( ip_event );
			
		}else if( command.startsWith( PosConstant.CMD_DECORATOR ) ){
			
			actDecoratorBtn( ip_event );
			
		}else if( command.equals( PosConstant.CMD_ADMIN ) ){
			
			actAdminBtn();

		}else if( command.equals( PosConstant.CMD_ADMIN_CONFIRM ) ){
							
			actAdminConfirmBtn();
		}
		
	}
	
	/**
	 * New Ice-Cream button action
	 */
	private void actNewIceCreamBtn(){
		
		//reset
		model.releaseIceCreamOrder();
		//new ice-cream order			
		model.createIceCreamOrder();
		//reset price;
		view.getTotalLbl().setText( totaldesc + "0" );	
	}
	
	/**
	 * Add Flavor button action
	 * @param ip_event
	 */
	private void actFlavorBtn( ActionEvent ip_event ){
		
		//only one ice-cream flavor can be chosen each time
		if ( model.isAddFlavor() ){
			
			//get value from button property
			String flavorDesc = getIceCreamDescFromJButton( PosConstant.CMD_FLAVOR_DESC, ip_event );
			int flavorCost = getIceCreamCostFromJButton( PosConstant.CMD_FLAVOR_COST, ip_event );
			
			//add flavor with description and cost in ice-cream
			model.addFlavor( flavorDesc, flavorCost, model.getIceCream() );			
			
			//refresh price
			refreshTotalPriceinView();
			
		}else{
			
			view.getAlertDialog().popUp( "Only ONE Flavor can be selected." );
		}
		
	}
	
	/**
	 * Add Decorator button action
	 * @param ip_event
	 */
	private void actDecoratorBtn( ActionEvent ip_event ){
		
		//get value from button property
		String decoratorDesc =  getIceCreamDescFromJButton( PosConstant.CMD_DECORATOR_DESC, ip_event );
		int decoratorCost =  getIceCreamCostFromJButton( PosConstant.CMD_DECORATOR_COST, ip_event );
		
		//add decorator with description and cost in ice-cream
		model.addDecorator( decoratorDesc, decoratorCost, model.getIceCream() );
		
		//refresh price
		refreshTotalPriceinView();
	}
	
	/**
	 * System Administrator button action
	 */
	private void actAdminBtn(){
		
		view.getAdminDialog().setVisible( true );
		view.getAdminDialog().getCostTxt().setText( "" );
		view.getAdminDialog().getDescTxt().setText( "" );
	}
	
	/**
	 * System Administrator confirm dialog button action
	 */
	private void actAdminConfirmBtn(){
		
		if ( validateAdminInput( view.getAdminDialog() ) ){
			
			String desc = view.getAdminDialog().getDescTxt().getText();
			String cost = view.getAdminDialog().getCostTxt().getText();
			
			if( view.getAdminDialog().getFlavorRadioBtn().isSelected() ){
												
				view.getFlavorPanel().addButton( desc, cost, PosConstant.CMD_FLAVOR, PosConstant.CMD_FLAVOR_DESC, PosConstant.CMD_FLAVOR_COST, this );					
				
			}else if ( view.getAdminDialog().getDecoratorRadioBtn().isSelected() ){
				
				view.getDecoratorPanel().addButton( desc, cost, PosConstant.CMD_DECORATOR, PosConstant.CMD_DECORATOR_DESC, PosConstant.CMD_DECORATOR_COST, this );					
			}
			
			view.getAdminDialog().dispose();
			view.pack();
		}
		
	}
	
	/**
	 * validate admintrative action box value
	 * @param ip_adminDialog
	 * @return
	 */
	private boolean validateAdminInput( PosAdminDialogView ip_adminDialog ){
		
		boolean res = true;
		String errMessage = "";
		
		//check whether radio button is selected
		if( !ip_adminDialog.getFlavorRadioBtn().isSelected() && !ip_adminDialog.getDecoratorRadioBtn().isSelected() ){
			
			res = false;			
			errMessage = "Please select which item would like to be added.\n";
		}
		
		//check whether description and cost all inputted
		if( ip_adminDialog.getCostTxt().getText().isEmpty() || ip_adminDialog.getDescTxt().getText().isEmpty() ) {
			
			res = false;			
			errMessage = errMessage + "Please input all description and cost value.\n";
		}

		//check whether cost is digial
		try{
			
			Integer.valueOf( ip_adminDialog.getCostTxt().getText() );
			
		}catch( Exception ex ){
			
			res = false;
			errMessage = errMessage + "cost must be Integer.\n";
		}		
		
		if ( !res ){
			
			view.getAlertDialog().popUp( errMessage );
		}
		
		return res;		
	}
	
	
	/**
	 * get description from Jbutton attribute
	 * @param ip_type
	 * @param ip_event
	 * @return
	 */
	private String getIceCreamDescFromJButton( String ip_type, ActionEvent ip_event ){
		
		JButton source = (JButton)ip_event.getSource();
		
		return (String)source.getClientProperty( ip_type );
	}
	
	/**
	 * get cost from Jbutton attribute 
	 * @param ip_type
	 * @param ip_event
	 * @return
	 */
	private int getIceCreamCostFromJButton( String ip_type, ActionEvent ip_event ){
		
		JButton source = (JButton)ip_event.getSource();
		
		return Integer.valueOf( (String)source.getClientProperty( ip_type ) ).intValue();
	}
	
	
	/**
	 * refresh price
	 */
	private void refreshTotalPriceinView(){
		
		System.out.println( model.getIceCream().getDescription() );
		
		view.getTotalLbl().setText( totaldesc + String.valueOf( model.getCost() ) );		
	}
	
}
