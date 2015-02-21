package maze;

/**
 * @author tom
 * A graph, together with a designated start vertex and finish vertex.
 * 
 * @param <T> The vertex type for the maze.
 */
public interface Maze<T> {

	/**
	 * @return the underlying graph
	 */
	public Graph<T> getGraph( );
	
	/**
	 * @return the start vertex
	 */
	public T getStart( );
	
	/**
	 * @return the finish vertex
	 */
	public T getFinish( );

}
