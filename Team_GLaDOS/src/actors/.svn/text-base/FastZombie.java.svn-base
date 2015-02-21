package actors;

import swag.RolledUpSock;
import gameBoard.FlatMap;

/**
 * @author amrsaad
 *
 */
public class FastZombie extends Monster{

	/**
	 * Fast Zombie is serializable
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the fast zombie.
	 * Sets the Monster Stats.
	 * 
	 * @param spawnloc Spawn Location.
	 */
	public FastZombie(FlatMap spawnloc,int ID) {
		super(spawnloc);
		this.defense = 1;

		this.HP = 25;
		this.imageFilename = "./EnemyIcons/normalZombie2.png";
		
		this.level = 1;
		this.moveSpeed = 4;
		this.playerClass = "Monster";
		this._id = ID;
		this.currentWeapon = new RolledUpSock();
		this.strength = 20*this.level;
		this.experiencePointWorth = 10000;
		this.moolah = 500*this.level;

	}



}
