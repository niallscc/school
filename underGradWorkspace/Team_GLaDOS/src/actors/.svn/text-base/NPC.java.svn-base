package actors;

import gameBoard.FlatMap;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import swag.Bludgeon;
import swag.ButterKnife;
import swag.ChainMailBikini;
import swag.CoatofPlates;
import swag.Excalibur;
import swag.LeadToothbrush;
import swag.LeatherArmor;
import swag.Mace;
import swag.Misericorde;
import swag.PipeCleanerOfDoom;
import swag.PlateMail;
import swag.RingMail;
import swag.RolledUpSock;
import swag.RustyDagger;
import swag.ScaleMail;
import swag.ShortSword;
import swag.SnailMail;
import swag.Swag;
import swag.WetTowel;
import swag.Zweihander;

/**
 * A class for NPC functionality. They are different from Beings.
 * @author Ryan
 *
 */
public class NPC implements Lifeform, Serializable {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The inventory of the NPC
	 */
	private List<Swag> inventory = new ArrayList<Swag>();
	/**
	 * The image file associated with the NPC
	 */
	private String imageFilename = "./NPCIcons/villager_1_2.png";
	/**
	 * Their location
	 */
	private FlatMap location;
	
	/**
	 * Creates an NPC at the given FlatMap location.
	 * @param loc The location to create them at.
	 */
	public NPC(FlatMap loc) {
		//Add in all of the SWAG.
		inventory.add(new ButterKnife());
		inventory.add(new Bludgeon());
		inventory.add(new ChainMailBikini());
		inventory.add(new CoatofPlates());
		inventory.add(new Excalibur());
		inventory.add(new LeadToothbrush());
		inventory.add(new LeatherArmor());
		inventory.add(new Mace());
		inventory.add(new Misericorde());
		inventory.add(new PipeCleanerOfDoom());
		inventory.add(new PlateMail());
		inventory.add(new RingMail());
		inventory.add(new RolledUpSock());
		inventory.add(new RustyDagger());
		inventory.add(new ScaleMail());
		inventory.add(new ShortSword());
		inventory.add(new SnailMail());
		inventory.add(new WetTowel());
		inventory.add(new Zweihander());
		location = loc;
	}
	/**
	 * Gives the ability to draw itself.
	 * @param g The graphics instance to draw itself on.
	 */
	public void draw(Graphics g) {
		Image npc = null;
		try {
			npc = (Image)ImageIO.read(new File(imageFilename));
		} catch (IOException e) {
			System.err.println("Unable to load being image.");
		}
		g.drawImage(npc, this.location.getX()*25, this.location.getY()*25, null);
	}	
	/**
	 * Getter for the inventory of the being
	 * @return Their loot
	 */
	public List<Swag> getInventory() {
		return this.inventory;
	}

}
