package events;

import actors.Being;
import game.ServerWorldState;
import server.Server;
import updates.DefendUpdate;
import updates.GladdosUpdate;

/**
 * The way that Defend Events work is a client will send a Defend event to the server. The execution time is calculated
 * for the event and when it is executed, it adds an Undefend Event to the Server's mailbox.
 * @author Ryan Hammer
 *
 */
public class DefendEvent extends GladdosEvent {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * I'm not quite sure what Global Clock ticks means in terms of this event, so this just holds a value for now.
	 */
	private int globalClockTicks = 200;

	/**
	 * Constructer for a defend event that will initialize all the necessary event variables.
	 * 
	 * @param id the clientID of the defender.
	 * @param defender The defender.
	 */
	public DefendEvent(int id, Being defender) {
		super(id);
		this.executionTime = 2*defender.getSpeed()*globalClockTicks;
		this.eventName = "Defend";
		
		if(Server.attdefDEBUG) System.out.println("Starting Defend Event: ");

	}

	@Override
	public GladdosUpdate execute(ServerWorldState serverWorldState) {
		//Get the current game time
		int currGameTime = serverWorldState.getServer().getServerGameController().getCurrentGameTime();
		//Place an Undefend event in the server's mailbox. It will take place currGameTime + execution time from now.
		try {
			serverWorldState.getServer().getMailbox().put(new UndefendEvent(this.getClientID(),currGameTime+executionTime));
		} catch (InterruptedException e) {
			System.err.println("Interrupted Exception trying to place Undefend event in server mailbox");
		}
		
		if(Server.attdefDEBUG)System.out.println("DEFENDEVENT: OMG CLIENT: " + this.getClientID() + " Is trying to defend themselves at time: " +currGameTime);
		
		//Get the map location
		int mapLocation = serverWorldState.getMapLocation(this.getClientID());
	
		//Then get the being and set them to defending.
		serverWorldState.getBeing(mapLocation, this.getClientID()).setDefending(true);

		return new DefendUpdate(this.getClientID());
	}

}
