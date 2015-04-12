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
public class TerrainCell {

	private final Terrain terrain;
	private final FlatMap location;
	private Image graphic;

	public TerrainCell(Terrain terrain, FlatMap location) {
		this.terrain = terrain;
		BufferedImage b = null;
		
		try {
			
			b = ImageIO.read(new File(terrain.getImageFileName()));
			//System.out.println("B in Terrain Cell "+ b);
		} catch (IOException e) {
			System.err.println("Could not load image at:  "+ terrain.getImageFileName());
			e.printStackTrace();
		}
		graphic= b.getScaledInstance(25, 25,0);
		
		
		this.location = location;
	}
	public Image getImage() {
	    return graphic;
	  }

	public Terrain getTerrain() {
		return terrain;
	}
	public FlatMap getCoordinates() {

		return location;

	}
}
