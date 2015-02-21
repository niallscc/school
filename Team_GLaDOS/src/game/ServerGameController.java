package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import actors.Being;

import events.GladdosEvent;
import server.BOTS;
import server.Server;
import updates.GladdosUpdate;

/**
 * The main loop in charge of receiving events from clients, processing those events, and sending updates back to
 * clients.
 * 
 * @author Gladdos
 *
 */
public class ServerGameController implements Runnable, GameController {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The mailbox shared with the server. Let's hope the server sends us some nice mail!
	 */
	private LinkedBlockingQueue<GladdosEvent> mailbox;
	/**
	 * The current game time
	 */
	private int gameTime = 0;
	/**
	 * A flag whether to quit the main game loop or not.
	 */
	private boolean runGame = true;
	/**
	 * The ServerEventHandler object
	 */
	private ServerEventHandler serverEventHandler;
	/**
	 * The global event scheduler.
	 */
	private Queue<GladdosEvent> eventScheduler;
	/**
	 * The WorldState associated with the server.
	 */
	private ServerWorldState serverWorldState;
	/**
	 * A list of in process events to be executed each time tick until they expire.
	 */
	private List<GladdosEvent> inProcessEvents;
	/**
	 * A list of player id's that already have events queued up.
	 */
	private Map<String, GladdosEvent> inProcessIDs;
	/**
	 * A list of events that are on reset time
	 */
	private List<GladdosEvent> resetEvents;
	/**
	 * Creates a ServerGameController and creates the necessary data structures.
	 * @param box The mail box to retrieve client events from.
	 * @param server The server that this controller is associated with.
	 */
	public ServerGameController(LinkedBlockingQueue<GladdosEvent> box, Server server) {
		inProcessIDs = new HashMap<String, GladdosEvent>();
		inProcessEvents = new ArrayList<GladdosEvent>();
		resetEvents = new ArrayList<GladdosEvent>();

		mailbox = box;
		eventScheduler = new PriorityQueue<GladdosEvent>();
		serverEventHandler = new ServerEventHandler(eventScheduler, inProcessIDs);
		serverWorldState = new ServerWorldState(server);
		for(Map<String,Being> map : serverWorldState.beingsOnMaps){
			new BOTS(serverWorldState,mailbox, map);
		}
	}

	/**
	 * This will drain the mailbox of all pending client events and process them. This is 
	 * done after every time step for the server.
	 * @throws InterruptedException 
	 */
	public void emptyMailbox(int currentGameTime) throws InterruptedException {
		if(mailbox.size() != 0) {
			for(int i = 0; i < mailbox.size(); i++)
				serverEventHandler.processEvent(mailbox.take(),currentGameTime);
		}
	}
	/**
	 * Getter for the current gameTime.
	 * @return The current game time.
	 */
	public int getCurrentGameTime() {
		return gameTime;
	}
	/**
	 * Delays the thread for the given amount of time.
	 * @param amount The amount of time to delay the thread for.
	 */
	public void delay(int amount) {
		try {
			Thread.sleep(amount);
		} catch (InterruptedException e) {
			System.err.println("Interrupted Exception sleeping server.");
		}
	}
	/**
	 *Getter for the ServerWorldState
	 *@return The ServerWorldState
	 */
	public ServerWorldState getServerWorldState() {
		return this.serverWorldState;
	}
	/**
	 * Getter for the mailbox
	 * @return The mailbox to return.
	 */
	public LinkedBlockingQueue<GladdosEvent> getMailbox(){
		return this.mailbox;
	}
	/**
	 * Goes through and regenerates stats for all of the players
	 */
	public void regenerateBeings() {
		//Iterate through all of the maps
		for(int i = 0; i < 4; i++) {
			//Iterate through all of the beings.
			for(String x : serverWorldState.getMapBeings(i).keySet()) {
				Being being = serverWorldState.getMapBeings(i).get(x);
				being.regenerate();
			}	
		}
	}

	/**
	 * This is where the main game loop will occur. It does the following:
	 * 1) Empties the mailbox and processes all events
	 * 2) Pulls the next event off of the scheduler
	 * 3) Determines if it is an event over time, if so, adds it to inprocess events
	 * 4) Determines if it needs reset
	 * 5) If it has execution time of 0, executes and returns the update
	 * 6) Then it loops through all inproccess events and excutes them and removes them if their expired
	 * 7) Then it loops through all of the resetEvents and determines if they have reached their reset time
	 * and removes them if they have.
	 */
	@Override
	public void run() {
		while(runGame) {
			delay(15);
			try {
				emptyMailbox(gameTime);
			} catch (InterruptedException e) {
				System.err.println("InterruptedException trying to empty mailbox");
			}

			GladdosEvent gameEvent = null;
			GladdosUpdate gameUpdate = null;

			while(((gameEvent = eventScheduler.peek()) != null) && (gameEvent.getStartExecutionTime() == gameTime)) {
				
				if(((gameEvent = eventScheduler.peek()) != null) && (gameEvent.getStartExecutionTime() == gameTime)) {
					gameEvent = eventScheduler.poll();
					if(Server.eventDEBUG)System.out.println("EXECUTING ID's: " + gameEvent.getClientID() + " event: " + gameEvent.getName() + " At time: " + gameTime);
					//First check to see if it has an execution time greater than 0, if it does,
					//add it to the in process events. It will be executed later in this time step
					if(gameEvent.getExecutionTime() > 0) {
						inProcessEvents.add(gameEvent);
					}
					else {
						gameUpdate = gameEvent.execute(serverWorldState);

						//Some Events don't need to return an update, so we need to make sure those null
						//updates will be caught
						if(gameUpdate != null)
							serverWorldState.getServer().getBroadcaster().broadcastUpdate(gameUpdate);
					}


					//Now check to see if the event has a reset time.
					if(gameEvent.getResetTime() != 0) {
						gameEvent.setFinishResetTime(gameEvent.getFinishExecutionTime() + gameEvent.getResetTime());
						if(Server.eventDEBUG)System.out.println("Event: " + gameEvent.getName() + " has reset finish time at: " + gameEvent.getFinishedResetTime());
						resetEvents.add(gameEvent);
					}

					//If it has an execution and reset time of 0, remove the event from inProcessIDs
					if((gameEvent.getResetTime() == 0) && (gameEvent.getExecutionTime() == 0)) {
						if(inProcessIDs.containsKey(""+gameEvent.getClientID())) {
							if(Server.eventDEBUG)System.out.println("Removing ID From inProcess Id's: " + gameEvent.getClientID());
							inProcessIDs.remove(""+gameEvent.getClientID());
						}
					}
				}
			}

//			//Dumps the contents of the Event Scheduler:
//			System.out.println("The following events are on the Global Event Scheduler on time click: " + gameTime);
//			for(GladdosEvent t : eventScheduler) {
//				System.out.println("ID: " + t.getClientID() + " Name: " + t.getName() + " ExecuteTime: " + t.getStartExecutionTime());
//			}

			//Now loop through all of the inprocess events and execute them
			for(int i = 0; i < inProcessEvents.size(); i++) {
				GladdosEvent t = inProcessEvents.get(i);

				//First check that it's still within it's execution time
				if(t.getFinishExecutionTime() >= gameTime) {

					//If it is, execute it
					GladdosUpdate update = t.execute(serverWorldState);

					//And return the update
					if(update != null)
						serverWorldState.getServer().getBroadcaster().broadcastUpdate(update);
				}
				//Otherwise remove it
				else {
					//Check to see if it had a reset time, because it will be removed later if it does, otherwise remove it
					//from both inProcessEvents and inProcessIDs
					if(t.getResetTime() == 0) {
						inProcessEvents.remove(i);
						inProcessIDs.remove(""+t.getClientID());
					}
					else
						inProcessEvents.remove(i);
				}

			} 
			//Finally, loop through all of the resetEvents and remove the ones which finish resetting at the current time.
			for(int i = 0; i < resetEvents.size(); i++) {
				GladdosEvent t = resetEvents.get(i);
				if(t.getFinishedResetTime() == gameTime) {
					if(Server.eventDEBUG)System.out.println("Event: " + t.getName() + " has finished resetting at time: " + gameTime);
					//Remove from the in processid's
					inProcessIDs.remove(""+t.getClientID());

					//Remove from the reset events.
					resetEvents.remove(i);
				}
			}


			//	System.out.println("Current Game Time: " + gameTime);
			//Regen HP and MP
			//			regenerateBeings();
			//			serverWorldState.getServer().getBroadcaster().broadcastUpdate(new RegenerateUpdate());

			//Increment the current game time at the end of the cycle.
			gameTime++;
		}
	}

}
