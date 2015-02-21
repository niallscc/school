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

import events.BuyEvent;

/**
 * this Window shows the current inventory for the npc
 * it allows the player to view what they have instock at any given time 
 * @author niallschavez
 *
 */
public class NPCInventoryWindow extends JFrame {
	/**
	 * Used for serialization.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The main window.
	 */
	private JFrame mainFrame;
	/**
	 * Labels: this array list holds a label for each of the items
	 */
	private ArrayList<JLabel> labels= new ArrayList<JLabel>();
	/**
	 * Buttons: this array list holds a button for each of the items, each button 
	 * has the image of that item on it
	 */
	private ArrayList<JButton> buttons= new ArrayList<JButton>();
	/**
	 * The buy button associated with each item.
	 */
	private ArrayList<JButton> buyButtons = new ArrayList<JButton>();
	/**
	 * the inventory of the player
	 */
	private List<Swag>inven;
	/**
	 * The client, it is used to send events
	 */
	private Client client;


	/**
	 * initializer method, takes in the title for the GUI, and does initializations 
	 * @param title
	 */
	public NPCInventoryWindow(String title, List<Swag>inventory, Client client){
		mainFrame= new JFrame();
		inven= inventory;
		
		this.client = client;

		IconsPanel ip= new IconsPanel();
		JScrollPane jsp= new JScrollPane(ip);
		mainFrame.add(jsp);
		mainFrame.pack();
		mainFrame.setTitle(title);
		mainFrame.setSize(260,100);
		mainFrame.setVisible(true);	
	}
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
			for(int i=0; i < inven.size(); i++){
				JPanel tempPanel= new JPanel();
				tempPanel.add(buttons.get(i));
				tempPanel.add(buyButtons.get(i));
				tempPanel.add(labels.get(i));
				
				this.add(tempPanel);
			}	

		}

		private void initLabels(){
			for(Swag i: inven){
				JLabel tempLabel= new JLabel("Name: "+i.getName()+" Cost: "+i.getVendorCost());
				labels.add(tempLabel);
			}
		}

		private void initButtons(){
			for(Swag i: inven){
				JButton tempButton= new JButton("", new ImageIcon(i.getImage().getScaledInstance(25, 25, 0)));
				
				JButton buyButton = new JButton("Buy this item");
				BuyButtonListener listener = new BuyButtonListener(i);
				buyButton.addActionListener(listener);
				
				buttons.add(tempButton);
				buyButtons.add(buyButton);
			}
		}
	}
	
	private class BuyButtonListener implements ActionListener {
		private Swag item;
		
		public BuyButtonListener(Swag e) {
			item = e;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			client.sendEvent(new BuyEvent(client.getClientGameController().getClientID(), item));
		}
	}
}
