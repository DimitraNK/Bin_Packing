// This interface is implemented in the Disk class.

public interface Comparable<Disk> {
	// compareTo(Disk d) method compares our disk's free space to disk's d free space.
	public int compareTo(Disk d);
}