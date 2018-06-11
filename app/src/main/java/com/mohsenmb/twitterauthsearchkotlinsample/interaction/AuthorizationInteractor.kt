package com.mohsenmb.twitterauthsearchkotlinsample.interaction

import com.mohsenmb.twitterauthsearchkotlinsample.service.TwitterWebService
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Token
import com.mohsenmb.twitterauthsearchkotlinsample.util.SessionManager
import com.mohsenmb.twitterauthsearchkotlinsample.util.generateTwitterBasicTokenAuthorization
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject
import javax.inject.Inject

/**
 * Created by mohsen on 6/11/18.
 */
interface AuthorizationInteractor : BaseInteractor {
    fun authorize(): Observable<Token>
}

class AuthorizationInteractorImpl @Inject constructor(private val twitterWebService: TwitterWebService,
                                                      private val sessionManager: SessionManager) : AuthorizationInteractor {
    private var authorizationDisposable: Disposable? = null
    private var authorizationReplaySubject: ReplaySubject<Token> = ReplaySubject.create()

    override fun authorize(): Observable<Token> {
        if (authorizationDisposable == null || authorizationDisposable!!.isDisposed) {
            twitterWebService
                    .authorize(generateTwitterBasicTokenAuthorization(sessionManager.getConsumerKey(),
                            sessionManager.getConsumerSecret()))
                    .subscribeOn(Schedulers.io())
                    .subscribe(authorizationReplaySubject)
            authorizationDisposable = authorizationReplaySubject.subscribe({},
                    { error -> error.printStackTrace() })
        }
        return authorizationReplaySubject.share()
    }

    override fun destroy() {
        if (authorizationDisposable != null &&
                !authorizationDisposable!!.isDisposed) {
            authorizationDisposable!!.dispose()
        }
    }
}