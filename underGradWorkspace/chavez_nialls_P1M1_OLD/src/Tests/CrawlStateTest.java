package Tests;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.unm.cs.cs351.tdrl.f10.p1.Graph;
import edu.unm.cs.cs351.tdrl.f10.p1.GraphStructureException;
import DemoClasses.demoCrawlState;

/**
 * @author niallschavez
 *
 */
public class CrawlStateTest {
	
	demoCrawlState CS;
	Graph<String> g;
	@BeforeClass public void initialTest(){
		/*These tests run basically inital tests to make sure that there
		 * is nothing in my graphs or queues and makes sure that everything is 
		 * initialized correctly 
		 */
		assertEquals(CS.getGraph(), null);
		assertEquals(CS.hasNextURL(),false);
		assertEquals(CS.getQueue(), null);
		assertEquals(CS.popNextURL(), null);
		assertEquals(CS.queueLength(),0);
		
	}
	@Test public void getGraphTest(){
		String s="My node";
		g.addNode(s);
		assertEquals(CS.getGraph(), s);
	}
	@Test public void hrefTest() throws GraphStructureException{
		/*In testing addhref(), you actually have to test addEdge in the Graph class
		 * because,  addHref actually sets in the values into addEdge, and thus you are
		 * actually testing containsEdge because if the strings were set into addHref(), then 
		 * they were set into addEdge() which means we can see them in containsEdge().
		 */
		
		String myString1 = "1"; String myString2="2";
		CS.addHref(myString1, myString2);
		assertEquals(g.containsEdge(myString1, myString2),true );
	}
	@Test public void getQueueTest(){
		assertEquals(CS.getQueue(), true);
	}
	@Test public void hasNextURL(){
		assertEquals(CS.hasNextURL(),true);
	}
	@Test public void popNextURL(){
		assertEquals(CS.popNextURL(),null);
		
	}
	@Test public void queueLength(){
		assertEquals(CS.queueLength(),0);	
	}
	
}
