package chavez_nialls_lab12;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class Client 
{
	Socket _socket;
	ObjectOutputStream _OUT;
	
	JTextArea jta;
	
	/**
	 * 
	 * 
	 * 
	 * @param hostAddress Address of the Server
	 * @param port Port to connect to the ServerSocket on
	 */
	public Client(InetAddress hostAddress,  int port)
	{
		initConnection(hostAddress, port);
		initGUI();
	}
	
	/*
	 * Creates a new socket to connect to the Server over
	 * Creates a new OutPutStream to write data to the Server
	 * Creates a new ClientListenerThread to listen for server messages 
	 * 
	 */
	private void initConnection(InetAddress host, int port)
	{
		try {
			Socket socket = new Socket(host.getHostName(), port);
			_socket = socket;
			_OUT = new ObjectOutputStream( socket.getOutputStream() );
			
			new ClientListenerThread(_socket, this);
			
			
		} catch (UnknownHostException e) {
		
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	private void initGUI()
    {
    	//Main frame
        JFrame _main = new JFrame();
        _main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        //Main JPanel
        BorderLayout mBL = new BorderLayout();
        mBL.setHgap(5);
        mBL.setVgap(5);
        JPanel jp = new JPanel(mBL);
        JPanel buttonPannel = new JPanel(new BorderLayout(5,5));
        
       
        //Add the JPanel to the Main Frame
        _main.getContentPane().add(jp);
        jp.add(buttonPannel, BorderLayout.SOUTH);
       
        jta = new JTextArea(10, 50);
        jta.setEditable(false);
        final JTextArea input = new JTextArea(1, 50);
       
        final JTextField userName = new JTextField(30);
        userName.setText("Guest");
       
        jp.add(jta, BorderLayout.CENTER);
        buttonPannel.add(input, BorderLayout.NORTH);
        jp.add(userName, BorderLayout.NORTH);
       
        
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(
                new ActionListener() // anonymous inner class
                 {
                    // event handler called when JButton is pressed
                    public void actionPerformed( ActionEvent event )
                    {
                                         	
                    	
                    	String in = input.getText();
                    	input.setText("");
                    	
                    	try {
                    		//Write the object to the output stream on the socket.
							_OUT.writeObject(userName.getText() +": " + in);
						}
                    	catch (SocketException se)
            			{
            				//Here to catch the possibility that the server blows up
                    		//Prevents this from crashing if something goes wrong
            			}
                    	catch (IOException e) {
							
							e.printStackTrace();
						}
                    	
                    }

                 } // end anonymous inner class

              ); // end call to addActionListener   
       
        buttonPannel.add(sendButton, BorderLayout.EAST);

        
        _main.pack();
        _main.setVisible(true);
       
       
    }
	
	
	public static void main(String args[])
	{
	
		try {
			
			final InetAddress host = InetAddress.getLocalHost();
		
			
			SwingUtilities.invokeLater(new Runnable() {
			      // All of this stuff happens on the AWT event processing thread
			      //@Override
			      public void run() {		    	  
			        new Client(host, 7777);
			      }
			    });
			
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} 
		
		
	}
	
}
