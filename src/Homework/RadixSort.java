/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Homework;

/**
 *
 * @author nbleier
 */
public class RadixSort {
    private int[] arr;
    
    public RadixSort(int[] arr) {
        this.arr = arr;
    }
    
    public void lsd() {
        int j = 3;
        
    }
    
    public void msd() {
        
    }
    
    public static void main(String[] args) {
        int bitmask = 0x0000001;
        int val = 0x312e1423;
        System.out.println(val);
        for (int i = 0; i < 32; i++) {
            System.out.println(bitmask & val);
            bitmask=bitmask<<1;
        }
        
    }
    
}
