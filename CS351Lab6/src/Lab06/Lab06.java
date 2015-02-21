package Lab06;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lab06 
{
	
	public static void main (String args[])
	{
		new Lab06();
	}
	
	public Lab06()
	{
		run(new File("Integer.html"));
	}
	
	public void run(File f)
	{
		try
		{
			
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			int c = br.read();
			
			State currentState = new StateZero();
			
			while (c != -1)
			{				
				currentState = currentState.execute((char) c);
				
				c = br.read();				
			}
			
			
		}
		catch(FileNotFoundException e)
		{
			System.exit(1);
		}
		catch(IOException e)
		{
			System.exit(1);
		}
		
	}

	
	class StateZero implements State
	{
		public StateZero()
		{
			
		}
		
		public State execute(char c)
		{
			if( c =='<' ){
				
				State s= new StateOne();
				return s;
			}
			else{
				
				State s= new StateZero();
				return  s;
			}
		}
	}
	
	class StateOne	implements State
	{
		public StateOne()
		{
			
		}
		
		public State execute(char c)
		{
			if( c=='a'){
				State s= new StateTwo();
				return s;
			}
			else 
			{
				State s= new StateZero();
				return s;
			}
		}
	}
	
	
	class StateTwo implements State
	{
		ArrayList<String>  al;
		
		public StateTwo()
		{
			al = new ArrayList<String>();
		}
		
		public State execute(char c)
		{
			State s;
			if( c !='>'){
				al.add(c+"");
				s = this;
			}
			else 
			{
				System.out.println(al.toString());
				s= new StateZero();
			}

			return s;
		}
	}
	
}
