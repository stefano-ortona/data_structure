package ortona.basic.data_structure.tree;

import java.util.List;
import java.util.Map;

/**
 * Interface that represents a Binary Search Tree where each node contains values of type T
 * @author ortona
 *
 * @param <T>
 */
public interface BST<T extends Comparable<T>> {
	
	public static enum OrderVisit {PRE_ORDER, POST_ORDER, IN_ORDER};

	/**
	 * Add an element to the tree
	 * @param element element to be added
	 * @return true if the element has been successfully added
	 */
	public boolean add(T element);

	/**
	 * look up for the input
	 * @param element in input
	 * @return the BST starting at element
	 */
	public BST<T> lookUp(T element);

	/**
	 * Get the minimum value of the BST
	 * @return	the BST starting at the minimum value
	 */
	public BST<T> getMinimum();

	/**
	 * Get the maximum value of the BST
	 * @return	the BST starting at the maximum value
	 */
	public BST<T> getMaximum();

	/**
	 * Delete the element
	 * @param element to delete
	 * @return the new BST without element
	 */
	public BST<T> delete(T element);

	/**
	 * Print values in descending order
	 */
	public void printDescendingOrder();

	/**
	 * print values in ascending order
	 */
	public void printAscendingOrder();

	/**
	 * Get the height of the tree
	 * @return height of the tree
	 */
	public int height();

	/**
	 * A binary tree is balanced if the difference in height between left and right subtree is not greater than 1
	 * @return	true if the tree is balanced
	 */
	public boolean isBalanced();

	/**
	 * Build a balanced binary tree from an array of elements
	 * @param array of elements
	 * @return	the built balanced tree
	 */
	public BST<T> buildBalancedTree(T[] array);

	/**
	 * Build a map where the key is an integer representing the depth, and value is the list of all elements residing at that depth
	 * @return	the depth2nodes map
	 */
	public Map<Integer, List<BST<T>>> nodesAtDepth();

	/**
	 * Get the depth of an element
	 * @param element
	 * @return	depth of element
	 */
	int depth(T element);

	/**
	 * Check whether the current tree is actually a binary search tree
	 * @return	true if the tree is a binary search tree
	 */
	public boolean isBinarySearchTree();
	
	/**
	 * Find the in order successor of an element
	 * @param element
	 * @return	the BST starting at the in order successor of element
	 */
	public BST<T> findInOrderSuccesor(T element);

	/**
	 * Find the common ancestor of two input elements
	 * @param elemenetOne first element
	 * @param elementTwo second element
	 * @return	the BST starting at the common ancestor
	 */
	public BST<T> findCommonAncestor(T elemenetOne, T elementTwo);

	/**
	 * Friendly readable printing of the tree
	 */
	public String toString();
	
	/**
	 * Get the left sub tree
	 * @return the left sub tree
	 */
	public BST<T> getLeftSubtree();
	
	/**
	 * Get the right sub tree
	 * @return the right sub tree
	 */
	public BST<T> getRightSubtree();
	
	/**
	 * Get the ancestor
	 * @return the ancestor
	 */
	public BST<T> getAncestor();
	
	/**
	 * Get the value residing at the root
	 * @return	the root value
	 */
	public T getValue();
	
	/**
	 * Set the left subtree of the current tree
	 * @param tree
	 */
	public void setLeftSubtree(BST<T> tree);
	
	/**
	 * Set the right subtree of the current tree
	 * @param tree
	 */
	public void setRightSubtree(BST<T> tree);
	
	/**
	 * Set the value of the root of the tree
	 * @param value
	 */
	public void setValue(T value);
	
	/**
	 * Set the ancestor of the current tree
	 * @param tree
	 */
	public void setAncestor(BST<T> tree);
	
}
