/**
 * 
 */
package DemoClasses;

import java.io.FileNotFoundException;
import java.util.Queue;

import edu.unm.cs.cs351.tdrl.f10.p1.Graph;
import edu.unm.cs.cs351.tdrl.f10.p1.GraphStructureException;
/**
 * @author niallschavez
 *
 */
public class demoCrawlState {
	public  Graph<String> getGraph(){
		
		return null;
	}
	public Queue<String> getQueue(){
		
		return null;
	}
	public void addHref(String currURL, String hrefURL) throws GraphStructureException{
		
	}
	public boolean hasNextURL(){
		return false;
	}
	
	public String popNextURL(){
		return null;
	}
	
	public int queueLength(){
		return 0;
	}
	public void saveYourself(String s) throws FileNotFoundException {
		
	}
}
