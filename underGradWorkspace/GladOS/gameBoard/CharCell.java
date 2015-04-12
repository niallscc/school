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


	private final Player player;
	private final FlatMap location;
	private Image graphic;

	public CharCell(Player player, FlatMap location) {
		this.player = player;
		BufferedImage b = null;
		
		try {
			b = ImageIO.read(new File(player.getImageFileName()));
		} catch (IOException e) {
			System.err.println("Could not load image at:  "+ player.getImageFileName());
			e.printStackTrace();
		}
		graphic= b.getScaledInstance(25, 25,0);
		
		
		this.location = location;
	}
	public Image getImage() {
	    return graphic;
	  }

	public Player getPlayer() {
		return player;
	}
	public FlatMap getCoordinates() {

		return location;

	}
}
