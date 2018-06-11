package com.mohsenmb.twitterauthsearchkotlinsample.view

/**
 * Created by mohsen on 6/11/18.
 */
interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String)
    fun showOfflineMessage()
    fun showUnexpectedError()
    fun showRetry()
}