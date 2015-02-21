package updates;


import server.Server;
import actors.Being;

import game.ClientWorldState;

/**
 * An update used to update the client world state and add in a new client connection.
 * @author Ryan
 *
 */
public class AddNewClientUpdate extends GladdosUpdate {
	/**
	 * Used for serialization.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The being that will be added to all of the player's HashMaps of beings
	 */
    private	Being newClientPC;
    /**
     * The map id to place it on.
     */
    private int mapID;
    /**
     * Creates a new ClientUpdate with the new Being and the map they are on.
     * @param newClient The Being to add.
     * @param map The map to add them on.
     */
    public AddNewClientUpdate(Being newClient, int map) { //changed to being to add monster client
    	newClientPC = newClient;

    	mapID = map;
    	name = "AddNewClientUpdate";
    	if(Server.updateDEBUG)System.out.println("Adding new client: " + newClientPC.getPlayerClass());
    }
    /**
     * Updates the Client World state and add's in the new being.
     */
	@Override
	public void execute(ClientWorldState clientWorldState) {
		clientWorldState.getMapBeings(mapID).put(""+newClientPC.getID(), newClientPC);
		clientWorldState.putMapLocation(newClientPC.getID(), mapID);
		
		if(Server.updateDEBUG)System.out.println("The new guy was added to map: " + clientWorldState.getMapLocation(newClientPC.getID()));
		
		for(String id : clientWorldState.getIDMap().keySet()) {
			if(Server.updateDEBUG)System.out.println("ID: " + id + " Map: " + clientWorldState.getIDMap().get(id));
		}
		
		//Place the being in the cell.
		clientWorldState.getWorldMap(mapID).getCellAt(newClientPC.getLocation()).setBeing(newClientPC);
		
		if(Server.updateDEBUG)System.out.println("AddNewClientUpdate received. ID: " + newClientPC.getID());
	}
}
