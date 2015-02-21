package source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.unm.cs.cs351.f10.tdrl.p2.Findable;
import edu.unm.cs.cs351.f10.tdrl.p2.FindableQueue;

public class GrellFindableQueue<S, T extends Comparable<T> & Findable<S>> implements FindableQueue<S, T> {
	/*Globals*/
	int size=0; 
	
	ArrayList<S> keys;

	Map<S, T> todo;
	
	Map<S, Integer> indecies;
	/*End Globals*/
	/**
	 * true iff this queue contains the object item, as located via its key (Findable.getKey()).
	 */
	public GrellFindableQueue(){
		
		indecies= new HashMap<S, Integer>();
		todo= new HashMap<S, T>();
		keys= new ArrayList<S>();
	}
	@Override
	public boolean contains(T item) {
		
		return todo.containsKey( item.getKey());
	
	}
	
	
	/**
	 * Return the first item, by priority, from the queue. 
	 * This does not alter the state of the queue (i.e., does not delete anything from the queue),
	 *  merely returns a reference to the first item. Returns null if the queue is currently empty.
	 */
	@Override
	public T first() {
		
		//returns the item at the top of the queue. 
		//if its empty it will return null.
		
		if( keys.isEmpty())
			return null;
		
		size--;
		T value= todo.get(keys.get(0));
		return value;
	}
	
	/**This looks up an arbitrary object in the queue, according to its Findable.getKey() method, 
	 * and returns a reference to the object stored in the queue.
	 * Semantically, this method is similar to contains(Comparable), 
	 * in that it is equivalent to scanning through the priority queue, looking for 
	 * any queue entry that is equivalent to element, according to the Findable.getKey() method. 
	 * As with contains(Comparable),
	*/
	@Override
	public T get(T item) {
		//returns a reference to the item requested. 
		// if its empty then it returns null
		
		//runs in O(1) time because todo is a hashmap. 
		if(todo.containsKey(item.getKey()))
			return item;
		
		return null;
		//return todo.get(item.getKey());
	}
	
	//Adding in to the queue the item being passed in, eg the object. the thing being compared though is the item 
	/**
	 * Steps for completing the binary sort. 
	 * 
	 * 1: insert node at bottom of Array list. 
	 * 2. find the parent of that node
	 * 3. compare to the parent.
	 * 4. move if necessary.
	 * 5. repeat until move is not required. 
	 */	
	@Override	
	public void insert(T item) {
		//System.out.println("The item being inserted into the queue is: "+item.getKey());
		if(length()!=0){
			
			if( todo.containsKey(item.getKey())){
				
				todo.put(keys.get(indecies.get(item.getKey())), item);
				keys.set(indecies.get(item.getKey()), item.getKey());
				size++;
			
			}
			else{
				//System.out.println("Keys inside of insert"+item);
				//Step 1
				todo.put(item.getKey(), item);
				keys.add(item.getKey());
				indecies.put(item.getKey(), length());
				size++;

				
				T parent= getParent(item);
				
				//System.out.println("node inserted: "+ item.getKey()+" Parent is: "+parent.getKey());
				
				//System.out.println("Comparing: "+ item.getKey()+ " to "+parent.getKey()+ " result : "+item.compareTo(parent));
				while(item.compareTo(parent) == -1 ){
					//System.out.println("Comparing: "+ item.getKey()+ " to "+parent.getKey()+ " result : "+item.compareTo(parent));
					
					switcheroo(item.getKey(), parent.getKey());
					
					//parent=item;
					//item=parent;
					parent=(getParent(item) );
					
					//System.out.println("Child is now:"+item.getKey()+" parent is now "+parent.getKey());
					
					checkChildren(parent);
					
					//System.out.println("Comparing(at end): "+ item.getKey()+ " to "+parent.getKey()+ " result : "+item.compareTo(parent));
				}
				
				//System.out.println("Comparing: "+ item.getKey()+ " to "+parent.getKey()+ " result : "+item.compareTo(parent));
				//System.out.println("Child is now:"+item.getKey()+" parent is now "+parent.getKey());
				
				checkChildren(parent);
				
				
				///end main while loop 
				/*
				System.out.println("Length("+length()+") In queue: ");
				for( int q=0; q<length(); q++){
					
					System.out.println("   "+keys.get(q)+"["+indecies.get(keys.get(q))+"] ");
				
				}*/
				
				//System.out.println();
				//once this loop is completed then the queue is sorted. 
	

			}
		}
		/*
		 * If the queue is empty insert the node at the top of the queue. 
		 */
		else {
			//System.out.println("first inserted is:(In findablequeue) "+item.getKey());
			
			todo.put(item.getKey(), item);
			keys.add(item.getKey());
			
			//System.out.println("Number of keys: "+keys.size());
			indecies.put(item.getKey(), 0);
			size++;
			//System.out.println("Length ="+ length());
		}
			
		
	}
	/**
	 * returns true or false depending on if the queue is empty or not. 
	 */
	@Override
	public boolean isEmpty() {
		//checks to see if there is anything in the queue 
		
		if( todo.isEmpty())
			return true;
		
		return false;
	}
	
	@Override
	public int length() {
		//returns the number of items in the queue.
		return size;
	}
	
	/**
	 * Remove and return the first item, by priority, from the queue. 
	 * Unlike first(), this does change the state of the queue, by removing the 
	 * first element from the queue and decreasing the queue length by one. Returns 
	 * null if the queue is currently empty.
	 */
	@Override
	public T pop() {
		size=keys.size();
		/*
		 * Steps to remove an item from the prioritized queue. 
		 * Step 1. get the last item in the queue.
		 * Step 2. get the first item in the queue and set it into a variable for returning. 
		 * Step 3. replace the first item with the last item.
		 * Step 4. percolte down. 
		 */
		T nodeToRemove=null;
		
		if(!keys.isEmpty())
		{	
			nodeToRemove= todo.get(keys.get(0));
			//System.out.println("Value to remove is is :"+todo.toString());
		}
		else {
			System.err.println("NO keys");
		}
		if(keys.contains(nodeToRemove.getKey())){
			//System.out.println("Keys contains it.");
			//if there is only one element remove the node from the hashmaps, and the array list and return 
			if(length()==1){
				//System.out.println("Only one thing to remove.");
				//System.out.println("nodeToRemove: "+nodeToRemove);
				keys.remove(0);
				todo.remove(nodeToRemove.getKey());
				indecies.remove(nodeToRemove.getKey());
				size=0;
				
				//System.out.println("does keys still have it? "+keys.contains(nodeToRemove.getKey()));
				//System.out.println("does it todo have it? "+todo.containsKey(nodeToRemove.getKey()));
				//System.out.println("does it indecies have it? "+indecies.containsKey(nodeToRemove.getKey()));
				
				return nodeToRemove;
			}
			//Step1: get replacement
			S nodeToRemoveKey= nodeToRemove.getKey();
			
			
			int tempSize=length();
			
			S replacement =keys.get(--tempSize);
			
			//Step2: replace 
			int index= keys.indexOf(nodeToRemoveKey);
			keys.set(index, replacement);
			todo.remove(nodeToRemoveKey);
			
			//remove the replacement
			keys.remove(tempSize);
			
			--size;
			
			while(getNumChildren(replacement)!=0 ){
				
				if(getNumChildren(replacement)==2){
					int tempParentIndex=keys.indexOf(replacement);
					
					T child1=todo.get(keys.get(tempParentIndex*2+1));
					T child2=todo.get(keys.get(tempParentIndex*2+2));
					
					if(child1.compareTo(child2) == 1){
						
						S tempNode= keys.get(tempParentIndex);
						
						keys.set(tempParentIndex, keys.get(tempParentIndex*2+1));
						keys.set(tempParentIndex*2+1, tempNode);
						
						replacement= keys.get(tempParentIndex*2+1);
					}
					else
					{
						
						S tempNode= keys.get(tempParentIndex);
						
						keys.set(tempParentIndex, keys.get(tempParentIndex*2+2));
						keys.set(tempParentIndex*2+2, tempNode);
						
						replacement= keys.get(tempParentIndex*2+2);
					}
				}
				else if(getNumChildren(replacement)==1){
						
					T parent= todo.get(replacement);
					T child=todo.get(keys.get(keys.indexOf(replacement)*2+1));
		
					
					if(parent.compareTo(child) ==1){
						//System.out.println("Switch");
						int tempParentIndex=keys.indexOf(replacement);
						
						S tempNode= keys.get(tempParentIndex);
						
						keys.set(tempParentIndex, keys.get(tempParentIndex*2+1));
						keys.set(tempParentIndex*2+1, tempNode);
						
						replacement= keys.get(tempParentIndex*2+1);
					}
					
				}

			}
			
			return todo.get(replacement);
		}
		
		return null;
	}

	
	/**
	 * Remove an element by reference. This uses the Findable.getKey() method to locate the
	 * element in the queue and then removes it from the queue, maintaining priority order 
	 * among all other elements of the queue. It returns a reference to the removed item 
	 * (i.e., the item that was actually found in the queue, not the argument to this method). 
	 * If the requested element is not currently in the queue, this returns null without modifying 
	 * the queue.
	 */
	@Override
	public T remove(T nodeToRemove) {
		//Steps to remove an item at a given index. 
		/*
		 * 1. get the last element in the array
		 * 2. replace it with the node that you want to remove
		 * 3. percolate down. 
		 * 4. repeat until node does not need to be moved
		 *
		 */	
		
		//if the node is actually in the arraylist.
		if(keys.contains(nodeToRemove.getKey())){
			
			//if there is only one element remove the node from the hashmaps, and the array list and return 
			
			//if(length()==1 || keys.indexOf(nodeToRemove.getKey())==keys.size()-1){
			if(length()==1 ||indecies.get(nodeToRemove.getKey())==keys.size()-1){
				keys.remove(nodeToRemove.getKey());
				todo.remove(nodeToRemove.getKey());
				size--;
				
				return nodeToRemove;
			}
			//Step1: get replacement
			S nodeToRemoveKey= nodeToRemove.getKey();
			int tempSize=length();
			S replacement =keys.get(--tempSize);
			
			//Step2: replace 
			int index= keys.indexOf(nodeToRemoveKey);
			keys.set(index, replacement);
			//System.out.println("key of node to remove "+ nodeToRemoveKey);
			
			//keys.set(indecies.get(nodeToRemoveKey), replacement);
			todo.remove(nodeToRemoveKey);
			
			//remove the replacement
			keys.remove(tempSize);
			
			--size;
			
			while(getNumChildren(replacement)!=0 ){
				
				if(getNumChildren(replacement)==2){
					//int tempParentIndex=keys.indexOf(replacement);
					int tempParentIndex=indecies.get(replacement);
					T child1=todo.get(keys.get(tempParentIndex*2+1));
					T child2=todo.get(keys.get(tempParentIndex*2+2));
					
					if(child1.compareTo(child2) == 1){
						
						S tempNode= keys.get(tempParentIndex);
						
						keys.set(tempParentIndex, keys.get(tempParentIndex*2+1));
						keys.set(tempParentIndex*2+1, tempNode);
						
						replacement= keys.get(tempParentIndex*2+1);
					}
					else
					{
						
						S tempNode= keys.get(tempParentIndex);
						
						keys.set(tempParentIndex, keys.get(tempParentIndex*2+2));
						keys.set(tempParentIndex*2+2, tempNode);
						
						replacement= keys.get(tempParentIndex*2+2);
					}
				}
				else if(getNumChildren(replacement)==1){
						
					T parent= todo.get(replacement);
					//T child=todo.get(keys.get(keys.indexOf(replacement)*2+1));
					T child=todo.get(keys.get(indecies.get(replacement)*2+1));
					
					if(parent.compareTo(child) ==1){
						//System.out.println("Switch");
						//int tempParentIndex=keys.indexOf(replacement);
						int tempParentIndex=indecies.get(replacement);
						
						S tempNode= keys.get(tempParentIndex);
						
						keys.set(tempParentIndex, keys.get(tempParentIndex*2+1));
						keys.set(tempParentIndex*2+1, tempNode);
						
						replacement= keys.get(tempParentIndex*2+1);
					}
					
				}

			}
			
			return todo.get(replacement);
		}
		
		return null;
	}
	
	/**
	 * 
	 * This is an auxilery method that returns the number of children a given node has. It works because each 
	 * node will have at most 2 children. This means that when the size minus the index it is at equals 0, 
	 * it is the last node, when its 1, it has only 1 child, else it has 2.
	 * @param node the item whose number of children we need to determine. 
	 * @return the number of children 
	 * 
	 */
	
	private void checkChildren(T parentIn){
		
		//System.out.println(parent.getKey()+" has "+getNumChildren(parent.getKey())+" children");
		if( getNumChildren(parentIn.getKey())==2){
			
			T child1= todo.get( keys.get(indecies.get(parentIn.getKey() )*2 + 1));
			T child2= todo.get( keys.get(indecies.get(parentIn.getKey() )*2 + 2));
			
			if( child1.compareTo(child2)==1){	
				
				switcheroo(child1.getKey(), child2.getKey());
				
			}
			
		}
		
	}
	
	private void switcheroo(S node1, S node2){
		
		S tempNode=keys.get(indecies.get(node2));
		int tempIndex= indecies.get(node1);
		
		keys.set(indecies.get(node2), node1);
		keys.set(indecies.get(node1), tempNode);
		
		indecies.put(node1, indecies.get(node2));
		indecies.put(node2, tempIndex);
		
		
	}
	
	private int getNumChildren(S node){
		
		int placement= indecies.get(node);
		//int placement= keys.indexOf(node);
		
		if((placement*2+2) < length() )
		{
			//System.out.println("parent at: "+placement+"children at ("+keys.get(placement*2+2) +", "+ (keys.get(placement*2+1)) +") size is"+length());
			return 2;
		}
		else if( (placement*2 +1)< length() ){
			//System.out.println("parent at: "+placement+"child at "+ (keys.get(placement*2+1)) +", size is"+length());
			return 1;
		}
		else{
			//System.out.println("parent at "+placement+"has no children");
			return 0;
		}
			
		
	}
	
	private T getParent(T node){
		if( indecies.get(node.getKey())!=0){
		
			if( 2 % indecies.get(node.getKey()) == 0)
				return(todo.get(keys.get( ( indecies.get( node.getKey() ) - 2) / 2 ) ));
		
			else 
				return(todo.get(keys.get( ( indecies.get( node.getKey() ) - 1) / 2 ) ));
		}
		else
			return todo.get(keys.get(0));
		
	}
	 
}
