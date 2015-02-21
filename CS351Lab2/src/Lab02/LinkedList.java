package Lab02;

public class LinkedList<T> implements LinkedListInterface<T>
{

	private Node<T> HEAD;
	private Node<T> TAIL;
	
	public LinkedList(T head)
	{
		HEAD = new Node<T>(head);
		TAIL = HEAD;
	}
	
	
	public LinkedList()
	{
		HEAD = null;
		TAIL = HEAD;
	}
	
	@Override
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
	
	

	@Override
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
	

	@Override
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
	
	@Override
	public T getHead()
	{
		if (HEAD == null)
		{
			return null;
		}
		
		return HEAD.getData();
	}

	@Override
	public LinkedList<T> deepCopy() {
		LinkedList<T> ll= new LinkedList<T>();
		Node<T> n = HEAD;
		while(n != null)
		{

			Node<T> temp = n.getNext();

			ll.add((T) temp);
			n = n.getNext();
			
		}
		return ll;
	}


	@Override
	public void empty() {

		if(HEAD !=null){
			HEAD=null;
			TAIL=null;
		}
	}


	@Override
	public T getAt(int index) throws IndexOutOfBoundsException {
		
		Node<T> n = HEAD;

		int counter=0;
		while(n != null)
		{
			
			if(counter==index)
				return n.getData();

			n = n.getNext();
			counter++;
		}
		return null;
	}


	@Override
	public void removeAt(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		Node<T> nHead = HEAD;
		Node<T> nTail= TAIL;
		
		if(this.getAt(0)!=null)
		{
			if(nHead!=null && nTail!=null){
				T val=this.getAt(index);
		
				this.remove(val);
			}
		}
	}


		
	
}
