package server;

import java.io.*;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import events.Event;
import events.moveEvent;

public class Server {
	/**
	 * The mailbox to place events received from clients in.
	 */
	private Queue<Event> mailbox = new ConcurrentLinkedQueue<Event>();
	/**
	 * A list of all of the clients that are connected.
	 */
	private Map<Integer, ConnectionHandler> clients; 
	/**
	 * The SocketChannel on which the server will be created.
	 */
	private ServerSocketChannel server = null;
	/**
	 * The port to bind the server to.
	 */
	private final int PORT = 7776;
	/**
	 * The log file to write to.
	 */
	private File logFile = new File("ServerLogFile1.txt");
	/**
	 * The output stream for the file.
	 */
	private FileOutputStream logFileOutput; 
	/**
	 * Flags whether to print log messages to the output.
	 */
	private boolean printOutput = true;
	/**
	 * Keeps track of the clients, each will be mapped from a number to a ConnectionHandler object in the hashMap
	 */
	private Integer clientCount = 0;
	/**
	 * The thread that broadcasts all of the messages to the clients
	 */
	private BroadcastThread broadcaster;

	/**
	 * Initializes the server on the above final port and ip. It is enabled for nonblocking.
	 * It also initializes the clients list.
	 */
	public Server() {
		clients = new HashMap<Integer, ConnectionHandler>();
		try {
			//Initialize the log file output stream
			logFileOutput = new FileOutputStream(logFile);

			//Write to the log file.
			writeToLogFile("Server started.");

			//broadcaster = new BroadcastThread(mailbox);

			//Open for connections and set to non-blocking mode.
			server = ServerSocketChannel.open();
			server.configureBlocking(false);

			//Get the address for the server
			InetAddress addr = InetAddress.getLocalHost();
			//Bind the server to the IP and port 7773
			server.socket().bind(new InetSocketAddress(addr, PORT));
		} catch(IOException e) {
			System.err.println("Could not listen on port 7773");
		}
		this.acceptNewConnections();
	}
	/**
	 * Writes a message to the log file for the server.
	 * @param message The message to write.
	 */
	private void writeToLogFile(String message) {
		if(printOutput)
			System.out.println(message);
		try {
			logFileOutput.write(message.getBytes());
		} catch (IOException e) {
			System.err.println("Trouble writing to log file!");
		}
	}
	/**
	 * Creates a new ConnectionHandler object for the client and adds it to the array list.
	 * 
	 * @param newClient The SocketChannel for the client.
	 */
	private void addNewClient(SocketChannel newClient) {
		ConnectionHandler newConnection = new ConnectionHandler(newClient, clientCount);
		/*try {
		//	broadcaster.addOut(new ObjectOutputStream(newClient.socket().getOutputStream()));
		} catch (IOException e) {
			System.err.println("IOException in add new client.");
		}*/
		clients.put(clientCount, newConnection);
		writeToLogFile("New connection from: " + newClient.socket().getInetAddress().toString());
	}

	private void printCurrentConnections() {
		System.out.println("All current connections: ");
		for(int t : clients.keySet())
			System.out.println("Client: " + clients.get(t).getClientIP());
	}
	/**
	 * Indefinitely listens for new connections from new clients and adds them to the clients list
	 * after it wraps them in a Runnable class.
	 */
	private void acceptNewConnections() {
		writeToLogFile("Listening for new connections");
		SocketChannel clientChannel;
		while(true) {
			try {
				clientChannel =server.accept();
				if(clientChannel != null) 
					addNewClient(clientChannel);

			} catch (IOException e) {
				System.err.println("Error accepting new connections!");
			}
		}
	}

	/**
	 * Closes up all of the connections
	 */
	protected void finalize() {
		writeToLogFile("Closing down");
		try {
			server.close();
		} catch (IOException e) {
			System.err.println("Could not close.");
		}
	}
	/**
	 * A class that implements Runnable to act as a listener thread on the given client socket.
	 * 
	 * @author Ryan Hammer
	 *
	 */
	private class ConnectionHandler implements Runnable {
		/**
		 * The client's socket
		 */
		private SocketChannel socket;
		/**
		 * The input stream for the client.
		 */
		private ObjectInputStream ois;
		/**
		 * The output stream for the client.
		 */
		private ObjectOutputStream oos;
		/**
		 * The int mapping for the specific client in the hash map
		 */
		private int clientID;

		public ConnectionHandler(SocketChannel socket, int count) {
			this.socket = socket;
			clientID = count;

			//Create a new thread using this object and start it
			Thread t = new Thread(this);
			t.start();
		}
		public String getClientIP() {
			return socket.socket().getInetAddress().toString();
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
			try {
				while(true) {
					//Get the input stream to the client
					ois = new ObjectInputStream(socket.socket().getInputStream());
					moveEvent clientEvent = (moveEvent) ois.readObject();
					mailbox.add(clientEvent);
					System.out.println("Client " + getClientIP() + ": Direction: " + clientEvent.getDirection() + " Location: " + clientEvent.getLoc());

					//Have the server send a message back
					oos = new ObjectOutputStream(socket.socket().getOutputStream());
					oos.writeObject("Server received message.");
				}
			} catch (IOException e) {
				//Remove the client from the list, it means they disconnected.
				clients.remove(clientID);
				writeToLogFile("Client: " + this.getClientIP() + " Disconnected");
			} catch (ClassNotFoundException e) {
				System.err.println("Class not found!!");
			}
		}
		@Override
		public void finalize() {
			try {
				ois.close();
				oos.close();
				socket.close();
				logFileOutput.close();
			} catch (IOException e) {
				System.err.println("IOException!");
			}
		}
	}
	/**
	 * Creates a new server object and runs it.
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = new Server();
	}
}
