/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carrano;

import java.util.Objects;

/**
 *
 * @author FukYu
 */
public class OrderedPair<L,R> {
    private L left;
    private R right;
    
    OrderedPair() {
        this(null, null);
    } //end default constructor
    
    OrderedPair(L left, R right) {
        this.left = left;
        this.right = right;
    } //end constructor
    
    public L getLeft() {
        return left;
    }
    
    public R getRight() {
        return right;
    }
    
    public void setLeft(L left) {
        this.left = left;
    }
    
    public void setRight(R right) {
        this.right = right;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderedPair))
            return false;
        
        OrderedPair check = (OrderedPair) o;
        
        return this.left.equals(check.getLeft()) && this.right.equals(check.getRight());
    }

     @Override
     public int hashCode() { return left.hashCode() ^ right.hashCode(); }
    
}
