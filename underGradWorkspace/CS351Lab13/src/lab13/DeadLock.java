package lab13;

public class DeadLock 
{

	public static void main(String args[])
	{
		
		ResOne r1 = new ResOne();
		ResTwo r2 = new ResTwo();
		Object lock = new Object();
		
		new Thread(new ThreadOne(r1,r2, lock)).start();
		new Thread(new ThreadTwo(r1,r2, lock)).start();
		
	}
	
}
