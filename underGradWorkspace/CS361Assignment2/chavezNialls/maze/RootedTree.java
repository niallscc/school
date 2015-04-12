/**
 * 
 */
package maze;

import java.util.Set;

/**
 * @author tom
 *
 */
public interface RootedTree<T> {

	public T getRoot( );
	
	public T parentOf( T v );
	
	public Set<T> childrenOf( T v );
	
}
