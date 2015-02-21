/**
 * 
 */
package actors;

import gameBoard.FlatMap;
import gameBoard.TerrainMap;


/**
 * @author amrsaad
 *
 */
public class AITest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TerrainMap tm = new TerrainMap(10,10);
		
		
		FlatMap start = new FlatMap(1,1);
		FlatMap end = new FlatMap(9,9);
		@SuppressWarnings("unused")
		FlatMap mid = new FlatMap(5,5);
//		ArrayList<FlatMap> test = ManDistance.getLineDist(start,new FlatMap(1,0), 5);
//		for(FlatMap fm : test){
//			System.out.println(fm.getX()+","+fm.getY());
//			
//		}
		
		System.out.println();
		System.out.println();
		tm.getCell(5, 0).setOccupied(true);

		tm.getCell(5, 9).setOccupied(true);

		tm.getCell(5, 8).setOccupied(true);
		tm.getCell(5, 7).setOccupied(true);
		tm.getCell(5, 6).setOccupied(true);
		tm.getCell(5, 4).setOccupied(true);
		tm.getCell(5, 3).setOccupied(true);
		tm.getCell(5, 2).setOccupied(true);
		tm.getCell(5, 1).setOccupied(true);

		

		
		
		AIastar  astar = new AIastar();
		System.out.println();
		astar.setPath(tm.getCellAt(start), tm.getCellAt(end), tm);
		
		System.out.println(astar.openList);
		astar.findPath();
		System.out.println(astar.getPath().size());
		for(FlatMap fm : astar.getPath())
		System.out.println(fm.getX()+","+fm.getY());

		

	}

}
