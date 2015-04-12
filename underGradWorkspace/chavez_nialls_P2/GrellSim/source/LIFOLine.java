package source;
/**
 * orders the queue in a Last in first out kind of order
 * @author niallschavez
 *
 * @param <T>
 */
public class LIFOLine<T> extends AbsOrderContainer<T> {
	
	double myArrivalTime;
	
	@Override
	public int compareTo(OrderContainer<T> o) {
		
		if( this.myArrivalTime > o.getCIT().getArrivalTime())
			return -1;
		else if(this.myArrivalTime < o.getCIT().getArrivalTime() )
			return 1;
		else
			return 0;
	}
	
}
