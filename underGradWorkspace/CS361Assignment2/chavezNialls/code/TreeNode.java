package code;

import java.util.HashSet;

public class TreeNode<T>{
	private T parent=null;
	private HashSet<T> nodes = new HashSet<T>();
	private int depth=0;
	public TreeNode(HashSet<T> nodesIn,int depthIn, T parentIn){
		nodes= nodesIn;
		depth=depthIn;
		parent=parentIn;
	}
	public HashSet<T> getNodes(){
		return nodes;
	}
	public int getDepth(){
		return depth;
	}
	public T getParent(){
		return parent;
	}
	public boolean contains(TreeNode<T> branch){
		return nodes.contains(branch);
	}
}
