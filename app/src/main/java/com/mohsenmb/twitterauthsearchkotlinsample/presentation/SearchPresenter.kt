package com.mohsenmb.twitterauthsearchkotlinsample.presentation

import com.mohsenmb.twitterauthsearchkotlinsample.interaction.SearchInteractor
import com.mohsenmb.twitterauthsearchkotlinsample.view.SearchTweetsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by mohsen on 6/11/18.
 */
interface SearchPresenter : BasePresenter<SearchTweetsView> {
    fun searchTweets(isConnected: Boolean, query: String)

    fun loadNextPage(isConnected: Boolean)
}

class SearchPresenterImpl @Inject constructor(private val interactor: SearchInteractor)
    : SearchPresenter {

    lateinit var searchTweetsView: SearchTweetsView
    var maxId: String? = null
    lateinit var query: String

    private lateinit var disposable: Disposable

    override fun destroy() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        interactor.destroy()
    }

    override fun setView(view: SearchTweetsView) {
        searchTweetsView = view
    }

    override fun searchTweets(isConnected: Boolean, query: String) {
        searchTweetsView.showProgress()
        disposable = interactor
                .searchTweets(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    searchTweetsView.hideProgress()
                    val canLoadMore = it.searchMetaData.nextResults != null
                    maxId = it.searchMetaData.maxId
                    this.query = it.searchMetaData.query
                    searchTweetsView.showTweets(it.tweets, canLoadMore)
                }, {
                    searchTweetsView.hideProgress()

                    if (isConnected) {
                        searchTweetsView.showUnexpectedError()
                        searchTweetsView.showRetry()
                    } else {
                        searchTweetsView.showOfflineMessage()
                    }
                })
    }

    override fun loadNextPage(isConnected: Boolean) {
        searchTweetsView.showProgress()
        if (maxId != null) {
            disposable = interactor
                    .loadSearchResultsNextPage(query, maxId!!)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        searchTweetsView.hideProgress()
                        searchTweetsView.showTweets(it.tweets, it.searchMetaData.nextResults != null)
                        maxId = it.searchMetaData.maxId
                    }, {
                        searchTweetsView.hideProgress()

                        if (isConnected) {
                            searchTweetsView.showUnexpectedError()
                        } else {
                            searchTweetsView.showOfflineMessage()
                        }
                    })
        } else {
            searchTweetsView.hideProgress()
            searchTweetsView.showUnexpectedError()
        }
    }

}