package events;

import actors.Being;
import game.ServerWorldState;
import server.Server;
import swag.Armor;
import swag.Swag;
import swag.Weapon;
import updates.EquipUpdate;
import updates.GladdosUpdate;

public class EquipEvent extends GladdosEvent {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The swag item to equip
	 */
	private Swag item;


	public EquipEvent(int id, Swag item) {
		super(id);
		this.item = item;
	}

	@Override
	public GladdosUpdate execute(ServerWorldState serverWorldState) {
		//First get the map location
		int mapLocation = serverWorldState.getMapLocation(this.getClientID());

		//Next get the equiper
		Being equiper = serverWorldState.getBeing(mapLocation, this.getClientID());
		if(Server.eventDEBUG) System.out.println("EquipEvent: Player ID: " + this.getClientID() + " Equiped: " + this.item.getName());

		//See if it's a weapon
		if(item.isWeapon())
			equiper.equipWeapon((Weapon)item);
		
		//or armor
		if(item.isArmor()) 
			equiper.equipArmor((Armor)item);

		//Now return a EquipUpdate
		return new EquipUpdate(this.getClientID(), item);

	}

}
