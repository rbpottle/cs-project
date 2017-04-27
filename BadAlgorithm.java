import java.util.*;

public class BadAlgorithm{

	private ArrayList<Item> itemList;
	private int carryLimit;
	private int money;
	private HashMap<Integer, HashSet<Integer>> constraints;
	private int numItems;

	public BadAlgorithm() {

	}

	public void newStore(ArrayList<Item> itemList, int carryLimit, int money, HashMap<Integer, HashSet<Integer>> constraints, int numItems) {
		this.itemList = itemList;
		this.carryLimit = carryLimit;
		this.money = money;
		this.constraints = constraints;
		this.numItems = numItems;
	}

	public ArrayList<String> runAlgorithm() {
		HashMap<Integer, HashSet<Integer>> currentConstraints = this.constraints;
		ArrayList<Item> itemsLeft = itemList;
		int moneyLeft = money;
		int weightLeft = carryLimit;
		HashSet<Integer> incompatible = new HashSet<Integer>();
		ArrayList<String> itemsToBuy = new ArrayList<String>();
		Collections.sort(itemsLeft);
		while (!itemsLeft.isEmpty()) {
			Item itemToAdd = itemsLeft.remove(0);
			if (itemToAdd.getWeight() <= weightLeft && itemToAdd.getCost() <= moneyLeft && !incompatible.contains(itemToAdd.getClass())) {
				itemsToBuy.add(itemToAdd.getName());
				moneyLeft -= itemToAdd.getCost();
				weightLeft -= itemToAdd.getWeight();
				if (currentConstraints.containsKey(itemToAdd.getClass())) {
					for (int conflictingClass : currentConstraints.remove(itemToAdd.getClass())) {
						incompatible.add(conflictingClass);
					}
				}

			}
		}
		return itemsToBuy;

	}

}