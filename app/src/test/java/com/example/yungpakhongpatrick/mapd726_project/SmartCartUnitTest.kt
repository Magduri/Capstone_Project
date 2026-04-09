package com.example.yungpakhongpatrick.mapd726_project

import org.junit.Test
import org.junit.Assert.*

class SmartCartUnitTest {

    // TEST 1: Username validation - must not be empty
    @Test
    fun emptyUsername_isInvalid() {
        val username = ""
        assertFalse("Empty username should be invalid", username.isNotEmpty())
    }

    // TEST 2: Valid username passes
    @Test
    fun validUsername_isValid() {
        val username = "john123"
        assertTrue("Non-empty username should be valid", username.isNotEmpty())
    }

    // TEST 3: Price calculation - total of cart items
    @Test
    fun cartTotal_calculatesCorrectly() {
        val prices = listOf(2.99, 5.49, 1.25)
        val total = prices.sum()
        assertEquals("Cart total should be 9.73", 9.73, total, 0.01)
    }

    // TEST 4: Store name is extracted from "Milk at Walmart"
    @Test
    fun extractStoreName_fromItemName() {
        val itemName = "Milk at Walmart"
        val store = if (itemName.contains(" at ")) itemName.substringAfter(" at ") else "Unknown"
        assertEquals("Should extract 'Walmart'", "Walmart", store)
    }

    // TEST 5: Product name is extracted from "Milk at Walmart"
    @Test
    fun extractProductName_fromItemName() {
        val itemName = "Milk at Walmart"
        val name = if (itemName.contains(" at ")) itemName.substringBefore(" at ") else itemName
        assertEquals("Should extract 'Milk'", "Milk", name)
    }

    // TEST 6: Item with no store info defaults to "Unknown"
    @Test
    fun itemWithNoStore_defaultsToUnknown() {
        val itemName = "Bread"
        val store = if (itemName.contains(" at ")) itemName.substringAfter(" at ") else "Unknown"
        assertEquals("Should default to Unknown", "Unknown", store)
    }

    // TEST 7: Empty list total is 0
    @Test
    fun emptyCart_totalIsZero() {
        val prices = emptyList<Double>()
        assertEquals(0.0, prices.sum(), 0.001)
    }

    // TEST 8: Password must not be empty
    @Test
    fun emptyPassword_isInvalid() {
        val password = "   ".trim()
        assertFalse("Blank password should be invalid", password.isNotEmpty())
    }
}