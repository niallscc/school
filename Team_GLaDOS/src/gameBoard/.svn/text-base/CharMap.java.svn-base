package gameBoard;

import actors.Character;
import actors.Player;

/**
 * @author amrsaad
 *
 */
public class CharMap {
	
	/**
	 * 2-dimensional Cell array to represent the map.
	 */
	CharCell[][] map;
	
	/**
	 * X coordinate or Y coordinate 
	 */
	private final int x, y;
	
	/**
	 * Object to represent the location on a 2-dimensional plane.
	 */
	FlatMap location;
	
	/**
	 * Player object to represent a player.
	 */
	Player player;	

	/**
	 * Constructor setting the x and y locations accordingly with the map.
	 * 
	 * @param x X position on the map array.
	 * @param y Y position on the map array.
	 */
	public CharMap(int x, int y){
		this.x = x;
		this.y = y;
		map = new CharCell[x][y];
	}

	
	/**
	 * Overloading with a parameter of type map to start to load a previously created map.
	 * 
	 * @param map The map being passed in.
	 */
	public CharMap(CharCell[][] map){
		this.map = map;
		y = map.length;
		x = map[0].length;
	}
	

	/**
	 * Method to return the length of the map.
	 * 
	 * @return The length of the map.
	 */
	public int getLength(){
		return x;

	}

	/**
	 * Method to return the height of the map.
	 * 
	 * @return The height of the map.
	 */
	public int getHeight(){
		return y;
	}

	/**
	 * Method to get the actual cell item at the given location.
	 * 
	 * @param location 2-Dimensional location.
	 * @return FlatMap location object.
	 */
	public CharCell getCellAt(FlatMap location) {
		return map[location.getX()][location.getY()];
	}

	
	/**
	 * Method to set the cell at the given location.
	 * 
	 * @param location 2-Dimensional location.
	 * @param character Object to be passed into the cell. 
	 */
	public void setCellAt(FlatMap location, Character character) {
		map[location.getX()][location.getY()] = new CharCell(character, location );
	}
	
	/**
	 * Method to return the map matrix.
	 * 
	 * @return A 2-dimensional integer array storing the values of the enum.
	 */
	public Character[][] getMapMatrix() {
		Character matrix[][] = new Character[x][y];
		for (int i=0; i<x; i++) {
			for (int j=0; j<y; j++) {
				matrix[i][j] = getCellAt(new FlatMap(i, j)).getCharacter();
			}
		}
		return matrix;
	}
	
	/**
	 * Method to return the entire map object.
	 * 
	 * @return The map object.
	 */
	public CharCell[][] getMap(){
		return map;
	}

	
}
