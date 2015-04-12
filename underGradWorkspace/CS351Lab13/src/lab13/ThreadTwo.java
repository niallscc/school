package lab13;

public class ThreadTwo implements Runnable
{
	
	ResOne r1 = null;
	ResTwo r2 = null;
	Object lock;
	public ThreadTwo(ResOne res1, ResTwo res2, Object l)
	{
		r1 = res1;
		r2 = res2;
		lock=l;
	}

	@Override
	public void run() 
	{
		//Get ResOne Lock
		synchronized (lock)
		{
			System.out.print("ThreadTwo has :");
			r1.print();
		
			
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized (lock)
			{
				System.out.print("ThreadTwo has :");
				r2.print();
			}
		
		}
		
		
		
	}

}
