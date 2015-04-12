package Lab02;
public class Node<T> 
{	
	private Node<T> NEXT;	
	private T DATA;
	
	/** Constructor for the Node class. Creates a node with NEXT set to null and DATA set to the passed parameter data
	 *  
	 * @param data the parameter to set DATA to
	 */
	public Node(T data)
	{
		DATA = data;
		NEXT = null;		
	}
		
	/** Sets the value of NEXT to the passed Node<T> next.
	 *  
	 * 
	 * @param next the parameter to set NEXT to.
	 */
	public void setNext(Node<T> next)
	{
		NEXT = next;
	}
	
	/**
	 * returns the Node<T> stored in NEXT
	 * 
	 * @return the Node<T> stored in NEXT
	 */
	public Node<T> getNext()
	{
		return NEXT;
	}
	
	/**
	 * returns the actual class of generic type T stored in DATA
	 * 
	 * 
	 * @return the actual class generic type T stored in DATA
	 */
	public T getData()
	{
		return DATA;
	}

}
