package ortona.basic.data_structure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * https://en.wikipedia.org/wiki/Rope_(data_structure)
 *
 * @author Stefano Ortona <stefano.ortona@gmail.com>
 *
 */

public class Rope {
  protected TreeNode root;

  private final static int MAX_LEAF_SIZE = 3;

  /**
   * Create a Rope data structure by giving an input string as a parameter
   *
   * @param s The rope will store the given input string s
   */
  public Rope(String s) {
    final char[] chars = s.toCharArray();
    StringBuilder sBuilder = new StringBuilder();
    final List<Rope> allLeafes = new ArrayList<>();
    for (int i = 0; i < chars.length; i++) {
      sBuilder.append(chars[i]);
      if ((sBuilder.length() == MAX_LEAF_SIZE) || (i == (chars.length - 1))) {
        allLeafes.add(new Rope(new TreeNode(sBuilder.length(), sBuilder.toString())));
        sBuilder = new StringBuilder();
      }
    }
    final Rope finalRope = concatenate(allLeafes);
    root = finalRope.root;
  }

  private Rope(TreeNode root) {
    this.root = root;
  }

  private Rope concatenate(List<Rope> allRopes) {
    if (allRopes.size() == 1) {
      return allRopes.get(0);
    }
    final List<Rope> nextRopes = new ArrayList<>((allRopes.size() / 2) + 1);
    while (allRopes.size() > 1) {
      final Rope first = allRopes.remove(0);
      final Rope second = allRopes.remove(0);
      final Rope concatenation = first.concat(second);
      nextRopes.add(concatenation);
    }
    nextRopes.addAll(allRopes);
    return concatenate(nextRopes);
  }

  /**
   * Concatenate the word represented by the current rope with the word represented by other
   *
   * @param other The word to concatenate after the current word
   * @return A new rope representing the concatenation of the current word with other word
   */
  public Rope concat(Rope other) {
    final TreeNode newRoot = new TreeNode(this.root.length + other.root.length, null);
    newRoot.left = this.root;
    newRoot.right = other.root;
    this.root = newRoot;
    return this;
  }

  /**
   * Convert the current rope into the stored string
   *
   * @return The de-serialized string
   */
  public String convert() {
    final StringBuilder sBuilder = new StringBuilder();
    preOrderTraversal(this.root, sBuilder);
    return sBuilder.toString();
  }

  /**
   * Delete characters from start index (included) to end index (excluded)
   * @param start
   * @param end
   */
  public void delete(int start, int end) {
    if ((start < 0) || (start > end)) {
      throw new StringIndexOutOfBoundsException();
    }
    if (start < end) {
      end = Math.min(end, this.root.length);
      deleteFromNode(this.root, start, end);
      // remove all nodes not carrying a string any longer
      cleanUp(this.root);
    }
  }

  private void cleanUp(TreeNode node) {
    if (node != null) {
      if ((node.left != null) && (node.left.length == 0)) {
        node.left = null;
      }
      if ((node.right != null) && (node.right.length == 0)) {
        node.right = null;
      }
      cleanUp(node.left);
      cleanUp(node.right);
    }
  }

  private void deleteFromNode(TreeNode node, int start, int end) {
    if (node.isLeaf()) {
      node.word = (end - start) == node.word.length() ? "" : node.word.substring(0, start) + node.word.substring(end);
      node.length = node.word.length();
    } else {
      node.length -= (end - start);
      final int leftLen = node.left == null ? 0 : node.left.length;
      if ((node.left != null) && (start <= node.left.length)) {
        final int leftStart = start;
        final int leftEnd = Math.min(leftLen, end);
        deleteFromNode(node.left, leftStart, leftEnd);
      }
      if ((node.right != null) && ((node.left == null) || (leftLen < end))) {
        final int rightStart = Math.max(0, start - leftLen);
        final int rightEnd = end - leftLen;
        deleteFromNode(node.right, rightStart, rightEnd);
      }
    }
  }

  /**
   * Return the character at i-th position, with a 0-index based position
   *
   * @param i The index of the character to retrieve
   * @return The character at index i, starting from 0
   * @throws StringIndexOutOfBoundsException if the index i is greater or equal than the string length, or negative
   */
  public char charAt(int i) {
    if ((i >= root.length) || (i < 0)) {
      throw new StringIndexOutOfBoundsException();
    }
    return charAt(this.root, i);
  }

  private char charAt(TreeNode current, int i) {
    if (current.isLeaf()) {
      return current.word.charAt(i);
    }
    if ((current.left != null) && (i < current.left.length)) {
      return charAt(current.left, i);
    }
    final int leftSize = current.left == null ? 0 : current.left.length;
    return charAt(current.right, i - leftSize);
  }

  private void preOrderTraversal(TreeNode current, StringBuilder builder) {
    if (current != null) {
      if (current.isLeaf()) {
        builder.append(current.word);
      } else {
        preOrderTraversal(current.left, builder);
        preOrderTraversal(current.right, builder);
      }
    }
  }

  protected class TreeNode {
    protected int length;
    protected String word;
    protected TreeNode left;
    protected TreeNode right;

    public TreeNode(int length, String word) {
      this.length = length;
      this.word = word;
    }

    public boolean isLeaf() {
      return Objects.nonNull(this.word);
    }
  }
}
