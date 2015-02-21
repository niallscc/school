package source;

import edu.unm.cs.cs351.f10.tdrl.p2.ConcrCIT;
/**
 * This is an intermediary abstract class that implements ORder container and then delegates the decision making to the 
 * Fifo,Lifo, and Highest Val assembly lines. 
 * @author niallschavez
 *
 * @param <T>
 */
public abstract class AbsOrderContainer<T> implements OrderContainer<T>{
	
	int _policy;
	protected MyOrderReceipt<T> orderRec;
	ConcrCIT<T> myCit;
	OrderContainer<T> c;
	/**
	 * Sets the priority policy 
	 */
	@Override
	public void setPolicy(int policy){
		//System.out.println("Setting policy:!!!!!!!!!");
		/*
		 * KEY: 0: FIFO factory 
		 *		1: LIFO factory
		 *		2: Higheset ValueFactory  
		*/
		_policy=policy;

		if( policy==0){
			 c=new FIFOLine<T>();
		}
		else if(policy==1){
			c=new LIFOLine<T>();
		}
		else if(policy==2){
			
			c=new HighestValLine<T>();
		}
	}
	/**Sets in the cit. needs a setter because S can not be referenced Statically
	 * 
	 */
	@Override
	public void setCIT(ConcrCIT<T> cit){
		myCit=cit;
	}

	@Override
	public OrderContainer <T> getContainer(){
		return c;
	}
	
	/**
	 * gets the key for the assembly line
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getKey() {
		
		//Integer key=orderRec.hashCode();
		//System.out.println("The orderRec in getKey(AbsOrderContainer) is "+orderRec.hashCode());
		return (T) orderRec;
	}
	/**
	 * getter method for the cit
	 */
	@Override 
	public ConcrCIT<T> getCIT(){
		return myCit;
	}
	
	/**
	 * sets the Receipt for the order
	 */
	@Override 
	public void setReceipt(MyOrderReceipt<T> o){
		//System.out.println("Order Receipt in AbsOrderContainer: "+o);
		orderRec=o;
	}
	/**
	 * gets the receipt for the order
	 */
	@Override
	public MyOrderReceipt<T> getReceipt(){
		return orderRec;
	}
	
	


}
