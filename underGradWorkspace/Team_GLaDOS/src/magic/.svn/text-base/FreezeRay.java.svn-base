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
 * The Freeze ray allows the user to select a direction of effect 
 * for the move and performs an ice attack on the cells in that range 
 * @author niallschavez
 *
 */
public class FreezeRay implements Magic, Serializable {

	/**
	 * returns the area of effect for the freeze ray 
	 */
	private static final long serialVersionUID = 1L;
	@Override
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
		
		return areaOfEffect;
	}

	@Override
	public Image getImage() {
		Image freezeRay = null;
		try {
			freezeRay = (Image)ImageIO.read(new File("./Magic_Icons/heavyIce.png")).getScaledInstance(32, 32, 0);;
		} catch (IOException e) {
			System.err.println("Could not load in Blizzard Image");
			e.printStackTrace();
		}
		return freezeRay;
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
		
		return "Freeze Ray";
	}

	@Override
	public boolean rotateable() {
		return true;
	}

}
