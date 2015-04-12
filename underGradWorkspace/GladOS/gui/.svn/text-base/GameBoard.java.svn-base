package gui;
import gameBoard.CharMap;
import gameBoard.FlatMap;
import gameBoard.ItemMap;
import gameBoard.Items;
import gameBoard.ItemsCell;
import gameBoard.Player;
import gameBoard.Terrain;
import gameBoard.TerrainCell;
import gameBoard.TerrainMap;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class GameBoard extends JFrame{
	/*
	 * MAP KEY: 
	 * 	g: grass
	 *  w: water
	 *  s: sand 
	 *  p: swamp
	 *  m: mountain
	 */
	
	
	/*
	 * Frame stuff
	 */
    private JMenuItem tabComponentsItem;
    private JMenuItem scrollLayoutItem;

	
	/*
	 * Player Stats
	 */
	private Player playerObj;
	private int level;
	private int speed; 
	private int hp;
	private int maxhp;
	private int maxMP;
	private int MP;
	
	private int strength;
	private int magic;
	private String playerClass;
	private String name;

	
	/*
	 * Environment stuff 
	 */
	private BufferedImage characterIMG;
	private Image scaledZombie1;
	private Image scaledGold1;
	private Image scaledTreasureChest;
	private Image scaledSlime1;
	private Image scaledCharacter;
	/*
	 * File Readers 
	 */
	private BufferedReader inputTerrain;
	private BufferedReader inputThings;
	
	/*
	 * Random Global stuff
	 */
	
	public BoardPanel bp;
	//This is a flag that gets set by the key listeners 
	private boolean updateBoard=false;
	private boolean updateStat=false;
	
	private int currentX=0;
	private int currentY=0;
	private double radianRotate=1;
	
	private TerrainMap tMap= new TerrainMap(20,20);
	private CharMap cMap= new CharMap(20,20);
	private ItemMap iMap= new ItemMap(20,20);
	
	
	public GameBoard() throws IOException, ClassNotFoundException{

		this.setTitle("GladOS game");
		this.setResizable(false);
		this.setSize(500,710);
		
		loadGraphics();
		
		loadPlayer();
		
		bp= new BoardPanel();
		add(bp);
		initMyMenu();
		
		this.addKeyListener(new movementListener());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/**
	 * This method creates the grid on the JFrame
	 */
	 private void initMyMenu() {

	        JMenuBar menuBar = new JMenuBar();
	        //create Options menu
	        tabComponentsItem = new JMenuItem("Help");
	     
	        tabComponentsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
	        tabComponentsItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               
	               File file = new File("Help.txt");
	               StringBuffer contents = new StringBuffer();
	               BufferedReader reader = null;
		           try {
						reader = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException e2) {
						System.err.println("Could Not find help file");
						e2.printStackTrace();
					}
		            String text = null;
		            int counter=0;
		            try {
		            	while ((text = reader.readLine()) != null){
		            		contents.append(text).append(System.getProperty("line.separator"));
		            		counter++;
		            		if(counter==10)
		            			contents.append('\n');
						}
					} catch (IOException e1) {
						System.err.println("Error reading the Help File. File could be corrupted");
						e1.printStackTrace();
					}
					
	            	JTextArea lab= new JTextArea(contents.toString());
	            	lab.setEditable(false);
	            	lab.setLineWrap(true);
	            	JScrollPane pan= new JScrollPane(lab);
	            	JFrame helpFrame= new JFrame();
	            	helpFrame.add(pan);
	            	helpFrame.setSize(300,300);
	                helpFrame.setVisible(true);

	            	
	            }
	        });
	        scrollLayoutItem = new JMenuItem("Chat");
	        scrollLayoutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
	        scrollLayoutItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	//new ChatServer();
	            	//TODO
	            }
	        });
	        JMenuItem resetItem = new JMenuItem("Quit Game");
	        resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
	        resetItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                System.exit(1);
	            }
	        });
	       
	        JMenu optionsMenu = new JMenu("Options");
	        optionsMenu.add(tabComponentsItem);
	        optionsMenu.add(scrollLayoutItem);
	        optionsMenu.add(resetItem);
	        menuBar.add(optionsMenu);
	        this.setJMenuBar(menuBar);
	    }
	private class  BoardPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g){

			
			super.paintComponents(g);
			
			//This is only Called once it initializes the background
			if(!updateBoard){
				
				try {
					initPaint(g);
					updateStats(g);
					updateBoard=true;
				} catch (IOException e) {
					System.err.println("Error doing initial paint");
					e.printStackTrace();
				}	
				
			}
			
			//Update the player location
			else{
				try {
					
					updateMap(g);
					
				} catch (IOException e) {
					System.err.println("Error updating map");
					e.printStackTrace();
				}
			}
			
			
			//Update the status board
			if(updateStat){
				updateStats(g);
			}
		}
	
	}
	
	/**
	 * This method does the paint for the graphics. Though it is only called once
	 * this does the initial paint by throwing things on to the game board like 
	 * the background, and the bad guys, and the items. This is the initialization mehtod. 
	 * @param g
	 * @throws IOException 
	 */
	private void initPaint(Graphics g) throws IOException{
		/*
		 * Map stuff
		 */
		
		inputTerrain =  new BufferedReader(new FileReader("./Map1.txt"));
		inputThings= new BufferedReader(new FileReader("./Map1_Items.txt"));
		
		System.out.println("calling init");
		int inEnvironment=0;
		int inThings=0;
		
		for(int y=0; y < 20; y++){
			for( int x=0; x < 20; x++){			
					/*
					 * This part  does the main map with background graphics
					 */
					try{
						inEnvironment=inputTerrain.read();
						inThings=inputThings.read();
						
						if(inEnvironment==10 ){
							inEnvironment=inputTerrain.read();
							inThings=inputThings.read();
						}
						else if( inEnvironment==-1){
							x=100;
							y=100;
							continue;
						}
					}catch(IOException e){
						System.err.println("Error reading in the Map");
					}
					
					System.out.println("inThings:"+ inThings);
					tMap.setCellAt(new FlatMap(x,y), Terrain.getTerrain(inEnvironment));
					if(inThings!=42)
						iMap.setCellAt(new FlatMap(x,y), Items.getItem(inThings));
			}
		}
		
		g.drawImage(scaledCharacter, currentX, currentY, null);
		inputTerrain.close();
		inputThings.close();
		

	}
	
	/**
	 * This method does the load for the character, it also does the initialization for the 
	 * the character stats taht are painted to the screen 
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void loadPlayer() throws IOException, ClassNotFoundException{
		//This method preforms the load operation :) 
		
		String saveData="SaveFile.sav";
		FileInputStream in = new FileInputStream(saveData);
		ObjectInputStream loadFile = new ObjectInputStream(in);
		
		Object o = loadFile.readObject();

		//loadFile.close();
		
		if(o instanceof Player){
			playerObj = (Player) o;
		}
		
	
		level=playerObj.getLevel();
		speed=playerObj.getSpeed();
		strength=playerObj.getStrength();
		magic=playerObj.getMagic();
		name= playerObj.getName();
		hp= playerObj.getCurHP();
		maxhp=playerObj.getMaxHP();
		maxMP=playerObj.getMaxMP();
		MP=playerObj.getCurMP();
		
		int temp = playerObj.getPC();
		
		if( temp==0)
			playerClass="Medic";
		else if( temp==1)
			playerClass="Marine";
		else 
			playerClass="New Recruit";
		
	}

	
	/**
	 * This method actually only loads in the character image and sets it into a scaled instance of its self
	 */
	private void loadGraphics(){
		try {
			characterIMG= ImageIO.read(new File("./playerPic.jpg"));
			scaledCharacter=characterIMG.getScaledInstance(25, 25, 0);
			
		} catch (IOException e) {
			System.err.println("Unable to find character Image. Please check configurations and try again.");
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO 
	 * This method will provide the functionality to update the graphics on the 
	 * map, not anything else. 
	 * @throws  
	 */
	public void updateMap(Graphics g) throws IOException{
	
		for(TerrainCell[] c: tMap.getMap() ){
			for(TerrainCell c2: c){
				g.drawImage(c2.getImage(), c2.getCoordinates().getX()*25, c2.getCoordinates().getY()*25, null);
				
			}
		}
		for( ItemsCell[] c: iMap.getMap()){
			for(ItemsCell c2: c){
				if( c2!=null){
					//System.out.println("Drawing item: "+ c2.getImage());
					g.drawImage(c2.getImage(), c2.getCoordinates().getX()*25, c2.getCoordinates().getY()*25, null);
				}
			}
		}
		
		//Graphics2D g2d=(Graphics2D)g; // Create a Java2D version of g.
        //g2d.translate(170, 0); // Translate the center of our coordinates.
        //g2d.rotate(radianRotate);  // Rotate the image by 1 radian.
        //g2d.drawImage(scaledCharacter,currentX, currentY, this);
        
		g.drawImage(scaledCharacter, currentX, currentY, null);
	}
	
	
	/**
	 * TODO
	 * This method will give the functionality to update the player stats pane. This will
	 * be updated at level up or at general change of player stats
	 */
	public void updateStats(Graphics g){

		
		String levelString= "Level: "+level;
		String magicString="Magic: "+magic;
		String speedString="Speed: "+speed;
		String strengthString="Strength: "+strength;
		
		g.drawString("Player Status:", 0,530);
		g.drawString("Name: "+ name, 10,560);
		g.drawString("Player Class: "+playerClass, 10, 590);	
		
		g.drawString("HP: "+hp+"/"+maxhp, 200, 560);
		g.drawString("MP: "+MP+"/"+maxMP, 200, 590);

		
		g.drawString(levelString, 330, 560);
		g.drawString(magicString, 330, 590);
		g.drawString(speedString, 330, 620);
		g.drawString(strengthString, 330, 650);
		g.drawImage(characterIMG, 440, 570, null);
		
	}
	
	
	/**
	 * this method does the initialization for the menu, the menu gives the functionality 
	 * to start a chat, quit the game, and see a help file. 
	 */
	 
	private class movementListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent pressed) {
			
			if( pressed.getKeyCode()==39){
				//RIGHT 
				updateBoard=true;
				if(currentX<475)
					currentX+=25;
				
				bp.repaint();
			}
			else if(pressed.getKeyCode()==40){
				//DOWN
				updateBoard=true;
				if(currentY<475)
				currentY+=25;
				
				bp.repaint();
			}
			else if(pressed.getKeyCode()==37){
				//LEFT
				updateBoard=true;
				if(currentX>0)
					currentX-=25;
				
				bp.repaint();
			}
			else if(pressed.getKeyCode()==38){
				//UP
				updateBoard=true;
				if(currentY>0)
					currentY-=25;
				
				bp.repaint();
			}
			else if(pressed.getKeyCode()==87){
				//W	
				
			}
			else if(pressed.getKeyCode()==65){
				//A
				radianRotate= (3*(Math.PI)/2);
				bp.repaint();
			}
			else if(pressed.getKeyCode()==68){
				//D
				radianRotate=(Math.PI)/2;
				
				bp.repaint();
			}
			else if(pressed.getKeyCode()==83){
				//S
			}
			
		}

		@Override
		public void keyReleased(KeyEvent released) {
			//System.out.println("You released.... something..");
			
		}

		@Override
		public void keyTyped(KeyEvent typed) {
			//System.out.println("You typed.... something..");
			
		}
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		new GameBoard();
	}
}
