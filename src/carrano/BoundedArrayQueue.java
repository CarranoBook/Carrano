/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrano;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 *
 * @author nbleier
 */
public class BoundedArrayQueue<E> implements Queue<E> {
    private E[] q;
    private int front;
    private int rear;
    private final int size;
    private int count;
    private static final int DEFAULT_SIZE = 25;
    
    public BoundedArrayQueue() {
        this(DEFAULT_SIZE);
    }
    
    public BoundedArrayQueue(int size) {
        this.size = size;
        front = 0;
        rear = 0;
        count = 0;
        @SuppressWarnings("unchecked")
        E[] tempQ = (E[]) new Object[this.size];
        q = tempQ;
    }
    
    @Override
    public boolean add(E e) {
        if ( count == size ) 
            throw new IndexOutOfBoundsException("Queue is already full.  See notes for offer method.");
        
        q[rear] = e;
        rear = (rear + 1) % size;
        count++;
        
        return true;
    }

    @Override
    public boolean offer(E e) {
        if ( count == size )
            return false;
        
        return this.add(e);
    }

    @Override
    public E remove() {
        if ( count == 0 )
            throw new IllegalStateException("Cannot remove queue.  See notes for poll method.");
        E temp = q[front];
        q[front] = null;
        
        front = (front + 1) % size;
        count--;
        
        return temp;
    }

    @Override
    public E poll() {
        if ( count == 0 )
            return null;
        
        return this.remove();
    }

    @Override
    public E element() {
        return q[front];
    }
                
    @Override
    public E peek() {
        if ( count == 0 )
            return null;
        return q[front];
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public boolean contains(Object o) {
        E temp = (E) o;
        for ( int i = 0; i < this.size; i++ ) {
            if ( temp.equals(q[i]) )
                return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++ ) {
            q[i] = null;
        }
        
        front = 0;
        rear = 0;
        count = 0;
        
    }

}
