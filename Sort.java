import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;
public class Sort{
    /** 
	 * partition(int myArray[], int start, int end) method 
	 * places the last element element at its correct position in sorted array myArray, 
	 * and places all the greater elements to the left of the last element and  
     * all smaller elements to right of the last element. 
	 */
    static int partition(int myArray[], int start, int end){ 
        int pivot = myArray[end]; // pivot is the last element 
        int i = (start-1); // index of greater element 
        for (int j=start; j<end; j++){ 
            // If  myArray[j] > pivot 
            if (myArray[j] > pivot){ 
                i++; 
  
                // swap myArray[i] and myArray[j] 
                int temp = myArray[i]; 
                myArray[i] = myArray[j]; 
                myArray[j] = temp; 
            } 
        } 
  
        // swap myArray[i+1] and myArray[end] (or pivot) 
        int temp = myArray[i+1]; 
        myArray[i+1] = myArray[end]; 
        myArray[end] = temp; 
  
        return i+1; 
    } 
  
  
    /** 
	 * sort(int myArray[], int start, int end) method implements QuickSort() 
	 * using method partition(int myArray[], int start, int end).
     * myArray[] represents the array we want to sort, 
     * start represents the starting index, 
     * end represents the ending index. 
	 */
    static void sort(int myArray[], int start, int end){ 
        if (start < end){ 
            // call method partition(int myArray[], int start, int end).
            int pi = partition(myArray, start, end); 
  
            // Recursively sort elements before and 
			// after calling method partition(int myArray[], int start, int end).
            sort(myArray, start, pi-1); 
            sort(myArray, pi+1, end); 
        } 
    } 
  
  
    public static void main (String[] args){
		try{
			// Get file name from the user.
			Path path = Paths.get(args[0]);
			Path fileName = path.getFileName();
			Scanner in= new Scanner(new BufferedReader(new FileReader(fileName.toString())));
			// Create SingleLinkedList folders to store each folders size.
			SingleLinkedList<Integer> folders = new SingleLinkedList<>();
			boolean end1 = false;
			// while the file still has data and each folders size is between 0 and 1000000
			// read from file and store in folders using InsertAtBack(T ItemToInsert) method.
			while ((in.hasNext())&&(!end1)){
				int fol = in.nextInt();
				if ((fol >= 0)&&(fol <= 1000000))
					folders.InsertAtBack(fol);
				else
					end1 = true;
			}
			// If we did not get any wrong data
			if (!end1){
				// Copy SingleLinkedList folders to array FolArray using methods RemoveFromFont()
				// and InsertAtBack(T ItemToInsert) and calculate the sum of all folders.
				int[] FolArray = new int[folders.size()];
				int sum = 0;
				for (int i = 0; i < folders.size(); i++){
					int fol = folders.RemoveFromFront();
					FolArray[i] = fol;
					folders.InsertAtBack(fol);
					sum += fol;
				}
				// Sort folders in FolArray
				sort(FolArray, 0, folders.size() - 1);
				// Create the first Disk.
				Disk d = new Disk();
				// Create a Priority Queue and insert the first Disk.
				MaxPQ PQDisks = new MaxPQ();
				PQDisks.insert(d);
				for (int i = 0; i < folders.size(); i++){
					// Using FindMax() method we find the Disk with the most free space (d_m).
					Disk d_m = PQDisks.FindMax();
					// If d_m's free space is greater or equal to the folder's size store the folder in d_m.
					if (d_m.getFreeSpace() >= FolArray[i]){
						PQDisks.getmax(); // Remove d_m from PQDisks.
						d_m.Add(FolArray[i]); // Add the folder to d_m.
						PQDisks.insert(d_m); // Insert d_m into PQDisks.
					}
					else{
						// Else create new Disk to store the folder and insert it into PQDisks.
						Disk d_n = new Disk();
						d_n.Add(FolArray[i]);
						PQDisks.insert(d_n);
					}
				}
				float sumTB = (float)sum;
				// Print sum of all folders and number of disks used.
				System.out.println("Sum of all folders = "+ sumTB*Math.pow(10,-6) +" TB");
				System.out.println("Total number of disks used = "+PQDisks.size());
				// Print Queue using PrintPQ() method if Disks are less or equal to 100.
				if (folders.size() <= 100)
					PQDisks.PrintPQ();
			}
			// If we got wrong data.
			else{
				System.out.print("Wrong data! Folder size must be between 0 and 1000000");
			}
		}
		// Throw exception if the path is wrong or inexistent.
		catch(IOException ioe){
			System.err.println("Error! File not found!");
			System.exit(1);
		}
	}
}