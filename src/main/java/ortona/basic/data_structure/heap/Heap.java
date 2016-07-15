package ortona.basic.data_structure.heap;

import java.util.List;

/**
 * Interface that implements a data structure of type heap.
 * Heap can contain the same element more than one time
 * 
 * @author ortona
 *
 * @param <T>
 */
public interface Heap<T extends Comparable<T>> {
	
	/**
	 * Get the input element from the heap
	 * 
	 * This operation cannot be done efficiently, hence it requires O(n) (n =  elements in the heap)
	 * 
	 * @param element
	 * @return
	 */
	public T getElement(T element);
	
	/**
	 * Insert in the heap the input element. 
	 * Complexity: O(logn)
	 * @param element
	 * @return	true if the element has been inserted, false if the heap already contains the element we try to insert
	 */
	public boolean insert(T element);
	
	/**
	 * Get the element at the top of the heap and remove it from the heap
	 * Complexity: O(logn)
	 * @return
	 */
	public T popTop();
	
	/**
	 * Get the element at the top of the heap
	 * Complexity: O(1)
	 * @return
	 */
	public T getTop();
	
	/**
	 * Return a list of elements in the heap visited with a classic top-to-bottom
	 * and left-to-right visit
	 * 
	 * @return
	 */
	public List<T> getInOrderElements();

}
