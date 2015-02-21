package updates;

import java.io.Serializable;



public abstract class GladdosUpdate implements Update, Serializable {
	protected String name;
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The clientID that was updated
	 */
	protected int clientID;
	
	public String getName() {
		return name;
	}
	public int getClientID() {
		return clientID;
	}
	
}
