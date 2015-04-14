package comp5134.business;

public abstract class PosIceCreamDecorator extends PosIceCream {

	protected final PosIceCream decoratedIceCream;
	
	public PosIceCreamDecorator( PosIceCream ip_decoratedIceCream ){
		
		decoratedIceCream = ip_decoratedIceCream;
	}
	
	public String getDescription(){
		
		return decoratedIceCream.getDescription();
	}
	
	public int getCost(){
		
		return decoratedIceCream.getCost();
	}
}
