package cafe;

/**
This is an interface that defines adding and removing behaviors.
@author Dharma Wijesinghe, Min Sun You
*/
public interface Customizable {
	
	/**
	 * This method provides add functionality.
	 * @param obj
	 * @return true if the object was added, false otherwise.
	 */
    boolean add(Object obj);
    
    /**
     * This method provides remove functionality.
     * @param obj
     * @return true if the object was removed, false otherwise.
     */
    boolean remove(Object obj);
}