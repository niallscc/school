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
 * Zot does selected lightening damage in the area of effect for this 
 * move 
 * @author niallschavez
 *
 */
public class Zot implements Magic, Serializable{

	/**
	 * returns the area of effect for this move
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
		Image zot = null;
		try {
			zot = (Image)ImageIO.read(new File("./Magic_Icons/HeavyElectricity.png")).getScaledInstance(32, 32, 0);;
		} catch (IOException e) {
			System.err.println("Could not load in Zot Image");
			e.printStackTrace();
		}
		return zot;
	}
	/**
	 * Returns whether or not you can select a single cell to attack or if it just attacks everyone in the area of effect
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
		
		return "Zot";
	}

	@Override
	public boolean rotateable() {
		return false;
	}

}
