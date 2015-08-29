/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carrano;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author NBleier
 */
public class MyUtils {
    
    static boolean isPalindrome(String input) {
        String s = input.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String test = "";
        OurStack<Character> in = new OurStack<>(s.length());
        
        
        for ( char x : s.toCharArray() )
            in.push(x);
        
        for ( int i = 0; i < s.length(); i++ ) {
            test += in.pop();
        }
        
        return s.equals(test);
    }
    
    public static <T extends Comparable<? super T>> OurStack<T> stackSort(OurStack<T> orig) {
        OurStack<T> s = new OurStack<>(orig.getSize());
        OurStack<T> ex = new OurStack<>(orig.getSize());
        boolean isSorted = false;
        boolean allCon;
        int twoCon = -1;
        int oneCon = -1;
        int small;
        int big;
        
        if (orig.peek() == null) return orig;
        
        while ( !isSorted ) {
            allCon = allContain(orig, s, ex);
            if ( !allCon ) {
                twoCon = twoContain(orig, s, ex);
                if ( twoCon == -1 ) oneCon = oneContain(orig, s, ex); 
            } //end if
            
            if ( oneCon > 0 ) {
                if ( oneCon == 1 )
                    s.push(orig.pop());
                else if ( oneCon == 2 )
                    isSorted = true;
                else
                    s.push(ex.pop());
            } //end if oneCon > 0
            
            else if ( twoCon > 0 ) {
                if ( twoCon == 1 ) {
                    if ( s.peek().compareTo(ex.peek()) <= 0 )
                        ex.push(s.pop());
                    else
                        s.push(ex.pop());
                } //end if twoCon == 1
                else if ( twoCon == 2 ) {
                    if ( orig.peek().compareTo(ex.peek()) > 0 ) 
                        s.push(orig.pop());
                    else
                        s.push(ex.pop());
                } //end if twoCon == 3
                else {
                    if ( s.peek().compareTo(orig.peek()) >= 0 ) 
                        s.push(orig.pop());
                    else
                        ex.push(s.pop());
                }
            } //end else if twoCon > 0
            else if ( allCon ) {
                if ( s.peek().compareTo(orig.peek()) >= 0 && s.peek().compareTo(ex.peek()) >= 0 ) //if s is biggest
                    if ( ex.peek().compareTo(orig.peek()) > 0 ) //s biggest and orig smallest
                        s.push(ex.pop());
                    else
                        s.push(orig.pop());
                
                else if ( orig.peek().compareTo(s.peek()) > 0 && orig.peek().compareTo(ex.peek()) > 0 ) //if orig is biggest
                    if ( s.peek().compareTo(ex.peek()) >= 0 ) //if orig is biggest, and ex is smallest
                        ex.push(s.pop());
                    else
                        s.push(ex.pop());
                
                else // if ex is biggest
                    if ( s.peek().compareTo(orig.peek()) > 0 ) // if ex is biggest, and orig is smallest
                        System.out.println("Condition hit");
                    else
                        orig.push(s.pop());
                        
                } //end else if allCon
            twoCon = -1;
            oneCon = -1;
       
            } //end while;
        return s;
    } //end stackSort
    
    
    /**
     * Return 1 if orig is not empty.  2 if s is not empty.  3 if ex is not empty.
     * @param orig
     * @param s
     * @param ex
     * @return 
     */
    private static int oneContain(OurStack orig, OurStack s, OurStack ex) {
        if ( !orig.isEmpty() ) return 1;
        if ( !s.isEmpty() ) return 2;
        
        return 3;
    }
    
    /**
     * Returns 1 if orig is empty and s, ex are not.  2 if s is empty, and orig, ex are not.  3 if ex is empty and orig, s are not.
     * returns -1 if at least two stacks are empty
     * @param orig
     * @param s
     * @param ex
     * @return 
     */
    private static int twoContain(OurStack orig, OurStack s, OurStack ex) {
        if ( orig.isEmpty() && !s.isEmpty() && !ex.isEmpty() )
            return 1;
        if ( !orig.isEmpty() && s.isEmpty() && !ex.isEmpty() )
            return 2;
        if ( !orig.isEmpty() && !s.isEmpty() && ex.isEmpty() )
            return 3;
        
        return -1;
    }
    
    private static boolean allContain(OurStack s1, OurStack s2, OurStack s3) {
        return !s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty();
    }
    
    private static boolean twoCon(OurStack s1, OurStack s2) {
        return !s1.isEmpty() && !s2.isEmpty();
    }
        
    
    public static void countUp(int n) {
        if ( n >= 1 ) {
            countUp(n-1);
            System.out.println(n);
        }
    }
    
    
    
    
    /**
     * This method does a selection sort of an array of any comparable type.  Note that
     * primitive type arrays will not work.  The selection sort is O(n^2) in time
     * and O(1) in space.
     * @param <T> a comparable class
     * @param arr an array of T
     * @param n the number of items in the array to sort
     */
    public static <T extends Comparable<? super T>> void arraySelectionSort(T[] arr, int n) {
        T smallest;
        int smallestIndex = 0;
        
        for ( int i = 0; i < n - 1; i++ ) {
            smallest = arr[i];
            for ( int j = i; j < n; j++ ) {
                if ( arr[j].compareTo(smallest) < 0 ) {
                    smallest = arr[j];
                    smallestIndex = j;
                }
            }
            arr[smallestIndex] = arr[i];
            arr[i] = smallest;
        }
        
    }
    
    
     /**
     * This method does a selection sort of an array of any comparable type.  Note that
     * primitive type arrays will not work.  The selection sort is O(n^2) in time
     * and O(1) in space.
     * @param <T> a comparable class
     * @param arr an array of T
     */
    public static <T extends Comparable<? super T>> void arraySelectionSort(T[] arr) {
        arraySelectionSort(arr, arr.length);
    }
    
    public static <T extends Comparable<? super T>> void arrayInsertionSort(T[] arr, int n) {
        int index;
        
        for ( int i = 1; i < n; i++ ) {
            index = i;
            while ( index >= 1 && arr[index].compareTo(arr[index - 1]) < 0 ) {
                swap(arr, index, index -1);
                index--;
            } //end while loop
        } //end for loop
        
    }
    
    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }//end swap
    
    public static <T extends Comparable<? super T>> void arrayShellSort(T[] arr, int start, int end) {
        double dGap = (5.0/11.0) * (end-start);
        for ( int gap = (int) dGap; gap > 1; gap = (int) (gap * (5.0/11.0)) ) { // gap iterator: decreases size of gap each iteration
            if ( gap % 2 == 0 )
                gap++;
            for ( int i = start; i + gap <= end; i++ ) { // individual index iterator: iterates along array by one
                for ( int j = i; j + gap <= end; j += gap ) { // value iterator: iterates along array by gap, allowing swaps to be made
                    if ( arr[j].compareTo(arr[j+gap]) > 0 )
                        swap(arr, j, j+gap);
                } //end value iterator
            } //end individual index iterator
        } // end gap iterator
    } //end arrayShellSort
    
    
    public static Integer[] generateIntArray(int size) {
        Date seed = new Date();
        Random generator = new Random(seed.getTime());
        Integer[] arr = new Integer[size];
        
        for ( int i = 0; i < size; i++ ) {
            arr[i] = generator.nextInt(size);
        }
        return arr;
    }
    

    private static <T extends Comparable<? super T>> void incrementalInsertion(T[] arr, int first, int last, int space) {
        int unsorted, index;
        
        for ( unsorted = first + space; unsorted <= last; unsorted += space ) {
            T nextToInsert = arr[unsorted];
            
            for ( index = unsorted - space; (index >= first) && 
                                        (nextToInsert.compareTo(arr[index]) < 0);
                                        index -= space) {
                arr[index + space] = arr[index];
            } //end for
            
            arr[index + space] = nextToInsert;
        }
    }
    
    public static <T extends Comparable<? super T>> void shellSort(T[] arr, int first, int last) {
        int n = last - first + 1; // number of array entries
        
        for ( int space = n/2; space > 0; space /= 2) {
            for ( int begin = first; begin < first + space; begin++ )
                incrementalInsertion(arr, begin, last, space);
        }
        
    }
    
    public static  <T extends Comparable<? super T>> void mergeSort(T[] arr, int first, int last) {
        @SuppressWarnings("unchecked")
        T[] tempArray = (T[]) new Comparable<?>[arr.length];
        mergeSort(arr, tempArray, first, last);
    }
    
    private static  <T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tempArray, int begin, int end) {
        int mid;
        if (begin < end) {
            mid = (begin + end) / 2;
            mergeSort(arr, tempArray, begin, mid);
            mergeSort(arr, tempArray, mid+1, end);
            merge(arr, tempArray, begin, mid, end);
        }
    }
    
    private static <T extends Comparable<? super T>> void merge(T[] arr, T[] tempArray, int begin, int mid, int end) {
        int beginHalf1 = begin;
        int endHalf1 = mid;
        int beginHalf2 = mid+1;
        int endHalf2 = end;
        int index = 0;
        
        while ( ( beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2) ) {
            if ( arr[beginHalf1].compareTo(arr[beginHalf2]) <= 0 ) {
                tempArray[index] = arr[beginHalf1];
                beginHalf1++;
            }
            else {
                tempArray[index] = arr[beginHalf2];
                beginHalf2++;
            }
            index++;
        }
        
        if ( beginHalf1 > endHalf1 ) {
            while ( beginHalf2 <= endHalf2 ) {
                tempArray[index] = arr[beginHalf2];
                beginHalf2++;
                index++;
            }
        }
        else {
            while ( beginHalf1 <= endHalf1 ) {
                tempArray[index] = arr[beginHalf1];
                beginHalf1++;
                index++;
            }
        }
        
        for ( int j = 0; j < index; j++ ) {
            arr[begin + j] = tempArray[j];
        }
        
    }
    
    public static double sharedBirthdays(int n) {
        double start = 1;
        for ( int i = 365 - n+1; i<365; i++ ) {
            start *= (double) i /365.0;
        }
        return 1-start;
    }
}
