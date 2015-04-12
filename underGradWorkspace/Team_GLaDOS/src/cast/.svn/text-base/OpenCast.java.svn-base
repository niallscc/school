package cast;

import server.Server;
import actors.Being;
import game.ClientWorldState;
import game.ServerWorldState;
import gameBoard.Portal;

public class OpenCast extends Cast {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructer for the Open Cast initializing all of the variables
	 * to their appropriate values.
	 * 
	 * @param caster The caster of this spell.
	 */
	public OpenCast(Being caster) {
		if(Server.spellsDEBUG) System.out.println("OpenCast created");
		this.difficulty = 20;
		if(Server.spellsDEBUG) System.out.println("Difficulty: " + difficulty);
		this.mpCost = 10;
		if(Server.spellsDEBUG) System.out.println("MP COST: " +mpCost);
		this.levelReq = 3;
		if(Server.spellsDEBUG) System.out.println("Level Req: " + levelReq);
		
		//The way our implementation works, makes it so that we can just add 10 onto the prep time to account
		//for the execution time.
		this.prepTime = Math.max(0, (difficulty-caster.getIntelligence())) + 10;
		if(Server.spellsDEBUG) System.out.println("Calculated Prep Time: " + prepTime);
		if(Server.spellsDEBUG) System.out.println("Calculated execution time: " + executionTime);
		this.resetTime = Math.max(0, difficulty- (2*caster.getIntelligence()));
		if(Server.spellsDEBUG) System.out.println("Calculated reset time: " + resetTime);
		
	}

	/**
	 * The execute method is overridden to accomodate for each cast.
	 */
	@Override
	public void execute(ClientWorldState clientWorldState, int id) {
		if(Server.spellsDEBUG)System.out.println("In client execution of Open!");
		int clientID = id;
		//First get their map location
		int mapLocation = clientWorldState.getMapLocation(clientID);
		if(Server.spellsDEBUG)System.out.println("Original Map Location: "+ mapLocation);

		//Get their Being object
		Being beingToPort = clientWorldState.getBeing(mapLocation, clientID);
		if(Server.spellsDEBUG)System.out.println("Being to be teleported: "+" clientID: " + beingToPort.getID()+" CLASS: " + beingToPort.getPlayerClass());

		//Now get the portal for the cell they are on
		Portal portal = clientWorldState.getWorldMap(mapLocation).getCellAt(beingToPort.getLocation()).getPortal();
		if(Server.spellsDEBUG)System.out.println("Portal Location: " + portal.getCurrentLocation().getX()+","+portal.getCurrentLocation().getY());
		//Uh oh...They gonna be teleported!
		//World teleportation is a complex process that requires years and years of physics and math
		//Well...We've figured out how to do it with 6 lines of code.

		//Step 1: remove the being from their old map
		clientWorldState.getMapBeings(mapLocation).remove(""+clientID);
		if(Server.spellsDEBUG)System.out.println("Removed Client: " + clientID );
		//Step 2: Place the being in the future map Beings HasMap
		clientWorldState.getMapBeings(portal.getNextMap()).put(""+clientID, beingToPort);
		if(Server.spellsDEBUG)System.out.println("Added Client To New Map: " + clientID );
		//Step 3: Remove them from the IDMap with their old map location
		clientWorldState.getIDMap().remove(""+clientID);
		//Step 4: Place them in the IDMap with their new map location.
		clientWorldState.getIDMap().put(""+clientID, portal.getNextMap());
		//Step 5: Remove them from their old cell.
		clientWorldState.getWorldMap(mapLocation).getCellAt(beingToPort.getLocation()).setBeing(null);
		//Step 6: Place them in the new cell
		clientWorldState.getWorldMap(portal.getNextMap()).getCellAt(portal.getNextMapPortalLocation()).setBeing(beingToPort);
		beingToPort.setMP(beingToPort.getMP()-this.mpCost);
		

		
	}

	/**
	 * The execute method is overridden to accomodate for each cast.
	 */
	@Override
	public CastUpdate execute(ServerWorldState serverWorldState, int id) {
		int clientID = id;

		//First get their map location
		int mapLocation = serverWorldState.getMapLocation(clientID);

		//Get their Being object
		Being beingToPort = serverWorldState.getBeing(mapLocation,clientID);
		//Make sure that they have the requirements to cast Open
		if(canCast(beingToPort)) {
			//Now get the portal for the cell they are on
			Portal portal = serverWorldState.getWorldMap(mapLocation).getCellAt(beingToPort.getLocation()).getPortal();

			//Make sure that it is set
			if(portal != null) {
				//Uh oh...They gonna be teleported!
				//World teleportation is a complex process that requires years and years of physics and math
				//Well...We've figured out how to do it with 6 lines of code.
				//Step 1: remove the being from their old map
				serverWorldState.getMapBeings(mapLocation).remove(""+clientID);
				//Step 2: Place the being in the future map Beings HasMap
				serverWorldState.getMapBeings(portal.getNextMap()).put(""+clientID, beingToPort);
				//Step 3: Remove them from the IDMap with their old map location
				serverWorldState.getIDMap().remove(""+clientID);
				//Step 4: Place them in the IDMap with their new map location.
				serverWorldState.getIDMap().put(""+clientID, portal.getNextMap());
				//Step 5: Remove them from their old cell.
				serverWorldState.getWorldMap(mapLocation).getCellAt(beingToPort.getLocation()).setBeing(null);
				//Step 6: Place them in the new cell
				serverWorldState.getWorldMap(portal.getNextMap()).getCellAt(portal.getNextMapPortalLocation()).setBeing(beingToPort);
				

				//Now return the update to all clients.
				return new CastUpdate(clientID, new OpenCast(beingToPort));
			}
		}
		//Otherwise it's invalid, just return null and the server will throw it out.
		return null;
	}
}
