package comp5134.model;

import comp5134.business.PosConstant;
import comp5134.business.PosIceCream;
import comp5134.business.PosIceCreamDecoratorItems;
import comp5134.business.PosIceCreamFactory;
import comp5134.business.PosIceCreamFlavor;

public class PosModel {

	int flavorCount = 0;
	
	//only one ice-cream object during started 
	PosIceCream iceCream;
	
	
	public PosIceCream getIceCream(){
		
		if ( iceCream == null ){
			
			createIceCreamOrder();
		}
				
		return iceCream;
	}
	
		
	/**
	 * Create a ice-cream order
	 * @return
	 */
	public void createIceCreamOrder(){
		
		iceCream = PosIceCreamFactory.getIceCreamInstanceByType( PosConstant.OBJ_SIMPLE_ICECREAM );
	}
	
	
	/**
	 * Add Decorator in ice-cream
	 * @param ip_desc
	 * @param ip_cost
	 * @param ip_iceCream
	 * @return
	 */
	public void addDecorator( String ip_desc, int ip_cost, PosIceCream ip_iceCream ){
	
		PosIceCreamDecoratorItems iceCreamDecoratorItem = ( PosIceCreamDecoratorItems )PosIceCreamFactory.getIceCreamInstanceByType( PosConstant.OBJ_ICECREAM_DECORATORITEM, iceCream );
		
		iceCreamDecoratorItem.setCost( ip_cost );
		iceCreamDecoratorItem.setDescription( ip_desc );

		iceCream = iceCreamDecoratorItem;		
	}
	
	/**
	 * Add Flavor in ice-cream
	 * @param ip_desc
	 * @param ip_cost
	 * @param ip_iceCream
	 * @return
	 */
	public void addFlavor( String ip_desc, int ip_cost, PosIceCream ip_iceCream ){
		
		//add one
		flavorCount = 1;
		
		PosIceCreamFlavor iceCreamFlavor = ( PosIceCreamFlavor )PosIceCreamFactory.getIceCreamInstanceByType( PosConstant.OBJ_ICECREAM_FLAVOR, iceCream );
		
		iceCreamFlavor.setCost( ip_cost );
		iceCreamFlavor.setDescription( ip_desc );
		
		iceCream = iceCreamFlavor;
	}
	
	/**
	 * Check whether added flavor in ice-cream	 * 
	 * @return
	 */
	public boolean isAddFlavor(){
		
		boolean res = false;
				
		//only one ice-cream flavor can be chosen each time
		if ( flavorCount < PosConstant.LIMIT_FLAVOR ){
			
			res = true;
		}
		
		return res;
	}
	
	/**
	 * reset ice-cream value.
	 */
	public void releaseIceCreamOrder(){
		
		flavorCount = 0;
		iceCream = null;
		
		System.out.println( "ice-cream reset!!" );
	}
	
	public int getCost(){
		
		return iceCream.getCost();
	}
		
	
}
