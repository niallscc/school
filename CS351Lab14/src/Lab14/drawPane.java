package Lab14;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JPanel;


public class drawPane extends JPanel
{
	
	ArrayList<Stuff> ObjList;
	
	public drawPane(LayoutManager lm, ArrayList<Stuff> objectList)
	{
		super(lm);
		ObjList = objectList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void setObj(ArrayList<Stuff> s)
	{
		ObjList = s;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		for (Stuff s : ObjList)
		{
			s.paint(g2d);
		}
		
	}
	

}
