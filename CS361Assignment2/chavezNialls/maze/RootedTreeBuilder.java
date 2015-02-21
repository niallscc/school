/**
 * 
 */
package maze;

import java.util.HashMap;

/**
 * @author tom
 *
 */
public class RootedTreeBuilder<T> {

	T root;
	HashMap<T,T> parent;
	
	public RootedTreeBuilder ( T v ) {
		root = v;
		parent = new HashMap<T,T>( );
		parent.put(root, null);
	}

	public void add( T v, T p ) throws DuplicateVertexException {
		if (parent.containsKey(v))
			throw new DuplicateVertexException("Vertex " + v + " was already added.");
		parent.put( v, p );
	}

	@SuppressWarnings("unchecked")
	public RootedTree<T> toRootedTree ( ) {
		return new BasicRootedTree<T>( root, (HashMap<T,T>) parent.clone( ) );
	}
}
