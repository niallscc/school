package gui;
import gameBoard.Player;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public class ConfigWindow extends JFrame {    

    private int tabNumber = 4;
    private final JTabbedPane pane = new JTabbedPane();
    private JMenuItem tabComponentsItem;
    private JMenuItem scrollLayoutItem;
    private Player playerObj;
    private JLabel loadStatus= new JLabel("");
    private JRadioButton serverB;
    private JRadioButton clientB;
    private JTextField ipadd;
    private JTextField portNumber;
    
    private String IP_In;
    private String port_In;
    
    
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
        scrollLayoutItem.setSelected(false);
        setSize(new Dimension(600, 400));
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
    private JPanel charConfigPanel(){

    	
    	JPanel characterPanel= new JPanel();
    	
    	JPanel loadPanel= new JPanel();
    	
    	JLabel loader= new JLabel("Load a pre-existing character");
    	JButton loadButton= new JButton("Load Character");
    	
    	loadPanel.add(loader);
    	loadPanel.add(loadButton);
 
    	JPanel createCharPanel= new JPanel();
    	
    	JLabel chCreate= new JLabel("Create a new Character          ");
    	JButton create= new JButton("New Character ");
    	
    	createCharPanel.add(chCreate);
    	createCharPanel.add(create);
    	
    	
    	JPanel mpCreate= new JPanel();
    	JLabel mapCreate= new JLabel("Create a new Map                      ");
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
    
    private JPanel networkConfig(){
    	
    	JPanel netConfigPan= new JPanel();
    
    	
    	JPanel connectPan= new JPanel();
    	
    	ButtonGroup bg= new ButtonGroup();
    	
    	serverB= new JRadioButton("Server                       ");
    	clientB= new JRadioButton("Client                 ");
    	//serverB.(true);
		bg.add(serverB);
		bg.add(clientB);
		
    	connectPan.add(serverB);
    	connectPan.add(clientB);
    	
    	JPanel iPinputPan= new JPanel();
    	ipadd= new JTextField("                              ");
    	portNumber=new JTextField("       ");
    	JLabel textlab= new JLabel("Please insert the IP address and Port number of the Server: ");
    	
    	
    	EventHandlerNetwork ehn= new EventHandlerNetwork(clientB, serverB);
    	
    	iPinputPan.add(textlab);
    	iPinputPan.add(ipadd);
    	iPinputPan.add(portNumber);
    	
    	clientB.addActionListener(ehn);
    	serverB.addActionListener(ehn);
    	
    	EventHandlerSettings ehs= new EventHandlerSettings(ipadd, portNumber);
    	ipadd.addActionListener(ehs);
    	portNumber.addActionListener(ehs);
    	
    	
    	netConfigPan.add(connectPan);
    	netConfigPan.add(iPinputPan);    	
    	
    	
		/*
		 * This is modified in the actionlistener
		 * 
		 */
		serverB.setEnabled(false);
		clientB.setEnabled(false);
		ipadd.setEditable(false);
		portNumber.setEditable(false);
    	return netConfigPan;
    
    }
    
    private JPanel lobbyPan(){
    
		JPanel lobby= new JPanel();
	    
		//TODO
		JLabel waitingLabel= new JLabel("Waiting for configurations.");
		lobby.add(waitingLabel);
		return lobby;
    }
   
    private class EventHandlerCharacter implements ActionListener{
    	JButton creator, loader;
    	public EventHandlerCharacter(JButton c, JButton l){
    		creator= c;
    		loader= l;
    		

    	}
		@Override
		public void actionPerformed(ActionEvent e) {
			if( e.getSource()==creator){
				
				new CreateCharacterWindow("Create Your Player").runTest();
			}
			else if( e.getSource()== loader){
				
				String saveData="SaveFile.sav";
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
					serverB.setEnabled(true);
					clientB.setEnabled(true);
				}
			}
		}
    	
    }
    private class EventHandlerNetwork implements ActionListener{
    	JRadioButton client, server;
    	public EventHandlerNetwork(JRadioButton c, JRadioButton s){
    		client = c;
    		server = s;
    	}
    	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(client)){
				server.setEnabled(false);
				ipadd.setEditable(true);
				portNumber.setEditable(true);
				
				
			}
			else if( e.getSource().equals(server)){
				client.setEnabled(false);
				String hostname = null;
				String ad=null;
				try {
				    InetAddress addr = InetAddress.getLocalHost();
				    
				    // Get IP Address
				    byte[] ipAddr = addr.getAddress();
				    ad=addr.getHostAddress();
				    // Get hostname
				    hostname = addr.getHostName();
				    
				} catch (UnknownHostException e2) {
				}
				JOptionPane.showMessageDialog(null, ("Your IP Address is: "+ ad +". The port to use is: 4444"));
				
				ipadd.setEditable(false);
				portNumber.setEditable(false);
			}
			
		}
    	
    }
    private class EventHandlerSettings implements ActionListener{
    	JTextField IPin, portIn;
    	
    	public EventHandlerSettings(JTextField ip, JTextField port){
    		
    		IPin=ip;
    		portIn=port;
    	}
    	
		@Override
		public void actionPerformed(ActionEvent e) {
			if( e.getSource().equals(IPin)){
				IP_In=IPin.getText();
				IP_In.replaceAll("\\W","");
				System.out.println("IP_In: "+ IP_In);
			}
			else if( e.getSource().equals(portIn)){
				port_In=portIn.getText();
				String temp= port_In.replaceAll("\\W","");
				System.out.println("portNumber inserted: "+temp);
			}
		}
    	
    }
    private class EventHandlerMap implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				new MapCreate("Create a New Map");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
    	
    }
    
    private class EventHandlerLobby implements ActionListener{

    	public EventHandlerLobby(){

    		//TODO
    	}
    	
		@Override
		public void actionPerformed(ActionEvent e) {
		
		}
    	
    }
 
    
}
