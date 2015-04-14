package comp5134.business;

public class PosIceCreamDecoratorItems extends PosIceCreamDecorator {
	
	public String description;
	public int cost;
	
	public PosIceCreamDecoratorItems( PosIceCream ip_decoratedIceCream ){
		
		super( ip_decoratedIceCream );
	}
	
	public String getDescription(){
				
		return super.getDescription() + "with Decorator [" + description + "] ";
	}
	
	public int getCost(){
		
		return super.getCost() + cost;
	}
	
	public void setDescription( String ip_desc ){
		
		description = ip_desc;
	}
	
	public void setCost( int ip_cost ){
		
		cost = ip_cost;
	}

}
