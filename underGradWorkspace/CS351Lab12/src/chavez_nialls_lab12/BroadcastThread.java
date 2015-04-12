package chavez_nialls_lab12;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;


public class BroadcastThread implements Runnable
{

	ArrayList<ObjectOutputStream> _OUTS; //Vector of all outputStreams for each of the clients sockets 
	LinkedBlockingQueue<String> _MAIL_BOX;
	
	/**
	 * 
	 * 
	 * 
	 * @param lbq MailBox for incoming messages 
	 */
	public BroadcastThread(LinkedBlockingQueue<String> lbq)
	{		
		_OUTS = new ArrayList<ObjectOutputStream>();
		_MAIL_BOX = lbq;
			
		new Thread(this).start();
		
	}

	/**
	 * Adds the ObjectOutputStream to the _OUTS list, so that when a message needs to be sent to all threads 
	 * It will be sent by iterating over that ObjectOutPutStream list.
	 * 
	 * @param o
	 */
	public void addOut(ObjectOutputStream o)
	{
		_OUTS.add(o);
	}
	
	@Override
	public void run() {
		
		while (true)
		{
			
			try {
				
					//TODO Blocking call on the Mail Box to get the next object off the Queue
					String temp =_MAIL_BOX.take();
				
					int j = _OUTS.size();
				
					for (int i = 0; i < j; i++)
					{
						try
						{	
							//TODO Write the object out to each OutPutStream
							_OUTS.get(j).writeBytes(temp);
						}
						//NOTE: OK this is a dirty exception handling but it allows clients to disconnect and new clients to reconnect
						// without blowing up the server. 
						catch (SocketException se)
						{
							_OUTS.remove(i);
							j--;
							i--;
							
						}
					}
				
				} 				
				
		
			catch (IOException e) {
			
				e.printStackTrace();
			}
			catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			
		}
		
		
	}

}


