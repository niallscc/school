package source;


import java.util.HashMap;

import edu.unm.cs.cs351.f10.tdrl.p2.DiscrEventSimulator;
import edu.unm.cs.cs351.f10.tdrl.p2.EmployeeType;

import edu.unm.cs.cs351.f10.tdrl.p2.CIT;
import edu.unm.cs.cs351.f10.tdrl.p2.MoMoneyWorld;
import edu.unm.cs.cs351.f10.tdrl.p2.World;
import edu.unm.cs.cs351.f10.tdrl.p2.XRandom;
/**
 * GrellSim.java is the main file for the Grell Factory Simulation, this file mainly takes in the information from the user, either from 
 * commandline arguments, or from the Grell UI, and from there it outputs the simulation report. 
 * @author niallschavez
 *
 * @param <S>
 */
public class GrellSim<S> implements DiscrEventSimulator<S> {
	

 	private double _PlantCost=100000.0;
	private double _HourlyRunningRate= 200.0;
	private double _curMoneyMade=0.0;
	private final int  _pPolicy, _aPolicy, _numAssLines;
	private final HashMap<EmployeeType, Integer> _emps;
	private int _curID=0;
	private int curMinutes;
	private int delivTime;
	private int _totTime;
	private String finalRep;
	World<S> w;
	Plant<S> plant;
	CIT<S>info;
	MyOrderReceipt<S> oR;
	
	/**
	 * The constructor sets up the global variables for the grell sim. 
	 * @param emps a hashmap that contains the number and type of each employee inserted in the queue. 
	 * @param pPolicy the priority policy for this plant, it can either be FIFO, LIFO, or HighestVal
	 * @param aPolicy the assignment policy for this plant, it can either be Round Robin, First Empty, or Shortest First
	 * @param numAssLines The number of assembly lines operating in this plant
	 * @param runTime the amount of time that the factory will run for. 
	 */
	public GrellSim(HashMap<EmployeeType, Integer> emps, int pPolicy, int aPolicy, int numAssLines, int runTime ){
		/*
		 * Set up
		 */
		_emps= emps;
		_pPolicy=pPolicy;
		_aPolicy= aPolicy;
		_numAssLines= numAssLines;
		_totTime= runTime;

		plant= new Plant<S>( _numAssLines, _emps, _pPolicy, _aPolicy );			
		
		
		/*
		 * Start the first order 
		 */
		oR = new MyOrderReceipt<S>();
		
		w=new MoMoneyWorld<S>(new XRandom());
		
		curMinutes=w.acknowledgeOrder(null, 0);
	
		info =  w.getOrderAtTime(curMinutes);
		System.out.println("The first order is: "+info.toString() );
		
		oR.setOrderID(_curID);
		oR.setCusID(info.getCustomerID());

		/*Run! the cops are coming!!!!
		 * 
		 */
		this.run(w, _totTime);
	}
	/**
	 * this method will return the amount of money made eg the net profit of the plant
	 * 
	 */
	@Override
	public double getCurrentBalance() {
		return calculateNet();
	}

	/**
	 * This method returns the current time that plant is operating at. 
	 */
	@Override
	public int getCurrentTime() {
		return curMinutes;
	}

	/**
	 * This gets the number of each type of employees
	 */
	@Override
	public int getEmployeeCount(EmployeeType t) {
	
		if(_emps.containsKey(t.toString())){
			return _emps.get(t.toString());
		}
		
		return 0;
	}

	/**
	 * Run does the main run for the plant, first it takes the initialized value, adds it to the queue, checks if it came back null, then processes the
	 * order and continues until the time runs out. 
	 */

	@Override
	public void run(World<S> w, int minutes) {
		
		_PlantCost=w.getPlantFixedCost();
		_HourlyRunningRate= w.getPlantHourlyOperatingCost();	
	
		int nextTime=w.acknowledgeOrder(oR, curMinutes);
		System.out.println("The order has been acknowledged and the next order is "+ w.getOrderAtTime(nextTime));

		
		while(nextTime <= minutes){
			
			plant.addItem(info, oR );
			//System.out.println("The order added in is : "+info);
			plant.completeOrder(curMinutes);
			//System.out.println("The order time is : "+curMinutes);
			delivTime=(Integer) plant.getTimeToComplete();
			//System.out.println("The id of the returned item is: "+ plant.getID());
			
			if( delivTime <= nextTime){
				
				CIT<S> delivItem = (CIT<S>) plant.checkNextOrder();
				
				output(plant.getID(), delivTime, plant.getWasScrewed(), plant.getR(), delivItem);
				if( delivItem.isCancellation()){
					System.out.println("Oops.. dont deliver this one..");
				}
				else{
					
				w.deliver(delivTime, plant.getR(), delivItem.getOrder(), plant.getWasScrewed() );
				
				}
				plant.removeTop();
				oR= new MyOrderReceipt<S>();
				
				++_curID;
				oR.setOrderID(_curID);
				oR.setCusID(info.getCustomerID());
				

				curMinutes=nextTime;
				
				info= w.getOrderAtTime(nextTime);
				System.out.println("The order has been acknowledged and the next order is "+ info+"Receipt is: "+oR.getOrderID());
				if( !info.isCancellation())	
					nextTime= w.acknowledgeOrder(oR, nextTime);
				
			}
			else {
				
				oR= new MyOrderReceipt<S>();
				
				++_curID;
				oR.setOrderID(_curID);
				oR.setCusID(info.getCustomerID());
				
				curMinutes=nextTime;
				
				info= w.getOrderAtTime(nextTime);
				System.out.println("The order has been acknowledged and the next order is "+ info);
				if(!info.isCancellation()){
					nextTime= w.acknowledgeOrder(oR, nextTime);
				}
				else{/*
					System.out.println("The order Rec is: "+oR.getCustomerID());
					System.out.println("NextTime "+nextTime);
					nextTime= w.acknowledgeOrder(oR, nextTime);
					oR= new MyOrderReceipt<S>();
					
					++_curID;
					oR.setOrderID(_curID);
					oR.setCusID(info.getCustomerID());
					

					curMinutes=nextTime;
					
					info= w.getOrderAtTime(nextTime);
					System.out.println("The order has been acknowledged and the next order is "+ info);*/
				}
			}
			
			
		}
		finalRep=w.finalReport(this);
	}
	/**
	 * This calculates the net profit made by this plant on this run. Can be called at anyp point
	 * @return returns the money made
	 */
	private double calculateNet(){
		
		double moneyMade=0.0;
		double empCost=0.0;
		
		for( int i = 0; i <  _emps.size(); i++){
			
			if(EmployeeType.DRONE.toString().equals(_emps.containsKey("DRONE") )){
				empCost +=_emps.get("DRONE")* (EmployeeType.DRONE.getHourlyRate() *( curMinutes/60));
			}
			else if(EmployeeType.GURU.toString().equals(_emps.containsKey("GURU") )){
				empCost +=_emps.get("GURU")* (EmployeeType.GURU.getHourlyRate() *( curMinutes/60));
			}
			else if(EmployeeType.MOUTH_BREATHER.toString().equals(_emps.containsKey("MOUTH_BREATHER") )){
				empCost +=_emps.get("MOUTH_BREATHER")* (EmployeeType.MOUTH_BREATHER.getHourlyRate() *( curMinutes/60));
			}
			else if(EmployeeType.MANAGER.toString().equals(_emps.containsKey("MANAGER") )){
				empCost +=_emps.get("MANAGER")* (EmployeeType.MANAGER.getHourlyRate() *( curMinutes/60));
			}
			
		}
		
		_curMoneyMade=plant.getGrossMade();
		moneyMade=_curMoneyMade-((_HourlyRunningRate * curMinutes/60)+_PlantCost + empCost);
		return moneyMade;
	}

	/**
	 * Output file, for de bugging purposes. 
	 * @param orderId
	 * @param deliveryTime
	 * @param screwed
	 * @param r
	 * @param delivItem
	 */
	private void output( int orderId, int deliveryTime, boolean screwed, MyOrderReceipt<S> r, CIT<S> delivItem){
		System.out.println("Order Delivered:\n     ID: "+orderId+"\n     Cust Type:"+delivItem.getCustomerType());
		System.out.println("     Computer ordered: "+delivItem.getOrder()+"\n     Delivery time: "+ deliveryTime);
		System.out.println("     Net: "+calculateNet()+"\n     Screwed: "+screwed+"\n     Receipt: "+ r);
		
	}
	
	/**
	 * This is the output for the UI
	 * @return the final report generated by the world. 
	 */
	public String getOutput(){
		return finalRep;
	}


}

