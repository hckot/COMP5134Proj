package comp5134.business;

public class PosIceCreamFactory {

	
	public static PosIceCream getIceCreamInstanceByType( String ip_type, PosIceCream ip_iceCream ){
	
		PosIceCream icecream = null;
		
		switch ( ip_type ){
			case PosConstant.OBJ_SIMPLE_ICECREAM:
				icecream = new PosSimpleIceCream();
				break;
			
			case PosConstant.OBJ_ICECREAM_DECORATORITEM:
				icecream = new PosIceCreamDecoratorItems( ip_iceCream );
				break;				

			case PosConstant.OBJ_ICECREAM_FLAVOR:
				icecream = new PosIceCreamFlavor( ip_iceCream );
				break;
				
			default:				
				System.out.println( "type unmatched!!");
				break;			
		}
		
		return icecream;
		
	}
	
	
	public static PosIceCream getIceCreamInstanceByType( String ip_type ){
		
		return getIceCreamInstanceByType( ip_type, null );
	}
		
}
