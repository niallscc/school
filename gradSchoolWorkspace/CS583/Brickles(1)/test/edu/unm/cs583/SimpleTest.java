package edu.unm.cs583;


import java.awt.Point;

import junit.framework.*;

/**
 * Some simple tests.
 *
 */
public class SimpleTest extends TestCase {
	Velocity v;
	Puck p; 
	Point pt;
	BricklesPlayingField pf;
	protected void setUp() {
	}
	public static Test suite() {

		/*
		 * the type safe way
		 *
		TestSuite suite= new TestSuite();
		suite.addTest(
			new SimpleTest("add") {
				 protected void runTest() { testAdd(); }
			}
		);

		suite.addTest(
			new SimpleTest("testDivideByZero") {
				 protected void runTest() { testDivideByZero(); }
			}
		);
		return suite;
		*/

		/*
		 * the dynamic way
		 */
		return new TestSuite(SimpleTest.class);
	}
	public void test1() {
		v = new Velocity();		
		assertTrue(v.getDirection() == 0);
	}
	public void test2(){
		PuckSupply pucks = new PuckSupply(null);
		assertTrue(pucks.numberLeft() == 3);
	}
	public void test3(){
		v= new Velocity();
		assertTrue(v.getSpeedX()==0 && v.getSpeedY()==0);
	}
	public void test4(){
		v= new Velocity();
		v.setSpeed(10);
		assertTrue(v.getSpeedX()==10 && v.getSpeedY()==0);
	}
	public static void main (String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}