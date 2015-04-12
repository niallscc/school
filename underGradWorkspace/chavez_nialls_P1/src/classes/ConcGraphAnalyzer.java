package classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import edu.unm.cs.cs351.tdrl.f10.p1.GraphAnalyzer;
import edu.unm.cs.cs351.tdrl.f10.p1.GraphStructureException;

public class ConcGraphAnalyzer<T> implements GraphAnalyzer<T> {

	/**
	 * @param args
	 */
	//Objects
	MyCrawlState cs;
	
	//Strings
	String filenameIn;
	
	//Integers
	int size;
	int maxSCC=0;
	int[][] SPArray; //shortest path array 
	ArrayList<String> list; //an array with the node set that was taken from nodeSet
	
	/**
	 * This constructor creates a new Crawlstate and also initializes the ArrayList(list) with the set of nodes, taken from  cs.getGraph()
	 */
	public ConcGraphAnalyzer(){
		cs= new MyCrawlState();
		
	}

	/** 
	 * This method calls the loader inside of crawlState, I do this first, that way I know that webGraph,
	 * and crawlstate are all ready to be parsed!
	 * @param filename the name of the file to be loaded
	 * @throws IOException 
	 * @throws ClassNotFoundException
	 */
	public void loader( String filename) throws IOException, ClassNotFoundException{
		if( filename==null){
			/*
			 * default file name set to test.wgr
			 */
			filename="test.wgr";
		}
		
		System.out.print("Loading objects in from "+filename+".....");
		
		cs.loadYourself(filename);	
		//the size of the graph is set initially that way I dont have to compute it all the time.
		setter();
		
		System.out.println("Done!");
	}
	
	/**
	 * this is a private setter class that is used to set in the node set size that way I only have to compute this number once. 
	 * that is unless the graph is changed. 
	 */
	private void setter(){
		
		size=cs.getGraph().nodeSet().size();
		
	}

	/**
	 *  This uses an all-pairs shortest paths algorithm ( Floyd-Warshall) to compute the shortest directed distance between
	 *  every two nodes in the graph. Note: this does not return the shortest paths themselves; it merely computes the distances 
	 *  that those paths require.
	 */

	@Override
	public int[][] allPairsShortestPaths() {
		Queue<String> neighborList = new LinkedList<String>();
		list = new ArrayList<String>(cs.getGraph().nodeSet());
		SPArray = new int[size][size];
		
		
		if( cs.getGraph().nodeSet().isEmpty()){
			System.err.println("Empty Graph, inside APSP");
			return new int[0][0];
		}
		//fill the array with Max integers
		//System.out.println("Initial fill.");
		for(int i=0;i < size;i++)
		{
			for(int j=0;j < size;j++)
			{
				if(i == j)
					SPArray[i][j] = 0;
				
				else
					SPArray[i][j] = 9999999;
			
				//System.out.print(SPArray[i][j]+" ");
			}
			//System.out.println();
				
		}
		//Fill the matrix with 1's if there is single connectivity.
		//System.out.println("Now with one's if there is single connectivity.");
		
		for(String node:list){
			
			try {
					neighborList.addAll(cs.getGraph().neighborSet(node));
					
					while(!neighborList.isEmpty()){
						String neighbor = neighborList.poll();
						
						int root=(cs.getGraph().getNodeID(node)-1);
						int dest=(cs.getGraph().getNodeID(neighbor)-1);
						
						//System.out.println("Root:"+root+"dest:"+dest);
						if(SPArray[root][dest] != 0){
							if(cs.getGraph().containsEdge(node, neighbor) )
								SPArray[(cs.getGraph().getNodeID(node)-1)][(cs.getGraph().getNodeID(neighbor)-1)]=1;
						}

					}
				}
			 catch (GraphStructureException e) {
				e.printStackTrace();
			}
		}
		
		//fill the matrix using floyd Warshall algorithm 
		//System.out.println("Floyd Warshall:");
		
		for(int k = 0; k < size; k++){

			for(int i = 0; i < size;i++){
				
				for(int j = 0; j < size; j++){
					
					SPArray[i][j]=Math.min(SPArray[i][j], (SPArray[i][k]+SPArray[k][j]));
				}
				//System.out.println();
				
			}
					
		}
		
		int tempCounter=0;
		for(int i = 0; i < size;i++){
			
			for(int j = 0; j < size; j++){
				
				if(SPArray[i][j]!=9999999){
					//System.out.print("["+i+"]["+j+"]:"+SPArray[i][j]);
					if( SPArray[i][j] > maxSCC) {
						maxSCC=SPArray[i][j];
						tempCounter++;
					}
				}
				if(tempCounter==5){
					//System.out.println();
				}
					
			}
			//System.out.println();
		}
				
		return SPArray;
	}
	
	
	/**
	 * This method calculated the diameter of the graph.The diameter is the farthest node from the starting position.
	 * @throws GraphStructureException 
	 */
	
	@Override
	public int diameter() {
		list = new ArrayList<String>(cs.getGraph().nodeSet());
		
		int depthCount = 0;
		int diameter = 0;
		
		
		Iterator<String> it=null;
		

		if(cs.getGraph().nodeSet().isEmpty())
		{
			System.err.println("Empty Graph, inside Diameter");
			return 0;
		}
		
		try {
			it= cs.getGraph().getDFSIterator(cs.getInitialPage()/* "http://www.cs.unm.edu/"*/);
		} catch (GraphStructureException e1) {
			System.err.println("Graph structure exception thrown. ");
			e1.printStackTrace();
		}
		for(String node:list)
		{
			//System.out.println("inside list");
			if(cs.getInputs(node) == 0){
				try {
					if(cs.getGraph().neighborCount(node) > 0){

							while(it.hasNext()){
								
								depthCount++;
								
								node = it.next();
								//System.out.println("Node is:"+node);
						}
						if(diameter < depthCount){
							diameter = depthCount;
						}
					}
				} catch (GraphStructureException e) {
					System.out.println("GraphStructureException thrown in Diameter");
					e.printStackTrace();
				}
			}
		}
		return diameter;
	
	}
	
	/**
	 * avgShortestPath takes the already calculated shortest path array and gets the average of all of them 
	 * this is a very simple formula
	 * but this does not work if the APSP has not been called. 
	 */
	@Override
	public double avgShortestPathDistance() {
		int pathNums=0;
		double connectivity=0;
		if(cs.getGraph().nodeSet().isEmpty())
		{
			System.err.println("Null graph.... exiting");
			return 0.0;
		}
		for(int i = 0;i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(SPArray[i][j] != 9)
				{
					pathNums += SPArray[i][j];
					connectivity++;
				}
			}
		}
		return pathNums/connectivity;

	}
	
	/**
	 * This method calculates the number of SCC's(strongly connected components) 
	 * basically this is a check to see if there is a pointer from (i to j) and (j to i)
	 */
	@Override
	public int countSCCs() {
		
		int sccCounter=0;
		java.util.Iterator<String> i = null;
		
		
		if(cs.getGraph().nodeSet().isEmpty()){
			System.err.println("Node set is empty.. exiting.");
			return 0;
		}
		else{
			
			 try {
				 i= cs.getGraph().getBFSIterator(cs.getInitialPage());
			} catch (GraphStructureException e) {
				System.out.println("GraphStructure exception thrown from iterator in countSCCS");
				e.printStackTrace();
			}
			
			while(i.hasNext()){
				String root= i.next();
				//("root is:"+root);
				try {
					
					if(i.hasNext() && isSCC(root, i.next())){
						
						sccCounter++;
						//System.out.println("in counter incrementer:"+counter);
					}
				} catch (GraphStructureException e) {
					System.err.println("Graph Structure exception thrown from countSCCs");
					e.printStackTrace();
				}
			}
		}
		return sccCounter;
	}
	
	/**
	 * this is a helper method that gets passed in the root and the destination we are going to and checks to see if they are a strongly connected
	 * component. this is very tricky because a scc is defined not only as something that is from i to j but it can be from i to j to k to l to j
	 * @param root this is the start node 
	 * @param dest this is the node to check against 
	 * @return returns true or false whether or not the root and the dest is SC
	 * @throws GraphStructureException thrown if there is something funky happening in the graph
	 */
	public boolean isSCC(String root, Object dest) throws GraphStructureException{
		
		
		boolean rootToDest = false;
		boolean destToRoot = false;
		
		ArrayList<String> open= new ArrayList<String>();
		HashSet<String> closed= new HashSet<String>();
		
		open.add(root);
		
		// Check to see if root is connected to dest
		while(!open.isEmpty()){
			
			String tempVar= open.remove(0);
			//System.out.println("tempVar(rootToDest): "+tempVar);
			if(cs.getGraph().neighborSet(tempVar).contains(dest)){
				
				rootToDest=true;
				break;
			}
			if(!closed.contains(tempVar)){
				
				closed.add(tempVar);
				open.addAll(cs.getGraph().neighborSet(tempVar));
			}
			
			
		}
		open.clear();
		//closed.clear();
		
		open.add((String) dest);
		
		// Check to see if dest is connected to root
		while(!open.isEmpty()){
			
			String tempVar= open.remove(0);
			//System.out.println("tempVar(destToRoot): "+tempVar);
			if(cs.getGraph().neighborSet(tempVar).contains(dest)){
				destToRoot=true;
				break;
			}
			if(!closed.contains(tempVar)){
				
				closed.add(tempVar);
				open.addAll(cs.getGraph().neighborSet(tempVar));
			}
			
		}
		open.clear();
		closed.clear();
		return rootToDest && destToRoot;
	}

	
	/*
	 * (non-Javadoc)
	 * @see edu.unm.cs.cs351.tdrl.f10.p1.GraphAnalyzer#maxSCCSize()
	 */
	@Override
	public int maxSCCSize() {
		if(SPArray.length<0){
			System.out.println("you have not yet called the APSP... probably");
			return 0;
		}
		else{
			int tempCounter=0;
			for(int i = 0; i < size;i++){
				
				for(int j = 0; j < size; j++){
					
					if(SPArray[i][j]!=9999999){
						//System.out.print("["+i+"]["+j+"]:"+SPArray[i][j]);
						if( SPArray[i][j] > maxSCC) {
							maxSCC=SPArray[i][j];
							tempCounter++;
						}
					}
					if(tempCounter==5){
						//System.out.println();
					}
						
				}
				//System.out.println();
			}
			return maxSCC;
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see edu.unm.cs.cs351.tdrl.f10.p1.GraphAnalyzer#inDegreeDistribution()
	 */
	/**
	 * 
	 * calculates the distribution of indegrees throughout the graph. 
	 * 
	 */
	@Override
	public double[] inDegreeDistribution() {
		HashMap<Integer,Integer> findPMF = new HashMap<Integer,Integer>();
		double[] pmf = null;
		int numInputs = 0;
		int arrIndexAdd = 0;
		int i = 0;
		int index = 0;
		int numNodes = 0;
		
		if(cs.getGraph().nodeSet().isEmpty()){

			return  new double[0];
			
		}
		for(String node:cs.getGraph().nodeSet())
		{
			numInputs = cs.getInputs(node);
			if(findPMF.containsKey(numInputs))
			{
				numNodes = findPMF.get(numInputs);
				numNodes++;
				findPMF.put(numInputs, numNodes);
			}
			else
			{
				index++;
				numNodes++;
				findPMF.put(numInputs, numNodes);
			}
		}
		pmf = new double[index];
		for(i=0;i<cs.getGraph().nodeSet().size();i++)
		{
			if(findPMF.containsKey(i)){
				pmf[arrIndexAdd] = (findPMF.get(i)*1.0)/cs.getGraph().nodeSet().size();
				arrIndexAdd++;
			}
		}
		return pmf;
	}
	
	/**
	 * calculates the distribution of outdegrees throughout the graph 
	 */
	/*
	 * (non-Javadoc)
	 * @see edu.unm.cs.cs351.tdrl.f10.p1.GraphAnalyzer#outDegreeDistribution()
	 */
	@Override
	public double[] outDegreeDistribution() {
		HashMap<Integer, Integer> findPMF = new HashMap<Integer, Integer>();
		double[] pmf = null;
		int numNeighbors = 0;
		int arrIndexAdd = 0;
		int i = 0;
		int index = 0;
		int numNodes = 0;
		
		if(cs.getGraph().nodeSet().isEmpty()){
			System.err.println("Node set is empty.. exiting.");
			//System.exit(1);
			return new double[0];
			
		}
		for(String node:cs.getGraph().nodeSet())
		{
			try {
				numNeighbors =cs.getGraph().neighborCount(node);
			} catch (GraphStructureException e) {
				System.err.println("GraphStructureException inside of outDegreeDistribution");
				e.printStackTrace();
			}
			
			if(findPMF.containsKey(numNeighbors))
			{
				numNodes = findPMF.get(numNeighbors);
				numNodes++;
				findPMF.put(numNeighbors, numNodes);
			}
			else
			{
				index++;
				numNodes++;
				findPMF.put(numNeighbors, numNodes);
			}
		}
		pmf = new double[index];
		for(i=0;i<cs.getGraph().nodeSet().size();i++)
		{
			if(findPMF.containsKey(i))
			{
				pmf[arrIndexAdd] = (findPMF.get(i)*1.0)/cs.getGraph().nodeSet().size();
				arrIndexAdd++;
			}
		}
		return pmf;
	}

	/**
	 * returns the biggest number of in degrees found.
	 */
	/*
	 * (non-Javadoc)
	 * @see edu.unm.cs.cs351.tdrl.f10.p1.GraphAnalyzer#maxInDegree()
	 */
	@Override
	public int maxInDegree() {
		int inputs = 0;
		int inDegreeMax=0;
		
		//error checking
		if(cs.getGraph().nodeSet().isEmpty())
		{
			System.out.println("UH-HO, you dont have any nodes.... exiting....sorry...");
			System.exit(1);
		}

		for( String node: cs.getGraph().nodeSet())
		{
			inputs = cs.getInputs(node);
			if(inDegreeMax < inputs)
			{
				inDegreeMax = inputs;
			}
		}
		return inDegreeMax;
	}

	/**
	 * returns the biggest number of out degrees found.
	 */
	/*
	 * (non-Javadoc)
	 * @see edu.unm.cs.cs351.tdrl.f10.p1.GraphAnalyzer#maxOutDegree()
	 */
	@Override
	public int maxOutDegree() {
		int numNeighbors = 0;
		int outDegreeMax=0;
		//error checking
		if(cs.getGraph().nodeSet().isEmpty())
		{
			System.out.println("UH-HO, you dont have any nodes.... exiting....sorry...");
			return 0;
		}
		for(String node: cs.getGraph().nodeSet())
		{
			//System.out.println("nodes"+node);
			try {
				numNeighbors = cs.getGraph().neighborCount(node);
	
			} catch (GraphStructureException e) {
				
				System.err.println("GraphStructureException thrown... ");
				e.printStackTrace();
			}
			//calculate Max
			if(outDegreeMax < numNeighbors)
			{
				outDegreeMax = numNeighbors;
			}
		}
		//System.out.println("max "+outDegreeMax);
		return outDegreeMax;
	}


	/*
	 * (non-Javadoc)
	 * @see edu.unm.cs.cs351.tdrl.f10.p1.GraphAnalyzer#minInDegree()
	 */
	/**
	 * figues out the smallest number of indegrees
	 */
	@Override
	public int minInDegree() {
		//needs to be set really high so it gets set to an actual relevant value
	    int	inDegreeMin=1000;
		int inputs=0;
		
		if(cs.getGraph().nodeSet().isEmpty())
		{
			System.err.println("There are no nodes loaded. exiting. ");
			return 0;
		}
		
		for(String node:cs.getGraph().nodeSet())
		{
			inputs= cs.getInputs(node);
			//System.out.println("the number of inputs for "+node+" is: "+inputs);
			if(inDegreeMin > inputs) 
			{
				inDegreeMin = inputs;
			}		
					
		}
		return inDegreeMin;
	}

	/**
	 * returns the smalles out degree 
	 */
	@Override
	public int minOutDegree() {
		//outDegreeMin set to an obscenely high number so that on the first time it checks, it initializes the 
		//actual outDegreeMin value;
		int numNeighbors=0, outDegreeMin=10000;
		
		//error checking
		if(cs.getGraph().nodeSet().isEmpty())
		{
			System.out.println("UH-HO, you dont have any nodes.... exiting....sorry...");
			return 0;
		}
		for(String node: cs.getGraph().nodeSet())
		{
			try {
				numNeighbors = cs.getGraph().neighborCount(node);
				//System.out.println("Num neighbors for: "+node+"= "+numNeighbors);
			} catch (GraphStructureException e) {
				
				System.err.println("GraphStructureExceptionThrown");
				e.printStackTrace();
			}
			//calculate Min
			if(outDegreeMin >= numNeighbors)
			{
				outDegreeMin = numNeighbors;
			}

		}
		return outDegreeMin;
	}
	/**
	 * returns the average number of indegrees
	 */
	@Override
	public double avgInDegree() {
		int denom = 0;
		double avgInputs = 0.0,inputs=0;

		if(cs.getGraph().nodeSet().isEmpty())
		{
			System.err.println("There are no nodes loaded. exiting. ");
			return 0;
		}
		for(String node:cs.getGraph().nodeSet())
		{
			inputs= cs.getInputs(node);
			avgInputs += inputs;
			denom++;	
		}
		
		//calculateAvg
		
		return inputs/denom;
	}
	/**
	 * finds the average number of out degrees in the graph
	 */
	@Override
	public double avgOutDegree() {
		
		int numNeighbors = 0, denom = 0;
		double avgOutputs = 0.0;
		
		//error checking
		if(cs.getGraph().nodeSet().isEmpty())
		{
			System.out.println("UH-HO, you dont have any nodes.... exiting....sorry...");
			return 0;
		}
		for(String node: cs.getGraph().nodeSet())
		{
			//System.out.println("nodes: "+node);
			try {
				numNeighbors = cs.getGraph().neighborCount(node);
				
				//functionality for calculating the average;
				
				avgOutputs += numNeighbors;
				denom++;
	
			} catch (GraphStructureException e) {
				
				System.err.println("Graph Structure Exception!");
				e.printStackTrace();
			}
		}
		return avgOutputs/denom;
	}
	

}
