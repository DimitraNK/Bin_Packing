import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;
class Comparison{
	// getRandomNumber() returns a random number in range [0,1000000]
	private static int getRandomNumber() {
		Random r = new Random();
		return r.nextInt(1000001);
	}
	
	public static void main (String[] args){
		BufferedWriter writer = null;
		// Array N stores 3 cases for the number of data in the files (case 1 -> each file has 100 numbers,
		// case 2 -> each file has 500 numbers, case 3 -> each file has 10000 numbers).
		int[] N ={100,500,1000};
		// GreedyDisks[3][10] and SortDisks[3][10] are used to store the number of disks
		// used for each case. In the firs array we store Greedy's outputs and in the second we 
		// store Greedy-decreasing's outputs.
		int[][] GreedyDisks = new int [3][10];
		int[][] SortDisks = new int [3][10];
		// In array GreedyDisksAverage we will store the average number of disks used for each value of N (Greedy).
		int[] GreedyDisksAverage = new int [3];
		// In array SortDisksAverage we will store the average number of disks used for each value of N (Greedy-decreasing).
		int[] SortDisksAverage = new int [3];
		try {
			for(int k=0; k<3 ; k++){
				for (int i = 1; i <= 10; i++){
					// Create and write in each file.
					writer = new BufferedWriter(new FileWriter("Input" + i + "_" + N[k] + ".txt"));
					for (int j = 0; j < N[k]; j++) {
						int m = getRandomNumber(); // m is the random number in [0,1000000]
						writer.write(String.valueOf(m)); // Write m in the file.
						writer.write("\n"); 
					}
					// Write any data left and close the file.
					writer.flush(); 
					writer.close(); 
				}
			}
			for(int k=0; k<3 ; k++){
				// SumGreedyDisks is the sum of disks used for each case.
				int SumGreedyDisks = 0;
				// SumSortDisks is the sum of disks used for each case.
				int SumSortDisks = 0;
				// Variable file helps us determine which file we are reading from.
				int file = 1;
				for (int i = 0; i < 10; i++){
					// Store the number of disks in the Arrays.
					GreedyDisks[k][i] = GreedyComparison.Greedy1("Input" + file + "_" + N[k] + ".txt");
					SortDisks[k][i] = SortComparison.Greedy2("Input" + file + "_" + N[k] + ".txt");
					SumGreedyDisks += GreedyDisks[k][i]; // Calculate sum for each case (Greedy).
					SumSortDisks += SortDisks[k][i]; // Calculate sum for each case (Greedy-decreasing).
					file++; // Next file.
				}
				// Calculate and store average for each case (Greedy).
				GreedyDisksAverage[k] = SumGreedyDisks / 10;
				// Calculate and store average for each case (Greedy-decreasing).
				SortDisksAverage[k] = SumSortDisks / 10;
			}
			// Print total number of disks used for each file for each case using both algorithms.
			System.out.println("Algorithm Greedy");
			for(int k=0; k<3 ; k++){
				System.out.println("Total number of disks used for each file with "+N[k]+" folders");
				for (int i = 0; i < 10; i++){
					System.out.print(GreedyDisks[k][i]);
					System.out.print(" ");
				}
				System.out.println(" ");
			}
			System.out.println("  -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-  ");
			System.out.println("Algorithm Greedy-decreasing");
			for(int k=0; k<3 ; k++){
				System.out.println("Total number of disks used for each file with "+N[k]+" folders");
				for (int i = 0; i < 10; i++){
					System.out.print(SortDisks[k][i]);
					System.out.print(" ");
				}
				System.out.println(" ");
			}
			// Print average number of disks used for each file for each case using both algorithms.
			System.out.println(">>-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-<<");
			System.out.println("Algorithm Greedy");
			for (int k=0; k<3; k++){
				System.out.println("Average number of disks used for each file with "+N[k]+" folders");
				System.out.println(GreedyDisksAverage[k]);
			}
			System.out.println("  -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-  ");
			System.out.println("Algorithm Greedy-decreasing");
			for (int k=0; k<3; k++){
				System.out.println("Average number of disks used for each file with "+N[k]+" folders");
				System.out.println(SortDisksAverage[k]);
			}
			int g = 0;
			int s = 0;
			int eq = 0;
			// Compare the two algorithms.
			System.out.println(">>-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-<<");
			for (int k = 0; k < 3; k++){
				if (GreedyDisksAverage[k] > SortDisksAverage[k]){
					System.out.println("Algorithm Greedy-decreasing is more efficient for " + N[k] + " folders");
					s++;
				}
				else if (GreedyDisksAverage[k] < SortDisksAverage[k]){
					System.out.println("Algorithm Greedy is more efficient for " + N[k] + " folders");
					g++;
				}
				else{
					System.out.println("Both algorithms are efficient for " + N[k] + " folders");
					eq++;
				}
			}
			// How many times was each algorithm more efficient.
			System.out.println(">>-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-<<");
			
			System.out.println("Algorithm Greedy-decreasing was more efficient for " + s + " of 3 cases");
			System.out.println("Algorithm Greedy was more efficient for " + g + " of 3 cases");
			System.out.println("Both algorithms were efficient for " + eq + " of 3 cases");
		} 
		// If there is no file throw exception.
		catch (IOException ioe) {
			System.err.println("Error! File not found!");
			System.exit(1);
		}
	}
}	