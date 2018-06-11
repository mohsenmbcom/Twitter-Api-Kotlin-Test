package com.mohsenmb.twitterauthsearchkotlinsample.view.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.mohsenmb.twitterauthsearchkotlinsample.R
import com.mohsenmb.twitterauthsearchkotlinsample.TwitterApplication
import com.mohsenmb.twitterauthsearchkotlinsample.di.SearchTweetsModule
import com.mohsenmb.twitterauthsearchkotlinsample.presentation.SearchPresenter
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Tweet
import com.mohsenmb.twitterauthsearchkotlinsample.util.isConnected
import com.mohsenmb.twitterauthsearchkotlinsample.view.SearchTweetsView
import com.mohsenmb.twitterauthsearchkotlinsample.view.components.TweetsAdapter
import kotlinx.android.synthetic.main.fragment_search_tweets.*
import javax.inject.Inject

/**
 * Created by mohsen on 6/11/18.
 */
class SearchTweetsFragment : BaseFragment(), SearchTweetsView {

    init {
        TwitterApplication
                .INSTANCE
                .component
                ?.plus(SearchTweetsModule(this))
                ?.inject(this)
    }

    var firstPage = true
    var tweets: MutableList<Tweet> = mutableListOf()

    @Inject
    lateinit var presenter: SearchPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_tweets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (v.text.trim().length > 2) {
                    firstPage = true
                    presenter.searchTweets(context?.isConnected()
                            ?: false, v.text.toString().trim())
                }
            }
            true
        }
        srlTweets.setOnRefreshListener {
            search()
        }
        rvTweets.adapter = TweetsAdapter(tweets)
        rvTweets.layoutManager = LinearLayoutManager(context)
    }

    private fun search() {
        if (etSearch.text.trim().length > 2) {
            firstPage = true
            presenter.searchTweets(context?.isConnected()
                    ?: false, etSearch.text.toString().trim())
        } else {
            srlTweets.isRefreshing = false
        }
    }

    override fun showTweets(tweets: List<Tweet>) {
        if (firstPage) {
            this.tweets.clear()
            this.tweets.addAll(tweets)
            rvTweets.adapter.notifyDataSetChanged()
        } else {
            val lastIndex = this.tweets.size
            this.tweets.addAll(tweets)
            rvTweets.adapter.notifyItemRangeInserted(lastIndex, tweets.size)
        }
    }

    override fun showRetry() {
        Snackbar
                .make(clSearch, R.string.please_try_again, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, {
                    search()
                })
                .show()
    }

    override fun hideProgress() {
        srlTweets.isRefreshing = false
    }

    override fun showProgress() {
        srlTweets.isRefreshing = true
    }
}