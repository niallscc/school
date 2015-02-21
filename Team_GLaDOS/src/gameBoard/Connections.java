package gameBoard;

import java.awt.Point;
import java.io.Serializable;
import java.util.HashMap;

public class Connections implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<HashMap<String, Point>, HashMap<String,  Point>> connectionMap;
	
	public HashMap<HashMap<String, Point>, HashMap<String,  Point>>  getConnections(){
		return connectionMap;
	}
	public void setConnection(String fromFileName, String toFileName, Point fromPoint, Point toPoint){
		
		HashMap<String, Point> fromMap= new HashMap<String, Point>();
		HashMap<String, Point> toMap= new HashMap<String, Point>();
		
		fromMap.put(fromFileName, fromPoint);
		toMap.put(toFileName, toPoint);
		connectionMap=new HashMap<HashMap<String, Point>, HashMap<String,  Point>>();
		connectionMap.put( fromMap, toMap);
		
	}
	public HashMap<String, Point> getConnections(String filename, Point fromLocation){
		
		HashMap<String, Point > tempMap= new HashMap<String,Point>();
		tempMap.put(filename, fromLocation);
		
		return connectionMap.get(tempMap);
		
	}
	
	
	
}
