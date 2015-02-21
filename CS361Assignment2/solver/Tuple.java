package solver;

import java.util.ArrayList;

public class Tuple {
	private int xCord, yCord, index;
	private String val;
	private boolean start=false, finish= false, explored=false;
	private ArrayList<Tuple> neighbors;
	private char direction;
	
	Tuple(int x, int y, String value){
		xCord= x; 
		yCord= y;
		val=value;
	}
	int getX(){
		return xCord;
	}
	int getY(){
		return yCord;
	}
	String getValue(){
		return val;
	}
	void setStart(){
		start=true;
	}
	boolean isStart(){
		return start;
	}
	void setFinish(){
		finish=true;
	}
	boolean isFinish(){
		return finish;
	}
	void setNeighbors(ArrayList<Tuple> n){
		neighbors= n;
	}
	ArrayList<Tuple> getNeighbors(){
		return neighbors;
	}
	void setIndex(int i){
		index=i;
	}
	int getIndex(){
		return index;
	}
	@Override
	public String toString(){
		//return ""+direction+": ("+xCord+", "+yCord+")";
		return direction+" ";
	}
	public void explored(){
		explored=true;
	}
	public boolean isExplored(){
		return explored; 
	}
	public void setDirection(char c){
		direction=c;
	}
	public char getDirection(){
		return direction;
	}
	
}

