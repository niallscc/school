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

	private final Items items;
	private final FlatMap location;
	private Image graphic;

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
	public Image getImage() {
	    return graphic;
	  }

	public Items getItems() {
		return items;
	}
	public FlatMap getCoordinates() {

		return location;

	}

}
