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

import events.SellEvent;

/**
 * This window allows the PC to sell inventory to the NPC 
 * it is invoked when the player selects sell inventory on the NPC 
 * interaction GUI 
 * @author niallschavez
 *
 */
public class SellInventoryWindow extends JFrame {
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
	ArrayList<JButton> buyButtons = new ArrayList<JButton>();
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
	public SellInventoryWindow(String title, List<Swag>inventory, Client cl){
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
				tempPanel.add(buyButtons.get(i));
				tempPanel.add(labels.get(i));

				this.add(tempPanel);
				panels.add(tempPanel);
			}	

		}

		private void initLabels(){
			labels= new ArrayList<JLabel>();
			Swag item = null;
			for(int i = 0; i < inven.size(); i++) {
				item = inven.get(i);
				JLabel tempLabel= new JLabel("Name: "+item.getName()+" Sell value: "+item.getVendorWorth());
				labels.add(tempLabel);
			}
		}

		private void initButtons(){
			buttons= new ArrayList<JButton>();
			buyButtons= new ArrayList<JButton>();
			
			Swag item = null;
			for(int i = 0; i < inven.size(); i++) {
				item = inven.get(i);
				JButton tempButton= new JButton("", new ImageIcon(item.getImage().getScaledInstance(25, 25, 0)));
				JButton sellButton = new JButton("Sell this item");

				SellButtonListener listener = new SellButtonListener(item, i);
				sellButton.addActionListener(listener);

				buttons.add(tempButton);
				buyButtons.add(sellButton);
			}
		}



		private class SellButtonListener implements ActionListener {
			private Swag item;
			private int index;

			public SellButtonListener(Swag e, int x) {
				item = e;
				index = x;
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Selling item!");
				client.sendEvent(new SellEvent(client.getClientGameController().getClientID(), item, index));
		
				IconsPanel.this.remove(panels.get(index));
				IconsPanel.this.repaint();
			}
		}
	}



}
