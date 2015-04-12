package gameBoard;

/**
 * @author amrsaad
 *
 */
public enum ItemTypeCost {
	
	MOOLAH(100),
	FOOD(10),
	DRINK(10);

	
	private final int _cost;
	
	ItemTypeCost(int cost){
		_cost = cost;
	}
	
	
	public int getCost(){
		return _cost;
	}
	
}
