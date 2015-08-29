/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carrano;

/**
 *
 * @author NBleier
 */
public class LinkedStack<T> implements StackInterface<T> {
    private Node topNode;
    
    public LinkedStack() {
        topNode = null;
    }
    
    public LinkedStack(T data) {
        topNode = new Node(data);
    }
    
    
    
    @Override
    public void push(T newEntry) {
        topNode = new Node(newEntry, topNode);
    }

    @Override
    public T pop() {
        if (this.isEmpty())
            return null;
        
        T temp = (T) topNode.data;
        topNode = topNode.next;
        return temp;
    }

    @Override
    public T peek() {
        T top = null;
        if (topNode != null)
            top = (T) topNode.data;
        
        return top;
    }
    
    public T peek2() {
        if ( this.isEmpty() || topNode.next == null  )
                return null;

        return (T) this.topNode.next.data;
        
    }
    
    public void displayBackwards() {
        displayChainBackwards(topNode);
    }
    
    private void displayChainBackwards(Node n) {
        if ( n != null ) {
            displayChainBackwards(n.next);
            System.out.println(n.data);
        }
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public void clear() {
        topNode = null;
    }
    
    private class Node<T> {
        private T data;
        private Node next;
        
        private Node(T data) {
            this.data = data;
        }
        
        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
        
        private T getData() {
            return data;
        }
        
    } //end node
    
}
