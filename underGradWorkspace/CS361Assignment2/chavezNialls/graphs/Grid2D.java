/**
 * 
 */
package graphs;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tom
 * An implementation of the 2-dimensional square grid graph.
 * There is one vertex at each integer point (i,j) in the plane.
 * Two vertices are adjacent if they agree in one coordinate, and
 * differ by one in the other coordinate.  Thus, each vertex 
 * has 4 neighbors: up, down, left and right.
 * 
 * This graph is represented implicitly, rather than as an adjacency
 * list.  (Necessary, since the vertex set has size 2^64.)
 */
public class Grid2D implements Graph<Point> {

	@Override
	public boolean isVertex( Point v ) {
		return true;  // all Points are vertices.
	}

	@Override
	public Set<Point> neighbors(Point v) {
		HashSet<Point> rv = new HashSet<Point>( );
		rv.add(new Point(v.x-1,v.y));
		rv.add(new Point(v.x+1,v.y));
		rv.add(new Point(v.x,v.y-1));
		rv.add(new Point(v.x,v.y+1));
		return rv;
	}

	@Override
	public boolean isEdge(Point v, Point w) {
		int dx = v.x - w.x;
		int dy = v.y - w.y;
		return (Math.abs(dx) + Math.abs(dy) == 1);
	}

}
