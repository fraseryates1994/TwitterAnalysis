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
public class CountryTest {
    
    public CountryTest() {
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
     * Test of getCode method, of class Country.
     */
    @Test
    public void testGetCode() {
        System.out.println("getCode");
        Country instance =  new Country("AT","Austria","EU");
        String expResult = "AT";
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCountry method, of class Country.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        Country instance = new Country("AT","Austria","EU");
        String expResult = "Austria";
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of getContinentCode method, of class Country.
     */
    @Test
    public void testGetContinentCode() {
        System.out.println("getContinentCode");
        Country instance = new Country("AT","Austria","EU");
        String expResult = "EU";
        String result = instance.getContinentCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Country.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Country instance = new Country("AT","Austria","EU");
        String expResult = "Country{" + "code=" + "AT" + ", country=" + "Austria" + ", continentCode=" + "EU" + '}';
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
