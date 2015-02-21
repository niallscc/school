package gameBoard;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



/**
 * @author amrsaad
 *
 */
public class ItemsCell {

	/**
	 * Enum type for items;
	 */
	private final Items items;
	/**
	 * Flatmap object to hold the location.
	 */
	private final FlatMap location;
	/**
	 * Image type object to hold the graphic.
	 */
	private Image graphic;

	/**
	 * Constructor for an items cell taking an item and a location to set the cell.
	 * 
	 * @param items Enum item.
	 * @param location FlatPoint location.
	 */
	public ItemsCell(Items items, FlatMap location) {
		this.items = items;
		BufferedImage b = null;
		
		try {
			b = ImageIO.read(new File(items.getImageFileName()));
		} catch (IOException e) {
			System.err.println("Could not load image at:  "+ items.getImageFileName());
			e.printStackTrace();
		}
		graphic= b.getScaledInstance(25, 25,0);
		
		
		this.location = location;
	}
	/**
	 * Get the graphic of the cell.
	 * @return graphic.
	 */
	public Image getImage() {
	    return graphic;
	  }
	
	/**
	 * Method to get the items.
	 * 
	 * @return Enum items.
	 */
	public Items getItems() {
		return items;
	}
	
	/**
	 * Method to get the coordinates of the cell.
	 * 
	 * @return Cell coordinates.
	 */
	public FlatMap getCoordinates() {

		return location;

	}

}
