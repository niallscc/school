package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import server.Client;
import swag.Swag;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import events.EquipEvent;
/**
 * This class does the inventory for the character, it loads in the
 * stuff the character has on him/her and once that is done, it allows
 * the user to equip different items and go through and sell items 
 * 
 * @author niallschavez
 *
 */
public class InventoryWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame mainFrame;


	/**
	 * Labels: this array list holds a label for each of the items
	 */
	ArrayList<JLabel> labels= new ArrayList<JLabel>();
	/**
	 * Buttons: this array list holds a button for each of the items, each button 
	 * has the image of that item on it
	 */
	ArrayList<JButton> buttons= new ArrayList<JButton>();
	ArrayList<JButton> equipButtons = new ArrayList<JButton>();
	/**
	 * panels: this is all the panels on the GUI
	 */
	ArrayList<JPanel>panels= new ArrayList<JPanel>();
	/**
	 * the inventory of the player
	 */
	List<Swag>inven;
	/**
	 * The client used to send a sell event.
	 */
	private Client client;


	/**
	 * initializer method, takes in the title for the GUI, and does initializations 
	 * @param title
	 */
	public InventoryWindow(String title, List<Swag>inventory, Client cl){
		mainFrame= new JFrame();
		inven= inventory;

		client =cl;

		IconsPanel ip= new IconsPanel();
		
		JScrollPane jsp= new JScrollPane(ip);
		mainFrame.add(jsp);
	
		mainFrame.pack();
		mainFrame.setTitle(title);
		mainFrame.setSize(260,100);
		mainFrame.setVisible(true);	
	}
	/**
	 * the iconsPanel is the panel upon which all of the 
	 * icons and labels are painted 
	 * @author niallschavez
	 *
	 */
	private class IconsPanel extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public IconsPanel(){

			initButtons();
			initLabels();
			addStuff();

		}
		/**
		 * This method adds the buttons to the Icons Panel 
		 */
		private void addStuff(){
			panels= new ArrayList<JPanel>();

			for(int i=0; i < inven.size(); i++){
				JPanel tempPanel= new JPanel();
				tempPanel.add(buttons.get(i));
				tempPanel.add(equipButtons.get(i));
				tempPanel.add(labels.get(i));

				this.add(tempPanel);
				panels.add(tempPanel);
			}	

		}
		/**
		 * this populates the labels 
		 */
		private void initLabels(){
			labels= new ArrayList<JLabel>();
			Swag item = null;
			for(int i = 0; i < inven.size(); i++) {
				item = inven.get(i);
				JLabel tempLabel= new JLabel("Name: "+item.getName());
				labels.add(tempLabel);
			}
		}
		/**
		 * this initializes the buttons and sets in what should be in 
		 * each button 
		 */
		private void initButtons(){
			buttons= new ArrayList<JButton>();
			equipButtons= new ArrayList<JButton>();
			
			Swag item = null;
			for(int i = 0; i < inven.size(); i++) {
				item = inven.get(i);
				JButton tempButton= new JButton("", new ImageIcon(item.getImage()));
				JButton equipButton = new JButton("Equip");

				EquipButtonListener listener = new EquipButtonListener(item, i);
				equipButton.addActionListener(listener);

				buttons.add(tempButton);
				equipButtons.add(equipButton);
			}
		}
		/**
		 * This does the actuall equipping of the item for use when it is selected 
		 * @author niallschavez
		 *
		 */
		private class EquipButtonListener implements ActionListener {
			private Swag item;

			public EquipButtonListener(Swag e, int x) {
				item = e;
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Equiping item!");
				client.sendEvent(new EquipEvent(client.getClientGameController().getClientID(), item));
		
			}
		}
	}



}

