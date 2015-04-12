/**
 * 
 */
package actors;

import java.util.ArrayList;
import java.util.List;


import gameBoard.FlatMap;
import gameBoard.TerrainCell;
import gameBoard.TerrainMap;

/**
 * @author amrsaad
 *	Class representing a node to be used in the Astar path finding.
 *
 */
public class AInode{

	/**
	 * The location of the node.
	 */
private FlatMap location;

/**
 * The location of the parent of the node.
 */
private FlatMap parent;
/**
 * The G value of this nodel.
 */
private int g;
/**
 * The H value of this node.
 */
private int h;
/**
 * The F value of this node.
 */
private int f;

/**
 * The terrain cost of this node.
 */
private int cost;

/**
 * Constructer for the AInode which initializes all values to their initial state.
 */
public AInode(TerrainCell startingCharCell)
{
  this.location = startingCharCell.getCoordinates();
  this.parent = null;
  this.cost = startingCharCell.getTerrainCost();
  this.g = 0;
  this.h = 0;
  this.f = 0;
}

/**
 * Returns the location or coordinates of this specific node.
 * 
 * @return location Location.
 */
public FlatMap getCoordinates()
{
  return this.location;
}

/**
 * Get the parent location of this node.
 * @return parent Location of Parent.
 */
public FlatMap getParent()
{
  return this.parent;
}

/**
 * Get the G value of this node.
 * @return g for the G value.
 */
public int getG()
{
  return this.g;
}

/**
 * Get the H value of this node
 * @return h for the H value.
 */
public int getH()
{
  return this.h;
}

/**
 * Get the F value for this node.
 * 
 * @return return f for the F value
 */
public int getF()
{
  return this.f;
}

/**
 * Get the terrain cost for this node.
 * @return cost Cost of the node.
 */
public int getCost()
{
  return this.cost;
}

/**
 * Return a  list of the neighbors of this node.
 * @param height height of the map.
 * @param width width of the map
 * @return result List of FlatMap Locations used to hold neighbors.
 */
public List<FlatMap> getNeighbors(int height, int width)
{
  List<FlatMap> result = new ArrayList<FlatMap>();

  if(this.location.getY()-1  > 0){
  result.add(
    new FlatMap(this.location.getX(), 
    (this.location.getY() - 1 )));
  }

  if(this.location.getY() + 1 < height){
  result.add(
    new FlatMap(this.location.getX(), 
    (this.location.getY() + 1)));
  }

  if(this.location.getX() + 1 < width ){
  result.add(
    new FlatMap((this.location.getX() + 1), 
    this.location.getY()));
  }

  if(this.location.getX() - 1 > 0 ){
  result.add(
    new FlatMap((this.location.getX() - 1), 
    this.location.getY()));
  }



  return result;
}

/**
 * Setter for setting the parent
 * @param newParent the new Parent of the node.
 */
public void setParent(FlatMap newParent)
{
  this.parent = newParent;
}

/**
 * Setter for the new G value of the node.
 * @param newG the new G value.
 */
public void setG(int newG)
{
  this.g = newG;
}

/**
 * Setter for the new H value of the node.
 * @param newH the new H value.
 */
public void setH(int newH)
{
  this.h = newH;
}

/**
 * Setter for the new F value of the node.
 * @param newF the new F value.
 */
public void setF(int newF)
{
  this.f = newF;
}

/**
 * Method to construct the F value of this node.
 * @param start The starting location.
 * @param dest The destination.
 * @param map The map being walked through.
 * @return f The F value of this node.
 */
public double calculateF(FlatMap start, FlatMap dest, TerrainMap map)
{
  this.h =(ManDistance.getDistance(this.location, dest));
  this.f = (this.g + this.h);
  return this.f;
}

/**
 * This is used for testing purposes in the actual AIastar class.
 */
public String toString()
{
  return "Location: (" + this.location.getX() + ", " + this.location.getY() + ")  " + 
    "F: " + this.f +" "+ "G: " + this.g + " H: " + this.h;
}

}
