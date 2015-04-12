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
 * Blizzard is a magic attack that does damage in a selected cell in 
 * the radius around the player
 * 
 * @author niallschavez
 *
 */
public class Blizzard implements Magic, Serializable{

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
		Image blizzard = null;
		try {
			blizzard = (Image)ImageIO.read(new File("./Magic_Icons/mediumIce.png")).getScaledInstance(32, 32, 0);
		} catch (IOException e) {
			System.err.println("Could not load in Blizzard Image");
			e.printStackTrace();
		}
		return blizzard;
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
		
		return "Blizzard";
	}

	@Override
	public boolean rotateable() {
		return false;
	}
}
