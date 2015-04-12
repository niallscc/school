package main;

import java.util.Arrays;
import java.util.List;

public class DNA {
	
	private Boolean promoter; 
	private String name; 
	private int[] sequence ;
	private Double gain =-1.0;
	
	public DNA(String line){
		boolean newOrdering = false;
		List<String> data= Arrays.asList(line.split(","));
		if( data.size()==1){
			newOrdering = true;
			data= Arrays.asList(line.split(" "));
		}
		
		if(data.get(0).equals("+"))
			promoter = true;
		else if( data.get(0).equals("-"))
			promoter = false; 
		else{
			if( data.get(1).equals("+"))
				promoter = true;
			else if(data.get(1).equals("-"))
				promoter = false;
			else{
				System.out.println("No valid DNA Format found. Exiting.");
				System.exit(0);
			}
		}
		if(!newOrdering)
			name = data.get(1);
		else{
			name= "unknown";
		}
		// a = 0 ;
		// c = 1;
		// g = 2;
		// t = 3;
		char[] sequenceTemp;
		if(!newOrdering)
			sequenceTemp = data.get(2).trim().toCharArray();
		else
			sequenceTemp = data.get(0).trim().toCharArray();
		sequence = new int[sequenceTemp.length];
		for(int i =0; i< sequenceTemp.length; i++){
			if( sequenceTemp[i] == 'a')
				sequence[i]=0;
			else if (sequenceTemp[i]=='c')
				sequence[i]=1;
			else if (sequenceTemp[i]=='g')
				sequence[i]=2;
			else if(sequenceTemp[i]=='t')
				sequence[i]=3;
		}
	}
	public Boolean isPromoter(){
		return this.promoter;
	}
	public String getName(){
		return name;
	}
	public int[] getSequence(){
		return this.sequence;
	}
	public int getSequenceAt(int index){
		return this.sequence[index];
	}
	public void setMaxGain(Double gain){
		this.gain = gain;
	}
	public double getMaxGain(){
		return this.gain;
	}
	@Override
	public String toString(){
		return "Class: "+ name+"\n     promoter: " + promoter+"\n     sequence: "+ Arrays.toString(sequence); 
	}
}
