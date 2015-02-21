package gameBoard;
import java.util.Random;


/**
 * @author amrsaad
 *
 */
public enum Character {
	
	slime ( 25, 30, "./GladOS_Graphics/zombie_2.png"),
	zombie( 25, 30, "./GladOS_Graphics/zombie_1.png");

	
	private final int defense;
	private final int attack;
	private final String filename;
	private static final Random rand = new Random();

	Character(int def, int att, String filename){
		this.defense = def;
		this.attack = att;
		this.filename = filename;
	}

	
	public int getMoolah() {
		return defense;
	}


	public int getbenValue() {
		return attack;
	}

	public String getImageFileName() {
		return filename;
	}


	public static Character getRandomCharacter(){
		Character[] cha = Character.values();
		return(cha[rand.nextInt(cha.length)]);

	}
	public static Character getCharacter(int i) {

		switch (i) {
			case 's':	return slime;
			case 'z': 	return zombie;
			case '*':   return null;
			case '0':   return null;
			
		}
		return null;

	}

}
