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
 * This class does the magic move I Feel Much Better now, this magic move 
 * basically allows the user to completely heal a character 
 * @author niallschavez
 *
 */
public class IFeelMuchBetterNow implements Magic, Serializable {

	/**
	 * Returns the area of effect for the magic healing move
	 * I feel Much Better or IFMB 
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
		areaOfEffect.add(new FlatMap(-25, -50));
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
		Image muchBetter = null;
		try {
			muchBetter = (Image)ImageIO.read(new File("./Magic_Icons/heavyHealing.png")).getScaledInstance(32, 32, 0);;
		} catch (IOException e) {
			System.err.println("Could not load in heavy healing Image");
			e.printStackTrace();
		}
		return muchBetter;
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
		
		return "I.F.M.B.N.";
	}

	@Override
	public boolean rotateable() {
		// TODO Auto-generated method stub
		return false;
	}
}
