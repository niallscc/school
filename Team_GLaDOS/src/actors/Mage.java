package actors;

import swag.RolledUpSock;
import swag.Weapon;
import gameBoard.FlatMap;

/**
 * The Mage PC type class. They have the initial stats:
 * Strength: 5
 * Speed: 8
 * Intelligence: 20
 * 
 * @author Ryan Hammer
 *
 */
public class Mage extends PC {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a Mage PC type with the given spawn location, name, and gender
	 * @param spawnloc	The location to spawn the PC
	 * @param name	The name of the Mage
	 * @param gender The gender of the Mage
	 */
	public Mage(FlatMap spawnloc, String name, String gender) {
		super(spawnloc, name, gender);
		this.playerClass = "Priest";
		
		this.level=1;
		
		this.speed = 8;
		this.strength = 5;
		this.intelligence = 20;
		this.HP = 8;
		this.MP = 20;
		this.maxHP = 8;
		this.maxMP = 20;
		
		this.HPMult = 1;
		this.MPMult = 3;
		
		this.levelingFactor = 1.5;
		this.spellRate = 1;
		
		this.minHPIncrease = 6;
		this.maxHPIncrease = 10;
		this.minMPIncrease = 10;
		this.maxMPIncrease = 15;
		this.minSpdIncrease = 2;
		this.maxSpdIncrease = 6;
		this.minStrIncrease = 1;
		this.maxStrIncrease = 3;
		this.minIntIncrease = 3;
		this.maxIntIncrease =8;
		
		imageFilename = "./PCIcons/magician.png";
		Weapon rolledUpSock = new RolledUpSock();
		
		//Give the player basic items
		inventory.add(rolledUpSock);
		this.equipWeapon(rolledUpSock);
	}
	/**
	 * for saving this sets the new level of the character
	 */
	public void setLevel(int newLevel){
		this.level=newLevel;
	}
	

}
