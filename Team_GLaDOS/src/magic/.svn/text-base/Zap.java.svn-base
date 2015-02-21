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
 * Zap is a rotateable magic move that allows the user to select a direction and 
 * attack all teh cells in that direction with lightening
 * @author niallschavez
 *
 */
public class Zap implements Magic, Serializable{

	/**
	 * returns the area of effect for a zap move
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
		Image zap = null;
		try {
			zap = (Image)ImageIO.read(new File("./Magic_Icons/lightElectricity.png")).getScaledInstance(32, 32, 0);;
		} catch (IOException e) {
			System.err.println("Could not load in zap Image");
			e.printStackTrace();
		}
		return zap;
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
		
		return "Zap";
	}

	@Override
	public boolean rotateable() {

		return true;
	}
}
