package server;

import java.awt.Dimension;
import java.io.*;
import java.net.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import events.GladdosEvent;


import game.GameController;
import game.ServerGameController;

/**
 * The server is setup just to receive connections and create new clients from them. It adds the new 
 * clients to a broadcast object that has the ability to send objects to the client. When a new client
 * connects, the server immediately sends them a NewGameUpdate object which the client instantiates 
 * their world from.
 * 
 * @author Ryan Hammer
 *
 */
public class Server implements Runnable {
	/**
	 * The mailbox to place events received from clients in.
	 */
	LinkedBlockingQueue<GladdosEvent> mailbox;
	/**
	 * A list of all of the clients that are connected. It maps a string version of their clientID to their
	 * associated ClientListener object.
	 */
	private Map<String, ClientListener> clients; 
	/**
	 * The SocketChannel on which the server will be created.
	 */
	private ServerSocket server = null;
	/**
	 * The port to bind the server to.
	 */
	private final int PORT = 7777;
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
	private Integer clientCount = 1;
	/**
	 * The thread that broadcasts all of the messages to the clients
	 */
	private Broadcaster broadcaster;
	/**
	 * Used to display server events to the user.
	 */
	private JTextArea log;
	/**
	 * Flags the server to continue running
	 */
	private boolean run = true;
	/**
	 * The server game controller.
	 */
	private GameController serverGameController;
	/**
	 * Flags to run in debug mode or not.
	 */
	private boolean debug = false;
	/**
	 * Flag to turn off the log
	 */
	private boolean runLog = true;

	/**
	 * Static boolean variables that will be used for testing.
	 */
	public static boolean aiDEBUG = false;
	public static boolean mandistDEBUG = false;
	public static boolean attdefDEBUG = false;
	public static boolean spellsDEBUG = false;
	public static boolean eventDEBUG = false;
	public static boolean updateDEBUG = false;
	public static boolean mapDEBUG = false;
	public static boolean serverclientDEBUG = false;


	/**
	 * Initializes the server on the above final port and ip. It is enabled for nonblocking.
	 * It also initializes the clients list. When the server is initialized, it also starts the
	 * main game loop running. The idea is that the world is persistent whether or not any players
	 * are playing.
	 */
	public Server() {
		if(runLog) {
			JFrame gui = new JFrame();
			
			log = new JTextArea();
			log.setEditable(false);

			JScrollPane jsp= new JScrollPane(log);

			gui.add(jsp);
			gui.pack();
			gui.setVisible(true);
			gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gui.setSize ( new Dimension (200, 300 ) );

			gui.setTitle("Server");
		}

		clients = new HashMap<String, ClientListener>();
		try {
			//Initialize the log file output stream
			logFileOutput = new FileOutputStream(logFile);

			//Write to the log file.
			writeToLogFile("Server started.");

			//Initialize the server
			server = new ServerSocket(PORT);

			//Initialize the game stuff now.
			mailbox = new LinkedBlockingQueue<GladdosEvent>();
			//Start up the broadcaster
			broadcaster = new Broadcaster(this);

			if(debug)
				System.out.println("Debug mode disabled.");
				//	serverGameController =new DebugServerGameController(mailbox, this);
			else {
				//Setup the ServerGameController, and start it as it's own thread.
				serverGameController = new ServerGameController(mailbox, this);
				Thread gameControllerThread = new Thread((ServerGameController)serverGameController);
				gameControllerThread.start();
			}
		} catch(IOException e) {
			System.err.println("Could not listen on port "+PORT);
			JOptionPane.showMessageDialog(new JFrame(),"Unable to start server! Is another instance already running?");
		}
		Thread t = new Thread(this);

		//Start the server listening for connections
		t.start();

	}
	/**
	 * Writes a message to the log file for the server.
	 * @param message The message to write.
	 */
	public void writeToLogFile(String message) {
		if(printOutput)
			if(runLog)
				log.append("\n" +message);
		if(serverclientDEBUG) System.out.println(message);
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
	private synchronized void addNewClient(Socket newClient) {
		ClientListener newConnection = new ClientListener(newClient, clientCount, mailbox, this);
		broadcaster.addOut(newClient, clientCount);
		clients.put(""+clientCount, newConnection);

		clientCount++;

		writeToLogFile("New connection from: " + newClient.getInetAddress().toString());
	}

	/**	
	 * This method is used to print all of the connections that are currently connected to the
	 * server.
	 * 
	 * There is a suppressWarning to get rid of the warning when it isn't being called.
	 */
	@SuppressWarnings("unused")
	private void printCurrentConnections() {
		if(serverclientDEBUG)System.out.println("All current connections: ");
		for(String t : clients.keySet())
			if(serverclientDEBUG)System.out.println("Client: " + clients.get(t).getClientIP());
	}

	/**
	 * Closes up all of the connections
	 */
	@Override
	protected void finalize() {
		writeToLogFile("Closing down");
		try {
			if(serverclientDEBUG)System.out.println("shutting down");
			run = false;
			server.close();
			this.logFileOutput.close();
		} catch (IOException e) {
			if(serverclientDEBUG)System.err.println("Could not close.");
		}
	}

	/**
	 * Indefinitely listens for new connections from new clients and adds them to the clients list
	 * after it wraps them in a Runnable class.
	 */
	@Override
	public void run() {
		writeToLogFile("Listening for new connections");
		Socket client;
		while(run) {
			try {
				client = server.accept();
				addNewClient(client);
			} catch (IOException e) {
				System.err.println("Error accepting new connections!");
			}
		}
	}
	public Broadcaster getBroadcaster() {
		return broadcaster;
	}
	/**
	 * Returns the ClientListener object for the given clientID
	 * @param The clientID that the ClientListener is associated with
	 * @return The ClientListener that the clientID is associated with.
	 */
	public ClientListener getClientListener(int clientID) {
		return clients.get(""+clientID);
	}
	/**
	 * Returns the number of connected clients to the server.
	 * @return the number of clients on the server
	 */
	public int getNumberConnections() {
		return clients.size();
	}
	/**
	 * Getter for the ServerGameController object that this server is tied to.
	 * @return The ServerGameController object.
	 */
	public GameController getServerGameController() {
		return this.serverGameController;
	}
	/**
	 * Method used for disconnected connections. It ensures the clientListener thread is stopped, the client is 
	 * removed from the broadcaster, and that the socket is closed.
	 * @param id The client to disconnect.
	 */
	public synchronized void removeClient(int id) {
		//First, make sure that they haven't already been removed.
		if(clients.containsKey(""+id)) {
			//Remove them from the broadcast thread
			broadcaster.removeOut(id);

			//Stop the clientListener thread
			clients.get(""+id).setConnected(false);

			//Then try to close the socket:
			try {
				clients.get(""+id).getSocket().close();
			} catch (IOException e) {
				System.err.println("IOException trying to close socket to client.");
			}
			//Finally remove them from the map
			clients.remove(""+id);
		}
	}

	/**
	 * Getter for the mailbox for the server.
	 * @return The server's mailbox
	 */
	public LinkedBlockingQueue<GladdosEvent> getMailbox() {
		return	mailbox;
	}
	/**
	 * Halts all connections on server.
	 */
	public void stopServer() {
		this.finalize();
	}
	/**
	 * Main method for the GUI
	 * @param args The optional command line arguments
	 */
	public static void main(String[] args) {

		new Server();

	}
}

