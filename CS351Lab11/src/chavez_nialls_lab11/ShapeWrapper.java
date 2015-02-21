package chavez_nialls_lab11;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;


public class ShapeWrapper 
{

	public Rectangle2D.Double shape = null;
	AffineTransform at = null;
	
	public ShapeWrapper(Rectangle2D.Double rec)
	{
		shape = rec;
		at = new AffineTransform();
	}
	
	
	public void rotate(double rads)
	{
		at.concatenate( AffineTransform.getRotateInstance(rads, shape.getCenterX(), shape.getCenterY()) );
	}
	
	public AffineTransform getXForm()
	{
		return at;
	}
	
	
}
