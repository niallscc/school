package events;

import java.io.Serializable;


public class moveEvent implements Event, Serializable {
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 1L;
	private String direction;
	private int location;
	private String eventName = "Move";
	
	public moveEvent(String dir, int loc)
	{
		direction = dir;
		location = loc;
	}
	
	public String getDirection() {
		return direction;
	}
	public int getLoc() {
		return location;
	}
	
	@Override
	public String getEventName() {
		return eventName;
	}
	

}
