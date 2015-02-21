package events;

import actors.PC;
import actors.PCFactory;
import game.ServerWorldState;
import gameBoard.FlatMap;
import server.Server;
import updates.AddNewClientUpdate;
import updates.GladdosUpdate;

public class NewClientEvent extends GladdosEvent {
	/**
	 * The spawn x location
	 */
	private FlatMap spawn = new FlatMap(10,2);
	/**
	 * The map to spawn them on.
	 */
	private int mapSpawn = 0;
	
	/**
	 * Used for serialization.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The name of the player.
	 */
	private String playerName;
	/**
	 * The factory for the class type.
	 */
	private PCFactory factory;
	/**
	 * The gender of the player.
	 */
	private String gender;
	/**
	 * Constructs the NewClientEvent with the new client's ID.
	 * @param id The client's id.
	 */
	public NewClientEvent(int id, PCFactory classFactory, String name, String gender) {
		super(id);
		playerName = name;
		factory = classFactory;
		
		this.gender = gender;
		this.eventName = "New Client";
		
		if(Server.eventDEBUG) System.out.println("New Client Event was made");
	}

	/**
	 * Implements execute to create a new being location, and then adds this being
	 * into the serverWorldState. 
	 * 
	 * It sends an AddNewClientUpdate to all connected players.
	 * @return The update object to reflect the change.
	 */
	@Override
	public GladdosUpdate execute(ServerWorldState serverWorldState) {
		
		// TODO - do checking to make sure the player can be placed in the middle of the map.
		//Fighter newClientPC = new Fighter(spawn, playerName, gender);
		PC newClientPC = factory.createPlayerClass(spawn, playerName, gender);
		
		//Set the ID
		newClientPC.setID(this.getClientID());
		//newClientPC.setID(id);
		
		
		
		
		//Place the being in the HashMap
		serverWorldState.getMapBeings(mapSpawn).put(""+this.getClientID(), newClientPC);
		
		//Place them in the cell
		serverWorldState.getWorldMap(mapSpawn).getCellAt(spawn).setBeing(newClientPC);
		if(Server.eventDEBUG) System.out.println("Client has been placed in a cell at: " +spawn.getX()+","+spawn.getY());
		//Set the map that this being is on.
		serverWorldState.putMapLocation(this.getClientID(), mapSpawn);

		if(Server.eventDEBUG) System.out.println("Client Added: " + this.getClientID() +"  "+ this.getName());
		return new AddNewClientUpdate(newClientPC, mapSpawn);
	}
}
