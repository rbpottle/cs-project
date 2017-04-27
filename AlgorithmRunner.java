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
					String stringArray[] = line.split("; ");
					
					// initialize item, add it to the list
					String name = stringArray[0];
					int classNum = Integer.parseInt(stringArray[1]);
					double weight = Double.parseDouble(stringArray[2]);
					double cost = Double.parseDouble(stringArray[3]);
					double resale = Double.parseDouble(stringArray[4]);
					itemList.add(new Item(name, classNum, weight, cost, resale));
					
				}
				
				// Initialize HashMaps and HashSets for each class
				HashMap<Integer, HashSet<Integer>> constraints = new HashMap<Integer, HashSet<Integer>>();
				for (int classNum = 0; classNum < numItems; classNum++) {
					constraints.put(classNum, new HashSet<Integer>());
				}
				
				
				// Read the lines of constraints
				for (int j = 0; j < numConstraints; j++)   {
					// splits the line up
					line = bufferReader.readLine();
					String stringArray[] = line.split(", ");
					
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
				System.out.println("finished reading the input file...");
				bufferReader.close();
				
				// -------------------- FOR RUNNING THE ALGORITHM -------------------- \\
				BadAlgorithm algorithm = new BadAlgorithm();
				algorithm.newStore(itemList, pounds, dollars, constraints, numItems);
				ArrayList<Item> itemsToBuy = algorithm.runAlgorithm();
				
				
				// -------------------- FOR THE OUTPUT FILE BELOW -------------------- \\
				
				//choose file name here
				PrintWriter writer = null;
		        try {
		            //choose file name here
		            writer = new PrintWriter("output/problem" + i + ".out"); // i is still the problem number lol
		            
		            double totalProfit = 0.0;
		            
		            for (Item currItem: itemsToBuy) {
		            	writer.write(currItem.getName() + "\r\n");
		            	totalProfit += currItem.getProfit();
		            	
		            	// System.out.println("added item: " + currItem.getName());
		            }
		           
		            System.out.println("total profit is: " + totalProfit);
		            
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