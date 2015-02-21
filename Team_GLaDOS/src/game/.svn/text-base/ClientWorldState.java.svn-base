package game;

import gameBoard.TerrainMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actors.Being;


/**
 * The ClientWorldState 
 * 
 * It's only job is to accurately reflect the server world. It will do this by being synced by Update 
 * objects received from the server. These Update objects will have an execute() method that will update
 * this world state so that it will reflect the server's world state.
 * 
 * @author Ryan Hammer
 *
 */
public class ClientWorldState extends WorldState {
	/**
	 * Flags whether the WorldState has been initialized by the server.
	 */
	private boolean initialized = false;
	/**
	 * The ClientGameController that controls this ClientWorldState
	 */
	private ClientGameController clientController;
	/**
	 * The PC Being that is associated with this WorldState
	 */
	private Being player;

	/**
	 * Construct the ClientWorldState object and assign it the controller it will be coupled with.
	 * @param controller The client's ClientGameController object that this world state is associated with.
	 */
	public ClientWorldState(ClientGameController controller) {
		super();
		clientController = controller;
	}
	
	/**
	 * Getter for the ClientGameController.
	 * @return The clientGameController.
	 */
	public ClientGameController getClientGameController() {
		return clientController;
	}
	/**
	 * This is called in NewGameUpdate and passes in the the ServerWorldState's entire List of beings in all of the maps.
	 * @param worldBeings The list of hashmaps that contain the beings for every map.
	 */
	public void setWorldBeings(ArrayList<Map<String, Being>> worldBeings) {
		this.beingsOnMaps = worldBeings;
	}
	/**
	 * This is called in NewGameUpdate and the server passes in it's List of TerrainMaps.
	 * @param maps The server's TerrainMaps for all of the maps in the world.
	 */
	public void setWorldMaps(List<TerrainMap> maps) {
		worldMaps = maps;
	}	
	/**
	 * This is called in NewGameUpdate and the server passes in it's mapping of BeingID's to which map
	 * number they are on.
	 * @param ids The Map of BeingID's to map number.
	 */
	public void setIDMap(HashMap<String, Integer> ids) {
		this.idMap = ids;
	}
	
	/**
	 * Getter for the being
	 * @return The Being associated with this world state
	 */
	public Being getBeing() {
		return player;
	}
	/**
	 * Setter for the being
	 * @param The Being to set it to.
	 */
	public void setBeing(Being being) {
		player = being;
	}
	/**
	 * Setter for being initialized
	 */
	public void setInitialized() {
		initialized = true;
	}
	/**
	 * Getter for initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}
}
