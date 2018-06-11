package com.mohsenmb.twitterauthsearchkotlinsample.view.activity

import android.os.Bundle
import com.mohsenmb.twitterauthsearchkotlinsample.R
import com.mohsenmb.twitterauthsearchkotlinsample.view.fragment.AuthorizationFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, AuthorizationFragment())
                .commit()
    }
}
