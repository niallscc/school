package computingJob;

import java.util.ArrayList;
import java.util.LinkedList;

public class Computing {
	
	static String tot= null;
	public static void main(String[] args) {
		
		int[] tempCompA={4,1,1};
		int[] tempCompB= {3,19,1};
		
		ArrayList<Integer> computerA= new ArrayList<Integer>();
		ArrayList<Integer> computerB= new ArrayList<Integer>();
		
		for(int i= 0; i < tempCompA.length; i++){	
			
			computerA.add(tempCompA[i]);
			computerB.add(tempCompB[i]);
		
		}
		
		//System.out.println("Using the algorithm provided, the plan is: "+ given(computerA, computerB).toString());
		int best= OPT(computerA, computerB, 0);
		System.out.println("The most computation time you can get is:" +best);
	}
	
	static ArrayList<Integer> currentComp = null, possibleComp=null;

	static int OPT(ArrayList<Integer> current, ArrayList<Integer> other, int currentTime){
		
		if( currentTime < current.size()-2 )
			return Math.max(Math.max(current.get(currentTime) + OPT(current, other, currentTime+1), 
							current.get(currentTime)+ OPT(other, current, currentTime+2)),
							(Math.max(other.get(currentTime) + OPT(other, current, currentTime+1), 
									other.get(currentTime)+ OPT(current, other, currentTime+2))));
		else if( currentTime< current.size()-1)
			return (current.get(currentTime) + OPT(current, other, currentTime+1));
		else 
			return current.get(currentTime);
		
	}
	
	/**
	 * This is the given algorithm from the book.
	 * @param A = Super computer A computation time 
	 * @param B = Super computer B computation time
	 * @return The "optimal" plan returned by the incorrect algorithm.
	 */
	static LinkedList<String> given(ArrayList<Integer> A, ArrayList<Integer> B){
		int currentMinute=0;
		int totalMinutes=A.size();
		LinkedList<String> plan= new LinkedList<String>();
		if(currentMinute==0){
			if( A.get(0) > B.get(0))
				plan.add("A");
			else
				plan.add("B");
			
			currentMinute++;
		}
		while(currentMinute < totalMinutes ){
			if(plan.get(currentMinute-1).equals("A")){
				if( (B.size() >currentMinute+1) && (B.get(currentMinute+1) >= (A.get(currentMinute+A.get(currentMinute+1))))){
					plan.add("MOVE");
					currentMinute++;
					plan.add("B");
					currentMinute++;
				}
				else{
					plan.add("A");
					currentMinute++;
				}
			}
			else{
				if( (A.size() >currentMinute+1) &&  ( A.get(currentMinute+1) >= (B.get(currentMinute)+B.get(currentMinute+1)))){
					plan.add("MOVE");
					currentMinute++;
					plan.add("A");
					currentMinute++;
				}
				else{
					plan.add("B");
					currentMinute++;
				}
			}	
		}
		return plan;
	}
}
