/**
 * 
 */
package maze;

/**
 * @author tom
 *
 */
public class BasicMaze<T> implements Maze<T> {

	Graph<T> g;
	T startVertex, finishVertex;
	
	public BasicMaze( ) {
	}

	public BasicMaze( BasicGraph<T> g ) {
		this.g = g;
	}
	
	public void setStart( T v ) {
		startVertex = v;
	}
	
	public void setFinish( T v ) {
		finishVertex = v;
	}
	
	@Override
	public Graph<T> getGraph( ) {
		return g;
	}
	
	@Override
	public T getStart( ) {
		return startVertex;
	}
	
	@Override
	public T getFinish( ) {
		return finishVertex;
	}
	
}
