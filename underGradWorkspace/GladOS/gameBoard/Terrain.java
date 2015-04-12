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
	
	Plain ( 5, 10, "./GladOS_Graphics/grass_pane.png"),
	Desert ( 25, 10, "./GladOS_Graphics/sand_pane.png"),
	Water ( 30, 30, "./GladOS_Graphics/water_pane.png"),
	Mountain ( 40, 100, "./GladOS_Graphics/mountain_pane.png"),
	Swamp ( 0, 60, "./GladOS_Graphics/swamp_pane.png");

	private final int moolah;
	private final int movecost;
	private final String filename;
	private static final Random rand = new Random();

	Terrain(int moolah, int movecost, String filename){
		this.moolah = moolah;
		this.movecost = movecost;
		this.filename = filename;
	}

	
	public int getMoolah() {
		return moolah;
	}


	public int getmovecostCost() {
		return movecost;
	}

	public String getImageFileName() {
		return filename;
	}


	public static Terrain getRandomTerrain(){
		Terrain[] terrain = Terrain.values();
		return(terrain[rand.nextInt(terrain.length)]);

	}

	

	public static Terrain getTerrain(int i) {

		switch (i) {
			case '*': return Plain;
			case 's': return Desert;
			case 'w': return Water;
			case 'm': return Mountain;
			case 'p': return Swamp;

		}
		return null;

	}
}
