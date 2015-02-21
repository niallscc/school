package game;

import java.util.ArrayList;
import java.util.Map;

import actors.Being;
import server.Server;
/**
 * The specific ServerWorldState of type WorldState. It adds the specific functionality that 
 * the server needs.
 * @author Ryan Hammer
 *
 */
public class ServerWorldState extends WorldState {
	/**
	 * The server associated with this wordState. Reference is used so that events
	 * can update the server(New client, disconnected client.
	 */
	private Server gameServer;
	/**
	 * The number of clients connected. Set to zero, it will be set to 1 when sent
	 */
	private int clientCount = 0;

	/**
	 * Calls the WorldState constructor, which loads in all of the maps.
	 * @param server
	 */
	public ServerWorldState(Server server) {
		super();
		gameServer = server;
	}
	/**
	 * The getter for the server object associated with this ServerWorldState.
	 * @return The Server
	 */
	public Server getServer() {
		return gameServer;
	}
	/**
	 * Getter for the ServerWorldState's ArrayList of beings in the entire world
	 */
	public ArrayList<Map<String, Being>> getWorldBeings() {
		return this.beingsOnMaps;
	}
	/**
	 * Getter for a clientID
	 * @return the next unique client id
	 */
	public int getNextClientID() {
		clientCount++;
		return clientCount;
	}
}
