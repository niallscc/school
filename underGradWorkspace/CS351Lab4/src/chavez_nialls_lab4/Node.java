package chavez_nialls_lab4;
import java.util.HashSet;


public class Node<T> 
{
	private T DATA;
	private HashSet<Node<T>> Children;
	
	public Node(T data)
	{
		DATA = data;
		Children = new HashSet<Node<T>>();
	}
	
	
	public T getData()
	{
		return DATA;
	}
	
	public HashSet<Node<T>> getChildren()
	{
		return Children;
	}
	
	public void addChild(Node<T> node)
	{
		Children.add(node);
	}
	
	public String toString()
	{
		return DATA.toString();
	}
	
	public boolean equals(Object obj)
	{
		return DATA.equals(((Node<T>) obj).getData());
	}
	
}
