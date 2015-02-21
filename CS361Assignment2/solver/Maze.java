package solver;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author niallschavez
 *
 */

public class Maze {
	LinkedList<Tuple> path=new LinkedList< Tuple>(); 
	Tuple start, finish; 
	ArrayList<Tuple> graph;

	boolean done=false;
	/**	
  	   Constructor for maze class, this takes in a maze and calls all the
	   methods needed to solve the maze
	*/
	Maze(ArrayList<String> mazeIn, int length){
		Graph g= new Graph(mazeIn,length );
		start=g.getStart();
		finish=g.getFinish();
		graph=g.getGraph();
	}
	
	LinkedList<Tuple> solve(){
		path.add(start);
		start.explored();
		path = directions(DFS(start));
		return path;
	}


	private LinkedList<Tuple> directions(LinkedList<Tuple> dfs) {
		LinkedList<Tuple> temp= new LinkedList<Tuple>();
		while(dfs.size() > 1){
			Tuple first= dfs.pop();
			Tuple second= dfs.peek();
		
			if( first.getX() < second.getX() )
				first.setDirection('S');
			else if( first.getX() > second.getX())
				first.setDirection('N');
			else if( first.getY() < second.getY())
				first.setDirection('E');
			else if( first.getY() > second.getY())
				first.setDirection('W');
			
			temp.add(first);
		}
		/*
		 * fix for the finish
		 * 
		 */
		Tuple last= dfs.pop();
		last.setDirection('F');
		temp.add(last);

		
		
		return temp;
	}

	/** sudo code DFS
	DFS(u) :
		Mark u as "Explored" and add u to R 
		For each edge (u,v) incident to u
					If v is not marked "Explored" then 
		Recursively invoke DFS(u)
					Endif 
		Endfor 
	*/
	private LinkedList<Tuple> DFS(Tuple s){
		//System.out.println("The neighbors of: "+s.toString()+" are: "+s.getNeighbors().toString());
		
		for(Tuple t: s.getNeighbors()){		
			if (!t.isExplored() ){
				if(t.getValue().equals("F")){
					//System.out.println("Finish Found");
					path.add(t);
					//System.out.println(path.contains(t));
					done=true;
				}
				t.explored();
				if(!done){
					path.add(t);
					DFS(t);
					if(done==false)
						path.removeLast();
				}
			}
		}
		return path; 
	}
	
}
