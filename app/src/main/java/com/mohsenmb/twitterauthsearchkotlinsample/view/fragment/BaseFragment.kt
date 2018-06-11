package com.mohsenmb.twitterauthsearchkotlinsample.view.fragment

import android.support.v4.app.Fragment
import android.widget.Toast
import com.mohsenmb.twitterauthsearchkotlinsample.R
import com.mohsenmb.twitterauthsearchkotlinsample.view.BaseView

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
}