/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carrano;

import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author NBleier
 */
public class ResizeableArrayBag<T> implements BagInterface<T> {
    
    private int numOfEntries;
    private static final int INITIAL_DEFAULT_SIZE = 25;
    private T[] bag;
    
    /**
     * Creates an empty bag whose initial capacity is 25
     */
    public ResizeableArrayBag () {
        this(INITIAL_DEFAULT_SIZE);
    } //end default constructor
    
    /**
     * Creates an empty bag whose initial capacity is maxSize
     * @param maxSize initial capacity of bag
     */
    public ResizeableArrayBag(int maxSize) {
        numOfEntries = 0;
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[]) new Object[maxSize];
        bag = tempBag;
    } //end constructor
    
    public ResizeableArrayBag(T[] arr) {
        numOfEntries = objectsInArray(arr);
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[]) new Object[numOfEntries];
        bag = tempBag;
        
        int i = 0;
        for ( T x : arr ) {
            if (x != null) {
                bag[i] = x;
                i++;
            } //end if
        } //end for
    }

    @Override
    public int getCurrentSize() {
        return numOfEntries;
    } //end getCurrentSize

    @Override
    public boolean isFull() {
        return false;
    } //end isFull

    @Override
    public boolean isEmpty() {
        return numOfEntries == 0;
    } //end isEmpty

    @Override
    public boolean add(T entry) {
        
        this.ensureCapacity();
        bag[numOfEntries] = entry;
        numOfEntries++;
        
        return true;
    } //end add

    @Override
    public T remove() {
        if (this.isEmpty()) {return null;} //end if
        T temp = this.remove(numOfEntries-1);
        return temp;
    } //end remove

    @Override
    public T remove(T anEntry) {        
        T result = null;
        T temp = bag[numOfEntries-1];
        int index = this.getIndexOf(anEntry);
        
        if (index == numOfEntries - 1) { //if anEntry is the last object in the bag array
            remove();
            return temp;
        } //end if
        
        if (index == -1) {
            return null;
        }
        
        result = this.remove(index);
        bag[index] = temp;
        numOfEntries++; //Because the 'null' is not at the end of the array
        this.remove();
        
        if (this.isTooBig()) this.reduceArray();

        return result;
    } //end remove
    
    /**
     * Removes each instance of a specific object from the bag
     * @param entry object whose instances are removed
     * @return number of instances removed
     */
    public int removeEvery(T entry) {
        int counter = 0;
        int limit = this.getFrequencyOf(entry);
        
        for (int i = 0; i < limit; i++)
            this.remove(entry);
        
        return limit;
    }
    
    /**
     * removes item from bag array at specified index
     * @param index array index whose member is replaced with null
     * @return removed member at index.  if no such index exists, returns null
     */
    private T remove(int index) {
        assert index < this.numOfEntries && index >= 0;
        if (index >= this.numOfEntries || index < 0) {
            return null;
        } //end if
        T temp;
        if ( index == this.numOfEntries - 1 ) {
            this.numOfEntries--;
            return bag[index];
        }
        
        temp = bag[index];
        bag[index] = bag[numOfEntries-1];
        this.numOfEntries--;
        if (this.isTooBig()) this.reduceArray();
        return temp;
    }
    
    /**
     * Finds and replaces a current member of the bag with a replacement member
     * @param currentEntry the member of the bag to be replaced
     * @param replacingEntry the object that will replace the current member
     * @return true if operation successful, false if not
     */
    public boolean replace(T currentEntry, T replacingEntry) {
        int index = this.getIndex(currentEntry);
        
        if ( index == -1 ) return false; //currentEntry is not in bag
        
        this.remove(index);
        
        this.add(replacingEntry);
        
        return true;
    }
    
    /**
     * Replaces all instances of a member with a different object
     * @param currentEntry member to be replaced
     * @param replacingEntry member which replaces 
     * @return number of members replaced
     */
    public int replaceAll(T currentEntry, T replacingEntry) {
        int limit = this.getFrequencyOf(currentEntry);
        int counter = 0;
        boolean check = true;
        for ( int i = 0; i < limit && check; i++ ) {
            check = this.replace(currentEntry, replacingEntry);
            if (check) counter++;
        }
        return counter;
    }

    @Override
    public void clear() {
        int check = this.numOfEntries;
        for (int i = 0; i < check; i++) {
            bag[i] = null;
            this.numOfEntries--;
        } //end for
        
        if (!this.isEmpty()) { System.out.println("Bag is not empty despite clearing");} //end if
    } //end clear

    @Override
    public int getFrequencyOf(T anEntry) {
        int counter = 0;
        for (int i = 0; i < numOfEntries; i++) {
            if (anEntry.equals(bag[i])) { counter++;}
        } //end for
        return counter;
    } //end getFrequencyOf

    @Override
    public boolean contains(T anObject) {
        
        for (int i = 0; i < numOfEntries; i++) {
            if (anObject.equals(bag[i])) { return true;}
        } //end for
        
        return false;
    } //end contains
    
    /**
     * Gets the index of an object in T[] bag.  Used in replace(T, T) public method
     * @param anObject an object whose index in T[] bag is returned
     * @return index of anObject in T[] bag.  -1 if anObject is not in T[] bag
     */
    private int getIndex(T anObject) {

        for (int i = 0; i < numOfEntries; i++ ) {
            if (anObject.equals(bag[i])) return i;
        }
        return -1;
        
    } //end getIndex

    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] res = (T[]) new Object[this.numOfEntries];
        System.arraycopy(bag, 0, res, 0, numOfEntries);
        return res;
    } //end toArray

    /**
     * This method reorders all the elements in the array, so that any 'null' members are at the end 
     * i.e. if the array size is 25, and it has 24 members, then the only 'null' member is bag[24]
     * @param i index of new 'null' member
     
    private void resizeBag(int i) {
        for (int j = i; j < numOfEntries && (j + 1) < (numOfEntries); j++) {
            bag[j] = bag[j+1];
        } //end for
        bag[numOfEntries - 1] = null;
    } //end resizeBag
    * */
    
    @Override
    public String toString() {
        
        if (this.numOfEntries == 0) {
            return "";
        } //end if
        else {
        String s = "";
        for (int i = 0; i < numOfEntries-1; i++) {
            s += bag[i].toString() + ", ";
        } //end for
        s += bag[numOfEntries - 1];
        return s;
        } //end else
    } //end toString
    
    /**
     * Gets the index of a specific object in the bag array
     * @param entry object whose index is retrieved
     * @return index of object if found, else -1 if object is not found
     */
    private int getIndexOf(T entry) {
        for (int i = this.numOfEntries-1; i >=0; i--) {
            if (entry.equals(bag[i])) {
                return i;
            } //end if
        } //end for
        assert false;
        return -1;
    } //end getIndexOf

    /**
     * Ensures bag never runs out of room by doubling the size of the array 'bag' if the array 'bag' is full
     */
    private void ensureCapacity() {
        if (bag.length == numOfEntries)
            bag = Arrays.copyOf(bag, numOfEntries * 2);
    }
    
    /**
     * Counts the number of members in an array.  Used to initialize 'bag' array when T[] constructor is invoked
     * @param arr T[] passed to constructor
     * @return number of non-null members in the argument
     */
    private int objectsInArray(T[] arr) {
        int counter = 0;
        for (T x : arr ) {
            if ( x != null ) 
                counter++;
        } //end for
        return counter;
    } //end objectsInArray
    
    /**
     * gets the smallest natural ordered item in bag
     * @return the smallest natural ordered item in bag
     */
    public T getMin() {
        if (numOfEntries == 0)
            return null;
        
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[]) new Object[this.numOfEntries];
        tempBag = Arrays.copyOf(bag, numOfEntries);
        Arrays.sort(tempBag);
        return tempBag[0];
    }
    
    /**
     * gets the largest natural ordered item in bag
     * @return the largest natural ordered item in bag
     */
    public T getMax() {
        if (numOfEntries == 0)
            return null;
        
        @SuppressWarnings("unchcked")
        T[] tempBag = (T[]) new Object[this.numOfEntries];
        tempBag = Arrays.copyOf(bag, numOfEntries);
        Arrays.sort(tempBag);
        return tempBag[numOfEntries - 1];
    }
    
    /**
     * removes the smallest natural ordered item in bag
     * @return the smallest natural ordered item in bag
     */
    public T removeMin() {
        return remove(this.getMin());
    }
    
    /**
     * removes the largest natural ordered item in bag
     * @return the largest natural ordered item in bag
     */
    public T removeMax() {
        return remove(this.getMax());
    }
    
    /*
    public ResizeableArrayBag<T> getAllLessThan(T anObject) {
        
    }
    */
    
    /*
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof ResizeableArrayBag)) return false;
        
        ResizeableArrayBag checkBag = (ResizeableArrayBag) o;
        
        
        return true;
        
    }
    */
    
    
    /**
     * determines if two ResizableArrayBags are equivalent.  Equivalent objects 
     * contain the same cardinalities of each object, i.e. 2 A's, 1 B, and 3 C's
     * @param o Object that will be tested for equivalence
     * @return true if equivalent false if not
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ResizeableArrayBag)) return false;
        
        ResizeableArrayBag checkBag = (ResizeableArrayBag) o;
        
        T[] arr1 = this.toArray();
        
        Object[] arr2 = checkBag.toArray();
        
        if ( arr1.length != arr2.length ) return false;
        
        Arrays.sort(arr1);
        
        Arrays.sort(arr2);
        
        int limit = arr1.length;
        
        for (int i = 0; i < limit; i ++) {
            if (!(arr1[i].equals(arr2[i]))) return false;
        } //end for
        
        return true;
    } //end equals
    
    private ArrayList<OrderedPair<T, Integer>> toOrderedPairs(ResizeableArrayBag<T> o) {
        ArrayList<OrderedPair<T, Integer>> arr = new ArrayList<>();
        int index;
        int adder;
        int limit = o.getCurrentSize();
        T x;
        
        while ( !(o.isEmpty()) ) {
            x = o.remove();
            index = findLeft(x, arr); 
            if (index >= 0) {
                adder = arr.get(index).getRight();
                arr.set(index, new OrderedPair<T, Integer>(x, adder+1));
            }
            else {
                arr.add(new OrderedPair<>(x, 1));
            }
        } //end while
        
        return arr;
    }

    /**
     * Gets the index of an ordered pair containing a specific instance of Generic Class T
     * @param x instance of Generic Class T to be found
     * @param arr ArrayList of OrderedPair<T, Integer> that will be searched for x
     * @return index if found, else -1
     */
    private int findLeft(T x, ArrayList<OrderedPair<T, Integer>> arr) {
        int index = -1;
        
        for ( int i = 0; i < arr.size(); i++) {
            if ( x.equals(arr.get(i).getLeft()))
                return i;
        }
        
        return -1;
    }
    
    private boolean isTooBig() {
        int l = bag.length;
        return l > 20 && this.numOfEntries * 2 < l;
    }
    
    /**
     * redces size of array bag to 3/4 its previous size
     */
    private void reduceArray() {
        int l = bag.length;
        bag = Arrays.copyOf(bag, (int) (l * .75 ));
    }
    
    
    /**
     * Creates a union of two ResizeableArrayBags.  Cardinality of the union is
     * the sum of the cardinalities of the two RABs that were joined, meaning
     * duplicate items are maintained.  I.E. if RAB 1 has 5 A's, and RAB 2 has
     * 3 A's, their union has 8 A's
     * @param bag2 RAB that will be joined
     * @return union of the two RABs
     */
    public ResizeableArrayBag<T> union(ResizeableArrayBag<T> bag2) {
        T[] arr = bag2.toArray();
        int bagLength = bag.length;
        int length = arr.length + bagLength;
        int i;
        
        @SuppressWarnings("unchecked")
        T[] output = (T[]) new Object[length];
        
        for ( i = 0; i < bagLength; i++ ) {
            output[i] = bag[i];
        }
        
        for ( i = bagLength; i < length; i++ ) {
            output[i] = arr[i - bagLength];
        }
        ResizeableArrayBag result = new ResizeableArrayBag(output);
        return result;
    }
    
    
    
    /**
     * Creates the intersection of two RABs.  Intersections can contain
     * multiple instances of the same object.  I.E. if RAB 1 has 2 A's,
     * and RAB 2 has 3 As, the intersection of RAB1 and RAB 2 will have 
     * 2 A's
     * @param input second RAB
     * @return intersection of the two RABs.
     */
    
    public ResizeableArrayBag<T> intersection(ResizeableArrayBag<T> input) {
        ResizeableArrayBag<T> aBag = this.clone();
        ResizeableArrayBag<T> bBag = input.clone();
        ResizeableArrayBag<T> result = new ResizeableArrayBag<>();

        
        T test;
        int inABag;
        int inBBag;
        int smallestInBag;


            while (!(aBag.isEmpty())) {
               test = aBag.remove();
               aBag.add(test);
               inABag = aBag.getFrequencyOf(test);
               inBBag = bBag.getFrequencyOf(test);
               smallestInBag = Math.min(inABag, inBBag);
               
               for ( int i = 0; i < smallestInBag; i++ ) {
                   result.add(test);
               } //end for
               
               aBag.removeEvery(test);
               
            } //end while
            
            
        return result;
        
    }
    
    /**
     * Creates the difference of two RABs.
     * @param subtrahend RAB that is "subtracted"
     * @return 
     */
    public ResizeableArrayBag<T> difference(ResizeableArrayBag<T> subtrahend) {
        ResizeableArrayBag<T> RAB1 = this.clone();
        ResizeableArrayBag<T> RAB2 = subtrahend.clone();
        ResizeableArrayBag<T> result = new ResizeableArrayBag<>();
        int inABag;
        int inBBag;
        int dif;
        T test;
        
        while ( !(RAB1.isEmpty()) ) {
            test = RAB1.remove();
            RAB1.add(test);
            inABag = RAB1.getFrequencyOf(test);
            inBBag = RAB2.getFrequencyOf(test);
            dif = inABag - inBBag;
            RAB1.removeEvery(test);
            RAB2.removeEvery(test);
            
            
            for ( int i = 0; i < dif; i++ ) 
                result.add(test);
        } //end while
        return result;
    }
    
    @Override
    public ResizeableArrayBag<T> clone() {
        T[] arr = bag.clone();
        ResizeableArrayBag<T> result = new ResizeableArrayBag<>(arr);
        return result;
    }
    
    public ResizeableArrayBag<T> getAllLessThan(T anObject) {
        int index;
        int limit;
        
        this.add(anObject);
        T[] arr = this.toArray();
        this.remove(anObject);
        Arrays.sort(arr);
        ResizeableArrayBag<T> result = new ResizeableArrayBag<>(arr);
        index = result.getIndex(anObject);
        limit = result.getCurrentSize() - index;
        
        for ( int i = 0; i < limit; i++ )
            result.remove();
        
        return result;
    }
    
    public ResizeableArrayBag<T> getAllGreaterThan(T anObject) {
        int index;
        
        this.add(anObject);
        T[] arr = this.toArray();
        this.remove(anObject);
        Arrays.sort(arr);
        ResizeableArrayBag<T> result = new ResizeableArrayBag<>(arr);
        index = result.getIndexOf(anObject);
        
        for ( int i = 0; i <= index; i++ )
            result.remove(i);
        
        return result;
    }
    
}// end class
