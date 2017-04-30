import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class ProfitGreedy {

	private ArrayList<Item> itemList;
	private double carryLimit;
	private double money;
	private HashMap<Integer, HashSet<Integer>> constraints;
	private int numItems;
	private ArrayList<Item> itemsLeft;

	public ProfitGreedy(){

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
		itemsLeft = itemList;
		double moneyLeft = money;
		double weightLeft = carryLimit;
		HashSet<Integer> incompatible = new HashSet<Integer>();
		ArrayList<Item> itemsToBuy = new ArrayList<Item>();
		
		mergeSort(itemsLeft);
		Collections.reverse(itemsLeft);
		
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
	
	public void mergeSort(ArrayList<Item> itemsLeft){       
        divide(0, itemsLeft.size()-1);
    }
     
    public void divide(int startIndex,int endIndex){
         
        //Divide till you breakdown your list to single element
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divide(startIndex, mid);
            divide(mid+1, endIndex);        
             
            //merging Sorted array produce above into one sorted array
            merger(startIndex,mid,endIndex);            
        }       
    }   
     
    public void merger(int startIndex,int midIndex,int endIndex){
         
        //Below is the mergedarray that will be sorted array Array[i-midIndex] , Array[(midIndex+1)-endIndex]
        ArrayList<Item> mergedSortedArray = new ArrayList<Item>();
         
        int leftIndex = startIndex;
        int rightIndex = midIndex+1;
         
        while(leftIndex<=midIndex && rightIndex<=endIndex){
            if(itemsLeft.get(leftIndex).getProfit() < itemsLeft.get(rightIndex).getProfit()){
                mergedSortedArray.add(itemsLeft.get(leftIndex));
                leftIndex++;
            }else{
                mergedSortedArray.add(itemsLeft.get(rightIndex));
                rightIndex++;
            }
        }       
         
        //Either of below while loop will execute
        while(leftIndex<=midIndex){
            mergedSortedArray.add(itemsLeft.get(leftIndex));
            leftIndex++;
        }
         
        while(rightIndex<=endIndex){
            mergedSortedArray.add(itemsLeft.get(rightIndex));
            rightIndex++;
        }
         
        int i = 0;
        int j = startIndex;
        //Setting sorted array to original one
        while(i<mergedSortedArray.size()){
            itemsLeft.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }
    
	/*
	public ArrayList<Item> profitSort(ArrayList<Item> itemsLeft) {
		System.out.println("starting sort");
		int size = itemsLeft.size();
		for (int i = 0; i < size; i++) {
			for (int j = 1; j < (size - i); j++) {
				Item currItem = itemsLeft.get(j);
				Item otherItem = itemsLeft.get(j - 1);
				if (currItem.getProfit() < otherItem.getProfit()) {
					itemsLeft.set(j - 1, currItem);
					itemsLeft.set(j, otherItem);
				}
				
			}
			
			
		}
		System.out.println("ending sort");
		return itemsLeft;
		
	}
	*/
	
	/*
	public ArrayList<Item> mergeSort(ArrayList<Item> whole) {
        ArrayList<Item> left = new ArrayList<Item>();
        ArrayList<Item> right = new ArrayList<Item>();
        int center;
 
        if (whole.size() == 1) {    
            return whole;
        } else {
            center = whole.size()/2;
            // copy the left half of whole into the left.
            for (int i=0; i<center; i++) {
                    left.add(whole.get(i));
            }
 
            //copy the right half of whole into the new arraylist.
            for (int i=center; i<whole.size(); i++) {
                    right.add(whole.get(i));
            }
 
            // Sort the left and right halves of the arraylist.
            left  = mergeSort(left);
            right = mergeSort(right);
 
            // Merge the results back together.
            merge(left, right, whole);
        }
        return whole;
    }
 
    private void merge(ArrayList<Item> left, ArrayList<Item> right, ArrayList<Item> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;
 
        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ( left.get(leftIndex).getProfit() < right.get(rightIndex).getProfit() ) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }
 
        ArrayList<Item> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            // The left ArrayList has been use up...
            rest = right;
            restIndex = rightIndex;
        } else {
            // The right ArrayList has been used up...
            rest = left;
            restIndex = leftIndex;
        }
 
        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i=restIndex; i<rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }
    */
	
	
}
