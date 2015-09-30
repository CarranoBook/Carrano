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
        OurStack<String> sStack = new OurStack<>();
        String[] rainbow = {"red", "orange", "yellow", "green", "blue", "indigo", "violet"};
        for ( int i = 0; i < rainbow.length; i++ ) 
            sStack.push(rainbow[i]);
        
        System.out.println("There are " + sStack.getSize() + " entries in the stack.\n"
                    + "Now popping from the stack:");
        
        for ( int i = 0; i < rainbow.length; i++ )
            System.out.println(sStack.pop());
        
        System.out.println("There are " + sStack.getSize() + " entries in the stack.");
        System.out.println("Stack is empty? " + sStack.isEmpty());
        
    }
}