package game;

import events.Event;
import server.Client;

/**
 * This is where events will be sent through that will go through some initial checking and then be sent to 
 * the server from the client. Initial checking done will be collision detection, etc.
 * 
 * @author Ryan Hammer
 *
 */
public class ClientEventHandler {
	/**
	 * The client that the event handler is tied to.
	 * 
	 */
	private Client client;
	/**
	 * Creates a ClientEventHandler with the given client object
	 * @param pc The client object
	 */
	public ClientEventHandler(Client pc) {
		client = pc;
	}
	/**
	 * If any parsing needs to occur, add it here. Otherwise, send the event.
	 * @param clientEvent The event to send.
	 */
	public void sendEvent(Event clientEvent) {
		client.sendEvent(clientEvent);
	}

}
