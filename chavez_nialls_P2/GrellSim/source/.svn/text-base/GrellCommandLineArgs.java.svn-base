package source;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import edu.unm.cs.cs351.f10.tdrl.p2.EmployeeType;

public class GrellCommandLineArgs {
	int numAssemblyLines=0, numManagers=0;
	int mouthBreathers=0, drone=0, guru=0;
	int assignment=-1, prioritization=-1;
	int runTime;

	String pPolicy, aPolicy;
	HashMap<EmployeeType, Integer > emps= new HashMap<EmployeeType, Integer>();
	
	/**
	 * A Temporary main. This file is mostly used for debugging purposes. 
	 * @param <S>
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 */
	public static <S> void main(String[] args) throws ParseException, IOException {

		GrellCommandLineArgs gcla = new GrellCommandLineArgs();
		
		BasicParser p = new BasicParser();
		Options Option = createOptions();
		CommandLine parsed = null;
		parsed = p.parse(Option,args);

		try {
			gcla.checkOptions(parsed);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Inside GCLA EMPS "+gcla.emps);
		//System.out.println("Prioritization: "+gcla.prioritization+"\nNumAssLines: "+gcla.numAssemblyLines+"\nAssignment: "+gcla.assignment);
		@SuppressWarnings("unused")

		GrellSim<S> gs= new GrellSim<S>(gcla.emps, gcla.prioritization, gcla.assignment, gcla.numAssemblyLines, gcla.runTime);
		
	}
	
	/**
	 * Checks the commandline args
	 * @param parsed
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void checkOptions(CommandLine parsed) throws IOException, ClassNotFoundException{
		
		/*
		 * Number of managers
		 */
		if(parsed.hasOption("m")){

			String tempVal = parsed.getOptionValue("m");
			
			numManagers = Integer.parseInt(tempVal);
			emps.put(EmployeeType.MANAGER, numManagers);
			//("The numManagers: "+ numManagers);
			//System.out.println(emps);
			
		}
		/*
		 * Number of employees
		 */
		if(parsed.hasOption("eD")){
			String tempVal = parsed.getOptionValue("eD");
			drone = Integer.parseInt(tempVal);
			//System.out.println("The num drones: "+ drone+"TempVal ="+tempVal);
			
			emps.put(EmployeeType.DRONE, drone);
			//System.out.println(emps);
		}
		if(parsed.hasOption("eMB")){
			
			String tempVal = parsed.getOptionValue("eMB");
			
			mouthBreathers = Integer.parseInt(tempVal);
			//System.out.println("The num mouth breathers: "+ mouthBreathers);
			emps.put(EmployeeType.MOUTH_BREATHER,mouthBreathers);
			//System.out.println(emps);
			
		}
		if(parsed.hasOption("eG")){
			
			String tempVal = parsed.getOptionValue("eG");
			
			guru = Integer.parseInt(tempVal);
			//System.out.println("The num guru's: "+ guru);
			emps.put(EmployeeType.GURU, guru);
			//System.out.println(emps);
		}
		
		/*
		 * assignment polcy 
		 */
		if( parsed.hasOption("aP")){
			
			String[] assignmentPolicy= {"RoundRobin", "ShortestFirst","First Empty"};
			String tempString = parsed.getOptionValue("aP");
			aPolicy= tempString; 
			
			
			for(int i =0; i <assignmentPolicy.length; i++){
				if( aPolicy.equals(assignmentPolicy[i])){
					assignment = i;
				}
			}
			
			if( assignment==-1){
				System.err.println("AssignmentPolicy not found, Defaulting to RoundRobin");
				assignment=0;
			}
			
			//System.out.println("The assignment policy: "+ aPolicy+" ("+assignment+")");
		}
		
		/*
		 * priority policy
		 */
		if( parsed.hasOption("pP")){
			
			String[] prioritizationPolicy= {"FIFO", "LIFO","HighestVal"};
			
			String tempString = parsed.getOptionValue("pP");
			
			pPolicy= tempString; 
			
			for(int i =0; i <prioritizationPolicy.length; i++){
				if( pPolicy.equals(prioritizationPolicy[i])){
					prioritization = i;
				}
			}
			
			if( prioritization==-1){
				System.err.println("PrioritizationPolicy not found, Defaulting to FIFO");
				prioritization=0;
			}
			
			//System.out.println("The prioritization policy : "+ pPolicy+" ("+prioritization+")");
		}
		if(parsed.hasOption("r")){
			
			String tempString = parsed.getOptionValue("r");
			runTime = Integer.parseInt(tempString);
			//System.out.println("The run time is: "+ runTime);
		
		}
		/*
		 * Number of assembly lines
		 */
		if(parsed.hasOption("a")){
			
			String tempString = parsed.getOptionValue("a");
			numAssemblyLines = Integer.parseInt(tempString);
			//("The assembly lines: "+ numAssemblyLines);
		}
	}
	/**
	 * Creates the command line arguments 
	 * @return
	 */
	private static Options createOptions() {
		
		Options Option = new Options();
		
		Option.addOption("m", true, "States that you want one manager on your assembly line");
		Option.addOption("eD", true, "States that you want one drone on your assembly line");
		Option.addOption("eMB", true, "States that you want one mouth breather on your assembly line");
		Option.addOption("eG", true, "States that you want one guru on your assembly line");
		Option.addOption("pP", true, "States the prioritization policy on your assembly line");
		Option.addOption("aP", true, "States the assignment policy on your assembly line");
		Option.addOption("a", true, "Specifies the number of assembly lines");
		Option.addOption("r", true, "Specifies the run time for the assembly line");
		Option.addOption("h", false, "Print a short help message and exit.");
		return Option;
	}
	/**
	 * Help! I need somebody! Help ! Not just Anybody! Help ! ... sorry... couldnt resist.. 
	 */
	public void printHelp()
	{
		System.out.println("================Command Line Arguments===========================================================================");
		System.out.println("-m          Specifies The number of managers on the assembly line.");
		System.out.println("-eD         Specifies The number of drones on the assembly line.");
		System.out.println("-eMB        Specifies The number of MouthBreathers on the assembly line.");
		System.out.println("-eG		    Specifies The number of Guru's on the assembly line.");
		System.out.println("-pP        	Specifies prioritization poilicy on the assembly line.");
		System.out.println("-aP         Specifies the assignment policy on the assembly line.");
		System.out.println("-a          Specifies The number of assembly lines.");
		System.out.println("-r          Specifies the runtime of the simulation");
		System.out.println("-h          Print a short help message and exit.");
		System.out.println("==================================================================================================================");
	}


	
}
