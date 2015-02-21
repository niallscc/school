package gameBoard;



/**
 * @author amrsaad
 *
 */
public abstract class Being implements Lifeform {
	
	private static int IDCounter = 0;
	private final int _id;
	private final Player _player;
	private FlatMap _location;
	protected int attack;
	protected int defense;
	protected int spawnTimer;


	public Being(Player player, FlatMap spawnloc)
	{
		_player = player;
		_location = spawnloc;
		_id = IDCounter++;
	}


	public FlatMap getLocation() {
		return _location;
	}

	public void setLocation(FlatMap location) {
		_location = location;
	}

	public int getId() {
		return _id;
	}

	public Player getPlayer() {
		return _player;
	}

	public int getAttack() {
		return attack;
	}


	public int getDefense() {
		return defense;

	}



}
