/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrano;

/**
 *
 * @author nbleier
 * @param <E> generic type which implements Comparable
 */
public class MyBinaryTree<E extends Comparable> {
    Node root;
    
    public MyBinaryTree() {
        root = null;
    }
    
    public MyBinaryTree(E rootData) {
        root = new Node(rootData);
    }
    
    //find, insert, delete
    
    public void insert(E data) {
        
        
        
    }
    
    
    
    private class Node<E extends Comparable> {
        Node left;
        Node right;
        E data;
        
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
        
        public void setLeft(Node l) {
            this.left = l;
        }
        
        public void setRight(Node r) {
            this.right = r;
        }
        
        public E getData() { return data;}
        
        public Node getLeft() { return left;}
        
        public Node getRight() { return right;}
        
    }
}
