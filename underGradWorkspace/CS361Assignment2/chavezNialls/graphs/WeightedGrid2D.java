/**
 * 
 */
package graphs;
import java.awt.Point;
import java.util.Random;

/**
 * @author tom
 *
 * An implementation of the 2-dimensional square grid graph,
 * with pseudo-random edge weights.
 * There is one vertex at each integer point (i,j) in the plane.
 * Two vertices are adjacent if they agree in one coordinate, and
 * differ by one in the other coordinate.  Thus, each vertex 
 * has 4 neighbors: up, down, left and right.
 * 
 * This graph is represented implicitly, rather than as an adjacency
 * list.  (Necessary, since the vertex set has size 2^64.)
 * 
 * How are the edge weights stored implicitly?  By using a 
 * pseudorandom generator.  The function edgeToLong converts each
 * edge into an integer, which can be passed to the pseudorandom
 * generator to produce a random-looking, but reproduceable value
 * for the edge weight.
 */
public class WeightedGrid2D extends Grid2D implements WeightedGraph<Point> {

	private Random random;
	private long baseSeed;
	private int maxWt;
	
	private long edgeToLong(Point p, Point q) throws NonEdgeException {
		int dx = p.x - q.x;
		int dy = p.y - q.y;
		Point basePt;
		if ((dx==0 || dy==0) && (dx + dy == 1))
			basePt = q;
		else if ((dx==0 || dy==0) && (dx + dy == -1))
			basePt = p;
		else
			throw new NonEdgeException("dx = "+dx + " dy = "+dy + "\n");
		return Math.abs(dx) + 2 * pointToLong( basePt );
	}

	private long pointToLong(Point p) {
		return p.y + Integer.MAX_VALUE * (long) p.x; 
	}

	
	/**
	 * Create a new WeightedGrid2D object, with pseudorandom edge weights
	 * in the range [0, maxWt - 1].
	 * @param maxWt 1 more than the maximum edge weight.
	 * @param seed the seed for the pseudorandom generator.
	 */
	public WeightedGrid2D(int maxWt, long seed) {
		this.maxWt = maxWt;
		baseSeed = seed;
		random = new Random(seed);
	}
	
	@Override
	public int getWeight(Point v, Point w) throws NonEdgeException {
		random.setSeed(baseSeed + edgeToLong(v, w));
		return random.nextInt(maxWt);
	}

}
