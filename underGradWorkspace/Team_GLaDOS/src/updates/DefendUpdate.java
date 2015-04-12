package updates;

import server.Server;
import game.ClientWorldState;

public class DefendUpdate extends GladdosUpdate {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	public DefendUpdate(int id) {
		this.clientID = id;
	}

	@Override
	public void execute(ClientWorldState clientWorldState) {
		if(Server.updateDEBUG)System.out.println("Client Defend UPdate Sent for Client:" + this.clientID);

		//Get the map location
		int mapLocation = clientWorldState.getMapLocation(this.getClientID());
	
		//Then get the being and set them to defending.
		clientWorldState.getBeing(mapLocation, this.getClientID()).setDefending(true);
	}

}
