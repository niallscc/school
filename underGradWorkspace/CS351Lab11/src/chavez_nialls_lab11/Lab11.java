package chavez_nialls_lab11;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;


public class Lab11 extends JComponent {
	
	BufferedImage img = null;
	
	ShapeWrapper shape;
	public Lab11() {
    // Make sure that parent constructor is called
		super();
		shape=new ShapeWrapper(new Rectangle2D.Double(70,70,30,30));
		setPreferredSize(new Dimension(200,200));	
		loadImg();
	}

  /* ******************** end of public stuff ******************** */

	@Override
	protected void paintComponent(Graphics g) {
		if (!(g instanceof Graphics2D)) {
			throw new IllegalArgumentException("Really expected a Graphics2D " +
			"drawing surface here...");
		}
    
		Graphics2D canvas=(Graphics2D)g;

		canvas.setColor(getBackground());
		canvas.fillRect(0,0,getWidth(),getHeight());
		
		canvas.drawImage(img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT), 0, 0, null);
   
		canvas.setColor(Color.BLUE);
		canvas.setStroke(new BasicStroke(10));
		
		
		//TODO transform the shape with createTransformShape()	
		Shape atf= shape.at.createTransformedShape(shape.shape);
		
		//TODO Draw the Object		
		canvas.draw(atf);
		
	}
	
	public void loadImg()
	{
			try {
			    img = ImageIO.read(new File("./Lab11/csBackground.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}

}
