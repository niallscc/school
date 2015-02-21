package tests;
import source.GrellFindableQueue;

import edu.unm.cs.cs351.f10.tdrl.p2.Findable;
import edu.unm.cs.cs351.f10.tdrl.p2.FindableQueue;

public class P2M1TimingAnalysis {
	private class node<T> implements Findable<T>, Comparable<node<T>> {
		int tester;
		
		String testString="test"; 
		
		
		@Override
		public int compareTo(node<T> o) {
			if(this.tester< o.tester) return -1;
			
			else if(this.tester > o.tester) return 1;
			
			else return 0;
			
		}

		@SuppressWarnings("unchecked")
		@Override
		
		public T getKey() {
			testString=""+tester;
			return (T)testString;
		}
	
	}
	
	//test ten inserts 
	private double ten( ){
		
		FindableQueue<Integer, node<Integer>> integerQueue=new GrellFindableQueue<Integer, node<Integer>>();
		node<Integer> testObj= new node<Integer>();

		double initTime= System.nanoTime();
		for(int i=0; i<10; i++){
			
			testObj.tester=i;
			integerQueue.insert(testObj);
			
		}		
		double finTime= System.nanoTime();
		return ((finTime/initTime)*2);
	}
	
	private double oneHundred( ){
		FindableQueue<Integer, node<Integer>> integerQueue=new GrellFindableQueue<Integer, node<Integer>>();
		node<Integer> testObj= new node<Integer>();

		double initTime= System.nanoTime();
		for(int i=0; i<100; i++){
			
			testObj.tester=i;
			integerQueue.insert(testObj);
			
		}	
		double finTime= System.nanoTime();
		return ((finTime/initTime)*2);
	}
	
	private double oneThousand( ){
		FindableQueue<Integer, node<Integer>> integerQueue=new GrellFindableQueue<Integer, node<Integer>>();
		node<Integer> testObj= new node<Integer>();

		double initTime= System.nanoTime();
		for(int i=0; i<1000; i++){
			
			testObj.tester=i;
			integerQueue.insert(testObj);
			
		}	
		double finTime= System.nanoTime();
		return ((finTime/initTime)*2);
	}
	
	private double tenThousand( ){
		FindableQueue<Integer, node<Integer>> integerQueue=new GrellFindableQueue<Integer, node<Integer>>();
		node<Integer> testObj= new node<Integer>();

		double initTime= System.nanoTime();
		for(int i=0; i<10000; i++){
			
			testObj.tester=i;
			integerQueue.insert(testObj);
			
		}	
		double finTime= System.nanoTime();
		return ((finTime/initTime)*2);
	}
	private double oneHundredThousand( ){
		FindableQueue<Integer, node<Integer>> integerQueue=new GrellFindableQueue<Integer, node<Integer>>();
		node<Integer> testObj= new node<Integer>();

		double initTime= System.nanoTime();
		for(int i=0; i<100000; i++){
			
			testObj.tester=i;
			integerQueue.insert(testObj);
			
		}	
		double finTime= System.nanoTime();
		return ((finTime/initTime)*2);
	}


	public static void main(String[] args) {

		P2M1TimingAnalysis ta= new P2M1TimingAnalysis();
		
		double tenRunTime= ta.ten();
		double oneHundredRunTime= ta.oneHundred();
		double oneThousandRunTime= ta.oneThousand();
		double tenThousandRunTime= ta.tenThousand();
		double oneHundredThousandRunTime= ta.oneHundredThousand();

		
		System.out.println("ten: "+tenRunTime);
		System.out.println("oneHundred: "+oneHundredRunTime);
		System.out.println("oneThousand: "+oneThousandRunTime);
		System.out.println("tenThousand: "+tenThousandRunTime);
		System.out.println("oneHundredThousand: "+oneHundredThousandRunTime);
		
	}

}
