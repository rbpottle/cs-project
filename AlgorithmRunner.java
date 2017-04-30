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

				
				// Read the lines of constraints
				for (int j = 0; j < numConstraints; j++)   {
					// splits the line up
					line = bufferReader.readLine();
					String stringArray[] = line.replaceAll("\\s","").split(",");
					
					// add to the HashSet for each class
					for (String currString: stringArray) {
						int currClass = Integer.parseInt(currString);
						HashSet<Integer> currHashMap = constraints.get(currClass);
						// currHashmap points to a HashSet and is able to edit it
						for (String otherString: stringArray) {
							int otherClass = Integer.parseInt(otherString);
							if (otherClass != currClass) { // can't add itself to its own HashSet
								currHashMap.add(otherClass);
							}
						}
					}
				}

				//Close the buffer reader
				// System.out.println("finished reading the input file...");
				bufferReader.close();
				
				// -------------------- FOR RUNNING THE ALGORITHM -------------------- \\
				
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
	            for (Item currItem: itemsPCR) {
	            	totalProfitPCR += currItem.getProfit();
	            }

	            // greedy based on only profit
	            BadAlgorithm greedyProfit = new BadAlgorithm();
				ArrayList<Item> tempItemList2 = new ArrayList<Item>();
				for (Item currItem: itemList) {
					String name = currItem.getName();
					int classNum = currItem.getClassNum();
					double weight = currItem.getWeight();
					double cost = currItem.getCost();
					double resale = currItem.getResale();
					tempItemList2.add(new ItemProfitGreedy(name, classNum, weight, cost, resale));
				}
				greedyProfit.newStore(tempItemList2, pounds, dollars, constraints, numItems);
				ArrayList<Item> items2 = greedyProfit.runAlgorithm();
				double totalProfit2 = 0.0;
	            for (Item currItem: items2) {
	            	totalProfit2 += currItem.getProfit();
	            }

	            // literally random (done 50 times)
	            double totalProfitRandom = 0.0;
	            ArrayList<Item> itemsRandom = new ArrayList<Item>();
	            for (int k = 0; k < 50; k++) {
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
					ArrayList<Item> items3 = randomAlgo.runAlgorithm();
					double totalProfit3 = 0.0;
		            for (Item currItem: items3) {
		            	totalProfit3 += currItem.getProfit();
		            }
		            
		            if (totalProfit3 > totalProfitRandom) {
		            	totalProfitRandom = totalProfit3;
		            	itemsRandom = items3;
		            	
		            }
		            
	            }
	            
	            
	            
	            
	            // initialize the largest profit as well as the corresponding items
	            ArrayList<Item> itemsToBuy = itemsPCR; // temporarily sets to avoid errors
				double highestProfit = totalProfitPCR;
	            
	            // compare the profit of the algorithms
				System.out.println("PCR profit is: " + totalProfitPCR);				
				System.out.println("ProfitGreedy profit is: " + totalProfit2);
				System.out.println("Random order profit is: " + totalProfitRandom);
	            if (totalProfitPCR > totalProfit2) {
	            	itemsToBuy = itemsPCR;
	            	highestProfit = totalProfitPCR;
	            } else {
	            	itemsToBuy = items2;
	            	highestProfit = totalProfit2;
	            }
	            
	            if (totalProfitRandom> highestProfit) {
	            	itemsToBuy = itemsRandom;
	            	highestProfit = totalProfitRandom;            	
	            }
	            
	            if (highestProfit == totalProfitPCR) {
	            	System.out.println("PCR has the most profit of: " + highestProfit);
	            } else if (highestProfit == totalProfit2) {
	            	System.out.println("ProfitGreedy has the most profit of: " + highestProfit);
	            } else {
	            	System.out.println("Random order has the most profit of: " + highestProfit);
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

		            // System.out.println("finished writing output file");
		            
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