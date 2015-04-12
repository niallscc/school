package updates;

import java.util.List;

import server.Server;
import swag.Swag;
import actors.Being;
import game.ClientWorldState;
import gameBoard.FlatMap;

public class PlayerMoveUpdate extends GladdosUpdate {

	/**
	 * Used for serialization
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
	private int clientID;
	/**
	 * PlayerMoveUpdate will send the axis that the player wants to move on (x, or y) and the amount(1 or -1).
	 * The server will look up the player's id in the hashMap, and then update it. 
	 * 
	 * @param id The ClientID making the move.
	 * @param axis The axis to move them on.
	 * @param value The amount to move them by.
	 */
	public PlayerMoveUpdate(int id, char a, int value) {
		clientID = id;
		axis = a;
		distance = value;
	}

	@Override
	public void execute(ClientWorldState clientWorldState) {
		//First find the map that this id is on.
		int map = clientWorldState.getMapLocation(clientID);

		if(Server.updateDEBUG)System.out.println("Update: Moving Client: " + clientID + " Map: " + map);
		Being beingToMove = clientWorldState.getMapBeings(map).get(""+clientID);

		FlatMap currentLocation = beingToMove.getLocation();

		//Then update it based on the axis and distance to move if it is a valid move.
		if(axis == 'x') {
			//First check to ensure that it is a valid move
			//Get what the future FlatMap location will be.
			FlatMap futureLocation = new FlatMap(currentLocation.getX()+distance, currentLocation.getY());

			//Make sure that the future cell isn't currently occupied, if it isn't, remove the being
			//from their currentCell, and place them on the future cell.
			if(clientWorldState.getWorldMap(map).getCellAt(futureLocation).getBeing() == null) {
				clientWorldState.getWorldMap(map).getCellAt(currentLocation).setBeing(null);
				clientWorldState.getWorldMap(map).getCellAt(futureLocation).setBeing(beingToMove);
				clientWorldState.getMapBeings(map).get(""+clientID).getLocation().incrementX(distance);
				
				//Now get the moolah on the cell.
				double moolah = (double)clientWorldState.getWorldMap(map).getCellAt(futureLocation).getMoolah();
				clientWorldState.getMapBeings(map).get(""+this.clientID).increaseMoolah(moolah);
				
				//Now get the swag on the cell.
				List<Swag> swag = clientWorldState.getWorldMap(map).getCellAt(futureLocation).getSwag();
				//Iterate through it and add it to the player.
				for(Swag i : swag) {
					clientWorldState.getMapBeings(map).get(""+this.clientID).addSwagItem(i);
				}
			
			}
		}

		else if(axis == 'y') {
			//Get what the future FlatMap location will be.
			FlatMap futureLocation = new FlatMap(currentLocation.getX(), currentLocation.getY()+distance);

			//Make sure that the future cell isn't currently occupied, if it isn't, remove the being
			//from their currentCell, and place them on the future cell.
			if(clientWorldState.getWorldMap(map).getCellAt(futureLocation).getBeing() == null) {
				clientWorldState.getWorldMap(map).getCellAt(currentLocation).setBeing(null);
				clientWorldState.getWorldMap(map).getCellAt(futureLocation).setBeing(beingToMove);
				clientWorldState.getMapBeings(map).get(""+clientID).getLocation().incrementY(distance);
				
				//Now get the moolah on the cell.
				double moolah = (double)clientWorldState.getWorldMap(map).getCellAt(futureLocation).getMoolah();
				clientWorldState.getMapBeings(map).get(""+clientID).increaseMoolah(moolah);
				
				//Now get the swag on the cell.
				List<Swag> swag = clientWorldState.getWorldMap(map).getCellAt(futureLocation).getSwag();
				//Iterate through it and add it to the player.
				for(Swag i : swag) {
					clientWorldState.getMapBeings(map).get(""+this.clientID).addSwagItem(i);
				}
				

			}
		}

	}
}
