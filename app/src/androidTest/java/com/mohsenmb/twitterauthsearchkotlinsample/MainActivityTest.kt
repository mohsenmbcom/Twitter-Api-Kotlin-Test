package com.mohsenmb.twitterauthsearchkotlinsample

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.recyclerview.widget.RecyclerView
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
    fun authorizationAndSearchPage_SimplePhraseSearch_ShowSearchList() {

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
        // end of story ;)
    }

    @Test
    fun searchAndTweetPage_PhraseSearch_ShowInnerPage() {

        mainActivity.launchActivity(Intent())

        // wait for loading the list
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.etSearch)).perform(typeText("DeadPool"),
                pressImeActionButton())


        // wait a sec
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        // scroll to the end of the list
        onView(withId(R.id.rvTweets))
                .perform(RecyclerViewActions.scrollToPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(9))


        // wait for the next page loading
        try {
            Thread.sleep(1500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        // scroll to the end of the list
        onView(withId(R.id.rvTweets))
                .perform(RecyclerViewActions.scrollToPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(19))


        // wait for 1.5 Second
        try {
            Thread.sleep(1500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        // click on the 18th item (an item of the second page) to show the details fragment
        onView(withId(R.id.rvTweets))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(18, ChildViewAction.clickChildViewWithId(R.id.btnViewTweet)))

        // wait for 1.5 Second
        try {
            Thread.sleep(1500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        pressBack()
        // end of story ;)
    }

    @Test
    fun searchAndTweetPage_PhraseSearchAndScreenRotation_ContinueUserJourney() {
        mainActivity.launchActivity(Intent())
        // wait for loading the list
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.etSearch)).perform(typeText("DeadPool"),
                pressImeActionButton())


        // wait a sec
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        
        // wait a sec
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        // scroll to the end of the list
        onView(withId(R.id.rvTweets))
                .perform(RecyclerViewActions.scrollToPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(9))

        // wait for the next page loading
        try {
            Thread.sleep(1500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        // Rotation
        mainActivity.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        // hide keyboard
        pressBack()
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        // scroll to the end of the list
        onView(withId(R.id.rvTweets))
                .perform(RecyclerViewActions.scrollToPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(19))


        // wait for 1.5 Second
        try {
            Thread.sleep(1500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        // click on the 18th item (an item of the second page) to show the details fragment
        onView(withId(R.id.rvTweets))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(14, ChildViewAction.clickChildViewWithId(R.id.btnViewTweet)))

        // wait for 1.5 Second
        try {
            Thread.sleep(1500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        // click on the 18th item (an item of the second page) to show the details fragment
        onView(withId(R.id.rvTweets))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(15, ChildViewAction.clickChildViewWithId(R.id.btnViewTweet)))

        // wait for 1.5 Second
        try {
            Thread.sleep(1500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        mainActivity.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // wait for 1.5 Sec
        try {
            Thread.sleep(1500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        pressBack()

        // wait for 1.5 Sec
        try {
            Thread.sleep(1500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        // scroll to the top of the list
        onView(withId(R.id.rvTweets))
                .perform(RecyclerViewActions.scrollToPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(0))

        // wait for 1.5 Sec
        try {
            Thread.sleep(1500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        // end of story ;)
    }
}
