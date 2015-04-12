package actors;

import java.io.Serializable;

import gameBoard.FlatMap;

/**
 * A Monster class from which each specific type of monster will be extended from.
 * 
 * @author amrsaad
 *
 */
public class Monster extends Being implements Serializable{

	/**
	 * Defines the serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Integer value holding the movement speed of 
	 * the monster.
	 */
	protected int moveSpeed;
	/**
	 * Constructor for a Monster in general.
	 * 
	 * @param spawnloc Spawn Location.
	 */
	public Monster(FlatMap spawnloc) {
		super(spawnloc);
	}

	/**
	 * Get the speed of the monster.
	 * @return Movement Speed.
	 */
	public int getSpeed(){
		return moveSpeed;
	}
	

}
