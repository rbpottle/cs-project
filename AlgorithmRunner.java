import java.io.BufferedReader;
import java.io.FileReader;

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
				for (int i = 0; i < numOfItems; i++)   {
					line = bufferReader.readLine();
					
				}
				//Close the buffer reader
				bufferReader.close();
			}catch(Exception e){
				System.out.println("Error while reading file line by line:" + e.getMessage());                      
			}
			
		}
		
	}
}