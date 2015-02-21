package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import actors.NPC;

import server.Client;
/**
 * this is the frame that allows the User to interact with an NPC in the game 
 * @author niallschavez
 *
 */
public class NPCInteractionGUI extends JFrame {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Button used for talking.
	 */
	private JButton talk = new JButton("Talk");
	/**
	 * Buy button
	 */
	private JButton buy = new JButton("Buy");
	/**
	 * Sell button
	 */
	private JButton sell = new JButton("Sell");
	/**
	 * The client object to send events with.
	 */
	private Client client;

	public NPCInteractionGUI(Client cl, NPC vendor) {
		client = cl;

		JPanel panel = new JPanel();
		JLabel label = new JLabel("Welcome to Mike's loot shop. Select an interaction.");

		VendorButtonListener listener = new VendorButtonListener(client, vendor);

		talk.addActionListener(listener);
		buy.addActionListener(listener);
		sell.addActionListener(listener);

		panel.add(label);
		panel.add(talk);
		panel.add(buy);
		panel.add(sell);

		this.add(panel);

		this.pack();
		this.setTitle("NPC Interaction");
		this.setSize(350, 75);
		this.setVisible(true);	
	}
	private class VendorButtonListener implements ActionListener {
		/**
		 * The client used to send events with.
		 */
		private Client client;
		/**
		 * The vendor
		 */
		private NPC npc;
		/**
		 * Constructs a new VendorButtonListener and sets the associated Client object
		 * @param cl The client object to send events with.
		 */
		public VendorButtonListener(Client cl, NPC vendor) {
			npc = vendor;
			client = cl;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand() == "Talk")
				talkToVendor();
			else if(arg0.getActionCommand() == "Buy")
				buyFromVendor();
			else if(arg0.getActionCommand() == "Sell")
				sellToVendor();
		}
		/**
		 * 
		 */
		public void buyFromVendor() {
			//Do some more checking, make sure it's not null
			if(npc != null) {	
				new NPCInventoryWindow("NPC Inventory", npc.getInventory(), client);	
			}
		}
		/**
		 * 
		 */
		public void sellToVendor() {
			//Again.. Make sure there's even an NPC...
			if(npc != null) {	
				new SellInventoryWindow("NPC Inventory", this.client.getClientGameController().getPC().getInventory(), client);	
			}
		}
		public void talkToVendor() {
			Random r= new Random();
			int rand=r.nextInt(5);
			if(rand==0 )
				JOptionPane.showMessageDialog(NPCInteractionGUI.this,"Things around these parts aint the same these days... Them portals do weird stuff when you walk onto them and press t.");	
			else if(rand==1)
				JOptionPane.showMessageDialog(NPCInteractionGUI.this,"You'd better watch out for all 'dem monstrostities runnin' around, especially that bugger with a bunch o' eyes! He'll eat'cha!");
			else if( rand==2)
				JOptionPane.showMessageDialog(NPCInteractionGUI.this,"What, do you not have anythin' better to do than talk to a stick figure who somewhat resembles Conan O'Brien?!");	
			else if( rand==3)
				JOptionPane.showMessageDialog(NPCInteractionGUI.this,"Don't forget, when you want to check out yer' stuff press the \"i\" button,  ");
			else if( rand==4)
				JOptionPane.showMessageDialog(NPCInteractionGUI.this,"What do you think yer doin?! you should be killin dem zombies, not talkin' to me!");	
		}

	}
}
