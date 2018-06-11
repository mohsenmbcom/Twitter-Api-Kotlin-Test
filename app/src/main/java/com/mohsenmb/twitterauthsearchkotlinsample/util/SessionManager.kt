package com.mohsenmb.twitterauthsearchkotlinsample.util

import android.content.SharedPreferences
import android.content.res.Resources
import com.mohsenmb.twitterauthsearchkotlinsample.R
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Token
import javax.inject.Inject

/**
 * Created by mohsen on 6/11/18.
 */
class SessionManager @Inject constructor(private val resources: Resources,
                                         private val sharedPreferences: SharedPreferences) {

    fun getConsumerKey(): String = resources.getString(R.string.twitter_consumer_key)

    fun getConsumerSecret(): String = resources.getString(R.string.twitter_consumer_secret)

    fun saveSessionToken(token: Token) {
        sharedPreferences
                .edit()
                .putString("bearerToken", token.accessToken)
                .apply()
    }

    fun getSessionToken(): Token = Token("bearer",
            sharedPreferences.getString("bearerToken", ""))
}