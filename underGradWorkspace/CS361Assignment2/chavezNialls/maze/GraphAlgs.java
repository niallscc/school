/**
 * 
 */
package maze;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tom
 *
 */
public abstract class GraphAlgs {
	
	public static<T> RootedTree<T> bFS ( Graph<T> g, T start ) {
		FIFOQueue<T> active = new FIFOQueue<T>( );
		Set<T> found = new HashSet<T>( );
		active.add(start);
		found.add(start);
		RootedTreeBuilder<T> rv = new RootedTreeBuilder<T>( start );		
		while (active.size() > 0) {
			T v = active.poll( );       // get next active node from the queue.
			for (T w : g.neighbors(v)) {
				if (!found.contains(w)) {
					found.add(w);
					active.add(w);
					try { 
						rv.add(w,v);
					} catch (DuplicateVertexException dve) {
						System.out.println("Duplicate vertex exception in bFS method.\n"+dve);
						assert(false);
					}
				}
			}
		}
		return rv.toRootedTree( );
	}
}
