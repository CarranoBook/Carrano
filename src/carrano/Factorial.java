/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrano;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author nbleier
 */
public class Factorial {
    long val;
    
    public Factorial(long n) {
        val = check(n);
    } //end constructor

    /**
     * This method checks to see that the factorial operation is defined for the value passed.
     * Although this isn't particularlly important for this particular algorithm, it can be very useful for
     * certain other recursive algorithms
     * @param n - value to be factorialed
     */
    private long check(long n) {
        if ( n == 1 || n == 0 )
            return 1;
        else if ( n < 0 || n > 20)
            throw new IllegalArgumentException("Must pass non-negative integer <= 20");
        
        return fac(n);
    } //end check
    
    /**
     * Calculates the value of n!
     * @param n - value to be factorialed
     */
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
        Scanner scan = new Scanner(System.in);
        long n;
        while (true) {
            try {
                System.out.println("Please enter an integer between 0 and 20: ");
                if (scan.hasNextLong()) {
                    n = scan.nextLong();
                    System.out.println(n + "! is " + new Factorial(n));
                }
                else {
                    scan.next();
                    continue;
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(e);
                continue;
            }
        }
    }
}
