package cast;

import updates.GladdosUpdate;
import events.GladdosEvent;
import game.ServerWorldState;

public class CastEvent extends GladdosEvent {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The type of cast to perform.
	 */
	private Cast castType;

	public CastEvent(int id, Cast cast) {
		super(id);
		castType = cast;
	}

	@Override
	public GladdosUpdate execute(ServerWorldState serverWorldState) {
		return castType.execute(serverWorldState, this.getClientID());
	}
	
	@Override
	public int getExecutionTime() {
		return castType.getExecutionTime();
	}
	@Override
	public int getPrepTime() {
		return castType.getPrepTime();
	}
	@Override
	public int getResetTime() {
		return castType.getResetTime();
	}

}
