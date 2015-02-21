package gameBoard;
/**
 * @author amrsaad
 *
 */
public class TerrainMap {
	

	TerrainCell[][] map;
	private final int x, y;
	FlatMap location;


	

	public TerrainMap(int x, int y){
		this.x = x;
		this.y = y;
		map = new TerrainCell[x][y];
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				map[i][j] = new TerrainCell(Terrain.getRandomTerrain(), new FlatMap(x,y));
	}

	

	public TerrainMap(TerrainCell[][] map){
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

	public TerrainCell getCellAt(FlatMap location) {
		return map[location.getX()][location.getY()];
	}

	

	public void setCellAt(FlatMap location, Terrain value) {
		//System.out.println ("X "+location.getX()+" Y "+location.getY() );
		map[location.getX()][location.getY()] = new TerrainCell(value, location );
		
	}
	

	public int[][] getMapMatrix() {
		int matrix[][] = new int[x][y];
		for (int i=0; i<x; i++) {
			for (int j=0; j<y; j++) {
				matrix[i][j] = getCellAt(new FlatMap(i, j)).getTerrain().ordinal();
			}
		}
		return matrix;
	}

	public static TerrainMap loadMapMatrix(int[][] m) {
		TerrainMap map = new TerrainMap(m.length, m[0].length);
		for (int i=0; i<m.length; i++) {
			for (int j=0; j<m[i].length; j++) {
				map.setCellAt( new FlatMap(i,j), Terrain.getTerrain(m[i][j]) );
			}
		}
		return map;
	}
	
	public  TerrainCell[][] getMap(){
		return map;
	}
	
}
