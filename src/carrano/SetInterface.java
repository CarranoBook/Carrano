/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carrano;

/**
 *
 * @author FukYu
 */
public interface SetInterface<T> {
    
    /**
     * Adds an object to the set, if possible.  Sets do not allow duplicate
     * objects.
     * @param newEntry object to add to the set
     * @return true if add successful, false if not
     */
    public boolean add(T newEntry);
    
    /**
     * Removes an object from the set, if possible
     * @param anEntry object to remove from the set
     * @return true if removed, false if not
     */
    public boolean remove(Object anEntry);
    
    /**
     * removes all objects from set, resulting in an empty set
     */
    public void clear();
    
    /**
     * Core method which identifies if an object is a member of the set
     * @param anEntry object to test set membership
     * @return true if it is a member, false if not
     */
    public boolean contains(Object anEntry);
    
    /**
     * Tests to see if set is empty set
     * @return true if empty set
     */
    public boolean isEmpty();
    
    /**
     * Finds cardinality of set (obviously limited to finite cardinalities)
     * @return cardinality of set
     */
    public int size();
    
    /**
     * Creates an array out of the set
     * @return array representing the set
     */
    public Object[] toArray();
    
}
