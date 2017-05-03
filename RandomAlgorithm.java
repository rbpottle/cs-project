
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class RandomAlgorithm{

	private ArrayList<Item> itemList;
	private double carryLimit;
	private double money;
	private HashMap<Integer, HashSet<Integer>> constraints;
	private int numItems;

	public RandomAlgorithm() {

	}

	public void newStore(ArrayList<Item> itemList, double carryLimit, double money, HashMap<Integer, HashSet<Integer>> constraints, int numItems) {
		this.itemList = itemList;
		this.carryLimit = carryLimit;
		this.money = money;
		this.constraints = constraints;
		this.numItems = numItems;
	}

	public ArrayList<Item> runAlgorithm() {
		/*
		HashMap<Integer, HashSet<Integer>> currentConstraints = new HashMap<Integer, HashSet<Integer>>();
		for (int classNum = 0; classNum < numItems; classNum++) {
			currentConstraints.put(classNum, new HashSet<Integer>());
		}
		for (int currClass = 0; currClass < constraints.size(); currClass++)   {
			HashSet<Integer> originalHashSet = constraints.get(currClass);
			HashSet<Integer> currHashSet = currentConstraints.get(currClass);
			currHashSet.addAll(originalHashSet);
		}
		*/
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
		
		Collections.shuffle(itemsLeft);
		
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

}