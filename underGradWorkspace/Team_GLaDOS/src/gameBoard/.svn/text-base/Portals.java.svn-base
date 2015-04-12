package gameBoard;

import java.awt.Point;
import java.io.Serializable;
import java.util.HashMap;

@SuppressWarnings("serial")
public class Portals implements Serializable {


	private  HashMap<String, HashMap<String, Point>> portalMap;
	
	
	public void setPortals(HashMap<String, Point> p, String fileName){
		if(portalMap!=null)
			portalMap.put(fileName, p);
		else{
			portalMap= new HashMap<String, HashMap<String, Point>>();
			portalMap.put(fileName, p);
		}
		
	}

	public HashMap<String, HashMap<String, Point>> getPortalmap(){
	
	
		return portalMap;
	}
}
