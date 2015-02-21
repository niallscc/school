package swag;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public abstract class Weapon implements Swag, Serializable {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The minimum damage of this item.
	 */
	protected int minDamage;
	/**
	 * The max damage of this item
	 */
	protected int maxDamage;
	/**
	 * The encumberance of this item.
	 */
	protected int encumberanceAmount;
	/**
	 * The strMultiplier of this item
	 */
	protected double strMultiplier;
	/**
	 * The cost for this item
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
	protected boolean isWeapon = true;
	/**
	 * Flags if the swag item is armor
	 */
	protected boolean isArmor = false;
	
	
	@Override
	public int getEncumberance() {
		return encumberanceAmount;
	}
	/**
	 * Returns a random value between the damage values.
	 * @return A random damage value.
	 */
	public int getDamage() {
		int damage = (int)(maxDamage * Math.random()) + minDamage;
		return  damage;
	}
	/**
	 * Returns the Strength multiplier for this weapon.
	 * @return The strength multiplier
	 */
	public double getStrengthMultiplier() {
		return this.strMultiplier;
	}
	public String getName() {
		return itemName;
	}
	public Image getImage() {
		Image itemImage = null;
		try {
			itemImage = (Image)ImageIO.read(new File(imageFile));
		} catch (IOException e) {
			System.err.println("Unable to load item image." + imageFile);
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
