package ortona.basic.data_structure.sorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The current implementation will work only with positive numbers, as it does not check the sign
 * The implementation can be adapted to work also on negative numbers with the following:
 * - divide the initial array into two parts: first only negative numbers, second only positive numbers (O(n))
 * - sort with radix sort the first part and the second part (O(n*k) + O(n*k))
 * - invert the first part (the one with negative numbers) (O(n))
 * - concatenate first part and second part as a final result (O(n))
 * - Total runtime: O(k*n)
 *
 * @author Stefano Ortona <stefano.ortona@gmail.com>
 *
 */
public class RadixSort {

  public void sort(Integer[] input) {
    // compute biggest digit
    int biggestDigit = 0;
    for (final Integer oneN : input) {
      biggestDigit = Math.max(biggestDigit, (oneN + "").length() - 1);
    }
    sortByDigit(input, 0, input.length - 1, biggestDigit);
  }

  private void sortByDigit(Integer[] input, int start, int end, int curDigit) {
    if ((start >= end) || (curDigit < 0)) {
      return;
    }
    // associate to each digit all the numbers
    final List<List<Integer>> digit2numbers = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      digit2numbers.add(new LinkedList<Integer>());
    }
    final double minimum = Math.pow(10, curDigit);
    for (int i = start; i <= end; i++) {
      final int numberDigit = input[i] >= minimum ? getNDigit(input[i], curDigit) : 0;
      digit2numbers.get(numberDigit).add(input[i]);
    }
    // for each digit, copy the number into the original array and sort with next digit
    for (final List<Integer> oneDigitNumbers : digit2numbers) {
      for (final int oneNumber : oneDigitNumbers) {
        input[start] = oneNumber;
        start++;
      }
      // sort current digit
      sortByDigit(input, start - oneDigitNumbers.size(), start - 1, curDigit - 1);
    }
  }

  /**
   * 324 -> digit 2 is 3, digit 1 is 2, digit 0 is 4
   *
   * @param number
   * @param digit
   * @return
   */
  private int getNDigit(int number, int digit) {
    final String sNumber = number + "";
    return Integer.parseInt(sNumber.charAt(sNumber.length() - digit - 1) + "");
  }

}
