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
 * This class sets up the normal attack, it is categorized as a magic attack because
 * that is easiest, basically what that means is that it is essentially just another magic attack
 * all that is different is the fact that the radius is only one around the player 
 * @author niallschavez
 *
 */
public class Attack implements Magic, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<FlatMap> getAttackArea() {
		ArrayList<FlatMap> areaOfEffect= new ArrayList<FlatMap>();
		

		//FlatMap(x,y)
		areaOfEffect.add(new FlatMap(-25, 0));
		areaOfEffect.add(new FlatMap(25, 0));
		areaOfEffect.add(new FlatMap(0, 25));
		areaOfEffect.add(new FlatMap(0, -25));
		
		return areaOfEffect;
	}

	@Override
	public Image getImage() {
		Image sword = null;
		try {
			sword = (Image)ImageIO.read(new File("./AttackIcons/SwordOverlayUp.png")).getScaledInstance(32, 32, 0);;
		} catch (IOException e) {
			System.err.println("Could not load in Sword Image");
			e.printStackTrace();
		}
		return sword;
	}

	@Override
	public boolean selectable() {
		
		return false;
	}

	@Override
	public String getName() {
		
		return "Attack";
	}

	@Override
	public boolean rotateable() {

		return true;
	}

}
