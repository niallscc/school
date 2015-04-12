/**
 * 
 */
package actors;

import java.util.ArrayList;
import server.Server;

import gameBoard.FlatMap;

/**
 * 
 * A utility class for calculating Manhatten Distance things
 * @author amrsaad
 * 
 */
public class ManDistance {
	
	/**
	 * The circle list to store all points in the circle radius
	 * from a given center point.
	 */
	private static ArrayList<FlatMap> circlist;
	
	/**
	 * The line list to store all points in the line of
	 * fire. Takes used the caster location and direction.
	 */
	private static ArrayList<FlatMap> linelist;
	
	/**
	 * Method that will get the distance between any two points on a map.
	 * 
	 * @param a The start location.
	 * @param b The end location.
	 * @return The integer distance between the start and end locations.
	 */
	public static int getDistance(FlatMap a, FlatMap b)
	  {
		if(Server.mandistDEBUG) System.out.println("ORIGINAL DISTANCE: ");
		if(Server.mandistDEBUG) System.out.println();
	    int dx1 = Math.abs(a.getX() - b.getX());
	    if(Server.mandistDEBUG) System.out.println("Dx :" +dx1);
	    int dy1 = Math.abs(a.getY() - b.getY());
	    if(Server.mandistDEBUG) System.out.println("DY :" +dy1);
	    if(Server.mandistDEBUG) System.out.println("Distance: " + (dx1+dy1));
	    return (dx1 + dy1);
	  }
	
	/**
	 * Method that will return an ArrayList of Locations that are contained
	 * in the radius given. Will search from the center location passed.
	 * 
	 * @param fm The center location to search from.
	 * @param radius The radius distance to search.
	 * @return circlist The circle list of cells in the vicinity.
	 */
	public static ArrayList<FlatMap> getDistRadius(FlatMap fm, int radius){
		//Initializing the circle list.
		circlist = new ArrayList<FlatMap>();
		
		if(Server.mandistDEBUG) System.out.println("Circle Radius: ");
		if(Server.mandistDEBUG) System.out.println();
		//Go through the square radius and only pick out the distances that are within the radius distances.
		for (int i = fm.getX()-radius; i <= fm.getX()+radius; i++)
			for(int j = fm.getY()-radius; j <= fm.getY() + radius; j++){
				FlatMap distanceto = new FlatMap(i,j);
				if(Server.mandistDEBUG) System.out.println("Square Radius: " + distanceto.getX()+","+distanceto.getY());
				int dist = getDistance(fm,distanceto);
				if(Server.mandistDEBUG) System.out.println("Distance to point from center: " + dist);
				if(dist <= radius && dist != 0 && distanceto.isValid() ){
					if(Server.mandistDEBUG) System.out.println("Circle Radius: " + distanceto.getX()+","+distanceto.getY());
					//Add the location into the array list.
					circlist.add(distanceto);
				}
			}
		
		if(Server.mandistDEBUG) System.out.println("Number of cells included: " +circlist.size());		
		return circlist;
		
	}
	
	/**
	 * Method that will return an arrayList of Locations that are contained in the
	 * line of fire of the given caster.
	 * 
	 * @param caster The location of the caster.
	 * @param dir The direction for line of fire.
	 * @param dist The distance the firing will occur in.
	 * @return linelist The nodes contained in the line of fire.
	 */
	public static ArrayList<FlatMap> getLineDist(FlatMap caster,FlatMap dir, int dist){
		//Initialize the array list for the line.
		linelist = new ArrayList<FlatMap>();
		if(Server.mandistDEBUG) System.out.println("Line Distance: ");
		if(Server.mandistDEBUG) System.out.println();
		
		//If the direction location is to the right add 5 cells to the east.
		if(caster.getX() < dir.getX()){
			for(int i = caster.getX()+1; i <= caster.getX()+dist; i++){
				FlatMap hitto = new FlatMap(i,caster.getY());
				if(hitto.isValid()){
					linelist.add(hitto);
					if(Server.mandistDEBUG) System.out.println("EAST: " + hitto.getX()+","+hitto.getY());
				}
			}
		}
		//If the direction location is to the left add 5 cells to the west.
		else if(caster.getX() > dir.getX()){
			for(int i = caster.getX()-1; i >= caster.getX()-dist; i--){
				FlatMap hitto = new FlatMap(i,caster.getY());
				if(hitto.isValid()){
					linelist.add(hitto);
					if(Server.mandistDEBUG) System.out.println("EAST: " + hitto.getX()+","+hitto.getY());
				}
			}
		}
		//If the direction location is downward add 5 cells to the south.
		else if(caster.getY() < dir.getY()){
			for(int i = caster.getY()+1; i <= caster.getY()+dist; i++){
				FlatMap hitto = new FlatMap(caster.getX(),i);
				if(hitto.isValid()){
					linelist.add(hitto);
					if(Server.mandistDEBUG) System.out.println("EAST: " + hitto.getX()+","+hitto.getY());
				}
			}
		}
		//If the direction location is upward add 5 cells to the north.
		else if(caster.getY() > dir.getY()){
			for(int i = caster.getY()-1; i >= caster.getY()-dist; i--){
				FlatMap hitto = new FlatMap(caster.getY(),i);
				if(hitto.isValid()){
					linelist.add(hitto);
					if(Server.mandistDEBUG) System.out.println("EAST: " + hitto.getX()+","+hitto.getY());
				}
			}
		}
		
		if(Server.mandistDEBUG) System.out.println("Linelist Size: " + linelist.size());
		return linelist;

	}
 
}
