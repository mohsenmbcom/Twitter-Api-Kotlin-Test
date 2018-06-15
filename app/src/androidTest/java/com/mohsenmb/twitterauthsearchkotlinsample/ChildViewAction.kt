package com.mohsenmb.twitterauthsearchkotlinsample

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.view.View
import org.hamcrest.Matcher


/**
 * Created by mohsen on 6/16/18.
 */
object ChildViewAction {

    fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun perform(uiController: UiController?, view: View?) {
                if (view != null) {
                    val v: View = view.findViewById(id)
                    v.performClick()
                }
            }

            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }
        }
    }

}