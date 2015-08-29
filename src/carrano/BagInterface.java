/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carrano;



/**
 *
 * @author NB
 */
public interface BagInterface<T> {
    
    /**
     * Finds the number of objects in the bag
     * @return the integer number of entries in the bag
     */
    public int getCurrentSize();
    
    /**
     * See whether the # of objects in bag == max # of objects
     * @return true if full, else false
     */
    public boolean isFull();
    
    /**
     * See whether the # of objects in bag == 0
     * @return true if empty, else false
     */
    public boolean isEmpty();
    
    /**
     * Adds argument to bag
     * @param entry object to add to bag
     * @return true if object added, false if bag already full
     */
    public boolean add(T entry);
    
    /**
     * Removes an unspecified object from the bag, if possible
     * @return removed object, or null if no object is removed
     */
    public T remove();
    
    /**
     * Remove an instance of a single object from the bag, if possible.
     * @param anEntry object to remove
     * @return removed T if successful, else null
     */
    public T remove(T anEntry);
    
    /**
     * removes all objects from the bag;
     */
    public void clear();
    
    /**
     * Counts the number of times a specific object is in a bag
     * @param anEntry object to count
     * @return number of times object occurs in bag
     */
    public int getFrequencyOf(T anEntry);
    
    /**
     * Tests whether a specific object is in the bag
     * @param anObject the object to test
     * @return true if object is in bag, else false
     */
    public boolean contains(T anObject);
    
    /**
     * 
     * @return an array containing all objects in the bag
     */
    public T[] toArray();
}
