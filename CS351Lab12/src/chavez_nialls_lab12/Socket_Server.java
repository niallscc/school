package chavez_nialls_lab12;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class Socket_Server 
{
	ServerSocket _SERVER_SOCKET;
	BroadcastThread _BCThread; // Broad Casting Thread
	LinkedBlockingQueue<String> _MAIL_BOX;
	
	/**
	 * Creates a new Socket_Server which listens via a SocketServer for incoming connections
	 * 
	 * 
	 * @param port The port to listen to the for new Socket Connections
	 */
	public Socket_Server(int port)
	{
		try {
			
			_MAIL_BOX = new LinkedBlockingQueue<String>();
			_SERVER_SOCKET = new ServerSocket(port);
			_BCThread = new BroadcastThread(_MAIL_BOX);
			processConnections();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * This method loops indefinitely. It blocks on _SERVER_SOCKET.accept() and waits for a new connection
	 * It then spawns a new listener thread for the connection and creates a socket to communicate over.   
	 * 
	 * 
	 */
	private void processConnections()
	{
		System.out.println("Waiting for Connection ...");
		
		while (true)
		{
			
			try {
				
				//TODO Accept the Connection
				Socket mySocket= _SERVER_SOCKET.accept();
				//TODO Create a new ListenerThread for this Client
				
				ListenerThread lt= new ListenerThread(mySocket, _MAIL_BOX);
				//TODO Add the outputStream to the BroadCastThread's list. 
				_BCThread.addOut(new ObjectOutputStream( mySocket.getOutputStream()));
				
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	public static void main(String args[])
	{
		
		new Socket_Server(7777);
		
	}
	
	
	
}
