package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextArea;



public class GameClient {
	private static JFrame clientFrame;
	private static JTextArea convoPane;
	private static final String newLine="\n";
	private static JTextArea inputPane;
	private static String clientInput;
	
	public static void main(String[] args) throws IOException {
			ChatClient cc= new ChatClient();
	        Socket kkSocket = null;
	        PrintWriter out = null;
	        BufferedReader in = null;

	        try {
	            kkSocket = new Socket("localhost", 7773);
	            out = new PrintWriter(kkSocket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host: localHost.");
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for the connection to: taranis.");
	            System.exit(1);
	        }
	        
	        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	        String fromServer;
	        String fromUser;
	        
	        
	        
	        convoPane.append("Beginning Chat"+newLine);
	        
	        while ((fromServer = in.readLine()) != null) {
	            System.out.println("Server: " + fromServer + newLine);
	            
	            if (fromServer.equals("Bye."))
	                break;
			    
	            fromUser = stdIn.readLine();
		   
	        if (clientInput != null) {		
	                System.out.println("Client: " + clientInput + newLine);
	                convoPane.append("Client"+clientInput + newLine);
	                out.println(fromUser);
	                clientInput=null;
		    }
	        }

	        out.close();
	        in.close();
	        stdIn.close();
	        kkSocket.close();
	    }	
		private class sendObject{
			
		}
	 


}
