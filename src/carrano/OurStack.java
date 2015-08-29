/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carrano;

import java.util.Arrays;

/**
 *
 * @author NBleier
 */
public class OurStack<T> implements StackInterface<T> {
    private int numberOfEntries;
    private T[] stack;
    private static final int INITIAL_DEFAULT_SIZE = 25;
    
    public OurStack() {
        this(INITIAL_DEFAULT_SIZE);
    } //end default constructor
    
    public OurStack(int size) {
        numberOfEntries = 0;
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[]) new Object[size];
        stack = tempStack;
    }
    
    @Override
    public void push(T newEntry) {
        stack[numberOfEntries] = newEntry;
        numberOfEntries++;
    }

    @Override
    public T pop() {
        if ( this.isEmpty() )
            return null;
        numberOfEntries--;
        T temp = stack[numberOfEntries];
        stack[numberOfEntries] = null;
        return temp;
    }

    @Override
    public T peek() {
        return stack[numberOfEntries - 1];
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
    }
    
    public T[] toArray() {
        @SuppressWarnings("checked")
        T[] arr = Arrays.copyOf(stack, numberOfEntries);
        return arr;
    }
    
    protected int getSize() {
        return this.numberOfEntries;
    }
       
}
