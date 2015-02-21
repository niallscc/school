package server;

import events.AttackEvent;
import events.GladdosEvent;
import events.MoveEvent;
import game.WorldState;
import gameBoard.FlatMap;
import gameBoard.TerrainCell;
import gameBoard.TerrainMap;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

import actors.AIastar;
import actors.Being;
import actors.ManDistance;


public class BOTS implements Runnable {


	/**
	 * Flags whether the client is connected to the server or not.
	 */
	private boolean run = true;

	/**
	 * Astar used for the path-finding.
	 */
	private AIastar astar;
	
	/**
	 * The mailbox to add events.
	 */
	private LinkedBlockingQueue<GladdosEvent> mailbox;
	
	/**
	 * The Server World State.
	 */
	private WorldState sws;
	
	/**
	 * Being map
	 */
	private Map<String,Being> map;


	


	/**
	 * Constructer for the bots to start all the bots on one thread.
	 */
	public BOTS(WorldState serverws, LinkedBlockingQueue<GladdosEvent> mailbox2, Map<String, Being> m) { 
		sws = serverws;
		mailbox = mailbox2;
		map = m;
		new Thread(this).start();
	} 

	/**
	 * Getter for the running state of the thread.
	 * @return Whether or not the thread is running.
	 */
	public boolean isRunning() {
		return run;
	}
	/**
	 * Sends an event to the server
	 * @param newEvent The event to send.
	 */
	public void sendEvent(GladdosEvent event) {
		mailbox.add(event);
	}
	
	/**
	 * Method  that will move all of the bots to where they need to go.
	 */
	public void work(){
		/*
		 *  Store the monsters that are on any map and tell them all to move.
		 */
		LinkedList<Being> monsters = new LinkedList<Being>();
		FlatMap moveto;
		FlatMap monloc;
		astar = new AIastar();

		/*
		 * Loop through the World Beings
		 */
		//for(Map<String,Being> map : sws.getWorldBeings()){
			//Loop through each being per map.
			run = false;
			for(String s : map.keySet()){
				//Get the being being looked at.
				Being b = map.get(s);
				//If its a monster add it to the monsters list.
				if(b.getPlayerClass() == "Monster" ){
					if(b.isAlive()) {
						monsters.add(b);
						run = true;
					}
					
				}
			}
			
//			//System.out.println(spawners.size());
//			while(spawners.size() > 0){
//				Being spawner = spawners.poll();
//				map.remove(""+spawner.getID());
//				//sws.spawnBot(map, sws.getMapLocation(spawner.getID())+1);
//			}
		//}
		//Keep moving all the monsters while there are still monsters.
		while(monsters.size()>0){
			//Pull off the first monster.
			Being mon = monsters.poll();
			//Find the map the monster is on.
			int mapID = sws.getMapLocation(mon.getID());
			//Pick a being thats on the map.
			for(Being b : sws.getMapBeings(mapID).values()){
				int dist = ManDistance.getDistance(mon.getLocation(), b.getLocation());
				if(dist == 1 && b.isAlive() && b.getPlayerClass() != "Monster" && mon.isAlive()){
					AttackEvent ae = new AttackEvent(mon.getID(),mon,b.getLocation());
					if(mon.isAlive())sendEvent(ae);
					break;
					}
				if((b.getPlayerClass() != "Monster") && 
						dist <= 5 && dist != 1 && mon.isAlive()
						&& (b.getLevel() - mon.getLevel()) < 3){

					TerrainMap tm = sws.getWorldMap(mapID);
					TerrainCell startcell = tm.getCellAt(mon.getLocation());
					TerrainCell endcell = tm.getCellAt(b.getLocation());
					
					astar.setPath(startcell, endcell, tm);
					LinkedList<FlatMap> dir = astar.findPath();
					if(dir.size() > 0){
						moveto = dir.get(0);
						monloc = mon.getLocation();
						MoveEvent me = new MoveEvent(mon.getID(),getAxis(monloc,moveto),
								getMoveValue(monloc,moveto),sws);
						if(mon.isAlive())sendEvent(me);
					}
					

				}
				
					
				}
			
		}
		
		monsters.clear();
		
	}
	
	
	
	
	

	/**
	 * Method to get the axis for the direction to move.
	 * @param a Original location
	 * @param b New Location.
	 * @return The direction of the move.
	 */
	public char getAxis(FlatMap a, FlatMap b){
		if(a.getX() == b.getX())return 'y';
		else return 'x';
	}
	
	public void moveRandom(Being mon){
		
    	
    	Random generator = new Random();
    	
    	int r = generator.nextInt( 4 );
    	    	
    	  switch ( r )
          {
          case 0:
        	  FlatMap moveeast = new FlatMap(mon.getLocation().getX()+1,mon.getLocation().getY());
        	  if(moveeast.isValid()){
        	  FlatMap monloc = mon.getLocation();
        	  MoveEvent me = new MoveEvent(mon.getID(),getAxis(monloc,moveeast),
						getMoveValue(monloc,moveeast),sws);
				sendEvent(me);
        	  }
              break;
          case 1:
        	  FlatMap movewest = new FlatMap(mon.getLocation().getX()-1,mon.getLocation().getY());
        	  if(movewest.isValid()){
        	  FlatMap monloc = mon.getLocation();
        	  MoveEvent me = new MoveEvent(mon.getID(),getAxis(monloc,movewest),
						getMoveValue(monloc,movewest),sws);
				sendEvent(me);
        	  }
              break;
          case 2:
        	  FlatMap movesouth = new FlatMap(mon.getLocation().getX(),mon.getLocation().getY()+1);
        	  if(movesouth.isValid()){
        	  FlatMap monloc = mon.getLocation();
        	  MoveEvent me = new MoveEvent(mon.getID(),getAxis(monloc,movesouth),
						getMoveValue(monloc,movesouth),sws);
				sendEvent(me);
				System.out.println("south");
        	  }
              break;
          case 3:
        	  FlatMap movenorth = new FlatMap(mon.getLocation().getX(),mon.getLocation().getY()-1);
        	  if(movenorth.isValid()){
        		  System.out.println("north");
        	  FlatMap monloc = mon.getLocation();
        	  MoveEvent me = new MoveEvent(mon.getID(),getAxis(monloc,movenorth),
						getMoveValue(monloc,movenorth),sws);
				sendEvent(me);
        	  }
              break;
          }

	}
	
	public void delay(int amount) {
		try {
			Thread.sleep(amount);
		} catch (InterruptedException e) {
			System.err.println("Interrupted Exception sleeping bot.");
		}
	}
	
	/**
	 * Method to get the value of the direction to move.
	 * 
	 * @param a Original Location
	 * @param b New Location
	 * @return The value -1 or 1 for the move.
	 */
	public int getMoveValue(FlatMap a, FlatMap b){
		if(a.getX() != b.getX()){
			if(a.getX()<b.getX()) return 1;
			else return -1;
		}
		else if(a.getY() != b.getY()){
			if(a.getY()<b.getY()) return 1;
			else return -1;
		}
		else return 0;	
		
	}

	/**
	 * Method to run the bots work.
	 */
	@Override
	public void run() {
		while(run){
		delay(1500);
		work();
		}
		
	}

}
