package chavez_nialls_lab9;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Lab09 
{
	
	public Lab09()
	{
		
	}
	
	private static void createAndShowGUI()
	{
		//Main frame
        JFrame _main = new JFrame();
        _main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
        //Main JPanel
        JPanel mainJP = new JPanel(new BorderLayout());
        
        JPanel jp = new JPanel(new GridLayout(2,2));
       

        mainJP.add(jp, BorderLayout.CENTER);
        
        //Add the JPanel to the Main Frame
        _main.getContentPane().add(mainJP);
        
        
        //Create JButton
        JButton jb = new JButton("RESET");
        
        //TODO Create JLabel
        JLabel jl = new JLabel ("I am a label ");
        
        //TODO Create JTextField
        JTextField jtf = new JTextField();
        
        //Create Radio Buttons
        JRadioButton radio1 = new JRadioButton("Radio 1");
        	radio1.setActionCommand("Radio 1");
        JRadioButton radio2 = new JRadioButton("Radio 2");
        	radio2.setActionCommand("Radio 2");
        JRadioButton radio3 = new JRadioButton("Radio 3");
        	radio3.setActionCommand("Radio 3");
        
        // Create the button Group	
        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        group.add(radio3);
        
        // Set default radio button selection
        radio1.setSelected(true);
        
        //TODO Create JComboBox
        
        
        String[] comboStrings = { "A", "B", "C", "D", "E" };
        JComboBox jcb= new JComboBox(comboStrings);
        

        //Add them to the container
        
        //TODO add JLabel to the mainJP
        jp.add(jl);
        //Add JButton to jp
        jp.add(jb);
       
        //TODO Add JTextField to jp
        jp.add(jtf);
        
        //Create the RadioButton sub Panel and add radio buttons
        JPanel radioPanel = new JPanel();
        jp.add(radioPanel);
        radioPanel.add(radio1);
        radioPanel.add(radio2);
        radioPanel.add(radio3);        
        
        //TODO Add ComboBox to jp
        jp.add(jcb);
        
        // Create event Handler  
        EventHandlerClass eventHandeler = new EventHandlerClass(jl, jtf, jcb, group);
        EventHandlerClassTwo eventHandeler2 = new EventHandlerClassTwo(jl, jtf, jcb, group);
        
        //Add eventHandeler
        //TODO addActionListener to jtf 
        jtf.addActionListener(eventHandeler);
        radio1.addActionListener(eventHandeler);
        radio2.addActionListener(eventHandeler);
        radio3.addActionListener(eventHandeler);
        //TODO addActionListener to jcb 
        jcb.addActionListener(eventHandeler);
        
        //Add Action Listener to JButton
        
        jb.addActionListener(eventHandeler2);
        
        _main.pack();
        _main.setVisible(true);
        
	}
	
	
	
	
	public static void main(String args[])
	{
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });

	}
	

}

/**
 * When a change occurs to the Radio buttons, JTextField, or ComboBox this eventHandler is used to update the JLabel
 * 
 * @author NickM
 *
 */
class EventHandlerClass implements ActionListener
{

	JLabel jl;
	JTextField jtf;
	JComboBox jcb;
	ButtonGroup jbg;
	
	public EventHandlerClass(JLabel label, JTextField txtField, JComboBox comboBox, ButtonGroup bg)
	{
		jl = label;
		jtf = txtField;
	
		jcb = comboBox;
		jbg = bg;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// TODO update the JLabel so that it shows the selections for the JTextField, JRadioButtons, and JComboBox
		
		jl.setText(jtf.getText()+" "+ jcb.getSelectedIndex() +" "+ jbg.getSelection().getActionCommand());
		
		
		// note for the comboBox use jbg.getSelection().getActionCommand() to determine which radio button is active. This only works b/c we set
		// the actionCommand for each radio button to be something different. 
		
		
	}
	
}

/**
 * When the JButton is pressed this event handler is invoked to clear the JLabel 
 * 
 * 
 * @author NickM
 *
 */
class EventHandlerClassTwo implements ActionListener
{

	JLabel jl;
	JTextField jtf;
	JComboBox jcb;
	ButtonGroup jbg;
	
	public EventHandlerClassTwo(JLabel label, JTextField txtField, JComboBox comboBox, ButtonGroup bg)
	{
		jl = label;
		jtf = txtField;
		jcb = comboBox;
		jbg = bg;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		jl.setText("");
		
	}
	
}

