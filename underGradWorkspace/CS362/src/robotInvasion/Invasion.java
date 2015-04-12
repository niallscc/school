package robotInvasion;

import java.util.ArrayList;
public class Invasion {
	
	static ArrayList <Integer>invasionSchedule= new ArrayList <Integer>();
	static int totSeconds;
	static int robotsDestroyed=0;
	public static void main(String args[]){

		/* 
		 * this algorithm was given by the question
		 * 
		 */
		int[] tempArray= {1,1,1,7 };
		totSeconds= tempArray.length;
		for(int i: tempArray){
			invasionSchedule.add(i);
		}
		//System.out.println(memoizedOPT(0,0));
		schedule_EMP( invasionSchedule.size()-1);
		System.out.println("The max number of robots destroyed is: "+ robotsDestroyed);
	}
	
	public static int[][] memoOPT;

	public static int memoizedOPT(int currentTime, int timeWaited){
		if(memoOPT == null){
			memoOPT = new int[totSeconds][totSeconds];
			
			for(int i = 0; i < memoOPT.length; i++){
				for(int j = 0; j < memoOPT[0].length; j++){
					memoOPT[i][j] = -1;
				}
			}
		}
		if(currentTime >= memoOPT.length || timeWaited >= memoOPT[0].length)
			return 0;
		if(memoOPT[currentTime][timeWaited] == -1)
			memoOPT[currentTime][timeWaited] = OPT(currentTime, timeWaited);
		
		return memoOPT[currentTime][timeWaited];
	}
	public static int OPT(int currentTime, int timeWaited){
		
		int numRobots= invasionSchedule.get(currentTime);
		
		return Math.max(Math.min(numRobots, f(timeWaited)) + memoizedOPT(currentTime +1, 0),memoizedOPT(currentTime + 1, 1));
		 
	}
	//This is the algorithm given by the book.
	public static int schedule_EMP(int currentTime){
		int j=0;
		int numRobots= invasionSchedule.get(currentTime);

		while(j < invasionSchedule.size() && numRobots > f(j) ){
			j++;
		}
		if( j > currentTime)
			j=currentTime;
		//System.out.println("detonated EMP at time "+ ++currentTime+" and destroyed "+ Math.min(f(j), numRobots)+" robots" );
		robotsDestroyed+=Math.min(f(j), numRobots);
		
		if( (invasionSchedule.size()- j - 1) >= 0){
				for( int i = 0; i <= j; i++)
					invasionSchedule.remove(invasionSchedule.size() -1);
				if( invasionSchedule.size() > 0 )
					schedule_EMP( invasionSchedule.size() - 1);
		}
		return robotsDestroyed;
	}
	//This is the function for f 
	public static int f(int j){
		int[] array= {2,3,5,7};
		return array[j];
	}
}
