package source;

import edu.unm.cs.cs351.f10.tdrl.p2.CIT;

/**
 * This is an interface that determines what assignment policy to use
 * @author niallschavez
 *
 * @param <S>
 */
public interface Assignment<S> {

	
	public void plantType(AssemblyLine<S>[] lines, CIT<S> info, MyOrderReceipt<S> oR);
	
	
}
