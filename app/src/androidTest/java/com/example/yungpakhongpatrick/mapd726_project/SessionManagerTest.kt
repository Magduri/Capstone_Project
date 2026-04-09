package com.example.yungpakhongpatrick.mapd726_project

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull

@RunWith(AndroidJUnit4::class)
class SessionManagerTest {

    private lateinit var sessionManager: SessionManager

    @Before
    fun setup() {
        val ctx = InstrumentationRegistry.getInstrumentation().targetContext
        sessionManager = SessionManager(ctx)
        sessionManager.clearSession()
    }

    @Test
    fun newSession_isNotLoggedIn() {
        assertFalse(sessionManager.isLoggedIn())
    }

    @Test
    fun afterSave_isLoggedIn() {
        sessionManager.saveUserSession("abc123", "john")
        assertTrue(sessionManager.isLoggedIn())
    }

    @Test
    fun afterSave_getUserId_returnsCorrectId() {
        sessionManager.saveUserSession("abc123", "john")
        assertEquals("abc123", sessionManager.getUserId())
    }

    @Test
    fun afterSave_getUserName_returnsCorrectName() {
        sessionManager.saveUserSession("abc123", "john")
        assertEquals("john", sessionManager.getUserName())
    }

    @Test
    fun afterClear_isNotLoggedIn() {
        sessionManager.saveUserSession("abc123", "john")
        sessionManager.clearSession()
        assertFalse(sessionManager.isLoggedIn())
    }

    @Test
    fun afterClear_getUserId_returnsNull() {
        sessionManager.saveUserSession("abc123", "john")
        sessionManager.clearSession()
        assertNull(sessionManager.getUserId())
    }
}