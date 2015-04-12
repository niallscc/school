package game;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import actors.Being;

import gameBoard.FlatMap;
import gui.GameBoard;
import server.Client;
import updates.GladdosUpdate;

/**
 * This class is used for all game specific control for the client. It keeps track of the local client 
 * game loop and timing. It also controls user input
 * 
 * @author Ryan Hammer
 *
 */
public class ClientGameController implements Runnable {
	/**
	 * The WorldState that the client's game is associated with.
	 */
	private ClientWorldState clientWorldState;
	/**
	 * The id for this client.
	 */
	private int clientID;
	/**
	 * Flags to continue running the controller.
	 */
	private boolean running = true;
	/**
	 * The GameBoard that this controller...controls.
	 */
	private GameBoard gameBoard;
	/**
	 * The Client object that this controller is associated with.
	 */
	private Client client;
	/**
	 * Flags whether the client received an update from the server.
	 */
	private boolean updateDisplay = true;
	/**
	 * The cells that are selected.
	 */
	private ArrayList<FlatMap> selectedCells= new ArrayList<FlatMap>();
	/**
	 * Creates a ClientGameController with the associated Client object. Initializes all of the game stuff.
	 * @param cl The client Object
	 */
	public ClientGameController(Client cl) {
		clientWorldState = new ClientWorldState(this);
		client = cl;

		gameBoard = null;
		try {
			gameBoard = new GameBoard(client);
			gameBoard.setMagicBoard();
		} catch (IOException e) {

		}
		Thread t = new Thread(this);
		t.start();
	}
	
	/**
	 * Setter for the client id
	 * @param The id to set it to
	 */
	public void setClientID(int id) {
		clientID = id;
	}
	/**
	 * Getter for the clientID
	 * @return The clientID
	 */
	public int getClientID() {
		return clientID;
	}
	/**
	 * Getter for the running state of the game.
	 * @return True or false if it's running.
	 */
	public boolean isRunning() {
		return running;
	}
	/**
	 * Getter for the ClientWorldState object
	 * @return The ClientWorldState object
	 */
	public ClientWorldState getWorldState() {
		return clientWorldState;
	}
	/**
	 * Used to control the game display time to the client.
	 * @param millis The time to delay by.
	 */
	public void delay(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			System.err.println("InterruptedException while delaying");
		}
	}
	/**
	 * Returns the location of the PC object of the player.
	 * @return The FlatMap location
	 */
	public FlatMap getPCLocation() {
		return getPC().getLocation();
	}
	/**
	 * Getter for the PC object
	 * @return The PC object of the player.
	 */
	public Being getPC() {
		int mapLocation = getWorldState().getMapLocation(clientID);
		return getWorldState().getMapBeings(mapLocation).get(""+clientID);
	}
	/**
	 * Receives Updates from the Client object and uses them to update the game
	 * @return The update from the client which it received from the server.
	 */
	public void processUpdate(GladdosUpdate gameUpdate) {
		gameUpdate.execute(clientWorldState);
		updateDisplay = true;
	}
	/**
	 * Setter for whether to update the display or not.
	 * @param value True or false to update the display.
	 */
	public void setUpdateDisplay(boolean value) {
		updateDisplay = value;
	}
	/**
	 * Getter for the gameboard
	 * @return The gameboard
	 */
	public GameBoard getGameBoard() {
		return this.gameBoard;
	}
	/**
	 * The overriden run method that implements the client side game loop. It initializes everything
	 * and then starts the game loop. It draws everything if updateDisplay is set to true.
	 */
	@Override
	public void run() {
		Graphics graphicsInstance = gameBoard.getBoardGraphics();
		
		//Wait for the server to initialize the worldState
		while(!this.getWorldState().isInitialized()){ }
		delay(4000); // Give the client time to receive and send the new game stuff.
		
		//Get the map that the player is on.
		int mapLocation = this.clientWorldState.getMapLocation(this.clientID);
		
		//Draw everything right off the bat.
		getWorldState().getWorldMap(mapLocation).drawMap(graphicsInstance);
		
		while(running) {

			//delay(100);
			if(updateDisplay) {
				if(!this.getPC().isAlive()) {
					running = false;

					JOptionPane.showMessageDialog(gameBoard, "You have been killed!");	

				}
				
				//Get the map that the player is on.
				mapLocation = this.clientWorldState.getMapLocation(this.clientID);
				
				//Draw the map
				getWorldState().getWorldMap(mapLocation).drawMap(graphicsInstance);
				gameBoard.getMagicBoard().paintAreaOfEffect(graphicsInstance, gameBoard.getSelectedMagic(), this.getPC(), this.getPCLocation());
				
				gameBoard.updateStats(graphicsInstance);
				
				gameBoard.getMagicBoard().paintMagicBoard(graphicsInstance, 
						gameBoard.getSelectedMagic(), this.getPC());
				
				gameBoard.setUpdatedCharacterPos(false);
				updateDisplay = false;
			}
			if(gameBoard.updateRadius()){
				gameBoard.getMagicBoard().paintAreaOfEffect(graphicsInstance, gameBoard.getSelectedMagic(), this.getPC(), this.getPCLocation());
				gameBoard.setPaintRadius(false);
			}
			
			if( gameBoard.doMagicAttack()){
				
				if( gameBoard.getMagicBoard().isSelectable(gameBoard.getSelectedMagic(), this.getPC())){
					for(FlatMap loc: gameBoard.getMagicBoard().getAreaOfEffect(gameBoard.getSelectedMagic(),this.getPC())){
						//System.out.println("Cell clicked at "+ gameBoard.getClickedCell().getX()*25 +" "+gameBoard.getClickedCell().getY()*25 );
						//System.out.println("FlatMap location: "+ (loc.getX()+(this.getPCLocation().getX()*25))+" "+((loc.getY()+this.getPCLocation().getY()*25) ));
						
						if( gameBoard.getClickedCell().getX()*25==(loc.getX()+(this.getPCLocation().getX()*25) ) && gameBoard.getClickedCell().getY()*25==((loc.getY()+this.getPCLocation().getY()*25) ) )
							gameBoard.getMagicBoard().paintMagicAttackCell(graphicsInstance, gameBoard.getSelectedMagic(), this.getPC(), gameBoard.getClickedCell());
					}					
				}
				else if( gameBoard.getMagicBoard().isRotateable(gameBoard.getSelectedMagic(), this.getPC())){
					
					
					gameBoard.getMagicBoard().paintMagicAttackLine(graphicsInstance, gameBoard.getSelectedMagic(), this.getPC(),this.getPCLocation(),selectedCells);
					selectedCells=new ArrayList<FlatMap>();
				}
				else
					gameBoard.getMagicBoard().paintMagicAttackRadius(graphicsInstance, gameBoard.getSelectedMagic(), this.getPC(),this.getPCLocation());
			
				
				gameBoard.setPerformMagicAttack(false);
			}
			
			if( gameBoard.shouldHighlight()){
				
				if( gameBoard.getMagicBoard().isSelectable(gameBoard.getSelectedMagic(), this.getPC())){
					for(FlatMap loc: gameBoard.getMagicBoard().getAreaOfEffect(gameBoard.getSelectedMagic(),this.getPC())){
						//System.out.println("Cell clicked at "+ gameBoard.getClickedCell().getX()*25 +" "+gameBoard.getClickedCell().getY()*25 );
						//System.out.println("FlatMap location: "+ (loc.getX()+(this.getPCLocation().getX()*25))+" "+((loc.getY()+this.getPCLocation().getY()*25) ));
						
						if( gameBoard.getClickedCell().getX()*25==(loc.getX()+(this.getPCLocation().getX()*25) ) && gameBoard.getClickedCell().getY()*25==((loc.getY()+this.getPCLocation().getY()*25) ) )
							gameBoard.highlightCell(graphicsInstance);
					}
				}
				gameBoard.setHighlightedCell(false);
			}
			/*
			 * This if statement determines wheter or not when WASD are pressed what to do with it
			 * so if you can rotate the attack then it fills the selected area with red 
			 */
			if(gameBoard.isDirChanged()){
				if(gameBoard.getMagicBoard().isRotateable(gameBoard.getSelectedMagic(), this.getPC())){
					//System.out.println("IS rotateable!");
					//LEFT
					/*
					 * These loops go through the total area of effect and get the applicable cells for each direction
					 * 
					 */
					selectedCells=new ArrayList<FlatMap>();
					if( gameBoard.getAttDir()==0)
					{	
						for(FlatMap cell: gameBoard.getMagicBoard().getAreaOfEffect(gameBoard.getSelectedMagic(), this.getPC())){
							if( cell.getX()<0 && cell.getY()==0)
								selectedCells.add(cell);
							
							gameBoard.attackDir(graphicsInstance,selectedCells);
						}
						
					}
					//UP
					else if(gameBoard.getAttDir()==1)
					{
						for(FlatMap cell: gameBoard.getMagicBoard().getAreaOfEffect(gameBoard.getSelectedMagic(), this.getPC())){
							if( cell.getX()==0 && cell.getY()<0)
								selectedCells.add(cell);
							
							gameBoard.attackDir(graphicsInstance,selectedCells);
						}
					}
					//RIGHT
					else if(gameBoard.getAttDir()==2)
					{
						for(FlatMap cell: gameBoard.getMagicBoard().getAreaOfEffect(gameBoard.getSelectedMagic(), this.getPC())){
							if( cell.getX()>0 && cell.getY()==0)
								selectedCells.add(cell);
							
							gameBoard.attackDir(graphicsInstance,selectedCells);
						}
					}
					//DOWN
					else if(gameBoard.getAttDir()==3)
					{
						for(FlatMap cell: gameBoard.getMagicBoard().getAreaOfEffect(gameBoard.getSelectedMagic(), this.getPC())){
							if( cell.getX()==0 && cell.getY()>0)
								selectedCells.add(cell);
							
							gameBoard.attackDir(graphicsInstance,selectedCells);
						}
					}			
					gameBoard.setDirChanged(false);
				}
			}
			if( gameBoard.swordAttack()){
				//System.out.println("Doing sword Attack!");
				gameBoard.getMagicBoard().paintSwordIcon(graphicsInstance, this.getPCLocation(), gameBoard.getAttDir());
				gameBoard.setSwordAttack(false);
			}
			//Now draw the magicBar
			if( gameBoard.isMagicBarChanged()){
				gameBoard.getMagicBoard().paintMagicBoard(graphicsInstance, 
						gameBoard.getSelectedMagic(), this.getPC());
				gameBoard.setMagicBarChanged(false);
			}

			//Now update the stats
			if(gameBoard.isStatusBarChanged()){
				
				gameBoard.updateStats(graphicsInstance);
				gameBoard.setStatusBarChanged(false);

			}
		}

	}
}
