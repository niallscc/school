package graphs;
public interface StressTester {
	
	/**
	 * Returns the largest int x such that there exists a path from s to t such that all
	 * edge weights are less than or equal to x.
	 * 
	 * The only reason this is not a static method is that Java doesn't allow static methods in
	 * interfaces, and I wanted to give you an interface file.
     *
     * Important note: In your implementation, you are allowed to give up on finding a path from
     * s to t if the BFS tree from either s or t reaches a size of more than 10,000 nodes without
     * including the other vertex.  In this case, you may end up returning a slightly conservative
     * (too small) estimate for the maxLoad function.  In practice, this should not happen for
     * any of the test examples provided in the StressTesterTester class.
     *
	 * @param <Node> The vertex type for g.
	 * @param g A weighted graph, with int weights on the edges.
	 * @param s The start node.
	 * @param t The destination node.
	 * @return The maximum load that can be safely transported along some path from s to t.
	 */
	public <Node> int maxLoad( WeightedGraph<Node> g, Node s, Node t);

}
