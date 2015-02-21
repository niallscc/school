package code;

import java.util.HashSet;

import graphs.NonEdgeException;
import graphs.StressTester;
import graphs.WeightedGraph;

public class MyStressTester implements StressTester{
	
	/**
	 * Given: start node ,finish node, and graph- can be infinte  
	 * Problem: The highest weight that is possible to traverse from point s to point t
	 * Algorithm: Start at finish node: take the highest weight, set it to the max weight. 
	 *            take its children put the most recently added node at the root then re-heapify 
	 *            continue til you reach the start node. 
	 */
	boolean flag= true;
	int currentMax;
	
	@Override
	public <Node> int maxLoad(WeightedGraph<Node> g, Node s, Node t) {
		currentMax=Integer.MAX_VALUE;
		BHeapTree<Node> heapTree= new BHeapTree<Node>();
		heapTree.initialize();
		heapTree.insert(s,Integer.MAX_VALUE);
		
		RootedTree<Node>rootedTree= new RootedTree<Node>();
		rootedTree.initialize(s);
		
		HashSet<Node> discovered= new HashSet<Node>();
		while(heapTree.size() > 0) {
			//System.out.println("size of heapTree in main: "+heapTree.size());
			boolean result= doThings(g, heapTree, t, s, rootedTree, discovered);
			if (result)
				return currentMax;
		}
		return currentMax;		
	}
	public <Node> boolean doThings(WeightedGraph<Node> g, BHeapTree<Node> heapTree, Node t, Node s, RootedTree<Node> rootedTree, HashSet<Node> discovered){
		 WeightedNode<Node> root= heapTree.pop();
		 if (!heapTree.heapness()){
			 flag= false;
			 //System.out.println(" Heapness: "+ heapTree.heapness());
		 }
		 //System.out.println("Parent at time of pop is: "+ root.getNode()+" its weight is: "+ root.getWeight());
		 if( discovered.contains(root.getNode())){
			 //System.out.println("Already contains "+ root);
			 return false;
		 }
		 discovered.add(root.getNode()); 
		 if( root.getWeight() < currentMax){
			 //System.out.println("Changing currentMax");
			 currentMax= root.getWeight();
		 }
		 if( root.getNode().equals(t)){
			 //System.out.println("Found finish");
			 return true;
		 }
		 if(rootedTree.getDepth(root.getNode()) > 10000 ){
			 //System.out.println("Bigger than 10000");
			 return false;  
		 }
		 else{
			 Node parent= root.getNode();
			 rootedTree.insertChildren(g.neighbors(parent), parent);
			 for( Node child: g.neighbors(parent)){
				try {
					heapTree.insert(child, g.getWeight(parent, child));
					 //System.out.println("The weight is: "+g.getWeight(parent, child));
				} catch (NonEdgeException e) {
					e.printStackTrace();
				}
			 }
			 
			 return false;
		 }	 
	 }
}