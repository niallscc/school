package gameBoard;

import java.awt.Graphics;
import java.io.Serializable;

/**
 * This is the main cell unit for the Gladdos game engine. It will be a holder for a being and a cell as 
 * well as the actual terrain image location
 * 
 * @author Gladdos
 *
 */
public class TerrainMap implements Serializable {
	
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 2-dimensional Cell array to represent the map.
	 */
	TerrainCell[][] map;
	
	/**
	 * X coordinate or Y coordinate 
	 */
	private final int x, y;
	
	/**
	 * Object to represent the location on a 2-dimensional plane.
	 */
	FlatMap location;	
	/**
	 * Constructor setting the x and y locations accordingly with the map.
	 * 
	 * @param x X position on the map array.
	 * @param y Y position on the map array.
	 */
	public TerrainMap(int x, int y){
		this.x = x;
		this.y = y;
		map = new TerrainCell[x][y];
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++){
				map[i][j] = new TerrainCell(Terrain.getRandomTerrain(), new FlatMap(i,j));
				//System.out.println(map[i][j].getTerrainCost());
			}
	}

	
	/**
	 * Overloading with a parameter of type map to start to load a previously created map.
	 * 
	 * @param map The map being passed in.
	 */
	public TerrainMap(TerrainCell[][] map){
		this.map = map;
		y = map[0].length;
		x = map.length;
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
	public TerrainCell getCellAt(FlatMap location) {
		return map[location.getX()][location.getY()];
	}
	
	public TerrainCell getCell(int x, int y){
		return map[x][y];
	}

	/**
	 * Method to set the cell at the given location.
	 * 
	 * @param location 2-Dimensional location.
	 * @param terrain Object to be passed into the cell. 
	 */
	public void setCellAt(FlatMap location, Terrain terrain) {
		//System.out.println ("X "+location.getX()+" Y "+location.getY() );
		map[location.getX()][location.getY()] = new TerrainCell(terrain, location );
		
	}
	
	/**
	 * Method to return the map matrix.
	 * 
	 * @return A 2-dimensional integer array storing the values of the enum.
	 */
	public int[][] getMapMatrix() {
		int matrix[][] = new int[x][y];
		for (int i=0; i<x; i++) {
			for (int j=0; j<y; j++) {
				matrix[i][j] = getCellAt(new FlatMap(i, j)).getTerrain().ordinal();
			}
		}
		return matrix;
	}
	
	
	/**
	 * Method to load the map matrix into the map object. 
	 * 
	 * @param m 2-dimensional integer array storing the values of the enum.
	 * @return The map object.
	 */
	public static TerrainMap loadMapMatrix(int[][] m) {
		TerrainMap map = new TerrainMap(m.length, m[0].length);
		for (int i=0; i<m.length; i++) {
			for (int j=0; j<m[i].length; j++) {
				map.setCellAt( new FlatMap(i,j), Terrain.getTerrain(m[i][j]) );
			}
		}
		return map;
	}
	
	/**
	 * Method to return the entire map object.
	 * 
	 * @return The map object.
	 */
	public  TerrainCell[][] getMap(){
		return map;
	}
	
	/**
	 * Draws the map onto the provided Graphics instance
	 * @param g The graphics instance
	 */
	public void drawMap(Graphics g) {
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				map[i][j].draw(g);
			}
		}
	}
	
}
