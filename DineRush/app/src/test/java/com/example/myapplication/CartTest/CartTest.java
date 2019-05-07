package com.example.myapplication.CartTest;

import com.example.myapplication.Cart;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class CartTest {

    Cart cartInstance = new Cart();

    @Test
    public void testShortAddress() {
        boolean result = cartInstance.validateAddress("Imshrt");
        assertFalse(result);
    }

    @Test
    public void testInvalidAddress() {
        boolean result = cartInstance.validateAddress("@In@@**Valid");
        assertFalse(result);
    }
    @Test
    public void testAnotherInValidAddress() {
        boolean result = cartInstance.validateAddress("..valid..100AtSt.");
        assertTrue(result);
    }
    @Test
    public void testvalidAddress() {
        boolean result =  cartInstance.validateAddress("ValidSt.109");
        assertTrue(result);
    }
    @Test
    public void testanotherValidaddress() {
        boolean result = cartInstance.validateAddress("E Northgate Dr.");
        assertTrue(result);
    }


}
