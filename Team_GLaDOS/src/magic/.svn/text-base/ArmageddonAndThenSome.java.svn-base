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
 * this class holds all the information for the magic attack: 
 * Armageddon And Then Some: this magic attack effects an area around the player 
 * with a heavy fire attack  
 * @author niallschavez
 *
 */
public class ArmageddonAndThenSome implements Magic, Serializable {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<FlatMap> getAttackArea() {
		ArrayList<FlatMap> areaOfEffect= new ArrayList<FlatMap>();
		
		/*
		 * vertical line
		 */
		//FlatMap(x,y)
		areaOfEffect.add(new FlatMap(0, -75));
		areaOfEffect.add(new FlatMap(0, -50));
		areaOfEffect.add(new FlatMap(0, -25));
		areaOfEffect.add(new FlatMap(0, 25));
		areaOfEffect.add(new FlatMap(0, 50));
		areaOfEffect.add(new FlatMap(0, 75));
		/*
		 * HorizontalLine
		 */
		areaOfEffect.add(new FlatMap(-75, 0));
		areaOfEffect.add(new FlatMap(-50, 0));
		areaOfEffect.add(new FlatMap(-25, 0));
		areaOfEffect.add(new FlatMap(25, 0));
		areaOfEffect.add(new FlatMap(50, 0));
		areaOfEffect.add(new FlatMap(75, 0));
		
		/*
		 * Corners
		 */
		areaOfEffect.add(new FlatMap(-50, -25));
		
		areaOfEffect.add(new FlatMap(25, -50));
		areaOfEffect.add(new FlatMap(50, -25));
		areaOfEffect.add(new FlatMap(50, 25));
		areaOfEffect.add(new FlatMap(25, 50));
		areaOfEffect.add(new FlatMap(-25,50 ));
		areaOfEffect.add(new FlatMap(-50, 25));
		
		areaOfEffect.add(new FlatMap(-25, -50));
		areaOfEffect.add(new FlatMap(-25, -25));
		areaOfEffect.add(new FlatMap(25, -25));
		areaOfEffect.add(new FlatMap(-25, 25));
		areaOfEffect.add(new FlatMap(25, 25));
		return areaOfEffect;
	}

	@Override
	public Image getImage() {
		Image armageddon = null;
		try {
			armageddon = (Image)ImageIO.read(new File("./Magic_Icons/heavyFire.png")).getScaledInstance(32, 32, 0);
		} catch (IOException e) {
			System.err.println("Could not load in Armageddon Image");
			e.printStackTrace();
		}
		return armageddon;
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
		
		return "Armageddon ATS";
	}

	@Override
	public boolean rotateable() {
		
		return false;
	}

}
