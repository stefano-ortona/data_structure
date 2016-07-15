package ortona.basic.data_structure.heap;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import junit.framework.Assert;

public class MinArrayHeapTest {

	private Heap<Integer> currentHeap;

	@Before
	public void bringUp(){
		currentHeap = new MinArrayHeap<Integer>();
		currentHeap.insert(1);
		currentHeap.insert(2);
		currentHeap.insert(3);
		currentHeap.insert(4);
		currentHeap.insert(5);
	}

	@Test
	public void testInsert(){
		//try to insert
		boolean result = currentHeap.insert(20);
		Assert.assertTrue(result);

		//insert an element at the end
		List<Integer> listHeap = currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 6);
		Assert.assertTrue(listHeap.get(5) == 20);
		Assert.assertTrue(listHeap.get(0) == 1);

		//insert an element in the middle
		result = currentHeap.insert(3);
		Assert.assertTrue(result);

		listHeap = currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 7);
		Assert.assertTrue(listHeap.get(6) == 3);
		Assert.assertTrue(listHeap.get(0) == 1);

		//insert another element in the middle
		result = currentHeap.insert(1);
		Assert.assertTrue(result);

		listHeap = currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 8);
		Assert.assertTrue(listHeap.get(7) == 4);
		Assert.assertTrue(listHeap.get(1) == 1);
		
		//insert a minimum
		result = currentHeap.insert(-3);
		Assert.assertTrue(result);

		listHeap = currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 9);
		Assert.assertTrue(listHeap.get(8) == 2);
		Assert.assertTrue(listHeap.get(0) == -3);
		Assert.assertTrue(listHeap.get(1) == 1);
		Assert.assertTrue(listHeap.get(3) == 1);
		Assert.assertTrue(listHeap.get(7) == 4);

	}
	
	@Test
	public void testGetTop(){
		int top = this.currentHeap.getTop();
		Assert.assertTrue(top == 1);
		
		this.currentHeap.insert(2);
		
		top = this.currentHeap.getTop();
		Assert.assertTrue(top == 1);
		
		this.currentHeap.insert(-2);
		
		top = this.currentHeap.getTop();
		Assert.assertTrue(top == -2);
		
		List<Integer> listHeap = this.currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 7);
	}
	
	@Test
	public void testPopTop(){
		Integer top = this.currentHeap.popTop();
		Assert.assertTrue(top == 1);
		List<Integer> listHeap = this.currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 4);
		Assert.assertTrue(listHeap.get(0) == 2);
		
		this.currentHeap.insert(-2);
		
		top = this.currentHeap.popTop();
		Assert.assertTrue(top == -2);
		listHeap = this.currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 4);
		Assert.assertTrue(listHeap.get(0) == 2);
		
		this.currentHeap.popTop(); 
		this.currentHeap.popTop();
		this.currentHeap.popTop();
		
		top = this.currentHeap.popTop();
		Assert.assertTrue(top == 5);
		listHeap = this.currentHeap.getInOrderElements();
		Assert.assertTrue(listHeap.size() == 0);
		
		top = this.currentHeap.popTop();
		Assert.assertNull(top);
	}
	
	@Test
	public void testConstructor(){
		List<Integer> elements = Lists.newLinkedList();
		elements.add(20);
		elements.add(4);
		elements.add(15);
		elements.add(12);
		elements.add(-3);
		elements.add(10);
		elements.add(11);
		elements.add(4);
		
		List<Integer> expectedList = Lists.newArrayList();
		expectedList.add(-3);
		expectedList.add(4);
		expectedList.add(10);
		expectedList.add(12);
		expectedList.add(4);
		expectedList.add(15);
		expectedList.add(11);
		expectedList.add(20);
		
		this.currentHeap = new MinArrayHeap<Integer>(elements);
		
		List<Integer> actualList = this.currentHeap.getInOrderElements();
		Assert.assertEquals(expectedList, actualList);
	}

}
