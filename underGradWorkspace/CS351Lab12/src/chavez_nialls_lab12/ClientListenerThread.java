package chavez_nialls_lab12;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.SwingUtilities;

class ClientListenerThread implements Runnable
{
	
	Socket _SOCKET;
	ObjectInputStream _IN;
	Client _CLIENT;
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param s Socket to listen over
	 * @param c A reference to client this thread is listening for
	 */
	public ClientListenerThread(Socket s, Client c)
	{
		_SOCKET = s;
		_CLIENT = c;
		
		try {
			// Create a new ObjectInputStream
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
			
			try {
				String message;				
				message = (String) _IN.readObject();				
				
				//Create a new Runnable class that will update the client's JTextArea when 
				//a message is received from the server
				
				class runner implements Runnable
				{
					String m;
					
					public runner(String s)
					{
						m = s;
					}
					
				    public void run() {				        
				    	 _CLIENT.jta.append(m);				    	  
				    }
				}
				
				// Have the AWT execute the Runnable class
				SwingUtilities.invokeLater(new runner(message));				
				
			} 
			catch (SocketException se)
			{
				// If something goes wrong stop listening over the socket
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