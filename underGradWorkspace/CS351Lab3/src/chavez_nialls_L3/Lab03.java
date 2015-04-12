package chavez_nialls_L3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.parser.ParserDelegator;



public class Lab03 
{

	private int numParagraphs;

	
	public static void main(String args[])
	{
		new Lab03().dlPage();
	}
	
	/**
	 * Connects to a specific URL "http://java.sun.com/docs/books/tutorial/index.html", downloads the page contents and parses 
	 * the contents for paragraph tags and tables. It invokes a parserCallback object to count the number of paragraph tags
	 * and to print out the attributes of the table tags. 
	 * 
	 */
	public void dlPage()
	{
		
		try 
		{			
			URL url = new URL("http://java.sun.com/docs/books/tutorial/index.html");			
			URLConnection page = url.openConnection();
			page.connect();		

			if(page.getContentType().equals("text/html"))
			{
			
				BufferedReader in = new BufferedReader(new InputStreamReader(page.getInputStream()));					
				ParserCallback callback = new callBack();				
			
				new ParserDelegator().parse(in, callback, false);
				
				System.out.println("numParagraphs: "+ numParagraphs);
			}

		} catch (IOException e ){
			System.out.println("Input Output Exception found exiting.\n");
			System.exit(1);
		}
	}	
	public class callBack extends ParserCallback
	{
		public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos)
		{
			int counter=0;
			if (t.equals(HTML.Tag.TABLE))
			{			
				Enumeration e= a.getAttributeNames();
					while(e.hasMoreElements()){
						counter++;
						System.out.println("Tag attributes "+e.nextElement()+"\n");
						
					}
						
			}
			else if (t.equals(HTML.Tag.P))
			{
				numParagraphs++;

			}
		}		
	
	}
	
}
