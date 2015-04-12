/**
 * 
 */
package maze;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author tom
 * A plain old First-In First-Out queue data structure, with only the essential functionality.
 * Backed by an ArrayBlockingQueue object, but variables declared as FIFOQueue won't have access
 * to all 34 methods of ArrayBlockingQueue.  Really, how many of these do we need?
 */
public class FIFOQueue<E> {

	private ArrayBlockingQueue<E> queue = new ArrayBlockingQueue<E>(100);

	public int size( ) {
		return queue.size( );
	}
		
	public E poll( ) {
		return queue.poll( );
	}
	
	public E peek( ) {
		return queue.peek( );
	}
	
	public void add(E elt) {
		queue.add(elt);
	}
	
}
