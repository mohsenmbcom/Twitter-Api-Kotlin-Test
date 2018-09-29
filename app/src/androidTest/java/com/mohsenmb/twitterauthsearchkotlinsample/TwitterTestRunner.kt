package com.mohsenmb.twitterauthsearchkotlinsample

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

/**
 * Created by mohsen on 6/15/18.
 */
class TwitterTestRunner: AndroidJUnitRunner() {
    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(classLoader: ClassLoader, className: String, context: Context): Application {
        // replace Application class with mock one
        return super.newApplication(classLoader, TwitterTestApplication::class.java.name, context)
    }
}