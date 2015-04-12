/**
 * 
 */
package testing;

import static org.junit.Assert.*;

import java.util.LinkedList;

import gameBoard.FlatMap;
import gameBoard.TerrainMap;

import org.junit.Before;
import org.junit.Test;

import actors.AIastar;
import actors.Being;
import actors.ZombieCow;

/**
 * @author amrsaad
 *
 */
public class AstarTesting {
	

	/**
	 * A terrain map that will be used for testing.
	 */
	TerrainMap tm;
	/**
	 * The start location
	 */
	FlatMap start;
	/**
	 * The end location..
	 */
	FlatMap end;
	/**
	 * A-star instance.
	 */
	AIastar astar;
	
	/**
	 * Being used for testing.
	 */
	Being b;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tm = new TerrainMap(10,10);
		astar = new AIastar();
		b = new ZombieCow(new FlatMap(2,2),100);

	}
	
	@Test
	public void Map1Test(){
		start = new FlatMap(0,0);
		end = new FlatMap(9,9);
			
		tm.getCell(5,0).setBeing(b);
		tm.getCell(5,1).setBeing(b);
		tm.getCell(5,2).setBeing(b);
		tm.getCell(5,3).setBeing(b);
		tm.getCell(5,4).setBeing(b);
		tm.getCell(5,5).setBeing(null);
		tm.getCell(5,6).setBeing(b);
		tm.getCell(5,7).setBeing(b);
		tm.getCell(5,8).setBeing(b);
		tm.getCell(5,9).setBeing(b);



		astar.setPath(tm.getCellAt(start), tm.getCellAt(end), tm);
		LinkedList<FlatMap> list = astar.findPath();
		assertTrue(list.contains(tm.getCell(5, 5).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 1).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 2).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 3).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 4).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 6).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 7).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 8).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 9).getCoordinates()));



		
	}
	
	@Test
	public void Map2Test(){
		start = new FlatMap(0,0);
		end = new FlatMap(9,9);
			
		tm.getCell(5,0).setBeing(b);
		tm.getCell(5,1).setBeing(b);
		tm.getCell(5,2).setBeing(b);
		tm.getCell(5,3).setBeing(b);
		tm.getCell(5,4).setBeing(b);
		tm.getCell(5,5).setBeing(b);
		tm.getCell(5,6).setBeing(b);
		tm.getCell(5,7).setBeing(b);
		tm.getCell(5,8).setBeing(b);
		tm.getCell(5,9).setBeing(null);



		astar.setPath(tm.getCellAt(start), tm.getCellAt(end), tm);
		LinkedList<FlatMap> list = astar.findPath();
		assertTrue(list.contains(tm.getCell(5, 9).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 1).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 2).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 3).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 4).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 6).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 7).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 8).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 5).getCoordinates()));
		
	}
	
	@Test
	public void Map3Test(){
		start = new FlatMap(0,5);
		end = new FlatMap(9,5);
			
		tm.getCell(5,0).setBeing(b);
		tm.getCell(5,1).setBeing(b);
		tm.getCell(5,2).setBeing(b);
		tm.getCell(5,3).setBeing(b);
		tm.getCell(5,4).setBeing(b);
		tm.getCell(5,5).setBeing(b);
		tm.getCell(5,6).setBeing(b);
		tm.getCell(5,7).setBeing(b);
		tm.getCell(5,8).setBeing(b);
		tm.getCell(5,9).setBeing(null);


		astar.setPath(tm.getCellAt(start), tm.getCellAt(end), tm);
		LinkedList<FlatMap> list = astar.findPath();
		assertTrue(list.contains(tm.getCell(5, 9).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 1).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 2).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 3).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 4).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 6).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 7).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 8).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 5).getCoordinates()));
		
	}

	@Test
	public void Map4Test(){
		start = new FlatMap(0,5);
		end = new FlatMap(9,5);
			
		tm.getCell(5,0).setBeing(b);
		tm.getCell(5,1).setBeing(b);
		tm.getCell(5,2).setBeing(b);
		tm.getCell(5,3).setBeing(b);
		tm.getCell(5,4).setBeing(b);
		tm.getCell(5,5).setBeing(b);
		tm.getCell(5,6).setBeing(b);
		tm.getCell(5,7).setBeing(b);
		tm.getCell(5,8).setBeing(null);
		tm.getCell(5,9).setBeing(b);



		astar.setPath(tm.getCellAt(start), tm.getCellAt(end), tm);
		LinkedList<FlatMap> list = astar.findPath();
		assertTrue(list.contains(tm.getCell(5, 8).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 1).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 2).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 3).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 4).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 6).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 7).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 9).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 5).getCoordinates()));
		
	}
	@Test public void Map5Test(){
		start = new FlatMap(0,5);
		end = new FlatMap(9,5);
			
		tm.getCell(5,0).setBeing(b);
		tm.getCell(5,1).setBeing(b);
		tm.getCell(5,2).setBeing(b);
		tm.getCell(5,3).setBeing(b);
		tm.getCell(5,4).setBeing(b);
		tm.getCell(5,5).setBeing(b);
		tm.getCell(5,6).setBeing(b);
		tm.getCell(5,7).setBeing(b);
		tm.getCell(5,8).setBeing(null);
		tm.getCell(5,9).setBeing(b);
		
		tm.getCell(7,0).setBeing(b);
		tm.getCell(7,1).setBeing(b);
		tm.getCell(7,2).setBeing(b);
		tm.getCell(7,3).setBeing(b);
		tm.getCell(7,4).setBeing(b);
		tm.getCell(7,5).setBeing(b);
		tm.getCell(7,6).setBeing(b);
		tm.getCell(7,7).setBeing(b);
		tm.getCell(7,8).setBeing(b);
		tm.getCell(7,9).setBeing(null);



		astar.setPath(tm.getCellAt(start), tm.getCellAt(end), tm);
		LinkedList<FlatMap> list = astar.findPath();
		assertTrue(list.contains(tm.getCell(5, 8).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 1).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 2).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 3).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 4).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 6).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 7).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 9).getCoordinates()));
		assertFalse(list.contains(tm.getCell(5, 5).getCoordinates()));
		

		assertTrue(list.contains(tm.getCell(7, 9).getCoordinates()));
		assertFalse(list.contains(tm.getCell(7, 1).getCoordinates()));
		assertFalse(list.contains(tm.getCell(7, 2).getCoordinates()));
		assertFalse(list.contains(tm.getCell(7, 3).getCoordinates()));
		assertFalse(list.contains(tm.getCell(7, 4).getCoordinates()));
		assertFalse(list.contains(tm.getCell(7, 6).getCoordinates()));
		assertFalse(list.contains(tm.getCell(7, 7).getCoordinates()));
		assertFalse(list.contains(tm.getCell(7, 8).getCoordinates()));
		assertFalse(list.contains(tm.getCell(7, 5).getCoordinates()));
		
	}

}
