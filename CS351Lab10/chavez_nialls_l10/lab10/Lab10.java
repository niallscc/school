package lab10;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;


public class Lab10 implements Runnable
{
	
	CountDownLatch latch;	
	ArrayList<Integer> BUFFER;
	boolean globAddSub;
	int upCount=0;
	int downCount=0;
	
	public Lab10(ArrayList<Integer> _buffer, int size, boolean addOrSubtract, CountDownLatch l)
	{
		//TODO
		BUFFER= _buffer;
		latch=l;
		globAddSub=addOrSubtract;
	
	}
	

	public void run() 
	{
		
		//TODO
		
		// Add the value of 1 to size or -1 to -size to the BUFFER ArrayList. 
		//(use the value of addorSubtract to determine if it should be from [-1, -n] or [1, n])
		if(globAddSub)
			BUFFER.add(++upCount);
		
		else
			BUFFER.add(--downCount);
		
	    //Count down the latch, when the thread is done (a.k.a. the run method finishes) 
		latch.countDown();
	}

	
	public static void main(String args[])
	{
		int size=1000;

		//TODO
		//Create the ArrayList and pass the same ArrayList to both threads. 
		
		ArrayList<Integer> myBuffer= new ArrayList<Integer>();
		//Create the CountDownLatch to be passed to both threads.
		
		CountDownLatch cdl= new CountDownLatch(size);
		//Start the threads.
		
		new Thread(new Lab10(myBuffer, size, true, cdl)).start();
		new Thread(new Lab10(myBuffer, size, false, cdl)).start();
		
		//Use latch.await() to wait for both threads to stop executing. 
		try {
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Then print out the values in the ArrayList.
		
		System.out.println("myBuffer: "+myBuffer.toString());
		
	}

	
}
