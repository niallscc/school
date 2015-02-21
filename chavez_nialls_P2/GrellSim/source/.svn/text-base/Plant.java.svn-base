package source;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import edu.unm.cs.cs351.f10.tdrl.p2.CIT;


import edu.unm.cs.cs351.f10.tdrl.p2.EmployeeType;

public class Plant<S> {

	private int numEmployees=0;
	private int assignment;
	private int numAssemblyLines;
	private int policy;
	private double moneyMade;
	private final HashMap<EmployeeType, Integer> employees; 
	private  AssemblyLine<S>[] lines;	
	int time;
	Assignment<S> assignmentObj;
	AssemblyLine<S> assemblyLineObj;
	
	HashMap<Integer, Order<S>> eventQueue;
	ArrayList<Integer> eventTimes;
	/**
	 * Initializes the Plant object gets the number of employees on this plant and their types, and also gets the priority policy used, and the assignmetn
	 * policy. 
	 * @param _numAssLines number of assemblylines
	 * @param _emps hashmap of employees
	 * @param _priority priority policy used
	 * @param _assPolicy assignment poliicy assigned.
	 */
	public Plant(int _numAssLines, HashMap<EmployeeType, Integer> _emps, int _priority,int _assPolicy )	{
		
		System.out.println("Creating Plant...");
		
		eventTimes= new ArrayList<Integer>();
		
		eventQueue=new HashMap<Integer, Order<S>>();
		
		assignment= _assPolicy;
		numAssemblyLines= _numAssLines;
		employees= _emps;
		getNumEmployees();
		createPlant();
		createAssemblyLines();
		
	}
	/**
	 * Is initialized from Grell sim, it creates the plant and sets the assignment policies
	 */
	private void createPlant() {
		
		/*
		 * Assignment key: 0: RoundRobin
		 * 				   1: FirstEmpty
		 * 				   2: ShortestFirst
		 * 				  
		 */	
		
		if(assignment == 0){
			System.out.println("Setting assignment Policy (RoundRobin)...");
			assignmentObj= new RoundRobin<S>();
		}
		
		else if( assignment== 1){
			System.out.println("Setting assignment Policy (FirstEmpty)...");
			assignmentObj= new FirstEmpty<S>();
			
		}
		else if (assignment==2){
			System.out.println("Setting assignment Policy (ShortestFirst)...");
			assignmentObj=new ShortestFirst<S>();
		}

		
	}
	//**************** BEGIN PLANT CONFIGURATIONS *************************//
	
	/**
	 * An auxilery method to get the number of employees on the plant. deprecated
	 */
	private int getNumEmployees() {
		EmployeeType [] a={ EmployeeType.DRONE ,EmployeeType.GURU,EmployeeType.MOUTH_BREATHER, EmployeeType.MANAGER }; 
		int[] empStats=new int[4];
		for(int i=0; i < 4; i++){
			
			if(employees.containsKey(a[i]) ){
				empStats[i]=employees.get(a[i]);
			}
			else
				empStats[i]=0;
		}
		
		for( int i = 0; i < empStats.length; i++ ){
			numEmployees+=empStats[i];
		}
		return numEmployees;
	}

	/**
	 * This loop creates and adds to my assembly Line Hashmap. This hashmap 
	 * holds a unique ID for each hashmap, and an assemblyLine object that holds what kind of 
	 * assembly line is being used. 
	 * each assemblyline holds what prioritization policy is being used and how many employees are assigned to it
	 * now, what needs to be fixed is the emps. 
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void createAssemblyLines(){
		
		lines = new AssemblyLine [numAssemblyLines];
		System.out.println("Creating assembly lines...");
		for( int i = 0 ; i < numAssemblyLines; i++ ){
			
			assemblyLineObj= new AssemblyLine<S>(policy, employees, numEmployees );
			lines[i]= assemblyLineObj;
		}
		//System.out.println("al="+assemblyLineObj);
	}
	
	/*
	 * gets an object that contains the assembly lines for this plant
	 */
	public AssemblyLine<S> getAssemblyLines(){
		
		return assemblyLineObj;
	}
	
	public  void addItem(CIT<S> info, MyOrderReceipt<S> receipt ){
		//System.out.println("Order Receipt in Plant: "+receipt);
		assignmentObj.plantType(lines, info, receipt);
		
		//calculate the money made on this order.
		moneyMade+=info.getOrder().getSalePrice() - info.getOrder().getFixedPrice();
	}
	
	//************** END PLANT CONFIGURATIONS****************//

	public double getGrossMade(){
		
		return moneyMade;
	}
	/**
	 * This pops off the item off the queue, returns it with the correct deliver time. 
	 * @param currentTime
	 */
	public void completeOrder(int currentTime){
		
		for(AssemblyLine<S> i: lines){
			
			i.processOrder();
			CIT<S> tempOrder= i.getOrder();
			
			if( tempOrder!= null){

				Order<S> temp= new Order<S>();
				
				temp.setOrd(tempOrder);
				temp.setId((Integer) i.getRec().getOrderID());
				temp.setTime((i.getTimeToBuild()+currentTime));
				temp.setRec(i.getRec());
				temp.setScrewed(i.getWasScrewedUp());
				
				
				System.out.println("  BuildTime inside Plant: "+ (i.getTimeToBuild()+currentTime));
				System.out.println("  Order Going out is: "+ tempOrder);
				System.out.println("  orderRec ID is: "+i.getRec().getOrderID());
				System.out.println("  orderReceipt: "+i.getRec());
				
				System.out.println("Inserting into the event queue: ");
				
				eventTimes.add(i.getTimeToBuild()+currentTime);
				eventQueue.put(i.getTimeToBuild()+currentTime, temp);
				
			}

		}
		Collections.sort(eventTimes);
		
	}
	/**
	 * get if the order was screwed
	 * @return boolean
	 */
	public boolean getWasScrewed(){
		
		return eventQueue.get(eventTimes.get(0)).getIsScrewed();
	}
	/**
	 * get the id of the order popped off
	 * @return the id
	 */
	public int getID(){
		
		return eventQueue.get(eventTimes.get(0)).getId();
	}
	/**
	 * get the receipt of the order popped off
	 * @return order Receipt
	 */
	public MyOrderReceipt<S> getR(){
		return eventQueue.get(eventTimes.get(0)).getRec();
	}
	/**
	 * removes the first item off the queue
	 */
	public void removeTop(){
		
		eventQueue.remove(eventTimes.remove(0));
		System.out.println("eventQueue size is: "+eventQueue.size());
	}
	/**
	 * Checks if the queue is empty or not
	 * @return a boolean
	 */
	public boolean checkIsEmpty(){
		return eventQueue.isEmpty();
	}
	/**
	 *Lets you look at the first item in the queue with out popping it off. 
	 */
	public CIT<S> checkNextOrder(){
		return eventQueue.get(eventTimes.get(0)).getOrd();
	}
	/**
	 * Gets the money made
	 * @return mulah ... I wish... If I could write a method that returned money, I wouldnt be in this class. Just sayin'.
	 */
	public double getMoneyMade(){
		return moneyMade;
	}
	/**
	 * gets the completion time of the item to be delivered
	 * @return time... Again, If i could write a method that returned time I would have an A in this class because Id return the time before the first midterm and get an A on It :)
	 */
	public int getTimeToComplete(){
		
		return eventQueue.get(eventTimes.get(0)).getTime();
	}
	/**
	 * A private inner class that holds the order that is coming off the queue
	 * @author niallschavez
	 *
	 * @param <T>
	 */
	private class Order<T>  {
		
		private int time; 
		private int id; 
		private CIT<S> ord;
		private boolean screwed;
		private MyOrderReceipt<T> rec;
		
		public void setTime(int time) {
			this.time = time;
		}
		public int getTime() {
			return time;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getId() {
			return id;
		}
		public void setOrd(CIT<S> ord) {
			this.ord = ord;
		}
		public CIT<S> getOrd() {
			return ord;
		}
		public void setScrewed(boolean screwed) {
			this.screwed = screwed;
		}
		public boolean getIsScrewed() {
			return screwed;
		}
		public void setRec(MyOrderReceipt<T> rec) {
			this.rec = rec;
		}
		public MyOrderReceipt<T> getRec() {
			return rec;
		}

	}

}

