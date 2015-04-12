package cast;

import java.io.Serializable;

import actors.Being;

import game.ClientWorldState;
import game.ServerWorldState;

public abstract class Cast implements Serializable {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	protected int difficulty;
	protected int mpCost;
	protected int levelReq;
	protected int clientID;
	protected int prepTime;
	protected int resetTime;
	protected int executionTime;

	public abstract void execute(ClientWorldState clientWorldState, int id);
	public abstract CastUpdate execute(ServerWorldState serverWorldState, int id);

	protected boolean canCast(Being being) {

		//First make sure they satasfy the basic requirements
		if((being.getLevel() >= levelReq) && (being.getMP() >= mpCost)) 
			return true;
		else
			return false;
	}
	/**
	 * Getter for the Prep time for the cast.
	 * @return The prep time required.
	 */
	public int getPrepTime() {
		return prepTime;
	}
	/**
	 * Getter for the reset time for the cast.
	 * @return The reset time
	 */
	public int getResetTime() {
		return this.resetTime;
	}
	/**
	 * Getter for the execution time for the cast
	 * @return The execution time.
	 */	
	public int getExecutionTime() {
		return this.executionTime;
	}
}
