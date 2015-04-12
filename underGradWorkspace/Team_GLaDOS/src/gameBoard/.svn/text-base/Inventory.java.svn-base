package gameBoard;

import java.util.HashMap;
import java.util.Map;
/**
 * @author amrsaad
 *
 */
public class Inventory {
	Map<ItemTypeCost, Integer> inventory;

	public Inventory() {
		inventory = new HashMap<ItemTypeCost, Integer>();
		inventory.put(ItemTypeCost.MOOLAH,ItemTypeCost.MOOLAH.getCost());
		inventory.put(ItemTypeCost.FOOD, ItemTypeCost.FOOD.getCost());
		inventory.put(ItemTypeCost.DRINK, ItemTypeCost.DRINK.getCost());
	}

	public int getCost(ItemTypeCost type) {
		return inventory.get(type);
	}

	public void increaseCost(ItemTypeCost type, int value) {
		int oldCost = inventory.get(type);
		inventory.put(type, oldCost+value);
	}
	
	public void decreaseCost(ItemTypeCost type, int value) {
		int oldCost = inventory.get(type);
		if (oldCost- value >= 0) inventory.put(type, oldCost-value);
	}
}
