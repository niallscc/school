package cast;

import server.Server;
import game.ClientWorldState;
import game.ServerWorldState;
import gameBoard.FlatMap;
import actors.Being;
import actors.ManDistance;

public class FlyCast extends Cast {
	
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The location to heal
	 */
	private FlatMap location;
	
	/**
	 * Constructer for the Fly Cast initializing all of the variables
	 * to their appropriate values.
	 * 
	 * @param caster The caster of this spell.
	 * @param loc The location to cast the spell.
	 */
	public FlyCast(Being caster, FlatMap loc) {
		if(Server.spellsDEBUG) System.out.println("FlyCast");
		this.difficulty = 40;
		if(Server.spellsDEBUG) System.out.println("Difficulty: " + difficulty);
		this.mpCost = 15;
		if(Server.spellsDEBUG) System.out.println("MP COST: " +mpCost);
		this.levelReq = 8;
		if(Server.spellsDEBUG) System.out.println("Level Req: " + levelReq);
		this.location = loc;
		if(Server.spellsDEBUG) System.out.println("Casting Location " + location);

		
		this.prepTime = Math.max(0, (difficulty-caster.getIntelligence()));
		if(Server.spellsDEBUG) System.out.println("Calculated Prep Time: " + prepTime);
		this.executionTime = 30*caster.getLevel()+caster.getIntelligence();
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
		//First get the map of where dis healing action is occurring.
		int mapLocation = clientWorldState.getMapLocation(clientID);
		
		//Next get the caster
		Being caster = clientWorldState.getBeing(mapLocation, clientID);
		if(Server.spellsDEBUG) System.out.println("The Caster: " + caster.getPlayerClass());
		
		//Next make sure they pass the requirements
		if(canCast(caster)){
			//Next get the Being to heal 
			Being target = clientWorldState.getWorldMap(mapLocation).getCellAt(location).getBeing();
			if(Server.spellsDEBUG) System.out.println("The targert: " + target.getPlayerClass());
			//Make sure there is a being, and that they are within 8 manhatten cell distances away
			if((target != null) && (ManDistance.getDistance(caster.getLocation(), location) <=8)) {
				//calculate how much to heal.
				//Random amount = new Random();
				//Calculate a random number between 1 and 10
				int healAmount = 100-target.getHP();
				if(Server.spellsDEBUG) System.out.println("Amount Healed " + healAmount);
				
				//Heal them.
				target.heal(healAmount);
				caster.setMP(caster.getMP()-this.mpCost);

			}
		}
	}
	/**
	 * Overridden to send updates to the server world state
	 */
	@Override
	public CastUpdate execute(ServerWorldState serverWorldState, int id) {
		int clientID = id;
		//First get the map of where dis healing action is occurring.
		int mapLocation = serverWorldState.getMapLocation(clientID);
		
		//Next get the caster
		Being caster = serverWorldState.getBeing(mapLocation, clientID);
		
		//Next make sure they pass the requirements
		if(canCast(caster)){
			//Next get the Being to heal 
			Being target = serverWorldState.getWorldMap(mapLocation).getCellAt(location).getBeing();
			
			//Make sure there is a being, and that they are within 8 manhatten cell distances away
			if((target != null) && (ManDistance.getDistance(caster.getLocation(), location) <=8)) {
				//calculate how much to heal.
				//Random amount = new Random();
				//Calculate a random number between 1 and 10
				int healAmount = 100-target.getHP();
				
				System.out.println("Can heal - healing for: " + healAmount);
				//Heal them.
				target.heal(healAmount);
				
				return new CastUpdate(clientID, new GoodDrugsCast(caster, location));
			}
		}
	
		//Otherwise return null for invalid
		return null;
	}



}
