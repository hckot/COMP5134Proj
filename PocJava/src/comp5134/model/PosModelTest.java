package comp5134.model;

import org.junit.Test;

import comp5134.business.PosSimpleIceCream;

import junit.framework.TestCase;

public class PosModelTest extends TestCase {

	private PosModel model;
	
	public void setUp(){
		
		model = new PosModel();
	}
	
	@Test
	public void testGetIceCream()  {
		
		//create simple ice-cream object
		assertNotNull( model.getIceCream() );		
	}

	@Test
	public void testCreateIceCreamOrder() {
		
		//new order as simple ice-cream object
		assertTrue( model.getIceCream() instanceof PosSimpleIceCream );
	}

	@Test
	public void testAddDecorator() {
		
		int expected = 1;
		//add one decorator, cost=1
		model.addDecorator( "testDecorator", 1, model.getIceCream() );		
		assertEquals( expected, model.getCost() );
		
		//add one decorator, cost=4, total:1+4=5		
		model.addDecorator( "testDecorator2", 4, model.getIceCream() );
		expected = 5;
		assertEquals( expected, model.getCost() );
	}

	@Test
	public void testAddFlavor() {
		
		int expected = 20;
		//add one flavor, cost=20
		model.addFlavor( "testFlavor", 20, model.getIceCream() );		
		assertEquals( expected, model.getCost() );
	}

	@Test
	public void testIsAddFlavor() {

		assertTrue( model.isAddFlavor() );
		
		//no impact for adding decorator
		model.addDecorator( "testDecorator", 9, model.getIceCream() );
			
		assertTrue( model.isAddFlavor() );
		
		//only one flavor can be added in the transaction
		model.addFlavor( "testFlavor", 5, model.getIceCream() );		
		
		assertFalse( model.isAddFlavor() );
	}

	@Test
	public void testReleaseIceCreamOrder() {
		
		//new order
		int expected = 25;
		model.addFlavor( "testFlavor", 20, model.getIceCream() );	
		model.addDecorator( "testDecorator", 5, model.getIceCream() );
		assertEquals( expected, model.getCost() );
		
		//reset order
		expected = 0;
		model.releaseIceCreamOrder();
		model.createIceCreamOrder();
		assertEquals( expected, model.getCost() );
	}

	@Test
	public void testGetCost() {
		
		//new order
		int expected = 0;
		model.createIceCreamOrder();
		assertEquals( expected, model.getCost() );

		//add one decorator, cost=1
		model.addDecorator( "testDecorator", 1, model.getIceCream() );
		expected = 1;
		assertEquals( expected, model.getCost() );

		//add one decorator, cost=9, total:9+1=10
		model.addDecorator( "testDecorator2", 9, model.getIceCream() );
		expected = 10;
		assertEquals( expected, model.getCost() );

		//add one flavor, cost=20, total:10+20=30
		model.addFlavor( "testFlavor", 20, model.getIceCream() );
		expected = 30;
		assertEquals( expected, model.getCost() );
		
		//add one flavor, cost=10, total:30 due to one flavor can be added
		if( model.isAddFlavor() ){
			model.addFlavor( "testFlavor2", 10, model.getIceCream() );
		}
		expected = 30;
		assertEquals( expected, model.getCost() );
		
		//add one decorator, cost=5, total:30+5=35
		model.addDecorator( "testDecorator3", 5, model.getIceCream() );
		expected = 35;
		assertEquals( expected, model.getCost() );
		
		//new order back to 0
		model.releaseIceCreamOrder();
		model.createIceCreamOrder();
		expected = 0;
		assertEquals( expected, model.getCost() );
	}

}
