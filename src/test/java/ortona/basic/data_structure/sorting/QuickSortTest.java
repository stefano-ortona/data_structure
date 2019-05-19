package ortona.basic.data_structure.sorting;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class QuickSortTest {
  QuickSort sort = new QuickSort();
  
  @Test
  public void testEmptyArray(){
    Integer []input = new Integer[0];
    sort.sort(input);
    Assert.assertEquals(0,input.length);
  }
  
  @Test
  public void testOneElementArray(){
    Integer []input = new Integer[]{10};
    sort.sort(input);
    Assert.assertEquals(10,(int)input[0]);
  }
  
  @Test
  public void testTwoElementsArray(){
    Integer []input = new Integer[]{10,1};
    sort.sort(input);
    Assert.assertEquals(1,(int)input[0]);
    Assert.assertEquals(10,(int)input[1]);
  }
  
  @SuppressWarnings("deprecation")
  @Test
  public void testTwnetyElementsArray(){
    Integer []input = new Integer[]{68,51,23,30,96,87,51,9,98,61,89,98,22,60,41,38,43,69,27,33};
    Integer []copy=Arrays.copyOf(input, input.length);
    sort.sort(input);
    Arrays.sort(copy);
    Assert.assertEquals(copy,input);
  }
  
  @SuppressWarnings("deprecation")
  @Test
  public void testRandomArray(){
    Random r = new Random();
    Integer []input=new Integer[r.nextInt(1000)];
    for(int i=0;i<input.length;i++){
      input[i]=r.nextInt();
    }
    Integer []copy=Arrays.copyOf(input, input.length);
    sort.sort(input);
    Arrays.sort(copy);
    Assert.assertEquals(copy,input);
  }

}
