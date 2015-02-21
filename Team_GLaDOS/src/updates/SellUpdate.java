package updates;

import server.Server;
import swag.Swag;
import actors.Being;
import game.ClientWorldState;

public class SellUpdate extends GladdosUpdate {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The item to sell
	 */
	private Swag sellItem;
	/**
	 * The index of the item to sell.
	 */
	private int itemIndex;

	public SellUpdate(int id, Swag item, int index) {
		this.clientID = id;
		sellItem = item;

		itemIndex = index;
	}

	@Override
	public void execute(ClientWorldState clientWorldState) {
		//First get the map location
		int mapLocation = clientWorldState.getMapLocation(this.getClientID());

		//Next get the buyer
		Being buyer = clientWorldState.getBeing(mapLocation, this.getClientID());

		//Next check to make sure they actually have the item.

		if(Server.updateDEBUG)System.out.println("SellEvent: You have the item!");

		//If they do, remove the item from the inventory.
		buyer.removeSwagItem(itemIndex);

		//Then add on the worth to their moolah
		buyer.increaseMoolah(sellItem.getVendorWorth());
	}
}
