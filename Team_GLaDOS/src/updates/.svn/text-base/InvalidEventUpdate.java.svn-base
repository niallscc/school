package updates;

import game.ClientWorldState;

public class InvalidEventUpdate extends GladdosUpdate {

	/**
	 * Used for serialization.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The event type that was invalid.
	 */
	private String event;
	
	public InvalidEventUpdate(String event) {
		this.event = event;
	}

	@Override
	public void execute(ClientWorldState clientWorldState) {
		System.out.println(event + " was invalid");
		
	}

}
