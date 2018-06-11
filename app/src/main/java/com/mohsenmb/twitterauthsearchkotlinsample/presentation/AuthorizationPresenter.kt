package com.mohsenmb.twitterauthsearchkotlinsample.presentation

import com.mohsenmb.twitterauthsearchkotlinsample.interaction.AuthorizationInteractor
import com.mohsenmb.twitterauthsearchkotlinsample.util.SessionManager
import com.mohsenmb.twitterauthsearchkotlinsample.view.AuthorizationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by mohsen on 6/11/18.
 */
interface AuthorizationPresenter : BasePresenter<AuthorizationView> {
    fun authorize(isConnected: Boolean)
}

class AuthorizationPresenterImpl @Inject constructor(private val sessionManager: SessionManager,
                                                     private val authorizationInteractor: AuthorizationInteractor)
    : AuthorizationPresenter {

    lateinit var authorizationView: AuthorizationView

    private lateinit var disposable: Disposable

    override fun setView(view: AuthorizationView) {
        this.authorizationView = view
    }

    override fun authorize(isConnected: Boolean) {
        authorizationView.showProgress()
        disposable = authorizationInteractor
                .authorize()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    sessionManager.saveSessionToken(it)
                    authorizationView.hideProgress()
                    authorizationView.onAuthorized()
                }, {
                    authorizationView.hideProgress()

                    if (isConnected) {
                        authorizationView.showUnexpectedError()
                        authorizationView.showRetry()
                    } else {
                        authorizationView.showOfflineMessage()
                    }
                })
    }

    override fun destroy() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        authorizationInteractor.destroy()
    }
}