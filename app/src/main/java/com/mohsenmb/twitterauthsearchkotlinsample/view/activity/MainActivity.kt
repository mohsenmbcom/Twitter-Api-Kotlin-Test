package com.mohsenmb.twitterauthsearchkotlinsample.view.activity

import android.content.res.Configuration
import android.os.Bundle
import com.mohsenmb.twitterauthsearchkotlinsample.R
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Tweet
import com.mohsenmb.twitterauthsearchkotlinsample.view.fragment.ARG_TWEET
import com.mohsenmb.twitterauthsearchkotlinsample.view.fragment.AuthorizationFragment
import com.mohsenmb.twitterauthsearchkotlinsample.view.fragment.SearchTweetsFragment
import com.mohsenmb.twitterauthsearchkotlinsample.view.fragment.TweetFragment

const val ARG_INIT_FRAGMENT = "init_fragment"
class MainActivity : BaseActivity() {
    var initFragment = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment = savedInstanceState?.getBoolean(ARG_INIT_FRAGMENT, true) ?: true
        if (initFragment) {
            initFragment = false
            showAuthorizationFragment()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(ARG_INIT_FRAGMENT, initFragment)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        initFragment = savedInstanceState?.getBoolean(ARG_INIT_FRAGMENT, true) ?: true
    }

    fun showAuthorizationFragment() {
        supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.flContainer, AuthorizationFragment())
                ?.commit()
    }

    fun showSearchFragment() {
        supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.flContainer, SearchTweetsFragment())
                ?.commit()
    }

    fun showTweetFragment(tweet: Tweet) {
        val tweetFragment = TweetFragment()
        val args = Bundle()
        args.putParcelable(ARG_TWEET, tweet)
        tweetFragment.arguments = args
        supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.flTweetContainer, tweetFragment)
                ?.commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager?.findFragmentById(R.id.flTweetContainer)
        if (fragment != null) {
            supportFragmentManager
                    ?.beginTransaction()
                    ?.remove(fragment)
                    ?.commit()
        } else {
            super.onBackPressed()
        }
    }
}
