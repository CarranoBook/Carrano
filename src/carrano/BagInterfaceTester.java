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
public class BagInterfaceTester {
    
    public static void main(String[] args) {
    
        LinkedBag<String> aBag = new LinkedBag<>();
        LinkedBag<String>bBag = new LinkedBag<>();

        String[] contentsOfBag1 = {"A", "B", "C", "GD", "B", "C", "D"};
        String s = "Hello my darling hello my gal";
        System.out.println(s.split("\\s+"));
        
        //LinkedBag<String>bBag = new LinkedBag<>();
        
        /*
        testGetFrequencyOf(aBag, "A", 1);
        testGetFrequencyOf(aBag, "B", 2);
        testGetFrequencyOf(aBag, "C", 2);
        */
        
        

    } //end main
    
 private static void testDifference(ResizeableArrayBag<String> aBag, ResizeableArrayBag<String> bBag) {
        displayBag(aBag);
        displayBag(bBag);
        System.out.println("difference of the bags is: ");
        displayBag(aBag.difference(bBag));
    }
    
    private static void testIntersection(LinkedBag<String> aBag, LinkedBag<String> bBag) {
        displayBag(aBag);
        displayBag(bBag);
        
        System.out.println("Intersection of the bags is: ");
        displayBag(aBag.intersection(bBag));
    }
    
    
    private static void testUnion(LinkedBag<String> aBag, LinkedBag<String> bBag) {
        displayBag(aBag);
        displayBag(bBag);
        
        System.out.println("Union of the bags is: ");
        displayBag(aBag.union(bBag));
    }
    
    
    private static void testReplaceAll(LinkedBag<String> aBag, String A, String B) {
        System.out.println("Before replacement: ");
        displayBag(aBag);
        System.out.println("After replacement: ");
        aBag.replaceAll(A, B);
        displayBag(aBag);
    }
    
    private static void testReplace(LinkedBag<String> aBag, String A, String B) {
        System.out.println("Before replacement: ");
        displayBag(aBag);
        System.out.println("After replacement: ");
        aBag.replace(A, B);
        displayBag(aBag);
    }
    
    private static void testArrayConstructor(String[] arr) {
        LinkedBag<String> aBag = new LinkedBag<>(arr);
        
        displayBag(aBag);
    }
    
    private static void testRemoveString(BagInterface<String> aBag, String content) {
        System.out.println("\nRemoving " + content + " from bag: " );
        displayBag(aBag);
        aBag.remove(content);
        System.out.println(content + " removed.");
        displayBag(aBag);
        
    }
    
    private static void testRemove(BagInterface<String> aBag) {
        System.out.println("\nRemoving element from bag: ");
        System.out.println(aBag.getCurrentSize() + " elements before removal: ");
        displayBag(aBag);
        System.out.println("Removing \'" + aBag.remove() + "\' from bag.");
        System.out.println(aBag.getCurrentSize() + " elements after removal: ");
        displayBag(aBag);
    }
    
    private static void testAdd(BagInterface<String> aBag, String[] content) {
        System.out.print("Adding to the bag: ");
        for (int i = 0; i < content.length; i++ ) {
            aBag.add(content[i]);
            System.out.print(content[i] + " ");
        } //end for
        
        System.out.println();
        
        displayBag(aBag);
    } //end testAdd

    private static void testIsFull(BagInterface<String> aBag, boolean correctResult) {
        System.out.print("\nTesting the method isFull with ");
        if (correctResult)
            System.out.println("a full bag: ");
        else
            System.out.println("a bag that is not full: ");
        
        System.out.print("isFull finds the bag ");
        if (correctResult && aBag.isFull())
            System.out.println("full: OK.");
        else if (correctResult)
            System.out.println("not full, but it is full: ERROR.");
        else if (!correctResult && aBag.isFull())
            System.out.println("full, but it is not full: ERROR.");
        else
            System.out.println("not full: OK.");
    } //end testIsull
    
    private static void testContains(BagInterface<String> aBag, String content, boolean correctResult) {
        System.out.print("\nTesting the method contains with ");
        if (correctResult)
            System.out.println("a bag containing: " + content);
        else
            System.out.println("a bag that does not contain: " + content);
        
        System.out.print("contains finds the bag ");
        if (correctResult && aBag.contains(content))
            System.out.println("contains: " + content + " Good!");
        else if (correctResult)
            System.out.println("does not contain " + content + ". ERROR.");
        else if (!correctResult && aBag.isFull())
            System.out.println("Contains " + content + "But it shouldn't: ERROR.");
        else
            System.out.println("Does not contain " + content + ".  Good!");
    } //end testContains
    
    private static void displayBag(BagInterface<String> aBag) {
        System.out.println("The bag contains the following string(s):" );
        Object[] bagArray = aBag.toArray();
        for (Object bagArray1 : bagArray) {
            System.out.print(bagArray1 + " ");
        } // end for
        System.out.println();
    } //end displayBag
    
    private static void testGetFrequencyOf(BagInterface<String> aBag, String content, int answer) {
        System.out.println("The bag contains " + answer + " " + content + "\'s");
        System.out.println("getFrequencyOf returns " + aBag.getFrequencyOf(content));
    }

    private static void getAllLessGreater(ResizeableArrayBag<String> aBag, String A) {
        ResizeableArrayBag<String> temp;
        
        displayBag(aBag);
        System.out.println("All items less than " + A + ": ");
        temp = aBag.getAllLessThan(A);
        displayBag(temp);
        System.out.println("All items greater than " + A + ": ");
        temp = aBag.getAllGreaterThan(A);
        displayBag(temp);
    }

}//end BagInterfaceTester
