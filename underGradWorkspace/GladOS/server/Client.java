package server;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import javax.swing.JFrame;

import events.Event;
import events.moveEvent;

class Client extends JFrame {
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The IP of the server to try to connect to.
	 */
	private static final String HOST_IP = "129.24.255.236";
	/**
	 * The port of the server to try to connect to.
	 */
	private static final int PORT = 7776;
	/**
	 * The server object that has a thread always listening for input from the server
	 */
	private ServerListener server = null;
	private SocketChannel socket = null;
	
	private boolean run = true;

	public Client() { 
		this.setTitle("Client");
		// Make application terminate when window closes
		this.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		// Center window on screen
		this.setLocationRelativeTo ( null );
		// Set the dimensions of this window
		this.setSize ( new Dimension (200, 200 ) );
		this.addKeyListener(new MovementListener());
	} 
	public void connect(){
		try {
			InetAddress ip = InetAddress.getByName(HOST_IP);
			socket = SocketChannel.open(new InetSocketAddress(ip, PORT));
			//server = new ServerListener(socket);	
		} catch (UnknownHostException e) {
			System.err.println("Unknown Host exception!");
		} catch (IOException e) {
			System.err.println("IOexception! in connect");
		}
	}
	public boolean getRun() {
		return run;
	}
	/**
	 * Method that can be added on to for chat functionality
	 */
	public void receiveInput() {
		Scanner in = new Scanner(System.in);

		// Reads a single line from the console 
		String text = in.nextLine();

		//sendEvent(new chatEvent("Hello"));
		if(text == "exit") {
			in.close();   
			run = false;
		}	
	}
	/**
	 * Sends an event to the server
	 * @param newEvent The event to send.
	 */
	public void sendEvent(Event newEvent) {
		try {
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(socket.socket().getOutputStream());	
			oos.writeObject(newEvent);
		} catch (IOException e) {
			System.err.println("IO Exception in SendMessage!");
		}
	}

	/**
	 * Closes up all of the connections
	 */
	protected void finalize() {
		System.out.println("Closing down");
		try {
			socket.socket().close();
		} catch (IOException e) {
			System.err.println("Could not close.");
		}
	}

	private class MovementListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyEvent = e.getKeyCode();
			if(keyEvent== KeyEvent.VK_W)
				sendEvent(new moveEvent("East", 2));
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
	
	public static void main(String[] args){
		Client client = new Client();
		client.setVisible(true);
		client.connect();

		while(true) {
		}
	}
	/**
	 * Listener thread for the server socket
	 * @author Ryan Hammer
	 *
	 */
	private class ServerListener implements Runnable {
		/**
		 * The client's socket
		 */
		private SocketChannel server;
		/**
		 * The input stream for the client.
		 */
		private ObjectInputStream ois;
		
		public ServerListener(SocketChannel server) {
			this.server = server;
			
			//Create a new thread using this object and start it
			Thread t = new Thread(this);
			t.start();
		}

		public SocketChannel getServerSocket() {
			return server;
		}
		@Override
		public void run() {
			try {
				while(true) {
					//Get the input stream to the client
					ois = new ObjectInputStream(server.socket().getInputStream());
					String serverMessage = (String) ois.readObject();
					System.out.println(serverMessage);
				}
			} catch (IOException e) {
				System.err.println("Lost connection to server");
			} catch (ClassNotFoundException e) {
				System.err.println("Class not found!!");
			}
		}
		
	}
	
}
