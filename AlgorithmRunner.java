import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AlgorithmRunner {
	public static void main(String[] args) {
		
		int numProblems = 1; // adjust this for however many number of problems there are
		for (int i = 1; i <= numProblems; i++) {
			String fileName="project_instances/problem" + i + ".in";
			try{
				//Create object of FileReader
				FileReader inputFile = new FileReader(fileName);
		
				//Instantiate the BufferedReader Class
				BufferedReader bufferReader = new BufferedReader(inputFile);
		
				//Variable to hold the one line data
				String line;
				
				double pounds = Double.parseDouble(bufferReader.readLine());
				double dollars = Double.parseDouble(bufferReader.readLine());
				int numOfItems = Integer.parseInt(bufferReader.readLine());
				int numOfConstraints = Integer.parseInt(bufferReader.readLine());
				
				System.out.println(pounds);
				System.out.println(dollars);
				System.out.println(numOfConstraints);
				System.out.println(numOfItems);

				// Read the lines of items
				ArrayList<Item> itemList = new ArrayList<Item>();
				for (int j = 0; j < numOfItems; j++)   {
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
				for (int classNum = 0; classNum < numOfItems; classNum++) {
					constraints.put(classNum, new HashSet<Integer>());
				}
				
				
				// Read the lines of constraints
				for (int j = 0; j < numOfConstraints; j++)   {
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
				System.out.println("all done!");
				bufferReader.close();
			}catch(Exception e){
				System.out.println("Error while reading file line by line:" + e.getMessage());                      
			}
			
		}
		
	}
}