package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Controller;


	@SuppressWarnings("serial")
	public class GUI extends JFrame{
		
		public GUI(String title){
			/*
			 * Initial set up for the window 
			 */
			this.pack();
			this.setTitle(title);
			this.setSize(325,350);
			this.setVisible(true);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setResizable(false);
			GUIPanel gp= new GUIPanel();
			this.add(gp);
			paintComponents(getGraphics());

			 
		}
		public class GUIPanel extends JPanel{
			/*
			 * The controller is what does all the heavy lifting, it takes the input
			 * and from there runs it through the loop comparing each creature 
			 * to another creature, and calculating the ending population. 
			 * 
			 */
			LinkedList <String> populationsInit= new LinkedList<String>();
			LinkedList <String> populations= new LinkedList<String>();
			LinkedList <String[]> dataList= new LinkedList<String[]>();
			LinkedList <String> repThreshAndChilds= new LinkedList<String>();
			Controller c;
			
			/*
			 * these are the creature input parameters
			 */
			
			JTextField c11= new JTextField(3);
			JTextField c12a= new JTextField(3);
			JTextField c12b= new JTextField(3);
			JTextField c21a= new JTextField(3);
			JTextField c21b= new JTextField(3);
			JTextField c22= new JTextField(3);
			JTextField rep= new JTextField(3);
			JTextField child= new JTextField(3);
			/*
			 * population for creature 1 and 2 respectively 
			 * 
			 */
			String Pop1S="500", Pop2S="500";
			String repS= "4", nC= "4", c1c1s= "5", c1c2as="1",
				        c1c2bs= "8", c2c1as= "8", c2c1bs= "1",
				        c2c2s= "3";
			JTextField pop1= new JTextField(3);
			JTextField pop2= new JTextField(3);
			
			
			public GUIPanel(){
				/*
				 * Painting 
				 */
				JLabel start= new JLabel("Please input the fitness for each Creature:");
				this.add(start);
				EH heyListen = new EH();
				pop1.setText(Pop1S);
				pop2.setText(Pop2S);
				rep.setText(repS);
				child.setText(nC);
				c11.setText(c1c1s);
				c12a.setText(c1c2as);
				c12b.setText(c1c2bs);
				c21a.setText(c2c1as);
				c21b.setText(c2c1bs);
				c22.setText(c2c2s);
				
				
				JLabel popLab1= new JLabel("Population Creature 1                    ");
				JLabel popLab2= new JLabel("Population Creature 2:                   ");
				JLabel reproduction= new JLabel("Reproduction Threshold:                ");
				JLabel numChildren = new JLabel("Max Number of children:               ");
				JLabel c1c1= new JLabel("Creature 1 vs Creature 1:               ");
				JLabel c1c2= new JLabel("Creature 1 vs Creature 2: ");
				JLabel c2c1= new JLabel("Creature 2 vs Creature 1: ");
				JLabel c2c2= new JLabel("Creature 2 vs Creature 2:               ");
				
				
				JButton submit = new JButton("Submit");
			
				this.add(popLab1);
				this.add(pop1);
				this.add(popLab2);
				this.add(pop2);			
				
				this.add(reproduction);
				this.add(rep);
				this.add(numChildren);
				this.add(child);
				this.add( c1c1);
				this.add( c11);

				this.add( c1c2);
				this.add( c12a);
				this.add( c12b);
				
				this.add( c2c1);
				this.add( c21a);
				this.add( c21b);
				
				this.add( c2c2);
				this.add( c22);
				
				this.add(submit);
				/*
				 * actionlistener
				 */
				submit.addActionListener(heyListen);

			}
			private class EH implements ActionListener{
				@Override
				public void actionPerformed(ActionEvent arg0) {
					/*
					 * this throws everything into an array then sends it over to controller
					 */
					String[] data= { c11.getText(), c12a.getText(),
									 c12b.getText(),c21a.getText(),c21b.getText(),c22.getText()}; 
					populationsInit.add(pop1.getText()+ " "+pop2.getText());
					
					repThreshAndChilds.add(rep.getText()+" "+child.getText());
					dataList.add(data);
					/*
					 * This gets the text out of the population boxes.
					 */
					String popC1= pop1.getText();
					String popC2= pop2.getText();
					
					
				    /*
				     * The controller object does the main control loop and calculates the probabilites for 
				     * each run of the game. 
				     */
					c= new Controller(data, popC1, popC2, rep.getText(), child.getText());	
				    
					int again=0, save=0;
					/*
					 * this checks if the populations are at or below zero. if they are 
					 * obviously we dont want to keep running with negative populations
					 */
					if(c.getPopC1() <=0 || c.getPopC2()<=0){
						JOptionPane.showConfirmDialog(new JFrame(),c.getEndState()+"\n The population has dropped below zero.","Game Over", again);
						save= JOptionPane.showConfirmDialog(new JFrame(),"Save results?\n","Save?", save);
						if (save==0)
							try {
								writeFile();
								GraphMaker gm= new GraphMaker("SimOutput.txt");
							} catch (IOException e) {
								e.printStackTrace();
							}
						
						System.exit(0);
					}
					
					again = JOptionPane.showConfirmDialog(new JFrame(),c.getEndState()+"\nSimulate again?","Again?", again);
					if( again != 0){
						/*
						 * Once the game has ended, we are given the ability to 
						 * save the output or not 
						 */
						save= JOptionPane.showConfirmDialog(new JFrame(),"Save results?\n","Save?", save);
						if (save==0)
							try {
								writeFile();
								GraphMaker gm= new GraphMaker("SimOutput.txt");
							} catch (IOException e) {
								e.printStackTrace();
							}
						
						System.exit(0);
					}
					else
					{
						/* 
						 * adding the populations into the Linked List and setting in the 
						 * populations for the next generation
						 */
						populations.add(c.getPopC1()+" "+c.getPopC2());
						pop1.setText(c.getPopC1()+"");
						pop2.setText(c.getPopC2()+"");
					}
				}
				
				private void writeFile() throws IOException {
					Writer out = new OutputStreamWriter(new FileOutputStream("SimOutput.txt"));
					try {
							int lengthList= populations.size();
						    for(int i=0; i < lengthList; i++ ){
						    	/*
						    	 * Initial Population
						    	 */
						    	out.write(populationsInit.get(i)+" ");
						    	out.write(repThreshAndChilds.get(i)+ " ");
						    	
						    	for( int j=0; j < dataList.get(i).length; j++ )
						    		out.write(dataList.get(i)[j] +" ");
						    	
						    	out.write(populations.get(i)+ " ");
						    	out.write("\n");
						    	
						    }
						    
						}
						finally {
						      out.close();
						}	
				}
			}
		}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GUI g = new GUI("Evolutionary Game Theory");
	}

}
