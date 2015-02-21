package gui;

	import gameBoard.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


	@SuppressWarnings("serial")
	public class CreateCharacterWindow extends JFrame {    
		
		private int characterClass=2;
		private int gender;
		private String playerName="Gordon Freeman";
		private Color color=Color.BLACK;
		
		
		//Setting a global Label for the output color for the pane
		private JLabel colorButton= new JLabel("Your Character's color is: "+ color);
		
		//Setting a global label for the output name for the pane.
		private JLabel nameLabel= new JLabel("Your Character's name is:"+playerName );
		
		//Setting a global label for the output pane for the class;
		private JLabel classLabel=new JLabel("Your Character's class is: New Recruit");
		
		//Setting a global label for the output pane for the gender
		private JLabel genderLabel= new JLabel("Your Character's class is: Male");
		
	    private int tabNumber = 4;
	    private final JTabbedPane cPane = new JTabbedPane();
	    private JMenuItem tabComponentsItem;
	    private JMenuItem scrollLayoutItem;

	    public static void main(String[] args) {
	        //Schedule a job for the event dispatch thread:
	        //creating and showing this application's GUI.
	        SwingUtilities.invokeLater(new Runnable(){
	            public void run(){
	                //Turn off metal's use of bold fonts
		        UIManager.put("swing.boldMetal", Boolean.FALSE);
	                new CreateCharacterWindow("Create your Character").runTest();
	            }
	        });
	    }
	    
	    public CreateCharacterWindow(String title) {
	        super(title);
	        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        initMyMenu();        
	        add(cPane);        
	    }
	    /**
	     * This creates all the Tabbed panes 
	     */
	    public void runTest() {

	    	JPanel[] items ={colorChoose(),playerConfig(),nameInput(), endConfig()};
	    	
	    	tabNumber=items.length;
	        cPane.removeAll();
	        
	        for (int i = 0; i < tabNumber; i++) {
	            JPanel title =items[i];
	            cPane.add(title);
	            initTabComponent(i);
	        }
	        
	        tabComponentsItem.setSelected(true);
	        cPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
	        scrollLayoutItem.setSelected(false);
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
	               //TODO Add help Functionality 
	            }
	        });
	        scrollLayoutItem = new JMenuItem("Reset All Fields");
	        scrollLayoutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
	        scrollLayoutItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	//TODO add Reset Functionality 
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
	        setJMenuBar(menuBar);
	    }
	    private JPanel colorChoose(){
	    	//TODO add Character Configuration stuff 
	    	
	    	
	    	JPanel colorPanel= new JPanel();
	    	JLabel selectLabel= new JLabel("Please select a color for your character:");
	    	JButton openChooser= new JButton("Choose a color");
	    	
	    	
	    	colorPanel.add(selectLabel);
	    	colorPanel.add(openChooser);
	    	EventHandlerColor c= new EventHandlerColor();
	    	
	    	openChooser.addActionListener(c);
	    	
	    	return colorPanel;
	    }
	    
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
	    	
	    	JRadioButton medic= new JRadioButton("Medic");
	    	JRadioButton marine= new JRadioButton("Marine");
	    	JRadioButton newRecruit= new JRadioButton("New Recruit");
	    		
	    	playerClass.add(medic);
	    	playerClass.add(marine);
	    	playerClass.add(newRecruit);
	    	
	    	pClass.add(cLabel);
	    	pClass.add(medic);
	    	pClass.add(marine);
	    	pClass.add(newRecruit);
	    	
	    	player.add(genderPan);
	    	player.add(pClass);
	    	
	    	
	    	
	    	EventHandlerClass ehp= new EventHandlerClass(medic, marine, newRecruit);
	    	medic.addActionListener(ehp);
	    	marine.addActionListener(ehp);
	    	newRecruit.addActionListener(ehp);
	    	
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

		    name.add(namePanel);
		    name.add(buttonPanel);
			return name;
	    }
	    
	    private JPanel endConfig(){
		    
			JPanel configPanel= new JPanel();
			
		    JPanel colorPanel=new JPanel();
		    JPanel namePanel= new JPanel();
		    JPanel classPanel= new JPanel();
		    JPanel genderPanel= new JPanel();
		    
		    colorPanel.add(colorButton);
		    namePanel.add(nameLabel);
		    classPanel.add(classLabel);
		    genderPanel.add(genderLabel);
		    
		    configPanel.add(namePanel);
		    configPanel.add(colorPanel);
		    configPanel.add(classPanel);
		    configPanel.add(genderPanel);
		    
		    EventHandlerFinish ehf= new EventHandlerFinish();
		    JButton jb= new JButton("Finish!");
		    jb.addActionListener(ehf);
		    
		    configPanel.add(jb);
		   
			return configPanel;
	    }
	    
	    private class EventHandlerColor implements ActionListener{
			public EventHandlerColor(){

	    	}
			@Override
			public void actionPerformed(ActionEvent e) {

				 color=JColorChooser.showDialog(new JPanel(), "Choose your Color", Color.BLACK); 
				 colorButton.setText("Your Character's color is: "+ color.toString());
			}	
	    }
	    private class EventHandlerName implements ActionListener{
	    	JTextField jtf;
	    	public EventHandlerName(JTextField n){
	    		jtf=n;
	    	}
	    	
			@Override
			public void actionPerformed(ActionEvent e) {
				playerName=jtf.getText();
				nameLabel.setText("Your Character's Name is: "+playerName);
			}
	    	
	    }
	    private class EventHandlerClass implements ActionListener{
	    	JRadioButton me, mar, rec;
	    	public EventHandlerClass(JRadioButton medic, JRadioButton marine, JRadioButton recruit){

	    		me=medic;
	    		mar=marine;
	    		rec= recruit;
	    	}
	    	
			@Override
			public void actionPerformed(ActionEvent e) {
				if( e.getSource()==me){
					characterClass=0;
					classLabel.setText("Your Character's class is: Medic");
				}
				else if(e.getSource()==mar){
					characterClass=1;
					classLabel.setText("Your Character's class is: Marine");
				}
				else if(e.getSource()==rec){
					classLabel.setText("Your Character's class is: New Recruit");
					characterClass=2;
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
				Player pObj= new Player( playerName,characterClass, gender,color);
				
				String fileName = "SaveFile.sav";
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
				
				//Serializable obj= (Serializable) pObj;

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
				
				/*
				 * 
				 * JPG CREATION STUFF
				 * 
				 * 
				 */
				
				BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);   
				for(int y=0; y<50; y++){
					for(int x=0; x<50; x++){
						image.setRGB(x, y, color.getRGB());
					}
				}
				Graphics g;
				 if( characterClass==0){
					 g = image.getGraphics();   
					 g.drawString("__+__", 5, 6);
					 g.drawString("0   0", 11, 20); 
					 g.drawString("(___)", 11, 35);
				 }
				 else if(characterClass==1){
					 g = image.getGraphics();   
					 g.drawString("_____", 5, 5);
					 g.drawString(">   <", 9, 20); 
					 g.drawString(" ___", 11, 35);
					 
				 }
				 else if(characterClass==2){
					 g = image.getGraphics();   
					 g.drawString("_____", 5, 5);
					 g.drawString("O   O", 10, 20); 
					 g.drawString(" ___ ", 11, 35);
					 
				 }
				 try {    
				   ImageIO.write(image, "jpg", new File("playerPic.jpg"));   
				 } catch (IOException c) {    
				  c.printStackTrace();   
				 }  
				
				 setVisible(false);
				 dispose();
				
			}
	    	
	    }
	  
}
