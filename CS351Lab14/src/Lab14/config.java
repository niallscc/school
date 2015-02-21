package Lab14;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class config 
{

	public config()
	{
		Properties p = new Properties();
		p.put("Class", "Frowny");
		
		//BufferedOutputStream os = new BufferedOutputStream(new OutputStream());
		
		
		
		try {
			FileOutputStream os = new FileOutputStream("config.txt");
			p.store(os, "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		new config();
	}
	
}
