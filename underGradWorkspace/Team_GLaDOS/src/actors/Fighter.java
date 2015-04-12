package actors;

import swag.RustyDagger;
import swag.Weapon;
import gameBoard.FlatMap;

/**
 * The Fighter class that a PC can select. Has the following initial stats:
 * Speed = 10;
 * Strength = 20;
 * Intelligence = 5
 * HP = 20
 * MP = 0
 * 
 * @author Ryan Hammer
 *
 */
public class Fighter extends PC {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a Fighter PC type with the given spawn location, name, and gender
	 * @param spawnloc	The location to spawn the PC
	 * @param name	The name of the fighter
	 * @param gender The gender of the fighter
	 */
	public Fighter(FlatMap spawnloc, String name, String gender) {
		super(spawnloc, name, gender);
		this.playerClass = "Knight";
		
		this.speed = 10;
		this.strength = 20;
		this.intelligence = 5;
		
		this.HP = 20;
		this.MP = 0;
	
		this.maxHP = 20;
		
		this.maxMP = 0;
		this.HPMult = 3;
		this.MPMult = 1;
		this.level = 1; // For testing changed back to 1

		this.levelingFactor = 1.55;
		this.spellRate = 5;

		this.minHPIncrease = 10;
		this.maxHPIncrease = 15; 
		this.minMPIncrease = 1;
		this.maxMPIncrease = 5;
		this.minSpdIncrease = 2;
		this.maxSpdIncrease = 5;
		this.minStrIncrease = 3;
		this.maxStrIncrease = 8;
		this.minIntIncrease = 1;
		this.maxIntIncrease =3;

		imageFilename = "./PCIcons/knight.png";
		Weapon rustyDagger = new RustyDagger();

		//Give the player basic items
		inventory.add(rustyDagger);
		this.equipWeapon(rustyDagger);

	}
	
	/**
	 * for saving this sets the new level of the character
	 */
	public void setLevel(int newLevel){
		this.level=newLevel;
	}

}
