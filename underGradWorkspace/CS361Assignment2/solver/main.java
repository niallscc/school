package solver;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 
 * This Program is a maze solver. It takes in a maze from either a file or by Command line arguments
 * this determination is set by a flag here in main. 
 * @author niallschavez
 *
 */
public class main {
	/**
	 * @param args
	 */
	static ArrayList<String> maze= new ArrayList<String>();
	static int length=0;
	
	public static void main(String[] args) {
		InputStream i= System.in;
		
		if(args.length > 1 && args[0].equals("-f")){
			try { 
				i = new FileInputStream(args[1]);
			}
			catch (Exception e){//Catch exception if any
				System.err.println("Error: " + e.getMessage());
			}	   
		}		
		
		maze=fileInput(i);
		maze= normalize(maze, length);
		Maze m= new Maze(maze, length);
		
		LinkedList<Tuple> path= m.solve();
		Tuple[] t = new Tuple[path.size()];
		path.toArray(t);
		
		if( path.get(path.size()-1).getValue().equals("F"))
			System.out.println("The path from start to finish is: "+path.toString() );
		else {
			System.out.println("Could not find a solution");
		}
	}

	private static ArrayList<String> fileInput(InputStream is) {
		try{
			//FileInputStream fstream = new FileInputStream(fFileName);
			DataInputStream in = new DataInputStream(is);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			maze.add("#");
			while ((strLine = br.readLine()) != null){
				//System.out.println(strLine);
				maze.add("#"+strLine+"#");
				if(strLine.length() > length)
					length=strLine.length()+2;
			}
			maze.add("#");
			//Close the input stream
			in.close();
			}
		catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}	   
		return maze;
	}



	/**
	 * This method normalizes the graph, so if there are lenghs of different sizes it pads them with walls
	 * @param maze the maze from the input
	 * @param length the length of the longest line
	 * @return the normalized maze 
	 */
	private static ArrayList<String> normalize(ArrayList<String> maze, int length ) {
		ArrayList<String> temp= new ArrayList<String>();	
		for(String s: maze){
					while( s.length() < length){
						s+="#";
				}
				//System.out.println(s);
				temp.add(s);
			}
		return temp;
	}
}