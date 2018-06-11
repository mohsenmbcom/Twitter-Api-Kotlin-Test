package com.mohsenmb.twitterauthsearchkotlinsample.view

import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Tweet

/**
 * Created by mohsen on 6/11/18.
 */
interface SearchTweetsView : BaseView {
    fun showTweets(tweets: List<Tweet>)
}