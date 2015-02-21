package updates;

import game.ClientWorldState;
import java.io.Serializable;

import server.Server;

import actors.Being;

public class ClientDisconnectUpdate extends GladdosUpdate implements Serializable {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	public ClientDisconnectUpdate(int id) {
		name = "ClientDisconnectUpdate";
		this.clientID = id;
	}

	@Override
	public void execute(ClientWorldState clientGameState) {
		if(Server.updateDEBUG)System.out.println("Hey! A client quit the game");
		
		//First find the map that this id is on.
		int map = clientGameState.getMapLocation(this.getClientID());
		
		//The being to remove.
		Being being = clientGameState.getMapBeings(map).get(""+this.getClientID());
		if(Server.updateDEBUG)System.out.println("Quitter ID: " + being.getID()+" Class: " + being.getPlayerClass() );
		
		//remove them from the ID to being map
		clientGameState.getMapBeings(map).remove(""+this.getClientID());
		
		//remove them from the cell they are on.
		clientGameState.getWorldMap(map).getCellAt(being.getLocation()).setBeing(null);
		
		//remove them from the ClientID to Map Number map
		clientGameState.getIDMap().remove(""+this.getClientID());
		
	}



}
