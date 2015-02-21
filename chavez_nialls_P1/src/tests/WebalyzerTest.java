package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import classes.ConcGraphAnalyzer;
import classes.MyCrawlState;
import edu.unm.cs.cs351.tdrl.f10.p1.GraphStructureException;


	public class WebalyzerTest {
		//Create some global variables for testing.
		ConcGraphAnalyzer<String> cga;
		MyCrawlState cs;
		
		
		String s0;
		String s1;
		String s2;
		String s3;
		String s4;
		String s5;
		String s6;
		//Initialize the global variables

	    @Before public void setUp()
	    {
	    	cga= new ConcGraphAnalyzer<String>();
	    	cs= new MyCrawlState();
	    	
		    s0 = new String("http://www.fseven.org");	
			s1 = new String("http://www.cs.unm.edu/");
			s2 = new String("http://www.unm.edu/");
			s3 = new String("http://www.yahoo.com");
			s4 = new String("http://www.google.com");
			s5 = new String("http://www.helloworld.com");
			s6 = new String("http://www.java.org/");
	    }	    
	    //Test allPairsShortestPaths()
	    
	    @Test public void testallPairsShortestPaths() throws GraphStructureException, IOException, ClassNotFoundException{
	    	
	    	assertEquals(cga.allPairsShortestPaths().length, 0);
			/*              0 
			 *            / | \ 
			 *           1--2  3- 
			 *           \ / \ / |
			 *            4   5  6
			 * 
			 */
	    
	    	cs.addHref(s0, s1);
			cs.addHref(s0, s2);
			cs.addHref(s0, s3);
			cs.addHref(s1, s2);
			cs.addHref(s1, s4);
			cs.addHref(s2, s4);
			cs.addHref(s2, s5);
			cs.addHref(s3, s5);
			cs.addHref(s3, s6);
			save();
			
	    	cga.allPairsShortestPaths();
	    	
	    	//assertEquals( 3,cga.maxSCCSize());
			
		}
		
		//Test checks to see the average amount of inputs throughout the graph
		@Test
		public void testAvgInDegree() throws GraphStructureException, IOException, ClassNotFoundException{
			cs.addHref(s1,s0);
			save();
			assertTrue(cs.getGraph().containsEdge(s1,s0));
			assertTrue(cga.avgInDegree()==1.0/2);
		}
		
		
		//Test checks to see the average amount of outputs throughout the graph
		 
		@Test
		public void testAvgOutDegree() throws GraphStructureException, IOException, ClassNotFoundException {

			cs.addHref(s0, s1);
			cs.addHref(s0, s2);
			cs.addHref(s0, s3);
			cs.addHref(s0, s4);
			cs.addHref(s0, s5);
			cs.addHref(s0, s6);
			//Just making sure that there isnt anything weird before I save
			save();
			assertEquals(true, cs.getGraph().containsNode(s0));
			assertEquals(true, cs.getGraph().containsEdge(s0, s1));

			assertTrue(cga.avgOutDegree() == (6.0/7));
		}
		
		
		/*
		//Test the average of all the shortest paths in the graph from node to node
		
		@Test public void testAvgShortestPath() throws Exception{
			testGraph.addNode(null);
			assertFalse(testGraph.containsNode(null));
		}
		//Test if one SCC is in the graph.
		*/
		@Test
		public void testCountSCC() throws GraphStructureException, IOException, ClassNotFoundException{
			/*
			 *      1 <--> 2
			 *      
			 */
			
			
			cs.setInitialPage(s1);
			cs.addHref(s1,s2);
			cs.addHref(s2,s1);
			save();
			assertTrue(cga.countSCCs() == 1);
		}
		
		//Test a couple of SCC's in the graph
		@Test
		public void testMultCountSCC() throws GraphStructureException, IOException, ClassNotFoundException{

			/*
			 *          - 0
			 *          |/
			 *          1-2
			 *            /\
			 *           4--3
			 *          
			 */
			
			cs.setInitialPage(s0);
			cs.addHref(s0, s1);
			cs.addHref(s1, s0);
			cs.addHref(s2, s3);
			cs.addHref(s3, s4);
			cs.addHref(s4, s2);
			cs.addHref(s1, s2);
			save();
			assertEquals(2,cga.countSCCs());
		}
	
		//Test the size of the graph. Looks for longest distance through graph.
		
		@Test public void testDiameter() throws GraphStructureException, IOException, ClassNotFoundException{
			cs.setInitialPage(s1);
			cs.addHref(s1,s0);
			cs.addHref(s1,s2);
			save();
			assertTrue(cs.getGraph().containsNode(s1));
			assertEquals(cs.getGraph().nodeSet().size(),3);
			assertEquals(3,cga.diameter());
			
		}

		
		//See what is the maximum number of inputs to a single node in graph
		@Test
		public void testMaxInDegree() throws GraphStructureException, IOException, ClassNotFoundException {

			cs.addHref(s0, s1);
			cs.addHref(s1, s2);
			cs.addHref(s2, s3);
			cs.addHref(s3, s2);
			cs.addHref(s4, s2);
			cs.addHref(s5, s2);
			
			save();
			assertTrue(cga.maxInDegree() == 4);
		}
		
		//Test to see what is the maximum number of outputs of a single node in graph
		@Test
		public void testMaxOutDegree() throws Exception{

			
			cs.addHref(s1, s1);
			cs.addHref(s1, s2);
			cs.addHref(s1, s3);
			cs.addHref(s2, s4);
			cs.addHref(s2, s3);
			cs.addHref(s3, s2);
			cs.addHref(s4, s2);
			save();
			assertEquals(3, cga.maxOutDegree());
		}
		
		//Test maxSCC by creating two SCC's one being larger and see if it returns
		//the size of the larger SCC
		@Test
		public void testmaxSCCSize() throws GraphStructureException, IOException, ClassNotFoundException {

			cs.addHref(s0, s1);
			cs.addHref(s1, s0);
			cs.addHref(s2, s3);
			cs.addHref(s3, s4);
			cs.addHref(s4, s2);
			save();
			cga.allPairsShortestPaths();
			assertEquals(2,cga.maxSCCSize());
		}
		
		//Test to see what the smallest output of a node in the graph is.
		@Test public void testMinOutDegree() throws Exception {
			
			cs.addHref(s0, s1);
			cs.addHref(s1, s3);
			cs.addHref(s1, s2);
			cs.addHref(s3, s4);
			cs.addHref(s3, s2);
			cs.addHref(s2, s3);
			cs.addHref(s4, s1);
			save();
			assertEquals(1, cga.minOutDegree());
		}
		
		@Test public void testMinInDegree() throws Exception {
			
			cs.addHref(s0, s1);
			cs.addHref(s1, s3);
			cs.addHref(s1, s2);
			cs.addHref(s3, s4);
			cs.addHref(s3, s2);
			cs.addHref(s2, s3);
			cs.addHref(s4, s1);
			cs.addHref(s1, s0);
			save();
			assertEquals(1, cga.minInDegree());
		}
		
		//Test OutDegreeDist()
		@Test
		public void testOutDegreeDist() throws GraphStructureException{
			assertEquals(cga.outDegreeDistribution().length, 0);
			
		}
		 
		@Test public void testInDegreeDist() throws GraphStructureException, IOException, ClassNotFoundException{
			
			
			assertEquals(cga.inDegreeDistribution().length, 0);
			
			cs.addHref(s0, s1);
			cs.addHref(s1, s3);
			cs.addHref(s1, s2);
			cs.addHref(s3, s4);
			cs.addHref(s3, s2);
			cs.addHref(s2, s3);
			cs.addHref(s4, s1);
			cs.addHref(s1, s0);
			
			save();
			
				
		}
		public void save() throws IOException, ClassNotFoundException{
			
			//saving so I can load the file in from ConcGraphAnalyzer
			cs.saveYourself("test.wgr");
			
			//If a IOException isnt thrown then it worked. 
			cga.loader("test.wgr");
			//if a ClassNotFoundException isnt thrown then the load worked. 
			
		}
}
