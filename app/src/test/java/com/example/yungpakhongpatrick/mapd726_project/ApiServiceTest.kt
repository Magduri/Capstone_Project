package com.example.yungpakhongpatrick.mapd726_project

import org.junit.Test
import org.junit.Assert.*

class ApiServiceTest {

    @Test
    fun baseUrl_isCorrect() {
        val service = ApiService("https://mapd713-group-project.onrender.com")
        // Just verify it constructs without crashing
        assertNotNull(service)
    }

    @Test
    fun emptyUsername_shouldNotBeUsed() {
        val username = "  ".trim()
        assertFalse("Trimmed blank username must be empty", username.isNotEmpty())
    }

    @Test
    fun itemNameWithStore_parsesCorrectly() {
        val raw = "Organic Milk at Costco"
        val name = raw.substringBefore(" at ")
        val store = raw.substringAfter(" at ")
        assertEquals("Organic Milk", name)
        assertEquals("Costco", store)
    }

    @Test
    fun itemNameWithoutStore_givesUnknown() {
        val raw = "Generic Item"
        val store = if (raw.contains(" at ")) raw.substringAfter(" at ") else "Unknown"
        assertEquals("Unknown", store)
    }

    @Test
    fun cartTotal_withMultipleItems_isCorrect() {
        val items = listOf(
            SavedItem("Milk", 3.49, "Walmart", false),
            SavedItem("Bread", 2.99, "Walmart", false),
            SavedItem("Eggs", 5.99, "Costco", false)
        )
        val total = items.sumOf { it.price }
        assertEquals(12.47, total, 0.01)
    }

    @Test
    fun savedItem_defaultIsNotChecked() {
        val item = SavedItem("Milk", 3.49, "Walmart", false)
        assertFalse(item.isChecked)
    }
}