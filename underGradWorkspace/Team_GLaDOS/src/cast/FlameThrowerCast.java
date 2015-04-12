package cast;

import java.util.ArrayList;

import server.Server;
import swag.Swag;

import actors.Being;
import actors.ManDistance;
import actors.PC;
import game.ClientWorldState;
import game.ServerWorldState;
import gameBoard.FlatMap;
import gameBoard.TerrainCell;

public class FlameThrowerCast extends Cast {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The location to cast it
	 */
	private FlatMap location;
	/**
	 * The location of the direction of the flamethrower spell.
	 */
	private FlatMap dir;

	/**
	 * Constructer for the FlameThrower Cast initializing all of the variables
	 * to their appropriate values.
	 * 
	 * @param caster The caster of this spell.
	 * @param dir The direction of the cast.
	 * @param loc The location to cast the spell.
	 */
	public FlameThrowerCast(Being caster, FlatMap loc,FlatMap dir) {
		if(Server.spellsDEBUG) System.out.println("FlameThrower");
		this.difficulty = 30;
		if(Server.spellsDEBUG) System.out.println("Difficulty: " + difficulty);
		this.mpCost = 2;
		if(Server.spellsDEBUG) System.out.println("MP COST: " +mpCost);
		this.levelReq = 5;
		if(Server.spellsDEBUG) System.out.println("Level Req: " + levelReq);
		this.location = loc;
		if(Server.spellsDEBUG) System.out.println("Casting Location " + location);
		this.dir = dir;
		if(Server.spellsDEBUG) System.out.println("Directional Cell " + dir.getX()+","+dir.getY());


		this.prepTime = Math.max(0, (difficulty-caster.getIntelligence()));
		if(Server.spellsDEBUG) System.out.println("Calculated Prep Time: " + prepTime);
		this.executionTime = caster.getIntelligence();
		if(Server.spellsDEBUG) System.out.println("Calculated execution time: " + executionTime);
		this.resetTime = Math.max(0, difficulty- (2*caster.getIntelligence()));
		if(Server.spellsDEBUG) System.out.println("Calculated reset time: " + resetTime);
		

	}

	/**
	 * The execute method is overridden to accomodate for each cast.
	 */
	@Override
	public void execute(ClientWorldState clientWorldState, int id) {
		int clientID = id;
		//First get the map of where dis zot action is occurring.
		int mapLocation = clientWorldState.getMapLocation(clientID);

		//Next get the caster
		Being caster = clientWorldState.getBeing(mapLocation, clientID);
		if(Server.spellsDEBUG) System.out.println("FlameThrowerCast: ID: " + clientID);

		//Next make sure they pass the requirements
		if(canCast(caster)){
			ArrayList<FlatMap> cells = ManDistance.getLineDist(caster.getLocation(), dir, 5);
			for(FlatMap fm : cells){
				if(Server.spellsDEBUG) System.out.println("FlameThrowerCast: Cell Hit: " + fm.getX()+","+fm.getY());
				//Next get the Being to damage 
				Being target = clientWorldState.getWorldMap(mapLocation).getCellAt(fm).getBeing();
				

				//Make sure there is a being
				if(target != null) {
					//Calculate a random number between 1 and 10
					int damageAmount =2;
					if(Server.spellsDEBUG) System.out.println("FlameThrowerCast: Damage: "+ damageAmount + " Done to ID: " + target.getID());

					//damage them.
					target.inflictDamage(damageAmount);
					caster.setMP(caster.getMP()-this.mpCost);
					if(!target.isAlive()) {
						TerrainCell currentcell = clientWorldState.getWorldMap(clientWorldState.getMapLocation(target.getID())).getCellAt(target.getLocation());
						currentcell.setBeing(null);
						for(Swag swag: target.getInventory()){
							currentcell.addSwag(swag);
						}
						currentcell.setMoolah(target.getMoolah()+currentcell.getMoolah());
						//clientWorldState.getMapBeings(clientWorldState.getMapLocation(target.getID())).remove(""+target.getID());
						//If it's a PC, give them some experience points.
						if(caster.isPC()) {
							PC pcAttacker = (PC)caster;
							pcAttacker.increaseExperiencePoints(target.getExperiencePointWorth());
						}
					}

				}
			}
		}
	}

	/**
	 * Overridden to send updates to the server world state
	 */
	@Override
	public CastUpdate execute(ServerWorldState serverWorldState, int id) {
		int clientID = id;
		//First get the map of where dis zot action is occurring.
		int mapLocation = serverWorldState.getMapLocation(clientID);

		//Next get the caster
		Being caster = serverWorldState.getBeing(mapLocation, clientID);

		//Next make sure they pass the requirements
		if(canCast(caster)){
			ArrayList<FlatMap> cells = ManDistance.getLineDist(caster.getLocation(), dir, 5);
			for(FlatMap fm : cells){
				//Next get the Being to heal 
				Being target = serverWorldState.getWorldMap(mapLocation).getCellAt(fm).getBeing();

				//Make sure there is a being
				if(target != null) {
					//Calculate a random number between 1 and 10
					int damageAmount = 2;

					//damage them.
					target.inflictDamage(damageAmount);

					return new CastUpdate(clientID, new ZorchCast(caster, location));
				}
			}
		}
		//Otherwise return null for invalid
		return null;
	}
}
