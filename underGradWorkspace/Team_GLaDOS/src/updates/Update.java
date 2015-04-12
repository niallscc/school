package updates;

import game.ClientWorldState;

public interface Update {
	public void execute(ClientWorldState clientWorldState);
}
