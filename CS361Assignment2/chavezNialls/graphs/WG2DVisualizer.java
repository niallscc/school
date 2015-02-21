package graphs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class WG2DVisualizer extends JFrame {

	class VisPanel extends JPanel implements ChangeListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		WG2DVisualizer parent;
		WeightedGrid2D graph;
		int minCapacity = 0;
		Point center = new Point(0,0);
		int halfWidthInPoints = 5;		
		
		public VisPanel ( WG2DVisualizer parent ) {
			this.parent = parent;
			graph = parent.graph;
		}
		
		@Override
		public void paint (Graphics g) {
			Graphics2D gg = (Graphics2D) g;
			int w = this.getWidth( );
			int h = this.getHeight( );
			// DrawingFrame
			gg.setColor(Color.GRAY);
			gg.fillRect(0,0,w,h);
			gg.setColor(Color.WHITE);
			gg.fillRect(w/10,h/10,8*w/10,8*h/10);
			gg.setColor(Color.RED);
			gg.fillOval(w/2-6,h/2-6,12,12);
			gg.setColor(Color.BLACK);
			gg.drawRect(w/10,h/10,8*w/10,8*h/10);
			for (int relX = 1-halfWidthInPoints; relX <= halfWidthInPoints; relX++) {
				int screenX = w/2 + 2*w*relX/5/halfWidthInPoints;
				try {
					for (int relY = -halfWidthInPoints; relY < halfWidthInPoints; relY++) {
						int screenY = h/2 - 2*h*relY/5/halfWidthInPoints;
						gg.fillOval(screenX-2, screenY-2, 4, 4);
						Point thisPoint = new Point(center);
						thisPoint.translate(relX,relY);
						Point upPoint = new Point(thisPoint);
						upPoint.translate(0,1);
						// System.out.println("" + thisPoint + "u " + upPoint);
						int upVal = graph.getWeight(thisPoint,upPoint);
						gg.drawString(""+upVal, screenX, screenY-h/(4*halfWidthInPoints));
						if (upVal >= minCapacity) {
							gg.drawLine(screenX, screenY, screenX, screenY - 2*h/5/halfWidthInPoints);
						}
						Point leftPoint = new Point(thisPoint);
						leftPoint.translate(-1,0);
						// System.out.println("" + thisPoint + "l " + leftPoint);
						int leftVal = graph.getWeight(thisPoint,leftPoint);
						gg.drawString(""+leftVal, screenX - w/(4*halfWidthInPoints), screenY);
						if (leftVal >= minCapacity) {
							gg.drawLine(screenX - 2*w/5/halfWidthInPoints, screenY, screenX, screenY);
						}
					}
				} catch (NonEdgeException nee) {
					System.out.println(nee);
					assert(false);
				}
			}
		}		
		
		@Override
		public void stateChanged( ChangeEvent e ) {
			JSlider which = (JSlider)e.getSource( );
			if (which == parent.minWtSlider) {
				minCapacity = which.getValue( );
			} else if (which == parent.widthSlider){
				halfWidthInPoints = which.getValue( )/2;
			}
			invalidate ( );
			repaint ( );
		}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	VisPanel thePanel;
	JSlider minWtSlider, widthSlider;
	WeightedGrid2D graph;
	
	public WG2DVisualizer ( WeightedGrid2D g ) {
		graph = g;
		setLayout(new BorderLayout( ));
		thePanel = new VisPanel( this );
		add ( thePanel, BorderLayout.CENTER ); 
		minWtSlider = new JSlider( 0, 100, 0 );
		minWtSlider.setLabelTable(minWtSlider.createStandardLabels(10));
		minWtSlider.setPaintLabels( true );
		minWtSlider.addChangeListener ( thePanel );
		minWtSlider.setOrientation( SwingConstants.VERTICAL );
		add( minWtSlider, BorderLayout.WEST );
		widthSlider = new JSlider(5,25,11);
		widthSlider.setLabelTable(widthSlider.createStandardLabels(2));
		widthSlider.setPaintLabels( true );
		widthSlider.addChangeListener ( thePanel );
		widthSlider.setOrientation( SwingConstants.VERTICAL );
		add( widthSlider, BorderLayout.EAST );
		JLabel instructions = new JLabel( "Left slider sets a minimum capacity.  Edges of this capacity or more will be drawn.\n" +
				"Right slider controls the width and height of the displayed portion of the grid.  The red vertex at center is (0,0).", SwingConstants.CENTER );
		add( instructions, BorderLayout.SOUTH );
		this.pack( );
		this.setVisible( true );
	}
		
	
	public static void doItAll( ) {
		@SuppressWarnings("unused")
		WG2DVisualizer vis = new WG2DVisualizer ( new WeightedGrid2D (100, 0L));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println( "Welcome.  About to run tests.");
		doItAll( );
	}

}
