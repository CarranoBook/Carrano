/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter21;
/**
 * @author Nathan Bleier
 */
public class LabC21Q1p601 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String[] a = new String[101];
        
        String s = "Java";
        a[s.hashCode() % 101] = s;
        a["Reed".hashCode() % 101] = "Reed";
        
        int index = 0;
        for ( String st : a) {
            if ( st != null )
                System.out.println(index + " " + st);
            index++;
        }
        
    }
}
