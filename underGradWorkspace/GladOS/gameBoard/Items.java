package gameBoard;
import java.util.Random;

/**
 * 
 */

/**
 * @author amrsaad
 *
 */
public enum Items {
	
	HealthPotion ( 25, 30, "./GladOS_Graphics/healthPotion.png"),
	ManaPotion ( 25, 30, "./GladOS_Graphics/mana.png"),
	Gold(25, 30, "./GladOS_Graphics/swag_panel.png"),
	TreasureChest( 25, 30, "./Glados_Graphics/treasure_chest_panel.png");
	
	private final int vendorcost;
	private final int value;
	private final String filename;
	private static final Random rand = new Random();

	Items(int moolah, int value, String filename){
		this.vendorcost = moolah;
		this.value = value;
		this.filename = filename;
	}

	
	public int getMoolah() {
		return vendorcost;
	}


	public int getbenValue() {
		return value;
	}

	public String getImageFileName() {
		return filename;
	}


	public static Items getRandomItems(){
		Items[] items = Items.values();
		return(items[rand.nextInt(items.length)]);

	}

	

	public static Items getItem(int i) {

		switch (i) {
			case 'h':	return HealthPotion;
			case 'm': 	return ManaPotion;
			case 't':	return TreasureChest;
			case 'g':   return Gold;
			case '*':   return null;
			
		}
		return null;

	}

}
