/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrano;

/**
 *
 * @author nbleier
 * @param <E> generic class which implements the Comparable interface
 */
public class QuickSort<E extends Comparable>  {
    E[] arr;
    final int switchToInsertion;
    static int SWITCH_TO_INSERTION_DEFAULT = 4;
    
    public QuickSort(E[] arr) {
        switchToInsertion = SWITCH_TO_INSERTION_DEFAULT;
        @SuppressWarnings("unchecked")
        E[] tempArr = (E[]) new Comparable[arr.length];
        tempArr = arr;
        this.arr = tempArr;
        this.pivot(0, arr.length - 1);
    }
    
    public QuickSort(E[] arr, int insert) {
        switchToInsertion = insert;
        @SuppressWarnings("unchecked")
        E[] tempArr = (E[]) new Comparable[arr.length];
        tempArr = arr;
        this.arr = tempArr;
        this.pivot(0, arr.length - 1);
    }

    private E selectPivot(int begin, int end) {
        int mid = (end - begin) / 2;
        E b = arr[begin];
        E m = arr[mid];
        E e = arr[end];
        
        //Returns the value at the mid index of the array
        if ( (m.compareTo(b) > 0 && m.compareTo(e) < 0) ||
                m.compareTo(b) < 0 && m.compareTo(e) > 0 )
            return m;
        
        //Returns the value at the beginning index of the array
        if ( (b.compareTo(m) < 0 && b.compareTo(e) > 0) ||
                b.compareTo(m) > 0 && b.compareTo(e) < 0)
            return b;
        
        return e;
    }
    
    private void pivot(int begin, int end)
    {
        if ( (end - begin + 1 ) <= switchToInsertion ) {
            this.insertionSort(begin, end);
        }
        else {
            int first = begin;
            int last = end;
            E pivot = this.selectPivot(begin, end);
            E temp;
            boolean check = first != last;

            while (check) {
                //If the element in the first index is greater than the pivot
                if ( arr[first].compareTo(pivot) > 0 )
                {
                    temp = arr[last];
                    arr[last] = arr[first];
                    arr[first] = temp;
                    last--;
                }
                else {
                    first++;
                }
                check = first != last;
            }//end while

            //recursive step
            if ( first != begin && first != end) {
                this.pivot(begin, first);
                this.pivot(last, arr.length - 1);
            }
        } //end else
    }
    
    private void insertionSort(int begin, int end) {
        E temp;
        int minIndex;
        for ( int i = begin; i <= end; i++ ) {
            temp = arr[i];
            minIndex = i;
            for ( int j = i + 1; j <= end; j++ ) {
                if ( temp.compareTo(arr[j]) > 0 ) {
                    temp = arr[j];
                    minIndex = j;
                } //end if
            } //end inner loop
            if ( minIndex != i ) {
                arr[minIndex] = arr[i];
                arr[i] = temp;
            } //end assignment if
        } //end outer loop
    }
    
    
    public E[] getArr() {
        return arr;
    }
    
    public static void main(String[] args) {
        Integer[] ar = {8, 3, 5, 7, 6, 43, 1234, 1234 ,3 ,4,3};
        QuickSort test = new QuickSort(ar);
        
        @SuppressWarnings("unchecked")
        Integer[] tempArr = (Integer[]) new QuickSort(ar).getArr();
        

        for ( Integer x : tempArr ) {
            System.out.println(x);
        }
    }

    
}
