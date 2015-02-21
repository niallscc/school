package server;

import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import actors.Player;

import updates.GladdosUpdate;
import events.DisconnectEvent;
import events.Event;
import events.NewClientEvent;
import game.ClientGameController;

public class Client implements Runnable {
	/**
	 * The port of the server to try to connect to.
	 */
	private int PORT = 7777;
	/**
	 * The server socket.
	 */
	private Socket serverSocket = null;
	/**
	 * The output stream for the server.
	 */
	private ObjectOutputStream oos;
	/**
	 * The input stream for the server.
	 */
	private ObjectInputStream ois;
	/**
	 * Flags whether the client is connected to the server or not.
	 */
	private boolean run = true;
	/**
	 * Tracks the number of times a client failed to send an event to the server.
	 */
	private int sendFailures = 0;
	/**
	 * Tracks how many times a client failed to receive an update from the server.
	 */
	private int receiveFailures = 0;
	/**
	 * The GameController associated with the client
	 */
	private ClientGameController clientController;
	/**
	 * The loaded player info.
	 */
	private Player playerInfo;


	public Client(InetAddress ipAdd, int port, Player playerInfo) { 
		if(Server.serverclientDEBUG) System.out.println("Trying to connect");

		try {		
			this.playerInfo = playerInfo;
			
			InetAddress ip = ipAdd;  //InetAddress.getByName(HOST_IP);
			//ip =InetAddress.getLocalHost();
			System.out.println("IP: " + ip.getHostAddress());
			this.PORT = port;

			serverSocket  = new Socket(ip, PORT);
			oos = new ObjectOutputStream(serverSocket.getOutputStream());
			ois = new ObjectInputStream(serverSocket.getInputStream());

			//Initialize the GameController
			clientController = new ClientGameController(this);

			if(Server.serverclientDEBUG)System.out.println("Connected to server.");
			if(Server.serverclientDEBUG)System.out.println("Waiting for input..."); 
			Thread t = new Thread(this);
			t.start();

		} catch (UnknownHostException e) {
			System.err.println("Unknown Host exception while trying to connect to the server!");
		} catch (IOException e) {
			System.err.println("IOexception when trying to connect! Is the server running?");
			JOptionPane.showMessageDialog(new JFrame(),"Unable to connect to server, are you sure it's running? (Check the IP and Port)");
		} 
	}  
	/**
	 * Getter for the connection state of the socket.
	 * @return Whether or not the socket is connected.
	 */
	public boolean isConnected() {
		return !serverSocket.isClosed();
	}
	/**
	 * Getter for the running state of the thread.
	 * @return Whether or not the thread is running.
	 */
	public boolean isRunning() {
		return run;
	}
	/**
	 * Sends an event to the server
	 * @param newEvent The event to send.
	 */
	public synchronized void sendEvent(Event event) {
		try {
			oos.writeObject(event);
			//If it wrote the object, reset the sendFailures if there are any.
			sendFailures = 0;
		} catch (IOException e) {
			//Exception encountered, track it.
			sendFailures++;

			//If there are more than 5, disconnect and inform the user.
			if(sendFailures > 5) {
				System.err.println("Unable to contact server. Please re-connect");
				disconnect();
			}
		}
	}
	/**
	 * Getter for the ClientGameController associated with this client.
	 * @return The Client's ClientGameController reference
	 */
	public ClientGameController getClientGameController() {
		return this.clientController;
	}
	/**
	 * Called when a client exits, to disconnect from the server.
	 */
	public void disconnect() {
		try {
			//First send a disconnect event to the server.
			sendEvent(new DisconnectEvent(getClientGameController().getClientID()));
	
			//Then close the socket to the server.
			serverSocket.close();
	
			//Then stop this thread from running
			this.run = false;
		} catch (IOException e) {
			System.err.println("IOException when trying to close socket to server.");
		}
	}
	@Override
	public void finalize() {
		disconnect();
	}

	@Override
	public void run() {
		//Wait for the initialize New Game Update
		GladdosUpdate gameUpdate = null;
		try {
			gameUpdate = (GladdosUpdate)ois.readObject();
			clientController.processUpdate(gameUpdate);
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		sendEvent(new NewClientEvent(clientController.getClientID(),playerInfo.getPCFactory(), playerInfo.getName(), playerInfo.getGender()));
		while(run) {
			try {
				gameUpdate = (GladdosUpdate)ois.readObject();	
				if(Server.serverclientDEBUG)System.out.println("New Update received(in Client)");
				//clientController.getWorldState();
				clientController.processUpdate(gameUpdate);

				//reset receiveFailures
				receiveFailures = 0;
			} catch (IOException e) {
				//Exception encountered, track it.
				receiveFailures++;

				//If there are more than 5, disconnect and inform the user.
				if(receiveFailures > 5) {
					System.err.println("Unable to receive update from server. Please re-connect: ID "+ this.getClientGameController().getClientID());
					disconnect();
				}
			} catch (ClassNotFoundException e) {
				System.err.println("That's weird... A classNotFoundException!");
			}
		}
	}
//	public static void main(String[] args) {
//		 new Client();
//		 
//	}
}


