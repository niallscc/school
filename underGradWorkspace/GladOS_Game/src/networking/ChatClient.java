package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ChatClient {
	
	private  JFrame clientFrame;
	private  JTextArea convoPane;
	private  final String newLine="\n";
	private  JTextArea inputPane;
	private  String clientInput;
	private static SendObject so;
	
	public static void main(String[] args) throws IOException {
			ChatClient cc= new ChatClient();
	        Socket kkSocket = null;
	        PrintWriter out = null;
	        BufferedReader in = null;
	        
	        InetAddress ip= InetAddress.getByName("129.24.42.67");
	        try {
	        	
	        	
	            kkSocket = new Socket(ip, 7773);
	            out = new PrintWriter(kkSocket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
	       
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host: "+ ip);
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for the connection to: "+ ip);
	            System.exit(1);
	        }
	        
	        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	        
	        String fromServer;
	        String fromUser;
	        /*
	        createWindow();
	        convoPane.append("Beginning Chat"+newLine);*/
	        
	        so = cc.new SendObject();
	        
	        while ((fromServer = in.readLine()) != null) {
	            System.out.println("Server: " + fromServer + cc.newLine);
	            
	            if (fromServer.equals("Bye."))
	                break;
			    
	            fromUser = stdIn.readLine();
	            
	            
		    if (fromUser != null) {
	        //if (clientInput != null) {		
	                System.out.println("Client: " + cc.clientInput + cc.newLine);
	                cc.convoPane.append("Client"+fromUser + cc.newLine);
	                out.println(fromUser);
		    }
	        }

	        out.close();
	        in.close();
	        stdIn.close();
	        kkSocket.close();
	    }
	 /*
	 private static void createWindow(){
			
			clientFrame= new JFrame();
			clientFrame.setSize(400, 400);
			clientFrame.setDefaultCloseOperation(clientFrame.EXIT_ON_CLOSE);
			clientFrame.setResizable(false);
			JPanel mainPanel= new JPanel();
			
			convoPane= new JTextArea(20, 30);
			inputPane= new JTextArea(2, 20);
			
			inputPane.setWrapStyleWord(true);
			convoPane.setEditable(false);
			
			JButton submit= new JButton("send");
			
			
			mainPanel.add(convoPane);
			mainPanel.add(inputPane);
			mainPanel.add(submit);
			
			clientFrame.add(mainPanel);
			clientFrame.setVisible(true);
		}*/
	
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
