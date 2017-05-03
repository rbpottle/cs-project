import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class AlgorithmRunner {
	public static void main(String[] args) {
		
		int numProblems = 21; // adjust this for however many number of problems there are
		for (int i = 1; i <= numProblems; i++) {
			System.out.println("reading from problem" + i);
			
			String fileName="project_instances/problem" + i + ".in";
			try{
				// -------------------- FOR READING INPUT FILE -------------------- \\
				
				//Create object of FileReader
				FileReader inputFile = new FileReader(fileName);
		
				//Instantiate the BufferedReader Class
				BufferedReader bufferReader = new BufferedReader(inputFile);
		
				//Variable to hold the one line data
				String line;
				
				double pounds = Double.parseDouble(bufferReader.readLine());
				double dollars = Double.parseDouble(bufferReader.readLine());
				int numItems = Integer.parseInt(bufferReader.readLine());
				int numConstraints = Integer.parseInt(bufferReader.readLine());
				
				// Read the lines of items
				ArrayList<Item> itemList = new ArrayList<Item>();
				for (int j = 0; j < numItems; j++)   {
					// splits the line up
					line = bufferReader.readLine();
					String stringArray[] = line.replaceAll("\\s","").split(";");
					
					// initialize item, add it to the list
					String name = stringArray[0];
					int classNum = Integer.parseInt(stringArray[1]);
					double weight = Double.parseDouble(stringArray[2]);
					double cost = Double.parseDouble(stringArray[3]);
					double resale = Double.parseDouble(stringArray[4]);
					itemList.add(new Item(name, classNum, weight, cost, resale));
					
				}
				
				// Initialize HashMap and HashSets for each class
				HashMap<Integer, HashSet<Integer>> constraints = new HashMap<Integer, HashSet<Integer>>();
				for (int classNum = 0; classNum < numItems; classNum++) {
					constraints.put(classNum, new HashSet<Integer>());
				}

				System.out.println("size of constraints is: " + constraints.size());
				
				// Read the lines of constraints
				for (int j = 0; j < numConstraints; j++)   {
					// splits the line up
					line = bufferReader.readLine();
					String stringArray[] = line.replaceAll("\\s","").split(",");
					
					// add to the HashSet for each class
					for (String currString: stringArray) {
						int currClass = Integer.parseInt(currString);
						HashSet<Integer> currHashSet = constraints.get(currClass);
						// currHashmap points to a HashSet and is able to edit it
						for (String otherString: stringArray) {
							int otherClass = Integer.parseInt(otherString);
							if (otherClass != currClass) { // can't add itself to its own HashSet
								currHashSet.add(otherClass);
							}
						}
					}
				}
				/*
				// remove unnecessary key value pairs
				for (int classNum = 0; classNum < numItems; classNum++) {
					HashSet<Integer> currHashSet = constraints.get(classNum);
					if (currHashSet.isEmpty()) {
						constraints.remove(classNum);
					}
				}
				*/
				System.out.println("size of constraints is: " + constraints.size());

				
				//Close the buffer reader
				System.out.println("finished reading the input file...");
				bufferReader.close();
				
				// -------------------- FOR RUNNING THE ALGORITHMS -------------------- \\
				
				//initialize and run each algorithm 

				// greedy based on PCR
				BadAlgorithm greedyPCR = new BadAlgorithm();
				ArrayList<Item> tempItemList1 = new ArrayList<Item>();
				for (Item currItem: itemList) {
					String name = currItem.getName();
					int classNum = currItem.getClassNum();
					double weight = currItem.getWeight();
					double cost = currItem.getCost();
					double resale = currItem.getResale();
					tempItemList1.add(new Item(name, classNum, weight, cost, resale));
				}
				greedyPCR.newStore(tempItemList1, pounds, dollars, constraints, numItems);
				ArrayList<Item> itemsPCR = greedyPCR.runAlgorithm();
				double totalProfitPCR = 0.0;
				double totalResalePCR = 0.0;
				double totalCostPCR = 0.0;
				for (Item currItem: itemsPCR) {
					totalProfitPCR += currItem.getProfit();
					totalResalePCR += currItem.getResale();
					totalCostPCR += currItem.getCost();
				}
				double totalPCR = totalResalePCR + (dollars - totalCostPCR);
				System.out.println("PCR finished");
				
				// greedy based on only profit
				BadAlgorithm greedyP = new BadAlgorithm();
				ArrayList<Item> tempItemListP = new ArrayList<Item>();
				for (Item currItem: itemList) {
					String name = currItem.getName();
					int classNum = currItem.getClassNum();
					double weight = currItem.getWeight();
					double cost = currItem.getCost();
					double resale = currItem.getResale();
					tempItemListP.add(new ItemProfitGreedy(name, classNum, weight, cost, resale));
				}
				greedyP.newStore(tempItemListP, pounds, dollars, constraints, numItems);
				ArrayList<Item> itemsP = greedyP.runAlgorithm();
				double totalProfitP = 0.0;
				double totalResaleP = 0.0;
				double totalCostP = 0.0;
				for (Item currItem: itemsP) {
					totalProfitP += currItem.getProfit();
					totalResaleP += currItem.getResale();
					totalCostP += currItem.getCost();
				}
				double totalP = totalResaleP + (dollars - totalCostP);
				System.out.println("ProfitGreedy finished");
				
				// greedy based on a heuristic
				BadAlgorithm greedyH = new BadAlgorithm();
				ArrayList<Item> tempItemListH = new ArrayList<Item>();
				for (Item currItem: itemList) {
					String name = currItem.getName();
					int classNum = currItem.getClassNum();
					double weight = currItem.getWeight();
					double cost = currItem.getCost();
					double resale = currItem.getResale();
					tempItemListH.add(new ItemHeuristic(name, classNum, weight, cost, resale));
				}
				greedyH.newStore(tempItemListH, pounds, dollars, constraints, numItems);
				ArrayList<Item> itemsH = greedyH.runAlgorithm();
				double totalProfitH = 0.0;
				double totalResaleH = 0.0;
				double totalCostH = 0.0;
				for (Item currItem: itemsH) {
					totalProfitH += currItem.getProfit();
					totalResaleH += currItem.getResale();
					totalCostH += currItem.getCost();
				}
				double totalH = totalResaleH + (dollars - totalCostH);
				System.out.println("ProfitHeuristic finished");
				
				BadAlgorithm algoGC = new BadAlgorithm();
				ArrayList<Item> tempItemListGC = new ArrayList<Item>();
				for (Item currItem: itemList) {
					String name = currItem.getName();
					int classNum = currItem.getClassNum();
					double weight = currItem.getWeight();
					double cost = currItem.getCost();
					double resale = currItem.getResale();
					tempItemListGC.add(new Item(name, classNum, weight, cost, resale));
				}
				algoGC.newStore(tempItemListGC, pounds, dollars, constraints, numItems);
				ArrayList<Item> itemsGC = algoGC.greedyClasses();
				double totalProfitGC = 0.0;
				double totalResaleGC = 0.0;
				double totalCostGC = 0.0;
				for (Item currItem: itemsGC) {
					totalProfitGC += currItem.getProfit();
					totalResaleGC += currItem.getResale();
					totalCostGC += currItem.getCost();
				}
				double totalGC = totalResaleGC + (dollars - totalCostGC);
				System.out.println("GreedyClasses finished");
				
				// literally random (done 50 times)
				double totalR = 0.0;
				ArrayList<Item> itemsRandom = new ArrayList<Item>();
				
				int l = 3;
				if (i == 4) {
					l = 0;
				}
				for (int k = 0; k < l; k++) {
					RandomAlgorithm randomAlgo = new RandomAlgorithm();
					ArrayList<Item> tempItemList3 = new ArrayList<Item>();
					for (Item currItem: itemList) {
						String name = currItem.getName();
						int classNum = currItem.getClassNum();
						double weight = currItem.getWeight();
						double cost = currItem.getCost();
						double resale = currItem.getResale();
						tempItemList3.add(new Item(name, classNum, weight, cost, resale));
					}
					
					randomAlgo.newStore(tempItemList3, pounds, dollars, constraints, numItems);
					
					ArrayList<Item> itemsR = randomAlgo.runAlgorithm();
					
					double totalProfitR = 0.0;
					double totalResaleR = 0.0;
					double totalCostR = 0.0;
					for (Item currItem: itemsR) {
						totalProfitR += currItem.getProfit();
						totalResaleR += currItem.getResale();
						totalCostR += currItem.getCost();
					}
					double temptotalR = totalResaleR + (dollars - totalCostR);

					if (temptotalR > totalR) {
						totalR = temptotalR;
						itemsRandom = itemsR;
						
					}

				}
				System.out.println("random finished");
				
				// initialize the largest profit as well as the corresponding items
				ArrayList<Item> itemsToBuy = itemsPCR; // temporarily sets to avoid errors
				double highestProfit = totalPCR;

				// compare the profit of the algorithms
				
				System.out.println("PCR profit is: " + totalPCR);
				System.out.println("ProfitGreedy profit is: " + totalP);
				System.out.println("HeuristicGreedy profit is: " + totalH);
				System.out.println("GreedyClasses profit is: " + totalGC);
				System.out.println("Random order profit is: " + totalR);
				
				
				if (totalP > highestProfit) {
					itemsToBuy = itemsP;
					highestProfit = totalP;
				}
				if (totalR > highestProfit) {
					itemsToBuy = itemsRandom;
					highestProfit = totalR;
				}
				if (totalH > highestProfit) {
					itemsToBuy = itemsH;
					highestProfit = totalH;
				}
				if (totalGC > highestProfit) {
					itemsToBuy = itemsGC;
					highestProfit = totalGC;
				}


				if (highestProfit == totalPCR) {
					System.out.println("PCR has the most profit of: " + highestProfit);
				} else if (highestProfit == totalP) {
					System.out.println("ProfitGreedy has the most profit of: " + highestProfit);
				} else if (highestProfit == totalR) {
					System.out.println("Random order has the most profit of: " + highestProfit);
				} else if (highestProfit == totalH) {
					System.out.println("Greedy Heuristic has the most profit of: " + highestProfit);
				} else if (highestProfit == totalGC) {
					System.out.println("Greedy Classes has the most profit of: " + highestProfit);
				}

				
				// -------------------- FOR THE OUTPUT FILE BELOW -------------------- \\
				
				//choose file name here
				PrintWriter writer = null;
				try {
					//choose file name here
					writer = new PrintWriter("output/problem" + i + ".out"); // i is still the problem number lol

					
					for (Item currItem: itemsToBuy) {
						writer.write(currItem.getName() + "\r\n");

					}

					System.out.println("finished writing output file");

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						// Close the writer regardless of what happens...
						writer.close();
					} catch (Exception e) {
					}
				}
				
			}catch(Exception e){
				System.out.println("Error while reading file line by line:" + e.getMessage());                      
			}
			
		}
		
	}
}