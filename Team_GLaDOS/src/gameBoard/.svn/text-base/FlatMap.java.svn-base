package gameBoard;

import java.io.Serializable;

/**
 * An object used to keep track of positions of items on a map.
 * 
 * @author amrsaad
 *
 */
public class FlatMap implements Serializable {
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The x coord on the map.
	 */
	private int x;
	/**
	 * The y coord on the map.
	 */
	private int y;
	/**
	 * Allows for checking to see if it's a valid FlatMap. Y boundary
	 */
	private final int xBoundary = 20;
	/**
	 * The x boundary
	 */
	private final int yBoundary = 20;

	/**
	 * Constructs a new FlatMap from the given x and y
	 * @param x The x coord to set
	 * @param y The y coord to set
	 */
	public FlatMap(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns whether this cell location is a valid cell in terms of a map
	 * @return Returns true or false if it is valid or not.
	 */
	public boolean isValid() {
		if((x >= 0) && (x < xBoundary) && (y >= 0) && (y < yBoundary))
			return true;
		else
			return false;
	}


	/**
	 * Getter for the x coord
	 * @return The x coord location
	 */
	public int getX() {
		return x;
	}

	/**
	 * Increment the x location.
	 * @param value increment by this value
	 */
	public void incrementX(int value) {
		x += value;
	}
	/**
	 * Increment the y location.
	 * @param value increment by this value
	 */
	public void incrementY(int value) {
		y += value;
	}
	/**
	 * Getter for the y coord
	 * @return The y coord location
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter for the y position. 
	 * @param y The y position to set.
	 */
	public void setY(int y){

		this.y = y;
	}
	/**
	 * Setter for the x position. 
	 * @param x The x position to set.
	 */
	public void setX(int x){

		this.x = x;
	}
}
