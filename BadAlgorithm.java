import java.util.*;

public class BadAlgorithm{

	private ArrayList<Item> itemList;
	private double carryLimit;
	private double money;
	private HashMap<Integer, HashSet<Integer>> constraints;
	private int numItems;

	public BadAlgorithm() {

	}

	public void newStore(ArrayList<Item> itemList, double carryLimit, double money, HashMap<Integer, HashSet<Integer>> constraints, int numItems) {
		this.itemList = itemList;
		this.carryLimit = carryLimit;
		this.money = money;
		this.constraints = constraints;
		this.numItems = numItems;
	}

	public ArrayList<Item> runAlgorithm() {
		HashMap<Integer, HashSet<Integer>> currentConstraints = this.constraints;
		ArrayList<Item> itemsLeft = itemList;
		double moneyLeft = money;
		double weightLeft = carryLimit;
		HashSet<Integer> incompatible = new HashSet<Integer>();
		ArrayList<Item> itemsToBuy = new ArrayList<Item>();
		Collections.sort(itemsLeft);
		while (!itemsLeft.isEmpty()) {
			Item itemToAdd = itemsLeft.remove(0);
			if (itemToAdd.getWeight() <= weightLeft && itemToAdd.getCost() <= moneyLeft && !incompatible.contains(itemToAdd.getClass())) {
				itemsToBuy.add(itemToAdd);
				moneyLeft -= itemToAdd.getCost();
				weightLeft -= itemToAdd.getWeight();
				if (currentConstraints.containsKey(itemToAdd.getClass())) {
					HashSet<Integer> incomp = currentConstraints.remove(itemToAdd.getClass());
					for (Integer conflictingClass : incomp) {
						incompatible.add(conflictingClass);
					}
				}

			}
		}
		return itemsToBuy;

	}

}