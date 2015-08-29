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
public class ThreeTuple<L, M, R> {
    private L left;
    private M mid;
    private R right;
    
    ThreeTuple() {
        this(null, null, null);
    } //end default constructor
    
    ThreeTuple(L left, M mid, R right) {
        this.left = left;
        this.mid = mid;
        this.right = right;
    } //end constructor
    
    public L getLeft() {
        return left;
    }
    
    public M getMid() {
        return mid;
    }
    
    public R getRight() {
        return right;
    }
    
    public void setL(L left) {
        this.left = left;
    }
    
    public void setM(M mid) {
        this.mid = mid;
    }
    
    public void setR(R right) {
        this.right = right;
    }
    
    @Override
    public boolean equals(Object o) {
        if ( !(o instanceof ThreeTuple) ) return false;
        
        ThreeTuple check = (ThreeTuple) o;
        
        return this.left == check.getLeft() && this.mid == check.getMid() && this.right == check.getRight();
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.left);
        hash = 29 * hash + Objects.hashCode(this.mid);
        hash = 29 * hash + Objects.hashCode(this.right);
        return hash;
    }
    
}
