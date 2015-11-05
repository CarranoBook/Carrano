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
    
    public QuickSort(E[] arr) {
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
                end--;
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
    }
    
    public E[] getArr() {
        return arr;
    }
    
    public static void main(String[] args) {
        Integer[] ar = {8, 3, 5, 7, 6};
        QuickSort test = new QuickSort(ar);
        @SuppressWarnings("Unchecked")
        Integer[] tempArr = (Integer[]) new Comparable[ar.length];
        ar =tempArr;
        for ( Integer x : ar ) {
            System.out.println(x);
        }
    }

    
}
