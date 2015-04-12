package source;
import edu.unm.cs.cs351.f10.tdrl.p2.OrderReceipt;

/**
 * This class creates a receipt for a given order that can then be looked up from inside the assembly line class to change or remove
 * orders 
 * @author niallschavez
 *
 * @param <S>
 */
public class MyOrderReceipt<S> implements OrderReceipt<S> {
	
	private Integer customerID=0;
	private Integer orderID=0;
	
	public void setCusID(int id){
		customerID= id;
	}

	public void setOrderID(int id){
		orderID=id;
	}
	@Override
	public int getCustomerID() {
		return customerID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public S getOrderID() {

		return (S)orderID;
	}

}
