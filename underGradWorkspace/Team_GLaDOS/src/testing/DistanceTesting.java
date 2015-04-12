/**
 * 
 */
package testing;


import static org.junit.Assert.*;
import gameBoard.FlatMap;
import gameBoard.TerrainMap;

import org.junit.Before;
import org.junit.Test;

import actors.ManDistance;

/**
 * @author amrsaad
 *
 */
public class DistanceTesting {


	/**
	 * A terrain map that will be used for testing.
	 */
	TerrainMap tm;
	/**
	 * The start location
	 */
	FlatMap caster;
	/**
	 * The end location..
	 */
	FlatMap target;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tm = new TerrainMap(10,10);
		
	}
	
	@Test
	public void distanceTest1(){
		
		caster = tm.getCell(2,2).getCoordinates();
		
		target = tm.getCell(3,3).getCoordinates();
		assertTrue(ManDistance.getDistance(caster, target) == 2);
		target = tm.getCell(1,1).getCoordinates();
		assertTrue(ManDistance.getDistance(caster, target) == 2);
		target = tm.getCell(2,3).getCoordinates();
		assertTrue(ManDistance.getDistance(caster, target) == 1);
		target = tm.getCell(2,1).getCoordinates();
		assertTrue(ManDistance.getDistance(caster, target) == 1);
		target = tm.getCell(3,2).getCoordinates();
		assertTrue(ManDistance.getDistance(caster, target) == 1);
		target = tm.getCell(1,2).getCoordinates();
		assertTrue(ManDistance.getDistance(caster, target) == 1);
		target = tm.getCell(2,5).getCoordinates();
		assertTrue(ManDistance.getDistance(caster, target) == 3);
		target = tm.getCell(3,5).getCoordinates();
		assertTrue(ManDistance.getDistance(caster, target) == 4);
		target = tm.getCell(5,5).getCoordinates();
		assertTrue(ManDistance.getDistance(caster, target) == 6);
		
	}
	
	@Test
	public void radiusTest1(){
		caster = tm.getCell(2,2).getCoordinates();
		assertTrue(ManDistance.getDistRadius(caster, 0).size() == 0);
		assertTrue(ManDistance.getDistRadius(caster, 1).size() == 4);
		assertTrue(ManDistance.getDistRadius(caster, 2).size() == 12);
		assertTrue(ManDistance.getDistRadius(caster, 3).size() == 22);
	}
	
	@Test
	public void lineTest1(){
		caster = tm.getCell(2,2).getCoordinates();
		FlatMap dir = tm.getCell(2, 3).getCoordinates();
		
		assertTrue(ManDistance.getLineDist(caster, dir, 1).size() == 1);
		assertTrue(ManDistance.getLineDist(caster, dir, 2).size() == 2);
		assertTrue(ManDistance.getLineDist(caster, dir, 3).size() == 3);
		assertTrue(ManDistance.getLineDist(caster, dir, 4).size() == 4);
		assertTrue(ManDistance.getLineDist(caster, dir, 5).size() == 5);
		
		FlatMap dir1 = tm.getCell(2, 1).getCoordinates();
		
		assertTrue(ManDistance.getLineDist(caster, dir1, 1).size() == 1);
		assertTrue(ManDistance.getLineDist(caster, dir1, 2).size() == 2);
		assertTrue(ManDistance.getLineDist(caster, dir1, 3).size() == 2);
		assertTrue(ManDistance.getLineDist(caster, dir1, 4).size() == 2);
		assertTrue(ManDistance.getLineDist(caster, dir1, 5).size() == 2);
		
		FlatMap dir3 = tm.getCell(1, 2).getCoordinates();
		
		assertTrue(ManDistance.getLineDist(caster, dir3, 1).size() == 1);
		assertTrue(ManDistance.getLineDist(caster, dir3, 2).size() == 2);
		assertTrue(ManDistance.getLineDist(caster, dir3, 3).size() == 2);
		assertTrue(ManDistance.getLineDist(caster, dir3, 4).size() == 2);
		assertTrue(ManDistance.getLineDist(caster, dir3, 5).size() == 2);
		
		FlatMap dir2 = tm.getCell(3, 2).getCoordinates();
		
		assertTrue(ManDistance.getLineDist(caster, dir2, 1).size() == 1);
		assertTrue(ManDistance.getLineDist(caster, dir2, 2).size() == 2);
		assertTrue(ManDistance.getLineDist(caster, dir2, 3).size() == 3);
		assertTrue(ManDistance.getLineDist(caster, dir2, 4).size() == 4);
		assertTrue(ManDistance.getLineDist(caster, dir2, 5).size() == 5);

	}
	

}
