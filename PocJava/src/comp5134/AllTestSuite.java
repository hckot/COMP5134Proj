package comp5134;

import junit.framework.TestSuite; 
import junit.framework.Test;


public class AllTestSuite extends TestSuite {

	public static Test suite() { 

		TestSuite suite = new TestSuite("POS Ice-Cream JUnit Tests");
		
		//Add one entry for each test class 
		suite.addTestSuite( comp5134.business.PosIceCreamFactoryTest.class );		
		suite.addTestSuite( comp5134.model.PosModelTest.class );
		
		return suite;
	}

}
