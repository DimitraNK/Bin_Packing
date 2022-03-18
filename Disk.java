// Class Disk implements Comparable.
public class Disk implements Comparable<Disk>{
	int id = 0;
	// Static variable next_id helps us determine every disk's id and is initially equal to 0 because
	// the first disk's id is 0. 
	static int next_id = 0;
	// Variable memory represents our disk's memory, which is initially equal to 1000000.
	int memory = 1000000;
	// folders is a SingleLinkedList that represents the folders we store in our disk.
	// Each element of the list is an integer that represents the folder's size.
	SingleLinkedList<Integer> folders = new SingleLinkedList<>();
	
	// Constructor assign an id to each disk we create.
	// The first disk's id is 0 and each new disk's id is equal to the previus disk's id plus 1.
	Disk(){
		id = next_id;
		next_id++; // Every time we use the constructor the value of next_id is increased by 1.
	}
	// Method Add() adds a folder to the disk.
	public void Add(int fmemory){
		folders.InsertAtBack(fmemory);
		memory = memory - fmemory;
	}
	// Method Remove() returns and removes a folder from the disk.
	public int Remove(){
		int RemovedMemory = folders.RemoveFromBack();
		memory = memory + RemovedMemory;
		return RemovedMemory;
	}
	// Returns the disk's remaining memory.
	public int getFreeSpace(){
		return memory;
	}
	// Uses SingleLinkedList's print(String message) method in order to print each disk's
	// id, free space and the size of each folder it contains.
	public void PrintDisk(){
		String message = "id "+id+" "+getFreeSpace()+": ";
		folders.print(message);
	}
	// Returns the disk's id.
	public int GetId(){
		return id;
	}
	// compareTo(Disk d) method compares our disk's free space to disk's d free space.
	// Returns integer 0, 1, or -1 depending on the comparison of our disk's memory 
	// and disk's d free space.
	public int compareTo(Disk d){
		if (memory > d.getFreeSpace())
			return 1;
		else if (memory < d.getFreeSpace())
			return -1;
		else 
			return 0;
	}
}