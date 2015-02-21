package events;

import java.io.Serializable;

/**
 * The Gladdos Game implementation of Event.
 * 
 * @author Ryan
 *
 */
public abstract class GladdosEvent implements Event, Serializable, Comparable<GladdosEvent> {
	/**
	 * The Name of the event
	 */
	protected String eventName;
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The id of the client who sent this event.
	 */
	private int clientID;
	/**
	 * The time that the server processed the event to run at.
	 */
	private int time;
	
	/**
	 * The amount of time it takes to prep the event.
	 */
	protected int prepTime;
	/**
	 * The amount of time the event will execute for.
	 */
	protected int executionTime;
	/**
	 * The time the event will start execution.
	 */
	protected int startExecutionTime;
	/**
	 * The amount of time the event needs to resent.
	 */
	protected int resetTime;
	/**
	 * Indicates whether clientside the event was determined to be valid or not initially.
	 */
	protected boolean valid = true;
	/**
	 * The time at which an event will finish resetting.
	 */
	protected int finishResetTime;
	/**
	 * The time at which an event finishes executing
	 */
	protected int finishExecutionTime;
	/**
	 * Constructs a new event with the given Client ID
	 * @param id The clientID
	 */
	public GladdosEvent(int id) {
		clientID = id;
		this.startExecutionTime = 0;
		this.executionTime = 0;
		this.prepTime = 0;
		this.resetTime =0;
	}
	public void setTime(int serverTime)  {
		time = serverTime;
	}
	public int getTime() {
		return time;
	}
	/**
	 * Getter for the client id
	 * @return The clientID.
	 */
	public int getClientID() {
		return clientID;
	}
	/**
	 * Returns the event with the soonest time.
	 */
	@Override
	public int compareTo(GladdosEvent arg0) {
		if(this.getStartExecutionTime() < arg0.getStartExecutionTime()) 
			return -1;
		else if(this.getStartExecutionTime() > arg0.getStartExecutionTime())
			return 1;
		else
			return 0;
	}
	/**
	 * Getter for the prep time
	 * @return The prep time for the event
	 */
	public int getPrepTime() {
		return this.prepTime;
	}
	/**
	 * Getter for the execution time
	 * @return The Event's execution time.
	 */
	public int getExecutionTime() {
		return this.executionTime;
	}
	/**
	 * Getter for the reset time for the event.
	 * @param Time to reset.
	 */
	public int getResetTime() {
		return this.resetTime;
	}
	public void setStartExecutionTime(int time) {
		startExecutionTime = time;
	}
	public int getStartExecutionTime() {
		return startExecutionTime;
	}
	public String getName() {
		return eventName;
	}
	public void setFinishResetTime(int time) {
		this.finishResetTime = time;
	}
	public int getFinishedResetTime() {
		return this.finishResetTime;
	}
	public void setFinishExecutionTime(int time) {
		this.finishExecutionTime = time;
	}
	public int getFinishExecutionTime() {
		return this.finishExecutionTime;
	}
}
