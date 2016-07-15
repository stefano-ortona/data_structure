package ortona.basic.data_structure.heap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Class that implements a heap through elements stored in an array
 * 
 * The method compareTo is used to compare two elements to be ordered in the heap, depending if we are implementing
 * a Max Heap or a Min Heap
 * @author ortona
 *
 * @param <T>
 */
public abstract class ArrayHeap<T extends Comparable<T>> implements Heap<T> {

	private ArrayList<T> elements;

	public ArrayHeap(){
		this.elements = Lists.newArrayList();
	}
	
	public ArrayHeap(Collection<T> elements){
		this.elements = Lists.newArrayList();
		this.elements.addAll(elements);
		
		for(int i=elements.size()-1;i>=0;i--)
			this.bubbleDown(i);
	}

	@Override
	public T getElement(T element){
		for(T oneElement : this.elements){
			if(element.compareTo(oneElement)==0)
				return oneElement;
		}
		return null;
	}

	@Override
	public boolean insert(T element){
		this.elements.add(element);
		int elementIndex = this.elements.size()-1;
		
		this.bubbleUp(elementIndex);
		
		//always return true
		return true;

	}

	private void bubbleUp(int elementIndex){
		int parentIndex = this.getParentIndex(elementIndex);

		while(elementIndex>0 && this.compareTo(this.elements.get(elementIndex), this.elements.get(parentIndex)) < 0 ){
			this.swap(elementIndex, parentIndex);
			elementIndex = parentIndex;
			parentIndex = this.getParentIndex(elementIndex);
		}

	}

	/**
	 * Get the index of the parent of the element residing at elementIndex
	 * @param elementIndex
	 * @return
	 */
	private int getParentIndex(int elementIndex){
		if(elementIndex <= 0 || elementIndex>=this.elements.size())
			return -1;

		return elementIndex%2==0 ? elementIndex/2-1 : elementIndex/2;
	}
	
	private int getLeftChildIndex(int elementIndex){
		if(elementIndex<0 || elementIndex*2+1 >= this.elements.size())
			return -1;
		
		return elementIndex*2 +1;
	}
	
	private int getRightChildIndex(int elementIndex){
		if(elementIndex<0 || elementIndex*2+2 >= this.elements.size())
			return -1;
		
		return elementIndex*2 +2;
	}
	
	/**
	 * Return the index of the smallest element between the current one and the two children
	 * @return
	 */
	private int getDominantChild(int elementIndex){
		if(elementIndex<0 || elementIndex >= this.elements.size())
			return -1;
		
		int bestIndex = elementIndex;
		
		//compare with left child
		int leftChildIndex = this.getLeftChildIndex(elementIndex);
		if(leftChildIndex!=-1){
			if(this.compareTo(this.elements.get(leftChildIndex),this.elements.get(bestIndex)) < 0)
				bestIndex = leftChildIndex;
		}
		
		//compare with right child
		int rightChildIndex = this.getRightChildIndex(elementIndex);
		if(rightChildIndex!=-1){
			if(this.compareTo(this.elements.get(rightChildIndex),this.elements.get(bestIndex)) < 0)
				bestIndex = rightChildIndex;
			
		}
		
		return bestIndex;
	}

	/**
	 * Swap elements residing at the two indexes
	 * @param fir
	 */
	private void swap(int firstIndex, int secondIndex){
		if(firstIndex<0 || firstIndex>=this.elements.size() || secondIndex<0 || secondIndex>=this.elements.size())
			return;

		T tempElem = this.elements.get(firstIndex);
		this.elements.set(firstIndex, this.elements.get(secondIndex));
		this.elements.set(secondIndex, tempElem);
	}
	
	@Override
	public T getTop(){
		return this.elements.size()>0 ? elements.get(0) : null;
	}
	
	@Override
	public T popTop(){
		if(this.elements.size()==0)
			return null;
		
		if(this.elements.size()==1){
			return this.elements.remove(0);
		}
		
		T topElement = this.elements.get(0);
		T newTopElement = this.elements.get(this.elements.size()-1);
		this.elements.set(0, newTopElement);
		this.elements.remove(this.elements.size()-1);
		
		this.bubbleDown(0);
		
		return topElement;
	}
	
	private void bubbleDown(int elementIndex){
		int dominantChild = this.getDominantChild(elementIndex);
		
		while(elementIndex != dominantChild){
			this.swap(elementIndex, dominantChild);
			elementIndex = dominantChild;
			dominantChild = this.getDominantChild(elementIndex);
		}
		
	}
	
	public List<T> getInOrderElements(){
		return Lists.newArrayList(this.elements);
	}

	/**
	 * Return an integer less than 0 if the firstElement comes before the secondElement (less in a min heap),
	 * greater than 0 if the secondElement comes first, =0 if the two elements are equal
	 * 
	 * @param firstElement
	 * @param secondElement
	 * @return
	 */
	public abstract int compareTo(T firstElement, T secondElement);

}
