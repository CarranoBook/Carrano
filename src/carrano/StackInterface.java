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
public interface StackInterface<T> {
    /**
     * Adds a new entry to the top of this stack.
     * @param neEwntry   an object to be added to the stack
     */
    public void push(T newEntry);
    
    /**
     * Removes and returns the stack's top entry
     * @return either the object at top of the stack, or null if the stack is empty
     */
    public T pop();
    
    /**
     * Retrieves the stack's top entry, but does not remove it from the stack.
     * @return either the object at the top of the stack, or null if the stack is empty
     */
    public T peek();
    
    /**
     * Detects whether the stack is empty
     * @return true if the stack is empty
     */
    public boolean isEmpty();
    
    /**
     * Removes all entries from the stack
     * @return 
     */
    public void clear();
    
    
}
