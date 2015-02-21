package chavez_nialls;

/**
 * This should be implemented as a linked list
 * 
 */

public interface LinkedListInterface<T> 
{
		
	// HINT: You probably want a HEAD and TAIL parameter
	//
	
	/**
	 * Adds a Node at the end of the List.
	 * 
	 * 
	 * 
	 * @param node
	 */
	public void add(T node);
	// HINT: special case if the HEAD is null
	
	/**
	 * 
	 * Iterates across the list checking if the passed element is in the list. 
	 * The check is done with the generic object's equals method.
	 * 
	 * @param node the node to be located in the list
	 * @return	returns true if the node is in the list, false otherwise
	 * 
	 */
	public boolean contains(T node);

	
	
	/**
	 * Removes the first element that is equal() to the passed node from the List.
	 * 
	 * @param node the node to be removed
	 */
	
	//HINT: Special case occurs when the HEAD is removed
	
	/**
	 * Returns the Head element of the List
	 * 
	 * @return returns the head
	 */
	
	//HINT: Special case occurs when the head is null
}