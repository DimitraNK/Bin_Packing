import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.FileReader;
// Public class GreedyComparison has Greeedy's code.
public class SortComparison{
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
	
	// Static method Greedy2 functions just like Sort's main method with a few necessary changes.
	// Greedy2 has one parameter, fileName, of type String, the file we want to read data from. 
	// Greedy2 throws IOException if the fileName does not exist.
	public static int Greedy2(String fileName) throws IOException{
			Scanner in= new Scanner(new BufferedReader(new FileReader(fileName)));
			// Create SingleLinkedList folders to store each folders size.
			SingleLinkedList<Integer> folders = new SingleLinkedList<>();
			
			// while the file still has data read from file and store in folders 
			// using InsertAtBack(T ItemToInsert) method.
			while (in.hasNext()){
				int fol = in.nextInt();
				folders.InsertAtBack(fol);
			}
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
		// Return number of disks used.
		return PQDisks.size();	
	}
}