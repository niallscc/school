package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/*
BFS (s) :
Set Discovered[s] = true and Discovered[v] = false for all other v
Initialize L[0] to consist of the single element s
Set the layer counter i=0
Set the current BFS tree T=0
While L[i] is not empty
	Initialize an empty list L[i+1] 
	For each node u Belonging to L[i]
		Consider each edge (u, v) incident to u 
		If Discovered[u] = false then
			Set Discovered[u] = true
			Add edge (u,v) to the tree T
			Add v to the list L[i+ I] 
		Endif
	Endfor
	Increment the layer counter i by one 
Endwhile
*/
public class RootedTree<T> {
	
	private ArrayList<TreeNode<T>> tree= null;
	
	public void initialize(T node){
		tree= new ArrayList<TreeNode<T>>();
		HashSet<T> root= new HashSet<T>();
		root.add(node);
		TreeNode<T> rootNode= new TreeNode<T>(root, 1, null);
		tree.add(rootNode);
	
	}
	public void insertChildren( Set<T> childrenIn, T parent){
		
		HashSet<T> childrens=new HashSet<T>();
		for(T children: childrenIn){
			childrens.add(children);
		}
		TreeNode<T> layer= new TreeNode<T>(childrens,tree.get(tree.size()-1).getDepth()+1,parent);
		tree.add(layer);
	
	}
	public int getDepth(T node){
		for(TreeNode<T> branch: tree){
			if( branch.getNodes().contains(node))
				return branch.getDepth();
		}
		System.out.println("Couldnt find the node");
		
		return 10001;
	}

	public boolean isBottom(T node){
		if( tree.get(tree.size()-1).getNodes().contains(node)){
				return true;
		}
		return false;
	}
}
