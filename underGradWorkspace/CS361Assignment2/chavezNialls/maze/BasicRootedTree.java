/**
 * 
 */
package maze;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author tom
 *
 * A bare-bones implementation of the RootedTree interface.
 * The childrenOf method, in particular, is not very efficient.
 */
public class BasicRootedTree<T> implements RootedTree<T> {

	T root;
	Map<T,T> parent;
	
	public BasicRootedTree (T root, Map<T,T> parent){
		this.root = root;
		this.parent = parent;
	}
	
	@Override
	public T getRoot() {
		return root;
	}

	@Override
	public T parentOf(T v) {
		return parent.get(v);
	}

	@Override
	public Set<T> childrenOf(T v) {
		Set<T> rv = new HashSet<T>( );
		for (T key : parent.keySet( )) {
			T val = parent.get(key);
			if (val.equals(v))
				rv.add(key);
		}
		return rv;
	}

}
