package ortona.basic.data_structure.heap;

import java.util.Collection;

/**
 * Class that implements a minimum array heap
 * @author ortona
 *
 * @param <T>
 */
public class MinArrayHeap<T extends Comparable<T>> extends ArrayHeap<T> {
	
	public MinArrayHeap(){
		super();
	}

	public MinArrayHeap(Collection<T> elements){
		super(elements);
	}

	@Override
	public int compareTo(T firstElement, T secondElement) {
		return firstElement.compareTo(secondElement);
	}

}
