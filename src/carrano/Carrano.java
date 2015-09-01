/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carrano;
//A558F2A6C98207E93B779FB4070F1A4B
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author NBleier
 */
public class Carrano {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String one, two;
        one ="";
        two ="";
        
        LogicParser test;
        try {
            test = new LogicParser("!(!(!p^q))");
            one = test.generateTruthTable();
            LogicParser test2 = new LogicParser("!p^q");
            two = test2.generateTruthTable();
            
            System.out.println(test.equals(test2));
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println(one);
        System.out.println(two);
    } //end main
    
   
    
    public static int recurCount(int n, int m) {
        if ( n < m ) {
            return recurCount(n+1, m);
        }
        
        return m;
    }
    
    public static int itCount(int n, int m) {
        while ( n <=m ) {
            n++;
        }
        return n;
    }
    
    public static long loopB(int n) {
        Date current = new Date();
        long startTime = current.getTime();
        int sum = 0;
        
        for ( int i = 1; i <= n; i++) {
            for ( int j = 1; j <= n; j++ ) 
                sum += j;
        } //end for
        
        current = new Date();
        long stopTime = current.getTime();
        long elapsedTime = stopTime - startTime;
        
        return elapsedTime;
        
    }
    
    
    
    
}

