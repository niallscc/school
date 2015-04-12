package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ChatServer {
	private static JFrame serverFrame;
	private static JTextArea conversationPane;
	private static final String newLine= "\n";
	public static void main(String[] args) throws IOException {
			
	        ServerSocket serverSocket = null;
	        try {
	            serverSocket = new ServerSocket(7773);
	        } catch (IOException e) {
	            System.err.println("Could not listen on port: 7773 (in ChatServer).");
	            System.exit(1);
	        }

	        Socket clientSocket = null;
	        try {
	            clientSocket = serverSocket.accept();
	        } catch (IOException e) {
	            System.err.println("Accept failed.");
	            System.exit(1);
	        }

	        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
	        String inputLine, outputLine;
	        ChatProtocol kkp = new ChatProtocol();
	        createWindow();
	        outputLine = kkp.processInput("Starting Chat");
	        
	        conversationPane.append(outputLine+newLine);
	        out.println(outputLine);
	        
	        while ((inputLine = in.readLine()) != null) {
	             outputLine = kkp.processInput(inputLine);
	             conversationPane.append(outputLine + newLine);
	             
	             out.println(outputLine);
	             if (outputLine.equals("Bye."))
	                break;
	        }
	        out.close();
	        in.close();
	        clientSocket.close();
	        serverSocket.close();
	    }
		private static void createWindow(){
			
			serverFrame= new JFrame();
			serverFrame.setSize(400, 400);
			serverFrame.setDefaultCloseOperation(serverFrame.EXIT_ON_CLOSE);
			serverFrame.setResizable(false);
			JPanel mainPanel= new JPanel();
			conversationPane= new JTextArea(20, 30);
			JTextArea inputPane= new JTextArea(2, 20);
			inputPane.setWrapStyleWord(true);
			conversationPane.setEditable(false);
			
			mainPanel.add(conversationPane);
			mainPanel.add(inputPane);
			serverFrame.add(mainPanel);
			serverFrame.setVisible(true);
		}
		
}
