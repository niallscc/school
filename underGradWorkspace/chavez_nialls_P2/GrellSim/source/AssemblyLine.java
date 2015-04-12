package source;

import java.util.HashMap;

import edu.unm.cs.cs351.f10.tdrl.p2.CIT;
import edu.unm.cs.cs351.f10.tdrl.p2.ConcrCIT;
import edu.unm.cs.cs351.f10.tdrl.p2.EmployeeType;
import edu.unm.cs.cs351.f10.tdrl.p2.FindableQueue;
import edu.unm.cs.cs351.f10.tdrl.p2.XRandom;

public class AssemblyLine<S> {


	final HashMap<EmployeeType, Integer> _employees;
	HashMap<Integer, CIT<S> > orders= new HashMap<Integer, CIT<S> >();
	CIT<S> ord;
	MyOrderReceipt<S> orderReceipt;
	
	private double _profit;
	private int _tBuild;
	private final int _numEmployees;
	private boolean _wasScrewedUp;
	private final int _policy;
	private final EmployeeType[] emps={EmployeeType.DRONE,EmployeeType.GURU,EmployeeType.MOUTH_BREATHER, EmployeeType.MANAGER };
	
	OrderContainer<S> assLine;
	final FindableQueue<CIT<S>, OrderContainer<CIT<S>>> queue;
	
	/**
	 * This class is created inside of the create assemblylines method in plant, and instances of this class are generated so that 
	 * each assemblyline can have its own employees, and own queue.
	 * @param policy priority policy 
	 * @param emp hash map of employees and the number of employees
	 * @param numEmps number of total employees
	 */
	public AssemblyLine(int policy, HashMap<EmployeeType, Integer> emp, int numEmps)
	{

		_employees=emp;
		_numEmployees= numEmps;
		_policy=policy;

		if( _policy==0){
			 assLine=new FIFOLine<S>();
		}
		else if(_policy==1){
			assLine=new LIFOLine<S>();
		}
		else if(_policy==2){
			
			assLine=new HighestValLine<S>();
		}
		System.out.println("Creating a new assembly line...");
		queue= new GrellFindableQueue<CIT<S>, OrderContainer<CIT<S> > >();
			
	}
	/**
	 * Adds an order into the queue. First checks if the order is already in there, if it is then it is either a change order or cancelation. 
	 * if its a cancelation. it checks if it is in process, if not it removes, if so. it still removes.:P
	 * @param cit2 Customer Identification Token
	 * @param oR order Receipt
	 */
	public void addOrder(CIT<S> cit2, MyOrderReceipt<S> oR){
		
		//adding the order into the class;
		ord= cit2;
		
		//This adds the order into the queue of orders to be checked to see if a change order has been requested 
		//If the value is true it is being processed, if it is false then it is waiting to be processed. 
		//System.out.println(" in AssemblyLine: "+oR);
		
		orders.put(oR.hashCode(), ord);
		assLine.setReceipt(oR);
		assLine.setCIT((ConcrCIT<S>)cit2);
		
		//If the orderID is already accounted for.  
		if(orders.containsKey(oR.hashCode())){
		
			//if its just a cancelation then straight up remove it from the queue. 
			if(cit2.isCancellation()){
				System.out.println("CANCEL!");
				
				orders.remove(cit2);
			}
			//else then its a change order, remove it, update it and put it back in. 
			else {
				queue.remove((OrderContainer<CIT<S>>) assLine);
				//orders.put(orderID, cit2);
				queue.insert((OrderContainer<CIT<S>>) assLine);
				
			}
			
		}
		
		else{
			//If its not a change order then add it in to the queue of things to do 
			queue.insert((OrderContainer<CIT<S>>) assLine);
			
		}
	
	}
	
	/**
	 * This is called to process, and remove orders from the queue. and return the order at the top of the queue
	 */
	@SuppressWarnings("unchecked")
	public void processOrder(){
		
		if(!queue.isEmpty()){
		
			orderReceipt=(MyOrderReceipt<S>) queue.first().getReceipt();
			//System.out.println("order Receipt in Assembly Line: "+orderReceipt.getOrderID());
			ConcrCIT<S> order= (ConcrCIT<S>) queue.pop().getCIT();
			
			ord=order;
			System.out.println("CIT In assembly Line "+ ord);
			System.out.println("Assembly line is empty? "+ queue.isEmpty());
			
			_tBuild= calcBuildTime();
			System.out.println("Buildtime:(in AssemblyLine) "+_tBuild);
			wasScrewedUp();
			
			double employeeCost= calcEmployeeCost();
			
			_profit=calcProfit( employeeCost);
		}
	
	}
	/**
	 * calculates the profit of this assemblyline 
	 * @param employeeCost sets the cost of the given employee
	 * @return
	 */
	private double calcProfit( double employeeCost){
		
	
		if(_wasScrewedUp==false){
			
			return ord.getOrder().getSalePrice() - (ord.getOrder().getFixedPrice()+(_tBuild*employeeCost) );
		}
		else{
			return 0.5*(ord.getOrder().getSalePrice() - (ord.getOrder().getFixedPrice()+(_tBuild*employeeCost) ));
		}
	}
	
	/**calculates the cost of the set of employees
	 * 
	 * @return
	 */
	private double calcEmployeeCost(){

		double employeeCost = 0;
		
		for(int i=0; i< _employees.size(); i++){
			if( _employees.containsKey(emps[i]))
				employeeCost+= (emps[i].getHourlyRate()/60 )*_employees.get(emps[i]);
	
		}

		return employeeCost;
	
	}
	/**
	 * Calculates the time to build an order
	 * @return
	 */
	private int calcBuildTime(){

		XRandom rand= new XRandom();
		double complex= ord.getOrder().getComplexity();
		double totEmpSpeed=0;		
		
		for(int i=0; i < _employees.size(); i++){

			if( _employees.containsKey(emps[i]) ){
				totEmpSpeed+=emps[i].getSpeed()*_employees.get(emps[i]);
			}
		}
		//x//rand.nextGeometric( Math.sqrt( 1 / ( 5 * totEmpSpeed ) ) ) ) );
		double randomNum= rand.nextGeometric( Math.sqrt( 1 / ( 5 * totEmpSpeed ) ) ) ;
		double time= (complex/totEmpSpeed) + (randomNum ) ;
		
		return  (int)time; 
	}
	/**
	 * Calculates the screw up probablity 
	 * @return
	 */
	public double calcScrewUpProb(){

		double screwUpProb=0.0;
		
		for(int i=0; i< _employees.size(); i++){
			if( _employees.containsKey(emps[i]))
			screwUpProb+=(emps[i].getScrewupProb(ord.getOrder()) )*_employees.get(emps[i]);
	
		}
		//System.out.println("screwUpProb"+screwUpProb);
		screwUpProb= 1/(_numEmployees)*screwUpProb ;
		
		return screwUpProb;
		
	}
	/**
	 * says whether or not the order was screwed up
	 */
	private void wasScrewedUp(){
		
		double prob= calcScrewUpProb();
		//System.out.println("prob:"+prob);
		XRandom myRand= new XRandom();
		
		double screwedUp= (myRand.nextGeometric(prob));

		
		if( screwedUp < 10 ){
			
			_wasScrewedUp=false;
		}
		
		else
			_wasScrewedUp=true;
		
		//System.out.println("Was screwedUp: "+ _wasScrewedUp);
	}
	/**
	 * gets the time to build the order popped off
	 * @return
	 */
	public int getTimeToBuild(){
		return _tBuild;
	}
	/**
	 * gets the profit from this order
	 * @return
	 */
	public double getProfit(){
		return _profit;
		
	}
	/**
	 * says whether or not it was screwed up
	 * @return
	 */
	public boolean getWasScrewedUp(){
		return _wasScrewedUp;
	}
	/**
	 * gets the order (CIT)
	 * @return
	 */
	public CIT<S>getOrder(){
		 return ord;
	}
	/**
	 * gets the receipt 
	 * @return
	 */
	public MyOrderReceipt<S> getRec(){
		return orderReceipt;
	}
	
}