package events;

import actors.Being;
import actors.PC;
import game.ServerWorldState;
import gameBoard.FlatMap;
import server.Server;
import updates.AttackUpdate;

import updates.GladdosUpdate;

public class AttackEvent extends GladdosEvent {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The cell location to attack
	 */
	private FlatMap cellToAttack;	

	/**
	 * COnstructor for an attack event to initialize the event variables and begin
	 * processing to send to the server.
	 * 
	 * @param id The client's ID.
	 * @param attacker The Being doing the attacking.
	 * @param cTOa The Cell that is being attacked.
	 */
	public AttackEvent(int id, Being attacker, FlatMap cTOa) {
		super(id);
		this.eventName = "Attack";
		if(Server.attdefDEBUG) System.out.println("Starting Attack Event: ");

		//Set the cell and calculate the prep time for this event.
		//The attack cell is the cell being passed in.
		cellToAttack = cTOa;
		this.prepTime = (int)Math.max(5.0, (int)(attacker.getEquipedWeapon().getDamage()-attacker.getSpeed()));
	}

	@Override
	public GladdosUpdate execute(ServerWorldState serverWorldState) {
		//Get the map all this action is taking place on.
		int mapLocation = serverWorldState.getMapLocation(this.getClientID());
		System.out.println("AttackEvent: Starting!");
		if(Server.attdefDEBUG) System.out.println("AttackEvent: Starting!");
		
		//First make sure the attack cell is even a valid cell to attack.
		if(cellToAttack.isValid()) {
			//Get the defender and attack beings
			Being attacker = serverWorldState.getBeing(mapLocation, this.getClientID());
			Being defender = serverWorldState.getMaps().get(mapLocation).getCellAt(cellToAttack).getBeing();

			
			//If there was a being on the cell.
			if(defender != null) {
				int calculation = Math.min(1, ((attacker.getStrength() + (5*attacker.getLevel())) - defender.getDefenseValue()));
				if(Server.attdefDEBUG) System.out.println("( "+attacker.getStrength() +" + "+ 5*attacker.getLevel()+") -"+defender.getDefenseValue());
				int hitProbability = Math.max(0, calculation);
				if(hitProbability != 0) {
					if(Server.attdefDEBUG) System.out.println("AttackEvent: ID: " + this.getClientID()+ " is attacking: " + defender.getID() + " with: " + attacker.getAttackValue() + " damage");
					//Inflict the damage on the poor guy
					defender.inflictDamage(attacker.getAttackValue());
					//Now check if he died so we can give teh attacker some nice rewards
					if(!defender.isAlive()) {
						//If it's a PC, give them some experience points.
						if(attacker.isPC()) {
							PC pcAttacker = (PC)attacker;
							pcAttacker.increaseExperiencePoints(defender.getExperiencePointWorth());
							if(Server.attdefDEBUG) System.out.println("AttackEvent: " + this.getClientID() + " Gained: " +defender.getExperiencePointWorth());
						}
					}
					return new AttackUpdate(this.getClientID(), this.cellToAttack);
				}
			}
		}
		//Otherwise return null for invalid
		return null;
	}

}
