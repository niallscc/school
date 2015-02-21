package events;

import actors.Being;
import server.Server;
import updates.ClientDisconnectUpdate;
import updates.GladdosUpdate;
import game.ServerWorldState;

/**
 * An event used to indicate a disconnection of a client that is sent to the server.
 * @author Ryan
 *
 */
public class DisconnectEvent extends GladdosEvent {
	/**
	 * Used for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct the event with the client id.
	 * @param id The client ID.
	 */
	public DisconnectEvent(int id) {
		super(id);
		this.eventName = "Disconnect";
		if(Server.eventDEBUG)System.out.println("Constructing new disconnect event");
	}
	/**
	 * Implements the execute to call the game server's remove client method.
	 * 
	 * TODO: Return an update that reflects this disconnect to all other clients.
	 */
	@Override
	public GladdosUpdate execute(ServerWorldState serverWorldState) {
		if(Server.eventDEBUG) System.out.println("Server received: DisconnectEvent From: " + this.getClientID());
	
		//First find the map that this id is on.
		int map = serverWorldState.getMapLocation(this.getClientID());
		
		//The being to remove.
		Being being = serverWorldState.getMapBeings(map).get(""+this.getClientID());
		
		if(Server.eventDEBUG)System.out.println("Removing client from the maps:" + this.getClientID());
		//remove them from the ID to being map
		serverWorldState.getMapBeings(map).remove(""+this.getClientID());
		
		//remove them from the cell they are on.
		serverWorldState.getWorldMap(map).getCellAt(being.getLocation()).setBeing(null);
		
		//remove them from the ClientID to Map Number map
		serverWorldState.getIDMap().remove(""+this.getClientID());
		
		//Remove them from the server.
		serverWorldState.getServer().removeClient(this.getClientID());
		
		return new ClientDisconnectUpdate(this.getClientID());
	}	
}
