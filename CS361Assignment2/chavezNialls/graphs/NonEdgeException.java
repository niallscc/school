package graphs;


/**
 * @author tom
 * These should be generated whenever a function which ordinarily
 * should get a pair of adjacent vertices as arguments instead gets
 * non-adjacent vertices as arguments.
 */
public class NonEdgeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NonEdgeException(String desc) {
		super(desc);	
	}


}
