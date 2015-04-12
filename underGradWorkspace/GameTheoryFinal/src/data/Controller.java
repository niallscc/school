package data;

import java.util.Random;

public class Controller {
	String output;
	Creature1 c1;
	Creature2 c2;
	
	public Controller( String[] fitness, String popC1, String popC2, 
			String reproduceThreshold, String childs){

		/*
		 * converting the input into integers
		 */
		int pop1= Integer.parseInt(popC1);
		int pop2= Integer.parseInt(popC2);
		int rT= Integer.parseInt(reproduceThreshold);
		int numChilds= Integer.parseInt(childs);
		/*
		 * this converts the array of String data into 
		 * an array of int data. 
		 */
		
		int[] cFitness= new int[fitness.length];
		int counter=0;
		for( String f: fitness){
			cFitness[counter]=Integer.parseInt(f);
			counter++;
		}
		/*
		 * this block puts in the species in a random order and makes sure that there 
		 * are creatures left in that population to put in. 
		 */
		Species[] spec= new Species[(pop1+pop2)];
		int tempBound= pop1+pop2;
		Random r= new Random (System.currentTimeMillis());
		r.nextBoolean();
		
		/*
		 * Initializing c1 and c2;
		 */
		
		c1= new Creature1(cFitness[0], cFitness[1], cFitness[4], numChilds,rT);
		c2= new Creature2(cFitness[5],cFitness[3], cFitness[2], numChilds, rT);
		for(int i =0; i < tempBound; i++){
			if(r.nextBoolean()){
				if( pop1!=0){
					spec[i]= c1;
					pop1--;
				}
				else{
					spec[i]= c2;
					pop2--;
				}
			}
			else {
				if( pop2!=0){
					spec[i]= c2;
					pop2--;
				}
				else{
					spec[i]= c1;
				}
			}
		}
		calculate(cFitness,spec, Integer.parseInt(popC1), Integer.parseInt(popC2), rT);
	}
	
	public void calculate(int[] cFitness, Species[] spec, int pop1, int pop2, int rT){
		int tempBound= (pop1+pop2) - 1;
		int first = 0, second = 1;	 	
		while(second <= tempBound ){
			
			/*
			 * if the the two adjacent creatures are the same, and when they meet their 
			 * utility is above the reproducing threshold,then they can reproduce
			 * else they dont get to reproduce
			 */
			if(spec[first] == spec[second]){
				if( spec[first].mySelf() > rT){
					spec[first].reproduce(spec[first].mySelf());
				}	
			}
			/*
			 * if spec[first] is Creature1then calculate Creature1 vs Creature2
			 * 
			 */
			else if (spec[first]==c1){
				if(spec[first].meVshim() >= rT)
					spec[first].reproduce(spec[first].meVshim());
				
				if(spec[second].himVsme() >= rT){
					spec[second].reproduce(spec[second].himVsme());
				}
			}
			/*
			 * This case can only be creature2 vs creature1
			 */
			else {
				if(spec[first].meVshim() >= rT)
					spec[first].reproduce(spec[first].meVshim());
				
				if(spec[second].himVsme() >= rT){
					spec[second].reproduce(spec[second].himVsme());
				}
			}
			
			first +=2; 
			second+=2;
			}
		output= "The population of Creature1 is: "+c1.getPopulation()+ 
		        " \nThe Population of Creature2 is "+ c2.getPopulation();
		}
	
	public String getEndState(){
		return output;
	}
	public int getPopC1(){
		return c1.getPopulation();
	}
	public int getPopC2(){
		return c2.getPopulation();
	}
}
