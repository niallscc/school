package Lab14;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Lab14 
{

	drawPane jp;
	JPanel jp_Main;
	
	public Lab14()
	{
		Properties p = new Properties();
		try 
		{
	
			p.load(new FileInputStream("config.txt"));
			
			initGUI((String)p.get("Class"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	public ArrayList<Stuff> dynamicLoad(String name)
	{
		ArrayList<Stuff> temp = new ArrayList<Stuff>();
		
		//TODO Get the ClassLoader with getsystemClassLoader(), see the API for ClassLoader
		ClassLoader loader = ClassLoader.getSystemClassLoader();
	
        
		try {
			
			
			//TODO using the ClassLoader and ClassLoader.loadClass() dynamically load the class by 
			//just the class name. ex: the class name for "Smiley.java" is just "Smiley"
			Class c= loader.loadClass("Lab14."+name);
				

			//TODO now that you have a reference to the Class object you need create a new instance of that object
			//use the aptly named method newInstance() to create the instance
			//Be sure to cast the instance to the Stuff interface.
			Stuff stuff=(Stuff)c.newInstance();
	        
	        //TODO add the instance to the ArrayList<Stuff> temp list. 
			temp.add(stuff);
	        
	        
	        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return temp;
       

	}
	
	
	public void initGUI(String CLASS)
    {
		ArrayList<Stuff> s = dynamicLoad(CLASS);
		
    	//Main frame
		JFrame _main = new JFrame();
        _main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        jp_Main = new JPanel(new BorderLayout());
        _main.getContentPane().add(jp_Main);
        
        
        jp = new drawPane(new BorderLayout(), s);
        JButton jb1 = new JButton("Load Smile");
        JButton jb2 = new JButton("Load Frown");
        
        jb1.addActionListener(
                new ActionListener() // anonymous inner class
                {
                   // event handler called when JButton is pressed
                   public void actionPerformed( ActionEvent event )
                   {
                   	
                	   jp.setObj(dynamicLoad("Smiley"));
                	   jp_Main.repaint();
                   	
                	   
                   }

                } // end anonymous inner class

             ); // end call to addActionListener       
      
       
        jb2.addActionListener(
                new ActionListener() // anonymous inner class
                {
                   // event handler called when JButton is pressed
                   public void actionPerformed( ActionEvent event )
                   {
                   	
                	   jp.setObj(dynamicLoad("Frowny"));
                	   jp_Main.repaint();
                   	
                	   
                   }

                } // end anonymous inner class

             ); // end call to addActionListener       
      
       
        
        //Add the JPanel to the Main Frame
        jp_Main.add(jp, BorderLayout.CENTER);
        jp_Main.add(jb1, BorderLayout.EAST);
        jp_Main.add(jb2, BorderLayout.WEST);
        
        _main.setPreferredSize(new Dimension(500,500));
        
        _main.pack();
        _main.setVisible(true);   
    }
	
	
	
	public static void main(String args[])
	{
		new Lab14();
	}
	
}
