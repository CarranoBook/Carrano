/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrano;

/**
 *
 * @author nbleier
 */
public class Factorial {
    long val;
    
    public Factorial(long n) {
        val = check(n);
    }

    private long check(long n) {
        if ( n == 1 || n == 0 )
            return 1;
        else if ( n < 0 )
            throw new IllegalArgumentException("Must pass non-negative longeger");
        
        return fac(n);
    }

    private long fac(long n) {
        if ( n == 2 )
            return 2;
        else
            return n * fac(n-1);
    }
    
    public long get() {
        return val;
    }
    
    public String toString() {
        return Long.toString(val);
    }
    
    public static void main(String[] args) {
        System.out.println(new Factorial(6));
    }
}