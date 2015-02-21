package chavez_nialls_lab07;


public class DataObject
{
	private int ID;
	private String NAME;
	private int PRIORITY;
	
	public DataObject(int _id, String _name, int _priority)
	{
		ID = _id;
		NAME = _name;
		PRIORITY = _priority;
	}
	
	public DataObject(DataObject _DO)
	{
		ID = _DO.getID();
		NAME = _DO.getNAME();
		PRIORITY = _DO.getPRIORITY();
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public int getPRIORITY() {
		return PRIORITY;
	}

	public void setPRIORITY(int priority) {
		PRIORITY = priority;
	}
	
	
	public String toString()
	{
		return new String("------------------------------------\nName: " + NAME + "\t ID: " + ID + "\t Priority: " + PRIORITY + "\n------------------------------------\n");
	}
	
	
}
