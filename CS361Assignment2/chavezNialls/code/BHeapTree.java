package code;

import java.util.ArrayList;

public  class BHeapTree<T> {
	
	private ArrayList<WeightedNode<T>> myHeap;

	
	public void initialize(){
		myHeap= new ArrayList<WeightedNode<T>>();
	}
	public ArrayList<WeightedNode<T>> getHeap(){
		return myHeap;
	}
	/**
	 * Insert node takes the node to be inserted into the tree,its parent
	 * and the weight associated with this vertex. This works by first inserting the node at the bottom
	 * of the tree, then performing heapify up then heapify down on the tree to maintain "heapness"  
	 * @param node item to be inserted
	 * @param weight the weight of the new vertex between the parent and the given node. 
	 */
	public void insert(T node, int weight){
		
		WeightedNode<T> wNode= new WeightedNode<T>(node, weight);
		myHeap.add(wNode);
		int index= heapifyUp();
		heapifyDown(myHeap.get(index), index);

	}
	public WeightedNode<T> pop(){
		
		WeightedNode<T> weightedRoot= myHeap.get(0);
		int size=myHeap.size()-1;
		myHeap.set(0, myHeap.get(size));
		myHeap.remove(size);
		if(myHeap.size() !=0){
			heapifyDown(myHeap.get(0),0);
			heapifyUp();
		}
		return weightedRoot;
	}
	/**
	   algorithm for heapify down: 
	  		get root node
	  		get two children
			check to see if either of the children are less than the parent
				if so 
					switch
			recurse down the tree.
	 */

	private void heapifyDown(WeightedNode<T> node, int index) {
		
		WeightedNode<T>[]children = getChildren(node, index);
		WeightedNode<T> comparer = children[0];
		
		int i = ((index + 1)*2)-1;
		
		if(comparer == null)
			return;
		
		if(children[1] != null)
		{
			if(children[1].getWeight() > comparer.getWeight() ){
				comparer = children[1];
				i++;
			}
		}
		if(comparer.getWeight() > node.getWeight()){
			myHeap.set(index,comparer);
		myHeap.set(i, node);
		}
		heapifyDown(node,i);
	}
	/**
	 * algorithm for a minimum heap
	   get the last item in the tree
	   get its parent
	   if the child is less than its parent
	   	switch
	   else 
	   	nothin
	   currentIndex= parentIndex
	   keep going til current Index==0
	 */
	private int heapifyUp(){
		
		int size= myHeap.size();
		int currentIndex=size-1; 
		
		while(currentIndex > 0){
			
			WeightedNode<T> cNode = myHeap.get(currentIndex);
			int parentIndex = (getParent(cNode));
			
			if( parentIndex < 0)
				return currentIndex;
			
			WeightedNode<T> parent = myHeap.get(parentIndex);
			
			if( parent!=null){
				
				if( cNode.getWeight() > parent.getWeight()){
					
					myHeap.set(currentIndex, parent);
					myHeap.set(parentIndex, cNode);
					currentIndex=parentIndex;
					//System.out.println("Swappin:"+parent.getWeight()+" and "+cNode.getWeight());
				}
				else
					return currentIndex;				
			}
			
		}
		return currentIndex;
	}
	private int getParent(WeightedNode<T> node ){		
		int i =myHeap.indexOf(node);
		i++;
		if(i % 2 == 1){
			i--;
		}
		i=(i/2)-1;
		
		return i;
	}
	private WeightedNode<T>[] getChildren(WeightedNode<T> node, int index){
		@SuppressWarnings("unchecked")
		WeightedNode<T>[] children = (WeightedNode<T>[]) new WeightedNode[2];
		int i = ((index + 1)*2)-1;
		if(i <= myHeap.size()-1)
			children[0]= myHeap.get(i);
		else 
			children[0]=null;
		if(( i+1 )<= myHeap.size()-1)
			children[1]= myHeap.get(i+1);
		else children[1]=null;

		return children;
	}
	public boolean isEmpty(){
		return myHeap.isEmpty();
	}
	public int size(){
		return myHeap.size();
	}
	public boolean heapness(){
		if(myHeap.size()==0)
			return true;
		return heapness(myHeap.get(0), 0);
	}
	private boolean heapness(WeightedNode<T>t, int i){

		if(t == null)
			return true;

		WeightedNode<T>[] children = getChildren(t, i);
		boolean result = true;
		
		if(children[0] != null && children[0].getWeight() > t.getWeight())
			return false;
		if(children[1] != null && children[1].getWeight() > t.getWeight())
			return false;

		int index = ((i+ 1)*2)-1;


		return result && heapness(children[0], index++) && heapness(children[1], index);
	}
}
