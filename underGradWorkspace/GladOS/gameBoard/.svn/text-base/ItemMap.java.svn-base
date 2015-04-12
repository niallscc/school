package gameBoard;
/**
 * @author amrsaad
 *
 */
public class ItemMap {
	

	ItemsCell[][] map;
	private final int x, y;
	FlatMap location;


	

	public ItemMap(int x, int y){
		this.x = x;
		this.y = y;
		map = new ItemsCell[x][y];
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				map[i][j] = new ItemsCell(Items.getRandomItems(), new FlatMap(x,y));
	}

	

	public ItemMap(ItemsCell[][] map){
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

	public ItemsCell getCellAt(FlatMap location) {
		return map[location.getX()][location.getY()];
	}

	

	public void setCellAt(FlatMap location, Items value) {
		map[location.getX()][location.getY()] = new ItemsCell(value, location );
	}
	

	public int[][] getMapMatrix() {
		int matrix[][] = new int[x][y];
		for (int i=0; i<x; i++) {
			for (int j=0; j<y; j++) {
				matrix[i][j] = getCellAt(new FlatMap(i, j)).getItems().ordinal();
			}
		}
		return matrix;
	}
	public  ItemsCell[][] getMap(){
		return map;
	}
}
