package swag;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public abstract class Armor implements Swag, Serializable {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The encumberance of this item.
	 */
	protected int encumberanceAmount;
	
	/**
	 * The amount of defense the item has.
	 */
	protected int defense;
	
	/**
	 * The cost of this item.
	 */
	protected int cost;
	
	/**
	 * The vendor cost for this item
	 */
	protected double vendorcost;
	
	/**
	 * The buy cost for this item
	 */
	protected double buycost;
	
	/**
	 * The Durability of this item
	 */
	protected int durability;
	
	/**
	 * The level requirement of this item.
	 */
	protected int levelreq;
	/**
	 * The Armor class of this item.
	 */
	protected int armorClass;
	/**
	 * The item name
	 */
	protected String itemName;
	/**
	 * The image filename
	 */
	protected String imageFile;
	/**
	 * Flags if a swag item is a weapon
	 */
	protected boolean isWeapon = false;
	/**
	 * Flags if the swag item is armor
	 */
	protected boolean isArmor = true;

	
	public int getEncumberance() {
		return encumberanceAmount;
	}
	
	public int getArmorClass() {
		return armorClass;
	}
	
	public String getName() {
		return itemName;
	}
	public Image getImage() {
		Image itemImage = null;
		try {
			itemImage = (Image)ImageIO.read(new File(imageFile));
		} catch (IOException e) {
			System.err.println("Unable to load item image. -" + imageFile);
		}
		
		return itemImage;
	}
	public double getVendorCost() {
		return this.buycost;
	}
	public double getVendorWorth() {
		return this.vendorcost;
	}
	/**
	 * Getter for weapon flag
	 * @return true or false if it's a weapon
	 */
	public boolean isWeapon() {
		return this.isWeapon;
	}
	/**
	 * Getter for the armor flag
	 * @return true or false if its armor.
	 */
	public boolean isArmor() {
		return this.isArmor;
	}
}
