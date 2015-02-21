package updates;

import game.ClientWorldState;
import actors.Being;
import server.Server;
import swag.Armor;
import swag.Swag;
import swag.Weapon;


public class EquipUpdate extends GladdosUpdate {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The swag item to equip
	 */
	private Swag item;

	public EquipUpdate(int id, Swag item) {
		this.clientID = id;
		this.item = item;
		if(Server.eventDEBUG) System.out.println("Equiping item: " + item.getName());
	}

	@Override
	public void execute(ClientWorldState clientWorldState) {
		//First get the map location
		int mapLocation = clientWorldState.getMapLocation(this.getClientID());

		//Next get the equiper
		Being equiper = clientWorldState.getBeing(mapLocation, this.getClientID());
		if(Server.eventDEBUG) System.out.println("EquipUpdate: Player ID: " + this.clientID + " Equiped: " + this.item.getName());

		//See if it's a weapon
		if(item.isWeapon())
			equiper.equipWeapon((Weapon)item);

		//or armor
		if(item.isArmor()) 
			equiper.equipArmor((Armor)item);
	}

}

