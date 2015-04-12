package Tests;
import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import edu.unm.cs.cs351.tdrl.f10.p1.Graph;
import edu.unm.cs.cs351.tdrl.f10.p1.GraphStructureException;
import DemoClasses.demoWebGraph;
/**
 * @author niallschavez
 *
 */


public class WebGraphTest<Date> {
	Graph<Date> dw=new demoWebGraph<Date>();
	//Graph<Date> dw;
	@Before public void initialTests(){
		//dw=new demoWebGraph<Date>();
	}
	
	@Test public void addEdgeTest(){
		demoWebGraph<Date> dw=new demoWebGraph<Date>();
		/*
		 * This method checks addEdge to see if the edges 
		 * are added correctly, and if they pass the 
		 * graphstructureException
		 * 
		 */
		dw.addEdge((Date)"01/02/05", (Date)"01/02/03");
		
		assertEquals(dw.edgeCount(),1);
		dw.addEdge(new Date(1), new Date(2));
		dw.addEdge((Date)"01/03/02", (Date)"05/02/01");
		dw.addEdge((Date)"01/01/08", (Date)"07/02/05");

		assertEquals(dw.edgeCount(),4);
	}
	
	@SuppressWarnings("unchecked")
	@Test public void addNodeTest(){
		demoWebGraph<Date> dw=new demoWebGraph<Date>();
		dw.addNode((Date)"01/02/01");
		// Now _stdInstance should contain exactly one node
		assertEquals(1,dw.size());
		dw.addNode((Date)"01/02/03");
		dw.addNode((Date)"01/02/045");
		// Now _stdInstance should contain exactly three nodes
		assertEquals(3,dw.size());
		
		dw.addNode((Date)"01/02/02");
		dw.addNode((Date)"01/02/02");
		dw.addNode((Date)"01/02/02");
		// dw should still contain only three nodes
	
		assertEquals(3,dw.size());
		assertEquals(dw.containsNode((Date)"01/02/02"),true);
		/*
		 * this checks to see if I have not added something
		 * that it really comes back false 
		 * 
		 */
		assertEquals(dw.containsNode((Date)"badData"),false);
		/*
		 * check to see if the last element is tested, just incase
		 * my loop is messed up
		 * 
		 */
		assertEquals(dw.containsNode((Date)"01/02/045"),true);
	
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test public void containsNodeTest(){
		demoWebGraph<Date> dw=new demoWebGraph<Date>();
		
		/*
		 * Here, I am testing to for the general case if i add 
		 * a node can I also find that same node 
		 */
		dw.addNode((Date)"10/10/10");
		
		assertEquals(dw.containsNode((Date)"10/10/10"), true);
		/*
		 * Here, I am testing a more expansive case, if there are extra
		 * nodes around the one I want to find, will it still find it.
		 * 
		 */
		dw.addNode((Date)"123/123/12");
		dw.addNode((Date)"113/163/13");
		dw.addNode((Date)"143/163/11");
		
		assertEquals(dw.containsNode((Date)"113/163/13"), true);
		/*
		 * Here, I am testing to see if it will find the last node in the 
		 * list, just in case to make sure my iterator works correctly 
		 *  
		 */
		assertEquals(dw.containsNode((Date)"143/163/11"),true);
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test public void containsEdgeTest(){
		demoWebGraph<Date> dw=new demoWebGraph<Date>();
		
		try {
			dw.addEdge((Date)"11334", (Date)"22213");
		} catch (GraphStructureException e) {
			
			System.out.println("GraphStructureException from containsEdge");
		}
		assertEquals(dw.containsEdge((Date)"11334", (Date)"22213"),true);
		try {
			dw.addEdge((Date)"1132124", (Date)"22234213");
			dw.addEdge((Date)"11312344", (Date)"22adf213");
			dw.addEdge((Date)"11334323", (Date)"22223413");
		} catch (GraphStructureException e) {
			System.out.println("GraphStructureException from containsEdge");
		}
		
		/*
		 * testing the general case to make sure that the node i added is able to 
		 * be found
		 */
		assertEquals(dw.containsEdge((Date)"1132124", (Date)"22234213"),true);
		
		/*
		 * Testing to make sure that the program doesn't just return true all the time
		 * 
		 */
		assertEquals(dw.containsEdge((Date)"233", (Date)"23452"),false);
		
		/*
		 * Testing the last addition to make sure the iterator works properly 
		 */
		assertEquals(dw.containsEdge((Date)"11334323", (Date)"22223413"),true);
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Test public void edgeCountTester(){
		demoWebGraph<Date> dw=new demoWebGraph<Date>();
		/*
		 * Checking to make sure that the initial edgeCount is 0;
		 */
		assertEquals(dw.edgeCount(),0);
		
		try {
			dw.addEdge((Date)"Date1", (Date)"Date2");
		} catch (GraphStructureException e) {
			System.out.println("GraphStructureException in edgeCount");
		}
		/*
		 * Checking to make sure that if i add just one edge that the count 
		 * increments;
		 */
		assertEquals(dw.edgeCount(),1);
		
		try {
			dw.addEdge((Date)"Date3", (Date)"Date7");
			dw.addEdge((Date)"Date4", (Date)"Date8");
			dw.addEdge((Date)"Date5", (Date)"Date9");
			
		} catch (GraphStructureException e) {
			System.out.println("GraphStructureException in edgeCount");
		}
		/*
		 * This makes sure that if I add multiple edges at one time it will do it
		 */
		assertEquals(dw.edgeCount(),4);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Test public void sizeTester(){
		/*
		 * Testing the initialization to make sure that
		 * size starts off at 0
		 */
		demoWebGraph<Date> dw=new demoWebGraph<Date>();
		assertEquals(dw.size(),0);
		
		/*
		 * Testing 1 addition to make sure that the counter works
		 * properly 
		 */
		dw.addNode((Date)"New Date");
		assertEquals(dw.size(),1);
		
		
		/*Testing multiple additions to make sure that 
		 * if i add multiple things the counter will still work properly 
		 */
		dw.addNode((Date)"23423");
		dw.addNode((Date)"547567567");
		dw.addNode((Date)"1314314");
		assertEquals(dw.size(),4); 
		
		/*
		 * Testing the duplicate node functionality, if i make multiple nodes
		 * of the same thing the counter should ignore it and not do anything 
		 * with it
		 */
		dw.addNode((Date)"23423");
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test public void getNodeID(){
		/*
		 * This method tests the get Node ID, every time a node is created an 
		 * arbitrary number for the ID should be generated, for the function of 
		 * this test file, I am assuming that the ID number will be the same as 
		 * the node size number, so as the size grows, the ID of the node will be set
		 * TODO: create functionality to test for O(l) time
		 */
		
		demoWebGraph<Date> dw=new demoWebGraph<Date>();
		dw.addNode((Date)"Some Date");
		int i =dw.size();
		try {
			assertEquals(dw.getNodeID((Date)"Some Date"), i);
		} catch (GraphStructureException e) {
			
			System.out.println("GraphStructureException from getNodeID Tester");
			
		}
		
		dw.addNode((Date)"New Date");
		dw.addNode((Date)"another New Date");
		
		/*This test is to make sure that the ID stayed consistent not only through multiple calls to get 
		 * node ID but when new nodes have been added and new ID's have been added   
		 * 
		 */
		try {
			assertEquals(dw.getNodeID((Date)"Some Date"), i);
		} catch (GraphStructureException e) {
			System.out.println("GraphStructureException from getNodeID Tester");
			e.printStackTrace();
		}
		/*
		 * Testing to see if the two ID's don't equal because if they are the same that is bad. 
		 */
		try {
			assertNotSame(dw.getNodeID((Date)"New Date"), dw.getNodeID((Date)"Some Date"));
		} catch (GraphStructureException e) {
			System.out.println("GraphStructureException from getNodeID Tester");
			e.printStackTrace();
		}
		/*
		 * this checks to see if the id is smaller than or equal to the number of nodes, or size
		 */
		try {
			assert(dw.size()-dw.getNodeID((Date)"new Date")>=0);
		} catch (GraphStructureException e) {
			e.printStackTrace();
		}
		
	}
	
}