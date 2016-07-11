package ortona.basic.data_structure.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class RecursiveBST<T extends Comparable<T>> implements BST<T> {

	private T value;
	private BST<T> leftSubtree;
	private BST<T> rightSubtree;
	private BST<T> ancestor;

	
	public RecursiveBST(T element){
		this.value = element;
	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#add(T)
	 */
	@Override
	public boolean add(T element){
		if(element.compareTo(value)==0)
			return false;


		BST<T> subTree = this.getCorrectSubTree(element);

		if(subTree==null){
			if(element.compareTo(value)>0){
				this.rightSubtree=new RecursiveBST<T>(element);
			}
			else{
				this.leftSubtree=new RecursiveBST<T>(element);
			}
			return true;
		}
		return subTree.add(element);

	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#lookUp(T)
	 */
	@Override
	public BST<T> lookUp(T element){
		if(this.value.compareTo(element)==0)
			return this;
		BST<T> subTree = this.getCorrectSubTree(element);
		if(subTree!=null)
			return subTree.lookUp(element);
		return null;
	}

	private BST<T> getCorrectSubTree(T element){
		if(element.compareTo(this.value)>0){
			return this.rightSubtree;
		}
		return this.leftSubtree;
	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#getMinimum()
	 */
	@Override
	public BST<T> getMinimum(){
		if(this.leftSubtree==null)
			return this;
		return this.leftSubtree.getMinimum();
	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#getMaximum()
	 */
	@Override
	public BST<T> getMaximum(){
		if(this.rightSubtree==null)
			return this;
		return this.rightSubtree.getMaximum();
	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#delete(T)
	 */
	@Override
	public BST<T> delete(T element){
		BST<T> subtree = this.lookUp(element);
		if(subtree==null)
			return null;
		boolean left = (subtree.getAncestor().getLeftSubtree()!= null && subtree.getAncestor().getLeftSubtree().equals(subtree)) 
				? true : false;
		if(subtree.getLeftSubtree()==null&&subtree.getRightSubtree()==null){
			if(left)
				subtree.getAncestor().setLeftSubtree(null);
			else
				subtree.getAncestor().setRightSubtree(null);;
			subtree.setAncestor(null);
			return subtree;
		}
		if(subtree.getLeftSubtree()==null){
			if(left)
				subtree.getAncestor().setLeftSubtree(subtree.getRightSubtree());
			else
				subtree.getAncestor().setRightSubtree(subtree.getRightSubtree());
			subtree.getRightSubtree().setAncestor(subtree.getAncestor());
			subtree.setAncestor(null);
			subtree.setLeftSubtree(null);
			subtree.setRightSubtree(null);
			return subtree;
		}
		if(subtree.getRightSubtree()==null){
			if(left)
				subtree.getAncestor().setLeftSubtree(subtree.getLeftSubtree());
			else
				subtree.getAncestor().setRightSubtree(subtree.getLeftSubtree());
			subtree.getLeftSubtree().setAncestor(subtree.getAncestor());
			subtree.setAncestor(null);
			subtree.setLeftSubtree(null);
			subtree.setRightSubtree(null);
			return subtree;
		}
		BST<T> designedElement = subtree.getLeftSubtree().getMaximum();
		designedElement.setAncestor(subtree.getAncestor());
		designedElement.setRightSubtree(subtree.getRightSubtree());
		
		//set the left subtree only if the designedElement is not the left subtree of subtree
		if(! subtree.getLeftSubtree().equals(designedElement))
			designedElement.setLeftSubtree(subtree.getLeftSubtree());
		if(left)
			subtree.getAncestor().setLeftSubtree(designedElement);
		else
			subtree.getAncestor().setRightSubtree(designedElement);
		subtree.setAncestor(null);
		subtree.setLeftSubtree(null);
		subtree.setRightSubtree(null);
		return subtree;
	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#printDescendingOrder()
	 */
	@Override
	public void printDescendingOrder(){
		if(this.rightSubtree!=null)
			this.rightSubtree.printDescendingOrder();
		System.out.print(this.value+" ");
		if(this.leftSubtree!=null)
			this.leftSubtree.printDescendingOrder();
	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#printAscendingOrder()
	 */
	@Override
	public void printAscendingOrder(){
		if(this.leftSubtree!=null)
			this.leftSubtree.printAscendingOrder();
		System.out.print(this.value+" ");
		if(this.rightSubtree!=null)
			this.rightSubtree.printAscendingOrder();
	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#height()
	 */
	@Override
	public int height(){
		int heighLeft=0;
		int heightRight=0;
		if(this.leftSubtree!=null)
			heighLeft=1+this.leftSubtree.height();
		if(this.rightSubtree!=null)
			heightRight=1+this.rightSubtree.height();
		if(heighLeft>heightRight)
			return heighLeft;
		return heightRight;
	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#isBalanced()
	 */
	@Override
	public boolean isBalanced(){
		int heightRight = this.rightSubtree.height();
		int heightLEFT = this.leftSubtree.height();
		if(Math.abs(heightRight-heightLEFT)>1)
			return false;
		return this.rightSubtree.isBalanced() && this.leftSubtree.isBalanced();
	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#buildBalancedTree(T[])
	 */
	@Override
	public BST<T> buildBalancedTree(T[] array){
		if(array.length==0)
			return null;
		if(array.length==1)
			return new RecursiveBST<T>(array[0]); 

		RecursiveBST<T> balancedTree = new RecursiveBST<T>(array[array.length/2]);
		balancedTree.rightSubtree=buildBalancedTree(Arrays.copyOfRange(array, 0, array.length/2));
		balancedTree.leftSubtree=buildBalancedTree(Arrays.copyOfRange(array, (array.length/2+1), array.length));
		return balancedTree;
	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#nodesAtDepth()
	 */
	@Override
	public Map<Integer,List<BST<T>>> nodesAtDepth(){
		int depth=0;
		Map<Integer,List<BST<T>>> depth2nodes = new HashMap<Integer,List<BST<T>>>();
		List<BST<T>> currentNodes = new LinkedList<BST<T>>();
		currentNodes.add(this);
		while(currentNodes.size()>0){
			depth2nodes.put(depth, currentNodes);
			List<BST<T>> newNodes = new LinkedList<BST<T>>();
			for(BST<T> currentNode:currentNodes){
				if(currentNode.getRightSubtree()!=null)
					newNodes.add(currentNode.getRightSubtree());
				if(currentNode.getLeftSubtree()!=null)
					newNodes.add(currentNode.getLeftSubtree());
			}
			depth++;
			currentNodes=newNodes;
		}
		return depth2nodes;
	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#depth(T)
	 */
	@Override
	public int depth(T element){
		int depth=0;
		List<BST<T>> currentNodes = new LinkedList<BST<T>>();
		currentNodes.add(this);
		while(currentNodes.size()>0){
			List<BST<T>> newNodes = new LinkedList<BST<T>>();
			for(BST<T> currentNode:currentNodes){
				if(currentNode.getValue().compareTo(element)==0)
					return depth;
				if(currentNode.getRightSubtree()!=null)
					newNodes.add(currentNode.getRightSubtree());
				if(currentNode.getLeftSubtree()!=null)
					newNodes.add(currentNode.getLeftSubtree());
			}
			currentNodes=newNodes;
			depth++;
		}
		return -1;

	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#isBinarySearchTree()
	 */
	@Override
	public boolean isBinarySearchTree(){

		//get initial min and max
		T min = this.getMinimum().getValue();
		T max = this.getMaximum().getValue();

		return this.isBinarySearchTree(min, max);
	}

	
	private boolean isBinarySearchTree(T min, T max){
		//check the value is included in min and max
		if(!(this.value.compareTo(min)>=0) || !(this.value.compareTo(max)<=0))
			return false;

		boolean leftBinaryTree = this.leftSubtree!=null ? ((RecursiveBST<T>)this.leftSubtree).isBinarySearchTree(min,this.value) : true;
		boolean rightBinaryTree = this.rightSubtree!=null ? ((RecursiveBST<T>)this.rightSubtree).isBinarySearchTree(this.value,max) : true;
		return leftBinaryTree && rightBinaryTree;

	}

	//we need a generic structure to increment the index globally
	private class Index{
		int index=0;
	}

	public RecursiveBST(List<T> elements, OrderVisit order){
		if(elements==null||elements.size()==0){
			//should throw an exception
			return;
		}

		//get max and min from the list
		T min = elements.get(0);
		T max = elements.get(0);

		if(order.equals(OrderVisit.PRE_ORDER) || order.equals(OrderVisit.POST_ORDER)){
			for(int i=1;i<elements.size();i++){
				if(elements.get(i).compareTo(min)<=0)
					min = elements.get(i);
				if(elements.get(i).compareTo(max)>=0)
					max = elements.get(i);
			}
		}
		BST<T> tree = null;

		switch(order) {
		case PRE_ORDER : 
			tree = buildFromPreOrder(elements,min,max, new Index());
			break;

		case POST_ORDER : 
			Index index = new Index();
			index.index = elements.size()-1;
			tree = buildFromPostOrder(elements,min,max,index);
			break;

		case IN_ORDER :
			tree = buildFromInOrder(elements,0, elements.size());
			break;
		
		default :
			//throw an exception

		}


		this.value = tree.getValue();
		this.leftSubtree = tree.getLeftSubtree();
		this.rightSubtree = tree.getRightSubtree();
		
	}

	/**
	 * There is not a single binary tree given a in order traversal!!!
	 * You can consider as a root all elements from 0 to the last element, and all elements to the left 
	 * are its left subtree, all elements to the right are its right subtree.
	 * We build the balanced one: the root is the middle element
	 * @param inOrderElement
	 * @param min
	 * @param max
	 * @param index
	 * @return
	 */
	private BST<T> buildFromInOrder(List<T> inOrderElement, int start, int end){

		if(end<=start)
			return null;
		if(start==end-1)
			return new RecursiveBST<T>(inOrderElement.get(start));

		int middleElement = (end-start)/2;
		RecursiveBST<T> root = new RecursiveBST<T>(inOrderElement.get(start+middleElement));
		root.leftSubtree = buildFromInOrder(inOrderElement, start, start+middleElement);
		root.rightSubtree = buildFromInOrder(inOrderElement, start+middleElement+1, end);

		return root;
	}

	private BST<T> buildFromPreOrder(List<T> preOrderElement, T min, T max, Index index){

		//get the element
		if(index.index>=preOrderElement.size())
			return null;

		T currentElement = preOrderElement.get(index.index);
		//if the element is not in the range, just continue;
		if(!(currentElement.compareTo(min)>=0) || !(currentElement.compareTo(max)<=0))
			return null;

		//it is a good node, initialise and keep analysing the rest of the list
		RecursiveBST<T> node = new RecursiveBST<T>(currentElement);
		index.index++;
		node.leftSubtree = buildFromPreOrder(preOrderElement,min, currentElement, index);
		node.rightSubtree = buildFromPreOrder(preOrderElement, currentElement, max, index);
		return node;
	}

	private BST<T> buildFromPostOrder(List<T> postOrderElement, T min, T max, Index index){

		//get the element
		if(index.index>=postOrderElement.size())
			return null;

		T currentElement = postOrderElement.get(index.index);
		//if the element is not in the range, just continue;
		if(!(currentElement.compareTo(min)>=0) || !(currentElement.compareTo(max)<=0))
			return null;

		//it is a good node, initialise and keep analysing the rest of the list
		RecursiveBST<T> node = new RecursiveBST<T>(currentElement);
		index.index--;
		node.leftSubtree = buildFromPreOrder(postOrderElement,min, currentElement, index);
		node.rightSubtree = buildFromPreOrder(postOrderElement, currentElement, max, index);
		return node;
	}


	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#findInOrderSuccesor(T)
	 */
	@Override
	public BST<T> findInOrderSuccesor(T element){
		BST<T> targetNode = this.lookUp(element);
		if(targetNode==null)
			return null;
		if(targetNode.getAncestor()==null){
			if(targetNode.getRightSubtree()==null)
				return null;
			return targetNode.getRightSubtree().getMinimum();
		}
		boolean comesFromLeft = targetNode.getAncestor().getRightSubtree()!=null
				&&targetNode.getAncestor().getRightSubtree().equals(targetNode);
		if(comesFromLeft){
			if(targetNode.getRightSubtree()==null)
				return null;
			return targetNode.getRightSubtree().getMinimum();
		}
		return targetNode.getAncestor().getMinimum();
	}


	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#findCommonAncestor(T, T)
	 */
	@Override
	public BST<T> findCommonAncestor(T elemenetOne, T elementTwo){
		BST<T> firstTree = this.lookUp(elemenetOne);
		if(firstTree==null)
			return null;
		BST<T> secondTree = this.lookUp(elementTwo);
		if(secondTree==null)
			return null;
		BST<T> smallerDepthTree = firstTree;
		BST<T> greaterDepthTree = secondTree;
		int smallerDepth = this.depth(firstTree.getValue());
		int greaterDepth = this.depth(secondTree.getValue());
		if(smallerDepth>greaterDepth){
			smallerDepthTree=secondTree;
			greaterDepthTree=firstTree;
			int temporal=smallerDepth;
			smallerDepth=greaterDepth;
			greaterDepth=temporal;
		}

		BST<T> greaterAncestorTree;
		BST<T> smallerAncestorTree;
		for(int i=0;i<=greaterDepth;i++){
			greaterAncestorTree=greaterDepthTree;
			for(int j=0;j<i+(greaterDepth-smallerDepth);j++){
				greaterAncestorTree=greaterAncestorTree.getAncestor();
			}
			smallerAncestorTree=smallerDepthTree;
			for(int j=0;j<i;j++)
				smallerAncestorTree=smallerAncestorTree.getAncestor();
			if(greaterAncestorTree.getValue().compareTo(smallerAncestorTree.getValue())==0)
				return greaterAncestorTree;
		}
		return null;

	}

	/* (non-Javadoc)
	 * @see ortona.data_structure.tree.BST#toString()
	 */
	@Override
	public String toString(){
		return this.value.toString();
	}

	@Override
	public BST<T> getLeftSubtree() {
		return this.leftSubtree;
	}

	@Override
	public BST<T> getRightSubtree() {
		return this.rightSubtree;
	}

	@Override
	public BST<T> getAncestor() {
		return this.ancestor;
	}

	@Override
	public T getValue() {
		return this.value;
	}

	@Override
	public void setLeftSubtree(BST<T> tree) {
		this.leftSubtree = tree;
		if(tree != null)
			tree.setAncestor(this);
		
	}

	@Override
	public void setRightSubtree(BST<T> tree) {
		this.rightSubtree = tree;
		if(tree != null)
			tree.setAncestor(this);
		
	}

	@Override
	public void setValue(T value) {
		this.value = value;
		
	}

	@Override
	public void setAncestor(BST<T> tree) {
		this.ancestor = tree;
	}

}
