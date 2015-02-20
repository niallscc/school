package edu.unm.cs583;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VelocityTester {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testreverseY (){
		Velocity revY=new Velocity();
		revY.setDirection(45);
		revY.ySpeed=45;
		revY.reverseY();
		assertEquals(315, revY.direction);
	}
	@Test
	public void testdecomposeSpeed (){
		Velocity decomp=new Velocity();
		decomp.setSpeed(45);
		decomp.decomposeSpeed();
		assertEquals(45, decomp.getSpeedX());
	
	}
	@Test
	public void testsetSpeed (){
		Velocity sets=new Velocity();
		sets.setSpeed(45);
		assertEquals(45, sets.getSpeedX());
	}
	@Test 
	public void testreverseX() {
		Velocity R1 = new Velocity();
	       R1.setDirection(45);	
		   R1.xSpeed=45;
		   R1.reverseX();
		   assertEquals(135, R1.direction);
		}



	@Test
	public void testSpeedX() {
			Velocity v = new Velocity();		
			assertTrue(v.getSpeedX() == 0);
		}
	@Test
	public void testSpeedY() {
			Velocity v = new Velocity();		
			assertTrue(v.getSpeedY() == 0);
	}

	@Test
	/** public void test() {
		fail("Not yet implemented"); **/
	public void testSetDirection() {
		Velocity out = new Velocity();
		   out.setDirection(45);
		   assertEquals("Result", 45, out.getDirection());
		}

}