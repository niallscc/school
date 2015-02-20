package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import junit.framework.TestCase;

/**
 * 
 * @author Sarah Heckman
 *
 * Unit tests for CoffeeMaker class.
 */
public class CoffeeMakerTest extends TestCase {
	
	private CoffeeMaker cm;
	private Recipe r1;
	private Recipe r2;
	private Recipe r3;
	private Recipe r4;

	protected void setUp() throws Exception {
		cm = new CoffeeMaker();
		
		//Set up for r1
		r1 = new Recipe();
		r1.setName("c1");
		r1.setAmtChocolate("0");
		r1.setAmtCoffee("3");
		r1.setAmtMilk("1");
		r1.setAmtSugar("1");
		r1.setPrice("50");
		
		//Set up for r2
		r2 = new Recipe();
		r2.setName("c2");
		r2.setAmtChocolate("20");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		r2.setAmtSugar("1");
		r2.setPrice("75");
		
		//Set up for r3
		r3 = new Recipe();
		r3.setName("Latte");
		r3.setAmtChocolate("0");
		r3.setAmtCoffee("3");
		r3.setAmtMilk("3");
		r3.setAmtSugar("1");
		r3.setPrice("100");
		
		//Set up for r4
		r4 = new Recipe();
		r4.setName("Hot Chocolate");
		r4.setAmtChocolate("4");
		r4.setAmtCoffee("0");
		r4.setAmtMilk("1");
		r4.setAmtSugar("1");
		r4.setPrice("65");
		
		super.setUp();
	}
	public void testMaxRecipes(){
		try {
			cm.addInventory("4","7","0","9");
			cm.addInventory("4","7","0","9");
			cm.addInventory("4","7","0","9");
			cm.addInventory("4","7","0","9");
			
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
		assertFalse(cm.getRecipes().length==4);
	
	}
	public void testAddInventory() {
		try {
			cm.addInventory("4","7","0","9");
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
	}
	
	public void testAddInventoryException() {
		try {
			cm.addInventory("4", "-1", "asdf", "3");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			//success if thrown
		}
	}
	public void testAddChocolate(){
		try {
			cm.addInventory("0", "0", "0", "1");
			System.out.println(cm.checkInventory());
			assertTrue(cm.checkInventory().contains("16"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testAddSugar(){
		try {
			cm.addInventory("0", "0", "1", "0");
			System.out.println(cm.checkInventory());
			assertTrue(cm.checkInventory().contains("16"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testAddCoffee(){
		try {
			cm.addInventory("0", "1", "0", "0");
			System.out.println(cm.checkInventory());
			assertTrue(cm.checkInventory().contains("16"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testAddMilk(){
		try {
			cm.addInventory("1", "0", "0", "0");
			System.out.println(cm.checkInventory());
			assertTrue(cm.checkInventory().contains("16"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testMakeCoffee() {
		cm.addRecipe(r1);
		assertEquals(25, cm.makeCoffee(0, 75));
	}
	public void testMakeCoffeeBadInput(){
		Main m = new Main();
		
	}

}
