/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import classes.MyCrawlState;
import edu.unm.cs.cs351.tdrl.f10.p1.GraphStructureException;


/**
 * This Class tests the CrawlState class and each of its individual methods 
 * 
 * @author niallschavez
 *
 */
public class CrawlStateTest {
	
		private MyCrawlState cs;
		
		String[] curURL={"www.google.com", "www.fseven.org", "kotaku.com", "www.twitter.com","www.myspace.com"};
		String[] hrefURL= {"www.unm.edu","www.yahoo.org","www.facebook.com","badURL"};
		String[] extension={"com", "edu","org","net", "biz", "info","mobi","us","tv"};
		String[] filenames={"goodFilename.wgr","badFile.txt","evenworsenamewgr"};
		/**
		 * This method initializes cs(CrawlState) before each test
		 * 
		 */
		@Before public void initializer(){
			cs= new MyCrawlState();
			
		}
		/**
		 * This method adds href's from an already created node and also checks to sere that the 
		 * url being added is a valid string url.
		 * @throws GraphStructureException
		 */
		
		@Test public void basictest() throws GraphStructureException{
			cs.addHref("1", "2");
			//assertEquals(0, cs.getGraph().edgeCount());
			assertEquals(true, cs.getGraph().containsEdge("1", "2"));
			assertEquals(1, cs.getGraph().edgeCount());
			
		}

		@Test public void aHrefTest() throws GraphStructureException{
			//cs.addHref("1", "2");
			//assertEquals(true, cs.getGraph().containsEdge("1", "2"));
			
			int exSize=0;
			
			
			for(String cur: curURL){
				
				for(String href:hrefURL){
					
					assertEquals(errorMessage(cur,href), exSize, cs.getGraph().edgeCount());
					cs.addHref(cur, href);
					exSize++;
					assertEquals(errorMessage(cur,href), exSize, cs.getGraph().edgeCount());
					
					assertEquals(errorMessage(href),true,cs.getGraph().containsNode(cur));
					
					
					
					assertEquals(errorMessage(cur,href), true, cs.getGraph().containsEdge(cur, href));
					
					cs.addHref(cur, href);
					cs.addHref(cur, href);
					cs.addHref(cur, href);
					
					System.out.println("added three to test\n");
					assertEquals(errorMessage(cur,href), true, cs.getGraph().containsEdge(cur, href));
					assertEquals(errorMessage(cur,href), exSize, cs.getGraph().edgeCount());
					
					
				}
			}
		}
		/**
		 * this method checks to see if the queue is there, it first tests if the queue has just been made
		 * if it is empty then adds items to the queue then checks if queue I created here returns
		 * the same queue as the getQueue method inside of CrawlState.java
		 * @throws GraphStructureException 
		 */
		@Test public void getQueueTest() throws GraphStructureException{
			assertEquals("Not Null Queue on Start(getQueue)", cs.getQueue().isEmpty(), true);
			for(String url: hrefURL){
	
				cs.addHref(url, url);
				//assertEquals("Node not found", true, cs.getQueue().contains(url));
			
			}
			assertEquals("Still null", false,cs.getQueue().isEmpty());
			
			
		}
		/**
		 * this method checks to see if the queue has any more items in it. if it doesnt then I know
		 * that I can move on to the next url and start working on its queue. 
		 * @throws GraphStructureException 
		 */
		
		@Test public void hasNextURLTest() throws GraphStructureException{
			//Queue<String> que= new LinkedList<String>();
			assertEquals("Not null Queue on Start(hasNextURL",cs.hasNextURL(),false);
			
			for(String url: hrefURL){
				
				cs.addHref(url, url);
			
				assertEquals("No next URL", true, cs.hasNextURL());
				
			}
			//Here I used suppressWarnings because I know I am right I am using URL 
			//as an iterator, and it is going through and removing all the items from the queue
			//and it is getting used but it just doesnt know its getting used :)
			for(@SuppressWarnings("unused") String url: hrefURL){
				
				cs.getQueue().remove();
		
			}
			
			assertEquals("No next URL", false,cs.hasNextURL());
			
		}
		/**
		 * This function tests to see that if i pop an item out of the queue that it really does remove it 
		 * and that that item is now removed from the queue
		 * @throws GraphStructureException 
		 */
		@Test public void popNextURLTest() throws GraphStructureException{
			int counter=0;
			assertEquals("Not Null Queue on Start(popNextURL)",cs.popNextURL(), null);
			for(String url: hrefURL){


				cs.addHref(url, url);
				assertEquals(cs.popNextURL(), url);
			    counter++;
			    
			}

			
		}
		/**
		 * this method checks the queue length to make sure that the length of the queue is updated
		 * each time an item is added into the queue 
		 * @throws GraphStructureException 
		 */
		@Test public void queueLengthTest() throws GraphStructureException{
			
			int counter=0;
			assertEquals(cs.queueLength(), counter);

			for(String url: curURL){
				cs.addHref(url, url);
				//cs.getQueue();
				
				counter++;
			}
			
			assertEquals( counter, cs.queueLength());
		
		}
		/**
		 * this method makes sure that the file name entered is valid and has a valid extension
		 * @throws IOException
		 */
		//@Test(expected = IOException.class)
		public void testSaveYourself() throws IOException {
			String fileName = "File.txt";
			cs.saveYourself(fileName);
			
		}
		/**
		 * this method is a helper method for saveYourSelfTest() to make sure that the extension 
		 * provided by the user is valid 
		 * @param filename: name of file passed in by the user 
		 * @return a boolean result that says whether or not the filename is valid
		 */
		public boolean wgrTest(String filename){
			if(filename.matches("wgr$") )
				return true;
			else 
				return false;
		}
		/**
		 * This is a helper method for the addHrefTest() method, it makes sure that the url being recieved 
		 * is a valid url and that nothing weird is in the string.
		 * @param href: a url to make sure is valid 
		 * @return a boolean result that says whether or not the url is valid.
		 */
		public boolean extensionTest(String href){
			
			for(String ext: extension){
				if(href.matches("^"+ext+"$")){
				
					
					return true;
				}
				
			}
			return false;
		}
		
		
		/**
		 * 
		 * @param <T> generic data type T 
		 * @param curURL url currently traversing 
		 * @param hrefURL a url found on the curURL 
		 * @return
		 */
		
		private <T> String errorMessage(T curURL, T hrefURL) {
		    return curURL.getClass().getSimpleName() + "(" + curURL + "->"+hrefURL+")";
		  }
		/**
		 * 
		 * @param <T> generic data type T 
		 * @param curURL url currently traversing 
		 * @return
		 */
		private <T> String errorMessage(T hrefURL) {
		    return curURL.getClass().getSimpleName() + "(" + hrefURL+")";
		  }
}