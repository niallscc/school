package events;

import java.util.List;

import actors.Being;
import game.ServerWorldState;
import game.WorldState;
import gameBoard.FlatMap;
import server.Server;
import swag.Swag;
import updates.GladdosUpdate;
import updates.PlayerMoveUpdate;

public class MoveEvent extends GladdosEvent {
	/**
	 * Use for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The axis to move.
	 */
	private char axis;
	/**
	 * The amount to move.
	 */
	private int distance;
	/**
	 * The x boundary a player can move
	 */
	private final int xBoundary = 20;
	/**
	 * The y boundary a player can move
	 */
	private final int yBoundary = 20;


	/**
	 * MoveEvents will send the axis that the player wants to move on (x, or y) and the amount(1 or -1).
	 * The server will look up the player's id in the hashMap, and then update it. 
	 * 
	 * @param id The ClientID making the move.
	 * @param axis The axis to move them on.
	 * @param value The amount to move them by.
	 */
	public MoveEvent(int id, char a, int value, WorldState clientWorldState) {
		super(id);

		axis = a;
		distance = value;
		eventName = "Move";
		if(Server.eventDEBUG) System.out.println("MOVE EVENT: ");
		//Calculate the Prep time for the event using the clientWorldState
		int map = clientWorldState.getMapLocation(this.getClientID());
		Being beingToMove = clientWorldState.getMapBeings(map).get(""+this.getClientID());
		FlatMap currentLocation = beingToMove.getLocation();
		FlatMap futureLocation = null;
		if(axis == 'x') {
	//		if(Server.eventDEBUG) System.out.println("Movement is east or west");
			futureLocation = new FlatMap(currentLocation.getX()+distance, currentLocation.getY());
			//First check to ensure that it is a valid move
			if(((futureLocation.getX()) <xBoundary) && ((futureLocation.getX()) >= 0)){ 
				valid = true;
			//	if(Server.eventDEBUG) System.out.println("Movement is Valid");
			}
			else{
				//if(Server.eventDEBUG) System.out.println("Movement is Invalid");
				valid = false;
			}
		}

		else if(axis == 'y') {
			//if(Server.eventDEBUG) System.out.println("Movement is north or south");
			futureLocation= new FlatMap(currentLocation.getX(), currentLocation.getY()+distance);
			if(((futureLocation.getY()) < yBoundary) && ((futureLocation.getY()) >= 0)){ 
		//		if(Server.eventDEBUG) System.out.println("Movement is Valid");
				valid = true;
			}
			else{
		//		if(Server.eventDEBUG) System.out.println("Movement is InValid");
				valid = false;
			}
		
	}

		if(valid) {
			//Set the prep time for this moveEvent
			int terrainCost = clientWorldState.getWorldMap(map).getCellAt(futureLocation).getTerrainCost();
			double cost = terrainCost - beingToMove.getSpeed() + (beingToMove.getEncumberance());
			//if(Server.eventDEBUG) System.out.println("MoveEvent: Moving and the cost is: " + cost);
			this.prepTime = (int)Math.max(cost, 1.0);
			if(Server.eventDEBUG) System.out.println("MoveEventMoving prep time: " + prepTime);
		}
	}

	@Override
	public GladdosUpdate execute(ServerWorldState serverWorldState) {
		//First find the map that this id is on.
		int map = serverWorldState.getMapLocation(this.getClientID());

		Being beingToMove = serverWorldState.getMapBeings(map).get(""+this.getClientID());
		FlatMap currentLocation = beingToMove.getLocation();

		//Then update it based on the axis and distance to move if it is a valid move.
		if(axis == 'x') {
			//First check to ensure that it is a valid move
			if(valid) {
				if(Server.eventDEBUG) System.out.println("Move event sent east or west");
				//Get what the future FlatMap location will be.
				FlatMap futureLocation = new FlatMap(currentLocation.getX()+distance, currentLocation.getY());

				//Make sure that the future cell isn't currently occupied, if it isn't, remove the being
				//from their currentCell, and place them on the future cell.
				if(serverWorldState.getWorldMap(map).getCellAt(futureLocation).getBeing() == null) {
					//Update the previous and future cell, and the being location
					serverWorldState.getWorldMap(map).getCellAt(currentLocation).setBeing(null);
					serverWorldState.getWorldMap(map).getCellAt(futureLocation).setBeing(beingToMove);
					serverWorldState.getMapBeings(map).get(""+this.getClientID()).getLocation().incrementX(distance);
					
					//Now get the moolah on the cell.
					double moolah = (double)serverWorldState.getWorldMap(map).getCellAt(futureLocation).getMoolah();
					serverWorldState.getMapBeings(map).get(""+this.getClientID()).increaseMoolah(moolah);
					
					//Now get the swag on the cell.
					List<Swag> swag = serverWorldState.getWorldMap(map).getCellAt(futureLocation).getSwag();
					//Iterate through it and add it to the player.
					for(Swag i : swag) {
						serverWorldState.getMapBeings(map).get(""+this.getClientID()).addSwagItem(i);
					}
					
					return new PlayerMoveUpdate(getClientID(), axis, distance);
				}
			}
		}
		else if(axis == 'y') {
			if(valid) {
				if(Server.eventDEBUG) System.out.println("Move event sent north or south");
				//Get what the future FlatMap location will be.
				FlatMap futureLocation = new FlatMap(currentLocation.getX(), currentLocation.getY()+distance);

				//Make sure that the future cell isn't currently occupied, if it isn't, remove the being
				//from their currentCell, and place them on the future cell.
				if(serverWorldState.getWorldMap(map).getCellAt(futureLocation).getBeing() == null) {
					//Now update the cells
					serverWorldState.getWorldMap(map).getCellAt(currentLocation).setBeing(null);
					serverWorldState.getWorldMap(map).getCellAt(futureLocation).setBeing(beingToMove);

					//Update their location
					serverWorldState.getMapBeings(map).get(""+this.getClientID()).getLocation().incrementY(distance);
					
					//Now get the moolah on the cell.
					double moolah = (double)serverWorldState.getWorldMap(map).getCellAt(futureLocation).getMoolah();
					serverWorldState.getMapBeings(map).get(""+this.getClientID()).increaseMoolah(moolah);
					
					//Now get the swag on the cell.
					List<Swag> swag = serverWorldState.getWorldMap(map).getCellAt(futureLocation).getSwag();
					//Iterate through it and add it to the player.
					for(Swag i : swag) {
						serverWorldState.getMapBeings(map).get(""+this.getClientID()).addSwagItem(i);
					}

					return new PlayerMoveUpdate(getClientID(), axis, distance);
				}
			}
		}
		//Return null for invalid, server will throw it out.
		return null;
	}
}
