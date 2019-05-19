package ortona.basic.data_structure.sorting;

/**
 * Quick sort: pick a random element, put it in the right position, then put all the elements
 * smaller than the element to the right, all elements bigger of the element to the left, and
 * sort left and right
 * 
 * @author Stefano Ortona <stefano.ortona@gmail.com>
 *
 */
public class QuickSort implements Sort{
  
  public <T extends Comparable<T>> void sort(T []input){
    sort(input,0,input.length-1);
  }
  
  private <T extends Comparable<T>> void sort(T []input, int start, int end){
    if(end-start<=0){
      //no need to sort
      return;
    }
    //do not use any extra memory
    int smallerElemenCount = start;
    T pivot = input[end];
    for(int i=start;i<end;i++){
      if(input[i].compareTo(pivot)<=0){
        smallerElemenCount++;
      }
    }
    //place the pivot in the right position
    swapElements(input, smallerElemenCount, end);
    int nextSmallerElement= smallerElemenCount;
    for(int i=start;i<smallerElemenCount;i++){
      //if a number is not smaller, swap it with a smaller one
      if(input[i].compareTo(pivot)>0){
        nextSmallerElement=findNextSmallerElement(input, nextSmallerElement+1, end, pivot);
        swapElements(input, i, nextSmallerElement);
      }
    }
    //merge left and right part
    sort(input,start,smallerElemenCount-1);
    sort(input, smallerElemenCount+1,end);
  }
  
  private <T> void swapElements(T []input,int firstIndex, int secondIndex){
    T temp = input[firstIndex];
    input[firstIndex]=input[secondIndex];
    input[secondIndex]=temp;
  }
  
  private <T extends Comparable<T>> int findNextSmallerElement(T []input, int start, int end, T target){
    for(int i=start;i<=end;i++){
      if(input[i].compareTo(target)<=0){
        return i;
      }
    }
    return -1;
  }
  
  public static void main(String []args){
    QuickSort qS = new QuickSort();
    qS.sort(new Integer[]{68,51,23,30,96,87,51,9,98,61,89,98,22,60,41,38,43,69,27,33},6,19);
  }

}
