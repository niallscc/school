package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import cast.ArmageddonAndThenSomeCast;
import cast.CastEvent;
import cast.FlameThrowerCast;
import cast.FlyCast;
import cast.GoodDrugsCast;
import cast.GreatDrugsCast;
import cast.IFeelMuchBetterNowCast;
import cast.OpenCast;
import cast.ZorchCast;
import cast.ZotCast;

import events.AttackEvent;
import events.DefendEvent;
import events.MoveEvent;
import game.ClientWorldState;
import gameBoard.FlatMap;
import gameBoard.MagicBoard;
import gameBoard.TerrainCell;
import gameBoard.TerrainMap;

import server.Client;
import swag.Armor;
import swag.Weapon;

import actors.Being;
import actors.Fighter;
import actors.FighterFactory;
import actors.MageFactory;
import actors.ManDistance;
import actors.NPC;
import actors.PCFactory;
import actors.Player;
import actors.ThiefFactory;

/**
 * This is the main GUI for the game, 
 * here is where you will find all of the intricies about the game, it gets from the client updates
 * and actually, while it contains the JFrame, punts off the painting to subsidiary classes that 
 * get a reference to the Graphics object for this class and 
 * do the painting in their own methods. This was done to clean up this class. 
 * @author niallschavez
 *
 */
public class GameBoard extends JFrame{
	/**
	 * The x location and y location of where the mouse is clicked at. 
	 */
	int xClickLoc, yClickLoc;
	/**
	 * This boolean variable will be set when we want the cell clicked to be highlighted;
	 */
	boolean highlightClickedCell;
	/**
	 * call Inventory sets whether or not the inventory board needs to be painted
	 */
	boolean callInventory=false;
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * scaledCharacter: the picture saved in character creation
	 */
	private Image scaledCharacter;

	/**
	 * The boolean flag representing whether or not an attack should be performed 
	 */
	private boolean performMagicAttack=false;
	/**
	 * BoardPanel is an inner class that does the painting for the main gameboard 
	 */
	public JPanel bp;

	/**
	 * Sets whether or not the magic bar needs to be repainted or not
	 */
	boolean changeBar=true, changeStatus=false;

	/**
	 *Determines whether or not to play music, initially set to off 
	 */
	private boolean playOrPause=false;
	/**
	 * The client that this GameBoard object is associated with.
	 */
	private Client client;
	/**
	 * The acutal "animations" that will be shown
	 */
	private Image shieldOverlay;

	/**
	 * This is set if the world needs to be repainted
	 */
	private Boolean isUpdated=true;
	/**
	 * Sets if the items are updated.
	 */
	private Boolean isItemsUpdated=true;
	/**
	 * Checks to see if the direction of attack is changed
	 */
	private boolean isDirChanged=false;
	/**
	 * The Magic bar
	 */
	private MagicBoard magicBoard;
	/**
	 * a boolean flag for whether or not the magicboard needs to be updated 
	 */
	private boolean shouldUpdateMagicBoard=true;
	/**
	 * A global int that represents which magic item is selected
	 */
	private int selectedMagic=1;
	/**
	 * paintRadius determines if the gameboard should be updated with a new radius of effect
	 */
	private boolean paintRadius=true;
	/**
	 * This is the direction of attack set by wasd
	 */
	private int attDir=1;
	/**
	 * isSword if there is a sword attack this is set
	 */
	private boolean isSword=false;
	
	
	/**
	 * FlatMap to hold the direction of the flamethrower.
	 */
	private FlatMap dir;

	/**
	 * these are the cells that were selected by the client to do an attack on.
	 */
	ArrayList<FlatMap> selectedCells= new ArrayList<FlatMap>();

	/**
	 * This is gets set when the player does a magic attack 
	 */
	CastEvent selectedMagicToCast= null;
	
	
	/**
	 * This is the panel for 
	 * the magic bar 
	 */
	JPanel magicPanel;
	
	/**
	 * Constructor initializes the gameboard from a newGameUpdate update that was sent by
	 * the server when the client first connected.
	 * 
	 * @param newGameUpdate the game update from the client 
	 * @throws IOException an error saysing it could not connect 
	 * @throws ClassNotFoundException an error saying the class could not be found 
	 */
	public GameBoard(Client cl) throws IOException {
		client = cl;
		//client.getClientGameController().setUpdateDisplay(true);

		/*
		 * Initializing the game board. I have the board
		 * non-resizable that is so that the paint method doesnt get messed up. if we end up
		 * making bigger maps that just can either be changed or we can do a scroll on mouse move 
		 * 
		 */
		this.setTitle("GladOS game");
		this.setResizable(false);
		this.setSize(500, 710);
		
		bp= new JPanel();
		magicPanel= new JPanel();
		//JTextArea test= new JTextArea();
		//test.setSize(100,100);
		//magicPanel.add(test);
		bp.add(magicPanel);
		
		//bp.setLayout(new BoxLayout(bp, BoxLayout.PAGE_AXIS));
		
		add(bp);

		/*
		 * Initializes the main menu at the top of the board
		 */
		initMyMenu();

		/*
		 * Begins to play music
		 */
		//playMusic();

		this.addKeyListener(new movementListener());
		this.addMouseListener(new clickListener());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.addWindowListener(new WindowListener() {
			public void windowClosed(WindowEvent arg0) { }
			public void windowActivated(WindowEvent arg0) {	}
			public void windowClosing(WindowEvent arg0) {

				client.disconnect();
				System.out.println("Sending disconnect Event");
				System.out.println("Window Closing");
			}
			public void windowDeactivated(WindowEvent arg0) { }
			public void windowDeiconified(WindowEvent arg0) { }
			public void windowIconified(WindowEvent arg0) { }
			public void windowOpened(WindowEvent arg0) { }
		});

	}

	/**
	 * Getter for the Graphics Instance for the board
	 * @return The Graphics Instance.
	 */
	public Graphics getBoardGraphics() {
		return bp.getGraphics();
	}

	/**
	 * This method creates the Menu on the gameboard. This will probably not be needing to messed with 
	 * but maybe basically the help component opens the help file and then the chat is un implemented 
	 * but it maybe fixed later. 
	 * 
	 * Close just closes the window 
	 * 
	 */
	private void initMyMenu() {
		JMenuItem tabComponentsItem;
		JMenuBar menuBar = new JMenuBar();
		//create Options menu
		tabComponentsItem = new JMenuItem("Help");

		tabComponentsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
		tabComponentsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				File file = new File("./HelpFiles/HowTo.txt");
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
		JMenuItem saveItem = new JMenuItem("Save & Quit");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_MASK));
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	saveCharacterState(client.getClientGameController().getPC());
            	System.exit(0);
            }
        });
		
		JMenuItem resetItem = new JMenuItem("Quit Game");
		resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
		resetItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		JMenuItem playPause = new JMenuItem("play/Pause");

		playPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(playOrPause){
					playOrPause=false;
				}
				else{ 
					playOrPause=true;
					playMusic();
				}
			}
		});

		JMenu optionsMenu = new JMenu("Options");
		optionsMenu.add(tabComponentsItem);
		optionsMenu.add(resetItem);
		optionsMenu.add(optionsMenu);
		optionsMenu.add(saveItem);
		optionsMenu.add(playPause);
		menuBar.add(optionsMenu);
		this.setJMenuBar(menuBar);
	}

	/**

	 * This method will give the functionality to update the player stats pane. This will
	 * be updated at level up or at general change of player stats
	 */
	public void updateStats(Graphics g){


		String levelString= "Level: " + this.client.getClientGameController().getPC().getLevel();
		String intString="Intelligence: "+ this.client.getClientGameController().getPC().getIntelligence();
		String speedString="Speed: "+ this.client.getClientGameController().getPC().getSpeed();
		String strengthString="Strength: "+ this.client.getClientGameController().getPC().getStrength();
		String playerClass = ""+ this.client.getClientGameController().getPC().getPlayerClass();
		String hp = "" + this.client.getClientGameController().getPC().getHP();
		String mp = "" + this.client.getClientGameController().getPC().getMP();
		String maxHP =  "" + this.client.getClientGameController().getPC().getMaxHP();
		String maxMP =""+ this.client.getClientGameController().getPC().getMaxMP();
		String name = "" + this.client.getClientGameController().getPC().getName();
		String moolah = "" + this.client.getClientGameController().getPC().getMoolah();
		String encumberance = "" + this.client.getClientGameController().getPC().getEncumberance();

		Armor armor =  this.client.getClientGameController().getPC().getEquipedArmor();
		Weapon weapon = this.client.getClientGameController().getPC().getEquipedWeapon();
		String eqpArmor;
		String eqpWeapon;

		if(armor == null)
			eqpArmor = "Nothing";
		else
			eqpArmor = armor.getName();
		if(weapon == null)
			eqpWeapon = "Nothing";
		else
			eqpWeapon = weapon.getName();

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 500, 500, 300);
		g.setColor(Color.BLACK);

		//g.drawString("Player Status:", 0,530);
		g.drawString("Current Weapon: " + eqpWeapon, 10, 530);
		g.drawString("Current Armor: " + eqpArmor, 10, 515);
		g.drawString("Encumberance: " + encumberance, 300, 530);

		g.drawString("Name: "+ name, 10,560);
		g.drawString("Player Class: "+playerClass, 10, 575);	
		g.drawString(levelString, 10, 590);

		g.drawString("HP: "+hp+"/"+maxHP, 200, 560);
		g.drawString("MP: "+mp+"/"+maxMP, 200, 575);
		g.drawString("Moolah: " + moolah, 200,590 );

		g.drawString(intString, 330, 590);
		g.drawString(speedString, 330, 575);
		g.drawString(strengthString, 330, 560);
		g.drawImage(scaledCharacter, 90, 510, null);

	}
	/**
	 * Basically this Click listener checks where the player clicks and sets that location into variables x clicked loc and 
	 * yClickedLoc 
	 * @author niallschavez
	 *
	 */
	private class clickListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			xClickLoc=(arg0.getX()/25) *25;
			yClickLoc=((arg0.getY()/25)-2 )*25;
			highlightClickedCell=true;

		}
		/**
		 * For our purposes these methods are unused 
		 */
		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}

	}

	/**
	 * The movement listener does the keyboard listening, in here is where we set a lot of location stuff and get key pressed
	 * events and whatnot 
	 * @author niallschavez
	 *
	 */
	private class movementListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent pressed) {
			if(client.getClientGameController().isRunning()) {
				/**
				 * To make the vendor stuff work you need to be next to the vendor and then click on him 
				 * then press B 
				 */
				if(pressed.getKeyCode() == KeyEvent.VK_B) {
					interactWithVendor();
				}

				//DEFEND!!!
				if(pressed.getKeyCode() == KeyEvent.VK_V) {
					Being defender = client.getClientGameController().getPC();
					client.sendEvent(new DefendEvent(client.getClientGameController().getClientID(), defender));
				}
				//TELEPORT!!!
				if(pressed.getKeyCode() == KeyEvent.VK_T) {
					Being caster = client.getClientGameController().getPC();
					OpenCast open = new OpenCast(caster);
					CastEvent cast = new CastEvent(client.getClientGameController().getClientID(), open);
					client.sendEvent(cast);
				}

				//Testing casts
				if(pressed.getKeyCode() == KeyEvent.VK_H) {
					Being caster = client.getClientGameController().getPC();
					FlatMap loc = getClickedCell();
					ZotCast goodDrugs = new ZotCast(caster,loc);
					CastEvent cast = new CastEvent(client.getClientGameController().getClientID(), goodDrugs);
					client.sendEvent(cast);
				}
				//RIGHT 
				if( pressed.getKeyCode()==39){
					isUpdated=true;
					client.sendEvent(new MoveEvent(client.getClientGameController().getClientID(), 'x', 1,client.getClientGameController().getWorldState()));
					int x= client.getClientGameController().getPCLocation().getX()*25;
					if(x<475)
						x+=25;
				}
				//DOWN
				else if(pressed.getKeyCode()==40){
					isUpdated=true;
					client.sendEvent(new MoveEvent(client.getClientGameController().getClientID(), 'y', 1, client.getClientGameController().getWorldState()));
					int y= client.getClientGameController().getPCLocation().getY()*25;
					if(y<475)
						y+=25;
				}
				//LEFT
				else if(pressed.getKeyCode()==37){
					isUpdated = true;
					client.sendEvent(new MoveEvent(client.getClientGameController().getClientID(), 'x', -1,client.getClientGameController().getWorldState()));
					int x= client.getClientGameController().getPCLocation().getX()*25;
					if(x>0)
						x-=25;
				}
				//UP
				else if(pressed.getKeyCode()==38){
					isUpdated=true;
					client.sendEvent(new MoveEvent(client.getClientGameController().getClientID(), 'y', -1,client.getClientGameController().getWorldState()));
					int y= client.getClientGameController().getPCLocation().getY()*25;
					if(y>0)
						y-=25;

				}
				//W
				else if(pressed.getKeyCode()==87){			
					/*This sets the selected cell to the 
					 * cell directly above the character
					 * 
					 */
					attDir=1;
					isDirChanged=true;
					int clientID = client.getClientGameController().getClientID();
					int mapID = client.getClientGameController().getWorldState().getMapLocation(clientID);
					FlatMap clientloc = client.getClientGameController().getPCLocation();
					
					TerrainMap tm = client.getClientGameController().getWorldState().getWorldMap(mapID);
					if(clientloc.getY()-1 >= 0) dir = tm.getCell(clientloc.getX(), clientloc.getY()-1).getCoordinates();
					paintRadius=true;


				}
				//A
				else if(pressed.getKeyCode()==65){
					/*
					 * Sets the slected cell to one cell to the left of the character
					 */
					attDir=0;
					isDirChanged=true;
					int clientID = client.getClientGameController().getClientID();
					int mapID = client.getClientGameController().getWorldState().getMapLocation(clientID);
					FlatMap clientloc = client.getClientGameController().getPCLocation();
					
					TerrainMap tm = client.getClientGameController().getWorldState().getWorldMap(mapID);
					if(clientloc.getX()-1 >= 0)dir = tm.getCell(clientloc.getX()-1, clientloc.getY()).getCoordinates();

					paintRadius=true;

				}
				//D
				else if(pressed.getKeyCode()==68){
					/*
					 * Sets the selected cell to one cell to the right of the character
					 */
					attDir=2;
					isDirChanged=true;
					int clientID = client.getClientGameController().getClientID();
					int mapID = client.getClientGameController().getWorldState().getMapLocation(clientID);
					FlatMap clientloc = client.getClientGameController().getPCLocation();
					
					TerrainMap tm = client.getClientGameController().getWorldState().getWorldMap(mapID);
					if(clientloc.getX()+1 <= tm.getLength()) dir = tm.getCell(clientloc.getX()-1, clientloc.getY()).getCoordinates();
					paintRadius=true;

				}
				//S
				else if(pressed.getKeyCode()==83){
					/*
					 * This sets the selected cell to one below the character
					 * 
					 */
					attDir=3;
					isDirChanged=true;
					int clientID = client.getClientGameController().getClientID();
					int mapID = client.getClientGameController().getWorldState().getMapLocation(clientID);
					FlatMap clientloc = client.getClientGameController().getPCLocation();
					
					TerrainMap tm = client.getClientGameController().getWorldState().getWorldMap(mapID);
					if(clientloc.getY()+1 <= tm.getHeight()) dir = tm.getCell(clientloc.getX()-1, clientloc.getY()).getCoordinates();
					paintRadius=true;
				}

				else if( pressed.getKeyCode()==49){
					//1
					/*
					 * 1 is always attack
					 */
					shouldUpdateMagicBoard=true;
					paintRadius=true;
					selectedMagic=0;
					isUpdated=true;

				}				
				else if( pressed.getKeyCode()==50){
					//2
					/*
					 * 2 is always defense
					 */
					shouldUpdateMagicBoard=true;
					paintRadius=true;
					selectedMagic=1;
					isUpdated=true;


				}
				else if( pressed.getKeyCode()==51){
					//3
					shouldUpdateMagicBoard=true;
					
					paintRadius=true;
					selectedMagic=2;
					isUpdated=true;
				}
				else if( pressed.getKeyCode()==52){
					//4
					shouldUpdateMagicBoard=true;
					paintRadius=true;
					selectedMagic=3;
					isUpdated=true;
				}
				else if( pressed.getKeyCode()==53){
					//5
					shouldUpdateMagicBoard=true;
					paintRadius=true;
					selectedMagic=4;
					isUpdated=true;
				}
				else if( pressed.getKeyCode()==54){
					//6
					shouldUpdateMagicBoard=true;
					paintRadius=true;
					selectedMagic=5;
					isUpdated=true;

				}				
				//Dis is for the spacebar
				else if( pressed.getKeyCode()==32){
					if( selectedMagic>=2){
						Being pc = client.getClientGameController().getPC();
						int id = client.getClientGameController().getClientID();
						performMagicAttack=true;
						switch (selectedMagic) {
							case 2:
								if(ManDistance.getDistance(pc.getLocation(), getClickedCell()) <= 10){
									client.sendEvent(new CastEvent(id,new ZotCast(pc, getClickedCell())));
								}
								break;
							case 3:
								if(ManDistance.getDistance(pc.getLocation(), getClickedCell())<=5){
									client.sendEvent(new CastEvent(id,new GoodDrugsCast(pc, getClickedCell())));
									}
								break;
							case 4:
								if(ManDistance.getDistance(pc.getLocation(), getClickedCell())<=8){
									client.sendEvent(new CastEvent(id,new GreatDrugsCast(pc, getClickedCell())));
									}
								break;
							case 5:
								if(ManDistance.getDistRadius(pc.getLocation(), pc.getLevel()/4).contains(getClickedCell())){
									client.sendEvent(new CastEvent(id,new ZorchCast(pc, getClickedCell())));
									}
								break;
								
							case 6:
								client.sendEvent(new CastEvent(id,new FlyCast(pc, getClickedCell())));
								break;
							case 7: 
								if(ManDistance.getLineDist(pc.getLocation(), dir, 5).contains(getClickedCell())){
									client.sendEvent(new CastEvent(id,new FlameThrowerCast(pc,getClickedCell(),dir) ));
									}
								break;
							case 8:
								if(ManDistance.getDistance(pc.getLocation(), getClickedCell())<=8){
									client.sendEvent(new CastEvent(id,new IFeelMuchBetterNowCast(pc, getClickedCell())));
									}
								break;
							case 9:
								if(ManDistance.getDistRadius(pc.getLocation(), 8).contains(getClickedCell())){
									client.sendEvent(new CastEvent(id,new ArmageddonAndThenSomeCast(pc, getClickedCell())));
									}
								break;		
						}	
					}
					else if(selectedMagic==0){
						isSword=true;
						Being attacker = client.getClientGameController().getPC();
						int id = client.getClientGameController().getClientID();
						if (ManDistance.getDistance(attacker.getLocation(), getClickedCell()) ==1){
						client.sendEvent(new AttackEvent(id, attacker,getClickedCell()));
						}
					}
					else if(selectedMagic == 1){
						Being defender = client.getClientGameController().getPC();
						int defid = client.getClientGameController().getClientID();
						client.sendEvent(new DefendEvent(defid,defender));
					}
				}
				else if (pressed.getKeyCode()==73){
					//i 
					new InventoryWindow("Inventory", client.getClientGameController().getPC().getInventory(), client);
				}
			}
		}
		/**
		 * for the purposes of this game we dont care about these methods 
		 */
		@Override
		public void keyReleased(KeyEvent released) {}

		@Override
		public void keyTyped(KeyEvent typed) {}

	}
	/**
	 * This method does the music playing. It loads in the music file and spawns a thread to play the song
	 */
	public void playMusic(){
		System.out.println("Playing Music");


		AudioInputStream strummingSong = null;
		try {
			strummingSong= AudioSystem.
			getAudioInputStream(new File("./Music/StrummingSong.au"));
		} catch (UnsupportedAudioFileException e) {
			System.err.println("Cannot play this kind of music file. ");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Could not read in music file. Please check path. ");
			e.printStackTrace();
		}
		//Getting the format of the Song to play
		AudioFormat format= strummingSong.getFormat();
		//Getting a dataline, This is what holds teh song
		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
		//Gets a description of the dataline Object
		SourceDataLine sourceDataLine=null;
		try {
			sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
		} catch (LineUnavailableException e) {
			System.err.println("This line is unavailable please try again. ");
			e.printStackTrace();
		}
		new musicPlayer(format, sourceDataLine, strummingSong).start();


	}
	/**
	 * reads in the audio file and begins a new thread to 
	 * play the music 
	 * @author niallschavez
	 *
	 */
	private class musicPlayer extends Thread{

		byte tempBuffer[] = new byte[10000];
		AudioFormat af;
		SourceDataLine sdl;
		AudioInputStream ais;
		public musicPlayer(AudioFormat audioFormat, SourceDataLine sourceDataLine, AudioInputStream audio){
			System.out.println("Starting thread");
			af=audioFormat; 
			sdl= sourceDataLine;
			ais=audio;
		}
		/**
		 * This runs the thread where the music is playing 
		 */
		public void run(){
			try {
				sdl.open(af);
			} catch (LineUnavailableException e) {
				System.err.println("Line Unavailable, thrown from player class");
				e.printStackTrace();
			}
			sdl.start();
			int cnt;
			try {
				while((cnt = ais.read(tempBuffer,0,tempBuffer.length)) != -1 && playOrPause ){
					if(cnt > 0){
						//System.out.println("Playing!");
						sdl.write(tempBuffer, 0, cnt);
					}//end if
				}
			} catch (IOException e) {
				System.err.println("IOexception thrown from player thread, in run method");
				e.printStackTrace();
			}//end while loop

		}
	}
	/**
	 * sets a shield over the player showing that he/she is defending 
	 * @param g the graphics of this Object 
	 */
	public void defend(Graphics g) {

		int x= client.getClientGameController().getPCLocation().getX()*25;
		int y= client.getClientGameController().getPCLocation().getY()*25;
		g.drawImage(shieldOverlay, x, y,null);
		client.getClientGameController().setUpdateDisplay(true);

	}
	/**
	 * this method is not only used for painting the direction of an attack, but also the direction of
	 * certain magic attacks, that is why it gets in the area of effect  array list 
	 * @param g Graphics of this Object 
	 * @param areaOfEffect the array list of selectable items 
	 */
	public void attackDir( Graphics g, ArrayList<FlatMap> areaOfEffect) {

		int x= client.getClientGameController().getPCLocation().getX()*25;
		int y= client.getClientGameController().getPCLocation().getY()*25;

		g.setColor(new Color(200,0,0,100));//red transparency 
		for(FlatMap cell: areaOfEffect)
			g.fillRect((x+cell.getX()), (y+cell.getY()), 25, 25);


	}
	/**
	 * returns whether or not the magic bar needs to be updated
	 * @return
	 */
	public boolean isMagicBarChanged(){
		return shouldUpdateMagicBoard;
	}
	/**
	 * checks whether or not the status bar  should be changed(set by ClientGameController) 
	 * @return
	 */
	public boolean isStatusBarChanged(){
		return changeStatus;
	}

	/**
	 * does the actual drawing of the highlighted cell (called from ClientGameController) 
	 * @param g
	 */
	public void highlightCell(Graphics g){
		System.out.println("Highlight Cell");

		client.getClientGameController().setUpdateDisplay(true);
		g.setColor(new Color(200,0,0,100));//Red transparency 
		g.fillRect(xClickLoc, yClickLoc,25,25);

	}
	/**
	 * get the location of where you clicked
	 * @return
	 */
	public FlatMap getClickedCell(){
		return (new FlatMap(xClickLoc/25, yClickLoc/25));
	}
	/**
	 * sets if the magic bar should be changed 
	 * @param b
	 */
	public void setMagicBarChanged(boolean b) {
		shouldUpdateMagicBoard=b;

	}
	/**
	 * sets if the status bar should be changed 
	 * @param b
	 */
	public void setStatusBarChanged(boolean b){
		changeStatus=false;
	}
	/**
	 * sets if the character position has been updated, eg if he/she has moved this method 
	 * is accessed by ClientGameController
	 * @param b
	 */
	public void setUpdatedCharacterPos(boolean b ){
		isUpdated=b;
	}
	/**
	 * checks whether or not the game board needs to be updated, if the character has
	 * moved/attacked/picked up an item 
	 * @return
	 */
	public Boolean isUpdated(){
		return isUpdated;
	}
	/**
	 * sets whether or not an item has been picked up 
	 * @param b
	 */
	public void setUpdatedItems(boolean b){
		isItemsUpdated=b;
	}
	/**
	 * returns whether or not an item has been picked up 
	 * @return
	 */
	public boolean isItemsUpdated(){
		return isItemsUpdated;
	}


	/**
	 * checks if the direction of attack has changed 
	 * @return
	 */
	public boolean isDirChanged() {

		return isDirChanged;
	}
	/**
	 * sets whether or not the direction should or should not be updated 
	 * called from Client Game Controller 
	 * @param b
	 */
	public void setDirChanged(boolean b){
		isDirChanged=false;
	}

	/**
	 * returns a true or false value depending on if we shoud or should not highlight 
	 * a cell/cells 
	 * @return
	 */
	public boolean shouldHighlight(){
		return highlightClickedCell;
	}
	/**
	 * sets in if a cell should be highlighted 
	 * @param b
	 */
	public void setHighlightedCell(boolean b){
		highlightClickedCell=b;
	}
	/**
	 * returns if the clientgamecontroller should create an inventory windown
	 * @return
	 */
	public boolean doInventory(){
		return callInventory;
	}

	/**
	 * Tells whether or not the client wants to create an inventory window to display its current inventory
	 * @param b
	 */
	public void setPaintInventory(boolean b){

		callInventory =b;	
		client.getClientGameController().setUpdateDisplay(true);
	}

	/**
	 * This creates theMagic board on initialization of the gameboard from clientgamecontroller
	 * @param b: the being for this class
	 */
	public void setMagicBoard(){
		magicBoard= new MagicBoard();

	}
	/**
	 * allows the ClientGameController to get the instance of the magicBoard
	 * @return
	 */
	public MagicBoard getMagicBoard(){
		return magicBoard;
	}
	/**
	 * returns which magic attack is selected
	 * @return
	 */
	public int getSelectedMagic(){
		return selectedMagic;
	}
	/**
	 * tells whether or not the radius needs to be repainted
	 * @return
	 */
	public boolean updateRadius(){
		return paintRadius;
	}
	/**
	 * sets the variable paintRadius(called from clientGameController)
	 * @param b
	 */

	public void setPaintRadius(boolean b){
		paintRadius= b;
		client.getClientGameController().setUpdateDisplay(true);
	}

	/**
	 * tells whether or not the client wants to perform a magic attack 
	 * 
	 */
	public boolean doMagicAttack(){
		return performMagicAttack;
	}

	/**
	 * sets in whether or not the magic attack should contiinue to be performed
	 * @param b
	 */
	public void setPerformMagicAttack(boolean b){
		performMagicAttack=b;
		client.getClientGameController().setUpdateDisplay(true);
	}
	/**
	 * returns an integer representation of which way to attack
	 * @return
	 */
	public int getAttDir(){
		return attDir;
	}

	/**
	 * returns whether or not a swor attack should be performed
	 * @return
	 */
	public boolean swordAttack(){
		return isSword;
	}

	/**
	 * Sets wheter or not there is a sword attack to be performed
	 * @param b
	 */
	public void setSwordAttack(boolean b){
		isSword=b;
		client.getClientGameController().setUpdateDisplay(true);
	}
	/**
	 * this saves the player into his/ her save file 
	 * @param pc
	 */
	public void saveCharacterState(Being pc){
		int gender=0;
		/*
		 * converting the gender 
		 */
		if( pc.getGender()=="Male")
			gender=0;
		else 
			gender=1;
		/*
		 * Converting the class
		 */
		PCFactory fac=null;
		if( pc.getPlayerClass()=="Knight")
			fac= new FighterFactory();
		else if(pc.getPlayerClass()=="Priest")
			fac= new MageFactory();
		else 
			fac= new ThiefFactory();
		
		Player pObj= new Player(pc.getName(), fac, gender);
		pObj.getPCFactory().createPlayerClass(
				new FlatMap(pc.getLocation().getX(), pc.getLocation().getY()), pc.getName(), pc.getGender()).setLevel(pc.getLevel());
		
		
		String fileName = "./UserSaveFiles/SaveFile.sav";
		/*
		 * FILE INPUT STUFF
		 * 
		 * 
		 */
		FileOutputStream in = null;
		try {
			in = new FileOutputStream(fileName);
		} catch (FileNotFoundException e2) {
			System.out.println("Could not save to :SaveFile.sav");
			e2.printStackTrace();
		}
		ObjectOutputStream saveData = null;
		try {
			saveData = new ObjectOutputStream(in);
		} catch (IOException e1) {
			System.err.println("Could not save data");
			e1.printStackTrace();
		}


		try {
			saveData.writeObject(pObj);
		} catch (IOException e1) {
			System.err.println("Could not save data");
			e1.printStackTrace();
		}

		try {
			saveData.flush();
		} catch (IOException e1) {
			System.out.println("Could not save data");
			e1.printStackTrace();
		}
	}

	/**
	 * does interactions with a vendor 
	 * for a vendor interaction to be made very specific qualifications must be met
	 * first you have to click on the vendor then you have to press B to initiate the talking with him 
	 * 
	 */
	public void interactWithVendor() {
		//Get the world state, it will make things easier to work with.
		ClientWorldState clientWorldState = this.client.getClientGameController().getWorldState();

		//Get the location of the clicked cell
		FlatMap npcLoc = this.getClickedCell();

		//Get the map location
		int mapLocation = clientWorldState.getMapLocation(this.client.getClientGameController().getClientID());

		//Make sure they clicked a cell.
		if((npcLoc != null) && (mapLocation != -1)) {
			//Get the cell for the NPC
			TerrainCell cell=this.client.getClientGameController().getWorldState().getWorldMap(mapLocation).getCellAt(npcLoc);


			//Make sure it exists
			if(cell != null) {
				//Make sure that the player is adjacent to the cell.
				if(ManDistance.getDistance(cell.getCoordinates(), this.client.getClientGameController().getPC().getLocation()) <= 1) {

					//Get the NPC on the cell
					NPC npc = cell.getNPC();

					//Make sure there's even an NPC...
					if(npc != null) {	
						new NPCInteractionGUI(client, npc);	
					}
				}
			}
		}
	}
}
