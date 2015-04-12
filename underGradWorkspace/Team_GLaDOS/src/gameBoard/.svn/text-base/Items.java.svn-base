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
	
	/**
	 * Initializing the enum values.
	 */
	HealthPotion ( 25, 30, "./ItemsIcons/healthPotion.png"),
	ManaPotion ( 25, 30, "./ItemsIcons/mana.png"),
	Gold(25, 30, "./ItemsIcons/moneyBag.png"),
	TreasureChest( 25, 30, "./ItemsIcons/treasure_chest_panel.png"),
	Portal(0,0,"./ItemsIcons/portal.png");
	/**
	 * Integer holding the vendor cost.
	 */
	private final int vendorcost;
	
	/**
	 * The beneficial value of the item.
	 */
	private final int value;
	/**
	 * Private final string to hold the file name of the graphics.
	 */
	private final String filename;
	/**
	 * Randomly generated number to generate the item.
	 */
	private static final Random rand = new Random();

	/**
	 * Enum constructor to initialize the integer values. 
	 * 
	 * @param moolah Money variable.
	 * @param movecost Cost to move to the cell.
	 * @param filename Name of the file to be opened.
	 */
	Items(int moolah, int value, String filename){
		this.vendorcost = moolah;
		this.value = value;
		this.filename = filename;
	}

	/**
	 * Method to return the Money value.
	 * 
	 * @return Amount of money.
	 */
	public int getMoolah() {
		return vendorcost;
	}

	/**
	 * Method to return the beneficial value.
	 * 
	 * @return The amount this item will help.
	 */
	public int getbenValue() {
		return value;
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
	 * Method to get a Random item to put into a cell.
	 * 
	 * @return A random item
	 */
	public static Items getRandomItems(){
		Items[] items = Items.values();
		return(items[rand.nextInt(items.length)]);

	}

	
	/**
	 * Method get the specified item according to the text file.
	 * 
	 * @param i Int to represent the item.
	 * @return
	 */
	public static Items getItem(int i) {

		switch (i) {
			case 'h':	return HealthPotion;
			case 'm': 	return ManaPotion;
			case 't':	return TreasureChest;
			case 'g':   return Gold;
			case 'p':   return Portal;
			case '*':   return null;
			case '0':   return null;
			
		}
		return null;

	}

}
