package actors;

import java.io.Serializable;

import gameBoard.FlatMap;

/**
 * A representation of a human player for the game. It keeps track of various information
 * related to the player.
 * 
 * @author Ryan Hammer
 *
 */
public class PC extends Being implements Serializable {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The spellRate
	 */
	protected int spellRate;
	/**
	 *	The leveling factor
	 */
	protected double levelingFactor;	
	/**
	 * The minimum increase for hp
	 */
	protected int minHPIncrease;
	/**
	 * The minimum increase for mp
	 */
	protected int minMPIncrease;
	/**
	 * The minimum increase for spd
	 */
	protected int minSpdIncrease;
	/**
	 * The minimum increase for str
	 */
	protected int minStrIncrease;
	/**
	 * The minimum increase for int
	 */
	protected int minIntIncrease;
	/**
	 * The maximum increase for hp
	 */
	protected int maxHPIncrease;
	/**
	 * The maximum increase for mp
	 */
	protected int maxMPIncrease;
	/**
	 * The maximum increase for spd
	 */
	protected int maxSpdIncrease;
	/**
	 * The maximum increase for str
	 */
	protected int maxStrIncrease;
	/**
	 * The maximum increase for int
	 */
	protected int maxIntIncrease;

	/**
	 * Creates an object of type PC at the given location and with the name and gender
	 * @param spawnloc The location to spawn
	 * @param name The name of the being
	 * @param gender The gender of the being.
	 */
	public PC(FlatMap spawnloc, String name, String gender) {
		super(spawnloc);
		encumberance = 0;
		isPC = true;
		
		this.beingName = name;
		this.gender = gender;
		
		experiencePointWorth = 50000;
	}
	
	/**
	 * Levels up the player, updates all stats.
	 */
	private void levelUp() {
		this.level++;
		
		//Note, each class has specific requirements for ranges of updating stats.
		//All of these values are set in the seperate player classes and follow the spec
		this.maxHP += (int)(maxHPIncrease * Math.random()) + minHPIncrease;
		this.maxMP += (int)(maxMPIncrease * Math.random()) + minMPIncrease;
		this.MP = maxMP;
		this.HP = maxHP;
		this.speed += (int)(maxSpdIncrease * Math.random()) + minSpdIncrease;
		this.strength += (int)(maxStrIncrease * Math.random()) + minStrIncrease;
		this.intelligence += (int)(maxIntIncrease * Math.random()) + minIntIncrease;
	}
	/**
	 * Getter for the amount of experience points a player needs in order to level up.
	 * @return The amount of experience points that they need.
	 */
	public double getRequiredExperiencePoints() { 
		return 1000*(Math.pow(this.levelingFactor, level));
	}
	/**
	 * Increments the player's experience points the provided amount.
	 * @param expPoints The amount to add on to the player's experience points.
	 */
	public void increaseExperiencePoints(int expPoints) {
		this.experiencePoints += expPoints;
		if(experiencePoints > getRequiredExperiencePoints())
			levelUp();
	}
}


