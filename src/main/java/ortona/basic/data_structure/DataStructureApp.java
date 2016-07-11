package ortona.basic.data_structure;

import java.util.LinkedList;
import java.util.List;

import ortona.basic.data_structure.tree.BST;
import ortona.basic.data_structure.tree.RecursiveBST;

public class DataStructureApp {

	public static void main(String[] args){
		playWithBinaryTree();
	}


	public static void playWithBinaryTree(){
		RecursiveBST<Integer> bTree3 = new RecursiveBST<Integer>(3);
		RecursiveBST<Integer> bTree2 = new RecursiveBST<Integer>(2);
		RecursiveBST<Integer> bTree1 = new RecursiveBST<Integer>(1);

		RecursiveBST<Integer> bTree5 = new RecursiveBST<Integer>(5);
		bTree3.setLeftSubtree(bTree2);
		bTree3.setRightSubtree(bTree5);
		bTree2.setLeftSubtree(bTree1);

		System.out.println(bTree3.isBinarySearchTree());

		List<Integer> inOrderVisit = new LinkedList<Integer>();
		inOrderVisit.add(0);
		inOrderVisit.add(1);
		inOrderVisit.add(2);
		inOrderVisit.add(3);
		inOrderVisit.add(4);
		inOrderVisit.add(5);
		inOrderVisit.add(6);
		inOrderVisit.add(7);
		inOrderVisit.add(8);

		RecursiveBST<Integer> bTree = new RecursiveBST<Integer>(inOrderVisit, BST.OrderVisit.IN_ORDER);
		System.out.println(bTree.getValue());
		System.out.println(bTree.getRightSubtree().getValue());
		System.out.println(bTree.getLeftSubtree().getValue());

		bTree.printAscendingOrder();
		System.out.println();
		bTree.printDescendingOrder();
	}

}
