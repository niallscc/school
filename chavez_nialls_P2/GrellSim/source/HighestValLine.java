package source;
/**
 * This class is used when the user wants to remove the higest value orders first so they dont get canceled. 
 * @author niallschavez
 *
 * @param <T>
 */
public class HighestValLine<T> extends AbsOrderContainer<T> {
	
	double compyVal;

	
	@Override
	public int compareTo(OrderContainer<T> p) { 

		// Highest Val stuff
		if( this.compyVal < p.getCIT().getOrder().getSalePrice())
			return -1;
		else if(compyVal >  p.getCIT().getOrder().getSalePrice())
			return 1;
		else
			return 0;
	}




}

