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
		HashMap<Integer, HashSet<Integer>> currentConstraints = new HashMap<Integer, HashSet<Integer>>();
		for (int classNum = 0; classNum < numItems; classNum++) {
			if (constraints.containsKey(classNum)) {
				currentConstraints.put(classNum, new HashSet<Integer>());
			}
		}
		
		for (int currClass: constraints.keySet())   {
			HashSet<Integer> originalHashSet = constraints.get(currClass);
			HashSet<Integer> currHashSet = currentConstraints.get(currClass);
			currHashSet.addAll(originalHashSet);
			
		}
		
		
		
		
		ArrayList<Item> itemsLeft = itemList;
		double moneyLeft = money;
		double weightLeft = carryLimit;
		HashSet<Integer> incompatible = new HashSet<Integer>();
		ArrayList<Item> itemsToBuy = new ArrayList<Item>();
		
		Collections.sort(itemsLeft);
		
		while (!itemsLeft.isEmpty()) {
			Item itemToAdd = itemsLeft.remove(0);
			if (itemToAdd.getWeight() <= weightLeft && itemToAdd.getCost() <= moneyLeft && !incompatible.contains(itemToAdd.getClassNum())) {
				itemsToBuy.add(itemToAdd);
				moneyLeft -= itemToAdd.getCost();
				weightLeft -= itemToAdd.getWeight();
				if (currentConstraints.containsKey(itemToAdd.getClassNum())) {
					HashSet<Integer> incomp = currentConstraints.get(itemToAdd.getClassNum());
					for (Integer conflictingClass : incomp) {
						incompatible.add(conflictingClass);
					}
					currentConstraints.remove(itemToAdd.getClassNum());
				}

			}
		}
		return itemsToBuy;

	}

	public ArrayList<Item> greedyClasses() {
		HashMap<Integer, HashSet<Item>> itemsByClass = new HashMap<Integer, HashSet<Item>>();
		for (Item i : itemList) {
			int currClass = i.getClassNum();
			if (!itemsByClass.containsKey(currClass)) {
				itemsByClass.put(currClass, new HashSet<Item>());
			}
			itemsByClass.get(currClass).add(i);
		}
		ArrayList<ClassItem> classItems = new ArrayList<ClassItem>();
		for (Integer j: itemsByClass.keySet()) {
			int numClass = itemsByClass.get(j).size();
			double totalProfit = 0;
			for (Item k: itemsByClass.get(j)) {
				totalProfit += k.getProfit();
			}
			classItems.add(new ClassItem(j, totalProfit/numClass));
		}
		Collections.sort(classItems);
		ArrayList<Item> itemsToBuy = new ArrayList<Item>();
		HashSet<Integer> incomp = new HashSet<Integer>();
		HashSet<Integer> seen = new HashSet<Integer>();
		double moneyLeft = money;
		double weightLeft = carryLimit;
		for (ClassItem c : classItems) {
			if (!incomp.contains(c.getClassNum())) {
				if (!seen.contains(c.getClassNum())) {
					incomp.addAll(constraints.get(c.getClassNum()));
				}
				for (Item itemToAdd : itemsByClass.get(c.getClassNum()))
					if (itemToAdd.getWeight() <= weightLeft && itemToAdd.getCost() <= moneyLeft) {
						itemsToBuy.add(itemToAdd);
						moneyLeft -= itemToAdd.getCost();
						weightLeft -= itemToAdd.getWeight();
					}
			}
		}
		return itemsToBuy;
	}

}