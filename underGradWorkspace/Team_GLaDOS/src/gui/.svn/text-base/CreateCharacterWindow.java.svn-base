package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import actors.FighterFactory;
import actors.MageFactory;
import actors.PCFactory;
import actors.Player;
import actors.ThiefFactory;

/**
 * This GUI does the creation of the character 
 * it allows the player to select their Gender, class, and name and 
 * saves the state of their character to a save file to be loaded in by 
 * the main game 
 * @author niallschavez
 *
 */
public class CreateCharacterWindow extends JFrame {    

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	private int gender;
	private String playerName="Gordon Freeman";
	//private Color color=Color.BLACK;
	/**
	 * The factory that will create the specific player class a client selected.
	 */
	PCFactory classFactory;

	//Setting a global Label for the output color for the pane
	//private JLabel colorButton= new JLabel("Your Character's color is: "+ color);

	//Setting a global label for the output name for the pane.
	private JLabel nameLabel= new JLabel("Your Character's name is:"+playerName );

	//Setting a global label for the output pane for the class;
	private JLabel classLabel=new JLabel("Your Character's class is: Serf");

	//Setting a global label for the output pane for the gender
	private JLabel genderLabel= new JLabel("Your Character's class is: Male");

	private int tabNumber = 3;
	private final JTabbedPane cPane = new JTabbedPane();
	private JMenuItem tabComponentsItem;


//	public static void main(String[] args) {
//		//Schedule a job for the event dispatch thread:
//		//creating and showing this application's GUI.
//		SwingUtilities.invokeLater(new Runnable(){
//			public void run(){
//				//Turn off metal's use of bold fonts
//				UIManager.put("swing.boldMetal", Boolean.FALSE);
//				new CreateCharacterWindow("Create your Character").runTest();
//			}
//		});
//	}

	public CreateCharacterWindow(String title) {
		super(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initMyMenu();        
		add(cPane);   
		this.setVisible(true);
		runTest();
	}
	/**
	 * This creates all the Tabbed panes 
	 */
	public void runTest() {

		JPanel[] items ={playerConfig(),nameInput(), endConfig()};

		tabNumber=items.length;
		cPane.removeAll();

		for (int i = 0; i < tabNumber; i++) {
			JPanel title =items[i];
			cPane.add(title);
			initTabComponent(i);
		}

		tabComponentsItem.setSelected(true);
		cPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);

		setSize(new Dimension(600, 500));
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Inits each of the CharacterTabs
	 * @param i a tab. 
	 */
	private void initTabComponent(int i) {
		cPane.setTabComponentAt(i,
				new CharacterCreateTab(cPane));
	}    

	//Setting menu

	private void initMyMenu() {
		JMenuBar menuBar = new JMenuBar();
		//create Options menu
		tabComponentsItem = new JMenuItem("Help");
		// tabComponentsItem = new JCheckBoxMenuItem("Help", true);
		tabComponentsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
		tabComponentsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("./HelpFiles/HelpCharacterCreate.txt");
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

		JMenuItem resetItem = new JMenuItem("Quit Game");
		resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
		resetItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});

		JMenu optionsMenu = new JMenu("Options");
		optionsMenu.add(tabComponentsItem);
	
		optionsMenu.add(resetItem);
		menuBar.add(optionsMenu);
		setJMenuBar(menuBar);
	}
//	private JPanel colorChoose(){
//		//TODO add Character Configuration stuff 
//
//
//		JPanel colorPanel= new JPanel();
//		JLabel selectLabel= new JLabel("Please select a color for your character:");
//		JButton openChooser= new JButton("Choose a color");
//
//
//		colorPanel.add(selectLabel);
//		colorPanel.add(openChooser);
//		EventHandlerColor c= new EventHandlerColor();
//
//		openChooser.addActionListener(c);
//
//		return colorPanel;
//	}

	private JPanel playerConfig(){

		JPanel player= new JPanel();

		JPanel genderPan= new JPanel();
		JLabel choose =new JLabel("Please Select your Gender");

		ButtonGroup gender= new ButtonGroup();

		JRadioButton male= new JRadioButton("Male");
		JRadioButton female= new JRadioButton("Female                            ");

		gender.add(male);
		gender.add(female);

		genderPan.add(choose);
		genderPan.add(male);
		genderPan.add(female);

		JPanel pClass= new JPanel();
		JLabel cLabel= new JLabel("Please select your class: ");
		ButtonGroup playerClass= new ButtonGroup();

		JRadioButton mage= new JRadioButton("Priest");
		JRadioButton knight= new JRadioButton("Knight");
		JRadioButton serf= new JRadioButton("Serf");

		playerClass.add(mage);
		playerClass.add(knight);
		playerClass.add(serf);

		pClass.add(cLabel);
		pClass.add(mage);
		pClass.add(knight);
		pClass.add(serf);

		player.add(genderPan);
		player.add(pClass);



		EventHandlerClass ehp= new EventHandlerClass(mage, knight, serf);
		mage.addActionListener(ehp);
		knight.addActionListener(ehp);
		serf.addActionListener(ehp);

		EventHandlerGender ehg= new EventHandlerGender(male, female);
		male.addActionListener(ehg);
		female.addActionListener(ehg);

		return player;

	}

	private JPanel nameInput(){

		JPanel name= new JPanel();
		JPanel buttonPanel=new JPanel();
		JPanel namePanel= new JPanel();

		JTextField pName= new JTextField(playerName);
		JLabel lab= new JLabel("Please enter your name: ");
		

		namePanel.add(lab);
		namePanel.add(pName);

		EventHandlerName ehn=new EventHandlerName(pName);

		pName.addActionListener(ehn);
		JButton set = new JButton("Set");
		namePanel.add(set);
		set.addActionListener(ehn);

		name.add(namePanel);
		name.add(buttonPanel);
		return name;
	}

	private JPanel endConfig(){

		JPanel configPanel= new JPanel();

	//	JPanel colorPanel=new JPanel();
		JPanel namePanel= new JPanel();
		JPanel classPanel= new JPanel();
		JPanel genderPanel= new JPanel();

		//colorPanel.add(colorButton);
		namePanel.add(nameLabel);
		classPanel.add(classLabel);
		genderPanel.add(genderLabel);

		configPanel.add(namePanel);
	//	configPanel.add(colorPanel);
		configPanel.add(classPanel);
		configPanel.add(genderPanel);

		EventHandlerFinish ehf= new EventHandlerFinish();
		JButton jb= new JButton("Finish!");
		jb.addActionListener(ehf);

		configPanel.add(jb);

		return configPanel;
	}

//	private class EventHandlerColor implements ActionListener{
//		public EventHandlerColor(){
//
//		}
//		@Override
//		public void actionPerformed(ActionEvent e) {
//
//			color=JColorChooser.showDialog(new JPanel(), "Choose your Color", Color.BLACK); 
//			colorButton.setText("Your Character's color is: "+ color.toString());
//		}	
//	}
	private class EventHandlerName implements ActionListener{
		JTextField jtf;
		public EventHandlerName(JTextField n){
			jtf=n;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "Set") {
				playerName=jtf.getText();
				nameLabel.setText("Your Character's Name is: "+playerName);
			}
		}

	}
	private class EventHandlerClass implements ActionListener{
		JRadioButton me, mar, rec;
		public EventHandlerClass(JRadioButton mage, JRadioButton knight, JRadioButton serf){

			me=mage;
			mar=knight;
			rec= serf;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if( e.getSource()==me){
				classFactory = new MageFactory();
				classLabel.setText("Your Character's class is: Mage");
			}
			else if(e.getSource()==mar){
				classFactory = new FighterFactory();
				classLabel.setText("Your Character's class is: Knight");
			}
			else if(e.getSource()==rec){
				classLabel.setText("Your Character's class is: Serf");
				classFactory = new ThiefFactory();
			}
		}

	}
	private class EventHandlerGender implements ActionListener{
		JRadioButton m, f;
		public EventHandlerGender(JRadioButton male, JRadioButton female){

			m=male;
			f=female;

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if( e.getSource()==m){
				gender=0;
				genderLabel.setText("Your Character's gender is: Male");
			}
			else if(e.getSource()==f){
				gender=1;
				genderLabel.setText("Your Character's gender is: Female");
			}

		}

	}

	private class EventHandlerFinish implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			Player pObj= new Player(playerName,classFactory, gender);

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

//			/*
//			 * 
//			 * JPG CREATION STUFF
//			 * 
//			 * 
//			 */
//
//			BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);   
//			for(int y=0; y<50; y++){
//				for(int x=0; x<50; x++){
//					image.setRGB(x, y, color.getRGB());
//				}
//			}
//			Graphics g;
//			if( characterClass==0){
//				g = image.getGraphics();   
//				g.drawString("______", 5, 6);
//				g.drawString("-    -", 11, 20); 
//				g.drawString(" (--) ", 11, 35);
//			}
//			else if(characterClass==1){
//				g = image.getGraphics();   
//				g.drawString("^^^^^", 5, 5);
//				g.drawString(">   <", 9, 20); 
//				g.drawString(" ___ ", 11, 35);
//
//			}
//			else if(characterClass==2){
//				g = image.getGraphics();   
//				g.drawString("_____", 5, 5);
//				g.drawString("<.  .>", 10, 20); 
//				g.drawString(" ____ ", 11, 35);
//
//			}
//			try {    
//				ImageIO.write(image, "jpg", new File("playerPic.jpg"));   
//			} catch (IOException c) {    
//				c.printStackTrace();   
//			}  

			setVisible(false);
			dispose();

		}

	}

}
