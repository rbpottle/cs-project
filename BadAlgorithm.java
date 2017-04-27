import java.io.*;
import java.util.*;
import Item;

public class BadAlgorithm{

	private ArrayList<Item> itemList;
	private int carryLimit;
	private int money;
	private HashMap<int, HashSet<int>> constraints
	private int numItems;

	public BadAlgorithm() {

	}

	public newStore(ArrayList<Item> itemList, int carryLimit, int money, HashMap<int, HashSet<int>> constraints, int numItems) {
		this.itemList = itemList;
		this.carryLimit = carryLimit;
		this.money = money;
		this.constraints = constraints;
		this.numItems = numItems;;;;
	}

	public ArrayList<String> runAlgorithm() {
		HashMap<int, HashSet<int>> currentConstraints = this.constraints;
		ArrayList<Item> itemsLeft = itemList;
		int moneyLeft = money;
		int weightLeft = carryLimit;
		HashSet<int> incompatible = new HashSet<int>();
		ArrayList<String> itemsToBuy = new ArrayList<String>();
		Collections.sort(itemsLeft);
		while (!itemsLeft.isEmpty()) {
			Item itemToAdd = ArrayList.remove(0);
			if (itemToAdd.getWeight() <= weightLeft && itemToAdd.getCost <= moneyLeft && !incompatible.contains(itemToAdd.getClass())) {
				itemsToBuy.add(itemToAdd);
				moneyLeft -= itemToAdd.getCost();
				weightLeft -= itemToAdd.getWeight();
				if (currentConstraints.containsKey(itemToAdd.getClass())) {
					for (int conflictingClass : currentConstraints.remove(itemToAdd.getClass)) {
						incompatible.add(conflictingClass);
					}
				}

			}
		}
		return itemsToBuy;

	}

}