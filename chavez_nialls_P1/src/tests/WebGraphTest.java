/**
 * WebGraphTest.java
 * This is a jUnit test file that goes through all the methods in webGraph.java <br>
 * and tests each one of them to make sure that functionality is present with <br>
 * webGraph. It tests addEdge, addNode, containsNode, containsEdge, containsNode, 
 * edgeCount, getNode, neighborCount, neighborSet, nodeSet, size
 * --At this point getBreadthFirstIterator, getDepthFirstIterator are not being tested
 * --this will be documented in the bugs.txt file
 * 
 * @author Nialls Chavez
 * 
 */

package tests;
import static org.junit.Assert.assertEquals;



import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import classes.MyWebGraph;
import edu.unm.cs.cs351.tdrl.f10.p1.Graph;
import edu.unm.cs.cs351.tdrl.f10.p1.GraphStructureException;

public class WebGraphTest {
	
	/* all the different data types to test, using generics */
	private Graph<Date>    wgDate;
	private Graph<Integer> wgInt;
	private Graph<String>  wgString;
	private Graph<Double>  wgDouble;
	
	/*The values for each data type to do tests with */
	Double[]  doubleArray={2.3,2.5,-2.5,-9.7, 19.0, 0.0};
	Integer[] integerArray={1, 5, 7, -5, 0};
	String[]  stringArray={"String1", "String2", "String6", "String4"/*,null, ""*/};
	Date[]    dateArray;
	
	/**
	 * This is the initializer, it creates a new DemoWebGraph object
	 * before each test is run, this makes sure that anything that was 
	 * previously added is taken out. It runs before each test method.
	 */
	@Before public void initializer() throws ParseException{
		
		wgDate = new MyWebGraph<Date>();	
		wgInt = new MyWebGraph<Integer>();
		wgString = new MyWebGraph<String>();
		wgDouble = new MyWebGraph<Double>();

		
		final DateFormat datePaser=DateFormat.getDateInstance(DateFormat.SHORT);
	    dateArray=new Date[4];
	    dateArray[0]=datePaser.parse("07/29/1990");
	    dateArray[1]=datePaser.parse("2/16/1993");
	    dateArray[2]=datePaser.parse("03/07/1996");
	    dateArray[3]=datePaser.parse("07/29/1991");
	}
	/**
	 * This test method takes five different types of data(Double, Date, Integer, String, and Boolean)
	 * and tests to see if for each of these cases, the addNode method inside of WebGraph will run properly, calls addNodeTest
	 * 
	 */
	@Test public void genericAddNodeTest() {
		System.out.println("Running addNodeTest\n");
		
	    addNodeTest(dateArray,wgDate);
	    
	    addNodeTest(integerArray,wgInt);
	    
	    addNodeTest(doubleArray,wgDouble);
	    
	    addNodeTest(stringArray,wgString);
	    
	 
	    System.out.println("Ending addNodeTest\n");
	}
	/**
	 * 	
	 * This test method takes five different types of data(Double, Date, Integer, String, and Boolean)
	 * and tests to see if for each of these cases, the addNode method inside of WebGraph will run properly.
	 * This class must throw GraphStructureException because if the graph becomes populated with the wrong types
	 * of data then it will not run properly, calls addEdgeTest
	 * 
	 *
	 * @throws GraphStructureException
	 */
	@Test public void genericAddEdgeTest() throws GraphStructureException {
		System.out.println("Running addEdgeTest\n");
	    //addEdgeTest(dateArray,wgDate);
	    
	    addEdgeTest(integerArray,wgInt);
	    
	    addEdgeTest(doubleArray,wgDouble);
	    
	    addEdgeTest(stringArray,wgString);
	    
	    System.out.println("Ending addEdgeTest\n");
	}
	/**
	 * This test method takes five different types of data(Double, Date, Integer, String, and Boolean)
	 * and tests to see if for each of these cases, the ContainsNode method inside of WebGraph will run properly.
	 * it checks, basically if I add a node will that node come back out when I look for it, calls containsNodeTest
	 * 
	 */
	@Test public void genericContainsNodeTest(){
		System.out.println("Running ContainsNodeTest\n");
		
		containsNodeTest(dateArray,wgDate);
	    
	    containsNodeTest(integerArray,wgInt);
	    
	    containsNodeTest(doubleArray,wgDouble);
	    
	    containsNodeTest(stringArray,wgString);
	    
	    System.out.println("Ending ContainsNodeTest\n");
		
	}
	/**
	 * 	
	 * This test method takes five different types of data(Double, Date, Integer, String, and Boolean)
	 * and tests to see if for each of these cases, the containsEdge method inside of WebGraph will run properly.
	 * This class must throw GraphStructureException because if the graph becomes populated with the wrong types
	 * of data then it will not run properly, calls containsEdgeTest
	 * 
	 * @throws GraphStructureException
	 */
	@Test public void genericContainsEdgeTest() throws GraphStructureException{
		System.out.println("Running ContainsEdgeTest\n");
		//containsEdgeTest(dateArray,wgDate);
	    
	    containsEdgeTest(integerArray,wgInt);
	    
	    containsEdgeTest(doubleArray,wgDouble);
	    
	    containsEdgeTest(stringArray,wgString);

	    System.out.println("Ending ContainsEdgeTest\n");
	}
	
	/**
	 * This test method takes five different types of data(Double, Date, Integer, String, and Boolean)
	 * and tests to see if for each of these cases, the size method inside of WebGraph will run properly.
	 * it checks, basically if I add an a node or multiple nodes, will the size of teh add node method come 
	 * back correctly, calls nodeSizeTest
	 * 
	 */
	@Test public void genericSizeTester(){
		System.out.println("Running sizeTest\n");
		
		nodeSizeTest(dateArray,wgDate);
	    
	    nodeSizeTest(integerArray,wgInt);
	    
	    nodeSizeTest(doubleArray,wgDouble);
	    
	    nodeSizeTest(stringArray,wgString);
	    
	    System.out.println("Ending sizeTest\n");
		
	}
	/**
	 * 	
	 * This test method takes five different types of data(Double, Date, Integer, String, and Boolean)
	 * and tests to see if for each of these cases, the nodeID method inside of WebGraph will run properly.
	 * it checks, basically if I add a node, it should get a unique ID number assigned to it and I should be 
	 * able to access that ID, Also if I add more nodes and check that same first node, it should have the same 
	 * ID number as before, calls nodeSizeTest
	 * @throws GraphStructureException 
	 * 
	 */
	@Test public void genericGetNodeIDTester() throws GraphStructureException{
		/*
		 * for the purposes of this test, I will assume, that the nodeID will be identical to its place in the 
		 * insertion process, for example, if a node is inserted at spot 100, its ID will be 100
		 */
		System.out.println("Running GetNodeIDTest\n");
		
		//nodeIDTest(dateArray,wgDate);
	    
	    nodeIDTest(integerArray,wgInt);
	    
	    nodeIDTest(doubleArray,wgDouble);
	    
	    nodeIDTest(stringArray,wgString);
	    
		
	    System.out.println("Ending GetNodeIDTest\n");
	}

	/**
	 * 
	 * This test method takes five different types of data(Double, Date, Integer, String, and Boolean)
	 * and tests to see if for each of these cases, the neighborCount method inside of WebGraph will run properly.
	 * it checks, basically if I add a node, then add an edge, the first node should have one neighbor, and so on, if 
	 * I add more edges from the initial node, it should have x number of neighbors, calls neighborCountTest
	 * 
	 * @throws GraphStructureException
	 */
	@Test public void genericSetNeighborTester() throws GraphStructureException{
		
		System.out.println("Running NeighborSetTest\n");
		setNeighborTest(dateArray,wgDate);
	    
		setNeighborTest(integerArray,wgInt);
	    
		setNeighborTest(doubleArray,wgDouble);
	    
		setNeighborTest(stringArray,wgString);

		
		System.out.println("Ending NeighborSetTest\n");
	}

	/**
	 * 	
	 * This test method takes five different types of data(Double, Date, Integer, String, and Boolean)
	 * and tests to see if for each of these cases, the neighborCount method inside of WebGraph will run properly.
	 * it checks, basically if I add a node, then add an edge, the first node should have one neighbor, and so on, if 
	 * I add more edges from the initial node, it should have x number of neighbors, this runs in conjunction with setNeighbor 
	 * because each of them are interdependent, a neighbor must be set, then the count must be incremented, calls setNeighborTest
	 * 
	 * @throws UnsupportedOperationException
	 * @throws GraphStructureException
	 */
	@Test public void genericNeighborCount() throws UnsupportedOperationException, GraphStructureException{
		System.out.println("Running NeighborCountTest\n");
		
		//neighborCountTest(dateArray,wgDate);
	    
		neighborCountTest(integerArray,wgInt);
	    
		neighborCountTest(doubleArray,wgDouble);
	    
		neighborCountTest(stringArray,wgString);
	    
		
		System.out.println("Ending NeighborCountTest\n");
	}
	
	/**
	 * This method tests the hashCode() method, it tests to see if the hash code created is valid and is 
	 * unique.
	 * @throws GraphStructureException 
	 * 
	 */
	 @Test public void hashCodeTest() throws GraphStructureException{
		 System.out.println("Running hashCodeTest\n");
		 
		hashCodeTest(integerArray,wgInt);
		    
		hashCodeTest(doubleArray,wgDouble);
		    
		hashCodeTest(stringArray,wgString);
		
		 System.out.println("Ending hashCodeTest\n");

		
		
	 }
	@Test public void edgeCountTest() throws GraphStructureException{
		System.out.println("Running edgeCountTest\n");
		
		edgeCountTest(dateArray,wgDate);
	    
		edgeCountTest(integerArray,wgInt);
	    
		edgeCountTest(doubleArray,wgDouble);
	    
		edgeCountTest(stringArray,wgString);
	    
		
		System.out.println("Ending edgeCountTest\n");
	}
	
	@Test public void EmpiricalTest() throws GraphStructureException {

			
			long[] counts={10, 100, 1000, 10000 };
			
		
			for( int x=0; x < 4; x++ ){
				
				Long timer = System.nanoTime();
				
				for( int i = 0; i < counts[x]; i++){
					
					wgInt.addNode(i);
				
				}
				
				Long secondTimer= System.nanoTime();
				
				System.out.println("Time for "+counts[x] + "created nodes is: "+ (secondTimer-timer) +"\n");
			}	
			for( int x=0; x < 4; x++ ){
				
				Long timer = System.nanoTime();
				
				for( int i = 0; i < counts[x]; i++){
					
					wgInt.addEdge(i, i);
				
				}
				
				Long secondTimer= System.nanoTime();
				
				System.out.println("Time for "+counts[x] + "created edges is: "+ (secondTimer-timer) +"\n");
			}	
	}
	
	/**
	 * In this method I check to see if i add a node, then add an edge between two nodes, 
	 * then the number of neighbors should be 1, I also test if it is a selfreferential edge that
	 * the number of neighbors is 0
	 * 
	 * @param <T> the Generic data type being passed in 
	 * @param data the information being passed in, eg: if Integers: 1,5,-4,and so on
	 * @param neighborData the object representing Graph
	 * @throws GraphStructureException
	 */
	public <T> void edgeCountTest(T[] data, Graph<T> eCount) throws GraphStructureException{
		
	    int expectedSize=0;
	    
	    assertEquals(expectedSize,eCount.edgeCount());
	    
	    System.out.println("expectedsize= "+ expectedSize+" actual size="+ eCount.edgeCount()+"\n");
	    for (T myData : data) {
	      
	      eCount.addNode(myData);
	     
	    }  
	    
	      eCount.addEdge(data[0], data[0]);
	      expectedSize++;
	      
	     // System.out.println("expectedsize= "+ expectedSize+" actual size="+ eCount.edgeCount()+"\n");
	      assertEquals(expectedSize,eCount.edgeCount());

	      eCount.addEdge(data[0], data[1]);
	      expectedSize++;
	      
	     // System.out.println("expectedsize= "+ expectedSize+" actual size="+ eCount.edgeCount()+"\n");
	      
	      assertEquals(expectedSize,eCount.edgeCount());
	      eCount.addEdge(data[0], data[2]);
	      expectedSize++;
	      
	      eCount.addEdge(data[0], data[3]);
	      
	      expectedSize++;
	      
	      assertEquals(expectedSize,eCount.edgeCount());
		
	}
	public <T> void hashCodeTest(T[] data, Graph<T> hash) throws GraphStructureException {
		int counter=0; 
		for (T myData : data) {
			 
			 hash.addNode(myData);
			 counter++;
			 assertEquals(errorMessage(data),counter, hash.getNodeID(myData));
			 
			 hash.addNode(myData);
			 hash.addNode(myData);
			 hash.addNode(myData);
			 
			 assertEquals(errorMessage(myData),counter, hash.getNodeID(myData));
			 
		 }
		
	}
	public <T> void neighborCountTest(T[] data, Graph<T> neighborData) throws GraphStructureException{
		int counter=0;
		
		for(T myData: data){
			
			neighborData.addNode(myData);

		}
		
		neighborData.addEdge(data[0], data[1]);
		counter++;
		assertEquals(errorMessage(data[0]),counter, neighborData.neighborCount(data[0]));
		
		neighborData.addEdge(data[0], data[2]);
		counter++;
		neighborData.addEdge(data[0], data[3]);
		counter++;
		assertEquals(errorMessage(data[0]), counter,neighborData.neighborCount(data[0]));	
		
		
			
	}
	/**
	 * Here I check to see if I add a node, then add an edge, if I get back the same set of 
	 * neighbors
	 * 
	 * @param <T> the Generic data type being passed in 
	 * @param data the information being passed in, eg: if Integers: 1,5,-4,and so on
	 * @param neighborData the object representing Graph
	 * @throws GraphStructureException
	 * @throws UnsupportedOperationException
	 */
	public <T> void setNeighborTest(T[] data, Graph<T> neighborData) 
		throws GraphStructureException, UnsupportedOperationException{
		neighborData.addNode(data[0]);
		
		assertEquals(neighborData.neighborSet(data[0]).contains(data[1]),false);
		
		for(T myData: data){
			
			neighborData.addNode(myData);
		}
		neighborData.addEdge(data[0], data[1]);
		
		assertEquals(neighborData.neighborSet(data[0]).contains(data[1]),true);
		
	}
	/**
	 * This test checks to see if I add a node, if I can get that node back, and if I add
	 * duplicate nodes if the number of nodes does not reflect inserting multiple of the same
	 * node.
	 * 
	 * @param <T> the Generic data type being passed in 
	 * @param data the information being passed in, eg: if Integers: 1,5,-4,and so on
	 * @param nodeData the object representing Graph
	 */
	 public <T> void addNodeTest(T[] data, Graph<T> nodeData) {
		    int expectedSize=0;
		    for (T myData : data) {
		      
		      assertEquals(expectedSize,nodeData.size());
		      
		      nodeData.addNode(myData);
		      
		      //expectedSize++;
		      
		     if(myData!=null && myData!="")
		    	 expectedSize++;
		     
		      assertEquals(errorMessage(myData),expectedSize,nodeData.size());
		      
		      nodeData.addNode(myData);
		      nodeData.addNode(myData);
		      nodeData.addNode(myData);
		     
		      assertEquals(errorMessage(myData),expectedSize,nodeData.size());
		    }
		   
		  }	
	 /**
	  * here I check if I add a node, then add an edge between those two nodes, If i can see
	  * that edge, and if it is a self referential node, if it still adds it. 
	  * 
	  * @param <T> the Generic data type being passed in 
	  * @param data the information being passed in, eg: if Integers: 1,5,-4,and so on
	  * @param edgeData the object representing Graph
	  * @throws GraphStructureException
	  */
	 public <T> void addEdgeTest(T[] data, Graph<T> edgeData) throws GraphStructureException {
		    int expectedSize=0;
		    
		    assertEquals(expectedSize,edgeData.edgeCount());
		    //System.out.println("expectedsize= "+ expectedSize+" actual size="+ edgeData.edgeCount()+"\n");
		    for (T myData : data) {
		      
		      edgeData.addNode(myData);
		     
		    }  
		    
		      edgeData.addEdge(data[0], data[0]);
		      expectedSize++;
		      
		      //System.out.println("expectedsize= "+ expectedSize+" actual size="+ edgeData.edgeCount()+"\n");
		      //assertEquals(expectedSize,edgeData.edgeCount());
		      assertEquals(true,edgeData.containsEdge(data[0], data[0]));

		      edgeData.addEdge(data[0], data[1]);
		      expectedSize++;
		      
		      //System.out.println("expectedsize= "+ expectedSize+" actual size="+ edgeData.edgeCount()+"\n");
		      
		      assertEquals(expectedSize,edgeData.edgeCount());
		      assertEquals(true,edgeData.containsEdge(data[0], data[1]));
		      edgeData.addEdge(data[0], data[2]);
		      expectedSize++;
		      
		      edgeData.addEdge(data[0], data[3]);
		      
		      expectedSize++;
		      
		      assertEquals(expectedSize,edgeData.edgeCount());
		      assertEquals(true,edgeData.containsEdge(data[0], data[3]));
		      
		  }	
	 /**
	  * This test adds a node, then tests if its there it also checks to 
	  * see if the edge cases stay valid 
	  * 
	  * @param <T> the Generic data type being passed in 
	  * @param data the information being passed in, eg: if Integers: 1,5,-4,and so on
	  * @param myNode the object representing Graph
	  */
	 public <T> void containsNodeTest(T[] data, Graph<T> myNode)
	 {
		 int counter=0;
		 for (T myData : data) {
			 
			 myNode.addNode(myData);
			 
			 assertEquals(errorMessage(data),true, myNode.containsNode(myData));
			 
			 counter++;
		 }
		 assertEquals(errorMessage(data),true, myNode.containsNode(data[0]));
		 assertEquals(errorMessage(data),true, myNode.containsNode(data[--counter]));
	 }
	 /**
	  * This test checks if i add an edge, if that edge can be found or not. 
	  * 
	  * @param <T> the Generic data type being passed in 
	  * @param data the information being passed in, eg: if Integers: 1,5,-4,and so on
	  * @param myEdge the object representing Graph
	  * @throws GraphStructureException
	  */
	 public <T> void containsEdgeTest(T[]data, Graph<T> myEdge) throws GraphStructureException{
		 int counter=0;
		 for (T myData : data) {
			myEdge.addNode(myData);
			 counter++;
		 }
		 myEdge.addEdge(data[0],data[0]);
		 
		 assertEquals(errorMessage(data[0]),true, myEdge.containsEdge(data[0], data[0]));
		 
	 }
	 /**
	  * This method checks that if i add a node, if the node size will increment with 
	  * the number of nodes added, it also checks that if i add duplicate nodes that 
	  * they are not added to the number of nodes and that the size is consistent 
	  * 
	  * @param <T> the Generic data type being passed in 
	  * @param data the information being passed in, eg: if Integers: 1,5,-4,and so on
	  * @param myNode the object representing Graph
	  */
	 public <T> void nodeSizeTest(T[]data, Graph<T> myNode){
		 int counter=0;
		 for (T myData : data) {
			 
			 myNode.addNode(myData);
			 counter++;
			 assertEquals(errorMessage(myData),counter,myNode.size());
			 
			 myNode.addNode(myData);
			 myNode.addNode(myData);
			 myNode.addNode(myData);
			 
			 assertEquals(errorMessage(myData),myNode.size(), counter);

			
		 }
		 
	 }
	 /**
	  * This test is to check that if i add a node that the id will be consistent and immutable, 
	  * and if i add multiple nodes that the initial node id will be consistent 
	  * 
	  * @param <T> the Generic data type being passed in 
	  * @param data the information being passed in, eg: if Integers: 1,5,-4,and so on
	  * @param myNode the object representing Graph
	  * @throws GraphStructureException
	  */
	 public <T> void nodeIDTest(T[]data, Graph<T> myNode) throws GraphStructureException{
		 int counter=0;
		 for (T myData : data) {
			 
			 myNode.addNode(myData);
			 counter++;
			 assertEquals(errorMessage(data),counter, myNode.getNodeID(myData));
			 
			 myNode.addNode(myData);
			 myNode.addNode(myData);
			 myNode.addNode(myData);
			 
			 assertEquals(errorMessage(myData),counter, myNode.getNodeID(myData));
			 
		 }
	 }
	 /**
	  * This test makes sure that if i traverse the graph that it does a bfs iteration, 
	  * and that each node is visited once and only once 
	  * 
	  * @param <T> the Generic data type being passed in 
	  * @param Data the information being passed in, eg: if Integers: 1,5,-4,and so on
	  * @param bfs the object representing Graph
	 * @throws GraphStructureException 
	  */
	 public <T> void bfsTest(T[]Data, Graph<T> bfs) throws GraphStructureException {
			java.util.Iterator<T> fooBar= bfs.getDFSIterator(null);
			 
			 assertEquals(errorMessage(null),fooBar.hasNext(), false);
			 assertEquals(errorMessage(null),fooBar.next(), null);
			 
			 
			 for (T myData : Data) {
				 
				 bfs.addNode(myData);
				 
			 }
			 
			 assertEquals(errorMessage(null),fooBar.hasNext(), true);
			 assertEquals(errorMessage(null),fooBar.next(), Data[0]);
		 
	 }
	 /**
	  * 
	  * This test makes sure that if i traverse the graph that it does a dfs iteration, 
	  * and that each node is visited once and only once 
	  * @param <T> the Generic data type being passed in 
	  * @param Data the information being passed in, eg: if Integers: 1,5,-4,and so on
	  * @param dfs the object representing Graph
	  * @throws GraphStructureException 
	  */
	 public <T> void dfsTest(T[]Data, Graph<T> dfs) throws GraphStructureException{

		java.util.Iterator<T> fooBar= dfs.getDFSIterator(null);
		 
		 assertEquals(errorMessage(null),fooBar.hasNext(), false);
		 assertEquals(errorMessage(null),fooBar.next(), null);
		 
		 
		 for (T myData : Data) {
			 
			 dfs.addNode(myData);
			 
		 }
		 
		 assertEquals(errorMessage(null),fooBar.hasNext(), true);
		 assertEquals(errorMessage(null),fooBar.next(), Data[0]);
		 
	 }
	 
	 
	 
	 /**
	  * This is the error message method and it makes sure that the error message that each error message 
	  * is relative to the error thrown so that i know where things went wrong
	  * @param <T> the Generic data type being passed in 
	  * @param data the information being passed in, eg: if Integers: 1,5,-4,and so on
	  * @return
	  */
	 
	 private <T> String errorMessage(T data) {
		    
		 	return data.getClass().getSimpleName() + "(" + data + ")";
		  }
	 
	 
	 
	 
	 
}