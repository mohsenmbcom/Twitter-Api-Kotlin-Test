package com.mohsenmb.twitterauthsearchkotlinsample.presentation

import com.mohsenmb.twitterauthsearchkotlinsample.view.BaseView

/**
 * Created by mohsen on 6/11/18.
 */
interface BasePresenter<in T : BaseView> {
    fun destroy()

    fun setView(view: T)
}