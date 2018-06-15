package com.mohsenmb.twitterauthsearchkotlinsample.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.Base64
import android.view.inputmethod.InputMethodManager

/**
 * Created by mohsen on 6/10/18.
 */

fun generateTwitterBasicTokenAuthorization(consumerKey: String, consumerSecret: String): String = "Basic ${"$consumerKey:$consumerSecret".toBase64()}"

fun generateTwitterBearerAuthorization(token: String): String = "Bearer $token"

fun String.toBase64(): String = Base64.encodeToString(this.toByteArray(), Base64.NO_WRAP)

fun Context.isConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}