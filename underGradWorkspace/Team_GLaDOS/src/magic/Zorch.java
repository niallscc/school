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
 * This returns the area of effect for the zorch move, zorch does selected damage 
 * on a given cell within the radius of effect 
 * @author niallschavez
 *
 */
public class Zorch implements Magic, Serializable {

	/**
	 * This returns the area of effect for the zorch move, zorch 
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
		Image zorch = null;
		try {
			zorch = (Image)ImageIO.read(new File("./Magic_Icons/mediumElectricity.png")).getScaledInstance(32, 32, 0);;
		} catch (IOException e) {
			System.err.println("Could not load in zorch Image");
			e.printStackTrace();
		}
		return zorch;
	}

	/**
	 * returns whether or not the radius of effect is selectable or if it just affects a whole area. 
	 */
	@Override
	public boolean selectable() {
		
		return true;
	}
	/**
	 * returns the name of this magic 
	 */
	@Override
	public String getName() {
		
		return "Zorch";
	}


	@Override
	public boolean rotateable() {
		return false;
	}

}
