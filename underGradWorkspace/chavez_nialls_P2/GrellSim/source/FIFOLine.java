package source;


/**
 * Fifo line does things in a first in first out manner. 
 * @author niallschavez
 *
 * @param <T>
 */
public class FIFOLine<T> extends AbsOrderContainer<T>{
	
	double myArrivalTime;

	public double arival;
	
	@Override
	public int compareTo(OrderContainer<T> o) {
		
		if( this.myArrivalTime < o.getCIT().getArrivalTime())
			return -1;
		else if(this.myArrivalTime > o.getCIT().getArrivalTime() )
			return 1;
		else
			return 0;
	}


}
