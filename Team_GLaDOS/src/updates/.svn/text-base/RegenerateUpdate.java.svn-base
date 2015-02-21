package updates;

import actors.Being;
import game.ClientWorldState;

public class RegenerateUpdate extends GladdosUpdate  {

	/**
	 * Used for serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void execute(ClientWorldState clientWorldState) {
		//Iterate through all of the maps
		for(int i = 0; i < 4; i++) {
			//Iterate through all of the beings.
			for(String x : clientWorldState.getMapBeings(i).keySet()) {
				Being being = clientWorldState.getMapBeings(i).get(x);
				being.regenerate();
			}	
		}
	}

}
