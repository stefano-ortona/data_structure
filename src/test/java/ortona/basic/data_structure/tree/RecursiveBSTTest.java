package ortona.basic.data_structure.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RecursiveBSTTest {

	private BST<Integer> currentTree;

	/**
	 * currentTree is initialised with the following tree:
	 * 
	 * 			4
	 * 		 2	   7
	 * 	    1 3   6 8
	 *     0     5
	 */
	@Before
	public void bringUp(){

		this.currentTree = new RecursiveBST<Integer>(4);

		BST<Integer> bTree2 = new RecursiveBST<Integer>(2);

		BST<Integer> bTree1 = new RecursiveBST<Integer>(1);
		bTree1.setLeftSubtree(new RecursiveBST<Integer>(0));

		bTree2.setLeftSubtree(bTree1);
		bTree2.setRightSubtree(new RecursiveBST<Integer>(3));

		this.currentTree.setLeftSubtree(bTree2);

		BST<Integer> bTree7 = new RecursiveBST<Integer>(7);

		BST<Integer> bTree6 = new RecursiveBST<Integer>(6);
		bTree6.setLeftSubtree(new RecursiveBST<Integer>(5));

		bTree7.setLeftSubtree(bTree6);
		bTree7.setRightSubtree(new RecursiveBST<Integer>(8));

		this.currentTree.setRightSubtree(bTree7);
	}

	@Test
	public void addTest(){
		boolean insertResult = this.currentTree.add(3);

		Assert.assertFalse(insertResult);

		insertResult = this.currentTree.add(10);

		Assert.assertTrue(insertResult);
		Assert.assertNotNull(this.currentTree.getRightSubtree().getRightSubtree().getRightSubtree());
		Assert.assertTrue(this.currentTree.getRightSubtree().getRightSubtree().getRightSubtree().getValue() == 10);
	}

	@Test
	public void lookUpTest(){
		BST<Integer> currentLookUp = this.currentTree.lookUp(1);

		Assert.assertNotNull(currentLookUp);
		Assert.assertTrue(currentLookUp.getLeftSubtree().getValue() == 0);
		Assert.assertNull(currentLookUp.getRightSubtree());

		currentLookUp = this.currentTree.lookUp(10);

		Assert.assertNull(currentLookUp);

	}

	@Test
	public void getMinimumTest(){
		Assert.assertTrue(this.currentTree.getMinimum().getValue() == 0);

		this.currentTree.setLeftSubtree(null);
		Assert.assertTrue(this.currentTree.getMinimum().getValue() == 4);
		this.currentTree.setRightSubtree(null);
		Assert.assertTrue(this.currentTree.getMinimum().getValue() == 4);

	}

	@Test
	public void getMaximumTest(){
		Assert.assertTrue(this.currentTree.getMaximum().getValue() == 8);

		this.currentTree.setRightSubtree(null);
		Assert.assertTrue(this.currentTree.getMaximum().getValue() == 4);
		this.currentTree.setLeftSubtree(null);
		Assert.assertTrue(this.currentTree.getMaximum().getValue() == 4);

	}

	@Test
	public void deleteTest(){
		BST<Integer> deletedElement = this.currentTree.delete(10);

		Assert.assertNull(deletedElement);

		//delete leaf
		deletedElement = this.currentTree.delete(5);

		Assert.assertNotNull(deletedElement);
		Assert.assertNull(this.currentTree.getRightSubtree().getLeftSubtree().getLeftSubtree());

		//delete non leaf
		deletedElement = this.currentTree.delete(7);

		Assert.assertNotNull(deletedElement);
		Assert.assertTrue(this.currentTree.getRightSubtree().getValue()==6);
		Assert.assertNull(this.currentTree.getRightSubtree().getLeftSubtree());
		Assert.assertTrue(this.currentTree.getRightSubtree().getRightSubtree().getValue()==8);
	}

	@Test
	public void isBinarySearchTreeTest(){
		boolean isBinarySearchTree = this.currentTree.isBinarySearchTree();

		Assert.assertTrue(isBinarySearchTree);

		this.currentTree.getLeftSubtree().getRightSubtree().setRightSubtree(new RecursiveBST<Integer>(9));

		isBinarySearchTree = this.currentTree.isBinarySearchTree();

		Assert.assertFalse(isBinarySearchTree);

	}

}
