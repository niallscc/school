/**
 * 
 */
package maze;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author tom
 *
 */
public class MazeRunner {

	static Maze<Point> buildMaze ( InputStream is ) throws InvalidInputException, IOException {	
		BasicGraphBuilder<Point> bgb = new BasicGraphBuilder<Point>( );
		BufferedReader br = new BufferedReader(new InputStreamReader( is ));
		Point start = null, finish = null;
		
		String line = br.readLine( );   // can throw IOException
		int row = 0;
		int ncols = line.length( );
		while (line != null) {
			if (line.length( ) != ncols)
				throw new InvalidInputException("not a rectangle");
			for (int col = 0; col < ncols; col++) {
				if (line.charAt(col)=='.' || line.charAt(col)=='S' || line.charAt(col)=='F') {
					// this character corresponds to an occupiable space.
					Point here = new Point( row, col );
					bgb.addVertex(here);	
					if (line.charAt(col)=='S')
						if (start==null)
							start = here;
						else throw new InvalidInputException("2 starts detected.");
					else if (line.charAt(col)=='F')
						if (finish==null)
							finish = here;
						else throw new InvalidInputException("2 finishes detected.");
					// try to connect north and west.  If east and south exist, they will be hit later.
					Point oneWest = new Point( row, col-1 );
					Point oneNorth = new Point( row-1, col );
					if (bgb.hasVertex(oneWest))   // check is needed.  If the location is legit, it was already added.
						bgb.addEdge(here,oneWest);
					if (bgb.hasVertex(oneNorth))  // check is needed (see above)
						bgb.addEdge(here,oneNorth);
					// ways locations can fail to be legit: row-1 or col-1 is -1    OR   char at neighbor is # (wall).
				}
			}
			line = br.readLine( );   // can throw IOException
			row++;
		}
		if (start==null || finish==null)
			throw new InvalidInputException("Missing start or finish character.");
		BasicMaze<Point> rv = new BasicMaze<Point> ( bgb.toGraph( ) );
		rv.setStart(start);
		rv.setFinish(finish);
		return rv;		
	}

	static String routeToExit (Maze<Point> theMaze) {
		RootedTree<Point> rt = GraphAlgs.bFS(theMaze.getGraph( ), theMaze.getStart( ));
		Point cur = theMaze.getFinish( );
		if (rt.parentOf(cur) == null)
			return "No exit route.";    // note: this is NOT an exceptional condition.
		StringBuilder output = new StringBuilder( );
		while (rt.parentOf(cur) != null) {
			Point next = rt.parentOf(cur);
			if (next.x == cur.x - 1)
				output.append("S");
			else if (next.x == cur.x + 1) 
				output.append("N");
			else if (next.y == cur.y - 1)
				output.append("E");
			else if (next.y == cur.y + 1)
				output.append("W");
			else
				assert false : output.toString( );   // shouldn't reach here.
			cur = next;
		}
		return output.reverse().toString();   // need to reverse, since we walked back from F to S.
		// we could have avoided this by doing our BFS from finish, rather than from start.
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws InvalidInputException, IOException {
		InputStream istrm = null;
		for (int i=0; i<args.length; i++) {
			if (args[i].equals("-f") && i+1 < args.length) {
				try {
					istrm = new FileInputStream(args[i+1]);
				} catch (FileNotFoundException e) {
					System.out.println("File " + args[i+1] + " not found.");
				}
				break;
			}
		}
		if (istrm == null) {
			System.out.println("Please enter your maze below, row by row.  (ctrl-D when done)");
			istrm = System.in;
		}
		Maze<Point> maze = buildMaze(istrm);
		System.out.println(routeToExit(maze));
	}

}
