/**
 * 
 */
package classes;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import edu.unm.cs.cs351.tdrl.f10.p1.CrawlState;
import edu.unm.cs.cs351.tdrl.f10.p1.Graph;
import edu.unm.cs.cs351.tdrl.f10.p1.GraphStructureException;

/**
 * @author niallschavez

 *
 */
public class MyCrawlState implements CrawlState  {

	/**
	 * CrawlState returns the current "state" of the crawler 
	 * 
	 */
	int queueLengthCounter=0;
	
	String initialPage=null;
	
	LinkedList<String> items = new LinkedList<String>();
	Set<String> done = new HashSet<String>();
	
	MyWebGraph<String> wg;
	
	public MyCrawlState() {
	
		wg= new MyWebGraph<String>();
		
	}

	@Override
	public void addHref(String curURL, String hrefURL) throws GraphStructureException {
		
		/*
		 * Here I am adding two nodes that are being passed in then adding an edge between the 
		 * two nodes, I also add the hrefURL(The URL that was found on the webpage) into the openList
		 * 
		 */
		wg.addNode( curURL);
		
		wg.addNode( hrefURL);

		wg.addEdge(curURL, hrefURL);
		
		//if( wg.wasLastAdded()){
			items.add(hrefURL);
			queueLengthCounter++;
		//}
	
	}
	public void setInitialPage(String page){
		initialPage=page;
		System.out.println("initial page set to : "+initialPage);
		
	}
	public String getInitialPage(){
		return initialPage;
	}
	
	@Override
	public Graph<String> getGraph() {
		/*
		 * this returns a pointer to webgraph
		 */
		return wg;
	}

	@Override
	public Queue<String> getQueue() {
		
		return items;
	
	}

	@Override
	public boolean hasNextURL() {
		//This method checks to see if the queue has any url's left in it 
		
		if(items.isEmpty())
			return false;
		
		else 
			return true;	
		
	}

	@Override
	public String popNextURL() {
		// This method pops off the top of the list and places the completed node
		// in a queue called done so that if I come across this node again, it will
		// not be re-traversed
		
		done.add(items.peek());
		return items.poll();
		
	}

	@Override
	public int queueLength() {
		//returns the size of the queue 
		//return items.size();
		return queueLengthCounter;
	}

	public int queueDone(){
		return done.size();
	}
	public void setDone(String doneURL){
		done.add(doneURL);
	}
	public int getInputs(String node){
		//System.out.println("Node coming in : "+ node);
		//System.out.println("Number of inputs:"+ wg.getInputs(node));
		
		return wg.getInputs(node);
	}
	
	

	@Override
	public void saveYourself(String fileName) throws IOException {
		//saves the webgraph object so that if I want to run this again 
		//I can easily pick up where I left off 
		
		if(fileName == null){
			fileName = "default.wgr";
		}
		FileOutputStream in = new FileOutputStream(fileName);
		ObjectOutputStream saveData = new ObjectOutputStream(in);
		
		Serializable obj= (Serializable) getGraph();
		Serializable obj2= (Serializable) items;
		Serializable obj3= (Serializable) initialPage;
		saveData.writeObject(obj);
		saveData.writeObject(obj2);
		saveData.writeObject(obj3);
		saveData.flush();
	}
	//I am sorry... I dont know if this is right... but I am using @suppressWarnings Please dont Kill me :/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	public void loadYourself(String saveData) throws IOException, ClassNotFoundException{
		//This method preforms the load operation :) 
		FileInputStream in = new FileInputStream(saveData);
		ObjectInputStream loadFile = new ObjectInputStream(in);
		
		Object o = loadFile.readObject();
		Object l = loadFile.readObject(); 
		Object i = loadFile.readObject();
		loadFile.close();
		
		if(o instanceof MyWebGraph){
			wg = (MyWebGraph) o;
		}
		
		if(l instanceof LinkedList){
			items = (LinkedList) l;
		}
		if(i instanceof String){
			initialPage= (String) i;
		}
	}
	
	
}
