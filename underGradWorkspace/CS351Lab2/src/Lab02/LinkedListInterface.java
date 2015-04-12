package Lab02;

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
	
	public void remove(T node);
	
	/**
	 * Returns the Head element of the List
	 * 
	 * @return returns the head
	 */
	
	public T getHead();

	

	/**
	 * Removes the element stored at the position specified by the index. Note that the linkedList is 0 indexed.
	 * 
	 * 
	 * @param index	the location of the element to be removed
	 */
	public void removeAt(int index) throws IndexOutOfBoundsException;
	
	
	/**
	 * Returns the element at the position specified by the index. Note that the LinkedList is 0 indexed.
	 * 
	 * 
	 * @param index the location of the element to be returned
	 */
	public T getAt(int index) throws IndexOutOfBoundsException;;
	
	
	/**
	 * Empties this list
	 * 
	 */
	public void empty();
	
	
	/**
	 * Returns a deep copy of this LinkedList
	 * 
	 * 
	 * 
	 * @return A deep copy of this LinkedList
	 */
	public LinkedList<T> deepCopy();
	
		
	
	
}
