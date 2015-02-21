package Lab14;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class Smiley implements Stuff
{

	@Override
	public void paint(Graphics2D g2D) {
		
		g2D.draw(new Ellipse2D.Double(0,0,100,100));
		g2D.draw(new Arc2D.Double(new Rectangle2D.Double(15,30,70,50), 180, 180, Arc2D.OPEN));
		g2D.fill(new Ellipse2D.Double(25,20,10,10));
		g2D.fill(new Ellipse2D.Double(65,20,10,10));
		
		
	}

}
