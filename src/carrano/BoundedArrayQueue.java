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
        if ( rear == size - 1 )
            rear = 0;
        else
            rear++;
        
        count++;
        return true;
    }
    
    public boolean isFull() {
        return count == size;
    }
    
    public boolean insert(E e) {
        return this.add(e);
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
        
        if ( front == size -1 )
            front = 0;
        else
            front++;

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
    
    public String toString() {
        String out = "Contents of Queue:\n";
        int index = this.front;
        int count = 1;
        boolean proc = true;
        while (proc) {
            out += count++ + ". " + this.q[index] + "\n";
            
            index = (index + 1) % this.size;
            if ( index == this.rear )
                proc = false;
        }
        
        return out;
    }
    
    public static void main(String[] args) {
        BoundedArrayQueue<Integer> test = new BoundedArrayQueue<>(5);
        test.add(10);
        test.add(20);
        test.add(30);
        test.add(40);
        test.remove();
        test.remove();
        test.remove();
        test.add(50);
        test.add(60);
        test.add(70);
        test.add(80);
        while (!test.isEmpty())
            System.out.println(test.remove());
    }

}
