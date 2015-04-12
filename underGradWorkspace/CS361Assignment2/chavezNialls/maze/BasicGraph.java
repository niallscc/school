/**
 * 
 */
package maze;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tom
 *
 */
public class BasicGraph<T> implements Graph<T> {

	private HashMap<T,HashSet<T>> adj;  // all the graph data, in adjacency list form.
	
	public BasicGraph( ) {
		this.adj = new HashMap<T,HashSet<T>>( );
	}	

	public BasicGraph(HashMap<T,HashSet<T>> adj) {
		this.adj = adj;
	}
	
	/* (non-Javadoc)
	 * @see edu.unm.cs.cs361.fall2011.hayes.tom.maze.Graph#getVertices()
	 */
	@Override
	public Set<T> getVertices() {
		return adj.keySet( );
	}

	/* (non-Javadoc)
	 * @see edu.unm.cs.cs361.fall2011.hayes.tom.maze.Graph#neighbors(java.lang.Object)
	 */
	@Override
	public Set<T> neighbors(T v) {
		return adj.get(v);
	}

	/* (non-Javadoc)
	 * @see edu.unm.cs.cs361.fall2011.hayes.tom.maze.Graph#isEdge(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean isEdge(T v, T w) {
		HashSet<T> vNbrs = adj.get(v);
		if (vNbrs == null)
			return false;
		return vNbrs.contains(w);
	}

}
