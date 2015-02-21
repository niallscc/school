package gameBoard;

import java.awt.Graphics;
/**
 * This class is the class that does the painting for the items on  the gameboard. 
 * @author niallschavez
 *
 */
public class BoardElements {
	ItemMap iMap=null;
	
	public BoardElements(ItemMap i){
		iMap=i;
	}
	
	
	public void paint(Graphics g ){
		
		for( ItemsCell[] c: iMap.getMap()){
			for(ItemsCell c2: c){
				if( c2!=null){
					g.drawImage(c2.getImage(), c2.getCoordinates().getX()*25, 
								c2.getCoordinates().getY()*25, null);
				}
			}
		}
	}
	
}
