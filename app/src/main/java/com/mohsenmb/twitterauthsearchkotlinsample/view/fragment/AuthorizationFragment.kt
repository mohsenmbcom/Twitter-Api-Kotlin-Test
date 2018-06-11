package com.mohsenmb.twitterauthsearchkotlinsample.view.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mohsenmb.twitterauthsearchkotlinsample.R
import com.mohsenmb.twitterauthsearchkotlinsample.TwitterApplication
import com.mohsenmb.twitterauthsearchkotlinsample.di.AuthorizationModule
import com.mohsenmb.twitterauthsearchkotlinsample.presentation.AuthorizationPresenter
import com.mohsenmb.twitterauthsearchkotlinsample.util.isConnected
import com.mohsenmb.twitterauthsearchkotlinsample.view.AuthorizationView
import kotlinx.android.synthetic.main.fragment_authorization.*
import javax.inject.Inject

/**
 * Created by mohsen on 6/11/18.
 */
class AuthorizationFragment : BaseFragment(), AuthorizationView {

    init {
        TwitterApplication
                .INSTANCE
                .component
                ?.plus(AuthorizationModule(this))
                ?.inject(this)
    }

    @Inject
    lateinit var authorizationPresenter: AuthorizationPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_authorization, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorizationPresenter.authorize(context?.isConnected() ?: false)
    }

    override fun onAuthorized() {
        tvProgress.text = getString(R.string.authorized)
        prg.visibility = View.INVISIBLE
    }

    override fun showProgress() {
        prg.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        prg.visibility = View.INVISIBLE
    }

    override fun showRetry() {
        Snackbar
                .make(clAuthorization, R.string.please_try_again, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, {
                    authorizationPresenter.authorize(context?.isConnected() ?: false)
                })
                .show()
    }

}