package solver;

import java.util.ArrayList;
public class Graph {
	int length=0; 
	Tuple start, finish;
	ArrayList<Tuple> graph;
	Graph(ArrayList<String> mazeIn, int l){
		length=l;
		ArrayList<Tuple> tupleList= normalize(mazeIn);
		setGraph(tupleList);
		createAdjacencyList(tupleList);
	}
	/**
	 * This Method will return an adjacency list of tuples for the tuple passed into it. 
	 */
	ArrayList<Tuple> getAdjacencyList(Tuple t){
		return t.getNeighbors();
	}
	/**
	 * This turns the input from the user into a form that the program can understand.
	 * Essentially it turns the symbols into tuples. 
	 */
	private ArrayList<Tuple> normalize(ArrayList<String> mazeIn){
		ArrayList<Tuple> tupleList= new ArrayList<Tuple>();
		int x=0, y=0; 
		int counter=0;
		for(String s: mazeIn){
			for(int i = 0; i< s.length(); i++){
				
               String item = s.substring(i,i+1);

               Tuple t= new Tuple(x, y, item);
               if(item.equals("S")){
            	   setStart(t);
               }
               if(item.equals("F")){
            	   setFinish(t);
               }
               tupleList.add(t);
               t.setIndex(counter);
               counter++;
               y++;
             }
			y=0;
			x++;
		}
		return tupleList;
	}
	/**
	 * This function sets into the tuple class, for each walkable element, it sets the walkable elements
	 * that surround that node
	 */
	private void createAdjacencyList(ArrayList<Tuple> tupleList) {

		for(Tuple t: tupleList){
			String item= t.getValue();
			if( !item.equals("#")){//If the item is a wall we dont want to do any of this, we essentially ignore it here. 
				
				if(item.equalsIgnoreCase(".")){ //If the item is part of a path then create adjacency list. 
					setAdjacencies(t, tupleList);
				}
				else if(item.equalsIgnoreCase("S")){//If the item is the start node set it as such. 
					t.setStart();
					setAdjacencies(t, tupleList);
				}
				else if(item.equalsIgnoreCase("F")){//If the item is the finish node set it as such.
					t.setFinish();
					setAdjacencies(t, tupleList);
				}	
			}
		}
	}
	/**
	 * sets into the tuple of the list a set of  
	 * paths around it that are traversable. 
	 * This is a helper function for createAdjacencyList
	*/
	private void setAdjacencies(Tuple t, ArrayList<Tuple> tList){
		ArrayList<Tuple>adj= new ArrayList<Tuple>();
		int index= t.getIndex();
		int size=tList.size();

		//the nodes to the left and right of the given node
		if( (index-1) > 0 ){
			if( !tList.get(index-1).getValue().equals("#"))
				adj.add( tList.get(index-1) );
		}
		if( (index+1) <=size ){
			//System.out.println("Value on the right is "+ tList.get(index+1).getValue());
			if(! tList.get(index+1).getValue().equals("#"))
				adj.add(tList.get(index+1));
		}
		//top
		if( (index-length) > 0 ){
			if(!tList.get(index-(length)).getValue().equals("#"))
				adj.add(tList.get(index-length));
		}
		//bottom
		if( (index+length) < size ){
			if(!tList.get(index+(length)).getValue().equals("#"))
				adj.add(tList.get(index+length));
		}
		
		t.setNeighbors(adj);
		
	}

	private void setStart(Tuple t){
		start=t;
	}
	private void setFinish(Tuple t){
		finish=t;
	}
	/**
	 * This returns the start node, EG. The tuple that is deginated "S"
	 * @return start Tuple 
	 */
	Tuple getStart(){
		return start;
	}
	/**
	 * This returns the finish node, EG. The tuple that is designated "F"
	 * @return Finish node 
	 */
	Tuple getFinish(){
		return finish;
	}
	
	private void setGraph(ArrayList<Tuple> tupleList) {
		graph=tupleList;
		
	}
	/**
	 * returns the graph that is created from normalize; 
	 * @return a graph representation of the matrix.
	 */
	public ArrayList<Tuple> getGraph(){
		return graph;
	}
}
