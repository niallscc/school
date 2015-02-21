package chavez_nialls;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test; 
public class linkedListTesterCase {
	
	static LinkedList<Integer> ll =new LinkedList<Integer>(2);
	int counter=0;
	@BeforeClass public static void setUp(){
		ll.add(new Integer(3));
	}
	@Before public  void addTest()
	{
		counter++;
		ll.add(new Integer(counter));
	}
	
	@Test public void removeTester(){
		ll.remove(new Integer(counter));
		assertEquals(ll.contains(counter),false);
	}
	@Test public void addTester(){
		ll= new LinkedList<Integer>(1);	
		ll.add(new Integer(5));
		//assertEquals(5,5);
		System.out.println("Testing add functionality\n");
	}
	
	@Test(expected= FileNotFoundException.class)
	public void testException() 
	{
		
		@SuppressWarnings("unused")
		
		FileInputStream f;
		try {
			//This test Will fail 
			f = new FileInputStream(new File("testFile.txt"));
			//String myText= f.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println("Testing fileNotFoundException\n");
	}
	@Test public void containsTester(){
		ll.contains(new Integer(5));
		assertEquals(ll.contains(new Integer(5)),true);
	}
	@Test public void getHeadTester()
	{
		assertEquals(ll.getHead(), new Integer(1));
	}

}

