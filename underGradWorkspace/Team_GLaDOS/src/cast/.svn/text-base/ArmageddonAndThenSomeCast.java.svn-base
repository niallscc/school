package cast;

import game.ClientWorldState;
import game.ServerWorldState;
import gameBoard.FlatMap;
import gameBoard.TerrainCell;

import java.util.ArrayList;

import server.Server;
import swag.Swag;

import actors.Being;
import actors.ManDistance;
import actors.PC;

/**
 * Cast for ArmageddonAndThenSome. It has the following stats:
 * Difficulty: 100
 * MP Cost: 3 per Global Clock Tick
 * Effect: 7 Hit point damage per global Clock tick
 * Execute time: PC.Intelligence or until MP == 0
 * Level: 18
 * 
 * @author Ryan
 *
 */
public class ArmageddonAndThenSomeCast extends Cast {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The location to cast it
	 */
	private FlatMap location;

	/**
	 * Constructor for the Armageddon Cast initializing all of the variables
	 * to their appropriate values.
	 * 
	 * @param caster The caster of this spell.
	 * @param loc The location to cast the spell.
	 */
	public ArmageddonAndThenSomeCast(Being caster, FlatMap loc) {
		if(Server.spellsDEBUG) System.out.println("Armageddon");
		this.difficulty = 100;
		if(Server.spellsDEBUG) System.out.println("Difficulty: " + difficulty);
		this.mpCost = 3;
		if(Server.spellsDEBUG) System.out.println("MP COST: " +mpCost);
		this.levelReq = 18;
		if(Server.spellsDEBUG) System.out.println("Level Req: " + levelReq);
		this.location = loc;
		if(Server.spellsDEBUG) System.out.println("Casting Location " + location);

		this.prepTime = Math.max(0, (difficulty-caster.getIntelligence()));
		if(Server.spellsDEBUG) System.out.println("Calculated Prep Time: " + prepTime);
		this.executionTime = caster.getIntelligence();
		if(Server.spellsDEBUG) System.out.println("Calculated execution time: " + executionTime);
		this.resetTime = Math.max(0, difficulty- (2*caster.getIntelligence()));
		if(Server.spellsDEBUG) System.out.println("Calculated reset time: " + resetTime);

	}

	/**
	 * The execute method that is called ClientSide. Looks through each cell and
	 */
	@Override
	public void execute(ClientWorldState clientWorldState, int id) {
		int clientID = id;
		//First get the map of where dis zot action is occurring.
		int mapLocation = clientWorldState.getMapLocation(clientID);

		//Next get the caster
		Being caster = clientWorldState.getBeing(mapLocation, clientID);
		if(Server.spellsDEBUG) System.out.println("The Caster: " + caster.getPlayerClass());

		//Next make sure they pass the requirements
		if(canCast(caster)){
			ArrayList<FlatMap> cells = ManDistance.getDistRadius(location,caster.getLevel()/4);
			for(FlatMap fm : cells){
				if(Server.spellsDEBUG) System.out.println("Cell Hit: " + fm.getX()+","+fm.getY());
				//Next get the Being to heal 
				Being target = clientWorldState.getWorldMap(mapLocation).getCellAt(fm).getBeing();
				if(Server.spellsDEBUG) System.out.println("The targert: " + target.getPlayerClass());
				//Make sure there is a being
				if(target != null) {
					TerrainCell currentcell = clientWorldState.getWorldMap(clientWorldState.getMapLocation(target.getID())).getCellAt(target.getLocation());
					currentcell.setBeing(null);
					for(Swag swag: target.getInventory()){
						currentcell.addSwag(swag);
					}
					currentcell.setMoolah(target.getMoolah()+currentcell.getMoolah());
					//Calculate a random number between 1 and 10
					int damageAmount =7;
					if(Server.spellsDEBUG) System.out.println("Damage Dealt: "+ damageAmount);
					//damage them.
					target.inflictDamage(damageAmount);
					caster.setMP(caster.getMP()-this.mpCost);
					
					if(!target.isAlive()) {
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
	 * Executed ServerSide
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
			ArrayList<FlatMap> cells = ManDistance.getDistRadius(location,caster.getLevel()/4);
			for(FlatMap fm : cells){
				//Next get the Being to heal 
				Being target = serverWorldState.getWorldMap(mapLocation).getCellAt(fm).getBeing();

				//Make sure there is a being
				if(target != null) {
					//Calculate a random number between 1 and 10
					int damageAmount =7;

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
