package actors;

import swag.LeadToothbrush;
import swag.Weapon;
import gameBoard.FlatMap;

/**
 * The Thief PC type. It has the following stats:
 * Speed: 15
 * Strength: 5
 * Intelligence: 8
 * 
 * @author Ryan
 *
 */
public class Thief extends PC {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a Thief PC type at the given location and name and gender.
	 * @param spawnloc The location to spawn them at.
	 * @param name The name of the Thief
	 * @param gender The gender of the Thief.
	 */
	public Thief(FlatMap spawnloc, String name, String gender) {
		super(spawnloc, name, gender);
		this.playerClass = "Serf";
		this.speed = 15;
		this.strength = 5;
		this.intelligence = 8;
		this.HP = 15;
		this.MP = 10;
		this.maxHP = 15;
		this.maxMP = 10;
		

		this.level = 1;
		this.HPMult = 2;
		this.MPMult = 2;

		this.levelingFactor = 1.45;
		this.spellRate = 3;

		this.minHPIncrease = 8;
		this.maxHPIncrease =12; 
		this.minMPIncrease = 5;
		this.maxMPIncrease = 10;
		this.minSpdIncrease = 3;
		this.maxSpdIncrease = 8;
		this.minStrIncrease = 2;
		this.maxStrIncrease = 5;
		this.minIntIncrease = 2;
		this.maxIntIncrease = 5;

		imageFilename = "./PCIcons/serf.png";
		Weapon leadToothbrush = new LeadToothbrush();

		//Give the player basic items
		inventory.add(leadToothbrush);
		this.equipWeapon(leadToothbrush);
	}
	/**
	 * for saving this sets the new level of the character
	 */
	public void setLevel(int newLevel){
		this.level=newLevel;
	}
}
