package cast;

import game.ClientWorldState;
import updates.GladdosUpdate;

public class CastUpdate extends GladdosUpdate {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The cast type to perform
	 */
	protected Cast castType;
	public CastUpdate(int id, Cast cast) {
		this.clientID = id;
		castType = cast;
	}

	@Override
	public void execute(ClientWorldState clientWorldState) {
		castType.execute(clientWorldState, this.getClientID());
	}

}
