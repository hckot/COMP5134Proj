package comp5134;

import comp5134.model.PosModel;
import comp5134.view.PosView;

public class PosMVCDriver  {

	public PosMVCDriver(){

		PosModel model = new PosModel();
		PosView view = new PosView( model );		
	}
	
	public static final void main( String[] args ){
		
		new PosMVCDriver();
	}
}
