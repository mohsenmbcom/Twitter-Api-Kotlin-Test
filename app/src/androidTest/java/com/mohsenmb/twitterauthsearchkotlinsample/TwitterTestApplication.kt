package com.mohsenmb.twitterauthsearchkotlinsample

import com.mohsenmb.twitterauthsearchkotlinsample.di.*

/**
 * Created by mohsen on 6/15/18.
 */
class TwitterTestApplication: TwitterApplication() {
    companion object {
        @JvmStatic
        lateinit var mainComponent: MainComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = createComponent()
    }

    override fun createComponent(): MainComponent {
        mainComponent = DaggerMainTestComponent
                .builder()
                .apiModule(ApiTestModule())
                .androidModule(AndroidModule(this))
                .build()
        return mainComponent
    }
}