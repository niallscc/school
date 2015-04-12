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
 * Defense, while it may not seems like a magic attack, it is actually 
 * performed like one it takes a defense event and sends it to the server then updates 
 * the only difference is that defense only effects the PC
 * @author niallschavez
 *
 */
@SuppressWarnings("serial")
public class Defense implements Magic, Serializable{

	@Override
	public ArrayList<FlatMap> getAttackArea() {
		ArrayList<FlatMap> area= new ArrayList<FlatMap>();
		area.add(new FlatMap(0,0));
		return area;
	}

	@Override
	public Image getImage() {
		Image shield = null;
		try {
			shield = (Image)ImageIO.read(new File("./AttackIcons/ShieldOverlay.png")).getScaledInstance(32, 32, 0);
		} catch (IOException e) {
			System.err.println("Could not load in Fire Image");
			e.printStackTrace();
		}
		return shield;
	}

	@Override
	public boolean selectable() {
		
		return false;
	}

	@Override
	public String getName() {
		
		return "Defend";
	}

	@Override
	public boolean rotateable() {
		// TODO Auto-generated method stub
		return false;
	}

}
