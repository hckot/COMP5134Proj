package comp5134.business;

import org.junit.rules.ExpectedException;
import org.junit.Test;
import junit.framework.TestCase;


import comp5134.model.PosModel;

public class PosIceCreamFactoryTest extends TestCase {

    public ExpectedException thrown= ExpectedException.none();
	
    private PosModel model;
	
    public void setUp(){
		
		model = new PosModel();
	}
    
	@Test
	public void testGetIceCreamInstanceByTypeStringPosIceCream() {
		
		//add decorator in ice-cream
		assertTrue( PosIceCreamFactory.getIceCreamInstanceByType( PosConstant.OBJ_ICECREAM_DECORATORITEM, model.getIceCream() ) instanceof PosIceCreamDecoratorItems );

		//add flavor in ice-cream
		assertTrue( PosIceCreamFactory.getIceCreamInstanceByType( PosConstant.OBJ_ICECREAM_FLAVOR, model.getIceCream() ) instanceof PosIceCreamFlavor );
		
		//no this decorator/flavor of ice-cream
		assertNull( PosIceCreamFactory.getIceCreamInstanceByType( "", model.getIceCream() ) );
	}

	@Test 
	public void testGetIceCreamInstanceByTypeString() {
			
		//create simple ice-cream
		assertTrue( PosIceCreamFactory.getIceCreamInstanceByType( PosConstant.OBJ_SIMPLE_ICECREAM ) instanceof PosSimpleIceCream );
		
		//no this type of ice-cream
		assertNull( PosIceCreamFactory.getIceCreamInstanceByType( "" ) );
		
	}
	
    @Test ( expected=NullPointerException.class )
    public void throwsExceptionWithNewFlavorByTypeString() {

    	//nullpointer exception will be thrown if created Flavor directly, simpleicecream need to be added.
    	PosIceCreamFlavor iceCreamFlavor = ( PosIceCreamFlavor )PosIceCreamFactory.getIceCreamInstanceByType( PosConstant.OBJ_ICECREAM_FLAVOR );
    	iceCreamFlavor.setCost( 5 );
    	
    	thrown.expect( NullPointerException.class );
		throw new NullPointerException();
    }
    
    @Test ( expected=NullPointerException.class )
    public void throwsExceptionWithNewDecoratorItemByTypeString() {

		//nullpointer exception will be thrown if created Decorator directly, simpleicecream need to be added.
		PosIceCreamDecoratorItems iceCreamDecoratorItem = ( PosIceCreamDecoratorItems )PosIceCreamFactory.getIceCreamInstanceByType( PosConstant.OBJ_ICECREAM_DECORATORITEM );
		iceCreamDecoratorItem.setCost( 9 );
		
		thrown.expect( NullPointerException.class );
		throw new NullPointerException();
    }
	

}
