package source;

import edu.unm.cs.cs351.f10.tdrl.p2.CIT;

public class Order {
	
	private int TIME;
	private double COST;
	private double COMPLEX;
	
	//************** BEGIN ORDER INFORMATION ****************//
	public Integer getOrderTime(){
		return TIME;
	}
	
	public double getCost(){
		return COST;
	}
	
	public double getComplexity(){
		return COMPLEX;
	}
	
	public <S> void CITSetter(CIT<S> info){
		
		TIME= info.getArrivalTime();
		
		COST=info.getOrder().getSalePrice();
		COST-=info.getOrder().getFixedPrice();
			
		COMPLEX= info.getOrder().getComplexity();
	
	}
	
	//************* END ORDER CONFIGURATIONS ****************//
	
}

