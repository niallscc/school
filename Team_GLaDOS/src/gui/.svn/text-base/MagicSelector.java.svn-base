/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * this GUI is invoked when the character levels up and provides a 
 * nice way for the player to pick a new spell and update his abilities 
 * @author niallschavez
 *
 */

public class MagicSelector extends JFrame {
	/**
	 * Used for serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	
	JCheckBox Armageddon, Blizzard, Fire, FlameThrower, FreezeRay, Fly,
	GoodDrugs, GreatDrugs, IFMBN, Zap, Zorch, Zot; 
	JButton submitButton;
	public MagicSelector(String title){
		

		mainPanel mp= new mainPanel();
		this.add(mp);
		
		this.pack();
		this.setVisible(true);
		this.setTitle(title);
		this.setSize(400, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	private class mainPanel extends JPanel{
		
		/**
		 * Used for Serialization.
		 */
		private static final long serialVersionUID = 1L;

		public mainPanel(){
			this.setCheckBoxes();
		}
		
		private boolean checkIsSelected(String name){
			//TODO 
			return false;
		}
		
		/**
		 * these checkboxes are representative of each magic spell and provide text next to them 
		 * they are checked to see if they are already in theplayers arsenal of attacks 
		 */
		private void setCheckBoxes(){
			Armageddon= new JCheckBox("Armageddon", checkIsSelected("Armageddon"));
			Blizzard= new JCheckBox("Blizzard", checkIsSelected("Blizzard"));
			Fire= new JCheckBox("Fire", checkIsSelected("Fire"));
			FlameThrower= new JCheckBox("Flame  Thrower", checkIsSelected("FlameThrower"));
			Fly= new JCheckBox("Fly", checkIsSelected("Fly"));
			FreezeRay= new JCheckBox("Freeze Ray", checkIsSelected("FreezeRay"));
			GoodDrugs= new JCheckBox("Good Drugs", checkIsSelected("GoodDrugs"));
			GreatDrugs= new JCheckBox("Great Drugs", checkIsSelected("GreatDrugs"));
			IFMBN= new JCheckBox("I.F.M.B.N", checkIsSelected("IFMBN"));
			Zap= new JCheckBox("Zap", checkIsSelected("Zap"));
			Zorch= new JCheckBox("Zorch",checkIsSelected("Zorch"));
			Zot= new JCheckBox("Zot",checkIsSelected("Zot"));
			
			
			
			submitButton= new JButton("Submit");
			EventHandlerSubmit ehs= new EventHandlerSubmit();
			submitButton.addActionListener(ehs);
			
			this.add(Armageddon);
			this.add(Blizzard);
			this.add(Fire);
			this.add(FlameThrower);
			this.add(Fly);
			this.add(FreezeRay);
			this.add(GoodDrugs);
			this.add(GoodDrugs);
			this.add(GreatDrugs);
			this.add(IFMBN);
			this.add(Zap);
			this.add(Zorch);
			this.add(Zot);
			
			this.add(submitButton);
				
		}

		
	}
	
	/**
	 * TODO save the new list of magic abilities 
	 * @author niallschavez
	 *
	 */
	public class EventHandlerSubmit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//TODO write teh new magic selection to the save file
			
		}
		
	}
	
	/**
	 * @param args
	 */	
	public static void main(String[] args) {
		new MagicSelector("Magic Selector");
	}

}
