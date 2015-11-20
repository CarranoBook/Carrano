/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrano;

//import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

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
    
    public ArrayBinarySearcher(E[] arr, E val, boolean sorted) {       
        this.arr = arr;
        this.val = val;
        if (!sorted)
            this.bubbleSort();
        index = binarySearch(0, arr.length - 1);
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
        return linearSearch(start, end); //runs linear search once binary search has reduced size of solution set to <=4 elements
    }
    
    private int linearSearch(int start, int end) {
        for ( int i = start; i<= end; i++ )
            if ( arr[i].equals(val) )
                return i;
        
        return -1;
    }
    
    private void bubbleSort() {      
        for ( int i = 0; i < arr.length - 1; i++ ) {
            for ( int j = 0; j < arr.length - 1 - i; j++ ) {
                if ( arr[j].compareTo(arr[j+1]) > 0)
                {
                    E temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
    
    /**
     * Gets the index at which the searched for value is found
     * @return index of the searched for value
     */
    public int getIndex() { return index;}
    
    /**
     * Returns the array that this class searches
     * @return array
     */
    public E[] toArray() { return arr;}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        String[] inArr;
        String Val;
        String val;
        String reg = "\\b";
        int counter = 0;
        Stack transfer = new Stack();
        
        while (true) {
            try {
                System.out.println("Please enter a statement of at least ten words: ");
                input = in.nextLine();
                

                inArr = input.toLowerCase().replaceAll("'", "").replaceAll("\\p{Punct}", "").
                        split(reg); //creates string array of individual words

                for ( String s : inArr ) {
                    s=s.trim().replace("\\s", ""); //removes white space from each word

                    if ( s.matches("[a-z]+") ) //pushes all words into stack, ignoring non word strings
                    {
                        counter++;
                        transfer.push(s);
                    } //end if
                }//end for 
                
                if (counter>= 10) {
                    
                    System.out.println("Does your sentence contain: ");
                    Val = in.nextLine();
                    val = Val.trim().replaceAll("\\p{Punct}", "").toLowerCase();
                
                    String[] refinedArr = new String[counter];
                    while (counter>0) { //pops words from transfer stack to our refined string array
                        refinedArr[counter-1] = (String) transfer.pop();
                        counter--;
                    }//end while

                    ArrayBinarySearcher<String> check = new ArrayBinarySearcher<>(refinedArr, val, false);
                    int f= check.getIndex();
                    if ( f >= 0)
                        System.out.println(Val + " was found at index " + f);
                    else
                        System.out.println(Val + " was not found.");
                }

            } //end try
            catch (IllegalArgumentException e) {
                System.out.println(e);
            } //end catch
        }//end while
        
    }
    
}
