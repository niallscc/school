/**
 * 
 */
package classes;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import edu.unm.cs.cs351.tdrl.f10.p1.Graph;
import edu.unm.cs.cs351.tdrl.f10.p1.GraphStructureException;

/**
 * 
 * @author nialls chavez
 * @param <T>
 *
 */
public class MyWebGraph<T> implements Graph<T>, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int edgeCounter=0;
	
	private int nodeCounter=0;
	private boolean lastAdded=false;
	int nodeID=0; 
	Map<T, Integer> IDMap;
	Map<T, Map<T, T>> graphMap;
	Map<T, Integer> inputs;
	
	
	/** The queue list. */
	LinkedList<T> queueListBFS;
	Set <T> doneBFS;
	
	/** The queue list. */
	LinkedList<T> queueListDFS;
	
	Set <T> doneDFS;
	
	public MyWebGraph() {
		
		//This is the constructor that creates the hashmaps for the structure for 
		//the rest of the program 
		//graphMap is the main data structure and edge map is the underlying datastructure 
		//that is used for storing references to the edges
		queueListBFS = new LinkedList<T>();
		doneBFS = new HashSet<T>();
		
		queueListDFS= new LinkedList<T>();
		doneDFS = new HashSet<T>();
		
		IDMap= new HashMap<T, Integer>();
		graphMap= new HashMap<T, Map<T, T>>();
		inputs= new HashMap<T, Integer>();
		
		//edgeMap= new HashMap<T, Integer>();
	}

	/**
	 * This is an auxilery function that is used in conjunction with myCrawlstate and Crawler.
	 * I know there is probably a better way to do this but this works to. every time i add a node i update 
	 * was last added and what it does is tell me if the last item that was passed in to add node was added or not 
	 * that way i know if i can update the queue in crawlstate or not.
	 * @return
	 */
	public boolean wasLastAdded(){
		
		return lastAdded;
	}
	
	
	@Override
	public void addEdge(T from, T to) throws GraphStructureException {
			
		/*
			addEdge creates an edge between two already created nodes. 
			this edge is then taken and stored in the value portion of the 
			underlying graphMap hashMap structure.
		*/

			//this checks to see if each of the nodes are already created 
			//if they are then it addes the edges, to the graphMap
			
			if(this.containsNode(from) && this.containsNode(to)){
				if( !(this.containsEdge(from, to)) ){
					graphMap.get(from).put(to, to);
					setInputs(to);

					edgeCounter++;
				}
			}
	}
	
	public int getInputs(T to){
		if( inputs.containsKey(to))
			return inputs.get(to);
		else
			return 0;
	}
	
	private void setInputs(T to){
		//System.out.println("Setting inputs\n\n");
		if(inputs.containsKey(to)){
			
			int counter=inputs.get(to).intValue();
			//System.out.println("counter: "+counter );
			inputs.put(to, ++counter);
		}
		else 
			inputs.put(to, 1);
		
	}
	/*
	 * This creates a node that can be used to create edges between 
	 * them
	 */
	@Override
	public void addNode(T urlIn) throws NullPointerException{
		try{
			//this checks to make sure that the url coming in is not empty 
			if(urlIn!=""){
				//this makes sure that the url coming in has not already been added 
				//if so it just gets ignored else it adds the node
				
				if( graphMap.containsKey(urlIn)==false){
						Map <T, T> addedNodeMap= new HashMap <T,T>();
						graphMap.put(urlIn, addedNodeMap);
						
						nodeID++;
						lastAdded=true;
						IDMap.put(urlIn, nodeID);
						
						nodeCounter++;
						
					}
				else 
					lastAdded=false;
				}
			}catch(NullPointerException e){
					System.out.println("You cannot pass in a null URL.\n");
			}		
	}

	@Override
	public boolean containsEdge(T from, T to) {
		//this checks to see if there is a an edge between two nodes.
		//System.out.println("graphMap.get="+graphMap.get(from)+"\n");
		
		if(this.containsNode(from) && this.containsNode(to)){
			if(graphMap.get(from).containsKey(to))
				return true;
			else 
				return false;
		}
		else 
			return false;
	}

	@Override
	public boolean containsNode(T urlToCheckIn) {
		//this checks to see if the graphMap contains a given node.
		//if it does it returns true else it returns false
		
		if(graphMap.containsKey(urlToCheckIn)==true )
			return true;
		else 
			return false;
	}

	@Override
	public int edgeCount() {
		//this return the number of edges between a given node 
		return edgeCounter;
	}

	@Override
	public Iterator<T> getBFSIterator(T startPoint) throws GraphStructureException {
		
		if(!containsNode(startPoint)){
			throw new GraphStructureException();
		}
		return new myBFSIterator(startPoint);
	}
	

	@Override
	public Iterator<T> getDFSIterator(T startPoint) throws GraphStructureException {
		if(!containsNode(startPoint)){
			throw new GraphStructureException();
		}
		return new myDFSIterator(startPoint);
	}

	private class myDFSIterator implements Iterator<T>{


		/** The node. */
		T node;

		/**
		 * Instantiates a new my dfs iterator.
		 * 
		 * @param key the key
		 */
		public myDFSIterator(T key){
			queueListDFS.add(key);
		}
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			if(!queueListDFS.isEmpty())
				return true;
			
			return false;
		}
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		
		@Override
		public T next() {
			node = queueListDFS.poll();
			//System.out.println("Node being pulled is: "+node);
			doneDFS.add(node);
			
			for(T loopNode: graphMap.get(node).values()){
				
				if(! doneDFS.contains(loopNode)){
					queueListDFS.addFirst(loopNode);
					
				}	
			}
			return node;
		}
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			queueListDFS.remove();
		}
		
	}
	
	public class myBFSIterator implements Iterator<T>{
		

		T node;
		int level=0;
		/**
		 * Instantiates a new my bfs iterator.
		 */
		public myBFSIterator(){
			queueListBFS = null; 
		}
			
		/**
		 * Instantiates a new my bfs iterator.
		 * 
		 * @param node the node
		 */
		public myBFSIterator(T node){
			queueListBFS.add(node);
		}
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			if(!queueListBFS.isEmpty())
				return true;
			else{
				
				level++;
				return false;
			}
		}

		public T next() {
			node = queueListBFS.poll();
			doneBFS.add(node);
			
			for(T loopNode: graphMap.get(node).values()){
				
				if(! doneBFS.contains(loopNode)){
					queueListBFS.addLast(loopNode);	
				}
			}
			return node;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		public int getLevelCount(){
			return level;
		}
		@Override
		public void remove() {
			queueListBFS.remove();
		}
		
	}
	public int getNodeID(T node) throws GraphStructureException {
		
	//if node is not in graph, throw GSE
	
	if(!containsNode(node)){
			throw new GraphStructureException();
		}
		return IDMap.get(node);
	}
	@Override
	public int neighborCount(T node) throws GraphStructureException {
		//this returns the number of neighbors between a given node
	
		return graphMap.get(node).keySet().size();
	}

	@Override
	public Set<T> neighborSet(T url) throws GraphStructureException {
		
		//Check to see if node is valid
		
		//this returns a total set of  neighbors in a graph
		return graphMap.get(url).keySet();
	}

	@Override
	public Set<T> nodeSet() {
		//this returns a total set of nodes 
		return (Set<T>) Collections.unmodifiableSet(graphMap.keySet());
		
	}

	@Override
	public int size() {
		//this returns the size of the underlying graphMap eg the number of nodes
		return nodeCounter;
	}


	public int hashCode(){
		long hc=0;
		for(T g: graphMap.keySet()){
			hc=hc+g.hashCode();
		}
		
		for(T g: graphMap.keySet()){
			for( T e: graphMap.get(g).keySet()){
				hc=hc+(graphMap.get(g).hashCode() * graphMap.get(g).hashCode())*e.hashCode();
			}
		}
			
			return (int)hc;
	}


}