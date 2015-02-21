package chavez_nialls_lab11;

import java.awt.geom.AffineTransform;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class Render {

	private Lab11 pane;
	
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      // All of this stuff happens on the AWT event processing thread
      //@Override
      public void run() {
        new Render();
      }
    });
    
 
    
  }

   
  /* ******************** end of public stuff ******************** */

  public Render() {
    _masterFrame=new JFrame("RenderDemo");
    _masterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    _masterFrame.add(_buildDemoPanel());
    _masterFrame.pack();
    _masterFrame.setVisible(true);  
    //TODO Start new rotate Thread
    new Thread(new Rotate(pane)).start();
   
    
  }
  
  private JComponent _buildDemoPanel() 
  {
	  
	pane = new Lab11();	
	JPanel jp = new JPanel();
    jp.add(pane);
    return jp;
    
  }
  
  private final JFrame _masterFrame;
}