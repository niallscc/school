package gameBoard;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Portal implements Serializable {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The map that this portal is on
	 */
	private int currentMap;
	/**
	 * The map that this portal connects to.
	 */
	private int nextMap;
	/**
	 * The flatmap location of the portal on the next map
	 */
	private FlatMap nextMapPortalLocation;
	/**
	 * The flatmap location of the portal on it's current map
	 */
	private FlatMap currentLocation;

	/**
	 * The image file for portals
	 */
	private String portalFile = "./ItemsIcons/portal.png";


	public FlatMap getCurrentLocation() {
		return currentLocation;
	}


	public Portal(int curMap, int nMap, FlatMap currLocation, FlatMap nLocation) {
		currentMap = curMap;
		nextMap = nMap;
		currentLocation = currLocation;
		nextMapPortalLocation = nLocation;
	}


	public int getCurrentMap() {
		return currentMap;
	}


	public int getNextMap() {
		return nextMap;
	}


	public FlatMap getNextMapPortalLocation() {
		return nextMapPortalLocation;
	}

	/**
	 * Gives each the portal object the ability to draw itself
	 * @param g The Graphics instance
	 */
	public void draw(Graphics g) {
		BufferedImage characterIMG = null;
		try {
			characterIMG = ImageIO.read(new File(portalFile));
			//characterIMG = ImageIO.read(new File("./UserSaveFiles/playerPic.jpg"));
		} catch (IOException e) {
			System.err.println("Unable to load being image.");
		}
		Image beingImage=characterIMG.getScaledInstance(25, 25, 0);
		g.drawImage(beingImage, this.getCurrentLocation().getX()*25, this.getCurrentLocation().getY()*25, null);
	}


}
