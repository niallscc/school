package chavez_nialls_lab12;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingQueue;

class ListenerThread implements Runnable
{
	Socket _SOCKET;
	ObjectInputStream _IN;
	LinkedBlockingQueue<String> _MAIL_BOX;
	
	/**
	 * Creates a new Thread and starts it. This thread will listen via the blocking call readObject over the socket
	 * 
	 * 
	 * 
	 * @param s The Socket created to communicate over
	 * @param lbq The LinkedBlockingQueue which is used as a Mail Box 
	 */
	public ListenerThread(Socket s, LinkedBlockingQueue<String> lbq)
	{
		_MAIL_BOX = lbq;
		_SOCKET = s;
		try {
			_IN = new ObjectInputStream(_SOCKET.getInputStream());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		new Thread(this).start();
		
	}
	
	@Override
	public void run() {	
		boolean connected = true;
		
		while (connected)
		{
						
			String message;
			try {
				
				//TODO Reads a message in over the socket with readObject()
				//Then it uses the Mail Box to communicate with the BroadCastThread to disperse this message to all clients
				message= (String) _IN.readObject();
			} 
			catch (SocketException se)
			{
				// this is here to close the Listener thread if the client stops, this technically is the wrong the way to do this
				// You should be using a heart beat to detect when the client has stopped communicating  
				connected = false;
			}
			catch (IOException e) {
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
			
			
		}
		
	}
}
