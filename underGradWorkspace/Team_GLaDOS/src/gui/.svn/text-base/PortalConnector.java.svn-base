/**
 * 
 */
package gui;

import gameBoard.Connections;
import gameBoard.Portals;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * This GUI is currently not in use, it is made to allowe the user to connect portals
 * and to be able to customize where the portals go, but it is not being used 
 * @author niallschavez
 *
 */
@SuppressWarnings("serial")
public class PortalConnector extends JFrame{
	/*
	 * Global panel elements
	 */
	/**
	 * this array holds all the labels that are the keys of the hash maps
	 * eg the names of the files
	 */
	JLabel toLabel=new JLabel("To: ");
	JLabel fromLabel=new JLabel("From: ");
	
	JComboBox fromPoints;
	JComboBox toPoints;
	
	/**
	 * from and to are the boxes that hold the names of the files
	 */
	JComboBox from;
	JComboBox to;
	
	/**
	 * These are the comboboxes that hold the places that portals are at. 
	 */
	
	JComboBox toPortalLocations;
	JComboBox fromPortalLocations;
	
	JButton submitButton;
	
	JPanel mainPanel;
	JPanel toPanel;
	JPanel fromPanel;
	JPanel submitPanel;
	
	Point selectedToPoint;
	Point selectedFromPoint;
	/*
	 * Global Variables 
	 */
	private  HashMap<String, HashMap<String, Point>> portalMap=null;
	private static  MainPanel mp;

	public PortalConnector(){
		
		/*
		 * Initializing the window 
		 */
		submitButton= new JButton("Connect!");
		mainPanel= new JPanel();
		toPanel= new JPanel();
		fromPanel= new JPanel();
		submitPanel= new JPanel();
		to= new JComboBox();
		from= new JComboBox();
		toPortalLocations= new JComboBox();
		fromPortalLocations= new JComboBox();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		readMaps();
		

		mp= new MainPanel();
		add(mp);
		this.setTitle("Connect Some Portals!");
		this.setPreferredSize(new Dimension(400,300));
		this.setVisible(true);
		this.pack();

		
	}
	
	/**
	 * This reads in the portal locations from the save file portals.p 
	 * and sets them into each of the boxes 
	 */
	public void readMaps(){
		
		Portals p=null;
	    ObjectInputStream objectIn=null;
		File f= new File("./PortalStuff/portals.p");
	    if(f.exists()){
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
			portalMap=p.getPortalmap();
	    }
	    else{
	    	//portalMap=new  HashMap<String, HashMap<String, Point>>();
	    	JOptionPane.showMessageDialog(null, ("You need to create a Map and Portals to connect portals"));
	    	System.exit(1);
	    }
		/*
		 * Initial Population of to and from boxes 
		 * 
		 */
		to=new JComboBox(portalMap.keySet().toArray());
		to.setSelectedIndex(0);
		
		from= new JComboBox(portalMap.keySet().toArray());
		from.setSelectedIndex(0);
		
		toPortalLocations= populate( (String)to.getSelectedItem());
		fromPortalLocations=populate( (String)from.getSelectedItem());
		
		/*
		 * Initializing them just incase someone just clicks submit and 
		 * never actually selects anything
		 */
		
		toPortalLocations.setSelectedIndex(0);
		fromPortalLocations.setSelectedIndex(0);
		
	}
	private JComboBox populate(String filename){
		int tempCounter=0;
		
		String[] tempArray= new String[portalMap.get(filename).keySet().toArray().length];
		for( String key: portalMap.get(filename).keySet()){
			int x=portalMap.get(filename).get(key).x;
			int y= portalMap.get(filename).get(key).y;
			tempArray[tempCounter]="("+x+", "+y+")";
			System.out.print(tempArray[tempCounter]+" ");
			tempCounter++;
		}
		
		JComboBox tempBox= new JComboBox(tempArray);
	
		return tempBox;
	}
	
	
	public class MainPanel extends JPanel{
	
		public MainPanel(){
			
			MapListener ml= new MapListener(to,from,fromPortalLocations, toPortalLocations, toPanel, fromPanel);
			EventHandlerSelectedTo ehst= new EventHandlerSelectedTo(to);
			EventHandlerSelectedFrom ehsf= new EventHandlerSelectedFrom(from);
			
			fromPanel.add(fromLabel);
			fromPanel.add(from);
			from.addActionListener(ml);
			fromPortalLocations.addActionListener(ehsf);
			fromPanel.add(fromPortalLocations);
			
			toPanel.add(toLabel);
			to.addActionListener(ml);
			toPanel.add(to);
			toPortalLocations.addActionListener(ehst);
			toPanel.add(toPortalLocations);
	
			submitButton.addActionListener(new EventHandlerSubmit());
			submitPanel.add(submitButton);


		}
		
		@Override
		public void paintComponent(Graphics g){
			
			super.paintComponents(g);
			mainPanel.add(fromPanel);
			mainPanel.add(toPanel);
			mainPanel.add(submitPanel);
			
			this.add(mainPanel);
			
		}
	}
	private class MapListener implements ActionListener{
		JComboBox fromLoc, toLoc;
		
		JPanel tPanel, fPanel;
		public MapListener(JComboBox t, JComboBox f, JComboBox fL, JComboBox tL, JPanel tP, JPanel fP ){
			fromLoc=fL;
			toLoc=tL;
			tPanel=tP;
			fPanel=fP;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if( e.getSource()==from){
				fPanel.removeAll();
				
				fromPortalLocations= populate((String)from.getSelectedItem());
				
				fPanel.add(fromLabel);
				fPanel.add(from);
				fPanel.add(fromLoc);
				repaint();
			}
			else if(e.getSource()==to){
				System.out.println("update to ");
				tPanel.removeAll();
				
				toLoc= populate((String)to.getSelectedItem());
				tPanel.add(toLabel);
				tPanel.add(to);
				tPanel.add(toLoc);
				
				repaint();
			}

		
		}	
	}
	
	private class EventHandlerSelectedTo implements ActionListener{
	
		JComboBox toBox;
		public EventHandlerSelectedTo(JComboBox jcb){
			toBox=jcb;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("Changed to!");
			for( String key: portalMap.get(toBox.getSelectedItem().toString()).keySet()){
				
				int x=portalMap.get(toBox.getSelectedItem().toString()).get(key).x;
				int y=portalMap.get(toBox.getSelectedItem().toString()).get(key).y;
			    String temp ="("+x+", "+y+")";
			    System.out.print(temp);
			    // ("  "+toPortals.getSelectedItem().toString());
			    
			    if(toPortalLocations.getSelectedItem().toString().equals(temp)){
					
			    	selectedToPoint= new Point(x,y);
			    	//System.out.println("Found: "+ selectedToPoint);
			    }
				
			
		}
			System.out.println("SelectedToPoint is: "+ selectedToPoint);
		
	}
	}
	private class EventHandlerSelectedFrom implements ActionListener{
		JComboBox frommer;
		public EventHandlerSelectedFrom(JComboBox jcb){
			frommer=jcb;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			for( String key: portalMap.get(frommer.getSelectedItem().toString()).keySet()){
				
				int x=portalMap.get(frommer.getSelectedItem().toString()).get(key).x;
				int y=portalMap.get(frommer.getSelectedItem().toString()).get(key).y;
			    String temp ="("+x+", "+y+")";

				if(fromPortalLocations.getSelectedItem().toString().equals(temp)){
					//System.out.println("The selected item is: "+temp);
					selectedFromPoint= new Point(x,y);
				}
				
		}
		
		System.out.println("SelectedFromPoint is: "+ selectedFromPoint);
		}
		
	}
	
	private class EventHandlerSubmit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			String fromFileName= from.getSelectedItem().toString();
			String toFileName= to.getSelectedItem().toString();
			System.out.println(fromFileName);
			System.out.println(selectedFromPoint.x);
			System.out.println(selectedFromPoint.y);
			JOptionPane.showMessageDialog(null, ("Are you sure you want to connect: "+fromFileName+" ("+selectedFromPoint.x+", "+selectedFromPoint.x+
					" to "+toFileName+"("+selectedToPoint.x+", "+selectedToPoint.y+") ?" ));
			//System.out.println("Saving Portal Connection: "+fromFileName+"("+
			//		selectedFromPoint.x+", "+selectedFromPoint.y+")");
			
			//System.out.println("-> "+toFileName+
				//	"("+selectedToPoint.x+", "+selectedToPoint.y+") ");
			
			File f= new File("./PortalStuff/connections.con");
			
			
			if(f.exists()){
				Connections c= read(fromFileName, toFileName);
				write(c);
			}
			else{
				Connections c= new Connections();
				c.setConnection(fromFileName, toFileName, selectedFromPoint, selectedToPoint);
				write(c);
				
			}
		
		}
		public Connections read(String fromFileName, String toFileName){
			Connections c = null;
		    ObjectInputStream objectIn=null;
			try {
				objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("./PortalStuff/connections.con")));
				c=(Connections) objectIn.readObject();
				objectIn.close();
			} catch (FileNotFoundException e) {
				System.err.println("Could not read in connections.con");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("IO exception when reading in connections.con");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.err.println("Class not found when reading in connections.con");
				e.printStackTrace();
			}
			c.setConnection(fromFileName, toFileName, selectedFromPoint, selectedToPoint);
			return c;
		}
		private void write(Connections c){
		    FileOutputStream out = null;
			try {
				out = new FileOutputStream("./portalStuff/connections.con");
			
			} catch (FileNotFoundException e2) {
				System.out.println("Could not save to :connections.con");
				e2.printStackTrace();
			}
			ObjectOutputStream saveData = null;
			try {
			
				saveData = new ObjectOutputStream(out);
				saveData.writeObject(c);
				saveData.flush();
			
			} catch (IOException e1) {
				System.err.println("Could not save data");
				e1.printStackTrace();
			}
			
		}
	}
	/**
	 * Start it UP!! Vrroooommmmmm :P 
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		new PortalConnector();

	}

}
