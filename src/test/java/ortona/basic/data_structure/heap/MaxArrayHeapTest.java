package ortona.basic.data_structure.heap;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import junit.framework.Assert;

public class MaxArrayHeapTest {
	private Heap<Integer> currentHeap;

	@Before
	public void bringUp(){
		currentHeap = new MaxArrayHeap<Integer>();
		currentHeap.insert(5);
		currentHeap.insert(4);
		currentHeap.insert(3);
		currentHeap.insert(2);
		currentHeap.insert(1);
	}

	@Test
	public void testInsert(){
		//try to insert
		boolean result = currentHeap.insert(0);
		Assert.assertTrue(result);

		//insert an element at the end
		List<Integer> listHeap = currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 6);
		Assert.assertTrue(listHeap.get(5) == 0);
		Assert.assertTrue(listHeap.get(0) == 5);

		//insert an element in the middle
		result = currentHeap.insert(3);
		Assert.assertTrue(result);

		listHeap = currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 7);
		Assert.assertTrue(listHeap.get(6) == 3);
		Assert.assertTrue(listHeap.get(0) == 5);

		//insert another element in the middle
		result = currentHeap.insert(3);
		Assert.assertTrue(result);

		listHeap = currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 8);
		Assert.assertTrue(listHeap.get(7) == 2);
		Assert.assertTrue(listHeap.get(3) == 3);
		
		//insert a minimum
		result = currentHeap.insert(8);
		Assert.assertTrue(result);

		listHeap = currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 9);
		Assert.assertTrue(listHeap.get(8) == 3);
		Assert.assertTrue(listHeap.get(0) == 8);
		Assert.assertTrue(listHeap.get(1) == 5);
		Assert.assertTrue(listHeap.get(3) == 4);
		Assert.assertTrue(listHeap.get(7) == 2);

	}
	
	@Test
	public void testGetTop(){
		int top = this.currentHeap.getTop();
		Assert.assertTrue(top == 5);
		
		this.currentHeap.insert(4);
		
		top = this.currentHeap.getTop();
		Assert.assertTrue(top == 5);
		
		this.currentHeap.insert(7);
		
		top = this.currentHeap.getTop();
		Assert.assertTrue(top == 7);
		
		List<Integer> listHeap = this.currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 7);
	}
	
	@Test
	public void testPopTop(){
		Integer top = this.currentHeap.popTop();
		Assert.assertTrue(top == 5);
		List<Integer> listHeap = this.currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 4);
		Assert.assertTrue(listHeap.get(0) == 4);
		
		this.currentHeap.insert(7);
		
		top = this.currentHeap.popTop();
		Assert.assertTrue(top == 7);
		listHeap = this.currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 4);
		Assert.assertTrue(listHeap.get(0) == 4);
		
		this.currentHeap.popTop(); 
		this.currentHeap.popTop();
		this.currentHeap.popTop();
		
		top = this.currentHeap.popTop();
		Assert.assertTrue(top == 1);
		listHeap = this.currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 0);
		
		top = this.currentHeap.popTop();
		Assert.assertNull(top);
	}
	
	@Test
	public void testConstructor(){
		List<Integer> elements = Lists.newLinkedList();
		elements.add(-6);
		elements.add(4);
		elements.add(15);
		elements.add(12);
		elements.add(3);
		elements.add(-10);
		elements.add(11);
		elements.add(4);
		
		List<Integer> expectedList = Lists.newArrayList();
		expectedList.add(15);
		expectedList.add(12);
		expectedList.add(11);
		expectedList.add(4);
		expectedList.add(3);
		expectedList.add(-10);
		expectedList.add(-6);
		expectedList.add(4);
		
		this.currentHeap = new MaxArrayHeap<Integer>(elements);
		
		List<Integer> actualList = this.currentHeap.getInOrderElements();
		Assert.assertEquals(expectedList, actualList);
	}

}
