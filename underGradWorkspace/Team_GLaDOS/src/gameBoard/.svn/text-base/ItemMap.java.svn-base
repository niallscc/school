package gameBoard;

import java.awt.Graphics;

/**
 * @author amrsaad
 *
 */
public class ItemMap {
	
	/**
	 * 2-dimensional Cell array to represent the map.
	 */
	ItemsCell[][] map;
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
	public ItemMap(int x, int y){
		this.x = x;
		this.y = y;
		map = new ItemsCell[x][y];
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				map[i][j] = new ItemsCell(Items.getRandomItems(), new FlatMap(x,y));
	}

	
	/**
	 * Overloading with a parameter of type map to start to load a previously created map.
	 * 
	 * @param map The map being passed in.
	 */
	public ItemMap(ItemsCell[][] map){
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
	public ItemsCell getCellAt(FlatMap location) {
		return map[location.getX()][location.getY()];
	}

	
	/**
	 * Method to set the cell at the given location.
	 * 
	 * @param location 2-Dimensional location.
	 * @param value Object to be passed into the cell. 
	 */
	public void setCellAt(FlatMap location, Items value) {
		map[location.getX()][location.getY()] = new ItemsCell(value, location );
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
				matrix[i][j] = getCellAt(new FlatMap(i, j)).getItems().ordinal();
			}
		}
		return matrix;
	}
	
	/**
	 * Method to return the entire map object.
	 * 
	 * @return The map object.
	 */
	public  ItemsCell[][] getMap(){
		return map;
	}


	public void drawItems(Graphics graphicsInstance) {
		
		for( ItemsCell[] c: this.getMap()){
			for(ItemsCell c2: c){
				if( c2!=null){
					//System.out.println("Drawing item: "+ c2.getImage());
					graphicsInstance.drawImage(c2.getImage(), c2.getCoordinates().getX()*25, c2.getCoordinates().getY()*25, null);
				}
			}
		}

		
	}
}
