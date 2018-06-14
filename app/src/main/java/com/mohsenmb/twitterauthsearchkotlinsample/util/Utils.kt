package com.mohsenmb.twitterauthsearchkotlinsample.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.util.Base64

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