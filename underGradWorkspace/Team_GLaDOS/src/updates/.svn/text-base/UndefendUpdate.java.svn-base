package updates;

import server.Server;
import game.ClientWorldState;

public class UndefendUpdate extends GladdosUpdate {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	
	public UndefendUpdate(int id) {
		this.clientID = id;
	}

	@Override
	public void execute(ClientWorldState clientWorldState) {
		
		if(Server.updateDEBUG)System.out.println("Undefend Update Sent Out For Client :" +" "+ this.clientID);
		//Get the map location
		int mapLocation = clientWorldState.getMapLocation(this.getClientID());
		
		//Then get the being and set them to not defending.
		clientWorldState.getBeing(mapLocation, this.getClientID()).setDefending(false);
	}

}
