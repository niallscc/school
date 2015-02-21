package magic;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import gameBoard.FlatMap;
import gameBoard.Magic;


/**
 * This class holds all the information for a fire move, this fire move basically
 * allows a user to select a cell in the radius and does the attack on that cell 
 * @author niallschavez
 *
 */
public class Fire implements Magic, Serializable{

	
	private static final long serialVersionUID = 1L;
	@Override
	
	/**
	 * returns the potential area of effect of a fire attack 
	 */
	public ArrayList<FlatMap> getAttackArea() {

		ArrayList<FlatMap> areaOfEffect= new ArrayList<FlatMap>();
		
		/*
		 * vertical line
		 */
		//FlatMap(x,y)
		areaOfEffect.add(new FlatMap(0, -50));
		areaOfEffect.add(new FlatMap(0, -25));
		areaOfEffect.add(new FlatMap(0, 25));
		areaOfEffect.add(new FlatMap(0, 50));
		/*
		 * HorizontalLine
		 */
		areaOfEffect.add(new FlatMap(-50, 0));
		areaOfEffect.add(new FlatMap(-25, 0));
		areaOfEffect.add(new FlatMap(25, 0));
		areaOfEffect.add(new FlatMap(50, 0));
		/*
		 * Corners
		 */
		areaOfEffect.add(new FlatMap(-25, -25));
		areaOfEffect.add(new FlatMap(25, -25));
		areaOfEffect.add(new FlatMap(-25, 25));
		areaOfEffect.add(new FlatMap(25, 25));
		
		return areaOfEffect;
	}

	@Override
	public Image getImage() {
		Image fire = null;
		try {
			fire = (Image)ImageIO.read(new File("./Magic_Icons/lightFire.png")).getScaledInstance(32, 32, 0);
		} catch (IOException e) {
			System.err.println("Could not load in Fire Image");
			e.printStackTrace();
		}
		return fire;
	}
	/**
	 * returns whether or not the radius of effect is selectable or if it just affects a whole area. 
	 */
	@Override
	public boolean selectable() {
		
		return false;
	}
	/**
	 * returns the name of this magic 
	 */
	@Override
	public String getName() {
		
		return "Fire";
	}

	@Override
	public boolean rotateable() {
		return false;
	}

}
