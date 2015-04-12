package gameBoard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import actors.Being;

public class MagicBoard {
	
	public void paintAreaOfEffect(Graphics g, int selectedMagic, Being b, FlatMap pos){
		List<Magic> availableMagic= b.getMagic();
		
		int counter=0;
		for( Magic m: availableMagic){
			if(counter==selectedMagic){
				for( FlatMap loc: m.getAttackArea()){
					//System.out.println("Radius is:"+ m.getAttackArea().toString());
					g.setColor(new Color(0,0,200,100));//blue transparency 
					g.fillRect(loc.getX()+(pos.getX()*25),loc.getY()+(pos.getY()*25) ,25,25);					
				}
			}
			counter++;
		}
	
	}
	public ArrayList<FlatMap> getAreaOfEffect(int selectedMagic, Being b){
		List<Magic> availableMagic= b.getMagic();
		
		int counter=0;
		for( Magic m: availableMagic){
			if(counter==selectedMagic){
				return m.getAttackArea();
			}
			counter++;
		}
		return null;
	}
	
	public void paintMagicBoard(Graphics g, int selectedAttack, Being b){
		
		g.setColor(Color.DARK_GRAY);
		g.fill3DRect(05, 600, 520, 50,true);
		
		//System.out.println("The being is: "+ b);
		List<Magic> availableMagic= b.getMagic();
		//System.out.println("the availableMagic is: "+ availableMagic.toString());
		int xPosition=10;
		int number=0;
		
		for(Magic m: availableMagic){
			
			if( number==selectedAttack){
				g.setColor(Color.RED);
				g.drawRect(xPosition-5, 605,40,40 );
			
			}
			g.drawImage(m.getImage(), xPosition, 610, null);
			g.setColor(Color.WHITE);
			g.drawString(m.getName(), xPosition, 660);
			xPosition+=90;
			number++;
		}
		
	}
	public String getSelectedMagic(int selectedIndex, Being b){
		int counter=0;
		List<Magic> availableMagic=b.getMagic();
		for( Magic m: availableMagic){
			if(counter== selectedIndex )
				return m.getName();
			counter++;
				
		}
		return null;
	}
	
	/**
	 * Determines whether or not the selected magic attack is able to be selected, so like if 
	 * you are able to click on a cell in the radius of effect
	 * @param selectedIndex the index of the magic attack 
	 * @param b the pc character
	 * @return true or false depending on what magic you are using
	 */
	
	public boolean isSelectable(int selectedIndex, Being b){
		int counter=0;
		List<Magic> availableMagic=b.getMagic();
		for( Magic m: availableMagic){
			if(counter== selectedIndex )
				return m.selectable();
			counter++;
				
		}
		return false;
	}
	
	
	public boolean isRotateable(int selectedIndex, Being b){
		int counter=0;
		List<Magic> availableMagic=b.getMagic();
		for( Magic m: availableMagic){
			if(counter== selectedIndex )
				return m.rotateable();
			counter++;
				
		}
		return false;
	}
	
	
	/**
	 * This method paints the attack icon in the selected cell so if you are allowed to select a cell it will paint the attack in that cell for 1 second
	 * @param g Graphics 
	 * @param selectedAttack integer representation of the selected magic attack
	 * @param b the player character
	 * @param selectedCell the cell that you want to perform the attack on
	 */
	public void paintMagicAttackCell(Graphics g, int selectedAttack, Being b, FlatMap cell ){
		int counter=0;
		
		List<Magic> availableMagic=b.getMagic();
		for( Magic m: availableMagic){
			if(counter== selectedAttack )
					g.drawImage( m.getImage(), cell.getX()*25, cell.getY()*25, 25,25, null);
			counter++;
		}
		
	}
	/**
	 * this method paints the attack on a line of selected cells 
	 * 
	 * @param g graphics reference to gameBoard
	 * @param selectedAttack the numeric value of the selected magic attack
	 * @param b the being doing the cast
	 * @param cells the cells to perform the magic on 
	 */
	public void paintMagicAttackLine(Graphics g, int selectedAttack, Being b, FlatMap playerLoc, ArrayList<FlatMap>cells ){
		int counter=0;
		
		List<Magic> availableMagic=b.getMagic();
		for( Magic m: availableMagic){
			if(counter== selectedAttack )
					for( FlatMap cell: cells)
						g.drawImage( m.getImage(),(playerLoc.getX()*25 +cell.getX()), (playerLoc.getY()*25 +cell.getY()), 25,25, null);
			counter++;
		}
		
	}
	
	
	/**
	 * This method paints the attack in the whole range of effect if you are not allowed to select a single cell to attack 
	 * @param g Graphics
	 * @param selectedAttack the magic attack
	 * @param b the player Character
	 */
	public void paintMagicAttackRadius(Graphics g, int selectedAttack, Being b, FlatMap playerLoc){
		int counter=0;
		List<Magic> availableMagic=b.getMagic();
		for( Magic m: availableMagic){
			if(counter== selectedAttack ){
				
				double startTime =System.currentTimeMillis();
				double currentTime=System.currentTimeMillis();

				while((currentTime-startTime) <=2.0){

					for(FlatMap loc: m.getAttackArea()){
				
						g.drawImage( m.getImage(),loc.getX()+(playerLoc.getX()*25),loc.getY()+(playerLoc.getY()*25) ,25,25, null);
					
					}
				currentTime=System.currentTimeMillis();
				}
			}
			counter++;
				
		}
	}
	
	/**
	 * The location that you pass this 
	 * method is actually the location you want to paint the sword at.
	 * 
	 * 
	 */
	public void paintSwordIcon(Graphics g, FlatMap playerLoc, int direction){
		
		if( direction==0)
		{
			//left
			try {
				Image left= (Image)ImageIO.read(new File("./AttackIcons/SwordOverlayLeft.png")).getScaledInstance(25, 25, 0);
				g.drawImage(left,(playerLoc.getX()*25)-25, (playerLoc.getY()*25), null );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			
			
		}
		else if( direction==1){
			//Up
			System.out.println("UpSword!");
			try {
				Image up= (Image)ImageIO.read(new File("./AttackIcons/SwordOverlayUp.png")).getScaledInstance(25, 25, 0);
				g.drawImage(up,(playerLoc.getX()*25), (playerLoc.getY()*25)-25, null );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		else if( direction==2){
			//Right
			System.out.println("RightSword!");
			try {
				Image right= (Image)ImageIO.read(new File("./AttackIcons/SwordOverlayRight.png")).getScaledInstance(25, 25, 0);
				g.drawImage(right,(playerLoc.getX()*25)+25, playerLoc.getY()*25, null );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		else if( direction==3){
			//Down
			System.out.println("DownSword!");
			try {
				Image down= (Image)ImageIO.read(new File("./AttackIcons/SwordOverlayDown.png")).getScaledInstance(25, 25, 0);
				g.drawImage(down,playerLoc.getX()*25, (playerLoc.getY()*25)+25, null );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		
	}
	/**
	 * this method returns whether or not the cell you clicked on is in the range of selectable attacks
	 * 
	 * @param cell the clicked cell
	 * @param selectedAttack the selected magic attack
	 * @param b the being who called the attack
	 * @return true or false depending on the validity of the attack
	 */
	public boolean isClickedCellValid(FlatMap cell, int selectedAttack, Being b, FlatMap playerLoc){
		int counter=0;
		
		List<Magic> availableMagic=b.getMagic();
		FlatMap realLoc= new FlatMap( cell.getX()*25, cell.getY()*25);
		for( Magic m: availableMagic){
			if(counter == selectedAttack ){
				for( FlatMap locations: m.getAttackArea()){
					FlatMap checkLocations= new FlatMap(locations.getX()+playerLoc.getX()*25, locations.getY()+playerLoc.getY()*25);
					if( checkLocations==realLoc)
						return true;
				}
			}
			else
				counter++;
		}
		return false;
	}
}
