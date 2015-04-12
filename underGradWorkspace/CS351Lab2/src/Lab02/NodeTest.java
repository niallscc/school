package Lab02;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

	@Test
	public void testNode() {
		Node<Integer> n = new Node<Integer>(1);
		
		assertEquals(new Integer(1),n.getData());
		
	}

	@Test
	public void testSetNext() {
		Node<Integer> n1 = new Node<Integer>(1);
		Node<Integer> n2 = new Node<Integer>(5);
		
		assertEquals(new Integer(1), n1.getData());
		assertEquals(new Integer(5), n2.getData());
		
		n1.setNext(n2);
		
		assertEquals(n1.getNext(), n2);
		
		n2.setNext(n1);
		
		assertEquals(n2.getNext(), n1);
		
	}

	@Test
	public void testGetNext() {
		Node<Integer> n1 = new Node<Integer>(1);
		
		assertEquals(null, n1.getNext());
		
		
	}


}
