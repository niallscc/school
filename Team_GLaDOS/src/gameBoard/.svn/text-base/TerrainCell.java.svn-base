package gameBoard;
import java.awt.Graphics;
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import swag.Swag;

import actors.Being;
import actors.NPC;



/**
 * 
 * The object used as a representation for each of the cells in our game. The object will hold
 * swag, Terrain type, A being, moolah, and portals. It has various methods to create the functionality
 * needed.
 * 
 * @author Gladdos
 *
 */
public class TerrainCell implements Serializable {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Enum type for terrain;
	 */
	private final Terrain terrain;
	/**
	 * Flatmap object to hold the location.
	 */
	private final FlatMap location;
	/**
	 * The being that is currently on this cell.
	 */
	private Being beingOnCell = null;
	/**
	 * A list of the swag on this cell.
	 */	
	private List<Swag> swagOnCell = new ArrayList<Swag>();
	/**
	 * A portal object for the cell, if it contains one
	 */
	private Portal portalOnCell = null;
	/**
	 * Store whether this cell is occupied or not.
	 */
	private boolean occupied;
	/**
	 * The Image object for an item.
	 */
	private transient Image itemsImage;
	/**
	 * The Image object for the terrain.
	 */
	private transient Image terrainImage;
	/**
	 * A holder for an NPC on this cell
	 */
	private NPC npcOnCell = null;
	/**
	 * The moolah on the cell.
	 */
	private int moolahOnCell = 0;

	/**
	 * Constructor for a terrain cell taking a terrain and a location to set the cell.
	 * 
	 * @param terrain Enum terrain.
	 * @param location FlatPoint location.
	 */
	public TerrainCell(Terrain terrain, FlatMap location) {
		this.terrain = terrain;
		setImages();
		this.location = location;
	}
	/**
	 * Setter for the moolah on the cell.
	 * @param The amount to set.
	 */
	public void setMoolah(int amount) {
		moolahOnCell = amount;
	}
	/**
	 * Method to return the Money value.
	 * 
	 * @return Amount of money.
	 */
	public int getMoolah() {
		int tempMoolah = moolahOnCell;
		moolahOnCell = 0;
		return tempMoolah;
	}

	/**
	 * Method to get the terrain.
	 * 
	 * @return Enum terrain.
	 */
	public Terrain getTerrain() {
		return terrain;
	}

	/**
	 * Method to get the terrain cost.
	 * 
	 * @return int Cost.
	 */
	public int getTerrainCost() {
		return terrain.getmovecostCost();
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
	 * Gives each TerrainCell object the ability to draw itself.
	 * @param g The Graphics instance
	 */
	public void draw(Graphics g) {

		if((itemsImage == null) || (terrainImage == null))
			setImages();
		
		if(beingOnCell != null) {
			g.drawImage(terrainImage, getCoordinates().getX()*25, getCoordinates().getY()*25, null);
			beingOnCell.draw(g);
		}
		else if(npcOnCell != null) {
			g.drawImage(terrainImage, getCoordinates().getX()*25, getCoordinates().getY()*25, null);
			npcOnCell.draw(g);	
		}
		//swag
		else if(swagOnCell.size() != 0) 
			g.drawImage(itemsImage, getCoordinates().getX()*25, getCoordinates().getY()*25, null);
		//Portal
		else if(portalOnCell != null) {
			g.drawImage(terrainImage, getCoordinates().getX()*25, getCoordinates().getY()*25, null);
			portalOnCell.draw(g);
		}
		else
			g.drawImage(terrainImage, getCoordinates().getX()*25, getCoordinates().getY()*25, null);
	}
	public void setImages() {
		String itemsFilename = Items.TreasureChest.getImageFileName();

		try {
			itemsImage = (Image)ImageIO.read(new File(itemsFilename));
			itemsImage= itemsImage.getScaledInstance(25, 25,0);

			terrainImage = (Image)ImageIO.read(new File(terrain.getImageFileName()));
			terrainImage= terrainImage.getScaledInstance(25,25,0);
	
		} catch (IOException e) {
			System.err.println("Could not load image at:  "+ terrain.getImageFileName());
		}
	}
	/**
	 * Setter for whether it's occupied or not.
	 * @param occupied The true or false value of occupation.
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	/**
	 * Getter for whether it's occupied or not.
	 * @return True or false for occupation state.
	 */
	public boolean getOccupied() {
		return occupied;
	}
	/**
	 * Returns whether there is an NPC or a being on the cell.
	 * @return True if it's occupied, false otherwise.
	 */
	public boolean isOccupied() {
		if((beingOnCell != null) || (npcOnCell != null)) 
			return true;
		else
			return false;
	}

	/**
	 * Setter for the being on this cell.
	 * @param The being to set.
	 */
	public void setBeing(Being cellBeing) {
		beingOnCell = cellBeing;
	}
	/**
	 * Getter for the being on this.
	 * @return The being
	 */
	public Being getBeing() {
		return beingOnCell;
	}
	/**
	 * Adds an item of swag to the cell.
	 * @param The swag item to add.
	 */
	public void addSwag(Swag item) {
		if(item != null)
			swagOnCell.add(item);
	}
	/**
	 * Sets a portal on this Cell
	 * @param The portal to set.
	 */
	public void setPortal(Portal portal) {
		this.portalOnCell = portal;
	}
	/**
	 * Getter for the portal on this cell
	 * @return The Portal
	 */
	public Portal getPortal() {
		if(portalOnCell == null) 
			return null;
		else
			return portalOnCell;
	}
	/**
	 * Setter for the NPC on the cell
	 * @param The NPC to set
	 */
	public void setNPC(NPC npc) {
		this.npcOnCell = npc;
	}
	/**
	 * Getter for the NPC on the cell.
	 * @return The NPC on the cell
	 */
	public NPC getNPC() {
		return this.npcOnCell;
	}
	/**
	 * Getter for the swag on this cell, removes it when it's called.
	 * @return The list of swag on the cell.
	 */
	public List<Swag> getSwag() {
		List<Swag> tempSwag = new ArrayList<Swag>();
		for(Swag i : swagOnCell) {
			tempSwag.add(i);
		}
		//Remove it
		swagOnCell = new ArrayList<Swag>();
		//return the tempSwag holder
		return tempSwag;
	}
}
