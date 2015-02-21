package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingQueue;

import events.GladdosEvent;
/**
 * A class that implements Runnable to act as a listener thread on the given client socket.
 * It receives Events from a client and places them in the main mailbox to be handled by
 * the event handler
 * 
 * @author Ryan Hammer
 *
 */
public class ClientListener implements Runnable {
	/**
	 * The client's socket
	 */
	private Socket socket;
	/**
	 * The input stream for the client.
	 */
	ObjectInputStream ois;
	/**
	 * The int mapping for the specific client in the hash map
	 */
	private int clientID;
	/**
	 * The server
	 */
	private Server server;
	/**
	 * The mailbox to place events received from client on.
	 */
	LinkedBlockingQueue<GladdosEvent> mailbox;
	/**
	 * A flag for the thread status
	 */
	private boolean running = true;


	public ClientListener(Socket newSocket, int id, LinkedBlockingQueue<GladdosEvent> inbox, Server server) {
		socket = newSocket;
		try {
			ois = new ObjectInputStream(this.socket.getInputStream());
			socket.setSoTimeout(500000);
		} catch (SocketException e) {
			//Remove from the server
			server.removeClient(id);
			
			//Remove them from the broadcast threads HashMap
			server.getBroadcaster().removeOut(id);
			
			running = false;
			server.writeToLogFile("Client " + id + " has timed out");
		} catch (IOException e) {
			System.err.println("IOException trying to create ObjectInputStream for new client!");
		} 

		clientID = id;
		mailbox = inbox;
		this.server = server;

		//Create a new thread using this object and start it
		Thread t = new Thread(this);
		t.start();
	}
	public String getClientIP() {
		return socket.getInetAddress().toString();
	}
	public Socket getSocket() {
		return socket;
	}
	public void setConnected(boolean connect) {
		running = connect;
	}
	public boolean isRunning() {
		return running;
	}
	/**
	 * The method that performs all of the listening on a specific client socket. It will
	 * run until an exception is thrown, which indicates, a closed connection or a connection
	 * failure.
	 * 
	 * This method will receive updates from the client, and will then place them in a synchronized
	 * queue to be processed by the main game world.
	 */
	@Override
	public void run() {
		while(running) {
			try {
				GladdosEvent clientEvent = (GladdosEvent) ois.readObject();
				mailbox.put(clientEvent);
			}
			catch (IOException e) {
				running = false;
				//Remove the client from the list, it means they disconnected.
				server.removeClient(clientID);
				server.writeToLogFile("Client: " + this.getClientIP() + " Disconnected");
			} catch (ClassNotFoundException e) {
				System.err.println("Class not found!!");
			} catch (InterruptedException e) {
				System.err.println("Interrupted while waiting to put GladdosEvent in mailbox");
			}
		}
	}

}