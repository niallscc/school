package magic;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import gameBoard.FlatMap;
import gameBoard.Magic;

public class GreatDrugs implements Magic, Serializable{

	/**
	 * Returns the area of effect for the Great drugs move, this move 
	 * allows a player to select a cell and perform a medium healing 
	 * on a PC character, even its self.
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
		Image greatDrugs = null;
		try {
			greatDrugs = (Image)ImageIO.read(new File("./Magic_Icons/mediumHealing.png")).getScaledInstance(32, 32, 0);;
		} catch (IOException e) {
			System.err.println("Could not load in great Drugs Image");
			e.printStackTrace();
		}
		return greatDrugs;
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
		
		return "Great Drugs";
	}

	@Override
	public boolean rotateable() {
	
		return false;
	}

}
