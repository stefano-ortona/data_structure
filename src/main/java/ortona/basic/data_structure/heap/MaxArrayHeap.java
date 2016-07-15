package ortona.basic.data_structure.heap;

import java.util.Collection;

public class MaxArrayHeap<T extends Comparable<T>> extends ArrayHeap<T>{
	public MaxArrayHeap(){
		super();
	}

	public MaxArrayHeap(Collection<T> elements){
		super(elements);
	}

	@Override
	public int compareTo(T firstElement, T secondElement) {
		return secondElement.compareTo(firstElement);
	}

}
