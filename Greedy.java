import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;
class Greedy{
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
				System.out.println("Total number of disks used = " + PQDisks.size());
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