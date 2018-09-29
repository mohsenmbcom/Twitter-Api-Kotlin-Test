package com.mohsenmb.twitterauthsearchkotlinsample.view.fragment

import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.mohsenmb.twitterauthsearchkotlinsample.R
import com.mohsenmb.twitterauthsearchkotlinsample.view.BaseView
import com.mohsenmb.twitterauthsearchkotlinsample.view.activity.MainActivity

/**
 * Created by mohsen on 6/11/18.
 */
abstract class BaseFragment : androidx.fragment.app.Fragment(), BaseView {
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
                .make(view!!, R.string.please_try_again, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, {
                    retry()
                })
                .show()
    }

    abstract fun retry()

    fun getMainActivity(): MainActivity? {
        if (activity != null && activity is MainActivity) {
            return activity as MainActivity
        }
        return null
    }
}