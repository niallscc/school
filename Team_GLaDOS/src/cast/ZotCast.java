package cast;

import java.util.Random;

import server.Server;
import swag.Swag;

import actors.Being;
import actors.PC;
import game.ClientWorldState;
import game.ServerWorldState;
import gameBoard.FlatMap;
import gameBoard.TerrainCell;

public class ZotCast extends Cast {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The location to cast it
	 */
	private FlatMap location;
	
	@SuppressWarnings("unused")
	private int mpLoss;
	
	/**
	 * Constructer for the Armageddon Cast initializing all of the variables
	 * to their appropriate values.
	 * 
	 * @param caster The caster of this spell.
	 * @param loc The location to cast the spell.
	 */
	public ZotCast(Being caster, FlatMap loc) {
		if(Server.spellsDEBUG) System.out.println("Zot");
		this.difficulty = 25;
		if(Server.spellsDEBUG) System.out.println("Difficulty: " + difficulty);
		this.mpCost = 3;
		if(Server.spellsDEBUG) System.out.println("MP COST: " +mpCost);
		this.levelReq = 1;
		if(Server.spellsDEBUG) System.out.println("Level Req: " + levelReq);
		this.location = loc;
		if(Server.spellsDEBUG) System.out.println("Casting Location " + location);

		
		this.prepTime = Math.max(0, (difficulty-caster.getIntelligence()));
		if(Server.spellsDEBUG) System.out.println("Calculated Prep Time: " + prepTime);
		this.executionTime = 0;
		if(Server.spellsDEBUG) System.out.println("Calculated execution time: " + executionTime);
		this.resetTime = Math.max(0, difficulty- (2*caster.getIntelligence()));
		if(Server.spellsDEBUG) System.out.println("Calculated reset time: " + resetTime);

	}
	/**
	 * The execute method is overridden to accomodate for each cast.
	 */
	@Override
	public void execute(ClientWorldState clientWorldState, int id) {
		//System.out.println("HEEEEEREEEE CLIENT");
		int clientID = id;
		//First get the map of where dis zot action is occurring.
		int mapLocation = clientWorldState.getMapLocation(clientID);
		
		//Next get the caster
		Being caster = clientWorldState.getBeing(mapLocation, clientID);
		if(Server.spellsDEBUG) System.out.println("The Caster: " + caster.getPlayerClass());
		
		//Next make sure they pass the requirements
		if(canCast(caster)){
			//Next get the Being to heal 
			Being target = clientWorldState.getWorldMap(mapLocation).getCellAt(location).getBeing();
			if(Server.spellsDEBUG) System.out.println("The targert: " + target.getPlayerClass());
			
			//Make sure there is a being
			if(target != null) {
				//calculate how much to heal.
				Random amount = new Random();
				//Calculate a random number between 1 and 10
				int damageAmount = amount.nextInt(5)+1;
				if(Server.spellsDEBUG) System.out.println("Damage Dealt: "+ damageAmount);
				//damage them.
				if(Server.spellsDEBUG)System.out.println(target.getHP());
				target.inflictDamage(damageAmount);
				if(Server.spellsDEBUG)System.out.println(target.getHP());
				//mpLoss = (caster.getMP()-this.mpCost);
				caster.setMP(caster.getMP()-this.mpCost);
				//caster.setMP(mpLoss);
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
		if(Server.spellsDEBUG)System.out.println("HEREEEE SERVER");
		//Next make sure they pass the requirements
		if(Server.spellsDEBUG)System.out.println("CAN YOU CAST: " +canCast(caster)+" MP: " + caster.getMP() );
		if(canCast(caster)){
			//Next get the Being to zot 
			Being target = serverWorldState.getWorldMap(mapLocation).getCellAt(location).getBeing();
			if(Server.spellsDEBUG)System.out.println(target);
			//Make sure there is a being
			if(target != null) {
				//calculate how much to damage.
				Random amount = new Random();
				//Calculate a random number between 1 and 5
				int damageAmount =caster.getLevel()*(amount.nextInt(5)+1);
				
				//damage them.
				if(Server.spellsDEBUG)System.out.println(target.getHP());
				target.inflictDamage(damageAmount);
				if(Server.spellsDEBUG)System.out.println(target.getHP());
				
				//caster.setMP(mpLoss);
				return new CastUpdate(clientID, new ZotCast(caster, location));
			}
		}
	
		//Otherwise return null for invalid
		return null;
	}

}
