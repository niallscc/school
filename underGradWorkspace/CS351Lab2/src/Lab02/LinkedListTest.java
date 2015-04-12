package Lab02;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class LinkedListTest 
{
	@Test public void testGetHead()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		assertEquals(LL.getHead(), new Integer(1));
		
	}
	
	@Test public void testAddContains()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		
		LL.add(new Integer(2));
		LL.add(new Integer(3));
		LL.add(new Integer(4));
		LL.add(new Integer(5));
		LL.add(new Integer(6));
		LL.add(new Integer(7));
		LL.add(new Integer(8));
		LL.add(new Integer(9));
		
		assertEquals(LL.getHead(), new Integer(1));
		assertTrue(LL.contains(new Integer(1)));
		assertTrue(LL.contains(new Integer(2)));
		assertTrue(LL.contains(new Integer(3)));
		assertTrue(LL.contains(new Integer(4)));
		assertTrue(LL.contains(new Integer(5)));
		assertTrue(LL.contains(new Integer(6)));
		assertTrue(LL.contains(new Integer(7)));
		assertTrue(LL.contains(new Integer(8)));
		assertTrue(LL.contains(new Integer(9)));
		
		assertFalse(LL.contains(new Integer(10)));
		assertFalse(LL.contains(new Integer(-1)));
		assertFalse(LL.contains(new Integer(1000)));
	}
	
	
	@Test public void testRemove()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		
		LL.add(new Integer(2));
		LL.add(new Integer(3));
		LL.add(new Integer(4));
		LL.add(new Integer(5));
		LL.add(new Integer(6));
		LL.add(new Integer(7));
		LL.add(new Integer(8));
		LL.add(new Integer(9));
		
		//Test removal of the head (edge case)
		LL.remove(1);
		assertFalse(LL.contains(new Integer(1)));
		assertEquals(LL.getHead(), new Integer(2));
		
		//test removal of a removed element to make sure it does not blow up
		LL.remove(1);
		assertFalse(LL.contains(new Integer(1)));
		assertEquals(LL.getHead(), new Integer(2));
		
		//test removal of the head again
		LL.remove(2);
		assertFalse(LL.contains(new Integer(2)));
		assertEquals(LL.getHead(), new Integer(3));
		
		//test removal of a non head element
		LL.remove(6);
		assertFalse(LL.contains(new Integer(6)));
		
		//test removal of the tail element		
		LL.remove(9);
		assertFalse(LL.contains(new Integer(9)));
		
		//test removal of the tail element again (another edge case)
		LL.remove(8);
		assertFalse(LL.contains(new Integer(8)));
		
		//Make sure the elements that are supposed to be there still are
		assertFalse(LL.contains(new Integer(1)));
		assertFalse(LL.contains(new Integer(2)));
		assertTrue(LL.contains(new Integer(3)));
		assertTrue(LL.contains(new Integer(4)));
		assertTrue(LL.contains(new Integer(5)));	
		assertFalse(LL.contains(new Integer(6)));
		assertTrue(LL.contains(new Integer(7)));
		assertFalse(LL.contains(new Integer(8)));
		assertFalse(LL.contains(new Integer(9)));
		
		//Make sure some random elements were not added
		assertFalse(LL.contains(new Integer(10)));
		assertFalse(LL.contains(new Integer(-1)));
		assertFalse(LL.contains(new Integer(1000)));
		
		//Make sure the head is correct after all the removes and adds
		assertEquals(LL.getHead(), new Integer(3));
	}
	
	@Test public void TestEmptyList()
	{
		//IF THE HEAD IS SET TO NULL STRANGE THINGS MAY HAPPEN
		//SO THE ADD, REMOVE AND GETHEAD METHODS MUST HAVE SPECIAL CASES TO DEAL WITH THIS
	
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		LL.remove(1);
		assertFalse(LL.contains(1));
		
		assertEquals(LL.getHead(), null); 	
		
		
		LL.add(1);
		
		assertTrue(LL.contains(1));
		assertEquals(LL.getHead(), new Integer(1)); 
		
	}
	
	@Test public void testEmpty()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		
		for (int i = 0; i < 100; i++)
		{
			LL.add(i);
		}
		
		LL.empty();
		
		for (int i = 0; i < 100; i++)
		{
			assertFalse(LL.contains(i));
		}
		
	}
	
	@Test public void testEmptyTail()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		
		for (int i = 0; i < 100; i++)
		{
			LL.add(i);
		}
		
		LL.empty();
		
		for (int i = 0; i < 100; i++)
		{
			assertFalse(LL.contains(i));
		}
		
		//Now we need to make sure that something fishy didn't happen to the list
		
		LL.add(1);
		LL.add(2);
		LL.add(3);
		
		assertTrue(LL.contains(1));
		assertTrue(LL.contains(2));
		assertTrue(LL.contains(3));
		
		
		for (int i = 4; i < 100; i++)
		{
			assertFalse(LL.contains(i));
		}
		
	}
	
	@Test public void testRemoveAtHead()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		LL.removeAt(0);
		
		assertEquals(null, LL.getHead());
		
		
		LL.add(1);
		LL.add(2);
		LL.add(3);
		LL.add(4);
		LL.add(5);
		
		LL.removeAt(0);
		
		assertEquals(new Integer(2),LL.getHead());
		assertTrue(LL.contains(2));
		assertTrue(LL.contains(3));
		assertTrue(LL.contains(4));
		assertTrue(LL.contains(5));
	}
	
	
	
	@Test public void testRemoveAt()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		LL.add(2);
		LL.add(3);
		LL.add(4);
		LL.add(5);
		
		
		LL.removeAt(3);
		assertFalse(LL.contains(4));
		
		LL.removeAt(2);
		assertFalse(LL.contains(3));
		
	}
	
	@Test public void testRemoveAtEnd()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		LL.add(2);
		LL.add(3);
		LL.add(4);
		LL.add(5);
		
		
		LL.removeAt(4);
		assertFalse(LL.contains(5));
		
		LL.removeAt(3);
		assertFalse(LL.contains(4));

		LL.removeAt(2);
		assertFalse(LL.contains(3));

		LL.removeAt(1);
		assertFalse(LL.contains(2));
		
	}
	
	@Test (expected= IndexOutOfBoundsException.class) 
	public void testRemoveAtEmpty()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		LL.removeAt(0);
		LL.removeAt(0);
	}
	
	@Test (expected= IndexOutOfBoundsException.class)
	public void testRemoveAtOutOfBounds()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		LL.removeAt(10);
	}
	
	
	@Test public void getAtHead()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		
		for (int i = 0; i < 100; i++)
		{
			LL.add(i);
		}
		
		
		assertEquals(new Integer(1), LL.getAt(0));
		assertEquals(LL.getHead(), LL.getAt(0));
		
		
		for (int i = 0; i < 100; i++)
		{
			assertTrue(LL.contains(i));
		}
		
	}
	
	@Test public void getAtTail()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		
		for (int i = 0; i < 100; i++)
		{
			LL.add(i);
		}
		
		
		assertEquals(new Integer(99),LL.getAt(100));
		
		
		for (int i = 0; i < 100; i++)
		{
			assertTrue(LL.contains(i));
		}
	}
	
	@Test public void getAt()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		
		LL.remove(1);
		
		for (int i = 0; i < 100; i++)
		{
			LL.add(i);
		}
		
		for (int i = 0; i < 100; i++)
		{
			assertEquals(new Integer(i), LL.getAt(i));
		}
	}
	
	@Test (expected = IndexOutOfBoundsException.class) public void getAtOutOfBound()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		
		for (int i = 0; i < 100; i++)
		{
			LL.add(i);
		}
		
		LL.getAt(1000);
	}
	
	@Test public void deepCopy()
	{
		LinkedListInterface<Integer> LL = new LinkedList<Integer>(new Integer(1));
		
		LL.remove(1);
		
		for (int i = 0; i < 100; i++)
		{
			LL.add(i);
		}
		
		LinkedListInterface<Integer> LL2 = LL.deepCopy();
		
		
		for (int i = 0; i < 100; i++)
		{
			assertEquals(LL.getAt(i),LL2.getAt(i));
		}
		
		// NOW TEST TO MAKE SURE IT IS A DEEP COPY BY MODIFYING 1 LIST
		
		LL2.remove(10);
		LL2.remove(5);
		
		assertFalse(LL2.contains(10));
		assertFalse(LL2.contains(5));
		
		for (int i = 0; i < 100; i++)
		{
			assertEquals(new Integer(i), LL.getAt(i));
		}
		
		
	}
	
	
	
}
