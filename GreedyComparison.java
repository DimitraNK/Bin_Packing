import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.FileReader;
// Public class GreedyComparison has Greeedy's code.
public class GreedyComparison{
	// Static method Greedy1 functions just like Greedy's main method with a few necessary changes.
	// Greedy1 has one parameter, fileName, of type String, the file we want to read data from. 
	// Greedy1 throws IOException if the fileName does not exist.
	public static int Greedy1(String fileName) throws IOException{
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