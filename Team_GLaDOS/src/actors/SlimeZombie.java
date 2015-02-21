package actors;

import swag.RolledUpSock;
import gameBoard.FlatMap;

/**
 * @author amrsaad
 *
 */
public class SlimeZombie extends Monster{

	/**
	 * Fast Zombie is serializable
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the fast zombie.
	 * 
	 * @param spawnloc Spawn Location.
	 */
	public SlimeZombie(FlatMap spawnloc,int ID) {
		super(spawnloc);
		this.defense = 1;

		this.HP = 20;
		this.imageFilename = "./EnemyIcons/slimeZombie.png";
		
		this.level = 1;
		this.moveSpeed = 4;
		this.playerClass = "Monster";
		this._id = ID;
		this.currentWeapon = new RolledUpSock();
		this.strength = 20*this.level;
		this.experiencePointWorth = 2000*level;
		this.moolah = 100*this.level;
	}
}
