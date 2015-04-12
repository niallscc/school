package events;

import actors.Being;
import game.ServerWorldState;
import server.Server;
import swag.Swag;
import updates.GladdosUpdate;
import updates.SellUpdate;

public class SellEvent extends GladdosEvent {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The swag item to buy from the vendor.
	 */
	private Swag sellItem;
	/**
	 * The index of the swag item in the inventory.
	 */
	private int itemIndex;

	public SellEvent(int id, Swag item, int index) {
		super(id);
		this.prepTime = 10;
		sellItem = item;
		itemIndex = index;
		if(Server.eventDEBUG) System.out.println("Selling item: " + sellItem.getName());
	}

	@Override
	public GladdosUpdate execute(ServerWorldState serverWorldState) {
		//First get the map location
		int mapLocation = serverWorldState.getMapLocation(this.getClientID());

		//Next get the buyer
		Being buyer = serverWorldState.getBeing(mapLocation, this.getClientID());
		if(Server.eventDEBUG) System.out.println("The Buyer: "+ buyer.getPlayerClass());

		//Next check to make sure they actually have the item.

		if(Server.eventDEBUG) System.out.println("SellEvent: You have the item!");

		//If they do, remove the item from the inventory.
		buyer.removeSwagItem(itemIndex);

		//Then add on the worth to their moolah
		buyer.increaseMoolah(sellItem.getVendorWorth());

		//Now return a SellUpdate
		return new SellUpdate(this.getClientID(), sellItem, itemIndex);

	}

}
