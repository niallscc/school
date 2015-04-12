package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import events.Event;

/**
 * I used this from the lab, I still need to modify it and clean it up
 * @author ryanhj
 *
 */
public class BroadcastThread implements Runnable
{
	/**
	 * Holds all of the output streams for every client.
	 */
	ArrayList<ObjectOutputStream> clients;
	/**
	 * Mailbox for game events
	 */
	private Queue<Event> mailbox = new ConcurrentLinkedQueue<Event>();

	/**
	 * Constructs a new Broadcast Thread Object. The server should only ever create one of these.
	 * 
	 * @param container MailBox for incoming messages 
	 */
	public BroadcastThread(Queue<Event> container)
	{		
		clients = new ArrayList<ObjectOutputStream>();
		mailbox = container;

		new Thread(this).start();
	}

	/**
	 * Adds the ObjectOutputStream to the clients list, so that when an event needs to be sent to all threads 
	 * It will be sent by iterating over that ObjectOutPutStream list.
	 * 
	 * @param o
	 */
	public synchronized void addOut(ObjectOutputStream o)
	{
		clients.add(o);
	}

	@Override
	public void run() {
		while (true)
		{
			try {
				Event gameEvent = mailbox.poll();

				int j = clients.size();

				for (int i = 0; i < j; i++)
				{
					try
					{
						clients.get(i).writeObject(gameEvent);

					}
					//NOTE: OK this is a dirty exception handling but it allows clients to disconnect and new clients to reconnect
					// without blowing up the server. 
					catch (SocketException se)
					{
						clients.remove(i);
						j--;
						i--;

					}
				}

			} 				


			catch (IOException e) {

				e.printStackTrace();
			}
		}


	}

}


