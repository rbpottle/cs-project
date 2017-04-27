import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class TextOutput {
	public static void main(String[] args) {
        PrintWriter writer = null;
        try {
            //choose file name here
            writer = new PrintWriter("problem1.txt");
            
            // -------------------- EDIT THESE 4 LINES -------------------- \\
            int pounds = 150000; // change this int for desired P pounds
        	int dollars = 5000000; // int for desired M dollars
        	int numberOfItems = 90000; // int for desired N # of items
        	int numberOfConstraints = 80; // int for desired C # of constraints
        	
        	writer.write(pounds + "\r\n");
        	writer.write(dollars + "\r\n");
        	writer.write(numberOfItems + "\r\n");
        	writer.write(numberOfConstraints + "\r\n");
        	
        	
            for (int itemNum = 0; itemNum < numberOfItems; itemNum++) {
            	// this loop creates each item
            	Random rand = new Random();           	
            	int classNum = rand.nextInt(250); // random int in range [0,n)
            	int w = rand.nextInt(1000); // tens and ones place become "cents"
            	int c = rand.nextInt(50000); 
            	int r = rand.nextInt(500000); 
            	double weight = w / 100.0;
            	double cost = c / 100.0;
            	double resaleValue = r / 100.0;
            	
            	writer.write("item_name_" + itemNum + "; ");
            	writer.write(classNum + "; ");
            	writer.write(weight + "; ");
            	writer.write(cost + "; ");
            	writer.write(resaleValue + "\r\n"); // creates new line too
            	
            }
            
            for (int constraintNum = 0; constraintNum < numberOfConstraints; constraintNum++) {
            	// this loop creates each constraint
            	Random rand = new Random();
            	
            	int constraintCount = rand.nextInt(3) + 2; // number of items in this constraint
            	ArrayList<Integer> added = new ArrayList<Integer>();
            	int i = 0;
            	while (i < constraintCount) {
            		int incompatibleClassNum = rand.nextInt(250);
            		if (!added.contains(incompatibleClassNum)) {
            			
            			if (i == 0){
            				writer.write(Integer.toString(incompatibleClassNum));
            			} else {
                            writer.write(", " + incompatibleClassNum );
                        }
                        i += 1;
                        added.add(incompatibleClassNum);
            		}
            	
            	}
            	writer.write("\r\n"); // new line for next constraint
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
