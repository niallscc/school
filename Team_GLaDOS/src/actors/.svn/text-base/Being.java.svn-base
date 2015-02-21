package actors;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import magic.Attack;

import magic.Defense;

import magic.FlameThrower;
import magic.GoodDrugs;
import magic.GreatDrugs;
import magic.Zorch;
import magic.Zot;

import cast.Cast;

import swag.Armor;
import swag.Swag;
import swag.Weapon;

import gameBoard.FlatMap;
import gameBoard.Magic;

/**
 * 
 * A representation of the Monster's and PC's in the Game. Both monster's and PC's will extend 
 * from this object type. It provides most of their shared functionality, and then the monster's
 * and PC's extend to add on the specific functionality that they need.
 * @author GLaDOS
 *
 */
public abstract class Being implements Lifeform, Serializable {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The id of the being.
	 */
	protected int _id;
	/**
	 * The HP Mult value
	 */
	protected int HPMult = 1;
	/**
	 * The MP mult value
	 */
	protected int MPMult = 1;
	/**
	 * The location of the player on the map.
	 */
	private FlatMap _location;
	/**
	 * The amount of defense the being has.
	 */
	protected int defense;
	/**
	 * The amount of HP that the being has
	 */
	protected int HP;
	/**
	 * The amount of MP a Being has
	 */
	protected int MP;
	/**
	 * The max MP
	 */
	protected int maxMP;
	/**
	 * The max HP
	 */
	protected int maxHP;
	/**
	 * The class of the being, set by each specific class.
	 */
	protected String playerClass;
	/**
	 * This being's Strength
	 */
	protected int strength;
	/**
	 * Their intelligence
	 */
	protected int intelligence;
	/**
	 * Their speed
	 */
	protected int speed;
	/**
	 * The image filename assocatied with the client.
	 */
	protected String imageFilename;
	/**
	 * The death Image file
	 */
	protected String deathFilename = "./PCIcons/tombstone.png";
	/**
	 * The level of the being
	 */
	protected int level = 1;
	/**
	 * The inventory of the being
	 */
	protected List<Swag> inventory = new ArrayList<Swag>();
	/**
	 * The encumberance value of the player.
	 */
	protected int encumberance;
	/**
	 * The current piece of armor that the player is using.
	 */
	protected Armor currentArmor;
	/**
	 * The current weapon the being is wielding.
	 */
	protected Weapon currentWeapon;

	/**
	 * The amount of experience points a player has.
	 */
	protected double experiencePoints = 0;
	/**
	 * The amount of experience points they are worth.
	 */
	protected int experiencePointWorth = 0;
	/**
	 * The name of the being
	 */
	protected String beingName;
	/**
	 * Flags whether they are in a defense state or not.
	 */
	protected boolean isDefending = false;
	/**
	 * Flags whether this being is a PC or not... I know, this is bad BAD design, but it is Thursday at 9:40 PM and we only have
	 * about 3.5 days left until this sucker has to be turned in.
	 */
	protected boolean isPC = false;
	/**
	 * A list of Spells that the player can cast
	 */
	protected List<Cast> spells = new ArrayList<Cast>();
	/**
	 * This is the arraylist of magic attacks that the being has selected
	 */
	protected ArrayList<Magic> magicList= new ArrayList<Magic>();
	/**
	 * The amount of moolah a player has
	 */
	protected int moolah = 1000;
	/**
	 * The gender of the being.
	 */
	protected String gender;
	
	/**
	 * Constructor giving the spawn location of any being.
	 * 
	 * @param spawnloc Spawnlocation.
	 */
	public Being(FlatMap spawnloc)
	{
		_location = spawnloc;
		gender = "Male";
		
		//Give them the attacks.
		magicList.add(new Attack());
		magicList.add(new Defense());
		
		
		magicList.add(new Zot());
		magicList.add(new GoodDrugs());
		magicList.add(new GreatDrugs());
		magicList.add(new Zorch());
		magicList.add(new FlameThrower());

	}
	/**
	 * Set the ID of any given being.
	 * @param id BeingID
	 */
	public void setID(int id) {
		_id = id;
	}

	/**
	 * Get the ID of any given being.
	 * @return BeingID
	 */
	public int getID() {
		return _id;
	}

	/**
	 * Gives each Being object the ability to draw itself.
	 * @param g The Graphics instance
	 */
	public void draw(Graphics g) {

		if(isAlive()) {
			BufferedImage characterIMG = null;
			try {
				characterIMG = ImageIO.read(new File(imageFilename));
				//characterIMG = ImageIO.read(new File("./UserSaveFiles/playerPic.jpg"));
			} catch (IOException e) {
				System.err.println("Unable to load being image.");
			}
			Image beingImage=characterIMG.getScaledInstance(25, 25, 0);
			g.drawImage(beingImage, this.getLocation().getX()*25, this.getLocation().getY()*25, null);
		}
		else {
			BufferedImage characterIMG = null;
			try {
				characterIMG = ImageIO.read(new File(deathFilename));
				//characterIMG = ImageIO.read(new File("./UserSaveFiles/playerPic.jpg"));
			} catch (IOException e) {
				System.err.println("Unable to load being image.");
			}
			Image beingImage=(Image)characterIMG;
			g.drawImage(beingImage, this.getLocation().getX()*25, this.getLocation().getY()*25, null);
		}
	}
	/**
	 * Gets the location of the being.
	 * 
	 * @return the location of the being.
	 */
	public FlatMap getLocation() {
		return _location;
	}

	/**
	 * Set the location of the being.
	 * @param location Location of being.
	 */
	public void setLocation(FlatMap location) {
		_location = location;
	}

	/**
	 * Get the defense of the being.
	 * @return Defense Rating.
	 */
	public int getDefenseValue() {
		int defenseMultiplier = 1;
		
		//First find out if they are in defense command or not..
		if(isDefending())
			defenseMultiplier = 2;

		//If they have armor...
		if(this.getEquipedArmor() != null)
			return defenseMultiplier*(this.getEquipedArmor().getArmorClass() + this.getSpeed() + (5*this.getLevel()));
		//Otherwise they are flat outta luck
		else
			return defenseMultiplier*(this.getSpeed() + (5*this.getLevel()));

	}

	/**
	 * Get the HP of the being.
	 * @return Current HP.
	 */
	public int getHP() {
		return HP;

	}
	/**
	 * Get the HP of the being.
	 * @return Current HP.
	 */
	public int getMaxHP() {
		return maxHP;

	}
	/**
	 * Get the HP of the being.
	 * @return Current HP.
	 */
	public int getMP() {
		return MP;

	}
	/**
	 * Get the HP of the being.
	 * @return Current HP.
	 */
	public int getMaxMP() {
		return maxMP;

	}
	/**
	 * Does damage to the Being
	 * @param The amount of damage to do
	 */
	public void inflictDamage(int amount) {
		HP -= amount;
	}
	/**
	 * Get the X value of the location
	 * 
	 * @return X value of the location.
	 */
	public int getXloc(){
		return _location.getX();
	}

	/**
	 * Get the Y value of the location.
	 * 
	 * @return Y value of the location.
	 */
	public int getYloc(){
		return _location.getY();

	}
	/**
	 * Getter for the speed
	 * @return The being's speed.
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * Getter for the strength.
	 * @return The strength of this being
	 */
	public int getStrength() { 
		return this.strength;
	}
	public int getLevel() {
		return level;
	}
	/**
	 * Getter for the intelligence.
	 * @return The intelligence of the being
	 */
	public int getIntelligence() {
		return this.intelligence;
	}

	/**
	 * Getter for the encumberance of the player. Loops through and calculates the encumberance of all of the swag
	 * @return The encumerance of the player.
	 */
	public int getEncumberance() {
		encumberance = 0;
		for(Swag t : inventory) {
			this.encumberance += t.getEncumberance();
		}
		return encumberance;
	}

	/**
	 * Setter for the armor of the PC
	 * @param The armor to equip them with.
	 */
	public void equipArmor(Armor armor) {
		this.currentArmor = armor;
	}
	/**
	 * Getter for the armor.
	 * @return the armor that the PC is currently wearing.
	 */
	public Armor getEquipedArmor() {
		return this.currentArmor;
	}
	/**
	 * Setter for the armor of the PC
	 * @param The armor to equip them with.
	 */
	public void equipWeapon(Weapon weapon) {
		this.currentWeapon = weapon;
	}
	/**
	 * Getter for the armor.
	 * @return the armor that the PC is currently wearing.
	 */
	public Weapon getEquipedWeapon() {
		return this.currentWeapon;
	}
	/**
	 * Gets the attack value for this being.
	 * @return The attack value
	 */
	public int getAttackValue() { 
		return (int)Math.floor(this.getEquipedWeapon().getDamage() + (this.getStrength() * this.getEquipedWeapon().getStrengthMultiplier()));
	}
	/**
	 * Getter for the Player class
	 * @return The player class.
	 */
	public String getPlayerClass() {
		return playerClass;
	}
	/**
	 * Getter for the life status of this being
	 * @return Whether he is alive or not.
	 */
	public boolean isAlive() {
		if(HP <= 0)
			return false;
		else
			return true;
	}
	/**
	 * Getter for the name.
	 * @return the name of the being
	 */
	public String getName() {
		return beingName;
	}
	/**
	 * Getter for isPC
	 * @return True or false if PC
	 */
	public boolean isPC() {
		return isPC;
	}
	/**
	 * Getter for the experience point worth of this being
	 * @return The amount of experience points they are worth
	 */
	public int getExperiencePointWorth() {
		return experiencePointWorth;
	}
	/**
	 * Getter for the amount of experience points they have.
	 * @return the amount of experience points they currently have.
	 */
	public double getExperiencePoints() {
		return this.experiencePoints;
	}

	/**
	 * Getter for whether this being is defending or not
	 * @return If they are defending or not.
	 */
	public boolean isDefending() {
		return isDefending;
	}
	/**
	 * Setter for defending or not.
	 * @param To defend or not
	 */
	public void setDefending(boolean status) {
		System.out.print(this.getName() + "'s Defending status changed to " + status + " Their old defense: " + this.getDefenseValue());
		isDefending = status;
		System.out.println(" Their new value: " + this.getDefenseValue());
	}
	/**
	 * Getter for the inventory of the being
	 * @return Their loot
	 */
	public List<Swag> getInventory() {
		return this.inventory;
	}
	/**
	 * Adds an item to the being's inventory
	 * @param The swag item to add.
	 */
	public void addSwagItem(Swag item) {
		this.inventory.add(item);
	}
	/**
	 * Removes an item of swag from the inventory.
	 * @param The swag item to remove.
	 */
	public void removeSwagItem(int itemIndex) {
		this.inventory.remove(itemIndex);
	}

	/**
	 * this is called when the player levels up and the new magic attack gets added
	 * into the arraylist 
	 * @param e
	 */
	public void addMagic(Magic e){
		magicList.add(e);
	}
	/**
	 * gets the list of magic attacks that the user has access to 
	 * @return
	 */
	public List<Magic>getMagic(){
		return magicList;
	}
	
	/**
	 * Adds to the HP the specified amount
	 * @param The amount to heal the player
	 */
	public void heal(int amount) {
		this.HP += amount;
		//Make sure it doesn't push them over their max.
		if(this.HP > this.maxHP)
			this.HP = maxHP;
	}
	/**
	 * Getter for the spells
	 * @return The List of spells a being has access to
	 */
	public List<Cast> getSpells() {
		return spells;
	}
	/**
	 * Adds a spell to the list of spells a being can cast.
	 * @param The spell to add
	 */
	public void addSpell(Cast spell) {
		spells.add(spell);
	}
	
	/**
	 * Set the death image.
	 */
	public void setDeath(){
		this.imageFilename = deathFilename;
	}
	/**
	 * Getter for the moolah
	 * @return The amount of moolah a player has
	 */
	public int getMoolah() {
		return this.moolah;
	}
	/**
	 * Decreases the amount of moolah a player has
	 * @param The amount to decrease it by
	 */
	public void decreaseMoolah(double amount) {
		moolah -= amount;
	}
	/**
	 * Increases the amount of moolah a player has.
	 * @param The amount to increase it by.
	 */
	public void increaseMoolah(double amount) {
		moolah += amount;
	}
		/**
	 * Set the level for the monsters.
	 * @param level Level of the monsters.
	 */
	public void setLevel(int level){
		this.level = level;
		this.strength = this.strength*level;
		this.speed = this.speed*level;
		this.defense = this.defense*level;
	}
	/**
	 * Regenerates the beings HP and MP.
	 */
	public void regenerate() {
		int hpIncrease = this.HPMult * this.level;
		int mpIncrease = this.MPMult*this.level;
		
		if((this.HP + hpIncrease) > this.maxHP)
			this.HP = maxHP;
		else
			HP += hpIncrease;
		
		if((this.MP + mpIncrease) > this.maxMP)
			this.MP = maxMP;
		else
			MP += mpIncrease;
	}
	/**
	 * Getter for the gender.
	 * @return The gender type of the being.
	 */
	public String getGender() {
		return this.gender;
	}
	
	/**
	 * Set the MP of the being.
	 */
	public void setMP(int mp){
		this.MP = mp;
	}
}

