/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrano;

/**
 *
 * @author nbleier
 */
public class ArrayBinarySearcher<E extends Comparable> {
    E[] arr;
    E val;
    int index;
    
    public ArrayBinarySearcher(E[] arr, E val) {
        this.arr = arr;
        this.val = val;
        index = binarySearch(0, arr.length - 1);
    }
    
    public ArrayBinarySearcher(E[] arr, E val, int start, int end) {
        this.arr = arr;
        this.val = val;
        index = binarySearch(start, end);
    }
    
    private int binarySearch(int start, int end) {
        int mid;
        while ( start + 4 <= end ) {
            mid = (start + end) / 2;
            if ( arr[mid].equals(val) ) 
                return mid;
            
            else if ( arr[mid].compareTo(val) < 0 ) // val is stored in + half of the array
            {
                start = mid + 1;
            }
            else
                end = mid - 1;
        }
        return linearSearch(start, end);
    }
    
    private int linearSearch(int start, int end) {
        for ( int i = start; i<= end; i++ )
            if ( arr[i].equals(val) )
                return i;
        
        return -1;
    }
    
    public int getIndex() { return index;}

    public static void main(String[] args) {
        String[] words = {"apple", "box", "gorilla", "hemophage", "phone", "yellow"};
        
        ArrayBinarySearcher<String> test = new ArrayBinarySearcher<>(words, "gorilla");
        System.out.println(words[test.getIndex()]);
    }
    
}
