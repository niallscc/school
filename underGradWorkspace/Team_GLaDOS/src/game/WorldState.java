package game;

import gameBoard.FlatMap;
import gameBoard.Portal;
import gameBoard.Terrain;

import gameBoard.TerrainMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import swag.Bludgeon;
import swag.ButterKnife;
import swag.ChainMailBikini;
import swag.CoatofPlates;
import swag.Excalibur;
import swag.LeadToothbrush;
import swag.LeatherArmor;
import swag.Mace;
import swag.Misericorde;
import swag.PipeCleanerOfDoom;
import swag.PlateMail;
import swag.RingMail;
import swag.RolledUpSock;
import swag.RustyDagger;
import swag.ScaleMail;
import swag.ShortSword;
import swag.SnailMail;
import swag.Swag;
import swag.WetTowel;
import swag.Zweihander;

import actors.Being;
import actors.FastZombie;
import actors.NPC;
import actors.SlimeZombie;
import actors.ZombieCow;
/**
 * An abstract world state that provides the shared functionality between the ClientWorldState and the ServerWorldState.
 * 
 * Will keep track of every world specific object in the game. This will include:
 * 
 * Per map:
 * 1) Beings (anything that moves)
 * 2) Swag
 * 3) Moolah
 * 4) The player's character.
 * 5) Terrain cells
 * 
 * @author Ryan
 *
 */
public abstract class WorldState {
	/**
	 * The width dimension we will be using for our maps.
	 */
	protected final int width = 20;
	/**
	 * The height dimension we will be using for our maps.
	 */
	protected final int height = 20;
	/**
	 * An ArrayList of HashMaps that map being ID's to beings
	 */
	protected ArrayList<Map<String, Being>> beingsOnMaps; 
	/**
	 * A HashMap that maps client id's to map numbers, this keeps track of which map a specific being is on.
	 */
	protected HashMap<String, Integer> idMap;
	/**
	 * A List of the TerrainMaps which contains all of the cells, which
	 * contain their specific contents.
	 */
	protected List<TerrainMap> worldMaps;

	/**
	 * The botIDs to be set.
	 */
	private int botID = 100;
	/**
	 * The list of all of the swag items in the game. Used for random selection.
	 */
	private List<Swag> allSwag;

	/**
	 * Does all of the initialization that is needed for the data structures that keeps the state of the world.
	 * Constructs:
	 * idMap - Maps Being Id's to map location
	 * worldMap - List of every TerrainMap in the world.
	 * beingsOnMaps - List of mappings from BeingID to being
	 */
	public WorldState() {
		beingsOnMaps = new ArrayList<Map<String, Being>>();
		idMap = new HashMap<String, Integer>();
		worldMaps = new ArrayList<TerrainMap>();
		allSwag = new ArrayList<Swag>();

		//Create a map for each of the 5 maps.
		HashMap<String, Being> map1Beings = new HashMap<String, Being>();
		HashMap<String, Being> map2Beings = new HashMap<String, Being>();
		HashMap<String, Being> map3Beings = new HashMap<String, Being>();
		HashMap<String, Being> map4Beings = new HashMap<String, Being>(); 
		HashMap<String, Being> map5Beings = new HashMap<String, Being>();

		beingsOnMaps.add(map1Beings);
		beingsOnMaps.add(map2Beings); 
		beingsOnMaps.add(map3Beings);
		beingsOnMaps.add(map4Beings);
		beingsOnMaps.add(map5Beings);

		//Create the array of swag items for random selection.
		allSwag.add(new ButterKnife());
		allSwag.add(new Bludgeon());
		allSwag.add(new ChainMailBikini());
		allSwag.add(new CoatofPlates());
		allSwag.add(new Excalibur());
		allSwag.add(new LeadToothbrush());
		allSwag.add(new LeatherArmor());
		allSwag.add(new Mace());
		allSwag.add(new Misericorde());
		allSwag.add(new PipeCleanerOfDoom());
		allSwag.add(new PlateMail());
		allSwag.add(new RingMail());
		allSwag.add(new RolledUpSock());
		allSwag.add(new RustyDagger());
		allSwag.add(new ScaleMail());
		allSwag.add(new ShortSword());
		allSwag.add(new SnailMail());
		allSwag.add(new WetTowel());
		allSwag.add(new Zweihander());	

		loadWorld();
		addBots();	
	}

	/**
	 * Method to load the bots into the different maps that are created..
	 */
	public void addBots(){
		/**********FOR THE AI DEMO************/
		FlatMap f = new FlatMap(14,4);
		ZombieCow cowaidemo = new ZombieCow(f, botID);
		cowaidemo.setLevel(1);
		beingsOnMaps.get(0).put(""+cowaidemo.getID(), cowaidemo);
		worldMaps.get(0).getCellAt(f).setBeing(cowaidemo);
		idMap.put(""+cowaidemo.getID(), 0);
		cowaidemo.addSwagItem(new RolledUpSock());
		botID++;
		/**********FOR THE AI DEMO************/
		int counter = 1;
		//Loop through every map that has been created and added to te beingsOnMaps.
		for(Map<String,Being> map : beingsOnMaps){
			//Add three random bots each map.
			for(int i = 0 ; i < 3+counter; i++){
				Random generator = new Random();
				int random = generator.nextInt(18); 
				int type = generator.nextInt( 3 );
				int x = generator.nextInt(width);
				int y = generator.nextInt(height);
				int index = beingsOnMaps.indexOf(map);
				FlatMap fm;
				//Switch case to help randomly pick the bot type.
				switch ( type )
				{
				//Zombie Cow
				case 0:
					fm = new FlatMap(x,y);
					ZombieCow cow = new ZombieCow(fm, botID);
					cow.setLevel(counter);
					map.put(""+cow.getID(), cow);
					worldMaps.get(index).getCellAt(fm).setBeing(cow);
					idMap.put(""+cow.getID(), index);
					cow.addSwagItem(allSwag.get(random));
					botID++;
					break;
					//FastZombie	
				case 1:
					fm = new FlatMap(x,y);
					FastZombie fz = new FastZombie(fm, botID);
					fz.setLevel(counter);
					map.put(""+fz.getID(), fz);
					worldMaps.get(index).getCellAt(fm).setBeing(fz);
					idMap.put(""+fz.getID(), index);
					fz.addSwagItem(allSwag.get(random));
					botID++;
					break;
					//SlimeZombie	
				case 2:
					fm = new FlatMap(x,y);
					SlimeZombie sz = new SlimeZombie(fm, botID);
					sz.setLevel(counter);
					map.put(""+sz.getID(), sz);
					worldMaps.get(index).getCellAt(fm).setBeing(sz);
					idMap.put(""+sz.getID(), index);
					sz.addSwagItem(allSwag.get(random));
					botID++;
					break;
				}

			}
			counter = counter + 2;
		}

	}

	/**
	 * Getter for which map a specific being ID is located on.
	 * @param The being ID to look up.
	 * @return The map that being is on.
	 */
	public int getMapLocation(int beingID) {
		if(!idMap.containsKey(""+beingID))
			return -1;
		else
			return idMap.get(""+beingID);
	}
	/**
	 * Setter for the map location mapping.
	 * @param id The beingID to map.
	 * @param map The map to map the being onto.
	 */
	public void putMapLocation(int id, int map) {
		idMap.put(""+id, map);
	}
	/**
	 * Getter for the List of maps in the world.
	 * @return The Map List
	 */
	public List<TerrainMap> getMaps() {
		return worldMaps;
	}
	/**
	 * Getter for the mapping of ID's to which map they are located on.
	 * @return The HasMap that they are mapped in.
	 */
	public HashMap<String, Integer> getIDMap() {
		return idMap;
	}

	/**
	 * Getter for the beings for a particular map
	 * @param The map id to get the beings for
	 * @return The HashMap of beings.
	 */
	public Map<String, Being> getMapBeings(int mapID) {

		return beingsOnMaps.get(mapID);
	}

	/**
	 * This method will iterate through each of the terrain, item, and monster files for each of our five maps 
	 * and load them into a terrainMap for each map in the world.
	 */
	public void loadWorld() {
		// TODO This is where all the maps for the world will be added.
		// TODO Need to recurse through the Maps folder and load each one into the maps list.


		BufferedReader inputTerrain;
		BufferedReader inputItems;

		//Loop through and read the data from the file in and place it into the TerrainMap
		try {
			int inEnvironment = 0;
			int inItems = 0;
			int portalMapNumber = 0;
			int nextPortalMapNumber = portalMapNumber+1;

			for(int i = 1; i < beingsOnMaps.size()+1; i++) {  //Loop through the total number of maps we have.
				inputTerrain =  new BufferedReader(new FileReader("./GameBoardMaps/Level" +i +".txt"));
				inputItems =  new BufferedReader(new FileReader("./GameBoardMaps/itemMapLevel"+i +".txt"));
				TerrainMap tMap= new TerrainMap(width,height);

				//Make the last map connect to the first map.
				if(portalMapNumber == 4)
					nextPortalMapNumber = 0;
				//Loop through and populate the arrays with the data from the files.
				for(int y=0; y < 20; y++){
					for( int x=0; x < 20; x++){			
						FlatMap location = new FlatMap(x,y); //Testing


						inEnvironment=inputTerrain.read();
						inItems=inputItems.read();

						if(inEnvironment==10 ){
							inEnvironment=inputTerrain.read();
						}

						//If P - Portal
						if(inEnvironment == 80) {
							//Create a plain cell
							tMap.setCellAt(location, Terrain.getTerrain('s'));
							Portal portal = new Portal(portalMapNumber, nextPortalMapNumber, location, location);
							tMap.getCellAt(location).setPortal(portal);
						}

						//If V - Vendor Cell ( Vendor's won't move so they can be considered a Terrain Type)
						else if(inEnvironment == 'V') {
							//Create plain cell
							tMap.setCellAt(location, Terrain.getTerrain('*'));

							//Create an NPC
							NPC vendor = new NPC(location);

							//Now add an NPC
							tMap.getCellAt(location).setNPC(vendor);
						}

						//Otherwise set it to the specified TerrainType
						else
							tMap.setCellAt(location, Terrain.getTerrain(inEnvironment));

						//Set the amount of moolah on the cell.
						tMap.getCellAt(location).setMoolah(Terrain.getTerrain(inEnvironment).getMoolah());
						if(inItems==10 )
							inItems=inputItems.read();

						if(inItems!=42) {
							Random g = new Random();
							int random = g.nextInt(18); 
							tMap.getCellAt(location).addSwag(allSwag.get(random));
							// TODO ADD SWAG
						}
					}
				}
				worldMaps.add(tMap);
				portalMapNumber++;
				nextPortalMapNumber++;
			}
		}
		catch (FileNotFoundException e1) {
			System.err.println("The system was unable to load a map file.");
		} catch (IOException e) {
			System.err.println("Error reading in the Map(s)");
		}

	}

	/**
	 * Spawns all of the bots needed.
	 * @param map The map to spawn them on.
	 * @param counter The amount of bots. 
	 */
	public void spawnBot(Map<String,Being> map, int counter){
		for(int i = 0; i < counter ; i++){
			Random generator = new Random();
			int type = generator.nextInt( 3 );
			int random = generator.nextInt(18); 
			int x = generator.nextInt(width);
			int y = generator.nextInt(height);
			int index = beingsOnMaps.indexOf(map);
			FlatMap fm;
			//Switch case to help randomly pick the bot type.
			switch ( type )
			{
			//Zombie Cow
			case 0:
				fm = new FlatMap(x,y);
				ZombieCow cow = new ZombieCow(fm, botID);
				cow.setLevel(counter);
				map.put(""+cow.getID(), cow);
				worldMaps.get(index).getCellAt(fm).setBeing(cow);
				idMap.put(""+cow.getID(), index);
				cow.addSwagItem(allSwag.get(random));
				botID++;
				break;
				//FastZombie	
			case 1:
				fm = new FlatMap(x,y);
				FastZombie fz = new FastZombie(fm, botID);
				fz.setLevel(counter);
				map.put(""+fz.getID(), fz);
				worldMaps.get(index).getCellAt(fm).setBeing(fz);
				idMap.put(""+fz.getID(), index);
				fz.addSwagItem(allSwag.get(random));
				botID++;
				break;
				//SlimeZombie	
			case 2:
				fm = new FlatMap(x,y);
				SlimeZombie sz = new SlimeZombie(fm, botID);
				sz.setLevel(counter);
				map.put(""+sz.getID(), sz);
				worldMaps.get(index).getCellAt(fm).setBeing(sz);
				idMap.put(""+sz.getID(), index);
				sz.addSwagItem(allSwag.get(random));
				botID++;
				break;
			}
		}
	}


	/**
	 * Getter for a specific being.
	 * @param map The map to get them off of
	 * @param id The id of the being to pull
	 * @return The Being to return
	 */
	public Being getBeing(int map, int id) {
		return this.getMapBeings(map).get(""+id);
	}


	/**
	 * Getter for a specific map in the world.
	 * @param id The map to pull.
	 * @return the TerrainMap associated with that id.
	 */
	public TerrainMap getWorldMap(int id) {
		return worldMaps.get(id);
	}

}
