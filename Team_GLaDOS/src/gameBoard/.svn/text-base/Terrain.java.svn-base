package gameBoard;

import java.util.Random;

/**
 * 
 */

/**
 * @author amrsaad
 *
 */
public enum Terrain {
	
	/**
	 * Initializing the enum values.
	 */
	Plain ( 5, 10, "./GameGraphics/grass_pane.png"),
	Desert ( 25, 10, "./GameGraphics/sand_pane.png"),
	Water ( 30, 30, "./GameGraphics/water_pane.png"),
	Forest ( 30, 30, "./GameGraphics/forrest_pane.png"),
	Mountain ( 40, 100, "./GameGraphics/mountain_pane.png"),
	Swamp ( 0, 60, "./GameGraphics/swamp_pane.png"),
	Wall (0,500, "./GameGraphics/brick_wall.png");
	
	/**
	 * Private final int to hold the amount of moolah.
	 */
	private int moolah;
	/**
	 * Private final int to hold the move cost of each terrain.
	 */
	private final int movecost;
	/**
	 * Private final string to hold the file name of the graphics.
	 */
	private final String filename;
	/**
	 * Randomly generated number to generate the terrain.
	 */
	private static final Random rand = new Random();

	/**
	 * Enum constructor to initialize the integer values. 
	 * 
	 * @param moolah Money variable.
	 * @param movecost Cost to move to the cell.
	 * @param filename Name of the file to be opened.
	 */
	Terrain(int moolah, int movecost, String filename){
		this.moolah = moolah;
		this.movecost = movecost;
		this.filename = filename;
	}

	/**
	 * Method to return the Money value.
	 * 
	 * @return Amount of money.
	 */
	public int getMoolah() {
		return moolah;
	}


	/**
	 * Method to return the move cost.
	 * 
	 * @return The move cost to the cell.
	 */
	public int getmovecostCost() {
		return movecost;
	}

	/**
	 * Method to get the file name of the picture.
	 * 
	 * @return The filename.
	 */
	public String getImageFileName() {
		return filename;
	}

	/**
	 * Method to get a Random terrain to put into a cell.
	 * 
	 * @return A random terrain
	 */
	public static Terrain getRandomTerrain(){
		Terrain[] terrain = Terrain.values();
		return(terrain[rand.nextInt(terrain.length)]);

	}

	
	/**
	 * Method get the specified terrain according to the text file.
	 * 
	 * @param i Int to represent the terrain.
	 * @return
	 */
	public static Terrain getTerrain(int i) {

		switch (i) {
			case '*': return Plain;
			case 's': return Desert;
			case 'w': return Water;
			case 'm': return Mountain;
			case 'p': return Swamp;
			case 'b': return Wall;
			case 'f': return Forest;
			default:
				return Plain;
		}
	//	return null;

	}
}
