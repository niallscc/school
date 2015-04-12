package graphs;
/**
 * @author tom
 * A graph together with int-valued weights on the edges.
 * @param <T> The type for the vertices of the graph.
 */
public interface WeightedGraph<T> extends Graph<T> {
	
	/**
	 * Returns the int-valued weight for the edge between v and w.
	 * If v and w are not adjacent, throws a NonEdgeException.
	 * @param v the first endpoint of the edge
	 * @param w the second endpoint of the edge
	 * @return the weight of the edge
	 */
	int getWeight(T v, T w) throws NonEdgeException;
	
}
