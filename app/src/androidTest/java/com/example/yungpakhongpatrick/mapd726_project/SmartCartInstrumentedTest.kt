package com.example.yungpakhongpatrick.mapd726_project

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class SmartCartInstrumentedTest {

    // TEST 1: App package name is correct
    @Test
    fun app_hasCorrectPackageName() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(
            "com.example.yungpakhongpatrick.mapd726_project",
            appContext.packageName
        )
    }

    // TEST 2: SharedPreferences can save and retrieve user ID
    @Test
    fun sharedPrefs_savesAndRetrievesUserId() {
        val ctx = InstrumentationRegistry.getInstrumentation().targetContext
        val prefs = ctx.getSharedPreferences("SmartCartPrefs", android.content.Context.MODE_PRIVATE)
        prefs.edit().putString("CURRENT_USER_ID", "test_user_123").apply()
        val result = prefs.getString("CURRENT_USER_ID", null)
        assertEquals("Saved user ID should be retrievable", "test_user_123", result)
    }

    // TEST 3: SharedPreferences can clear session
    @Test
    fun sharedPrefs_clearSession_removesUserId() {
        val ctx = InstrumentationRegistry.getInstrumentation().targetContext
        val prefs = ctx.getSharedPreferences("SmartCartPrefs", android.content.Context.MODE_PRIVATE)
        prefs.edit().putString("CURRENT_USER_ID", "some_user").apply()
        prefs.edit().remove("CURRENT_USER_ID").apply()
        val result = prefs.getString("CURRENT_USER_ID", null)
        assertNull("User ID should be null after clearing session", result)
    }

    // TEST 4: Can save and read phone number in SharedPreferences
    @Test
    fun sharedPrefs_savesPhoneNumber() {
        val ctx = InstrumentationRegistry.getInstrumentation().targetContext
        val prefs = ctx.getSharedPreferences("SmartCartPrefs", android.content.Context.MODE_PRIVATE)
        prefs.edit().putString("USER_PHONE", "416-555-0000").apply()
        val phone = prefs.getString("USER_PHONE", "Add Phone Number")
        assertEquals("Phone number should be saved", "416-555-0000", phone)
    }

    // TEST 5: Base URL string resource is not empty
    @Test
    fun baseUrl_isNotEmpty() {
        val ctx = InstrumentationRegistry.getInstrumentation().targetContext
        val baseUrl = ctx.getString(R.string.base_url)
        assertTrue("Base URL must not be empty", baseUrl.isNotEmpty())
        assertTrue("Base URL must start with https", baseUrl.startsWith("https"))
    }
}