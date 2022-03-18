import java.util.Comparator;

/**
 * Priority Queue implementation based in Heap
 */
public class MaxPQ implements MaxPQInterface<Disk>{
    /**
     * Heap based implementation of PriorityQueue
     * To implement it you need to implement the following helper functions as well
     * swim, sing, swap, grow
     */

    private Disk[] heap; // the heap to store data in
    private int size; // current size of the queue

    private static final int DEFAULT_CAPACITY = 4; // default capacity
    private static final int AUTOGROW_SIZE = 4; // default auto grow

    /**
     * Queue constructor
     */
    public MaxPQ() {
        this.heap = new Disk[DEFAULT_CAPACITY + 1];
        this.size = 0;
    }

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param item, type Disk.
     */
    @Override
    public void insert(Disk item) {
        // Check available space
        if (size == heap.length - 1)
            grow();

        // Place item at the next available position
        heap[++size] = item;

        // Let the newly added item swim
        swim(size);
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of the queue
     */
    @Override
    public Disk peek() {
        // Ensure not empty
        if (size == 0)
            return null;

        // return root without removing
        return heap[1];
    }

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of the queue
     */
    @Override
    public Disk getmax() {
        // Ensure not empty
        if (size == 0)
            return null;

        // Keep a reference to the root item
        Disk root = heap[1];

        // Replace root item with the one at rightmost leaf
        heap[1] = heap[size];
        size--;

        // Dispose the rightmost leaf
        // Sink the new root element
        sink(1);

        // Return the int removed
        return root;
    }
	/**
	* Finds the Disk within the Queue that has the most free space, 
	* swaps the Disk with the most free space with the first Disk of the Queue
	* and returns the Disk with the most free space.
	*/
	public Disk FindMax(){
		int i_max = 1;
		// If there's only one Disk in the Queue return it.
		if (size == 1)
			return heap[1];
		// More than one Disks in the Queue.
		for (int i = 2; i < size; i++){
			// If heap[i_max] < heap[i]
			if (heap[i_max].compareTo(heap[i]) == -1){
				i_max = i;
			}
		}
		// Swap heap[i_max] with heap[1].
		Disk temp = heap[1];
		heap[1] = heap[i_max];
		heap[i_max] = temp;
		// Return the Disk with the most free space.
		return heap[1];
	}
	/**
	* Returns the size of the Queue.
	*/
	public int size(){
		return size;
	}
	/**
	* Prints the Queue using method PrintDisk() defined in class Disk, and method SortPQ().
	*/
	public void PrintPQ(){
		// Sort Queue.
		SortPQ();
		// Print each Disk of the Queue.
		for (int i = 1; i < size+1; i++){
			heap[i].PrintDisk();
		}
	}
	/**
	 *
	 * Method SortPQ() implements Bubble Sort 
	 *
	 */
	public void SortPQ(){
		boolean swapped = true;
		int j = 0;
		Disk tmp;
		while (swapped) {
			swapped = false; // No elements swapped
			j++;
			for (int i = 1; i < size+1 - j; i++) {
				// If heap[i] < heap[i+1] swap.
				if (heap[i].compareTo(heap[i + 1]) == -1) {
					tmp = heap[i];
					heap[i] = heap[i + 1];
					heap[i + 1] = tmp;
					swapped = true; // Elements swapped
				}
			}
		}
	}

    /**
     * Helper function to swim items to the top
     *
     * @param i the index of the item to swim
     */
    private void swim(int i) {
        // if i is root (i==1) return
        if (i == 1)
            return;

        // find parent
        int parent = i / 2;

        // compare parent with child i
        while (i != 1 && heap[i].compareTo(heap[parent]) > 0) {
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }

        // recursive function
        // if (heap[i] > heap[parent]) {
        //     swap(i, parent);
        //     swim(parent);
        // }
    }

    /**
     * Helper function to swim items to the bottom
     *
     * @param i the index of the item to sink
     */
    private void sink(int i) {
        // determine left, right child
        int left = 2 * i;
        int right = left + 1;

        // if 2*i > size, node i is a leaf return
        if (left > size)
            return;

        // while haven't reached the leafs
        while (left <= size) {
            // Determine the largest child of node i
            int max = left;
            if (right <= size) {
                if (heap[left].compareTo(heap[right]) < 0)
                    max = right;
            }
            // If the heap condition holds, stop. Else swap and go on.
            // child smaller than parent
            if (heap[i].compareTo(heap[max]) >= 0)
                return;
            else {
                swap(i, max);
                i = max;
                left = i * 2;
                right = left + 1;
            }
        }
    }

    /**
     * Helper function to swap two elements in the heap
     *
     * @param i the first element's index
     * @param j the second element's index
     */
    private void swap(int i, int j) {
        Disk tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    /**
     * Helper function to grow the size of the heap
     */
    private void grow() {
        Disk[] newHeap = new Disk[heap.length + AUTOGROW_SIZE];

        // copy array
		//(notice: in the priority queue, elements are located in the array slots with positions in [1, size])
        for (int i = 0; i <= size; i++) {
            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }
}