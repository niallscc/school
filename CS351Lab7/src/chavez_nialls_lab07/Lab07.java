package chavez_nialls_lab07;


import java.util.ArrayList;
import java.util.PriorityQueue;


public class Lab07 
{

	PriorityQueue<DataObject> PQ;
	DataFactory FACTORY;
	
	public Lab07(ArrayList<DataObject> al, DataFactory _FACTORY)
	{
		PQ = new PriorityQueue<DataObject>();
		
		for (int index = 0; index < al.size(); index++)
		{
			PQ.add(_FACTORY.getDataObject(al.get(index)));
			
		
		}
		
		while (!PQ.isEmpty())
		{
			System.out.println(PQ.poll().toString());
		}
		System.out.println("####################################\n####################################\n####################################\n");
	}
	
	
	
	public static void main(String args[])
	{	
		
		ArrayList<DataObject> tempArray = new ArrayList<DataObject>();
		
		tempArray.add(new DataObject(1, "Epsilon", 3));
		tempArray.add(new DataObject(2, "Delta", 5));
		tempArray.add(new DataObject(3, "Charlie", 2));
		tempArray.add(new DataObject(4, "Beta", 1));
		tempArray.add(new DataObject(5, "Alpha", 4));
		
		new Lab07(tempArray, new IDFactory());		
		new Lab07(tempArray, new NameFactory());
		new Lab07(tempArray, new PriorityFactory());
	}
	
}//END LAB8
	
	class IDWrapper extends DataObject implements Comparable<IDWrapper>{

		public IDWrapper(DataObject _DO)
		{
			super (_DO);

		}
		
		@Override
		public int compareTo(IDWrapper id2) {
			
				return this.getID()- id2.getID();
		}
		
	}

	class NameWrapper extends DataObject implements Comparable<NameWrapper>{

			
		public NameWrapper(DataObject _DO) {
			super(_DO);
		}

		@Override
		public int compareTo(NameWrapper n) {
				
				return this.getNAME().compareTo(n.getNAME());
		}
		
	}

	class PriorityWrapper extends DataObject implements Comparable<PriorityWrapper>{

		public PriorityWrapper(DataObject _DO) {
			super(_DO);
		}


		@Override
		public int compareTo(PriorityWrapper i) {
			
			return this.getPRIORITY()- i.getPRIORITY();
		}
		
	}

	class IDFactory implements DataFactory {

		@Override
		public DataObject getDataObject(DataObject _DO) {
			
			return new IDWrapper(_DO);
		}
		
	}

	class NameFactory implements DataFactory {

		@Override
		public DataObject getDataObject(DataObject _DO) {
			return new NameWrapper(_DO);
		}
		
	}

	class PriorityFactory implements DataFactory {

		@Override
		public DataObject getDataObject(DataObject _DO) {
			return new PriorityWrapper(_DO);
		}
		
	}


