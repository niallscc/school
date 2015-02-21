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
public class CharCell {


	private final Character c;
	private final FlatMap location;
	private Image graphic;

	public CharCell(Character cha, FlatMap location) {
		this.c = cha;
		BufferedImage b = null;
		
		try {
			b = ImageIO.read(new File(c.getImageFileName()));
		} catch (IOException e) {
			System.err.println("Could not load image at:  "+ c.getImageFileName());
			e.printStackTrace();
		}
		graphic= b.getScaledInstance(25, 25,0);
		
		
		this.location = location;
	}
	public Image getImage() {
	    return graphic;
	  }

	public Character getCharacter() {
		return c;
	}
	public FlatMap getCoordinates() {

		return location;

	}
}
