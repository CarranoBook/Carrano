/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Homework;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 *
 * @author nbleier
 */
public class IteratorExample {
    
    public static void main(String[] args) {
        ArrayList<String> l0 = new ArrayList<>();
        l0.add("Jamie");
        l0.add("Joey");
        l0.add("Rachel");
        l0.add("Micah");
        l0.add("Holly");
        
        
        //Question 1
        ListIterator<String> l0it = l0.listIterator();
        System.out.println(l0it.hasNext());
        
        while(l0it.hasNext()) {
            System.out.print(l0it.next() + " ");
            try {
                l0it.next();
            }
            catch (NoSuchElementException e) {}
        }
        
        //Question 2
        l0it = l0.listIterator();
        l0it.next();
        l0it.remove();
        l0it.next();
        l0it.remove();
        System.out.println(l0it.next());
        
        //Question 3
        l0.add("Reed");
        l0.add("William");
        l0.add("Reed");
        l0.add("John");
        
        l0it = l0.listIterator();
        while(l0it.hasNext()) {
            System.out.println(l0it.next());
        }
        
        //Question 4
        l0it = l0.listIterator();
        while (l0it.hasNext()) {
            l0it.next();
            l0it.remove();
        }
        
        System.out.println(l0);
        
        
        
    }
    
}
