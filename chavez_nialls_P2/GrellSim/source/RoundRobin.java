package source;


import edu.unm.cs.cs351.f10.tdrl.p2.CIT;
/**
 * Puts things onto the 
 * queue in a Round robin kinda way, where each item is placed on the next assembly line and then 
 * when it reaches the end it wraps around. 
 * @author niallschavez
 *
 * @param <S>
 */
public class RoundRobin<S> implements Assignment<S>{
	int currentLine=0;
	

	@Override
	public void plantType(AssemblyLine<S>[] lines,CIT<S> cit, MyOrderReceipt<S> oR) {
		//System.out.println("in Round Robin "+lines+" id is: "+id);
			if( (++currentLine) < lines.length ){
				lines[currentLine].addOrder(cit, oR);		
			}
			else 
				lines[0].addOrder(cit, oR);
	}
}