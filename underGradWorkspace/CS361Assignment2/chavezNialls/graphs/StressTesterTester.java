package graphs;
import java.awt.Point;

import code.MyStressTester;

/**
 * @author tom
 *
 * A small suite of test cases for your implementation of the StressTester interface.
 * Your code should pass all 24 tests successfully.
 */
public class StressTesterTester {

	public static void runGridTest( StressTester st ) {

		
		int[ ] ans1 = { 85, 55, 49, 61, 89, 59, 93, 49, 45, 69, 97, 49};
		for (int i=0; i<12; i++) {
			long seed = i << 20;
			WeightedGraph<Point> wg = new WeightedGrid2D(100,seed);
			//try {
				int val = st.maxLoad(wg, new Point(0,0), new Point(1,0));
				System.out.println("seed = " + seed + ". maxLoad returned "+ val);
				if (val == ans1[i]) 
					System.out.println("Test " + i + " passed.");
				else
					System.out.println("Test " + i + " failed. Expected " + ans1[i] + ".  Got " + val);
			//} catch (Exception exc) {
			//	System.out.println("Test "+i + " failed.  Exception: " + exc);
			//}
		}
		int[ ] ans2 = { 49, 53, 50, 48, 48, 50, 49, 52, 45, 51, 50, 49};
		for (int i=0; i<12; i++) {
			long seed = i << 20;
			WeightedGraph<Point> wg = new WeightedGrid2D(100,seed);
			//try {
				int val = st.maxLoad(wg, new Point(0,0), new Point(5,5));
				System.out.println("seed = " + seed + ". maxLoad returned "+ val);
				if (val == ans2[i]) 
					System.out.println("Test " + (12 + i) + " passed.");
				else
					System.out.println("Test " + (12 + i) + " failed. Expected " + ans2[i] + ".  Got " + val);
			//} catch (Exception exc) {
			//	System.out.println("Test " + (12 + i) + " failed.  Exception: " + exc);
			//}
		}

	}
	
	/**
	 * A main method so you can run your tests from the command line, if desired.
	 * See comment in source code for what needs to be fixed before this will run.
	 * @param args not used.
	 */
	public static void main( String[] args ) {
		StressTester st = new MyStressTester( );    
		// Replace "MyStressTester" with the class you provide.
		// It should reside in its own, appropriately named, package.
		runGridTest(st);
		
	}
}
