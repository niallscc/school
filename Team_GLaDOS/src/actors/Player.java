package actors;

import java.io.Serializable;

/**
 * Used for storing the character configuration that the player created for their PC.
 * @author Gladdos
 *
 */
public class Player implements Serializable, Lifeform{
	/**
	 * Used for serialization.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The name of the player.
	 */
	private String n;
	/**
	 * The gender of the player.
	 */
	private String g;
	/**
	 * The factory used to create the player class
	 */
	private PCFactory classFactory;
	/**
	 * Creates an object with the given name, PC type and gender.
	 * @param name The name of the Player 
	 * @param playerClass The type of PC
	 * @param gend The gender of the PC
	 */
	public Player(String name, PCFactory playerClass, int gend){
		classFactory=playerClass;
		n=name;
		if(gend == 0)
			g = "Male";
		else
			g = "Female";
	}
	/**
	 * The factory to create the specified PC type.
	 * @return The specific PC type.
	 */
	public PCFactory getPCFactory(){
		return classFactory;
	}
	/**
	 * Getter for the name of the Player object
	 * @return The name.
	 */
	public String getName(){
		return n;
	}
	/**
	 * Getter for the gender of the Player object
	 * @return The gender.
	 */
	public String getGender(){
		return g;
	}
	public void setLevel(int Level){
		
	}
	
}
