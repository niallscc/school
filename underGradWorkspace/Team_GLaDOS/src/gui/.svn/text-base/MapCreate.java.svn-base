	package gui;


import gameBoard.Portals;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

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

/**
 * This class allows the player to  create a new Map 
 * for the game, they can completely customize the way the game map looks and
 * can specify where to put items, terrain, npcs and monsters
 * @author niallschavez
 *
 */
@SuppressWarnings("serial")
public class MapCreate extends JFrame{
	/*
	 * MAP KEY: 
	 * 	g: grass
	 *  w: water
	 *  s: sand 
	 *  p: swamp
	 *  m: mountain
	 *  f: forrest
	 */
	
	/**
	 * normal Zombie Image  
	 */
	private Image zombie;
	/**
	 * Slime Zombie Image
	 */
	private Image slime;
	/**
	 * Zombie Cow image
	 */
	private Image zombieCow;
	/**
	 * Boss ZOmbie Image 
	 */
	private Image bossZombie;
	/**
	 * normal zombie Image 
	 */
	private Image zombie2;
	
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
	private Image forrest;
	/*
	 * NPC stuff
	 */
	private Image npc;
	
	
	/*
	 * Portal Stuff
	 * 
	 */
	private Image portal;
	
	/*
	 * Random Global stuff
	 */
	private boolean init=true;
	public BoardPanel bp;
	private int xLoc;
	private int yLoc;
	private int currentSelection=4;
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
	
	
	/*
	 * the array list that holds all the images in it 
	 */
	private ArrayList<Image>images= new ArrayList<Image>();
	
	/*
	 * the hashmap that holds all of the informaion for a portal 
	 */
	HashMap<String, HashMap<String, Point>> portalMap= new HashMap<String, HashMap<String, Point>>();
	
	
	public MapCreate(String title) throws IOException, ClassNotFoundException{

		this.setTitle(title);
		//this.setResizable(false);
		this.setSize(500,545);
		
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
	        JMenuItem forrestItem =new JMenuItem("Forrest");
	        
	        forrestItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=15;
	            
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
	        
	        JMenuItem slime = new JMenuItem("Slime Monster");
	        
	        slime.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=10;
	            	
	            }
	        });
	        
	        JMenuItem zombie = new JMenuItem("Zombie");
	        
	        zombie.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=11;
	            	
	            }
	        });
	        JMenuItem triangleZombie = new JMenuItem("Zombie 2");
	        triangleZombie.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=12;
	            	
	            }
	        });
	        
	        JMenuItem zombieCow = new JMenuItem("Zombie Cow");
	        
	        zombieCow.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=13;
	            	
	            }
	        });
	        JMenuItem bossZombie = new JMenuItem("Boss Zombie");
	        
	        bossZombie.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=14;
	            }
	        });
	        /***********************************NPC ELEMENTS***********************************/
	        JMenuItem npc = new JMenuItem("Vendor");
	        
	        npc.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	currentSelection=16;
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
	        JMenuItem loadItem = new JMenuItem("Load");
	        loadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_MASK));
	        loadItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	JFrame frame = new JFrame("Load");

	                // prompt the user to enter their name
	                String name = JOptionPane.showInputDialog(frame, "Please enter the name of the map to load");
	            	
	                if( name!=null)
	                	load(name);
	            }
	        });
	       
	        /**
	         * opens up the help file for the map maker 
	         */
	        HelpItem = new JMenuItem("Help");
	        HelpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
	        HelpItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               
	               File file = new File("HelpFiles/HelpMapMaker.txt");
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
	        JMenuItem overlayForrest =new JMenuItem("Forrest Overlay");
	        overlayForrest.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	overlayFlag=true;
	            	overlayChoice=forrest;
	            	overChar='f';
	            	
	            }
	        });
	        /********************************PORTAL STUFF******************************/
	        JMenuItem portalItem = new JMenuItem("Portal");
	        portalItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	System.out.println("Portal!");
	            	currentSelection=17;
	            }
	        });
	        JMenuItem connectPortals = new JMenuItem("Connect Created Portals ");
	        connectPortals.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	
	            	new PortalConnector();
	            }
	        });
	        
	        
	        /*************************************** MENU STUFF ***********************/
	        JMenu optionsMenu = new JMenu("Options");
	        optionsMenu.add(HelpItem);
	        optionsMenu.add(saveItem);
	        optionsMenu.add(loadItem);
	        optionsMenu.add(quitItem);
	        
	        JMenu terrainMenu= new JMenu("Terrain");
	       
	        terrainMenu.add(waterItem);
	        terrainMenu.add(sandItem);
	        terrainMenu.add(mountainItem);
	        terrainMenu.add(grassItem);
	        terrainMenu.add(swampItem);
	        terrainMenu.add(wallItem);
	        terrainMenu.add(forrestItem);
	        
	        JMenu itemsMenu= new JMenu("Items");
	        itemsMenu.add(goldItem);
	        itemsMenu.add(manaItem);
	        itemsMenu.add(healthItem);
	        itemsMenu.add(treasureItem);
	        itemsMenu.add(clearItem);
	        
	        JMenu monsterMenu= new JMenu("Monsters");
	        monsterMenu.add(slime);
	        monsterMenu.add(zombie);
	        monsterMenu.add(triangleZombie);
	        monsterMenu.add(zombieCow);
	        monsterMenu.add(bossZombie);
	        JMenu npcMenu= new JMenu("NPC's");
	        npcMenu.add(npc);
	        
	        
	        JMenu overlayMenu= new JMenu("Overlays");
	        overlayMenu.add(overlayForrest);
	        overlayMenu.add(overlaySwamp);
	        overlayMenu.add(overlaySand);
	        overlayMenu.add(overlayWater);
	        overlayMenu.add(overlayMountain);
	        overlayMenu.add(overlayGrass);
	        
	        JMenu portalMenu= new JMenu("Portals");
	        portalMenu.add(portalItem);
	        portalMenu.add(connectPortals);
	        
	        menuBar.add(optionsMenu);
	        menuBar.add(terrainMenu);
	        menuBar.add(itemsMenu);
	        menuBar.add(monsterMenu);
	        menuBar.add(npcMenu);
	        menuBar.add(overlayMenu);
	        menuBar.add(portalMenu);
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
				init=false;
			}

			if(overlayFlag){
				overlay(g);
			}
			else{
				updateMap(g);
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
			BufferedImage tempMount= ImageIO.read(new File("./GameGraphics/mountain_pane.png"));
			BufferedImage tempSand= ImageIO.read(new File("./GameGraphics/sand_pane.png"));
			BufferedImage tempGrass= ImageIO.read(new File("./GameGraphics/grass_pane.png"));
			BufferedImage tempSwamp= ImageIO.read(new File("./GameGraphics/swamp_pane.png"));
			BufferedImage tempWater= ImageIO.read(new File("./GameGraphics/water_pane.png"));
			BufferedImage tempWall= ImageIO.read(new File("./GameGraphics/brick_wall.png"));
			BufferedImage tempForrest= ImageIO.read(new File("./GameGraphics/forrest_pane.png"));
			
			/*******************ITEMS ************************/
			
			BufferedImage tempGold= ImageIO.read(new File("./ItemsIcons/moneyBag.png"));
			BufferedImage tempTreasure= ImageIO.read(new File("./ItemsIcons/treasure_chest_panel.png"));
			BufferedImage tempHealth= ImageIO.read(new File("./ItemsIcons/healthPotion.png"));
			BufferedImage tempMana= ImageIO.read(new File("./ItemsIcons/mana.png"));
			
			
			/****************** MONSTERS *******************/
			BufferedImage tempSlime= ImageIO.read(new File("./EnemyIcons/slimeZombie.png"));
			BufferedImage tempZombie= ImageIO.read(new File("./EnemyIcons/normalZombie.png"));
			BufferedImage tempZombieCow= ImageIO.read(new File("./EnemyIcons/zombieCow.png"));
			BufferedImage tempZombieTriangleHead= ImageIO.read(new File("./EnemyIcons/normalZombie2.png"));
			BufferedImage tempBossZombie= ImageIO.read(new File("./EnemyIcons/bossZombie.png"));
			/******************* NPC **********************/
			BufferedImage tempNPC = ImageIO.read(new File("./NPCIcons/villager_1_2.png"));
			
			
			/*******************PORTAL ********************/
			BufferedImage tempPortal= ImageIO.read(new File("./ItemsIcons/portal.png"));
			
			/*****************IMAGES **********************/
			sand= tempSand.getScaledInstance(25,25, 0);
			water=tempWater.getScaledInstance(25,25, 0);
			mountain=tempMount.getScaledInstance(25,25, 0);
			grass=tempGrass.getScaledInstance(25,25, 0);
			swamp=tempSwamp.getScaledInstance(25,25, 0);
			wall=tempWall.getScaledInstance(25,25, 0);
			forrest=tempForrest.getScaledInstance(25, 25, 0);
			
			gold=tempGold.getScaledInstance(25,25, 0);
			treasure=tempTreasure.getScaledInstance(25,25, 0);
			healthPotion=tempHealth.getScaledInstance(25,25, 0);
			mana=tempMana.getScaledInstance(25,25, 0);
			
			slime= tempSlime.getScaledInstance(25,25,0);
			zombie= tempZombie.getScaledInstance(25,25,0);
			zombie2=tempZombieTriangleHead.getScaledInstance(25,25,0);
			zombieCow= tempZombieCow.getScaledInstance(25,25,0);
			bossZombie=tempBossZombie.getScaledInstance(25,25,0);
			
			npc= tempNPC.getScaledInstance(25,25,0);
			
			portal= tempPortal.getScaledInstance(25,25,0);
			
			
			/**
			 * this array list holds all of the images for the game 
			 */
			images.add(sand);
			images.add( water);
			images.add(mountain);
			images.add(swamp);
			images.add(grass);
			
			images.add(wall);
			images.add(gold);
			images.add(treasure);
			images.add(healthPotion);
			images.add(mana);
			images.add(slime);
			images.add(zombie);
			images.add(zombie2);
			images.add(zombieCow);
			images.add(bossZombie);
			images.add(forrest);
			images.add(npc);
			images.add(portal);
			
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
			
				/*
				 * Terrain
				 */
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
				else if( terrainMap[i][j]=='s')
					g.drawImage(sand, i*25, j*25, null);
				else if(terrainMap[i][j]=='f')
					g.drawImage(forrest, i*25, j*25, null);
				else if(terrainMap[i][j]=='P'){
					System.out.println("Painting portal");
					g.drawImage(portal, i*25, j*25, null);
				}
				
				else if( terrainMap[i][j]=='c')
					g.drawImage(npc,i*25, j*25, null);
				/*
				 * Items
				 */
				if(itemMap[i][j]=='g')
					g.drawImage(gold, i*25, j*25, null);
				else if(itemMap[i][j]=='t')
					g.drawImage(treasure, i*25, j*25, null);
				else if(itemMap[i][j]=='h')
					g.drawImage(healthPotion, i*25, j*25, null);
				else if(itemMap[i][j]=='m')
					g.drawImage(mana, i*25, j*25, null);
			
				/*
				 * Monsters
				 */
				if( monsterMap[i][j]=='z')
					g.drawImage(zombie, i*25, j*25, null);
				else if( monsterMap[i][j]=='s')
					g.drawImage(slime, i*25, j*25, null);
				else if(monsterMap[i][j]=='c')
					g.drawImage(zombieCow, i*25, j*25, null);
				else if(monsterMap[i][j]=='2')
					g.drawImage(zombie2,i*25, j*25,null);
				else if(monsterMap[i][j]=='b')
					g.drawImage(bossZombie, i*25, j*25,null);

					
				
			}

		}
		if( currentSelection<=100)
			g.drawImage(images.get(currentSelection),xLoc, yLoc, null);
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
					terrainMap[xLoc][yLoc]='*';
				else if(currentSelection==5)
					terrainMap[xLoc][yLoc]='b';
				else if(currentSelection==15)
					terrainMap[xLoc][yLoc]='f';
				else if( currentSelection==16)
					terrainMap[xLoc][yLoc]='c';
				else if (currentSelection==17)
					terrainMap[xLoc][yLoc]='P';
					
				if(currentSelection==6)
					itemMap[xLoc][yLoc]='g';
				else if(currentSelection==7)
					itemMap[xLoc][yLoc]='t';
				else if(currentSelection==8)
					itemMap[xLoc][yLoc]='h';
				else if(currentSelection==9)
					itemMap[xLoc][yLoc]='m';
				//This one clears all items and monsters out of a space
				else if(currentSelection==1000){
					itemMap[xLoc][yLoc]='*';
					monsterMap[xLoc][yLoc]='*';
				}
				/*
				 * Here I give the portal an identifier and an id and make sure that it knows what point
				 * it is at
				 */
				
				if(currentSelection==10)
					monsterMap[xLoc][yLoc]='s';
				else if(currentSelection==11)
					monsterMap[xLoc][yLoc]='z';
				else if(currentSelection==12)
					monsterMap[xLoc][yLoc]='2';
				else if(currentSelection==13)
					monsterMap[xLoc][yLoc]='c';
				else if(currentSelection==14)
					monsterMap[xLoc][yLoc]='b';

				
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
	
	/**
	 * This method takes in the file name aquired when the user enters a file name when they click save
	 * once it has this it saves each of the arrays to different files each modified by the name 
	 * of the file 
	 * @param filename name of file 
	 */
	public void save(String filename){
		
		 try{
		    portalAssignments(filename);
			 
			// Create file 
		    
			 
			FileWriter mapStream = new FileWriter("./GameBoardMaps/"+filename);
		    FileWriter characterStream = new FileWriter("./GameBoardMaps/charMap"+filename);
		    FileWriter itemStream = new FileWriter("./GameBoardMaps/itemMap"+filename);  
		    BufferedWriter outMap = new BufferedWriter(mapStream);
		    BufferedWriter outChar = new BufferedWriter(characterStream);
		    BufferedWriter outItem = new BufferedWriter(itemStream);
		    
		    
		    
		    for( int i = 0; i < 20; i++ ){
				for( int j =0; j < 20; j++){
					
					outMap.write(terrainMap[j][i]);

					outItem.write(itemMap[j][i]);
					outChar.write(monsterMap[j][i]);
				}
				
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
	
	/**
	 * This loads in a file from a given level name 
	 * if it fails, it doesn tdo anuything 
	 * @param filename name of level to load in 
	 */
	public void load(String filename){
		
		File terrain= new File("./GameBoardMaps/"+filename);
		File items= new File("./GameBoardMaps/itemMap"+filename);
		File character= new File("./GameBoardMaps/charMap"+filename);
		
		if(terrain.exists()){
			terrainMap= loadStuff(terrain);
			System.out.println("Loaded Terrain");
		}
		
			
		if( items.exists()){
			itemMap=loadStuff(items);
			System.out.println("Loaded Items");
		}
		
		if(character.exists()){
			monsterMap=loadStuff(character);
			System.out.println("Loaded Characters");
		}
		
		this.repaint();
		
	}
	
	/**
	 * The helper file that loads in the terrain map 
	 * @param terrain
	 * @return
	 */
	private char[][] loadStuff(File terrain){
		BufferedReader reader= null;
		char[][] map= new char[20][20];

		try{ 
			reader= new BufferedReader(new FileReader(terrain));
			
			for( int y = 0; y < 20; y++){
				for(int x = 0; x < 20; x++){
					
				char text= (char)reader.read();
				System.out.print(text);
				if( text==10)
					text=(char)reader.read();
				map[x][y]=text;
				
				}
				
				System.out.println();
			}
			reader.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
		

	}
	
	
	
	/**
	 * Okay, So this is kinda confusing from the outside looking in, but what this method does is as 
	 * follows, the idea is that i will need an object that will store the x, y location of all given
	 * portals on this map, so what I am doing is puting those portals in a hashmap with a counter
	 * being its Unique identifier, and what the program is going to do then is put that hashmap into 
	 * a kind of save file that will essentially be a hashmap of hashmaps that I will be able to read 
	 * from to be able to then load in all the info on the portals, then i will be able to set up 
	 * a method inside the portalConnector class that will create a mapping between two portals
	 * 
	 * @param filename
	 */
	private void portalAssignments(String filename){
		int key=0;
		
		/*
		 * Populating hashmap 
		 */
		HashMap<String,Point> portalz= new HashMap<String,Point>();
		for( int i =0; i< 20; i++){
			for(int j=0; j<20; j++){
				if(itemMap[i][j]=='p')
				{
					portalz.put(key+"",new Point(j, i));
					key++;
				}
			}
		}
		/*
		 * Reading in current hashmap and adding in the 
		 * current hashmap
		 */
	    Portals p = null;


	    File f= new File("./portalStuff/portals.p");

	    if(f.exists()){
	    	p=read();
	    	p.setPortals(portalz, filename);
	    	write(p);
		}
	    else{ 
	    	p= new Portals();
	    	p.setPortals(portalz, filename);
	    	write(p);
	    }

	}
/**
 * Reads in the existing portals from the map 
 * @return
 */
	private Portals read(){
		
		Portals p=null;
	    ObjectInputStream objectIn=null;
		try {
			objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("./PortalStuff/portals.p")));
			p=(Portals) objectIn.readObject();
			objectIn.close();
		} catch (FileNotFoundException e) {
			System.err.println("Could not read in portals.p");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO exception when reading in portals.p");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found when reading in portals.p");
			e.printStackTrace();
		}
		
    	return p;
	}
	
	/**
	 * writes to the portals file 
	 * @param p
	 */
	private void write(Portals p){
	    FileOutputStream out = null;
		try {
			out = new FileOutputStream("./portalStuff/portals.p");
		
		} catch (FileNotFoundException e2) {
			System.out.println("Could not save to :portals.p");
			e2.printStackTrace();
		}
		ObjectOutputStream saveData = null;
		try {
			saveData = new ObjectOutputStream(out);
			saveData.writeObject(p);
			saveData.flush();
		} catch (IOException e1) {
			System.err.println("Could not save data");
			e1.printStackTrace();
		}
		
	}
	/**
	 * this is a helper method that gets what kind of terrain to overlay the map with then fills the 
	 * terrain map with that object then draws it 
	 * @param g
	 */
	private void overlay(Graphics g){
		
		for( int y = 0; y < 20; y++){
			for(int x = 0; x < 20; x++){
				terrainMap[x][y]=overChar;
				g.drawImage(overlayChoice,x*25, y*25, null);
				
			}
		}
		overlayFlag=false;
	}
	/**
	 *  Main does creation of the GUI 
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		new MapCreate("Map Creator");
	}
}
