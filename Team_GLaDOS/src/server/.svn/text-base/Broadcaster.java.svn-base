package server;

import game.ServerWorldState;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import updates.GladdosUpdate;
import updates.NewGameUpdate;

/**
 * This thread is established on the server to contain every client's output stream. Whenever the server needs to update the clients,
 * this object is called which iterates through and writes the update to every client's output stream.
 * 
 * @author Ryan Hammer
 *
 */
public class Broadcaster
{
	/**
	 * The server object that this broadcast thread is associated with.
	 */
	Server gameServer;
	/**
	 * Holds all of the output streams for every client. Maps their ClientID as a string to their ObjectOutputStream
	 */
	Map<String, ObjectOutputStream> clients;
	/**
	 * Constructs a new Broadcast Thread Object. The server should only ever create one of these.
	 * 
	 * @param container MailBox for incoming messages 
	 */
	public Broadcaster(Server server)
	{		
		clients = new HashMap<String, ObjectOutputStream>();
		gameServer = server;
	}

	/**
	 * Returns the number of client ObjectOutput streams this broadcaster has.
	 * @return The number of client streams
	 */
	public int getClientCount() {
		return clients.size();
	}

	/**
	 * Adds the ObjectOutputStream to the clients list, so that when an event needs to be sent to all threads 
	 * It will be sent by iterating over that ObjectOutPutStream list. When a new output stream is added,
	 * An object is immediately sent to the client. This object is used to initialize the game
	 * for the client.
	 * 
	 * @param o The ObjectOutputStream
	 */
	public synchronized void addOut(Socket client, int clientID)
	{
		//Now it sends a NewGameUpdate update to the client from which they can initialize their game
		//state.
		ObjectOutputStream o = null;
		try {
			ServerWorldState sWorldState =gameServer.getServerGameController().getServerWorldState();
			o = new ObjectOutputStream(client.getOutputStream());
			o.writeObject(new NewGameUpdate(clientID, sWorldState.getWorldBeings(), sWorldState.getMaps(), sWorldState.getIDMap()));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error while trying to send client initialization object.");
		}
		clients.put(""+clientID, o);
	}
	/**
	 * Returns whether or not it has the provided ObjectOutputStream
	 * @param oos	The ObjOutputstream to look for.
	 * @return whether or not it has the oos.
	 */
	public boolean contains(ObjectOutputStream oos) {
		return clients.containsValue(oos);
	}
	/**
	 * Method used by server if it loses a connection to a client. This will remove the output stream from
	 * the client. 
	 * @param clientID The client to remove.
	 */
	public synchronized void removeOut(int clientID) {
		clients.remove(""+clientID);
	}
	/**
	 * Sends a server update out to all of the currently connected clients.
	 * @param update The update to send out.
	 */
	public void broadcastUpdate(GladdosUpdate update) {
		//Iterate through all of the clients and send the update out.
		for(String i : clients.keySet()) {
			try
			{
				clients.get(""+i).writeObject(update);
			}
			//If a client becomes disconnected, make sure they are removed
			catch (SocketException se)
			{
				// TODO ALLOW AN EXCEPTION UP TO 5 TIMES BEFORE DISCONNECTING CLIENT.

				//Make sure the server does the appropriate cleanup.
				int id = Integer.parseInt(i);
				gameServer.removeClient(id);
			} catch (IOException e) {
				System.err.println("IOException trying to broadcast game update!");
			}
		}
	}
	
}


