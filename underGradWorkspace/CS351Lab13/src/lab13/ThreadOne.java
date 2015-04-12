package lab13;

public class ThreadOne implements Runnable
{

	ResOne r1 = null;
	ResTwo r2 = null;
	Object l;
	public ThreadOne(ResOne res1, ResTwo res2, Object lock)
	{
		r1 = res1;
		r2 = res2;
		l= lock;
	}

	@Override
	public void run() 
	{
		//Get ResOne Lock
		
		synchronized (l)
		{
			System.out.print("ThreadOne has :");
			r2.print();
			
			
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
		
				e.printStackTrace();
			}
			
			synchronized (l)
			{
				System.out.print("ThreadOne has :");
				r1.print();
			}
		
		}
		
		
		
	}

}
