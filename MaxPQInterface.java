public interface MaxPQInterface<Disk> {

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param item
     */
    void insert(Disk item);


    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of the queue
     */
    Disk peek();


    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of the queue
     */
    Disk getmax();
}
