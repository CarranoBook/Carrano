/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carrano;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author FukYu
 */
public class LinkedBag<T> implements BagInterface<T> {
    
    private Node firstNode;
    private int numberOfEntries;
    
    LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    } //end constructor
    
    LinkedBag(T[] input) {
        firstNode = null;
        numberOfEntries = 0;
        int size = input.length;
        
        for ( int i = 0; i < size; i++ ) {
            if ( input[i] != null ) {
                this.add(input[i], true);
            } //end if
        } //end for
        
    } //end T[] constructor
    
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean add(T entry) { //final because called in constructor
        Node newNode = new Node(entry);
        newNode.next = firstNode;
        firstNode = newNode;
        numberOfEntries++;
        
        return true;
    }
    
    /**
     * This overload of add is invoked in the T[] constructor
     * @param entry T object to be added 
     * @param distinguish used to overload add method
     * @return true
     */
    private boolean add(T entry, boolean distinguish) {
        Node newNode = new Node(entry);
        newNode.next = firstNode;
        firstNode = newNode;
        numberOfEntries++;
        
        return true;
    }

    @Override
    public T remove() {
        if ( this.isEmpty() ) return null;
        
        
        T result = firstNode.data;
        firstNode = firstNode.next;
        numberOfEntries--;
        return result;
    }

    @Override
    public T remove(T anEntry) {
        if ( !(this.contains(anEntry)) ) return null;
        
        if ( this.firstNode.data.equals(anEntry) ) return this.remove();
        
        Node tempNode = this.firstNode;
        Node tempNode2 = tempNode;
        
        while ( !(tempNode.data.equals(anEntry)) ) {
            tempNode2 = tempNode;
            tempNode = tempNode.next;
        }
        
        tempNode2.next = tempNode.next;
        numberOfEntries--;
        return tempNode.data;
        
    }
    
    
    /**
     * Removes every instance of a member
     * @param anEntry member to removed
     * @return number of members removed
     */
    public int removeEvery(T anEntry) {
        int limit = this.getFrequencyOf(anEntry);
        for ( int i = 0; i < limit; i++ ) {
            this.remove(anEntry);
        }
        return limit;
    }

    @Override
    public void clear() {
        this.firstNode = null;
        this.numberOfEntries = 0;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        Node tempNode = this.firstNode;
        int counter = 0;
        for ( int i = 0; i < numberOfEntries; i++ ) {
            if ( tempNode.data.equals(anEntry)) counter++;
            tempNode = tempNode.next;
        }
        return counter;
    }
    
    /**
     * Finds and replaces a current member of the bag with a replacement member
     * @param currentEntry the member of the bag to be replaced
     * @param replacingEntry the object that will replace the current member
     * @return true if operation successful, false if not
     */    
    public boolean replace(T currentEntry, T replacingEntry) {
        if ( !(this.contains(currentEntry)) ) return false;
        
        Node tempNode = this.firstNode;
        
        for ( int i = 0; i < numberOfEntries; i++ ) {
            if ( tempNode.data.equals(currentEntry) ) {
                tempNode.data = replacingEntry;
                return true;
            } //end if
            tempNode = tempNode.next;
        } //end for
        return false;
    }
    
    public int replaceAll(T currentEntry, T replacingEntry) {
        int limit = this.getFrequencyOf(currentEntry);
        
        for ( int i = 0; i < limit; i++ ) {
            this.replace(currentEntry, replacingEntry);
        }
        return limit;
    }

    @Override
    public boolean contains(T anObject) {
        Node tempNode = this.firstNode;
        
        for ( int i = 0; i < numberOfEntries; i++ ) {
            if ( tempNode.data.equals(anObject) ) return true;
            
            tempNode = tempNode.next;
        }
        return false;
    }

    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) new Object[numberOfEntries];
        Node currentNode = firstNode;
        
        for ( int i = 0; i < numberOfEntries; i++ ) {
            arr[i] = currentNode.data;
            currentNode = currentNode.next; 
        }
        return arr;
    }
    
    public T getMin() {
        T[] arr = this.toArray();
        Arrays.sort(arr);
        return arr[0];
    }
    
    public T getMax() {
        T[] arr = this.toArray();
        Arrays.sort(arr);
        return arr[numberOfEntries - 1];
    }
    
    @Override
    public boolean equals(Object o) {
        if ( !(o instanceof LinkedBag) ) return false;
        
        LinkedBag checkBag = (LinkedBag) o;
        
        if ( checkBag.getCurrentSize() != this.getCurrentSize() ) return false;
        
        T[] arr1 = this.toArray();
        Object[] arr2 = checkBag.toArray();
        int limit = arr1.length;
        
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        for ( int i = 0; i < limit; i++ ) {
            if ( arr1[i] != arr2[i] ) return false;
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.firstNode);
        hash = 53 * hash + this.numberOfEntries;
        return hash;
    }
    
    public LinkedBag intersection(LinkedBag<T> bag2) {
        LinkedBag<T> aBag = this.clone();
        LinkedBag<T> bBag = bag2.clone();
        LinkedBag<T> result = new LinkedBag<>();
        
        T test;
        int smallestInBag;
        
        while ( !(aBag.isEmpty()) ) {
            test = aBag.remove();
            aBag.add(test);
            smallestInBag = Math.min(aBag.getFrequencyOf(test), bBag.getFrequencyOf(test));
            aBag.removeEvery(test);
            bBag.removeEvery(test);
            
            for ( int i = 0; i < smallestInBag; i++ ) 
                result.add(test);
            
        }
        
        return result;
    }
    
    public LinkedBag<T> difference(LinkedBag<T> bag2) {
        LinkedBag<T> aBag = this.clone();
        LinkedBag<T> bBag = bag2.clone();
        LinkedBag<T> result = new LinkedBag();
        T test;
        int inABag, inBBag, dif;
        
        
        while ( !(aBag.isEmpty()) ) {
            test = aBag.remove();
            aBag.add(test);
            inABag = aBag.getFrequencyOf(test);
            inBBag = bBag.getFrequencyOf(test);
            dif = inABag - inBBag;
            aBag.removeEvery(test);
            bBag.removeEvery(test);
            
            for ( int i = 0; i < dif; i++ ) 
                result.add(test);
            
        } //end while
        return result;
    } // end difference
    
     /**
     * Creates a union of two LinkedBags.  Cardinality of the union is
     * the sum of the cardinalities of the two LinkedBagss that were joined, meaning
     * duplicate items are maintained.  I.E. if LinkedBags 1 has 5 A's, and LinkedBags 2 has
     * 3 A's, their union has 8 A's
     * @param bag2 LinkedBags that will be joined
     * @return union of the two RABs
     */
    public LinkedBag union(LinkedBag<T> bag2) {
        T[] arr1 = this.toArray();
        T[] arr2 = bag2.toArray();
        
        
        int arr1Length = arr1.length;
        int length = arr1Length + arr2.length;
        @SuppressWarnings("unchecked")
        T[] joined = (T[]) new Object[length];
        
        for (int i = 0; i < length; i++ ) {
            if ( i < arr1Length ) 
                joined[i] = arr1[i];
            else
                joined[i] = arr2[i - arr1Length];
        }
        
        LinkedBag<T> result = new LinkedBag<>(joined);
        
        return result;
    }
    
    public LinkedBag<T> clone() {
        T[] arr = this.toArray();
        
        LinkedBag<T> result = new LinkedBag<>(arr);
        
        return result;
    }
    
    public void insertionSort() {
        
    }
    
    
    
    
    
    private class Node {
    
        private T data;
        private Node next;
    
        Node(T dataPortion) {
            this(dataPortion, null);
        } //end constructor
        
        Node(T entry, Node nextNode) {
            data = entry;
            next = nextNode;
        } //end constructor
        

    } //end Node

}
