/**
 * 
 */
package maze;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author tom
 *
 */
public class BasicGraphBuilder<T> {

	private HashMap<T,HashSet<T>> adj;
	
	public BasicGraphBuilder ( ) {
		adj = new HashMap<T,HashSet<T>>( );
	}

	public void addVertex ( T v ) {
		if (adj.containsKey(v))
			return;
		else adj.put(v, new HashSet<T>( ));
	}
	
	public void addEdge ( T v, T w ) {
		addVertex(v);  // ensure that v, w, have entries in adj.
		addVertex(w);
		adj.get(v).add(w);    // add w to nbr list for v
		adj.get(w).add(v);    // add v to nbr list for w
	}

	public boolean hasVertex ( T v ) {
		return adj.containsKey(v);
	}
	
	@SuppressWarnings("unchecked")
	public BasicGraph<T> toGraph( ) {
		return new BasicGraph<T>( (HashMap<T,HashSet<T>>) adj.clone( ) );
	}
}
