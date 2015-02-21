package actors;
import java.util.Random;


/**
 * @author amrsaad
 *
 */
public enum Character {
	
	slime ( 25, 30, "./EnemyIcons/slimeZombie.png"),
	zombie( 25, 30, "./EnemyIcons/normalZombie.png"),
	boss(40,40,"./EnemyIcons/bossZombie.png"),
	zombieCow(30,30,"./EnemyIcons/zombieCow.png"),
	zombie2(25,30, "./EnemyIcons/normalZombie2.png");

	
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
			case '2':   return zombie2;
			case 'b':   return boss;
			case 'c':   return zombieCow;
			case '*':   return null;
			case '0':   return null;
			
		}
		return null;

	}

}
