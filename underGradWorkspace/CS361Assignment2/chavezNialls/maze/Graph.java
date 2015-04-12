/**
 * 
 */
package maze;

import java.util.Set;

/**
 * @author tom
 * A graph whose vertex set consists of objects of type T.
 * For now, the assumption is that the graph is undirected, and simple
 * (no self-loops or repeated edges).
 *
 * @param <T> the type of vertex in the graph.
 */
public interface Graph<T> {
		
	/**
	 * @return the vertex set of the graph.
	 */
	public Set<T> getVertices( );
	
	/**
	 * @param v a vertex in the graph
	 * @return the set of all vertices adjacent to v in the graph.
	 */
	public Set<T> neighbors(T v);
	
	/**
	 * @param v a vertex in the graph
	 * @param w another vertex in the graph
	 * @return true if the two arguments are neighbors in the graph.
	 */
	public boolean isEdge(T v, T w);
	
}
