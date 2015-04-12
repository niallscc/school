/**
 * 
 */
package actors;

import gameBoard.FlatMap;
import gameBoard.TerrainCell;
import gameBoard.TerrainMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import server.Server;

/**
 * @author amrsaad
 * 
 * Class that will be doing the A star calculations and path finding.
 *
 */
public class AIastar {
	/**
	 * The closed list containing all of the already visited nodes.
	 * 
	 * The Key of the HashMap is the location corresponding to the Astar node.
	 * The Value of the HashMap is the Astar node.
	 */
	  private  Map<FlatMap, AInode> closedList;
	  /**
	   * List holding the nodes that need to be checked initially only holding the 
	   * starting node and adding the subsequent adjacent/reachable nodes.
	   */
	  public List<AInode> openList;
	  /**
	   * The Character Cell that I am beginning the search from.
	   */
	  private TerrainCell start;
	  /**
	   * The Character Cell that I am trying to get to.
	   */
	  private TerrainCell endCharCell;
	  
	  /**
	   * The final Path from the start node to the end node.
	   */
	  private LinkedList<FlatMap> finalPath;
	  
	  /**
	   * A* node to represent the parent node of any node.
	   */
	  private AInode parentNode = null;
	  
	  /**
	   * The current node being looked at by the A* path finding.
	   */
	  private AInode currentNode = null;

	  /**
	   * The Location of the current Node
	   */
	  private FlatMap currentPoint = null;
	  
	 /** 
	  * The TerrainMap that is being searched for a path.
	  */
	  public TerrainMap cellMap;
	  
	
	  

	  /**
	   * Sets the path to go from a starting Cell to an ending Cell within a Cellmap.
	   * 
	   * @param startingCharCell The cell to begin path finding from.
	   * @param endCharCell The cell that is to be reached.
	   * @param gameMap The map that the search is composed on.
	   */
	  public void setPath(TerrainCell startingCharCell, TerrainCell endCharCell, TerrainMap gameMap)
	  {
		 //Assign the starting cell.
	    this.start = startingCharCell;
	    //Assign the ending cell
	    this.endCharCell = endCharCell;
	    //the terrainmap that the search will be composed on.
	    this.cellMap = gameMap;
	    //Initialize the list for the visited nodes.
	    this.closedList = new HashMap<FlatMap, AInode>(gameMap.getLength() * gameMap.getHeight() / 2);
	    //The open list 
	    this.openList = new LinkedList<AInode>();
	    //create a node from the starting cell.
	    AInode initialAInode = new AInode(startingCharCell);
	    //Set the G value
	    initialAInode.setG(0);
	    // Add the node to the list that will be checked.
	    this.openList.add(initialAInode);
	    //The List for the final path of the search.
	    this.finalPath = new LinkedList<FlatMap>();
	    if(Server.aiDEBUG)System.out.println("PATH SET: FROM "+startingCharCell.getCoordinates().getX()+","
	    		+startingCharCell.getCoordinates().getY() +" TO " + endCharCell.getCoordinates().getX()+","
	    		+endCharCell.getCoordinates().getY());
	    
	  }

	  /**
	   * Method that calls setPath() to load in a starting/ending node and a Map to trace on.
	   * 
	   * 
	   * @return returns a method call which returns the finalPath.
	   */
	  public LinkedList<FlatMap> findPath()
	  {
	    setPath(this.start, this.endCharCell, this.cellMap);
	    while (this.openList.size() > 0) {
	      processPath();
	    }
	    return getPath();
	  }
	  
	  /**
	   * Get the final path of the A-star algorithm.
	   * @return finalPath LinkedList of all the FlatMap Locations to get to the destination.
	   */
	  public LinkedList<FlatMap> getPath()
	  {
	    return this.finalPath;
	  }
	  
	  /**
	   * All the work done to process the A star shortest path.
	   * Uses currentNode, parentNode and currentPoint.
	   */
	  private void processPath()
	  {
		// Initialize the nodes to null
	    this.parentNode = null;
	    this.currentNode = null;
	    // The FlatMap location for the currentNode
	    this.currentPoint = null;
	  
	    if(Server.aiDEBUG)System.out.println("OPENLIST SIZE: "+openList.size());
	    if(Server.aiDEBUG)System.out.println("OPENLIST BEFORE: "+openList);
	    
	    // If there are any nodes to be processed then keep going.
	    if (this.openList.size() > 0)
	    {
	    	
	      double currentNodeF;
	      double altNodeF;
	      if(Server.aiDEBUG)System.out.println();
	      //For each possible alternate node check if its a better possibility
	      for (AInode altNode : this.openList) { 
	    	  if(Server.aiDEBUG)System.out.println("Looked at first : " +altNode);
	        currentNodeF = -1.0D;
	        if (this.currentNode != null) {
	          currentNodeF = this.currentNode.calculateF(this.start.getCoordinates(), this.endCharCell.getCoordinates(), this.cellMap);
	        }
	        //Calculate the F value of every comparison node.
	        altNodeF = altNode.calculateF(this.start.getCoordinates(), this.endCharCell.getCoordinates(), this.cellMap);
	        if(Server.aiDEBUG)System.out.println("COMPARISON: ALT VS CURRENT : " +altNode +" " + currentNode);
	        if ((this.currentNode != null) && 
	          (currentNodeF <= altNodeF) && (
	          (currentNodeF != altNodeF) || (currentNodeF - this.currentNode.getG() <= altNodeF - altNode.getG()))) continue;
	        this.currentNode = altNode;
	        if(Server.aiDEBUG)System.out.println("Current Node Set To: " + currentNode);
	      }
 
	      // If the end is reached clear the openlist.
	      if (this.currentNode.getCoordinates().equals(this.endCharCell.getCoordinates())) {
	        this.closedList.put(this.currentNode.getCoordinates(), this.currentNode);
	        this.openList.clear();
	      }
	      else
	      {
	    	  if(Server.aiDEBUG)System.out.println(closedList);

	        this.openList.remove(this.currentNode);
	        this.closedList.put(this.currentNode.getCoordinates(), this.currentNode);
	        this.parentNode = this.currentNode;
	        
	        /***********************PRINTS****************************/
	        if(Server.aiDEBUG)System.out.println();
	        if(Server.aiDEBUG)System.out.println("OPEN LIST: "+this.openList);
	        if(Server.aiDEBUG)System.out.println();
	        if(Server.aiDEBUG)System.out.println();
	        if(Server.aiDEBUG)System.out.println("CLOSED LIST SIZE: " +this.closedList.size());
	        if(Server.aiDEBUG)System.out.println("CLOSED LIST: " +this.closedList);
	        if(Server.aiDEBUG)System.out.println();
	        
        	if(Server.aiDEBUG)System.out.println("PARENT NODE: " +this.parentNode);
        	if(Server.aiDEBUG)System.out.println();
        	
        	//Check all the neigbors of the specified neighbor node.
	        for (FlatMap neighborNode : this.parentNode.getNeighbors(this.cellMap.getHeight(),this.cellMap.getLength()))
	        {
	       
	        	if(Server.aiDEBUG)System.out.println("HERE NEIGHBOR: " +neighborNode.getX()+","+neighborNode.getY());
	        	if(Server.aiDEBUG)System.out.println(closedList);

	        	//Check to see if the neighbor is another monster.
	          if (this.cellMap.getCellAt(neighborNode).getBeing()!=null)
	          {
	        	if(this.cellMap.getCellAt(neighborNode).getBeing().getPlayerClass() == "Monster") continue;
	          }
	        	
	          //Checking the closed list to see if the node being checked is equal to one in the closed list.
	          for(FlatMap fm: this.closedList.keySet()){ 	     
	        	  if (fm.getX() == neighborNode.getX()&&fm.getY()==neighborNode.getY()){
	        		  neighborNode = fm;
	        	  }
	          }
	          	if(Server.aiDEBUG)System.out.println("CLOSED LIST SIZE: " +this.closedList.size());
		        if(Server.aiDEBUG)System.out.println("CLOSED LIST: " +closedList);
		        if(Server.aiDEBUG)System.out.println("CLOSED LIST CONTAINS: " + this.closedList.containsKey(neighborNode));
		        if(Server.aiDEBUG)System.out.println();
		        
		        //If the closed list contains the neighborNode  continue.
	          if (this.closedList.containsKey(neighborNode)) {
	        	  // set the current node.
	            this.currentNode = ((AInode)this.closedList.get(neighborNode));
	            if(Server.aiDEBUG)System.out.println("Current Node Set: " + currentNode);
	            if(Server.aiDEBUG)System.out.println();

	            if(Server.aiDEBUG)System.out.println("COMPARE CURRENT VS PARENT: " + currentNode+" " + parentNode);
	            // Check the currentNode thats in the closedList vs the parent node.
	            if (this.currentNode.getG() <= this.parentNode.getG() + this.currentNode.getCost()) continue;
	            this.closedList.remove(this.currentNode);
	            
	            if(Server.aiDEBUG)System.out.println("CLOSED LIST AFTER REMOVE SIZE: " +this.closedList.size());
		        if(Server.aiDEBUG)System.out.println("CLOSED LIST AFTER REMOVE: " +closedList);
	            if(Server.aiDEBUG)System.out.println();

	            //Re-adjust the parents.
	            this.currentNode.setParent(this.parentNode.getCoordinates());
	            //Set the new G value of the node.
	            this.currentNode.setG(this.parentNode.getG() + this.currentNode.getCost());
	            //Calculate the F value of the new currentNode.
	            this.currentNode.calculateF(this.start.getCoordinates(), this.endCharCell.getCoordinates(), this.cellMap);
	            //Add the newest node to the closed list.
	            this.closedList.put(this.currentNode.getCoordinates(), this.currentNode);
	            if(Server.aiDEBUG)System.out.println();
	            if(Server.aiDEBUG)System.out.println("CLOSED LIST AFTER REMOVE SIZE: " +this.closedList.size());
		        if(Server.aiDEBUG)System.out.println("CLOSED LIST AFTER REMOVE: " +closedList);
	            if(Server.aiDEBUG)System.out.println();
	          }
	          else
	          {
	        	 if(Server.aiDEBUG)System.out.println();
	  	        if(Server.aiDEBUG)System.out.println("OPEN LIST CHECKING BEST NODE: "+this.openList);
	  	        if(Server.aiDEBUG)System.out.println();
	            this.currentNode = null;
	            
	            //If the currentNode wasnt on the closed list go through the openlist.
	            for (AInode bestNode : this.openList)
	            {
	            	if(Server.aiDEBUG)System.out.println("NEIGHBOR NODE: "+neighborNode.getX()+","+neighborNode.getY() +" BESTNODE : " +bestNode);
	              if (neighborNode.getX() == bestNode.getCoordinates().getX() && neighborNode.getY() == bestNode.getCoordinates().getY()) {
	                this.currentNode = bestNode;
	                if(Server.aiDEBUG)System.out.println("CURRENT NODE SET NOW BREAKING: " +this.currentNode);
	                break;
	              }

	            }
	            //If the current node is not null and current node F value >  parent node F value + the currentNode cost.
	            if ((this.currentNode != null) && (this.currentNode.getG() > this.parentNode.getG() + this.currentNode.getCost()))
	            {
	              this.openList.remove(this.currentNode);
	              this.currentNode.setParent(this.parentNode.getCoordinates());
	              this.currentNode.setG(this.parentNode.getG() + this.currentNode.getCost());

	              this.currentNode.calculateF(this.start.getCoordinates(), this.endCharCell.getCoordinates(), this.cellMap);
	              this.openList.add(this.currentNode);
	            }

	            if (this.currentNode != null)
	            {
	              continue;
	            }
	            TerrainCell currentMapNode = this.cellMap.getCellAt(neighborNode);
	            AInode tempNode = new AInode(currentMapNode);
	            tempNode.setG(this.parentNode.getG() + tempNode.getCost());
	            tempNode.setParent(this.parentNode.getCoordinates());

	            tempNode.calculateF(this.start.getCoordinates(), this.endCharCell.getCoordinates(), this.cellMap);

	            this.openList.add(tempNode);
	            if(Server.aiDEBUG)System.out.println();
	            if(Server.aiDEBUG)System.out.println("ADDING Temp Node: " + tempNode);
	            if(Server.aiDEBUG)System.out.println();
	            
	            if(Server.aiDEBUG)System.out.println("AFTER ADDING TEMP NODE OPENLIST:" +openList);
	            if(Server.aiDEBUG)System.out.println();

	          }
	        }

	      }

	    }
	    //If there is no path then return an empty list.
	    if (!this.closedList.containsKey(this.endCharCell.getCoordinates())) {
	      this.finalPath.clear();
	    }
	    else
	    {
	      this.currentPoint = this.endCharCell.getCoordinates();
	      while (this.currentPoint != null) {
	        this.currentNode = ((AInode)this.closedList.get(this.currentPoint));
	        this.finalPath.addFirst(this.currentNode.getCoordinates());


	        if (!this.currentNode.getParent().equals(this.start.getCoordinates())) {
	          this.currentPoint = ((AInode)this.closedList.get(this.currentNode.getParent())).getCoordinates();
	        }
	        else
	          this.currentPoint = null;
	      }
	    }
	  }
}	  



