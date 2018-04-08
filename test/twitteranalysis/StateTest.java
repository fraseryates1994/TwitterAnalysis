/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteranalysis;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fraser
 */
public class StateTest {
    
    public StateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCode method, of class State.
     */
    @Test
    public void testGetCode() {
        System.out.println("getCode");
        State instance = new State("AL","Alabama");
        String expResult = "AL";
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStateName method, of class State.
     */
    @Test
    public void testGetStateName() {
        System.out.println("getStateName");
        State instance = new State("AL","Alabama");
        String expResult = "Alabama";
        String result = instance.getStateName();
        assertEquals(expResult, result);
    }
    
}
