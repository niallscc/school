package events;

import actors.Being;
import game.ServerWorldState;
import swag.Swag;
import updates.BuyUpdate;
import updates.GladdosUpdate;

public class BuyEvent extends GladdosEvent {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The swag item to buy from the vendor.
	 */
	private Swag purchaseItem;

	/**
	 * Constructer for the BuyEvent that will initialize the Swag item that is being bought
	 * and the ID of the buyer.
	 * 
	 * @param id ClientID buying the 
	 * @param item
	 */
	public BuyEvent(int id, Swag item) {
		super(id);
		this.prepTime = 10;
		purchaseItem = item;
	}

	@Override
	public GladdosUpdate execute(ServerWorldState serverWorldState) {
		//First get the map location
		int mapLocation = serverWorldState.getMapLocation(this.getClientID());
		
		//Next get the buyer
		Being buyer = serverWorldState.getBeing(mapLocation, this.getClientID());
		
		//Next check to make sure they have enough money
		if(buyer.getMoolah() >= purchaseItem.getVendorCost()) {
			System.out.println("BuyEvent: You have enough funds!");
			//If they do, transfer the Item to the player's inventory
			buyer.addSwagItem(purchaseItem);
			
			//Then subtract from their moolah
			buyer.decreaseMoolah(purchaseItem.getVendorCost());
			
			//Now return a BuyUpdate
			return new BuyUpdate(this.getClientID(), purchaseItem);
		}
	
		System.out.println("BuyEvent: You don't have enough moolah");
		//Else return null for invalid
		return null;
	}

}
