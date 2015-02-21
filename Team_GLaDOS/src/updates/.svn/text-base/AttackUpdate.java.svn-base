package updates;

import server.Server;
import swag.RolledUpSock;
import actors.Being;
import actors.PC;
import game.ClientWorldState;
import gameBoard.FlatMap;
import gameBoard.TerrainCell;

/**
 * A AttackUpdate of type GladdosUpdate that processes and updates a Client's World state to 
 * reflect an attack event.
 * 
 * @author Ryan
 *
 */
public class AttackUpdate extends GladdosUpdate {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The cell to attack
	 */
	private FlatMap cellToAttack;
	/**
	 * Creates an AttackUpdate with the given User ID and the cell to attack.
	 * @param id
	 * @param attackCell
	 */
	public AttackUpdate(int id, FlatMap attackCell) {
		clientID = id;
		cellToAttack = attackCell;
		if(Server.attdefDEBUG) System.out.println("Client: " + clientID + " Attacking" );
	}
	/**
	 * Updates the client's world state and calculates:
	 * 1) If the attack is valid
	 * 2) If the attack hit
	 * 3) How much damage to do
	 * 4) Did the defender die
	 * 5) Give experience points
	 */
	@Override
	public void execute(ClientWorldState clientWorldState) {
		//Get the map all this action is taking place on.
		int mapLocation = clientWorldState.getMapLocation(this.getClientID());
		if(Server.attdefDEBUG)System.out.println("WTF, Server said : " + this.getClientID() + " is trying to attack cell: " + this.cellToAttack.getX() + ", " + this.cellToAttack.getY());
		
		//Get the defender and attack beings
		Being attacker = clientWorldState.getBeing(mapLocation, this.getClientID());
		Being defender = clientWorldState.getMaps().get(mapLocation).getCellAt(cellToAttack).getBeing();
		if(defender != null) {
			int calculation = Math.min(1, (attacker.getStrength() + (5*attacker.getLevel()) - defender.getDefenseValue()));
			int hitProbability = Math.max(0, calculation);
			if(hitProbability != 0) {
				if(Server.attdefDEBUG)System.out.println("OH NO! HIT! BEFORE HEALTH: " + defender.getHP());
				defender.inflictDamage(attacker.getAttackValue());
				
				//Now check if he died so we can give teh attacker some nice rewards
				if(!defender.isAlive()) {
					FlatMap currentloc = defender.getLocation();
					TerrainCell currentcell = clientWorldState.getWorldMap(mapLocation).getCellAt(currentloc);
					currentcell.setBeing(null);
					currentcell.addSwag(new RolledUpSock());
					currentcell.setImages();
					//If it's a PC, give them some experience points.
					if(attacker.isPC()) {
						if(Server.attdefDEBUG)System.out.println("ATTACKEVENT: IS a PC");
						PC pcAttacker = (PC)attacker;
						pcAttacker.increaseExperiencePoints(defender.getExperiencePointWorth());
					}
				}
				if(Server.attdefDEBUG)	System.out.println("After Health: " + defender.getHP());
			}
		}	
	}

}
