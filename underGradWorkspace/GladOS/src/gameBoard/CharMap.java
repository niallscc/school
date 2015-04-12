package gameBoard;
/**
 * @author amrsaad
 *
 */
public class CharMap {
	

	CharCell[][] map;
	private final int x, y;
	FlatMap location;
	Player player;	

	public CharMap(int x, int y){
		this.x = x;
		this.y = y;
		map = new CharCell[x][y];
	}

	

	public CharMap(CharCell[][] map){
		this.map = map;
		y = map.length;
		x = map[0].length;
	}
	

	public int getLength(){
		return x;

	}

	public int getHeight(){
		return y;
	}

	public CharCell getCellAt(FlatMap location) {
		return map[location.getX()][location.getY()];
	}

	

	public void setCellAt(FlatMap location, Character character) {
		map[location.getX()][location.getY()] = new CharCell(character, location );
	}
	

	public Character[][] getMapMatrix() {
		Character matrix[][] = new Character[x][y];
		for (int i=0; i<x; i++) {
			for (int j=0; j<y; j++) {
				matrix[i][j] = getCellAt(new FlatMap(i, j)).getCharacter();
			}
		}
		return matrix;
	}
	public CharCell[][] getMap(){
		return map;
	}

	
}
