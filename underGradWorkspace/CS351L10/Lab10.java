package Lab10;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;


public class Lab10 implements Runnable
{
	
	
	CountDownLatch latch;	
	ArrayList<Integer> BUFFER;

	
	public Lab10(ArrayList<Integer> _buffer, int size, boolean addOrSubtract, CountDownLatch l)
	{
		//TODO
	}
	

	public void run() 
	{
		//TODO
		// Add the value of 1 to size or -1 to -size to the BUFFER ArrayList. 
		//(use the value of addorSubtract to determine if it should be from [-1, -n] or [1, n])
		
	    //Count down the latch, when the thread is done (a.k.a. the run method finishes) 
		
	}

	
	public static void main(String args[])
	{
		//TODO
		//Create the ArrayList and pass the same ArrayList to both threads. 
		//Create the CountDownLatch to be passed to both threads.
		//Start the threads.
		//Use latch.await() to wait for both threads to stop executing. 
		//Then print out the values in the ArrayList.

		
	}

	
}
