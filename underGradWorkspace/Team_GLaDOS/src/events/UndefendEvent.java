package events;

import game.ServerWorldState;
import server.Server;
import updates.GladdosUpdate;
import updates.UndefendUpdate;

/**
 * This event will be used in order to change the stance of a being from defending to not defending.
 * 
 * @author Ryan Hammer
 *
 */
public class UndefendEvent extends GladdosEvent {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	public UndefendEvent(int id, int startTime) {
		super(id);
		this.startExecutionTime = startTime;
		this.eventName = "Undefend";
		if(Server.attdefDEBUG) System.out.println("Client is Undefending.");
	}

	@Override
	public GladdosUpdate execute(ServerWorldState serverWorldState) {
		//Get the map location
		int mapLocation = serverWorldState.getMapLocation(this.getClientID());
		
		if(Server.attdefDEBUG) System.out.println("UNDEFENDEVENT: Defend ended at: " + this.startExecutionTime);
		
		//Then get the being and set them to not defending.
		serverWorldState.getBeing(mapLocation, this.getClientID()).setDefending(false);
		
		
		return new UndefendUpdate(this.getClientID());
	}

}
