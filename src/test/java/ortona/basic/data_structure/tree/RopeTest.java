package ortona.basic.data_structure.tree;

import org.junit.Test;

import junit.framework.Assert;
import ortona.basic.data_structure.tree.Rope.TreeNode;

public class RopeTest {

  @Test
  public void testCreateRopeAndDeserialize() {
    final String input = "This is an example input string";
    final Rope rope = new Rope(input);
    Assert.assertEquals(input, rope.convert());
    Assert.assertEquals(input.length(), rope.root.length);
    // get the left most node, and check is carrying string 'Thi'
    TreeNode leftMost = rope.root;
    while (leftMost.left != null) {
      leftMost = leftMost.left;
    }
    Assert.assertEquals("Thi", leftMost.word);
    Assert.assertEquals(3, leftMost.length);
    // get the right most node, and check is carrying string 'g'
    TreeNode rightMost = rope.root;
    while (rightMost.right != null) {
      rightMost = rightMost.right;
    }
    Assert.assertEquals("g", rightMost.word);
    Assert.assertEquals(1, rightMost.length);
  }

  @Test
  public void testGetCharAt() {
    final String input = "This is an example input string";
    final Rope rope = new Rope(input);
    for (int i = 0; i < input.length(); i++) {
      Assert.assertEquals(input.charAt(i), rope.charAt(i));
    }
  }

  @Test
  public void testDelete() {
    final String input = "This is an example input string";
    final Rope rope = new Rope(input);
    rope.delete(5, 10);
    Assert.assertEquals(input.substring(0, 5) + input.substring(10), rope.convert());
  }

  @Test
  public void testConcat() {
    final String input = "This is an example input string";
    final String input2 = "This is another example input string";
    final Rope rope = new Rope(input);
    final Rope rope2 = new Rope(input2);
    Assert.assertEquals(input + input2, rope.concat(rope2).convert());
  }

}
