package gameBoard;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import actors.Character;


/**
 * @author amrsaad
 *
 */
public class CharCell {

	
	/**
	 * boolean to store whether the cell is occupied.
	 */
	private boolean occupied;
	
	/**
	 * Enum type for character.
	 */
	private final Character c;
	/**
	 * Flatmap object to hold the location.
	 */
	private final FlatMap location;
	/**
	 * Image type object to hold the graphic.
	 */
	private Image graphic;

	/**
	 * Constructor for a character cell taking a character and a location to set the cell.
	 * 
	 * @param character Enum character.
	 * @param location FlatPoint location.
	 */
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

	
	/**
	 * Get the graphic of the cell.
	 * @return graphic.
	 */
	public Image getImage() {
	    return graphic;
	  }

	/**
	 * Method to get the character.
	 * 
	 * @return Enum character.
	 */
	public Character getCharacter() {
		return c;
	}
	
	/**
	 * Method to get the coordinates of the cell.
	 * 
	 * @return Cell coordinates.
	 */
	public FlatMap getCoordinates() {

		return location;

	}
	
	/**
	 * Set the occupied state of the cell.
	 * 
	 * @param b boolean for if its occupied.
	 */
	public void setOccupied(boolean b){
		occupied = b;
		
	}
	

	/**
	 * Check to see if a cell is occupied.
	 * @return
	 */
	public boolean isOccupied() {
		// TODO Auto-generated method stub
		return occupied;
	}


}
