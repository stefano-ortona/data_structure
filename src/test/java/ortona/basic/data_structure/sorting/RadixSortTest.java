package ortona.basic.data_structure.sorting;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class RadixSortTest {
  RadixSort sort = new RadixSort();

  @Test
  public void testEmptyArray() {
    final Integer[] input = new Integer[0];
    sort.sort(input);
    Assert.assertEquals(0, input.length);
  }

  @Test
  public void testOneElementArray() {
    final Integer[] input = new Integer[] { 10 };
    sort.sort(input);
    Assert.assertEquals(10, (int) input[0]);
  }

  @Test
  public void testTwoElementsArray() {
    final Integer[] input = new Integer[] { 10, 1 };
    sort.sort(input);
    Assert.assertEquals(1, (int) input[0]);
    Assert.assertEquals(10, (int) input[1]);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testTwnetyElementsArray() {
    final Integer[] input = new Integer[] { 68, 51, 23, 30, 96, 87, 51, 9, 98, 61, 89, 98, 22, 60, 41, 38, 43, 69, 27,
        33 };
    final Integer[] copy = Arrays.copyOf(input, input.length);
    sort.sort(input);
    Arrays.sort(copy);
    Assert.assertEquals(copy, input);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testRandomArray() {
    final Random r = new Random();
    final Integer[] input = new Integer[r.nextInt(1000)];
    for (int i = 0; i < input.length; i++) {
      input[i] = Math.abs(r.nextInt());
    }
    final Integer[] copy = Arrays.copyOf(input, input.length);
    sort.sort(input);
    Arrays.sort(copy);
    Assert.assertEquals(copy, input);
  }

}
