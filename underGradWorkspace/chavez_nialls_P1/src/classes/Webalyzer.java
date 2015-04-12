package classes;

import java.io.IOException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Webalyzer<T>{
	
	String filenameIn=null;
	ConcGraphAnalyzer<T> cga;
	int counter=0;
	
	public Webalyzer(){
		
		cga=new ConcGraphAnalyzer<T>();
	}
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws ParseException, IOException {
		Webalyzer w= new Webalyzer ();
		BasicParser p = new BasicParser();
		Options Option = createOptions();
		CommandLine parsed = null;
		parsed = p.parse(Option,args);

		try {
			w.checkOptions(parsed);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private void checkOptions(CommandLine parsed) throws IOException, ClassNotFoundException{
		
		if(parsed.hasOption("L")){
			
			filenameIn=parsed.getOptionValue("L");
			filenameIn="crawl_dump.dat";
			System.out.println("Filename is:"+ filenameIn);
			cga.loader(filenameIn);
		}
		if(parsed.hasOption("o")){
			System.out.println("---------Average Outdegree Distribution--------");
			System.out.println(cga.avgOutDegree());
			System.out.println("-----------------------------------------------");
		}
		if(parsed.hasOption("O")){
			System.out.println("----------Full Outdegree Distribution----------");
			//System.out.println(cga.outDegreeDistribution());
			for(double i: cga.outDegreeDistribution()){
				System.out.println("["+counter+"] "+i);
				counter++;
			}
			System.out.println("-----------------------------------------------");
		}
		if(parsed.hasOption("n")){
			System.out.println("---------------Minimum Outdegree---------------");
			System.out.println(cga.minOutDegree());
			System.out.println("-----------------------------------------------");
		}
		if(parsed.hasOption("N")){
			System.out.println("---------------Maximum Outdegree---------------");
			System.out.println(cga.maxOutDegree());
			System.out.println("-----------------------------------------------");
		}
		if(parsed.hasOption("i")){
			System.out.println("---------Average Indegree Distribution---------");
			System.out.println(cga.avgInDegree());
			System.out.println("-----------------------------------------------");
		}
		if(parsed.hasOption("I")){
			System.out.println("------------Indegree Distribution--------------");
			//System.out.println(cga.outDegreeDistribution());
			for(double i: cga.inDegreeDistribution()){
				System.out.println("["+counter+"] "+i);
				counter++;
			}
			System.out.println("-----------------------------------------------");
		}
		if(parsed.hasOption("j")){
			System.out.println("--------------Minimum Indegree-----------------");
			System.out.println(cga.minInDegree());
			System.out.println("-----------------------------------------------");
		}
		if(parsed.hasOption("J")){
			System.out.println("--------------Maximum Indegree-----------------");
			System.out.println(cga.maxInDegree());
			System.out.println("-----------------------------------------------");
		}
		if(parsed.hasOption("d")){
			System.out.println("-------------Average Shortest Path-------------");
			System.out.println(cga.avgShortestPathDistance());
			System.out.println("-----------------------------------------------");
		}
		if(parsed.hasOption("D")){
			System.out.println("-------------Connectivity Matrix---------------");
			cga.allPairsShortestPaths();

			System.out.println("-----------------------------------------------");
		}
		if(parsed.hasOption("r")){
			System.out.println("----------------Size of Diameter---------------");
			System.out.println(cga.diameter());
			System.out.println("-----------------------------------------------");
		}
		if(parsed.hasOption("c")){
			System.out.println("-----------------Number of SCC's---------------");
			System.out.println(cga.countSCCs());
			System.out.println("-----------------------------------------------");
		}
		if(parsed.hasOption("C")){
			System.out.println("------------------Largest SCC------------------");
			System.out.println(cga.maxSCCSize());
			System.out.println("-----------------------------------------------");
		}
		if(parsed.hasOption("h")){
			printHelp();
		}	
	}
	private static Options createOptions() {
		Options Option = new Options();
		
		Option.addOption("L", false, "Specifies file to load a crawlstate.");
		Option.addOption("o", false, "Print report on the average OUTDEGREE of the graph.");
		Option.addOption("O", false, "Print report on the full OUTDEGREE of the graph.");
		Option.addOption("n", false, "Print report on the minimum OUTDEGREE of the graph.");
		Option.addOption("N", false, "Print report on the maximum OUTDEGREE of the graph.");
		Option.addOption("i", false, "Print report on the average INDEGREE of the graph.");
		Option.addOption("I", false, "Print report on the full INDEGREE of the graph.");
		Option.addOption("j", false, "Print report on the minimum INDEGREE of the graph.");
		Option.addOption("J", false, "Print report on the maximum INDEGREE of the graph.");
		Option.addOption("d", false, "Print report on the average shortest-path distance between mutually-reachable nodes in the graph.");
		Option.addOption("D", false, "Print report on the complete shortest-path distance matrix between all pairs of nodes in the graph.");
		Option.addOption("r", false, "Print report on the diameter of the graph.");
		Option.addOption("c", false, "Print report on the number of strongly connected components in the graph.");
		Option.addOption("C", false, "Print report on the size of the largest strongly connected component in the graph.");
		Option.addOption("h", false, "Print a short help message and exit.");
		

		return Option;
	}
	public void printHelp()
	{
		System.out.println("================Command Line Arguments===========================================================================");
		System.out.println("-L          Specifies file to load.");
		System.out.println("-o          Print report on the average OUTDEGREE of the graph.");
		System.out.println("-O          Print report on the full OUTDEGREE of the graph.");
		System.out.println("-n          Print report on the minimum OUTDEGREE of the graph.");
		System.out.println("-N          Print report on the maximum OUTDEGREE of the graph.");
		System.out.println("-i          Print report on the average INDEGREE of the graph.");
		System.out.println("-I          Print report on the full INDEGREE of the graph.");
		System.out.println("-j          Print report on the minimum INDEGREE of the graph.");
		System.out.println("-J          Print report on the maximum INDEGREE of the graph.");
		System.out.println("-d          Print report on the average shortest-path distance between mutually-reachable nodes in the graph.");
		System.out.println("-D          Print report on the complete shortest-path distance matrix between all pairs of nodes in the graph.");
		System.out.println("-r          Print report on the diameter of the graph.");
		System.out.println("-c          Print report on the number of strongly connected components in the graph.");
		System.out.println("-C          Print report on the size of the largest strongly connected component in the graph.");
		System.out.println("-h          Print a short help message and exit.");
		System.out.println("==================================================================================================================");
	}

	
	
}
