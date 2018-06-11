package com.mohsenmb.twitterauthsearchkotlinsample

import android.app.Application
import com.mohsenmb.twitterauthsearchkotlinsample.di.AndroidModule
import com.mohsenmb.twitterauthsearchkotlinsample.di.DaggerMainComponent
import com.mohsenmb.twitterauthsearchkotlinsample.di.MainComponent


/**
 * Created by mohsen on 6/11/18.
 */
class TwitterApplication : Application() {
    companion object {
        lateinit var INSTANCE: TwitterApplication
    }

    var component: MainComponent? = null

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        component = createComponent()
    }

    private fun createComponent(): MainComponent {
        return DaggerMainComponent
                .builder()
                .androidModule(AndroidModule(this))
                .build()
    }
}