package networking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Client extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel text, clicked;
	JButton sendTextButton;
	JButton sendObjectButton;
	
	JPanel panel;
	JTextField textField;
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;

	public Client() { 
		this.setTitle("Server");
		this.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		text = new JLabel("Text to send over socket:");
		textField = new JTextField(20);
		sendTextButton = new JButton("Send Text");
		sendObjectButton = new JButton("Send Object");
		
		
		sendTextButton.addActionListener(this);
		sendObjectButton.addActionListener(this);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.white);
		getContentPane().add(panel);
		panel.add("North", text);
		panel.add("Center", textField);
		panel.add("South", sendTextButton);
		panel.add("West", sendObjectButton);
	} 

	public void actionPerformed(ActionEvent event){
		
		if(event.getSource().equals(sendTextButton)){
			//Send data over socket
			String text = textField.getText();
			out.println(text);
			textField.setText(new String(""));
			//Receive text from server
			try{
				String line = in.readLine();
				System.out.println("Text received :" + line);
			} catch (IOException e){
				System.out.println("Read failed");
				System.exit(1);
			}
		}
		
		else if( event.getSource().equals(sendObjectButton)){
			System.out.println("Object Sending...");
			OutputStream os;
			try {
				os = socket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);  
				SendObject so= new SendObject();  
				so.setMessage(textField.getText());
				
				oos.writeObject(so);  
				oos.close();  
				os.close();  
		
			} catch (IOException e) {
				System.err.println("Object Send Error");
				e.printStackTrace();
			}  
			
			System.out.println("Object Sent!");
		}
	}

	public void connect(){
		InetAddress ip=null;
		try{
			ip = InetAddress.getByName("129.24.42.242");
			socket = new Socket(ip, 7773);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: "+ip);
			System.exit(1);
		} catch  (IOException e) {
			System.out.println("No I/O");
			System.exit(1);
		}
	}

	public static void main(String[] args){
		Client frame = new Client();

		frame.pack();
		frame.setVisible(true);
		frame.connect();
	}
	
	class SendObject implements Serializable  {
		
		private String messageOut;
		public void setMessage(String message){
			messageOut= message;
		}
		
		public String getMessage(){
			return messageOut;
		}
	}
}
