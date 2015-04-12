package chavez_nialls;

public class LinkedList<T> implements LinkedListInterface<T>
{

	private Node<T> HEAD;
	private Node<T> TAIL;
	
	public LinkedList(T head)
	{
		HEAD = new Node<T>(head);
		TAIL = HEAD;
	}
	
	/**
	 * Creates a new empty list if the list is not empty. Throws an exception if the list is already empty.
	 *  
	 * @throws ListEmptyException
	 */
	public void newList() throws ListEmptyException
	{
		if (HEAD == null)
		{
			throw new ListEmptyException("List not empty");
		}
		else 
		{
			HEAD = null;
			TAIL = HEAD;
		}		
	}
	
	/**
	 * Adds a Node at the end of the Linked List.
	 * 
	 * 
	 * 
	 * @param node
	 */
	public void add(T node)
	{
		if (HEAD == null)
		{
			Node<T> temp = new Node<T>(node);
			HEAD = temp;
			TAIL = temp;
		}
		else
		{
			Node<T> temp = new Node<T>(node);
			TAIL.setNext(temp);
			TAIL = temp;
		}
	}
	
	
	/**
	 * 
	 * Iterates across the list checking if the passed element is in the linked list. 
	 * The check is done with the generic object's equals method.
	 * 
	 * @param node the node to be located in the list
	 * @return	returns true if the node is in the list, false otherwise
	 * 
	 */
	
	public boolean contains(T node)
	{
		Node<T> n = HEAD;
		
		while(n != null)
		{
			if (n.getData().equals(node))
			{
				return true;				
			}
			
			n = n.getNext();
			
		}
		
		return false;
	}
	
	
	/**
	 * Removes the specified node from the Linked List.
	 * 
	 * @param node the node to be removed
	 */
	
	public void remove(T node)
	{
		Node<T> n = HEAD;
		Node<T> prev = null;
		
		while(n != null)
		{
			if (n.getData().equals(node))
			{		
				
				Node<T> temp = n.getNext();
				
				if (n == HEAD)
				{
					HEAD = temp;
				}	
				else
				{					
					prev.setNext(temp);					
				}
							
				
				break;				
			}
			
			prev = n;
			n = n.getNext();
			
		}
				
	}
	
	public T getHead()
	{
		if (HEAD == null)
		{
			return null;
		}
		
		return HEAD.getData();
	}
	
	
	public static void main(String args[])
	{
		LinkedList<Integer> LL = new LinkedList<Integer>(new Integer(1));
		
		LL.remove(1);
		LL.getHead();
		
		LL.add(new Integer(2));
		LL.add(new Integer(3));
		LL.add(new Integer(4));
		LL.add(new Integer(5));
		LL.add(new Integer(6));
		LL.add(new Integer(7));
		LL.add(new Integer(8));
		LL.add(new Integer(9));
		
		System.out.println("1 :" + LL.contains(1));
		System.out.println("2 :" + LL.contains(2));
		System.out.println("3 :" + LL.contains(3));
		System.out.println("4 :" + LL.contains(4));
		System.out.println("5 :" + LL.contains(5));
		System.out.println("10 :" + LL.contains(10));
		
		LL.remove(1);
		LL.remove(2);
		LL.remove(6);
		LL.remove(9);
		
		System.out.println("--------------");
		
		System.out.println("1 :" + LL.contains(1));
		System.out.println("2 :" + LL.contains(2));
		System.out.println("3 :" + LL.contains(3));
		System.out.println("4 :" + LL.contains(4));
		System.out.println("5 :" + LL.contains(5));
		System.out.println("6 :" + LL.contains(6));
		System.out.println("8 :" + LL.contains(8));
		System.out.println("9 :" + LL.contains(9));
		System.out.println("10 :" + LL.contains(10));
		
	}
	
}

class ListEmptyException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListEmptyException(String msg){
	      super(msg);
	    }

	    public ListEmptyException(String msg, Throwable t){
	      super(msg,t);
	    } 
	
}
