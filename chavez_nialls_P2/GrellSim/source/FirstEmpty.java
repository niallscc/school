package source;

import edu.unm.cs.cs351.f10.tdrl.p2.CIT;

/**
 * First empty checks for the queue that will be empty first, and assigns that item to that queue. 
 * @author niallschavez
 *
 * @param <S>
 */
public class FirstEmpty<S> implements Assignment<S>{

	@Override
	public void plantType(AssemblyLine<S>[] lines,
			CIT<S> cit, MyOrderReceipt<S> oR) {

		//TODO fix this to put it to spec
		int isEmptyFlag=0;
		int shortestQueue=100000;
		
		for(int i=0; i< lines.length; i++){
			
			if(lines[i].queue.isEmpty()){
				lines[i].addOrder(cit, oR);
				
				i=lines.length;
				isEmptyFlag=1;
				if( lines[i].queue.length() < shortestQueue)
					shortestQueue=i;
				
			}
			if(isEmptyFlag == 0){
				lines[shortestQueue].addOrder(cit, oR);
			}
		}
	}

}
