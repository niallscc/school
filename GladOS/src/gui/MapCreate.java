package gui;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
public class MapCreate extends JFrame{
	/*
	 * MAP KEY: 
	 * 	g: grass
	 *  w: water
	 *  s: sand 
	 *  p: swamp
	 *  m: mountain
	 */
	

	
	/*
	 * Characters stuff 
	 */
	private Image zombie;
	private Image slime;

	/*
	 * Item Stuff
	 * 
	 * 
	 */
	private Image gold;
	private Image treasure;
	private Image mana;
	private Image healthPotion;
	
	/*
	 * Terrain Stuff
	 */
	
	private Image sand;
	private Image water;
	private Image mountain;
	private Image swamp;
	private Image grass;
	private Image wall;
	
	/*
	 * Random Global stuff
	 */
	private boolean init=true;
	public BoardPanel bp;
	private int xLoc;
	private int yLoc;
	private int currentSelection=0;
	private boolean overlayFlag=false;
	private Image overlayChoice;
	private char overChar;
	
	
	/*
	 *These arrays hold the maps for each layer of the gameboard
	 * 
	 */
	private char[][] terrainMap= new char[20][20];
	private char[][] itemMap= new char[20][20];
	private char[][] monsterMap= new char[20][20];
	
	
	private Image[] images=new Image[13];
	//This is a flag that gets set by the key listeners 
	
	
	public MapCreate(String title) throws IOException, ClassNotFoundException{

		this.setTitle(title);
		this.setResizable(false);
		this.setSize(500,550);
		
		loadGraphics();
	
		
		/*
		 * Initializing the terrain map to all Grass
		 * Initializing the item map to all empty
		 * Initializing the monster map to all empty 
		 */
		for( int i = 0; i < 20; i++ )
			for( int j =0; j< 20; j++){
				terrainMap[i][j]='*';
				itemMap[i][j]='*';
				monsterMap[i][j]='*';
			}
				
		
		/*
		 * The board panel holds everything for the map  here I add it to the frame
		 */
		bp= new BoardPanel();
		add(bp);
		
		
		/*
		 * This creates the menu bar that holds all of the map making stuff for the game. 
		 */
		initMyMenu();
		/*
		 * Adding a mouse listener to the main frame so that whenever I click I can get the location of
		 * that click 	
		 */
		this.addMouseListener(new movementListener());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/**
	 * This method creates the grid on the JFrame
	 */
	private void initMyMenu() {
		 	JMenuItem HelpItem;
		 	JMenuItem sandItem;
	        JMenuBar menuBar = new JMenuBar();
	        //create Options menu
	       
	        /********************************* TERRAIN ELEMENTS ************************************/
	        
	        sandItem = new JMenuItem("Sand");
	        sandItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
	        sandItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	          
	            	currentSelection=0;
	            }
	        });
	        JMenuItem waterItem =new JMenuItem("Water");
	        waterItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.ALT_MASK));
	        waterItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=1;
	         
	            }
	        });

	        JMenuItem mountainItem =new JMenuItem("Mountain");
	        mountainItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_MASK));
	        mountainItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=2;
	            	
	            }
	        });
	        JMenuItem swampItem =new JMenuItem("Swamp");
	        swampItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
	        swampItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=3;
	            
	            }
	        });
	        
	        JMenuItem grassItem =new JMenuItem("Grass");
	        grassItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_MASK));
	        grassItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=4;
	            	
	            }
	        });
	        
	        JMenuItem wallItem =new JMenuItem("Wall");
	        wallItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.ALT_MASK));
	        wallItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=5;
	            
	            }
	        });
	        
	        /************************************ ITEMS ELEMENTS *******************************/
	        JMenuItem goldItem =new JMenuItem("Gold");
	        goldItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_MASK));
	        goldItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=6;
	            
	            }
	        });	        
	        JMenuItem manaItem =new JMenuItem("Treasure");
	        manaItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_MASK));
	        manaItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=7;
	            	
	            }
	        });
	        
	        JMenuItem healthItem =new JMenuItem("Health");
	        healthItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.ALT_MASK));
	        healthItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=8;
	            	
	            }
	        });
	        
	        JMenuItem treasureItem =new JMenuItem("Mana");
	        treasureItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_MASK));
	        treasureItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=9;
	            	
	            }
	        });
	        
	        JMenuItem clearItem =new JMenuItem("Clear");
	        clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
	        clearItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=1000;
	            	
	            }
	        });
	        
	        /************************************* MONSTER ELEMENTS ****************************/
	        
	        JMenuItem slime =new JMenuItem("Slime Monster");
	        
	        slime.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=10;
	            	
	            }
	        });
	        
	        JMenuItem zombie =new JMenuItem("Zombie");
	        
	        zombie.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=11;
	            	
	            }
	        });
	        /************************************* OPTIONS ELEMENTS*****************************/
	        
	        
	        JMenuItem quitItem = new JMenuItem("Quit Game");
	        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
	        quitItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                System.exit(1);
	            }
	        });
	        JMenuItem saveItem = new JMenuItem("Save");
	        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_MASK));
	        saveItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	JFrame frame = new JFrame("Save");

	                // prompt the user to enter their name
	                String name = JOptionPane.showInputDialog(frame, "Please enter a file name (Don't forget the extension):");
	            	
	                if( name!=null)
	                	save(name);
	            }
	        });
	       
	        HelpItem = new JMenuItem("Help");
	        HelpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
	        HelpItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               
	               File file = new File("MapMakerHelp.txt");
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
	            	helpFrame.setSize(500,300);
	                helpFrame.setVisible(true);

	            	
	            }
	        });
	        
	        /*************************************** TERRAIN OVERLAY *****************/
	        
	        JMenuItem overlaySwamp =new JMenuItem("Swamp Overlay");
	        overlaySwamp.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	overlayFlag=true;
	            	overlayChoice=swamp;
	            	overChar='p';
	            	
	            }
	        });
	        
	        JMenuItem overlaySand =new JMenuItem("Sand Overlay");
	        overlaySand.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	overlayFlag=true;
	            	overlayChoice=sand;
	            	overChar='s';
	            	
	            }
	        });
	        
	        JMenuItem overlayMountain =new JMenuItem("Mountain Overlay");
	        overlayMountain.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	overlayFlag=true;
	            	overlayChoice=mountain;
	            	overChar='m';
	            }
	        });
	        
	        JMenuItem overlayWater =new JMenuItem("Water Overlay");
	        overlayWater.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	overlayFlag=true;
	            	overlayChoice=water;
	            	overChar='w';
	            }
	        });
	        JMenuItem overlayGrass =new JMenuItem("Grass Overlay");
	        overlayGrass.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	overlayFlag=true;
	            	overlayChoice=grass;
	            	overChar='*';
	            	
	            }
	        });
	        
	        
	        /*************************************** MENU STUFF ***********************/
	        JMenu optionsMenu = new JMenu("Options");
	        optionsMenu.add(HelpItem);
	        optionsMenu.add(saveItem);
	        optionsMenu.add(quitItem);
	        
	        JMenu terrainMenu= new JMenu("Terrain");
	       
	        terrainMenu.add(waterItem);
	        terrainMenu.add(sandItem);
	        terrainMenu.add(mountainItem);
	        terrainMenu.add(grassItem);
	        terrainMenu.add(swampItem);
	        terrainMenu.add(wallItem);
	        
	        JMenu itemsMenu= new JMenu("Items");
	        itemsMenu.add(goldItem);
	        itemsMenu.add(manaItem);
	        itemsMenu.add(healthItem);
	        itemsMenu.add(treasureItem);
	        itemsMenu.add(clearItem);
	        
	        JMenu monsterMenu= new JMenu("Monsters");
	        monsterMenu.add(slime);
	        monsterMenu.add(zombie);
	        
	        JMenu overlayMenu= new JMenu("Overlays");
	        overlayMenu.add(overlaySwamp);
	        overlayMenu.add(overlaySand);
	        overlayMenu.add(overlayWater);
	        overlayMenu.add(overlayMountain);
	        overlayMenu.add(overlayGrass);
	        
	        
	        menuBar.add(optionsMenu);
	        menuBar.add(terrainMenu);
	        menuBar.add(itemsMenu);
	        menuBar.add(monsterMenu);
	        menuBar.add(overlayMenu);
 
	        this.setJMenuBar(menuBar);
	    }
	private class  BoardPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g){

			
			super.paintComponents(g);
			
			/*
			 * Basically this works ona flag system, 
			 * where when an option is selected a flag is set then in the method that is called
			 * the flag is unset when the operation is completed 
			 * Init: initializes teh gameboard, it is called once 
			 * overlayFlag: checks if the user wants to overlay the entire gameboard with 
			 * 			    one terrain element
			 * else: just updates the map with the new stuff. this is called most often 
			 * 
			 * 
			 */
			
			if(init){
				
				initPaint(g);
				paintMenu(g);
				init=false;
			}

			if(overlayFlag){
				overlay(g);
			}
			else{
				updateMap(g);
				paintTerrain(g);
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
	private void initPaint(Graphics g){
		System.out.println("Initializing Game Grid");
		for( int y = 0; y <= 20; y++){
			for(int x = 0; x <= 20; x++){
				g.drawImage(grass,x*25, y*25, null);
			}
		}
	}

	/**
	 * This method actually only loads in the character image and sets it into a scaled instance of its self
	 */
	private void loadGraphics(){
		try {

			
			/*******************TERRAIN ***********************/
			BufferedImage tempMount= ImageIO.read(new File("./GladOS_Graphics/mountain_pane.png"));
			BufferedImage tempSand= ImageIO.read(new File("./GladOS_Graphics/sand_pane.png"));
			BufferedImage tempGrass= ImageIO.read(new File("./GladOS_Graphics/grass_pane.png"));
			BufferedImage tempSwamp= ImageIO.read(new File("./GladOS_Graphics/swamp_pane.png"));
			BufferedImage tempWater= ImageIO.read(new File("./GladOS_Graphics/water_pane.png"));
			BufferedImage tempWall= ImageIO.read(new File("./GladOS_Graphics/brick_wall.png"));
			
			/*******************ITEMS ************************/
			
			BufferedImage tempGold= ImageIO.read(new File("./GladOS_Graphics/swag_panel.png"));
			BufferedImage tempTreasure= ImageIO.read(new File("./GladOS_Graphics/treasure_chest_panel.png"));
			BufferedImage tempHealth= ImageIO.read(new File("./GladOS_Graphics/healthPotion.png"));
			BufferedImage tempMana= ImageIO.read(new File("./GladOS_Graphics/mana.png"));
			
			
			/****************** MONSTERS *******************/
			BufferedImage tempSlime= ImageIO.read(new File("./GladOS_Graphics/zombie_2.png"));
			BufferedImage tempZombie= ImageIO.read(new File("./GladOS_Graphics/zombie_1.png"));
			
			
			/*****************IMAGES **********************/
			sand= tempSand.getScaledInstance(25,25, 0);
			water=tempWater.getScaledInstance(25,25, 0);
			mountain=tempMount.getScaledInstance(25,25, 0);
			grass=tempGrass.getScaledInstance(25,25, 0);
			swamp=tempSwamp.getScaledInstance(25,25, 0);
			wall=tempWall.getScaledInstance(25,25, 0);
			
			gold=tempGold.getScaledInstance(25,25, 0);
			treasure=tempTreasure.getScaledInstance(25,25, 0);
			healthPotion=tempHealth.getScaledInstance(25,25, 0);
			mana=tempMana.getScaledInstance(25,25, 0);
			
			slime= tempSlime.getScaledInstance(25,25,0);
			zombie= tempZombie.getScaledInstance(25,25,0);
			
			
			
			images[0]= sand;
			images[1]= water;
			images[2]= mountain;
			images[3]= swamp;
			images[4]= grass;
			images[5]= wall;
			images[6]= gold;
			images[7]= treasure;
			images[8]= healthPotion;
			images[9]= mana;
			images[10]= slime;
			images[11]= zombie;
			
		} catch (IOException e) {
			System.err.println("Unable to find character Image. Please check configurations and try again.");
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will provide the functionality to update the graphics on the 
	 * map, not anything else. 
	 * @throws  
	 */
	public void updateMap(Graphics g){
		System.out.println("Updating Game Grid");
		
		
		for( int i = 0; i < 20; i++ ){
			for( int j =0; j< 20; j++){
				if( terrainMap[i][j]=='*')
					g.drawImage(grass, i*25, j*25, null);
				else if( terrainMap[i][j]=='m')
					g.drawImage(mountain, i*25, j*25, null);
				else if( terrainMap[i][j]=='p')
					g.drawImage(swamp, i*25, j*25, null);
				else if( terrainMap[i][j]=='w')
					g.drawImage(water, i*25, j*25, null);
				else if( terrainMap[i][j]=='b')
					g.drawImage(wall, i*25, j*25, null);
				
				if(itemMap[i][j]=='g')
					g.drawImage(gold, i*25, j*25, null);
				else if(itemMap[i][j]=='t')
					g.drawImage(treasure, i*25, j*25, null);
				else if(itemMap[i][j]=='h')
					g.drawImage(healthPotion, i*25, j*25, null);
				else if(itemMap[i][j]=='m')
					g.drawImage(mana, i*25, j*25, null);
				
				if( monsterMap[i][j]=='z')
					g.drawImage(zombie, i*25, j*25, null);
				
				else if( monsterMap[i][j]=='s')
					g.drawImage(slime, i*25, j*25, null);
					
				
			}

		}
		if( currentSelection<100)
			g.drawImage(images[currentSelection],xLoc, yLoc, null);
	}
	
	public void paintMenu(Graphics g){
		System.out.println("Painting menu");
		
		
	}
	
	public void paintTerrain(Graphics g){
		System.out.println("Painting terrain");

	}

	/**
	 * this method does the initialization for the menu, the menu gives the functionality 
	 * to start a chat, quit the game, and see a help file. 
	 */
	 
	private class movementListener implements MouseListener{
		@Override
		public void mousePressed(MouseEvent arg0) {
			
			xLoc=(arg0.getX()/25);
			yLoc=(arg0.getY()/25)-2;
			
			if( xLoc < 20 && xLoc >= 0 && yLoc < 20 && yLoc >= 0 ){
				
				
				if(currentSelection==0)
					terrainMap[xLoc][yLoc]='s';
				else if(currentSelection==1)
					terrainMap[xLoc][yLoc]='w';
				else if(currentSelection==2)
					terrainMap[xLoc][yLoc]='m';
				else if(currentSelection==3)
					terrainMap[xLoc][yLoc]='p';
				else if(currentSelection==4)
					terrainMap[xLoc][yLoc]='g';
				else if(currentSelection==5)
					terrainMap[xLoc][yLoc]='b';
				
				if(currentSelection==6)
					itemMap[xLoc][yLoc]='g';
				else if(currentSelection==7)
					itemMap[xLoc][yLoc]='t';
				else if(currentSelection==8)
					itemMap[xLoc][yLoc]='h';
				else if(currentSelection==9)
					itemMap[xLoc][yLoc]='m';
				else if(currentSelection==1000)
					itemMap[xLoc][yLoc]='*';
				
				if(currentSelection==10)
					monsterMap[xLoc][yLoc]='s';
					
				if(currentSelection==11)
					monsterMap[xLoc][yLoc]='z';
				
				xLoc*=25;
				yLoc*=25;
				
				bp.repaint();
			}
			
			
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {}

		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}

	}
	public void save(String filename){
		
		 try{
		    // Create file 
		    FileWriter mapStream = new FileWriter(filename);
		    FileWriter characterStream = new FileWriter("charMap"+filename);
		    FileWriter itemStream = new FileWriter("itemMap"+filename);
		    
		    BufferedWriter outMap = new BufferedWriter(mapStream);
		    BufferedWriter outChar = new BufferedWriter(characterStream);
		    BufferedWriter outItem = new BufferedWriter(itemStream);
		    
		    for( int i = 0; i < 20; i++ ){
				for( int j =0; j < 20; j++){
					
					outMap.write(terrainMap[j][i]);
					//System.out.print(terrainMap[j][i]);
					outItem.write(itemMap[j][i]);
					outChar.write(monsterMap[j][i]);
				}
				//System.out.println();
				
				outMap.write('\n');
				outChar.write('\n');
				outItem.write('\n');
			}
		    
		    outMap.close();
		    outChar.close();
		    outItem.close();
		    }catch (Exception e){
		    	//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
		  
	
	
	}
	
	public void overlay(Graphics g){
		
		for( int y = 0; y < 20; y++){
			for(int x = 0; x < 20; x++){
				terrainMap[x][y]=overChar;
				g.drawImage(overlayChoice,x*25, y*25, null);
				
			}
		}
		overlayFlag=false;
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		new MapCreate("Map Creator");
	}
}
