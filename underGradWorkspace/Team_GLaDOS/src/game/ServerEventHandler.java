package game;

import java.util.Map;
import java.util.Queue;

import server.Server;
import events.GladdosEvent;

/**
 * Sorts and processes events that clients send in. It sorts them and places them in the global
 * event scheduler.
 * 
 * @author Ryan Hammer
 *
 */
public class ServerEventHandler {
	/**
	 * The global event scheduler to add on updates to.
	 */
	private Queue<GladdosEvent> eventScheduler;
	/**
	 * A list of player id's that already have events queued up.
	 */
	private Map<String, GladdosEvent> inProcessIDs;
	/**
	 * Creates a ServerEventHandler with the current event scheduler queue and the inprocess id's list.
	 * @param gameEvents The Game event scheduler.
	 * @param inProcessID All of the ID's that currently have an event pending.
	 */
	public ServerEventHandler(Queue<GladdosEvent> gameEvents, Map<String, GladdosEvent> inProcessID) {
		eventScheduler = gameEvents;
		inProcessIDs = inProcessID;
	}	
	/**
	 * This processes an event and sets the appropriate times for the event, and then places it in the
	 * Even scheduler.
	 * @param e The event
	 * @param currentGameTime The current Game clock tick.
	 */
	public void processEvent(GladdosEvent e, int currentGameTime) {
		//First check to make sure the player doesn't already have an event queued up.
		if(!inProcessIDs.containsKey(""+e.getClientID())) {

			//Some events will have their start execution time already set(The undefend event), so we need to
			//Check to see if we need to set it.
			if(e.getStartExecutionTime() == 0) { 
				e.setStartExecutionTime(currentGameTime + e.getPrepTime());
				if(Server.eventDEBUG)System.out.println("Server received event: " + e.getName() + " From: " + e.getClientID() + " It will execute at time: " + e.getStartExecutionTime());
			}
			
			//Now set the time at which the event will finish executing
			e.setFinishExecutionTime(e.getStartExecutionTime() + e.getExecutionTime());

			inProcessIDs.put(""+e.getClientID(), e);
			eventScheduler.add(e);
		}
		else 
			if(Server.eventDEBUG) System.out.println("ID: " + e.getClientID() + " Already has an event pending");
	}
}
