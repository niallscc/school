package updates;

import server.Server;
import swag.Swag;
import actors.Being;
import game.ClientWorldState;

public class BuyUpdate extends GladdosUpdate {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The item to purchase
	 */
	private Swag purchaseItem;

	public BuyUpdate(int id, Swag item) {
		this.clientID = id;
		purchaseItem = item;
		
		if(Server.updateDEBUG)System.out.println("Buying item: " + purchaseItem.getName());
	}

	@Override
	public void execute(ClientWorldState clientWorldState) {
		//First get the map location
		int mapLocation = clientWorldState.getMapLocation(this.getClientID());

		//Next get the buyer
		Being buyer = clientWorldState.getBeing(mapLocation, this.getClientID());
		if(Server.updateDEBUG)System.out.println("The Buyer: " + buyer.getPlayerClass() + "ID: " + buyer.getID());
		//Next check to make sure they have enough money
		if(buyer.getMoolah() >= purchaseItem.getVendorCost()) {
			//If they do, transfer the Item to the player's inventory
			buyer.addSwagItem(purchaseItem);

			//Then subtract from their moolah
			buyer.decreaseMoolah(purchaseItem.getVendorCost());
		}
	}
}
