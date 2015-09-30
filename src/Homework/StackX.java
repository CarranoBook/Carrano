/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Homework;

import java.util.Arrays;

/**
 *
 * @author nbleier
 */

interface StackInterface<T> {
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
     */
    public void clear();
}

public class StackX<T> implements StackInterface<T>  {
    private int numberOfEntries;
    private T[] stack;
    private static final int INITIAL_DEFAULT_SIZE = 25;
    
    public StackX() {
        this(INITIAL_DEFAULT_SIZE);
    } //end default constructor
    
    public StackX(int size) {
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
        return stack[numberOfEntries];
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

class StackApp {
    public static void main(String[] args) {
        StackX<String> sStack = new StackX<>();
        String[] rainbow = {"red", "orange", "yellow", "green", "blue", "indigo", "violet"};
        for (String color : rainbow) {
            sStack.push(color);
        }
        
        System.out.println("There are " + sStack.getSize() + " entries in the stack.\n"
                    + "Now popping from the stack:");
        
        for (String color : rainbow) {
            System.out.println(sStack.pop());
        }
        
        System.out.println("There are " + sStack.getSize() + " entries in the stack.");
        System.out.println("Stack is empty? " + sStack.isEmpty());
    }
}

