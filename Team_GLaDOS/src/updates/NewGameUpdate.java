package updates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import server.Server;

import actors.Being;
import game.ClientWorldState;
import gameBoard.TerrainMap;

public class NewGameUpdate extends GladdosUpdate {
	/**
	 * Used for serialization.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The ID that will be assigned to the new client.
	 */
	private int clientID;
	/**
	 * An ArrayList of maps that contain a mapping for each beingID;
	 */
	ArrayList<Map<String, Being>> worldBeings;
	/**
	 * An ArrayList of all of the TerrainMaps for the world
	 */
	List<TerrainMap> worldMaps;
	/**
	 * A mapping of being id's to the map number they are on.
	 */
	HashMap<String, Integer> idMap;
	/**
	 * Create a NewGameUpdate with the clientID
	 * @param id The ID the client will be assigned.
	 */
	public NewGameUpdate(int id, ArrayList<Map<String, Being>> beingsOnMaps, List<TerrainMap> maps, HashMap<String, Integer> ids) {
		clientID = id;
		worldBeings = beingsOnMaps;
		name = "NewGameUpdate";
		worldMaps = maps;
		idMap = ids;
	}
	
	@Override
	public void execute(ClientWorldState clientGameState) {
		if(Server.updateDEBUG)System.out.println("ID: " + clientID + " Client received new game update");
		
		//Set the beings
		clientGameState.setWorldBeings(worldBeings);
		
		//Now set the maps
		clientGameState.setWorldMaps(worldMaps);
		
		//Set the clientID
		clientGameState.getClientGameController().setClientID(clientID);
		
		//Set the id Mapping
		clientGameState.setIDMap(idMap);
		
		//Finally set it to initialized
		clientGameState.setInitialized();
		}
}
