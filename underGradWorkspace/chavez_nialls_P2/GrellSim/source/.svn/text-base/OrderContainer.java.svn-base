package source;

import edu.unm.cs.cs351.f10.tdrl.p2.ConcrCIT;
import edu.unm.cs.cs351.f10.tdrl.p2.Findable;
/**
 * The interface for the set order 
 * @author niallschavez
 *
 * @param <S>
 */
public interface OrderContainer<S> extends  Findable<S>, Comparable< OrderContainer<S>>  {
	int order=0;
	ConcrCIT<S> getCIT();

	void setPolicy(int policy);
	
	OrderContainer<S> getContainer();

	void setCIT(ConcrCIT<S> cit);
	
	void setReceipt(MyOrderReceipt<S> oR);
	
	MyOrderReceipt<S> getReceipt();
}
