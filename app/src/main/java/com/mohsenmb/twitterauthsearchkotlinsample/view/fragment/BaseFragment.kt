package com.mohsenmb.twitterauthsearchkotlinsample.view.fragment

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.widget.Toast
import com.mohsenmb.twitterauthsearchkotlinsample.R
import com.mohsenmb.twitterauthsearchkotlinsample.view.BaseView
import kotlinx.android.synthetic.main.fragment_search_tweets.*

/**
 * Created by mohsen on 6/11/18.
 */
abstract class BaseFragment : Fragment(), BaseView {
    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showOfflineMessage() {
        showMessage(getString(R.string.offline_message))
    }

    override fun showUnexpectedError() {
        showMessage(getString(R.string.unexpected_error_message))
    }

    override fun showRetry() {
        Snackbar
                .make(clSearch, R.string.please_try_again, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, {
                    retry()
                })
                .show()
    }

    abstract fun retry()
}