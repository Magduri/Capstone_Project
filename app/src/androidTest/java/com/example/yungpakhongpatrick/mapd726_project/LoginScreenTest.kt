package com.example.yungpakhongpatrick.mapd726_project

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    // This launches the MainActivity before each test
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // TEST 1: Login screen shows the username field
    @Test
    fun loginScreen_showsUsernameField() {
        onView(withId(R.id.etLoginUsername))
            .check(matches(isDisplayed()))
    }

    // TEST 2: Login screen shows the password field
    @Test
    fun loginScreen_showsPasswordField() {
        onView(withId(R.id.etLoginPassword))
            .check(matches(isDisplayed()))
    }

    // TEST 3: Login screen shows the Login button
    @Test
    fun loginScreen_showsLoginButton() {
        onView(withId(R.id.btnLogin))
            .check(matches(isDisplayed()))
    }

    // TEST 4: Tapping login with empty fields shows error
    @Test
    fun loginWithEmptyFields_showsError() {
        onView(withId(R.id.btnLogin)).perform(click())
        // After clicking with empty fields, the username field should show an error
        onView(withId(R.id.etLoginUsername))
            .check(matches(hasErrorText("Please enter a username/password")))
    }

    // TEST 5: Can type in the username field
    @Test
    fun canTypeInUsernameField() {
        onView(withId(R.id.etLoginUsername))
            .perform(typeText("testuser"), closeSoftKeyboard())
        onView(withId(R.id.etLoginUsername))
            .check(matches(withText("testuser")))
    }

    // TEST 6: Can type in the password field
    @Test
    fun canTypeInPasswordField() {
        onView(withId(R.id.etLoginPassword))
            .perform(typeText("mypassword"), closeSoftKeyboard())
        onView(withId(R.id.etLoginPassword))
            .check(matches(withText("mypassword")))
    }
}