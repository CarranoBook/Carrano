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
public class ArraySet<T> implements SetInterface<T> {
    
    ResizeableArrayBag<T> set;
    
    ArraySet() {
        set = new ResizeableArrayBag<>();
    } //end default constructor

    @Override
    public boolean add(T newEntry) {
        if ( this.contains(newEntry) ) return false;
        
        set.add(newEntry);
        return true;
    }

    @Override
    public boolean remove(Object anEntry) {
        if ( !(this.contains(anEntry)) ) return false;
        
        T entry = (T) anEntry;
        set.remove(entry);
        return true;
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public boolean contains(Object anEntry) {
        if (anEntry == null) return false;        
        
        return set.contains((T) anEntry);
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public int size() {
        return set.getCurrentSize();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }
    
}
