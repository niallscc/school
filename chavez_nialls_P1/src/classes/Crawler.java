/**
 * The Crawler program is the executable program responsible for accessing the web; down-loading, parsing, and analyzing web pages; 
 * constructing the local image of the web graph; and storing a local copy of the web graph.
 * It uses Graph<T> and CrawlState data structures to accomplish the data representation and storage parts of this job, 
 * but the web access and HTML parsing components require additional tools
 * In accordance with the Crawler Safety Requirements, the Crawler program does not download any single page more than once in a single crawl.
 * Thus, the Crawler is be capable of cycle-detection in the WEB GRAPH. The capabilities of the CrawlState data structure assist in this.
 * The Crawler program obeys the site-specific robots.txt file rules 
 * 1. The Crawler program accepts a non-negative integer that sets an upper bound on the number of PAGEs that the spider will retrieve. 
 *  This parameter is be user settable. The spider does not crawl more than this number of PAGEs. 
 *  If the spider is run with an existing WEB GRAPH (i.e., from a previous crawl), the count of retrieved PAGEs includes the number of PAGEs 
 *  already found in the previous crawl. (E.g., if the previous crawl found 10 PAGEs and the CRAWL-MAX parameter for this run is 20, the spider
 *  does not retrieve more than 10 additional pages. If the current CRAWL-MAX parameter is 5, then the spider MUST halt without retrieving any pages.)
 * 2.  The absolute maximum CRAWL-MAX allowable is 10,000. All programs MUST terminate by or before downloading 10,000 pages 
 *  (including any previously stored from a prior crawl).
 * 3. The Crawler engine pauses for at least 1000 milliseconds between each page request. The pause time is a user-configurable parameter, 
 *  but Crawler does not allow any delay less than one second.
 * 4. The Crawler engine obeys the robots.txt file and any meta robots tags. the Crawler is conservative and simply excludes any sites that have any robots.txt
 *  file at all and not indexing any page with a meta robots tag.
 * 5. The Crawler engine does not retrieve the same page more than once in any given crawl.
 * At the end of a crawl session, the Crawler program is responsible for saving a durable copy of the WEB GRAPH local representation to disk.
 */
package classes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import edu.unm.cs.cs351.tdrl.f10.p1.Graph;
import edu.unm.cs.cs351.tdrl.f10.p1.GraphStructureException;
import edu.unm.cs.cs351.tdrl.f10.p1.RobotHandler;

/**
 * @author niallschavez
 *
 * This is the physical implementation of the web crawler. 
 * This class does the physical parsing of the HTML webpages, and all the heavy lifting
 * of the program in terms of front end functionality work. 
 *
 */
public class Crawler {
	
	/*
	 * Global vriables used in different methods 
	 * throughout crawler
	 */
	static int CRAWL_MAX;
	static int openPageCount;
	static int delay;
	
	static boolean printStats;
	static boolean crawl;
	
	static URL newWebPage;

	static String startDate;
	static String endDate;
	static String currentWorkingPage;
    static String initialPage;


	static String resultsFile;
	static String loadFile;
	static String foundPage;
	
	static Graph<String> wg;
	static MyCrawlState cs;
	static double pause;
	static boolean finished;
	
	
	public Crawler(){
		/*
		 * Initializer, gives default values to values that need default 
		 * values :) 
		 */
		CRAWL_MAX = 20;
		pause = 1000;
		openPageCount = 0;
		printStats = false;
		crawl = true;
		finished=false;
	
	}
	
	public static void main(String [] arg0) throws MalformedURLException, IOException, GraphStructureException, Exception{
		/* 
		 * Parser parses the commandline args
		 */

		BasicParser p = new BasicParser();
		/*
		 * Command line args stuff 
		 */
		Options Option = createOptions();
		CommandLine parsed = null;

		parsed = p.parse(Option,arg0);
	
		//HTML is the physical html page found by parsing 
		//a webpage
		String HTML;
		
		cs = new MyCrawlState();
		wg = cs.getGraph();
		
		try {
			checkOptions(parsed);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		/*
		 * this method begins the crawl 
		 */
		
		Crawler cr= new Crawler();
		
		/*
		 * start date is the beginning of the crawler 
		 * 
		 */
		startDate=getDate();
		System.out.println("Beginning crawl. Please wait til all webpages have been parsed.");
		/* 
		 * This while loop works by checking if the the todo queue in crawlstate
		 * has items to parse in it and then also compares the number of pages visited 
		 * with CRAWL_MAX to make sure that I am not over the limit. If the crawler is loaded in 
		 * already being over the limit it does not do any crawl 
		 */
		
		while(!cs.items.isEmpty() && cs.done.size()< CRAWL_MAX){	

			//popping off the first item off the todo stack 
			
			String currentURL= cs.items.poll();
			//openPageCount++;
			cs.setDone(currentURL);
			
			
			
			
			//System.out.println("edge added from"+currentURL+"->");

			
			/*
			 * This method opens the initial connection and parses out the webpage passed in 
			 * it returns a string (HTML) that contains all the HTML code found in the webpage 
			 */
			
			
			//this checks to make sure that we have a current url and also that the url given doesnt have a robots meta tag
			//if it skips this node and goes on to the next url 

				if( currentURL!=null && checkRobots(currentURL)){
					
					//System.out.println("No robots :) \n");
					
					HTML= openConnection(currentURL);	
					
					
					initialPage=currentURL;
					/*
					 * okay, so this part is a little bit confusing, so if the HTML string passed back from openConnection()
					 * is null then what that means is that the currentURL coming in was unable to be parsed, eg it was not of type
					 * text/html 
					 * so if its null it just goes back to the top while loop and tries to pop off the next url( if there is one available) 
					 * 
					 */

				if( HTML!= null){
						Set<String> l= cr.crawl(HTML);
					
						
						//this goes through the set containing the nodes that were found in the parse/crawl and adds them if they can be added and 
						//doesnt add them if they are already in there :) 
						for(String found: l){
							if(!cs.getGraph().containsNode(found)){
							
								//System.out.println("    "+found+"\n");
								cs.addHref(currentURL, found);
							}
							//else 
								//System.out.println(" the url "+ found+ " (Has already been added)\n");	
						}
					}
				}

			//else 
				//System.out.println("thar be ROBOTS on this ship!");
			
			//end date is the date at which the program ends 
			endDate= getDate();
			
		}	
		
			//output if we finish correctly!!! whooooooo!!! working!!!! whoooo.....cricket, cricket ....oh.... jk 

		finished=true;
		try {
			checkOptions(parsed);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("\n\n ************ CRAWL COMPLETED SUCCESSFULLY! ************ \n\n");
			
		}
	
	
	/**
	 * check robots is what makes sure that the url being passed in allows for users to crawlthrough it 
	 * it returns a boolean value indicating whether or not i should parse that page or not.
	 * it is called by main.
	 * @param url the url to check if it can be parsed
	 * @return a boolean value indicating whether or not the page can  be parsed 
	 * @throws MalformedURLException
	 */
	private static boolean checkRobots(String url) throws MalformedURLException {
		
		RobotHandler r= new RobotHandler();
		URL u= new URL(url);
		r.isAllowed(u);
		return r.isAllowed(u);
		
	}

	/**
	 * this method is an auxilery method that canonaclizes urls and makes sure that there arent anuy relative urls being 
	 * passed in as nodes. because those arent very helpful now are they :P 
	 *  
	 * @param urlFound href found in current page 
	 * @param curURL the url i am currently at 
	 * @return returns a fixed url 
	 */
	
	private String fixURL(String urlFound, String curURL){
	
		String  regex  = "://";
		
		//String[] commonForms={"http://www.","www.","https://www.", "http://", "https://"};
		
		//for( String regex: commonForms){
			//System.out.println("urlFound in"+ urlFound);
			Pattern p = Pattern.compile(regex);
			Matcher matcher = p.matcher(urlFound);
        
        	//This works by checking if there is a colon in http://
        	//if there is then it is already in the correct form if not i need to canonaclize it 
        
        	if(matcher.find()){
        		//System.out.println("URL("+urlFound+") is good\n");
        		return urlFound;
        	}
		//}
        else 
        {
    		//String  reg  = "/[^/]*$";
			Pattern pa = Pattern.compile("#");
			Matcher ma = pa.matcher(urlFound);
        	if(ma.find()){
        		return curURL;
        		
        	}
    		//System.out.println("URL to fix : "+urlFound+", current url+"+curURL+"\n");
    	
    		String[] reg =curURL.split("http://[^/]+/((?:[^/]+/)*(?:[^/]+$)?)",1);
    		//System.out.println(reg[0]);
    		if(reg[0]!=curURL )
    		{
    			//String[] reg =curURL.split("^[^/]+/((?:[^/]+/)*(?:[^/]+$)?)");
    			//System.out.println("curFile: " +reg[1]);
    			//System.out.println("CanonURL: (path:"+reg[0]+", relative:"+ urlFound+")\n");
    			return (reg[0]+urlFound);
    		}
    		else 
    			return(curURL+urlFound);
        }
	}
	
	
	
	/**
	 * this does the actual crawl through the html passed into it it reads in the html and 
	 * from there it parses out all the links :) 
	 * 
	 * @param htmlContent the physical html code from the webpage 
	 * @return the set containing all the links found 
	 * @throws Exception
	 */
	private Set<String> crawl(final String htmlContent) throws Exception {
		
		Set<String> links = new HashSet<String>();
		
		Reader reader = new StringReader(htmlContent);
		
		new ParserDelegator().parse(reader, new Parser(links), true);
		
		return links;
	}
	
	/**
	 * This method opens the connection to the initial webpage to begin crawling.
	 * The String FirstURL is used incase I want to give the user the ability to start from 
	 * a page of their choosing. 
	 * @throws IOException 
	 */
	
	private static String openConnection(String currentURL) throws IOException {
		//initializing the html string 
		
		String HTML = null;
		
		//System.out.println("inside openConnection "+ currentURL+"\n");
		
		/*
		 * this opens the url checks if the page is an html page, if so then it 
		 * returns the full html code by getting each part line by line and compiling it all 
		 * into one string that is passed back to main 
		 * 
		 */

		URL initial = new URL(currentURL);
	    URLConnection c = initial.openConnection();
	    
	    if(c.getContentType().contains("text/html")){
	    	
	    	BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
	    	String inputLine;

	    	while ((inputLine = in.readLine()) != null) 
	    		HTML+=inputLine;
	       
	    	in.close();
	    	return HTML;
	    	
	    }
	    else 
	    {
	    	System.out.println("the url passed in("+ currentURL+")is not HTML \n");
	    	return null;
	    }
	}
	/**
	 * this class does the actual parsing of the html string, this guy is the heavy lifter so to say, it 
	 * takes in all the html code and getes out all the links and passes back the set into crawl which in turn 
	 * passes the set of links back to main which then adds all the nodes into crawlstate which adds the nodes 
	 * into webgraph!! I KNOW! crrraaazzzyyy town :) 
	 * 
	 * 
	 * Side note: I like funny javadoc it makes it more fun to read i think. 
	 * 
	 * @author niallschavez
	 */
	
	class Parser extends HTMLEditorKit.ParserCallback {
		
		//initializing class variables 
		
        private static final int insideLink = 0;
        private static final int outsideLink = 1;
        private int state = outsideLink;
        private Set<String> links;
        private String href;


        
		Parser(Set<String> links){
			this.links = links; //pass in our list
		
		}
		//returns the set of links after pausing for 1000 miliseconds, or the pause time given by the user :) 
		public Set<String> getLinks() throws InterruptedException{
			myPause();
			return links;
			
		}
		
		
		//this prefoms the pause :) 
		private void myPause() throws InterruptedException{
			wait((long) pause);
		}
		
		//java keeps on parsin' away just keeps parsin' away !
		public void handleStartTag(HTML.Tag tag, MutableAttributeSet attrSet, int pos) {
			
			if (tag == HTML.Tag.A) {  //findout what kind of tag it is. 
				href = (String) attrSet.getAttribute(HTML.Attribute.HREF);
				state = insideLink;	
				
			}
			
		}
		
		
		@Override
		public void handleEndTag(HTML.Tag tag, int pos) {
			
			if (tag == HTML.Tag.A && state == insideLink) {
				if (href != null) {
					
					//okay this regex is a little crazy town, the idea is that i want to make sure i am not getting any general 
					//picture files so i parse them out :) its nice because then i dont even mess with them at all :) because i dont want
					//those in my graph 
					
					String  regexp  = "(?<=\\.)(jpg|zip|tar|gif|png)$";

			    	Pattern pattern = Pattern.compile(regexp);
			        Matcher matcher = pattern.matcher(href);
			       
			        if(!matcher.find()){
			        	
			        	String fixed= fixURL(href, initialPage);
			        	
			        	//System.out.println("The url being added is: "+ fixed+"\n");
			        	links.add(fixed);

			        	openPageCount++;
			        }
			        
				}
				state = outsideLink;
			}
		}		
	}
	
	
/*
 * 
 * 
 * commandline argument stuff starts here
 */

//used for the output of the commandline args
private static String getDate()
{
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	String datetime = dateFormat.format(date);
	return datetime;
}
/**
 * This method goes to MyCrawlState and checks to see the numeber of pages that have 
 * already been completed 
 * @return
 */
private static int pagesDownloaded()
{
	int pages;
	pages = ((MyCrawlState) cs).queueDone();
	return pages;
}

/**
 * printStats() prints the statistics about the graphs, nodes
 * and arrayLists.
 * 
 * @return void
 */

public static void printHelp()
{
	System.out.println("================Command Line Arguments===========================================================================");
	System.out.println("-s          Specify the URL to start at");
	System.out.println("-L          Specify where to load a previous crawl state eg. the file name/path ");
	System.out.println("-S          Specify the file to save the crawl after completion if string is null, file used is :default.wgr");
	System.out.println("-M          Specify a crawl max page parameter(max is 10000)");
	System.out.println("-D          Specify delay time between page crawls (min is 1000)");
	System.out.println("-r          Generate a report at end of crawl");
	System.out.println("-h          Print a short help message");
	System.out.println("==================================================================================================================");
}
private static Options createOptions(){
	Options Option = new Options();
	
	Option.addOption("s", true, "Specify the URL to start at");
	Option.addOption("L", true, "Specify where to load a previous crawl state eg. the file name/path");
	Option.addOption("S", true, "Specify the file to save the crawl after completion if string is null, file used is :default.wgr");
	Option.addOption("m", true, "Specify a crawl max page parameter(max is 10000)");
	Option.addOption("d", true, "Specify delay time between page crawls (min is 1000)");
	Option.addOption("r", false, "Generate a report at end of crawl");
	Option.addOption("h", false, "Print a short help message");
	
	return Option;
}

/**

 * 
 * @param parsed the parsed
 * 
 * @return void
 * 
 * @throws IOException Signals that an I/O exception has occurred.
 * @throws ClassNotFoundException 
 * @throws GraphStructureException 
 */

	private static void checkOptions(CommandLine parsed) throws IOException, ClassNotFoundException, GraphStructureException{
		
		String tempString = "";
		int x;
		
		if(parsed.hasOption("s")){
			if(parsed.hasOption("L")){
				return;
			}
			tempString = parsed.getOptionValue("s");
			System.out.println("url in "+ tempString);
			cs.items.add(tempString);
			cs.setInitialPage(tempString);
			
		}
		if(parsed.hasOption("L")){
			loadFile = parsed.getOptionValue("L");
			((MyCrawlState) cs).loadYourself(loadFile);
		}
		if(parsed.hasOption("S")){
			resultsFile = parsed.getOptionValue("S");
			cs.saveYourself(resultsFile);
		}
		if(parsed.hasOption("m")){
			tempString = parsed.getOptionValue("m");
			x = Integer.parseInt(tempString);
			if(x < 10000 || x > 1)
				CRAWL_MAX = x;
		}
		if(parsed.hasOption("d")){
			tempString = parsed.getOptionValue("d");
			x = Integer.parseInt(tempString);
			if(x < 1000){
				delay = 1000;
			}else{
				delay = x;
			}
		}
		if(parsed.hasOption("r")){
			printStats = true;
			printStats();
		}
		if(parsed.hasOption("h")){
			printHelp();
			System.exit(0);
			}
	}

	
	/**
	 * statistics to be printed it gives all the info about the crawl that was completed. 
	 * @throws GraphStructureException 
	 */
	private static void printStats() throws GraphStructureException{
		if( finished){
			System.out.println("===== Crawler Stats =====");
			System.out.println("Crawl Start Time Started: " + startDate);
			System.out.println("Time Crawl Ended: " + endDate);
			System.out.println("Start Page: "+ initialPage);
			System.out.println("Number of Pages Downloaded: " + pagesDownloaded());
			System.out.println("Total nodes discovered: " + cs.getGraph().size());
			
			//System.out.println("Total hrefs discovered: " + cs.getQueue().size());
			System.out.println("Length of to-do list: " + cs.queueLength());
			System.out.println("===== Discovered pages =====");

			for( String n: cs.getGraph().nodeSet()){
				System.out.println(n+"("+cs.getGraph().getNodeID(n)+")");
			}
			System.out.println("\n\n");
			
			System.out.println("===== TODO queue =====");
			for(String t: cs.getQueue()){
				System.out.println("   "+t);
			}
			
			System.out.println("\n\n");
			
			
			System.out.println("===== Graph edges =====");
			
			for(String s: cs.getGraph().neighborSet(initialPage))
			{	
				System.out.println(s+"->");
				for(String e: cs.getGraph().neighborSet(s))
				{
					System.out.println("     "+e);
				}
				System.out.println();
			}

			System.out.println("\n\n");
		}
		

	}
	
	
	
	

}
