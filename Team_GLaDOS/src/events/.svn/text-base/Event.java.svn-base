package events;

import game.ServerWorldState;
import updates.GladdosUpdate;

/**
 * The game engine's interface for a general event. It is to be implemented by a specific
 * game implementation.
 * 
 * @author Ryan
 *
 */
public interface Event {
	/**
	 * Each event will have a different implementation of this method in order to modify
	 * the world state in different ways.
	 * @param serverWorldState The serverWorldState to modify.
	 * @return A GladdosUpdate object that reflects this change that will be broadcasted
	 * to all connected clients.
	 */
	public GladdosUpdate execute(ServerWorldState serverWorldState);
}
