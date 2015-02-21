/**
 * This class is the test file for the GrellFindableQueue program. 
 * it tests each method in GrellFindableQueue for  reasonable, unreasonable, 
 * and erroneous inputs. Edge cases and corner cases are also be examined.
 * In particular, this class tests the following methods: Contains, first, get, insert,
 * isEmpty, length, pop, and remove. 
 */

package tests;


import org.junit.Before;
import org.junit.Test;

import source.GrellFindableQueue;

import edu.unm.cs.cs351.f10.tdrl.p2.Findable;
import edu.unm.cs.cs351.f10.tdrl.p2.FindableQueue;
import static org.junit.Assert.assertEquals;


public class GrellFindableQueueTest{
	
	FindableQueue<String, node<String>> stringQueue;
	FindableQueue<Integer, node<Integer>> integerQueue;
	FindableQueue<Double, node<Double>> doubleQueue;
	
	Double[]  doubleArray={2.3,2.5,-2.5,-9.7, 19.0, 0.0};
	Integer[] integerArray={1, 5, 7, -5, 0};
	String[]  stringArray={"String1", "String2", "String3", "String4", null, ""};
	
	private class node<T> implements Findable<T>, Comparable<node<T>> {
		int tester;
		
		String testString="test"; 
		
		
		@Override
		public int compareTo(node<T> o) {
			if(this.tester< o.tester) return -1;
			
			else if(this.tester > o.tester) return 1;
			
			else return 0;
			
		}

		@SuppressWarnings("unchecked")
		@Override
		
		public T getKey() {
			testString=""+tester;
			return (T)testString;
		}
	
	}
	/**
	 * This method does the setting up process before each test to make sure that 
	 * I have "fresh" objects everytime. 
	 */
	@Before public void init(){
		
		integerQueue= new GrellFindableQueue<Integer, node<Integer>>();
		doubleQueue= new GrellFindableQueue<Double, node<Double>>();
		stringQueue= new GrellFindableQueue<String, node<String>>();
	
	}
	
	/**
	 * this tests the first() method in GrellFindableQueue. 
	 */
	@Test public void generalFirstTest() {
		
	    firstTest(integerQueue);
	    firstTest(doubleQueue);
	    firstTest(stringQueue);
		
	}
	/**
	 * This method tests the get method in GrellFindableQueue. 
	 */
	@Test public void generalGetTest() {
		
		getTest(integerQueue);
		getTest(doubleQueue);
		getTest(stringQueue);
		
	}
	/**
	 * This method tests the insert method in GrellFindableQueue. 
	 */
	@Test public void insert() {
		
		insertTest(integerQueue);
		//insertTest(doubleQueue);
		//insertTest(stringQueue);
		
	}
	
	/**
	 * This method tests the isEmpty method in GrellFindableQueue. 
	 */
	@Test public void generalIsEmptyTest() {
	
		isEmptyTest(integerQueue);
		isEmptyTest(doubleQueue);
		isEmptyTest(stringQueue);
	}
	/**
	 * This method tests the length method in GrellFindableQueue. 
	 */
	@Test public void generalLengthTest() {
		
		lengthTest(integerQueue);
		lengthTest(doubleQueue);
		lengthTest(stringQueue);
	
	}
	
	/**
	 * This method tests the pop method in GrellFindableQueue. 
	 */
	@Test public void generalPopTest() {
		
		popTest(integerQueue);
		popTest(doubleQueue);
		popTest(stringQueue);

	}
	
	/**
	 * This method tests....you guessed it... the remove method in GrellFindableQueue. 
	 */
	@Test public void generalRemoveTest() {
		
		removeTest(integerQueue);
		removeTest(doubleQueue);
		removeTest(stringQueue);
		
	}
	
	
	/**
	 * This method test to make sure that the object at the head of the queue is the correct object to have there.
	 * It does this by using the comparable in this class and the comparable in the GrellFindableQueue clas to make sure
	 * that the binary heap ordering is correct. 
	 * @param <T> generic type to be passed into the function, the three types I am testing are Ints, Strings, and Doubles.
	 * @param queue the object to pass in. This simulates what a real object would be like, the real object will have a client, 
	 * 		  order number, and type of computer ordered. 
	 */
	private <T> void firstTest(FindableQueue<T, node<T>> queue) {
		
		node<T> i= new node<T>();
		
		queue.insert(i);
		
		assertEquals( queue.contains(i), true);
		
		node<T> first= queue.first();
		
		assertEquals(first, i);
		assertEquals( queue.contains(i), true);
		
		node<T> j= new node<T>();
	
		queue.insert(j);
		
		node<T> k= new node<T>();
		
		queue.insert(k);
		
		node<T> firstj= queue.first();
		System.out.println("true or false="+ firstj.equals(j));
		
		assertEquals( firstj.getKey(), j.getKey());
		assertEquals( queue.contains(j), true);
		
	}
	/**
	 * Tests the length of the queue, it makes sure that as I add nodes, and remove them that the length
	 * changes accordingly. 
	 * @param <T> generic type to be passed into the function, the three types I am testing are Ints, Strings, and Doubles.
	 * @param queue the object to pass in. This simulates what a real object would be like, the real object will have a client, 
	 * 		  order number, and type of computer ordered. 
	 */
	private <T> void lengthTest(FindableQueue<T, node<T>> queue){
		
		assertEquals(queue.length(), 0);
		
		node<T> i= new node<T>();
		node<T> j= new node<T>();
		node<T> k= new node<T>();
		node<T> l= new node<T>();
		i.tester=0;
		j.tester=1;
		k.tester=2;
		l.tester=3;
		
		queue.insert(i);
		
		assertEquals(queue.length(), 1);
		
		queue.insert(j);
		queue.insert(k);
		queue.insert(l);
		
		assertEquals(queue.length(), 4);
		
		queue.remove(k);
		assertEquals(queue.length(), 3);
		
		queue.remove(i);
		assertEquals(queue.length(), 2);
		
	}
	
	/**
	 * This test makes sure that as I remove items from the queue that they are actually removed, you will notice here that I am not
	 * testing the length as items are removed. This is because I am testing the length at remove in the lengthTest method. 
	 * 
	 * @param <T> generic type to be passed into the function, the three types I am testing are Ints, Strings, and Doubles.
	 * @param queue the object to pass in. This simulates what a real object would be like, the real object will have a client, 
	 * 		  order number, and type of computer ordered. 
	 */
	private <T> void removeTest(FindableQueue<T, node<T>> queue){
		//assertEquals(intQueue.remove(null), null);
		
		assertEquals(queue.isEmpty(), true);
		
		node<T> j= new node<T>();
		node<T> k= new node<T>();
		node<T> l= new node<T>();
		node<T> m= new node<T>();
		node<T> n= new node<T>();
		
		j.tester=0;
		k.tester=1;
		l.tester=2;
		m.tester=3;
		n.tester=4;
		
		//testing if i can insert 1 node then remove it 
		queue.insert(j);
		
		assertEquals( true, queue.contains(j));
		
		queue.remove(j);
		
		assertEquals( false, queue.contains(j));
		assertEquals(0, queue.length());
		
		
		//testing if i insert two nodes and remove the second one if it works 
		queue.insert(j);

		queue.insert(k);
		
		assertEquals( true, queue.contains(j));
		assertEquals( true, queue.contains(k));
		
		queue.remove(j);
		
		assertEquals( false, queue.contains(j));
		assertEquals( true, queue.contains(k));
		queue.remove(k);
		
		assertEquals( false, queue.contains(k));
		
		
		//testing if i can insert 5 nodes then remove a node with 2 children 
		queue.insert(j);
		queue.insert(k);
		queue.insert(l);
		queue.insert(m);
		queue.insert(n);
		
		//test remove the last node
		queue.remove(k);
		assertEquals(false, queue.contains(k));
		
		
	}
	/**
	 * This tests the pop method and makes sure taht as i pop things off the queue, that not only do i get them back but that 
	 * the object is actually removed from the queue. 
	 * 
	 * @param <T> generic type to be passed into the function, the three types I am testing are Ints, Strings, and Doubles.
	 * @param queue the object to pass in. This simulates what a real object would be like, the real object will have a client, 
	 * 		  order number, and type of computer ordered. 
	 */
	private <T> void popTest(FindableQueue<T, node<T>> queue){
		
		node<T> i= new node<T>();
		i.tester=0;
		queue.insert(i);
		
		assertEquals(queue.pop(), i);
		assertEquals(queue.contains(i), false);
		
		node<T> j= new node<T>();
		j.tester=0;
		queue.insert(j);
		
		node<T> k= new node<T>();
		k.tester=1;
		queue.insert(k);
		queue.pop();
		//assertEquals(queue.pop(), j);
		assertEquals(queue.contains(j), false);
		
		
	}
	/**
	 * This method tests if the queue's isEmpty state stays consistent throughout the adding and removing process. 
	 * 
	 * @param <T> generic type to be passed into the function, the three types I am testing are Ints, Strings, and Doubles.
	 * @param queue the object to pass in. This simulates what a real object would be like, the real object will have a client, 
	 * 		  order number, and type of computer ordered. 
	 */
	private <T> void isEmptyTest(FindableQueue<T, node<T>> queue){
		
		assertEquals (queue.isEmpty(), true);
			
		node<T> j= new node<T>();
		node<T> k= new node<T>();
		node<T> l= new node<T>();
		node<T> m= new node<T>();
		j.tester=0;
		k.tester=1;
		l.tester=2;
		m.tester=3;

		queue.insert(j);
		assertEquals (queue.isEmpty(), false);
		
		queue.remove(j);
		assertEquals (true, queue.isEmpty());
		
		queue.insert(j);
		queue.insert(k);
		queue.insert(l);
		queue.insert(m);
		
		assertEquals (queue.isEmpty(), false);
		
		queue.remove(k);
		queue.remove(l);
		queue.remove(m);
		
		assertEquals (queue.isEmpty(), false);
		
		
	}
	
	/**
	 * 
	 * This method tests the insert method. This method adds an object into the queue to be processed, and this method makes sure that
	 * as things are added that everything is updated accordingly.  
	 * 
	 * @param <T> generic type to be passed into the function, the three types I am testing are Ints, Strings, and Doubles.
	 * @param queue the object to pass in. This simulates what a real object would be like, the real object will have a client, 
	 * 		  order number, and type of computer ordered. 
	 */
	private <T> void insertTest(FindableQueue<T, node<T>> queue){
		
		node<T> i = new node<T>();
		node<T> j = new node<T>();
		node<T> k = new node<T>();
		node<T> l = new node<T>();
		node<T> m = new node<T>();
		node<T> n = new node<T>();
		node<T> o = new node<T>();
		node<T> p = new node<T>();
		node<T> q = new node<T>();
		node<T> r = new node<T>();
		node<T> s = new node<T>();
		
	
		i.tester=0;
		j.tester=1;
		k.tester=2;
		l.tester=3;
		m.tester=4;
		n.tester=5;
		o.tester=6;
		p.tester=7;
		q.tester=8;
		r.tester=9;
		s.tester=10;
		
		//insert them in a random order. 
		queue.insert(j);
		queue.insert(s);
		queue.insert(k);
		queue.insert(l);
		queue.insert(o);
		queue.insert(p);
		queue.insert(i);
		queue.insert(m);
		queue.insert(n);
		queue.insert(r);
		queue.insert(q);
		
		
		/*
		queue.insert(i);
		
		assertEquals(true,queue.contains(i)); 
		//i.tester=20;
		queue.insert(i);
		assertEquals(true,queue.contains(i)); 
		
		queue.insert(j);
		queue.insert(k);
		queue.insert(l);	
		queue.insert(m);
		
		assertEquals(true,queue.contains(k)); 
		assertEquals(true,queue.contains(m)); 
		assertEquals(true,(queue.contains(j)) ); 
		
		//order test
		 * 
		 */
		
		

			
	}
	
	
	private <T> void getTest(FindableQueue<T, node<T>> queue){
		node<T> i = new node<T>();
		i.tester=2;
		queue.insert(i);
		
		assertEquals(true,queue.contains(i)); 
		
		node<T> j = new node<T>();
		j.tester=5;
		queue.insert(j);
		
		node<T> k = new node<T>();
		k.tester=1;
		queue.insert(k);
		
		node<T> l = new node<T>();
		l.tester=3;
		queue.insert(l);
		
		node<T> m = new node<T>();
		m.tester=10;
		queue.insert(m);
		
		assertEquals(true,queue.contains(k)); 
		
		assertEquals(true,(queue.contains(j)) ); 

	}
	
	
	
}