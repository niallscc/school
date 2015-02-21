package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import actors.Player;

import server.Client;
import server.Server;

/**
 * This class is the main GUI that loads up upon starting the game 
 * it does all the configurations for the start of the game like setting if you 
 * are a client or a server, loading in a character, and many other intricies of the game
 * 
 * @author niallschavez
 *
 */
public class ConfigWindow extends JFrame {    
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	private int tabNumber = 4;
	private final JTabbedPane pane = new JTabbedPane();
	private JMenuItem tabComponentsItem;
	private Player playerObj;
	private JLabel loadStatus= new JLabel("");
	private JRadioButton serverB;
	private JRadioButton clientB;
	private JTextField ipadd;
	private JTextField portNumber;
	private final int portNumberToUse = 7777;
	private boolean charLoaded = false;
	private boolean serverLoaded = false;
	private boolean serverBool=false;
	private InetAddress ip;
	private int port = 7777;

	/**
	 * Begins the main thread 
	 * @param args
	 */
	public static void main(String[] args) {
		//Schedule a job for the event dispatch thread:
		//creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				new ConfigWindow("GlaDOS Game").runTest();
			}
		});
	}
	/**
	 * this starts up the configuration window 
	 * this is the constructor it takes in the title to be set into the 
	 * window 
	 * @param title
	 */
	public ConfigWindow(String title) {
		
		super(title);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initMenu();        
		add(pane);        
	}
	/**
	 * This creates all the Tabbed panes 
	 */
	public void runTest() {


		JPanel[] items ={charConfigPanel(),networkConfig(),lobbyPan()};

		tabNumber=items.length;
		pane.removeAll();

		for (int i = 0; i < tabNumber; i++) {
			JPanel title =items[i];

			pane.add(title);

			initTabComponent(i);
		}
		tabComponentsItem.setSelected(true);
		pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		setSize(new Dimension(600, 300));
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Inits each of the tabs
	 * @param i a tab. 
	 */
	private void initTabComponent(int i) {
		pane.setTabComponentAt(i,
				new Tabs(pane));
	}    

	//Setting menu

	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		//create Options menu
		tabComponentsItem = new JMenuItem("Help");
		// tabComponentsItem = new JCheckBoxMenuItem("Help", true);
		tabComponentsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
		tabComponentsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				File file = new File("./HelpFiles/Help.txt");
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
	/**
	 * This is the configuration panel for the characters 
	 * @return
	 */
	private JPanel charConfigPanel(){


		JPanel characterPanel= new JPanel();

		JPanel loadPanel= new JPanel();

		JLabel loader= new JLabel("Load a pre-existing character");
		JButton loadButton= new JButton("Load Character");

		loadPanel.add(loader);
		loadPanel.add(loadButton);

		JPanel createCharPanel= new JPanel();

		JLabel chCreate= new JLabel("Create a new Character");
		JButton create= new JButton("New Character ");

		createCharPanel.add(chCreate);
		createCharPanel.add(create);


		JPanel mpCreate= new JPanel();
		JLabel mapCreate= new JLabel("Create a new Map");
		JButton mapMaker= new JButton("    New Map     ");
		EventHandlerMap ehm= new EventHandlerMap();
		mapMaker.addActionListener(ehm);
		mpCreate.add(mapCreate);
		mpCreate.add(mapMaker);

		characterPanel.add(loadPanel);
		characterPanel.add(createCharPanel);
		characterPanel.add(mpCreate);
		characterPanel.add(loadStatus);

		EventHandlerCharacter ehc= new EventHandlerCharacter(create, loadButton);
		loadButton.addActionListener(ehc);
		create.addActionListener(ehc);

		return characterPanel;
	}

	/**
	 * this is the panel for the network configurations
	 * here you set if you want to be a server or a client then 
	 * you can start the game accordingly  
	 * @return
	 */
	private JPanel networkConfig(){

		JPanel netConfigPan= new JPanel();


		JPanel connectPan= new JPanel();

		ButtonGroup bg= new ButtonGroup();

		serverB= new JRadioButton("Server                       ");
		clientB= new JRadioButton("Client                 ");
		//JButton startServer = new JButton("Start Server");

		//serverB.(true);
		bg.add(serverB);
		bg.add(clientB);


		connectPan.add(serverB);
		connectPan.add(clientB);

		JPanel iPinputPan= new JPanel();
		ipadd= new JTextField();

		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.err.println("Unable to get local address");
		}
		//Give it a size.
		ipadd.setPreferredSize(new Dimension(125, 30));

		ipadd.setText(ip.getHostAddress());
		portNumber=new JTextField();
		portNumber.setPreferredSize(new Dimension(60, 30));
		portNumber.setText("" + port);


		JLabel textlab= new JLabel("IP address and Port number of the Server: ");
		JPanel buttonPanel = new JPanel();
		JButton setServerSettings = new JButton("Set");
		buttonPanel.add(setServerSettings);
		
		EventHandlerNetwork ehn= new EventHandlerNetwork(clientB, serverB);


		iPinputPan.add(textlab);
		iPinputPan.add(ipadd);
		iPinputPan.add(portNumber);
		iPinputPan.add(setServerSettings);
		iPinputPan.add(buttonPanel);
		clientB.addActionListener(ehn);
		serverB.addActionListener(ehn);

		EventHandlerSettings ehs= new EventHandlerSettings(ipadd, portNumber);
		ipadd.addActionListener(ehs);
		portNumber.addActionListener(ehs);
		setServerSettings.addActionListener(ehs);

		netConfigPan.add(connectPan);
		netConfigPan.add(iPinputPan);    	


		/*
		 * This is modified in the actionlistener
		 * 
		 */
		//	serverB.setEnabled(false);
		//clientB.setEnabled(false);
		ipadd.setEditable(false);
		portNumber.setEditable(false);
		return netConfigPan;

	}

	/**
	 * in the lobby panel you can start the 
	 * main game 
	 * @return
	 */
	private JPanel lobbyPan(){

		JPanel lobby= new JPanel();
		JButton startButton= new JButton("Start Game");
		JLabel waitingLabel= new JLabel("Waiting for configurations....");
		lobby.add(waitingLabel);

		lobby.add(startButton);
		EventHandlerLobby ehl= new EventHandlerLobby();
		startButton.addActionListener(ehl);


		return lobby;
	}

	/**
	 * tells what to do with the events from the buttons on the character tab 
	 * @author niallschavez
	 *
	 */
	private class EventHandlerCharacter implements ActionListener{
		JButton creator, loader;
		public EventHandlerCharacter(JButton c, JButton l){
			creator= c;
			loader= l;

		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if( e.getSource()==creator){

				new CreateCharacterWindow("Create Your Player");
			}
			else if( e.getSource()== loader){

				String saveData="./UserSaveFiles/SaveFile.sav";
				FileInputStream in = null;
				try {
					in = new FileInputStream(saveData);
				} catch (FileNotFoundException e1) {
					System.err.println("Could not find SaveFile");
					e1.printStackTrace();
				}
				ObjectInputStream loadFile = null;
				try {
					loadFile = new ObjectInputStream(in);
				} catch (IOException e1) {
					System.err.println("Could not load character. Save file may be corrupted");
					e1.printStackTrace();
				}
				Object o = null;
				try {
					o = loadFile.readObject();
				} catch (IOException e1) {
					System.err.println("Could not read player object...");
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					System.err.println("Class not found Exception thrown from Config Window");
					e1.printStackTrace();
				}

				try {
					loadFile.close();
				} catch (IOException e1) {
					System.err.println("IO exception when trying to close the Save file");
					e1.printStackTrace();
				}

				if(o instanceof Player){
					playerObj = (Player) o;

					loadStatus.setText("Your Character: "+playerObj.getName()+" was loaded successfully!");
					charLoaded = true;
					serverB.setEnabled(true);
					clientB.setEnabled(true);
				}
			}
		}

	}

	/**
	 * tells what to do with the buttons on the network tab 
	 * @author niallschavez
	 *
	 */
	private class EventHandlerNetwork implements ActionListener{
		JRadioButton client, server;
		public EventHandlerNetwork(JRadioButton c, JRadioButton s){
			client = c;
			server = s;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(client)){
				serverBool=false;
				server.setEnabled(false);
				ipadd.setEditable(true);
				portNumber.setEditable(true);


			}
			else if( e.getSource().equals(server)){
				serverBool=true;
				client.setEnabled(false);
				String ad=null;
				try {
					InetAddress addr = InetAddress.getLocalHost();
					ad = addr.getHostAddress();
					new Server();

				} catch (UnknownHostException e2) {
					System.err.println("Unknown host exception while starting the server");
				}
				JOptionPane.showMessageDialog(null, ("Your IP Address is: "+ ad +". The port to use is: " + portNumberToUse));

				ipadd.setEditable(false);
				portNumber.setEditable(false);
			}
		}

	}
	/**
	 * tells what to do with the buttons/textfields on the settings( character config) tab 
	 * @author niallschavez
	 *
	 */
	private class EventHandlerSettings implements ActionListener{
		JTextField IPin, portIn;

		public EventHandlerSettings(JTextField ip, JTextField port){

			IPin=ip;
			portIn=port;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//Checks the IP and sets it and the port
			if(e.getActionCommand() == "Set") {
				//Ensure that the IP address is valid
				//WTF?!
				Pattern pattern = Pattern.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"); 
				String ipAdd = IPin.getText().trim();
				Matcher match = pattern.matcher(ipAdd);

				//Convert the port number to an int
				try {
					if(portIn.getText().trim() != "")
						port = Integer.parseInt(portIn.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(ConfigWindow.this,"Invalid Port!");
				}

				if(match.matches()) {
					try {
						ip = InetAddress.getByName(ipAdd);
						serverLoaded = true;
					} catch (UnknownHostException e1) {
						IPin.setText("");
						JOptionPane.showMessageDialog(ConfigWindow.this,"Invalid IP Address!");
					}
				}
				else 
					JOptionPane.showMessageDialog(ConfigWindow.this,"Invalid IP Address!");	
			}
		}

	}
	/**
	 * sets in the map stuff for the server 
	 * @author niallschavez
	 *
	 */
	private class EventHandlerMap implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				new MapCreate("Create a New Map");
			} catch (IOException e1) {
				System.err.println("IOException thrown from EventHandlerMap");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				System.err.println("Class not found. in EventHandlerMap");
				e1.printStackTrace();
			}

		}

	}
	/**
	 *holds everything tha thas to do with the lobbys events 
	 *
	 */
	private class EventHandlerLobby implements ActionListener{

		//Here is where the stuff for what happens when the start button gets pressed goes. 
		@Override
		public void actionPerformed(ActionEvent e) {
			if(serverBool )
				JOptionPane.showMessageDialog(ConfigWindow.this,"Please open a new Config Window for your client. You cannot open a client when you have the server loaded");
			else if((charLoaded) && (serverLoaded)) {
				System.out.println("IP: "+ConfigWindow.this.ip+" CongfigWindow: "+ConfigWindow.this.port);
				new Client(ConfigWindow.this.ip, ConfigWindow.this.port, playerObj);
			}
			
			else
				JOptionPane.showMessageDialog(ConfigWindow.this,"Please create and load in a character and set the server settings.");	
		}

	}


}
