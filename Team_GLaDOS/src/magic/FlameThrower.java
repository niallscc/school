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
 * This class holds all the information for teh Flamethrower move, 
 * this move lets the PC slect direction of effect and attacks in that direction all 
 * selected cells
 * @author niallschavez
 *
 */
public class FlameThrower implements Magic, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public ArrayList<FlatMap> getAttackArea() {
		ArrayList<FlatMap> areaOfEffect= new ArrayList<FlatMap>();
		
		/*
		 * vertical line
		 */
		//FlatMap(x,y)
		areaOfEffect.add(new FlatMap(0, 75));
		areaOfEffect.add(new FlatMap(0, -50));
		areaOfEffect.add(new FlatMap(0, -25));
		areaOfEffect.add(new FlatMap(0, 25));
		areaOfEffect.add(new FlatMap(0, 50));
		areaOfEffect.add(new FlatMap(0, -75));
		/*
		 * HorizontalLine
		 */
		areaOfEffect.add(new FlatMap(-75, 0));
		areaOfEffect.add(new FlatMap(-50, 0));
		areaOfEffect.add(new FlatMap(-25, 0));
		areaOfEffect.add(new FlatMap(25, 0));
		areaOfEffect.add(new FlatMap(50, 0));
		areaOfEffect.add(new FlatMap(75, 0));
		
		return areaOfEffect;
	}

	@Override
	public Image getImage() {
		Image flameThrower = null;
		try {
			flameThrower = (Image)ImageIO.read(new File("./Magic_Icons/mediumFire.png")).getScaledInstance(32, 32, 0);;
		} catch (IOException e) {
			System.err.println("Could not load in FlameThrower Image");
			e.printStackTrace();
		}
		return flameThrower;
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
		
		return "F. Thrower";
	}

	@Override
	public boolean rotateable() {
		
		return true;
	}
}
