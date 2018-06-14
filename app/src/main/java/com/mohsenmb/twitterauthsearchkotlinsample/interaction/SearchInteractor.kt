package com.mohsenmb.twitterauthsearchkotlinsample.interaction

import com.mohsenmb.twitterauthsearchkotlinsample.service.TwitterWebService
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.SearchResponse
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Token
import com.mohsenmb.twitterauthsearchkotlinsample.util.generateTwitterBearerAuthorization
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject
import javax.inject.Inject

/**
 * Created by mohsen on 6/11/18.
 */
interface SearchInteractor : BaseInteractor {
    fun searchTweets(query: String): Observable<SearchResponse>
    fun loadSearchResultsNextPage(nextResults: String): Observable<SearchResponse>
}

class SearchInteractorImpl @Inject constructor(private val twitterWebService: TwitterWebService,
                                               private val token: Token) : SearchInteractor {
    private var searchDisposable: Disposable? = null
    private lateinit var searchReplaySubject: ReplaySubject<SearchResponse>

    override fun searchTweets(query: String): Observable<SearchResponse> {
        if (searchDisposable == null || searchDisposable!!.isDisposed) {
            searchReplaySubject = ReplaySubject.create()
            twitterWebService.searchTweets(generateTwitterBearerAuthorization(token.accessToken), query = query)
                    .subscribeOn(Schedulers.io())
                    .subscribe(searchReplaySubject)
            searchDisposable = searchReplaySubject.subscribe({},
                    { error -> error.printStackTrace() })
        }
        return searchReplaySubject.hide()
    }

    override fun loadSearchResultsNextPage(nextResults: String): Observable<SearchResponse> {
        if (searchDisposable == null || searchDisposable!!.isDisposed) {
            twitterWebService
                    .loadSearchNextPage(generateTwitterBearerAuthorization(token.accessToken), nextResults)
                    .subscribeOn(Schedulers.io())
                    .subscribe(searchReplaySubject)
            searchDisposable = searchReplaySubject.subscribe({},
                    { error -> error.printStackTrace() })
        }
        return searchReplaySubject.hide()
    }

    override fun destroy() {
        if (searchDisposable != null &&
                !searchDisposable!!.isDisposed) {
            searchDisposable!!.dispose()
        }
    }

}