package com.mohsenmb.twitterauthsearchkotlinsample

import android.content.Intent

import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.mohsenmb.twitterauthsearchkotlinsample.view.activity.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    public val mainActivity: ActivityTestRule<MainActivity> = ActivityTestRule(
            MainActivity::class.java,
            true,
            // false: do not launch the activity immediately
            false)

    @Before
    fun setUp() {

    }

    @Test
    fun testIt() {

        mainActivity.launchActivity(Intent())

        // wait for loading the list
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.etSearch)).perform(typeText("Hello"),
                pressImeActionButton())


        // wait a sec
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        // back to the grid
//        pressBack()
        // end of story ;)
    }
}
