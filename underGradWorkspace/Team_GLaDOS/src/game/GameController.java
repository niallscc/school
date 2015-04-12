package game;

import java.util.concurrent.LinkedBlockingQueue;

import events.GladdosEvent;
/**
 * This will be used to add on a different ServerGameController to allow stepping through time.
 * @author Ryan
 *
 */
public interface GameController {
	public ServerWorldState getServerWorldState();
	public LinkedBlockingQueue<GladdosEvent> getMailbox();
	public int getCurrentGameTime();
}
