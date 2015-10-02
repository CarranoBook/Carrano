/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import carrano.StackInterface;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author nbleier
 */
@RunWith(Parameterized.class)
public class StackInterfaceTest {
    public StackInterface stack;
    
    public StackInterfaceTest(StackInterface stack) {
        this.stack = stack;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    public void shouldBeEmptyIfStackIsEmpty() {
        StackInterface<String> stack = new StackInterface<>();
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
